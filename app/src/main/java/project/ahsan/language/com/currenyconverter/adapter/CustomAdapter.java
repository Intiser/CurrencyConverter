package project.ahsan.language.com.currenyconverter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import project.ahsan.language.com.currenyconverter.R;
import project.ahsan.language.com.currenyconverter.model.CountryVat;
import project.ahsan.language.com.currenyconverter.model.TaxRate;

public class CustomAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<CountryVat> items;
    private int mResource;


    public CustomAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.textview_country);

        CountryVat countryVat = items.get(position);

        textView.setText(countryVat.getCountry());

        return view;
    }


}
