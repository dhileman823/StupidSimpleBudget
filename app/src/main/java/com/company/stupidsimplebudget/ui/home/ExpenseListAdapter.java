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
import com.company.stupidsimplebudget.data.expense.Expense;

import java.text.DecimalFormat;
import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder> {

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout view;
        public TextView tvAmount;
        public TextView tvName;
        public ExpenseViewHolder(View v){
            super(v);
            view = (ConstraintLayout)v;
            tvAmount = v.findViewById(R.id.tvExpenseAmount);
            tvName = v.findViewById(R.id.tvExpenseName);
        }
    }

    private LayoutInflater inflater = null;
    private List<Expense> expenses;

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        Log.d("Adapter", "expense records: " + expenses.size());
        notifyDataSetChanged();
    }

    public ExpenseListAdapter(Context context, List<Expense> expenses) {
        this.inflater = LayoutInflater.from(context);
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ExpenseListAdapter.ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseListAdapter.ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        DecimalFormat df = new DecimalFormat("0.00");
        holder.tvAmount.setText("$ "+df.format(expense.amount));
        holder.tvName.setText(expense.name);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }
}
