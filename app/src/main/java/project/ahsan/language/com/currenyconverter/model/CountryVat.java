package project.ahsan.language.com.currenyconverter.model;

import java.util.ArrayList;

public class CountryVat {
    String country;
    String code;
    String country_code;
    ArrayList<TaxRate>taxRates = new ArrayList<>();

    public CountryVat(String country, String code, String country_code, ArrayList<TaxRate> taxRates) {
        this.country = country;
        this.code = code;
        this.country_code = country_code;
        this.taxRates = taxRates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public ArrayList<TaxRate> getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(ArrayList<TaxRate> taxRates) {
        this.taxRates = taxRates;
    }
}
