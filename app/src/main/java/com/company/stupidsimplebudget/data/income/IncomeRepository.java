package com.company.stupidsimplebudget.data.income;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.company.stupidsimplebudget.data.AppDatabase;

import java.util.List;

public class IncomeRepository {

    IncomeDao incomeDao;
    LiveData<List<Income>> incomes;

    public LiveData<List<Income>> getIncomes() {
        return incomes;
    }

    public IncomeRepository(Application app){
        AppDatabase db = AppDatabase.getInstance(app);
        incomeDao = db.incomeDao();
        incomes = incomeDao.getAll();
    }

    public void insert(Income income){
        new com.company.stupidsimplebudget.data.income.IncomeRepository.insertAsyncTask(incomeDao).execute(income);
    }

    private static class insertAsyncTask extends AsyncTask<Income, Void, Void> {

        private IncomeDao asyncDao;

        insertAsyncTask(IncomeDao dao) {
            asyncDao = dao;
        }

        @Override
        protected Void doInBackground(Income... incomes) {
            asyncDao.insert(incomes[0]);
            return null;
        }
    }

    public void delete(Income income){
        new deleteAsyncTask(incomeDao).execute(income);
    }

    private static class deleteAsyncTask extends AsyncTask<Income, Void, Void> {

        private IncomeDao asyncDao;

        deleteAsyncTask(IncomeDao dao) {
            asyncDao = dao;
        }

        @Override
        protected Void doInBackground(Income... incomes) {
            asyncDao.delete(incomes[0]);
            return null;
        }
    }
}
