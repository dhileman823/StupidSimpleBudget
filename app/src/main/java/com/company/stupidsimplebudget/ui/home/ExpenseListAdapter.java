package com.company.stupidsimplebudget.ui.home;

import android.app.Activity;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
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

    private HomeFragment fragment;
    private LayoutInflater inflater = null;
    private List<Expense> expenses;

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        Log.d("Adapter", "expense records: " + expenses.size());
        notifyDataSetChanged();
    }

    public ExpenseListAdapter(HomeFragment fragment, List<Expense> expenses) {
        this.fragment = fragment;
        this.inflater = LayoutInflater.from(fragment.getContext());
        this.expenses = expenses;
    }

    @NonNull
    @Override
    public ExpenseListAdapter.ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_expense, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Adapter", "Item clicked: " + view.getTag());
                TextView tvExpenseAmount = v.findViewById(R.id.tvExpenseAmount);
                TextView tvExpenseName = v.findViewById(R.id.tvExpenseName);
                ExpenseEditDialogFragment dialog = new ExpenseEditDialogFragment();
                dialog.setListener(fragment);
                dialog.setExpenseId((int)view.getTag());
                String amt = tvExpenseAmount.getText().toString();
                amt = amt.substring(2);
                dialog.setAmount(Float.parseFloat(amt)); //2, cut preceding $<space>
                dialog.setName(tvExpenseName.getText().toString());
                dialog.show(((AppCompatActivity)fragment.getContext()).getSupportFragmentManager(), "ExpenseEditDialogFragment");
            }
        });

        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseListAdapter.ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        DecimalFormat df = new DecimalFormat("0.00");
        holder.tvAmount.setText("$ "+df.format(expense.amount));
        holder.tvName.setText(expense.name);
        holder.view.setTag(expense.expenseId);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }
}
