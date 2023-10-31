import java.util.concurrent.Semaphore;

public class Resources {
    public int waitingRiders;
    public Semaphore busWait;  //signal when bus arrive, wait on bus
    public Semaphore boarded;  //bus wait on till all riders are boarded
    public Semaphore mutex;   //mutex for shared variable waitingRiders
    public Bus bus;

    public Resources() {
        this.waitingRiders = 0;
        this.mutex = new Semaphore(1);
        this.busWait = new Semaphore(0);
        this.boarded = new Semaphore(0);
    }
}
