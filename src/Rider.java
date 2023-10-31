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
            resources.mutex.acquire();
                resources.waitingRiders += 1;
                System.out.println(resources.waitingRiders + " Riders on waiting");
            resources.mutex.release();

            resources.busWait.acquire();

            board();

            resources.bus.passengers +=1;
            if(resources.bus.passengers ==50 || resources.bus.passengers ==resources.waitingRiders){
                resources.waitingRiders=Math.max(resources.waitingRiders - 50, 0);
                resources.boarded.release();
            }else {
                resources.busWait.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
