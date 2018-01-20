package com.werockstar.dagger2demo.model

import com.google.gson.Gson
import com.werockstar.dagger2demo.JsonResource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepoTest {

    private lateinit var response: String
    private lateinit var collection: Repo


    @Before
    @Throws(Exception::class)
    fun setUp() {
        response = JsonResource.fromResource("repo.json")
        collection = Gson().fromJson(response, Repo::class.java)
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