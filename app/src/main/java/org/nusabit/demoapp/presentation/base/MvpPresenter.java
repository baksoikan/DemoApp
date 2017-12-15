package org.nusabit.demoapp.presentation.base;

/**
 * Created by sid-tech on 12/15/17.
 */

public interface MvpPresenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}
