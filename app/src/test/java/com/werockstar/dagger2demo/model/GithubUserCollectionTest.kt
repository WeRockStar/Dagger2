package com.werockstar.dagger2demo.model

import com.google.gson.Gson
import com.werockstar.dagger2demo.JsonResource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GithubUserCollectionTest {

    private lateinit var response: String
    private lateinit var collection: GithubUserCollection

    @Before
    @Throws(Exception::class)
    fun setUp() {
        response = JsonResource.fromResource("github_profile.json")
        collection = Gson().fromJson(response, GithubUserCollection::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun collection_should_not_null() {
        assertNotNull(collection)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_github_user() {
        assertEquals("WeRockStar", collection.username)
        assertEquals("https://avatars.githubusercontent.com/u/5949511?v=3", collection.imageUrl)
        assertEquals("https://api.github.com/users/WeRockStar/repos", collection.repoUrl)
        assertEquals(5949511, collection.id.toLong())
    }
}