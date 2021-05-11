package com.company.stupidsimplebudget.data.expense;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Expense {
    @PrimaryKey(autoGenerate = true)
    public int expenseId;

    public String name;

    public float amount;
}
