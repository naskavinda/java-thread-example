package v7;

public interface ItemStore<T> {

    void newRequestReceived(T value);
    T getRequest() throws InterruptedException;
}
