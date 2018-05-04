import java.sql.Date;

public class Record {
    private Date date;
    private String product;
    private double cost;

    public Record(Date date, String product, double cost) {
        this.date = date;
        this.product = product;
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
