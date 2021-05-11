package com.company.stupidsimplebudget.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.company.stupidsimplebudget.data.expense.Expense;
import com.company.stupidsimplebudget.data.expense.ExpenseRepository;
import com.company.stupidsimplebudget.data.income.Income;
import com.company.stupidsimplebudget.data.income.IncomeRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private ExpenseRepository expenseRepository;
    private IncomeRepository incomeRepository;
    private LiveData<List<Expense>> expenses;
    private LiveData<List<Income>> incomes;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.expenseRepository = new ExpenseRepository(application);
        this.incomeRepository = new IncomeRepository(application);
        this.expenses = this.expenseRepository.getExpenses();
        this.incomes = this.incomeRepository.getIncomes();
    }

    public LiveData<List<Expense>> getExpenses() {
        return expenses;
    }

    public void insert(Expense expense){
        this.expenseRepository.insert(expense);
    }

    public LiveData<List<Income>> getIncomes() { return incomes; }

    public void insert(Income income) { this.incomeRepository.insert(income); }
}