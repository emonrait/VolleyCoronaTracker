package com.example.volleycoronatracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import org.json.JSONException

import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    private lateinit var totalCasesWorld: TextView
    private lateinit var totalDeathsWorld: TextView
    private lateinit var totalRecoveredWorld: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize the objects
        totalCasesWorld = findViewById(R.id.newCasesWorld)
        totalDeathsWorld = findViewById(R.id.newDeathsWorld)
        totalRecoveredWorld = findViewById(R.id.newRecoveredWorld)

        //getData()

        getData();
    }

    private fun getData() {

        // Create a String request using Volley Library
        //val myUrl = "https://corona.lmao.ninja/v2/all"
        val myUrl = "https://disease.sh/v3/covid-19/historical/all?"
        val myRequest = StringRequest(
            Request.Method.GET, myUrl,
            { response ->
                try {
                    //Create a JSON object containing information from the API.
                    val myJsonObject = JSONObject(response)
                    totalCasesWorld.setText(myJsonObject.getString("cases"))
                    totalRecoveredWorld.setText(myJsonObject.getString("recovered"))
                    totalDeathsWorld.setText(myJsonObject.getString("deaths"))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { volleyError ->
            Toast.makeText(
                this@MainActivity,
                volleyError.message,
                Toast.LENGTH_SHORT
            )
                .show()
        }
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(myRequest)
    }
}