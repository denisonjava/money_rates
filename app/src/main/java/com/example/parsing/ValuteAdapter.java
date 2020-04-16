package com.example.parsing;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ValuteAdapter extends RecyclerView.Adapter<ValuteAdapter.ValuteViewHolder> {

    private List<ParsingValute> valuteList;
    private LayoutInflater mInflater;
    private String date;
    private ArrayList<Integer> imageArray;

    public ArrayList<Integer> getImageArray() {
        return imageArray;
    }

    public void setImageArray(ArrayList<Integer> imageArray) {
        this.imageArray = imageArray;
    }


    public List<ParsingValute> getValuteList() {
        return valuteList;
    }

    public void setValuteList(List<ParsingValute> valuteList) {
        this.valuteList = valuteList;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    ValuteAdapter(Context context, List<ParsingValute> data, String date, ArrayList<Integer> imageArray) {
        this.mInflater = LayoutInflater.from(context);
        this.valuteList = data;
        this.date = date;
        this.imageArray = imageArray;
    }


    @Override
    public ValuteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.valute_card, parent, false);
        return new ValuteViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ValuteViewHolder holder, int position) {


        String charcode = valuteList.get(position).getCharCode();
        holder.codeTextView.setText(charcode);
        String nominal = valuteList.get(position).getNominal();
        holder.nominalTextView.setText(nominal);
        String name = valuteList.get(position).getName();
        holder.nameTextView.setText(name);
        String value = valuteList.get(position).getValue();
        holder.valueTextView.setText(value);
        holder.dateTextView.setText(date);
        holder.constraintLayout.setBackgroundResource(imageArray.get(position));

    }

    @Override
    public int getItemCount() {
        return valuteList.size();
    }


    public class ValuteViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout;
        TextView codeTextView;
        TextView nameTextView;
        TextView nominalTextView;
        TextView valueTextView;
        TextView dateTextView;


        ValuteViewHolder(View itemView) {
            super(itemView);
            codeTextView = itemView.findViewById(R.id.textView_code);
            nominalTextView = itemView.findViewById(R.id.textView_nominal);
            nameTextView = itemView.findViewById(R.id.textView_name);
            valueTextView = itemView.findViewById(R.id.textView_value);
            dateTextView = itemView.findViewById(R.id.textView_date);
            constraintLayout = itemView.findViewById(R.id.background);
        }

    }

}
