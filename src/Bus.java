public class Bus implements Runnable {
    private final SharedResources SharedResources;
    public int passengers = 0;

    public Bus(SharedResources SharedResources) {
        this.SharedResources = SharedResources;
    }

    private void depart() {
        System.out.println("There are " + passengers + " riders in the Bus and " + SharedResources.getWaitingRiders() + " riders are left");
        System.out.println("***** BUS DEPARTED NOW !!! *** \n");
    }

    @Override
    public void run() {
        try {
            SharedResources.getMutex().acquire();              //avoid new riders when bus is at stop
                System.out.println("***** NOTICE - BUS ARRIVED !!! *** \n");

                int waitingRiders = SharedResources.getWaitingRiders();
                System.out.println( waitingRiders + "Riders are waiting for Bus");

                int canBoard = Math.min(SharedResources.getWaitingRiders(),50);
                System.out.println(canBoard + "can board to Bus" );

                if (SharedResources.getWaitingRiders() > 0) {
                    SharedResources.setBus(this);
                    SharedResources.getBusWait().release();    //Riders count when bus arrived to the halt
                    SharedResources.getBoarded().acquire();    //Wait till 50 or less are aboard
                }
            depart();
            SharedResources.getMutex().release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
