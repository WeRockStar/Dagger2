package com.werocksta.dagger2demo.model;

import com.google.gson.Gson;
import com.werocksta.dagger2demo.JsonResource;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GithubUserCollectionTest {

    private String response;
    private GithubUserCollection collection;

    @Before
    public void setUp() throws Exception {
        response = JsonResource.fromResource("github_profile.json");
        collection = new Gson().fromJson(response, GithubUserCollection.class);
    }

    @Test
    public void collection_should_not_null() throws Exception {
        assertNotNull(collection);
    }

    @Test
    public void should_see_github_user() throws Exception {
        assertEquals("WeRockStar", collection.getUsername());
        assertEquals("https://avatars.githubusercontent.com/u/5949511?v=3", collection.getImageUrl());
        assertEquals("https://api.github.com/users/WeRockStar/repos", collection.getRepoUrl());
        assertEquals(5949511, collection.getId());
    }
}