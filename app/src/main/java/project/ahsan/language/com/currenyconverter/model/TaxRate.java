package project.ahsan.language.com.currenyconverter.model;

public class TaxRate {
    String taxtype;
    double rate;

    public TaxRate(String taxtype, double rate) {
        this.taxtype = taxtype;
        this.rate = rate;
    }

    public String getTaxtype() {
        return taxtype;
    }

    public void setTaxtype(String taxtype) {
        this.taxtype = taxtype;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
