public class Rider implements Runnable {
    private final Resources resources;
    public Rider(Resources resources) {
        this.resources = resources;
    }
    private void board() {
        System.out.println("Rider boarded to bus");
    }

    @Override
    public void run() {
        try {
            resources.getMutex().acquire();
                resources.setWaitingRiders(resources.getWaitingRiders() + 1);
                System.out.println(resources.getWaitingRiders() + " Riders on waiting");
            resources.getMutex().release();

            resources.getBusWait().acquire();

            board();

            resources.getBus().passengers +=1;
            if(resources.getBus().passengers ==50 || resources.getBus().passengers == resources.getWaitingRiders()){
                resources.setWaitingRiders(Math.max(resources.getWaitingRiders() - 50, 0));
                resources.getBoarded().release();
            }else {
                resources.getBusWait().release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
