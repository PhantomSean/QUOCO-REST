package service.core;

public class Quotation implements java.io.Serializable {
    private String company;
    private String reference;
    private double price;

    public Quotation() {
        // Default Constructor
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public String getCompany() {
        return company;
    }
    public void setReference(String refernce) {
        this.reference = refernce;
    }
    public String getReference() {
        return reference;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}
