package com.company.stupidsimplebudget.data.expense;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.company.stupidsimplebudget.data.AppDatabase;

import java.util.List;

public class ExpenseRepository {

    ExpenseDao expenseDao;
    LiveData<List<Expense>> expenses;

    public LiveData<List<Expense>> getExpenses() {
        return expenses;
    }

    public ExpenseRepository(Application app){
        AppDatabase db = AppDatabase.getInstance(app);
        expenseDao = db.expenseDao();
        expenses = expenseDao.getAll();
    }

    public void insert(Expense expense){
        new insertAsyncTask(expenseDao).execute(expense);
    }

    private static class insertAsyncTask extends AsyncTask<Expense, Void, Void> {

        private ExpenseDao asyncDao;

        insertAsyncTask(ExpenseDao dao) {
            asyncDao = dao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            asyncDao.insert(expenses[0]);
            return null;
        }
    }
}
