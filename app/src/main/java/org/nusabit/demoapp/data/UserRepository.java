package org.nusabit.demoapp.data;

import org.nusabit.demoapp.data.remote.model.User;

import java.util.List;

import rx.Observable;

/**
 * Created by sid-tech on 12/15/17.
 */

public interface UserRepository {

    Observable<List<User>> searchUsers(String searchTerm);

}
