package v5;

public interface ItemStore<T> {

    void newRequestReceived(T value);
    T getRequest() throws InterruptedException;
}
