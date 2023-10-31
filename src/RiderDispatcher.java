import java.util.Random;

public class RiderScheduler implements Runnable {
    private final SharedResources SharedResources;
    private float meanTime = 3f * 1000;
    private static final Random random = new Random();
    public RiderScheduler(SharedResources SharedResources) {
        this.SharedResources = SharedResources;
    }

    @Override
    public void run() {
        while (true) {
            new Thread(new Rider(SharedResources)).start();
            try {
                float lambda = 1 / meanTime;
                Thread.sleep(Math.round(-Math.log(1 - random.nextFloat()) / lambda));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
