public class Bus implements Runnable {
    private final SharedResources sharedResources;
    public int ridersLoaded = 0;

    public Bus(SharedResources SharedResources) {
        this.sharedResources = SharedResources;
    }

    private void depart() {
        System.out.println(ridersLoaded + " Riders are boarded to the Bus and " + sharedResources.getWaitingRiders() + " Riders are left waiting for next bus");
        System.out.println("\n ***** NOTICE - BUS DEPARTED !!! *** \n");
    }

    @Override
    public void run() {
        try {
            sharedResources.getMutex().acquire();              //avoid new riders when bus is at stop
                System.out.println("\n ***** NOTICE - BUS ARRIVED !!! *** \n");

                int waitingRiders = sharedResources.getWaitingRiders();
                System.out.println( waitingRiders + " Riders are waiting for Bus");

                int canBoard = Math.min(sharedResources.getWaitingRiders(),50);
                System.out.println(canBoard + " Riders can board to Bus" );

                if (sharedResources.getWaitingRiders() > 0) {
                    sharedResources.setBus(this);
                    sharedResources.getBusWait().release();    //Riders count when bus arrived to the halt
                    sharedResources.getBoarded().acquire();    //Wait till 50 or less are aboard
                }
            depart();
            sharedResources.getMutex().release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
