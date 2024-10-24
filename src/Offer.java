import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Offer {
    private Stock stock;
    private int quantity;
    private double pricePerShare;
    private String sellerId;
    private final Lock lock = new ReentrantLock();  // Lock for this specific offer

    public Offer(Stock share, int quantity, double pricePerShare, String sellerId) {
        this.stock = share;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.sellerId = sellerId;
    }

    public Stock getShare() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isFulfilled() {
        return quantity <= 0;
    }

    public Lock getLock() {
        return lock;  // Provide access to the specific lock for this offer
    }
}