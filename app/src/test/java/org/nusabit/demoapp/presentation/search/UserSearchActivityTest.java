package org.nusabit.demoapp.presentation.search;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nusabit.demoapp.data.UserRepository;
import org.nusabit.demoapp.data.remote.model.User;
import org.nusabit.demoapp.data.remote.model.UsersList;
import org.nusabit.demoapp.presentation.base.BasePresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sid-tech on 12/15/17.
 */
public class UserSearchActivityTest {
    private static final String USER_LOGIN_RIGGAROO = "baksosapi";
    private static final String USER_LOGIN_2_REBECCA = "ahmad";
    @Mock
    UserRepository userRepository;
    @Mock
    UserSearchContract.View view;

    UserSearchPresenter userSearchPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userSearchPresenter = new UserSearchPresenter(userRepository, Schedulers.immediate(), Schedulers.immediate());
        userSearchPresenter.attachView(view);
    }

    @Test
    public void search_ValidSearchTerm_ReturnsResults() {
        UsersList userList = getDummyUserList();
        when(userRepository.searchUsers(anyString())).thenReturn(Observable.<List<User>>just(userList.getItems()));

        userSearchPresenter.search("riggaroo");

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view).showSearchResults(userList.getItems());
        verify(view, never()).showError(anyString());
    }

    @Test
    public void search_UserRepositoryError_ErrorMsg() {
        String errorMsg = "No internet";
        when(userRepository.searchUsers(anyString())).thenReturn(Observable.error(new IOException(errorMsg)));

        userSearchPresenter.search("bookdash");

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view, never()).showSearchResults(anyList());
        verify(view).showError(errorMsg);
    }

    UsersList getDummyUserList() {
        List<User> githubUsers = new ArrayList<>();
        githubUsers.add(user1FullDetails());
        githubUsers.add(user2FullDetails());
        return new UsersList(githubUsers);
    }

    User user1FullDetails() {
        return new User(USER_LOGIN_RIGGAROO, "Rigs Franks", "avatar_url", "Bio1");
    }

    User user2FullDetails() {
        return new User(USER_LOGIN_2_REBECCA, "Rebecca Franks", "avatar_url2", "Bio2");
    }

    @Test(expected = BasePresenter.MvpViewNotAttachedException.class)
    public void search_NotAttached_ThrowsMvpException() {
        userSearchPresenter.detachView();

        userSearchPresenter.search("test");

        verify(view, never()).showLoading();
        verify(view, never()).showSearchResults(anyList());
    }
}