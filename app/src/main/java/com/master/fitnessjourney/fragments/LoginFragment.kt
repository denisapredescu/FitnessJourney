package com.master.fitnessjourney.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.android.volley.BuildConfig
import com.android.volley.toolbox.StringRequest
import com.master.fitnessjourney.R
import java.lang.reflect.Method
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

// Define data class for login request body
@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

// Define data class for response
@Serializable
data class LoginResponse(
    val token: String
)


class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        val doLoginBtn = view.findViewById<Button>(R.id.btn_login)
//        doLoginBtn.setOnClickListener { login("test@example.com", "password123") }
    }
//
//    private fun login(email: String, password: String): String? {
//        // Create login request body
//        val requestBody = LoginRequest(email, password)
//
//        // Serialize request body to JSON
//        val jsonString = Json.encodeToString(requestBody)
//
//        // Make POST request to login endpoint
//        val url = URL("https://fakestoreapi.com/auth/login")
//        (url.openConnection() as? HttpURLConnection)?.run {
//            requestMethod = "POST"
//            doOutput = true
//            outputStream.write(jsonString.toByteArray())
//
//            // Get response
//            val responseCode = responseCode
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                val responseString = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
//                val loginResponse = Json.decodeFromString<LoginResponse>(responseString)
//                return loginResponse.token
//            } else {
//                println("Error: HTTP $responseCode")
//            }
//            disconnect()
//        }
//        return null
//    }
//
//    private fun main() {
//        // Example usage
//        val token = login("test@example.com", "password123")
//        println("Token: $token")
//    }



//    private fun doLogin() {
//        val editUsername = view?.findViewById<EditText>(R.id.et_username) ?: return
//        val editPassword = view?.findViewById<EditText>(R.id.et_password) ?: return
//
//        val username: String
//        val password: String
//
//        when (BuildConfig.DEBUG) {
//            true -> {
//                username = "mor_2314"
//                password = "83r5^_"
//            }
//
//            false -> {
//                username = editUsername.text.toString().trim()
//                password = editPassword.text.toString().trim()
//            }
//        }
//
////        val url = "${BuildConfig.BASE_URL}auth/login"
//        val url = "https://fakestoreapi.com/auth/login"
//
//        val stringRequest = object : StringRequest(
//            Method.POST,
//            url,
//            { response ->
//                Log.e("success", this.toString())
//
////                goToProducts()
//            },
//            {
//                Log.e("That didn't work!", this.toString())
//            }
//        ) {
//            override fun getParams(): MutableMap<String, String> {
//                val params: MutableMap<String, String> = HashMap()
//                params["username"] = username
//                params["password"] = password
//                return params
//            }
//        }
//
//        stringRequest.tag = "SRTAG"
//
//        VolleyRequestQueue.addToRequestQueue(stringRequest)
//    }
}