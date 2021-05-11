package com.company.stupidsimplebudget.data.income;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IncomeDao {
    @Query("SELECT * FROM Income")
    LiveData<List<Income>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Income income);

    @Delete
    void delete (Income income);
}