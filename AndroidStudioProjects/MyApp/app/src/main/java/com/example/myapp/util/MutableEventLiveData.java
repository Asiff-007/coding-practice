package com.example.myapp.util;

public class MutableEventLiveData<T> extends com.example.myapp.util.EventLiveData<T> {

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }
}