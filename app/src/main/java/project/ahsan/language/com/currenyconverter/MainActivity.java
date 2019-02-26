package project.ahsan.language.com.currenyconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import project.ahsan.language.com.currenyconverter.adapter.CustomAdapter;
import project.ahsan.language.com.currenyconverter.adapter.ListAdapter;
import project.ahsan.language.com.currenyconverter.manager.DataManager;
import project.ahsan.language.com.currenyconverter.model.CountryVat;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    RecyclerView recyclerView;
    ListAdapter adapter;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
        initList();
        initSpinner();
    }

    private void initTextViews() {
        editText = findViewById(R.id.edit_text_money);
        textView = findViewById(R.id.textview_tax);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setValueToTextView("" + charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setValueToTextView(String charSequence) {
        if(charSequence == ""){
            return;
        }
        Log.d("TAG", "setValueToTextView: " + charSequence);
        double val = Double.parseDouble("" + charSequence);
        int r = DataManager.sharedInstance().getSelectedTax();
        CountryVat countryVat = DataManager.sharedInstance().getSelectedCountryModel();
        double tax = (countryVat.getTaxRates().get(r).getRate() / 100.0);
        tax = tax * val;
        textView.setText("Tax: "+tax);
    }

    private void initList() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter = new ListAdapter(this);
        adapter.setListener(new ListAdapter.Listener() {
            @Override
            public void checkButtonClicked(int pos) {
                Log.d("TAG", "checkButtonClicked: ");
                DataManager.sharedInstance().setSelectedTax(pos);
                adapter.setSelected(pos);
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.refresh();
                    }
                });
                updateTextView();


            }
        });
        adapter.setTaxRates(DataManager.sharedInstance().getCountryVatArrayList().get(0).getTaxRates());
        recyclerView.setAdapter(adapter);
    }

    private void initSpinner() {
        spinner = findViewById(R.id.spinner);
        CustomAdapter cadapter = new CustomAdapter(this,R.layout.item_spinner, DataManager.sharedInstance().getCountryVatArrayList());
        spinner.setAdapter(cadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DataManager.sharedInstance().setSelectedCountry(i);
                adapter.setTaxRates(DataManager.sharedInstance().getCountryVatArrayList().get(i).getTaxRates());
                adapter.setSelected(DataManager.sharedInstance().getSelectedTax());
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.refresh();
                    }
                });
                updateTextView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void updateTextView() {
        runOnUiThread(new Runnable(){
            public void run() {
                String text = editText.getText().toString();
                setValueToTextView(text);

            }
        });
    }


}
