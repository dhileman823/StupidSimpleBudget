package com.company.stupidsimplebudget.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AboutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a simple to use budgeting app. Enter your income for the month, your expenses for the month, and let the app calculate how much you have left over.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
