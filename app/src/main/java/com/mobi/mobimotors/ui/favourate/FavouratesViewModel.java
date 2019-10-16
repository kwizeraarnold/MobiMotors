package com.mobi.mobimotors.ui.favourate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavouratesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavouratesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Apply advanced filters to the search");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
