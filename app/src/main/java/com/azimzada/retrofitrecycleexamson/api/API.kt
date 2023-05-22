package com.azimzada.retrofitrecycleexamson.api

import com.azimzada.retrofitrecycleexamson.model.Comment
import com.azimzada.retrofitrecycleexamson.model.PostDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("posts")
    fun getPosts(): Call<List<PostDTO?>?>?

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): Call<List<Comment?>?>?

}