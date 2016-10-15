package com.werocksta.dagger2demo.model;

import com.google.gson.Gson;
import com.werocksta.dagger2demo.JsonResource;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GithubUserInfoCollectionTest {

    private String response;
    private GithubUserInfoCollection collection;

    @Before
    public void setUp() throws Exception {
        response = JsonResource.fromResource("github_profile.json");
        collection = new Gson().fromJson(response, GithubUserInfoCollection.class);
    }

    @Test
    public void collection_should_not_null() throws Exception {
        assertNotNull(collection);
    }

    @Test
    public void should_see_username() throws Exception {
        assertEquals("WeRockStar", collection.getUsername());
    }

    @Test
    public void should_see_url_avatar() throws Exception {
        assertEquals("https://avatars.githubusercontent.com/u/5949511?v=3", collection.getImageUrl());
    }

    @Test
    public void should_see_url_repo() throws Exception {
        assertEquals("https://api.github.com/users/WeRockStar/repos", collection.getRepoUrl());
    }

    @Test
    public void should_see_id() throws Exception {
        assertEquals(5949511, collection.getId());
    }
}