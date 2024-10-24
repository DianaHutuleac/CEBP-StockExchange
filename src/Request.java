import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Request Class
class Request {
    private Stock stock;
    private int quantity;
    private double maxPricePerShare;
    private String buyerId;
    private final Lock lock = new ReentrantLock();  // Lock for this specific request

    public Request(Stock stock, int quantity, double maxPricePerShare, String buyerId) {
        this.stock = stock;
        this.quantity = quantity;
        this.maxPricePerShare = maxPricePerShare;
        this.buyerId = buyerId;
    }

    public Stock getShare() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getMaxPricePerShare() {
        return maxPricePerShare;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isFulfilled() {
        return quantity <= 0;
    }

    public Lock getLock() {
        return lock;  // Provide access to the specific lock for this request
    }
}