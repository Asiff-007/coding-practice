package io.thoughtbox.asif.util;

public class MutableEventLiveData<T> extends io.thoughtbox.asif.util.EventLiveData<T> {

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }
}