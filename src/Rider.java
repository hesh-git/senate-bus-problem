public class Rider implements Runnable {
    private final SharedResources sharedResources;
    public Rider(SharedResources SharedResources) {
        this.sharedResources = SharedResources;
    }
    private void board() {
        System.out.println("Rider boarded to bus");
    }

    @Override
    public void run() {
        try {
            sharedResources.getMutex().acquire();
                sharedResources.setWaitingRiders(sharedResources.getWaitingRiders() + 1);
                System.out.println(sharedResources.getWaitingRiders() + " Riders on waiting");
            sharedResources.getMutex().release();

            sharedResources.getBusWait().acquire();

            board();

            sharedResources.getBus().ridersLoaded +=1;
            if(sharedResources.getBus().ridersLoaded ==50 || sharedResources.getBus().ridersLoaded == sharedResources.getWaitingRiders()){
                sharedResources.setWaitingRiders(Math.max(sharedResources.getWaitingRiders() - 50, 0));
                sharedResources.getBoarded().release();
            }else {
                sharedResources.getBusWait().release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
