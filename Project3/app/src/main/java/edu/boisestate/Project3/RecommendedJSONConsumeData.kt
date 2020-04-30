package edu.boisestate.Project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_recommended_jsonconsume_data.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class RecommendedJSONConsumeData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommended_jsonconsume_data)

        recommended_recyclerView.layoutManager = LinearLayoutManager(this)
        getRecommendedJson()
    }


    private fun getRecommendedJson() {
        val recommendedUrl = "https://api.jsonbin.io/b/5ea614c41299b9374236ac8f"

        val client = OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()

        val request = Request.Builder().url(recommendedUrl).build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to submit request + $e")
            }

            /**
             * If the connection is successful,
             * then our body contents are added to an array.
             */
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)
                val gson = GsonBuilder().create()
                val recProducts:List<RecommendedBase> =
                    gson.fromJson(body, Array<RecommendedBase>::class.java).toList()
                println(body)
                //ensures that the recycler view displays

                runOnUiThread {
                    recommended_recyclerView.adapter = RecommendedJSONConsumeDataAdapter(recProducts)
                }

            }
        })
    }
}
