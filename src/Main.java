public class Main {

    public static void main(String[] args) {
        System.out.println("+++++ Senate Bus Stop +++++");
        System.out.println("***** Bus Capacity: 50 *****");

        Resources resources = new Resources();

        Thread busScheduler = new Thread(new BusScheduler(resources));
        Thread riderScheduler = new Thread(new RiderScheduler(resources));

        busScheduler.start();
        riderScheduler.start();

        try {
            busScheduler.join();
            riderScheduler.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program terminated");
    }
}
