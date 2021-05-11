package com.company.stupidsimplebudget.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.company.stupidsimplebudget.R;
import com.company.stupidsimplebudget.data.income.Income;

import java.text.DecimalFormat;
import java.util.List;

public class IncomeListAdapter extends RecyclerView.Adapter<IncomeListAdapter.IncomeViewHolder> {

    public static class IncomeViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout view;
        public TextView tvAmount;
        public TextView tvName;
        public IncomeViewHolder(View v){
            super(v);
            view = (ConstraintLayout)v;
            tvAmount = v.findViewById(R.id.tvIncomeAmount);
            tvName = v.findViewById(R.id.tvIncomeName);
        }
    }

    private LayoutInflater inflater = null;
    private List<Income> incomes;

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
        Log.d("Adapter", "incomes records: " + incomes.size());
        notifyDataSetChanged();
    }

    public IncomeListAdapter(Context context, List<Income> incomes) {
        this.inflater = LayoutInflater.from(context);
        this.incomes = incomes;
    }

    @NonNull
    @Override
    public IncomeListAdapter.IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_income, parent, false);
        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeListAdapter.IncomeViewHolder holder, int position) {
        Income income = incomes.get(position);
        DecimalFormat df = new DecimalFormat("0.00");
        holder.tvAmount.setText("$ "+df.format(income.amount));
        holder.tvName.setText(income.name);
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }
}
