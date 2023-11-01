public class Main {

    public static void main(String[] args) {
        System.out.println("+++++ Senate Bus Stop +++++");
        System.out.println("***** Bus Capacity: 50 *****");

        SharedResources sharedResources = new SharedResources();

        Thread busSchedulerThread = new Thread(new BusDispatcher(sharedResources));
        Thread riderSchedulerThread = new Thread(new RiderDispatcher(sharedResources));

        busSchedulerThread.start();
        riderSchedulerThread.start();

        try {
            busSchedulerThread.join();
            riderSchedulerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program is terminated!!!");
    }
}
