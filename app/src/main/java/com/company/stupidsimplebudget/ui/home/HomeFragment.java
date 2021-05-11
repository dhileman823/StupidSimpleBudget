package com.company.stupidsimplebudget.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.stupidsimplebudget.R;
import com.company.stupidsimplebudget.data.expense.Expense;
import com.company.stupidsimplebudget.data.income.Income;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ExpenseDialogFragment.ExpenseDialogListener, IncomeDialogFragment.IncomeDialogListener {

    private HomeViewModel homeViewModel;

    private float incomeTotal = 0;
    private float expenseTotal = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TextView textView = root.findViewById(R.id.tvAvailableFunds);
        RecyclerView expenseListView = root.findViewById(R.id.rvExpenses);
        ExpenseListAdapter expenseAdapter = new ExpenseListAdapter(getContext(), new ArrayList<>());
        expenseListView.setAdapter(expenseAdapter);
        expenseListView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView incomeListView = root.findViewById(R.id.rvIncome);
        IncomeListAdapter incomeAdapter = new IncomeListAdapter(getContext(), new ArrayList<>());
        incomeListView.setAdapter(incomeAdapter);
        incomeListView.setLayoutManager(new LinearLayoutManager(getContext()));

        homeViewModel.getExpenses().observe(getViewLifecycleOwner(), new Observer<List<Expense>>() {
            @Override
            public void onChanged(@Nullable List<Expense> expenses) {
                float total = 0;
                for(Expense expense: expenses){
                    total += expense.amount;
                }
                expenseTotal = total;
                float funds = incomeTotal - expenseTotal;
                DecimalFormat df = new DecimalFormat("0.00");
                textView.setText("$" + df.format(funds));
                if(funds < 0){
                    textView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    textView.setTextColor(getResources().getColor(R.color.green));
                }

                expenseAdapter.setExpenses(expenses);
            }
        });

        homeViewModel.getIncomes().observe(getViewLifecycleOwner(), new Observer<List<Income>>() {
            @Override
            public void onChanged(@Nullable List<Income> incomes) {
                float total = 0;
                for(Income income: incomes){
                    total += income.amount;
                }
                incomeTotal = total;
                float funds = incomeTotal - expenseTotal;
                DecimalFormat df = new DecimalFormat("0.00");
                textView.setText("$" + df.format(funds));
                if(funds < 0){
                    textView.setTextColor(getResources().getColor(R.color.red));
                }
                else{
                    textView.setTextColor(getResources().getColor(R.color.green));
                }

                incomeAdapter.setIncomes(incomes);
            }
        });

        Button btnAddExpense = root.findViewById(R.id.btnAddExpense);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new ExpenseDialogFragment();
                dialog.show(getChildFragmentManager(), "ExpenseDialogFragment");
            }
        });

        Button btnAddIncome = root.findViewById(R.id.btnAddIncome);
        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new IncomeDialogFragment();
                dialog.show(getChildFragmentManager(), "IncomeDialogFragment");
            }
        });

        return root;
    }

    @Override
    public void onExpenseDialogSave(ExpenseDialogFragment dialog) {
        Log.d("HomeFragment", "Save click");
        Log.d("HomeFragment", "expense name: " + dialog.getName());
        Log.d("HomeFragment", "expense amt: " + dialog.getAmount());

        Expense expense = new Expense();
        expense.name = dialog.getName();
        expense.amount = dialog.getAmount();
        homeViewModel.insert(expense);

        dialog.dismiss();
    }

    @Override
    public void onExpenseDialogCancel(ExpenseDialogFragment dialog) {
        Log.d("HomeFragment", "Cancel click");
        dialog.dismiss();
    }

    @Override
    public void onIncomeDialogSave(IncomeDialogFragment dialog) {
        Log.d("HomeFragment", "Save click");
        Log.d("HomeFragment", "income name: " + dialog.getName());
        Log.d("HomeFragment", "income amt: " + dialog.getAmount());

        Income income = new Income();
        income.name = dialog.getName();
        income.amount = dialog.getAmount();
        homeViewModel.insert(income);

        dialog.dismiss();
    }

    @Override
    public void onIncomeDialogCancel(IncomeDialogFragment dialog) {
        Log.d("HomeFragment", "Cancel click");
        dialog.dismiss();
    }
}