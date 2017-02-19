package com.werocksta.dagger2demo.model;

import com.google.gson.Gson;
import com.werocksta.dagger2demo.JsonResource;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepoCollectionTest {

    private String response;
    private RepoCollection collection;


    @Before
    public void setUp() throws Exception {
        response = JsonResource.fromResource("repo.json");
        collection = new Gson().fromJson(response, RepoCollection.class);
    }

    @Test
    public void should_see_repo_name() throws Exception {
        assertEquals("30DaysofSwift", collection.getNameRepo());
    }

    @Test
    public void should_see_full_repo_name() throws Exception {
        assertEquals("Swift", collection.getLanguage());
        assertEquals(58261555, collection.getId());
        assertEquals("https://dummy.com", collection.getHtmlUrl());
        assertEquals("WeRockStar/30DaysofSwift", collection.getFullNameRepo());
    }
}