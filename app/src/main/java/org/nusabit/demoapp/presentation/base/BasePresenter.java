package org.nusabit.demoapp.presentation.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sid-tech on 12/15/17.
 */

public class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T view;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
        compositeSubscription.clear();
    }

    public T getView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public void addSubscription(Subscription subscription) {
        this.compositeSubscription.add(subscription);
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter");
        }
    }
}
