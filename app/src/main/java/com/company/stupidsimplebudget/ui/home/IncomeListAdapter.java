package com.company.stupidsimplebudget.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    private HomeFragment fragment;
    private LayoutInflater inflater = null;
    private List<Income> incomes;

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
        Log.d("Adapter", "incomes records: " + incomes.size());
        notifyDataSetChanged();
    }

    public IncomeListAdapter(HomeFragment fragment, List<Income> incomes) {
        this.fragment = fragment;
        this.inflater = LayoutInflater.from(fragment.getContext());
        this.incomes = incomes;
    }

    @NonNull
    @Override
    public IncomeListAdapter.IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_income, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Adapter", "Item clicked: " + view.getTag());
                TextView tvIncomeAmount = v.findViewById(R.id.tvIncomeAmount);
                TextView tvIncomeName = v.findViewById(R.id.tvIncomeName);
                IncomeEditDialogFragment dialog = new IncomeEditDialogFragment();
                dialog.setListener(fragment);
                dialog.setIncomeId((int)view.getTag());
                String amt = tvIncomeAmount.getText().toString();
                amt = amt.substring(2);
                dialog.setAmount(Float.parseFloat(amt)); //2, cut preceding $<space>
                dialog.setName(tvIncomeName.getText().toString());
                dialog.show(((AppCompatActivity)fragment.getContext()).getSupportFragmentManager(), "IncomeEditDialogFragment");
            }
        });

        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeListAdapter.IncomeViewHolder holder, int position) {
        Income income = incomes.get(position);
        DecimalFormat df = new DecimalFormat("0.00");
        holder.tvAmount.setText("$ "+df.format(income.amount));
        holder.tvName.setText(income.name);
        holder.view.setTag(income.incomeId);
    }

    @Override
    public int getItemCount() {
        return incomes.size();
    }
}
