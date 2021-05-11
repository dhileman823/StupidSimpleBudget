package com.company.stupidsimplebudget.data.expense;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    LiveData<List<Expense>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Expense expense);

    @Delete
    void delete (Expense expense);
}
