package com.company.stupidsimplebudget.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.company.stupidsimplebudget.data.expense.Expense;
import com.company.stupidsimplebudget.data.expense.ExpenseDao;
import com.company.stupidsimplebudget.data.income.Income;
import com.company.stupidsimplebudget.data.income.IncomeDao;

@Database(entities = {Expense.class, Income.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IncomeDao incomeDao();
    public abstract ExpenseDao expenseDao();

    private static volatile AppDatabase instance = null;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (AppDatabase.class) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context, AppDatabase.class, "StupidSimpleBudgetDB").build();
                }
            }
        }
        return instance;
    }
}
