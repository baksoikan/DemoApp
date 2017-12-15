package org.nusabit.demoapp.presentation.search;

import org.nusabit.demoapp.data.UserRepository;
import org.nusabit.demoapp.data.remote.model.User;
import org.nusabit.demoapp.presentation.base.BasePresenter;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by sid-tech on 12/15/17.
 */

class UserSearchPresenter extends BasePresenter<UserSearchContract.View> implements UserSearchContract.Presenter {
    private final Scheduler mainScheduler, ioScheduler;
    private UserRepository userRepository;

    UserSearchPresenter(UserRepository userRepository, Scheduler ioScheduler, Scheduler mainScheduler) {
        this.userRepository = userRepository;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    @Override
    public void search(String term) {
        checkViewAttached();
        getView().showLoading();
        addSubscription(userRepository.searchUsers(term).subscribeOn(ioScheduler).observeOn(mainScheduler)
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showError(
                                e.getMessage()); //TODO You probably don't want this error to show to users - Might want to show a friendlier message :)
                    }

                    @Override
                    public void onNext(List<User> users) {
                        getView().hideLoading();
                        getView().showSearchResults(users);
                    }
                }));
    }
}
