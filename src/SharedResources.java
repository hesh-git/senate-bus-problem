import java.util.concurrent.Semaphore;

public class SharedResources {
    private int waitingRiders;
    private Semaphore busWait;  //signal when bus arrive and wait on bus
    private Semaphore boarded;  //signal till all riders are boarded
    private Semaphore mutex;   //mutex for shared variable waitingRiders
    private Bus bus;

    public SharedResources() {
        this.waitingRiders = 0;
        this.mutex = new Semaphore(1);
        this.busWait = new Semaphore(0);
        this.boarded = new Semaphore(0);
    }

    public int getWaitingRiders() {
        return waitingRiders;
    }

    public void setWaitingRiders(int waitingRiders) {
        this.waitingRiders = waitingRiders;
    }

    public Semaphore getBusWait() {
        return busWait;
    }

    public void setBusWait(Semaphore busWait) {
        this.busWait = busWait;
    }

    public Semaphore getBoarded() {
        return boarded;
    }

    public void setBoarded(Semaphore boarded) {
        this.boarded = boarded;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
