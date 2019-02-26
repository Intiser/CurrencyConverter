package project.ahsan.language.com.currenyconverter.manager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.ahsan.language.com.currenyconverter.model.CountryVat;
import project.ahsan.language.com.currenyconverter.model.TaxRate;

public class DataManager {

    private static String TAG = DataManager.class.getName();

    private static DataManager dataManager = new DataManager();

    private int selectedCountry = 0;
    private int selectedTax = 0;

    private ArrayList<CountryVat> countryVatArrayList = new ArrayList<>();

    private DataManager() {

    }

    public static DataManager sharedInstance() {
        return dataManager;
    }

    public ArrayList<CountryVat> getCountryVatArrayList(){
        return countryVatArrayList;
    }

    public int getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(int selectedCountry) {
        this.selectedCountry = selectedCountry;
        this.selectedTax = 0;
    }
    public CountryVat getSelectedCountryModel(){
        return countryVatArrayList.get(selectedCountry);
    }

    public int getSelectedTax() {
        return selectedTax;
    }

    public void setSelectedTax(int selectedTax) {
        this.selectedTax = selectedTax;
    }

    public void extractData(String json) {
        JSONObject jsonObject = null;
        ArrayList<CountryVat> countryVats = new ArrayList<>();

        try {
            jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("rates");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String country_nam = jsonObject1.getString("name");
                String code = jsonObject1.getString("code");
                String country_code = jsonObject1.getString("country_code");
                JSONArray jsonArray1 = jsonObject1.getJSONArray("periods");
                JSONObject jsonObject2 = jsonArray1.getJSONObject(0);
                JSONObject jsonObject3 = jsonObject2.getJSONObject("rates");

//                Log.d(TAG, "extractData: " + country_nam);
//                Log.d(TAG, "extractData: " + code);
//                Log.d(TAG, "extractData: " + country_code);

                ArrayList<TaxRate> taxRates = extractRates(jsonObject3);
                CountryVat countryVat = new CountryVat(country_nam,code,country_code, taxRates);
                countryVats.add(countryVat);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        countryVatArrayList = countryVats;
    }

    ArrayList<TaxRate> extractRates(JSONObject object){
        ArrayList<TaxRate> taxRates = new ArrayList<>();

        double rate = 0;

        String str = "standard";
        rate = getRateFromObject(object, str);
        if(rate != -1){
            TaxRate taxRate = new TaxRate(str,rate);
            taxRates.add(taxRate);
        }
        str = "reduced";
        rate = getRateFromObject(object, str);
        if(rate != -1){
            TaxRate taxRate = new TaxRate(str,rate);
            taxRates.add(taxRate);
        }

        str = "reduced1";
        rate = getRateFromObject(object, str);
        if(rate != -1){
            TaxRate taxRate = new TaxRate(str,rate);
            taxRates.add(taxRate);
        }

        str = "reduced2";
        rate = getRateFromObject(object, str);
        if(rate != -1){
            TaxRate taxRate = new TaxRate(str,rate);
            taxRates.add(taxRate);
        }

        str = "super_reduced";
        rate = getRateFromObject(object, str);
        if(rate != -1){
            TaxRate taxRate = new TaxRate(str,rate);
            taxRates.add(taxRate);
        }

        str = "parking";
        rate = getRateFromObject(object, str);
        if(rate != -1){
            TaxRate taxRate = new TaxRate(str,rate);
            taxRates.add(taxRate);
        }

        return taxRates;
    }

    private double getRateFromObject(JSONObject object, String standard_string){
        try {
            double rate1 = object.getDouble(standard_string);
            return rate1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
