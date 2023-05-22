package com.azimzada.retrofitrecycleexamson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.azimzada.retrofitrecycleexamson.adapter.CommentAdapter
import com.azimzada.retrofitrecycleexamson.databinding.ActivityPostBinding
import com.azimzada.retrofitrecycleexamson.model.Comment
import com.azimzada.retrofitrecycleexamson.model.PostDTO
import com.azimzada.retrofitrecycleexamson.networkjk.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPostBinding
    lateinit var post: PostDTO
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPostBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = intent
        post = intent.getSerializableExtra("Post") as PostDTO
        binding.postTitleText.setText(post.title)
        binding.postBodyText.setText(post.body)
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        getComment()
    }
    fun getComment(){
        val call: Call<List<Comment?>?>? = RetrofitClient.getRetrofitInstance()?.getApi()?.getComments(post.id)
        call?.enqueue(object : Callback<List<Comment?>?> {
            override fun onResponse(call: Call<List<Comment?>?>?, response: Response<List<Comment?>?>) {
                val postList: List<Comment> = response.body() as List<Comment>
                binding.commentRecyclerView.adapter = CommentAdapter(postList)
            }

            override fun onFailure(call: Call<List<Comment?>?>?, t: Throwable?) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }
}