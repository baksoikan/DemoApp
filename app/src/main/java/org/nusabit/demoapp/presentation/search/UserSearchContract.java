package org.nusabit.demoapp.presentation.search;

import org.nusabit.demoapp.data.remote.model.User;
import org.nusabit.demoapp.presentation.base.MvpPresenter;
import org.nusabit.demoapp.presentation.base.MvpView;

import java.util.List;

/**
 * Created by sid-tech on 12/15/17.
 */

public interface UserSearchContract {

    interface View extends MvpView {
        void onSearchResultsLoaded(List<User> githubUserList);

        void showError(String message);

        void showLoading();

        void hideLoading();

        void showSearchResults(List<User> githubUserList);
    }

    interface Presenter extends MvpPresenter<View> {
        void search(String term);
    }
}
