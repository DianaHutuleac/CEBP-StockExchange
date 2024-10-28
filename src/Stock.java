public class Stock {
    private String companyName;
    private int totalShares;

    public Stock(String companyName, int totalShares) {
        this.companyName = companyName;
        this.totalShares = totalShares;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(int totalShares) {
        this.totalShares = totalShares;
    }
}
