package org.nusabit.demoapp.data.remote;

import org.nusabit.demoapp.data.remote.model.User;
import org.nusabit.demoapp.data.remote.model.UsersList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sid-tech on 12/15/17.
 */

public interface GithubUserRestService {

    @GET("/search/users?per_page=2")
    Observable<UsersList> searchGithubUsers(@Query("q") String searchTerm);

    @GET("/users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
