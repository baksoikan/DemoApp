package org.nusabit.demoapp.data;

import org.nusabit.demoapp.data.remote.GithubUserRestService;
import org.nusabit.demoapp.data.remote.model.User;

import java.io.IOException;
import java.util.List;

import rx.Observable;

/**
 * Created by sid-tech on 12/15/17.
 */

public class UserRepositoryImpl implements UserRepository {

    private GithubUserRestService githubUserRestService;

    public UserRepositoryImpl(GithubUserRestService githubUserRestService) {
        this.githubUserRestService = githubUserRestService;
    }

    @Override
    public Observable<List<User>> searchUsers(final String searchTerm) {
        return Observable.defer(() -> githubUserRestService.searchGithubUsers(searchTerm).concatMap(
                usersList -> Observable.from(usersList.getItems())
                        .concatMap(user -> githubUserRestService.getUser(user.getLogin())).toList()))
                .retryWhen(observable -> observable.flatMap(o -> {
                    if (o instanceof IOException) {
                        return Observable.just(null);
                    }
                    return Observable.error(o);
                }));
    }

}
