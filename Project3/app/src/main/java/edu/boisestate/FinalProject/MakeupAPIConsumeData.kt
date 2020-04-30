package edu.boisestate.FinalProject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_consume_data.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * consumes data from Makeup API.
 */
class MakeupAPIConsumeData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //calls layout with list for the products
        setContentView(R.layout.activity_consume_data)
        //calls the recycler view to style each product in the list from activity_consume_data
        recyclerview_ConsumeData.layoutManager = LinearLayoutManager(this)
        //recyclerview_ConsumeData.adapter = ConsumeDataAdapter()
        //calls method that consumes the data
        getJSON()
    }

    /**
     * Gets Makeup API JSON content.
     */
    private fun getJSON(){

        val makeupApiUrl = "https://makeup-api.herokuapp.com/api/v1/products.json"

        //Setting the time because it kept timing out and would not connect
        val client = OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()

        val request = Request.Builder().url(makeupApiUrl).build()

        client.newCall(request).enqueue(object:Callback{

            /**
             * Alerts if the connection failed.
             */
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to submit request + $e")
            }

            /**
             * If the connection is successful,
             * then our body contents are added to an array.
             */
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val apiProducts = gson.fromJson(body, kotlin.Array<Base>::class.java).toList()
                println(body)
                //ensures that the recycler view displays
                runOnUiThread {
                    recyclerview_ConsumeData.adapter = ConsumeDataAdapter(apiProducts)
                }
            }
        })
    }
}



