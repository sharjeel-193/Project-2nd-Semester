package com.selflearning.starcover.ui.sing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SingViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public SingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Sing fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}