package com.azimzada.retrofitrecycleexamson
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.azimzada.retrofitrecycleexamson.adapter.OnItemClickListener
import com.azimzada.retrofitrecycleexamson.adapter.PostAdapter
import com.azimzada.retrofitrecycleexamson.databinding.ActivityMainBinding
import com.azimzada.retrofitrecycleexamson.model.PostDTO
import com.azimzada.retrofitrecycleexamson.networkjk.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getPosts()
    }

    fun getPosts() {
        val call: Call<List<PostDTO?>?>? =
            RetrofitClient.getRetrofitInstance()?.getApi()?.getPosts()
        call?.enqueue(object : Callback<List<PostDTO?>?> {
            override fun onResponse(
                call: Call<List<PostDTO?>?>?,
                response: Response<List<PostDTO?>?>
            ) {
                val postList: List<PostDTO> = response.body() as List<PostDTO>
                binding.recyclerView.adapter = PostAdapter(postList, object : OnItemClickListener {
                    override fun OnItemClick(item: PostDTO) {
                        val intent = Intent(this@MainActivity, PostActivity::class.java)
                        intent.putExtra("Post", item)
                        Log.d("Salam", "Gonder ")
                        startActivity(intent)
                    }
                })
            }

            override fun onFailure(call: Call<List<PostDTO?>?>?, t: Throwable?) {
                Log.d("Salam", " tOAST ")
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }
}