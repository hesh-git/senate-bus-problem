public class Rider implements Runnable {
    private final SharedResources SharedResources;
    public Rider(SharedResources SharedResources) {
        this.SharedResources = SharedResources;
    }
    private void board() {
        System.out.println("Rider boarded to bus");
    }

    @Override
    public void run() {
        try {
            SharedResources.getMutex().acquire();
                int waitingRiders = SharedResources.getWaitingRiders()
                SharedResources.setWaitingRiders(waitingRiders + 1);
                System.out.println(SharedResources.getWaitingRiders() + " Riders on waiting");
            SharedResources.getMutex().release();

            SharedResources.getBusWait().acquire();

            board();

            SharedResources.getBus().passengers +=1;
            if(SharedResources.getBus().passengers ==50 || SharedResources.getBus().passengers == SharedResources.getWaitingRiders()){
                SharedResources.setWaitingRiders(Math.max(SharedResources.getWaitingRiders() - 50, 0));
                SharedResources.getBoarded().release();
            }else {
                SharedResources.getBusWait().release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
