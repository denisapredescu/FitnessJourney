package com.master.fitnessjourney.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputLayout
import com.master.fitnessjourney.BuildConfig
import com.master.fitnessjourney.MainActivity
import com.master.fitnessjourney.R
import com.master.fitnessjourney.helpers.LogInOutEvent
import org.greenrobot.eventbus.EventBus

//interface OnLoginSuccessListener {
//    fun onLoginSuccess()
//}

class LoginFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
//    private var loginSuccessListener: OnLoginSuccessListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedPreferences =
            (activity?.getSharedPreferences("CONTEXT_DETAILS", Context.MODE_PRIVATE))!!

        return inflater.inflate(R.layout.fragment_login, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val doLoginBtn = view.findViewById<Button>(R.id.button_login)
        doLoginBtn.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        val editUsername = view?.findViewById<TextInputLayout>(R.id.et_username) ?: return
        val editPassword = view?.findViewById<TextInputLayout>(R.id.et_password) ?: return

        val username: String
        val password: String

        when (BuildConfig.DEBUG) {
            true -> {
                username = "mor_2314"
                password = "83r5^_"
            }

            false -> {
                username = editUsername.toString().trim()
                password = editPassword.toString().trim()
            }
        }

        val url = "${BuildConfig.BASE_URL}auth/login"

        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            { response ->
                Log.e("success", this.toString())

                Toast.makeText(activity,  "Signed in with success", Toast.LENGTH_LONG).show();

                sharedPreferences.edit().putString("email", "set email").apply()
                sharedPreferences.edit().putString("token", response).apply()

                EventBus.getDefault().post(LogInOutEvent(isLoggedIn = true))

                val action = LoginFragmentDirections.actionNavigationLoginToNavigationHome()
                findNavController().navigate(action)
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

    private fun goToHome() {
        val fragment = HomeFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.action_navigation_login_to_navigation_home, fragment)?.commit()
    }

    private fun goToActivity() {
        val i = Intent(
            activity,
            MainActivity::class.java
        )
        startActivity(i)
        (activity as Activity?)!!.overridePendingTransition(0, 0)
    }
}