package project.ahsan.language.com.currenyconverter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.ahsan.language.com.currenyconverter.R;
import project.ahsan.language.com.currenyconverter.manager.DataManager;
import project.ahsan.language.com.currenyconverter.model.TaxRate;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder>{


    LayoutInflater inflater;
    Context context;
    ArrayList<TaxRate> taxRates = new ArrayList<>();
    int selected = 0;
    Listener listener;

    public ListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }


    public void setTaxRates(ArrayList<TaxRate> taxRates) {
        this.taxRates = taxRates;
    }


    public void setSelected(int selected){
        this.selected = selected;

    }

    public void refresh(){
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.textView.setText(taxRates.get(position).getTaxtype());
        if(position == selected){
            holder.checkBox.setChecked(true);
        }
        else{
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true && listener != null){
                    listener.checkButtonClicked(position);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return taxRates.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView textView;
        CheckBox checkBox;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_tax);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }

    public interface Listener{
        void checkButtonClicked(int pos);
    }
}
