package v5;


import util.LogPrinter;

import java.util.LinkedList;

@SuppressWarnings("Duplicates")
public class ItemStoreImpl<T> implements ItemStore<T> {

    private LinkedList<T> items = new LinkedList<>();

    @Override
    public void newRequestReceived(T value) {
        LogPrinter.logMsg("New Request : " + value);
        synchronized (items) {
            items.offer(value);
            items.notifyAll();
        }
    }

    @Override
    public T getRequest() throws InterruptedException {
        synchronized (items) {
            do {
                if (items.size() > 0) {
                    return items.remove();
                }
                items.wait();
                LogPrinter.logMsg("Notified.....");
            } while (items.isEmpty());
            return items.remove();
        }
    }
}
