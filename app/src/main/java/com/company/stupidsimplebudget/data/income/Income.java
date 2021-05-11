package com.company.stupidsimplebudget.data.income;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Income {
    @PrimaryKey(autoGenerate = true)
    public int incomeId;

    public String name;

    public float amount;
}
