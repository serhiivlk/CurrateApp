package com.serhiiv.test.currate.core.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.serhiiv.test.currate.core.tools.SingleLiveEvent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {
    private final MutableLiveData<String> toastMessage = new SingleLiveEvent<>();

    private final CompositeDisposable disposable = new CompositeDisposable();

    protected void showToastMessage(String message) {
        toastMessage.setValue(message);
    }

    protected void track(Disposable disposable) {
        this.disposable.add(disposable);
    }

    public LiveData<String> getMessage() {
        return toastMessage;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
