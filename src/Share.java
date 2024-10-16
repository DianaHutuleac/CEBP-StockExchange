public class Share {
    private String companyName;
    private int totalShares;

    public Share(String companyName, int totalShares) {
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
