package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.manager.ApiService;
import com.werocksta.dagger2demo.model.GithubUserInfoCollection;
import com.werocksta.dagger2demo.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class GithubUserInfoPresenterTest {

    @Rule
    public RxSchedulersOverrideRule mRxSchedulersOverride = new RxSchedulersOverrideRule();

    GithubUserInfoPresenter presenter;

    @Mock
    GithubUserInfoPresenter.View view;

    @Mock
    ApiService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new GithubUserInfoPresenter(view, service);
    }

    @Test
    public void presenterShouldBeNotNull() throws Exception {
        assertNotNull(presenter);
    }

    @Test
    public void getUserInfoShouldHaveUserInfo() throws Exception {
        GithubUserInfoCollection userInfo = new GithubUserInfoCollection();
        when(service.getUserInfo("WeRockStar")).thenReturn(Observable.just(userInfo));
        presenter.getUserInfo("WeRockStar");
        verify(view).loading();
        verify(view).getUserInfoSuccess(userInfo);
        verify(view).getUserInfoComplete();
    }

    @Test
    public void getUserInfoErrorShouldHaveException() throws Exception {
        Throwable exception = new Throwable();
        when(service.getUserInfo("WeRockStar")).thenReturn(Observable.error(exception));
        presenter.getUserInfo("WeRockStar");
        verify(view).loading();
        verify(view).getUserInfoError(exception.getMessage());
        verify(view).getUserInfoComplete();
    }
}