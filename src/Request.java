public class Request {
    private Share share;
    private int quantity;
    private double maxPricePerShare;
    private String buyerId;

    public Request(Share share, int quantity, double maxPricePerShare, String buyerId) {
        this.share = share;
        this.quantity = quantity;
        this.maxPricePerShare = maxPricePerShare;
        this.buyerId = buyerId;
    }

    public Share getShare() {
        return share;
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
}
