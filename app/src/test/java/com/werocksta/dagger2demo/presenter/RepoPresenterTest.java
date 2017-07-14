package com.werocksta.dagger2demo.presenter;

import com.werocksta.dagger2demo.api.GithubAPI;
import com.werocksta.dagger2demo.model.RepoCollection;
import com.werocksta.dagger2demo.util.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepoPresenterTest {

    @Mock public GithubAPI api;

    @Mock public RepoPresenter.View view;

    @Rule
    public RxSchedulersOverrideRule mRxSchedulersRule = new RxSchedulersOverrideRule();

    RepoPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new RepoPresenter(api);
        presenter.injectView(view);
    }

    @Test
    public void presenterShouldNotNull() throws Exception {
        assertNotNull(presenter);
    }

    @Test
    public void getRepoShouldDisplayListRepo() throws Exception {
        String user = "WeRockStar";
        List<RepoCollection> collections = new ArrayList<>();

        when(api.getRepo(user)).thenReturn(Observable.just(collections));
        presenter.getRepo(user);

        verify(view).loading();
        verify(view).displayRepo(collections);
        verify(view).loadComplete();
    }

    @Test
    public void getRepoErrorShouldReturnEmptyArray() throws Exception {
        String emptyUser = "";

        when(api.getRepo(emptyUser)).thenReturn(Observable.error(new Throwable()));
        presenter.getRepo(emptyUser);

        verify(view).loading();
        verify(view).displayRepo(new ArrayList<>());
        verify(view).loadComplete();
    }

    @After
    public void tearDown() throws Exception {
        presenter.onStop();
    }
}