package com.werocksta.dagger2demo.model

import com.google.gson.Gson
import com.werocksta.dagger2demo.JsonResource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepoCollectionTest {

    private lateinit var response: String
    private lateinit var collection: RepoCollection


    @Before
    @Throws(Exception::class)
    fun setUp() {
        response = JsonResource.fromResource("repo.json")
        collection = Gson().fromJson(response, RepoCollection::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_repo_name() {
        assertEquals("30DaysofSwift", collection.nameRepo)
    }

    @Test
    @Throws(Exception::class)
    fun should_see_full_repo_name() {
        assertEquals("Swift", collection.language)
        assertEquals(58261555, collection.id.toLong())
        assertEquals("https://dummy.com", collection.htmlUrl)
        assertEquals("WeRockStar/30DaysofSwift", collection.fullNameRepo)
    }
}