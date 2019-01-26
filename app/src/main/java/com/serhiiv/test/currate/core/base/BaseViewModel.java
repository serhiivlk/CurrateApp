package com.serhiiv.test.currate.core.base;

import com.serhiiv.test.currate.core.tools.SingleLiveEvent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
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

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
