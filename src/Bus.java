public class Bus implements Runnable {
    private final Resources resources;
    public int passengers = 0;
    public Bus(Resources resources) {
        this.resources = resources;
    }
    private void depart() {
        System.out.println("Bus loaded with " + passengers + " riders and " + resources.waitingRiders + " riders are left");
        System.out.println("***** BUS DEPARTED !!! *** \n");
    }

    @Override
    public void run() {
        try {
            resources.mutex.acquire();              //avoid new riders when bus is at stop
                System.out.println("***** BUS ARRIVED !!! *** \n");
                System.out.println("Number of Riders waiting for Bus : "+ resources.waitingRiders );
                System.out.println("Number of Riders who can board to Bus : "+ Math.min(resources.waitingRiders,50) );
                if (resources.waitingRiders > 0) {
                    resources.bus=this;
                    resources.busWait.release();    //Signal riders that bus arrived
                    resources.boarded.acquire();    //Wait till 50 or less are aboard
                }
            depart();
            resources.mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
