public class Bus implements Runnable {
    private final Resources resources;
    public int passengers = 0;
    public Bus(Resources resources) {
        this.resources = resources;
    }
    private void depart() {
        System.out.println("Bus loaded with " + passengers + " riders and " + resources.getWaitingRiders() + " riders are left");
        System.out.println("***** BUS DEPARTED !!! *** \n");
    }

    @Override
    public void run() {
        try {
            resources.getMutex().acquire();              //avoid new riders when bus is at stop
                System.out.println("***** BUS ARRIVED !!! *** \n");
                System.out.println("Number of Riders waiting for Bus : "+ resources.getWaitingRiders());
                System.out.println("Number of Riders who can board to Bus : "+ Math.min(resources.getWaitingRiders(),50) );
                if (resources.getWaitingRiders() > 0) {
                    resources.setBus(this);
                    resources.getBusWait().release();    //Signal riders that bus arrived
                    resources.getBoarded().acquire();    //Wait till 50 or less are aboard
                }
            depart();
            resources.getMutex().release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
