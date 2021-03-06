package com.example.myapp.util;

import android.os.Build;
import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static androidx.lifecycle.Lifecycle.State.DESTROYED;
import static androidx.lifecycle.Lifecycle.State.STARTED;

public class EventLiveData<T> extends LiveData<T> {

    private final HashMap<Observer<? super T>, EventObserverWrapper> observers = new HashMap<>();
    private final Observer<T> internalObserver;
    int mActiveCount = 0;

    public EventLiveData() {
        this.internalObserver = (new Observer<T>() {
            @Override
            public void onChanged(T t) {
                Iterator<Map.Entry<Observer<? super T>, EventObserverWrapper>> iterator = com.example.myapp.util.EventLiveData.this.observers.entrySet().iterator();
                while (iterator.hasNext()) {
                    EventObserverWrapper wrapper = iterator.next().getValue();
                    if (wrapper.shouldBeActive())
                        wrapper.getObserver().onChanged(t);
                }
            }
        });
    }

    private void internalObserve() {
        super.observeForever(this.internalObserver);

    }

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
        observe(owner, observer, STARTED, null);
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer, @NonNull Lifecycle.State minimumStateForSendingEvent) {
        observe(owner, observer, minimumStateForSendingEvent, null);
    }

    @MainThread
    public void observeInOnStart(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
        observe(owner, observer, STARTED, Lifecycle.Event.ON_STOP);
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer, @NonNull Lifecycle.State minimumStateForSendingEvent, Lifecycle.Event removeObserverEvent) {
        assertMainThread("observe");
        assertNotNull(owner, "owner");
        assertNotNull(observer, "observer");
        assertNotNull(owner, "minimumStateForSendingEvent");
        assertDestroyedState(minimumStateForSendingEvent);
        assertMaximumEvent(removeObserverEvent);
        if (minimumStateForSendingEvent == DESTROYED) {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement caller = stackTraceElements[3];
            String className = caller.getClassName();
            String methodName = caller.getMethodName();
            IllegalArgumentException exception =
                    new IllegalArgumentException("State can not be equal to DESTROYED! : " +
                            "method " + className + "." + methodName +
                            ", parameter " + minimumStateForSendingEvent);
            throw sanitizeStackTrace(exception);
        }

        if (owner.getLifecycle().getCurrentState() == DESTROYED) {
            return;
        }

        EventLifecycleBoundEventObserver wrapper = new EventLifecycleBoundEventObserver(owner, observer);
        wrapper.setMinimumStateForSendingEvent(minimumStateForSendingEvent);
        wrapper.setMaximumEventForRemovingEvent(removeObserverEvent);
        EventObserverWrapper existing = wrapper;
        if (!observers.containsKey(observer)) existing = observers.put(observer, wrapper);
        if (existing != null && !existing.isAttachedTo(owner)) {
            throw new IllegalArgumentException("Cannot add the same observer"
                    + " with different lifecycles");
        }
        if (existing != null) {
            return;
        }
        owner.getLifecycle().addObserver(wrapper);

        if (!super.hasObservers()) {
            internalObserve();
        }

    }

    @MainThread
    @Override
    public void observeForever(@NonNull Observer observer) {
        assertMainThread("observeForever");
        assertNotNull(observer, "observer");
        EventAlwaysActiveEventObserver wrapper = new EventAlwaysActiveEventObserver(observer);
        EventObserverWrapper existing = wrapper;
        if (!observers.containsKey(observer)) existing = observers.put(observer, wrapper);
        if (existing != null && existing instanceof com.example.myapp.util.EventLiveData.EventLifecycleBoundEventObserver) {
            throw new IllegalArgumentException("Cannot add the same observer"
                    + " with different lifecycles");
        }
        if (existing != null) {
            return;
        }
        if (!super.hasObservers()) {
            internalObserve();
        }
        wrapper.activeStateChanged(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObservers(@NonNull LifecycleOwner owner) {
        assertMainThread("removeObservers");
        assertNotNull(owner, "owner");
        Iterator<Map.Entry<Observer<? super T>, EventObserverWrapper>> iterator = com.example.myapp.util.EventLiveData.this.observers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Observer<? super T>, EventObserverWrapper> entry = iterator.next();
            if (entry.getValue() instanceof com.example.myapp.util.EventLiveData.EventLifecycleBoundEventObserver) {
                EventLifecycleBoundEventObserver eventLifecycleBoundObserver = (EventLifecycleBoundEventObserver) entry.getValue();
                if (eventLifecycleBoundObserver.isAttachedTo(owner))
                    this.observers.remove(entry.getKey());
            }
        }
    }

    @Override
    public void removeObserver(@NonNull Observer observer) {
        assertMainThread("removeObserver");
        assertNotNull(observer, "observer");
        this.observers.remove(observer);

    }

    final protected void onActive() {
    }

    protected void onActiveEvent() {
    }

    protected void onInactive() {

    }

    @SuppressWarnings("WeakerAccess")
    public boolean hasObservers() {
        return observers.size() > 0;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean hasActiveObservers() {
        return mActiveCount > 0;
    }

    private void assertDestroyedState(@NonNull Lifecycle.State minimumStateForSendingEvent) {
        if (minimumStateForSendingEvent == DESTROYED) {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement caller = stackTraceElements[3];
            String className = caller.getClassName();
            String methodName = caller.getMethodName();
            IllegalArgumentException exception = new IllegalArgumentException("State can not be equal to " + minimumStateForSendingEvent + "method " + className + "." + methodName + ", parameter   minimumStateForSendingEvent");
            throw sanitizeStackTrace(exception);
        }
    }

    private void assertMaximumEvent(@NonNull Lifecycle.Event maximumEventForRemovingEvent) {
        if (maximumEventForRemovingEvent == Lifecycle.Event.ON_START || maximumEventForRemovingEvent == Lifecycle.Event.ON_CREATE
                || maximumEventForRemovingEvent == Lifecycle.Event.ON_RESUME) {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StackTraceElement caller = stackTraceElements[3];
            String className = caller.getClassName();
            String methodName = caller.getMethodName();
            IllegalArgumentException exception = new IllegalArgumentException("State can not be equal to " + maximumEventForRemovingEvent + "method " + className + "." + methodName + ", parameter  maximumEventForRemovingEvent");
            throw sanitizeStackTrace(exception);
        }
    }

    private void assertMainThread(String methodName) {
        boolean isUiThread = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? Looper.getMainLooper().isCurrentThread() : Thread.currentThread() == Looper.getMainLooper().getThread();
        if (!isUiThread) {
            throw new IllegalStateException("Cannot invoke " + methodName + " on a background" + " thread");
        }
    }

    private void assertNotNull(Object value, String paramName) {
        if (value == null) {
            throwParameterIsNullException(paramName);
        }
    }

    private void throwParameterIsNullException(String paramName) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTraceElements[3];
        String className = caller.getClassName();
        String methodName = caller.getMethodName();
        IllegalArgumentException exception =
                new IllegalArgumentException("Parameter specified as non-null is null: " +
                        "method " + className + "." + methodName +
                        ", parameter " + paramName);
        throw sanitizeStackTrace(exception);
    }

    private <T extends Throwable> T sanitizeStackTrace(T throwable) {
        return sanitizeStackTrace(throwable, this.getClass().getName());
    }

    <T extends Throwable> T sanitizeStackTrace(T throwable, String classNameToDrop) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int size = stackTrace.length;
        int lastIntrinsic = -1;
        for (int i = 0; i < size; i++) {
            if (classNameToDrop.equals(stackTrace[i].getClassName())) {
                lastIntrinsic = i;
            }
        }
        StackTraceElement[] newStackTrace = Arrays.copyOfRange(stackTrace, lastIntrinsic + 1, size);
        throwable.setStackTrace(newStackTrace);
        return throwable;
    }

    class EventLifecycleBoundEventObserver extends EventObserverWrapper implements LifecycleObserver {
        @NonNull
        private final LifecycleOwner mOwner;
        private Lifecycle.State MINIMUM_STATE_FOR_SENDING_EVENT = STARTED;
        private Lifecycle.Event MAXIMUM_EVENT_FOR_REMOVING_EVENT = null;

        EventLifecycleBoundEventObserver(@NonNull LifecycleOwner owner, Observer<? super T> observer) {
            super(observer);
            mOwner = owner;
        }

        public Lifecycle.State getMinimumStateForSendingEvent() {
            return MINIMUM_STATE_FOR_SENDING_EVENT;
        }

        public void setMinimumStateForSendingEvent(Lifecycle.State MINIMUM_STATE_FOR_SENDING_EVENT) {
            this.MINIMUM_STATE_FOR_SENDING_EVENT = MINIMUM_STATE_FOR_SENDING_EVENT;
        }

        public Lifecycle.Event getMaximumStateForRemovingEvent() {
            return MAXIMUM_EVENT_FOR_REMOVING_EVENT;
        }

        public void setMaximumEventForRemovingEvent(Lifecycle.Event MAXIMUM_EVENT_FOR_REMOVING_EVENT) {
            this.MAXIMUM_EVENT_FOR_REMOVING_EVENT = MAXIMUM_EVENT_FOR_REMOVING_EVENT;
        }

        @Override
        boolean shouldBeActive() {
            Lifecycle.State state = mOwner.getLifecycle().getCurrentState();
            return state.isAtLeast(MINIMUM_STATE_FOR_SENDING_EVENT);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
            if (mOwner.getLifecycle().getCurrentState() == DESTROYED || (MAXIMUM_EVENT_FOR_REMOVING_EVENT != null && MAXIMUM_EVENT_FOR_REMOVING_EVENT == event)) {
                removeObserver(mObserver);
                return;
            }
            activeStateChanged(shouldBeActive());
        }

        @Override
        boolean isAttachedTo(LifecycleOwner owner) {
            return mOwner == owner;
        }

        @Override
        void detachObserver() {
            mOwner.getLifecycle().removeObserver(this);
        }
    }

    private abstract class EventObserverWrapper {
        protected final Observer<? super T> mObserver;
        boolean mActive;

        EventObserverWrapper(Observer<? super T> observer) {
            mObserver = observer;
        }

        abstract boolean shouldBeActive();

        boolean isAttachedTo(LifecycleOwner owner) {
            return false;
        }

        void detachObserver() {
        }

        public Observer<? super T> getObserver() {
            return mObserver;
        }

        void activeStateChanged(boolean newActive) {
            if (newActive == mActive) {
                return;
            }
            // immediately set active state, so we'd never dispatch anything to inactive
            // owner
            mActive = newActive;
            boolean wasInactive = com.example.myapp.util.EventLiveData.this.mActiveCount == 0;
            com.example.myapp.util.EventLiveData.this.mActiveCount += mActive ? 1 : -1;
            if (wasInactive && mActive) {
                onActiveEvent();
            }
            if (com.example.myapp.util.EventLiveData.this.mActiveCount == 0 && !mActive) {
                onInactive();
            }
        }
    }

    private class EventAlwaysActiveEventObserver extends EventObserverWrapper {

        EventAlwaysActiveEventObserver(Observer<? super T> observer) {
            super(observer);
        }

        @Override
        boolean shouldBeActive() {
            return true;
        }
    }
}
