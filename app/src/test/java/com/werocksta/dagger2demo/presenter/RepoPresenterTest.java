package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.manager.ApiService;
import com.werocksta.dagger2demo.model.RepoCollection;
import com.werocksta.dagger2demo.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepoPresenterTest {

    @Mock
    ApiService service;

    @Mock
    RepoPresenter.View view;

    @Rule
    public RxSchedulersOverrideRule mRxSchedulersRule = new RxSchedulersOverrideRule();

    RepoPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new RepoPresenterImpl(view, service);
    }

    @Test
    public void presenterShouldNotNull() throws Exception {
        assertNotNull(presenter);
    }

    @Test
    public void getRepoShouldDisplayListRepo() throws Exception {
        List<RepoCollection> collections = new ArrayList<>();
        when(service.getRepo("WeRockStar")).thenReturn(Observable.just(collections));
        presenter.getRepo("WeRockStar");
        verify(view).loading();
        verify(view).displayRepo(collections);
        verify(view, times(2)).loadComplete();
    }

    @Test
    public void getRepoErrorShouldReturnException() throws Exception {
        when(service.getRepo("WeRockStar")).thenReturn(Observable.error(new Throwable()));
        presenter.getRepo("WeRockStar");
        verify(view).loading();
        verify(view).getRepoError(null);
        verify(view, times(1)).loadComplete();
    }
}