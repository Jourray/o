package com.example.myapplication.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseModel {
protected   CompositeDisposable compositeDisposable = new CompositeDisposable();
    public void destroy() {
        compositeDisposable.clear();
    }
}
