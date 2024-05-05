package com.master.fitnessjourney.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.master.fitnessjourney.BuildConfig

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.master.fitnessjourney.R

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
        val doLoginBtn = view.findViewById<Button>(R.id.btn_login)
        doLoginBtn.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        val editUsername = view?.findViewById<EditText>(R.id.et_username) ?: return
        val editPassword = view?.findViewById<EditText>(R.id.et_password) ?: return

        val username: String
        val password: String

        when (BuildConfig.DEBUG) {
            true -> {
                username = "mor_2314"
                password = "83r5^_"
            }

            false -> {
                username = editUsername.text.toString().trim()
                password = editPassword.text.toString().trim()
            }
        }

        val url = "${com.master.fitnessjourney.BuildConfig.BASE_URL}auth/login"

        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            { response ->
                Log.e("success", this.toString())

                Toast.makeText(activity,  "Signed in with success", Toast.LENGTH_LONG).show();
            },
            { error->
                Log.e("That didn't work!", this.toString())
                Log.e(error.toString(), this.toString())

                Toast.makeText(activity, "That didn't work!", Toast.LENGTH_LONG).show();
                Toast.makeText(activity, "Error: " + error.message, Toast.LENGTH_LONG).show();
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        stringRequest.tag = "SRTAG"

        Volley.newRequestQueue(requireContext()).add(stringRequest)

//        VolleyRequestQueue.addToRequestQueue(stringRequest)
    }
}