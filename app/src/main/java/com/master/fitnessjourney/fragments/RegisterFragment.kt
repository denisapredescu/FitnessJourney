package com.master.fitnessjourney.fragments

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputLayout
import com.master.fitnessjourney.BuildConfig
import com.master.fitnessjourney.R
import org.json.JSONObject
import java.net.URL


// Define data class for sign-up request body
//@Serializable
//data class SignUpRequest(
//    val email: String,
//    val username: String,
//    val password: String
//)

// Define data class for response
//@Serializable
//data class SignUpResponse(
//    val token: String
//)

class RegisterFragment : Fragment() {
    private lateinit var email: TextInputLayout
    private lateinit var username: TextInputLayout
    private lateinit var password: TextInputLayout
    private lateinit var confirmPassword: TextInputLayout
    private lateinit var signUpButton: Button

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.your_layout)
//
//        // Initialize views
//        email = findViewById(R.id.email)
//        password = findViewById(R.id.password)
//        confirmPassword = findViewById(R.id.confirmPassword)
//        phone = findViewById(R.id.phone)
//        registerButton = findViewById(R.id.register_button)

        // Set up text watcher
//        val textWatcher = object : TextWatcher {
//            // Override methods here
//        }
//
//        // Attach text watcher to TextInputLayouts
//        email.editText?.addTextChangedListener(textWatcher)
//        password.editText?.addTextChangedListener(textWatcher)
//        confirmPassword.editText?.addTextChangedListener(textWatcher)
//        phone.editText?.addTextChangedListener(textWatcher)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // Initialize views
        email = view.findViewById(R.id.email)
        username = view.findViewById(R.id.username)
        password = view.findViewById(R.id.password)
        confirmPassword = view.findViewById(R.id.confirmPassword)
        signUpButton = view.findViewById(R.id.button_signup)

        // Set up text watcher
        val textWatcher = object : TextWatcher {
            // Override methods here
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                val emailText = email.editText?.text.toString()
                val usernameText = username.editText?.text.toString()
                val passwordText = password.editText?.text.toString()
                val confirmPasswordText = confirmPassword.editText?.text.toString()

                var pass = true

                if (!confirmPasswordText.isEmpty()) {
                    if (notTheSame(passwordText, confirmPasswordText)) {
                        confirmPassword.error = "Not the same password"
                        pass = false
                    } else {
                        confirmPassword.helperText = "Ok"
                    }
                } else {
                    confirmPassword.helperText = "*Required"
                }

                if (!emailText.isEmpty()) {
                    if (!isValidEmail(emailText)) {
                        email.error = "Invalid format for email"
                        pass = false
                    } else {
                        email.helperText = "Ok"
                    }
                } else {
                    email.helperText = "*Required"
                }

                if (!usernameText.isEmpty()) {
                    username.helperText = "Ok"
                } else {
                    pass = false
                    username.helperText = "*Required"
                }

                if (!passwordText.isEmpty()) {
                    if (!isValidPassword(passwordText)) {
                        password.error = "Must contain lowercase characters, uppercase characters, numbers, and special characters"
                        pass = false
                    } else {
                        password.helperText = "Ok"
                    }
                } else {
                    password.helperText = "*Required"
                }
//
                signUpButton.isEnabled = pass
            }

            override fun afterTextChanged(editable: Editable?) {
                // No action needed
            }
        }

        // Attach text watcher to TextInputLayouts
        email.editText?.addTextChangedListener(textWatcher)
        password.editText?.addTextChangedListener(textWatcher)
        confirmPassword.editText?.addTextChangedListener(textWatcher)

        return view
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
            //your codes here

            super.onViewCreated(view, savedInstanceState)

//        email = view.findViewById(R.id.email)
//        username = view.findViewById(R.id.username)
//        password = view.findViewById(R.id.password)
//        confirmPassword = view.findViewById(R.id.confirmPassword)

//        val signUpButton = view.findViewById<Button>(R.id.button_signup)
            signUpButton.setOnClickListener {
                signUp()
            }
        }

    }

    fun notTheSame(password: String, confirmPassword: String): Boolean {
        return password != confirmPassword
    }

    fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^[a-zA-Z0-9@#$%^&!\\\\+=()*_/].{5,20}$"
        return password.matches(passwordRegex.toRegex())
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"
        return email.matches(emailRegex.toRegex())
    }

    fun signUp() {
//        val email = view?.findViewById<EditText>(R.id.email) ?: return
//        val username = view?.findViewById<EditText>(R.id.username) ?: return
//        val password = view?.findViewById<EditText>(R.id.password) ?: return
        val emailText = email.editText?.text.toString()
        val usernameText = username.editText?.text.toString()
        val passwordText = password.editText?.text.toString()

        val username: String
        val password: String
        val email: String
        val phone: String
        val firstname: String
        val lastname: String


        when (BuildConfig.DEBUG) {
            true -> {
                username = "johnd"
                password = "Parola123!"
                email="m38rmF\$"
                phone="1-570-236-7033"
                firstname="John"
                lastname="Doe"
            }

            false -> {
                username = usernameText
                password = passwordText
                email = emailText
                phone="1-570-236-7033"
                firstname="John"
                lastname="Doe"
            }
        }

        val url = "${BuildConfig.BASE_URL}users"
        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            { response ->
                Log.d(TAG, response.toString())

                Toast.makeText(activity,  "Signed up with success", Toast.LENGTH_LONG).show();
                signUpButton.isEnabled = false
            },
            { error ->
                Log.e(error.toString(), this.toString())

                Toast.makeText(activity, "That didn't work!", Toast.LENGTH_LONG).show();
                Toast.makeText(activity, "Error: " + error.message, Toast.LENGTH_LONG).show();
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()

                // Fill in the simple fields directly
                params["username"] = username
                params["password"] = password
                params["email"] = email
                params["phone"] = phone

                // Create nested JSON objects for name and address
                val nameObject = JSONObject()
                nameObject.put("firstname", firstname)
                nameObject.put("lastname", lastname)

//                val addressObject = JSONObject()
//                addressObject.put("city", "kilcoole")
//                addressObject.put("street", "7835 new road")
//                addressObject.put("number", 3)
//                addressObject.put("zipcode", "12926-3874")
//
//                val geolocationObject = JSONObject()
//                geolocationObject.put("lat", "-37.3159")
//                geolocationObject.put("long", "81.1496")
//
//                addressObject.put("geolocation", geolocationObject)

                // Put the nested JSON objects into the params map as strings
                params["name"] = nameObject.toString()
//                params["address"] = addressObject.toString()

                return params
            }
        }

        stringRequest.tag = "SRTAG"

        Volley.newRequestQueue(requireContext()).add(stringRequest)


//        val stringRequest: StringRequest =
//            object : StringRequest(Request.Method.POST, "https://fakestoreapi.com/users",
//                Response.Listener<String?> {
//                fun onResponse(response: String) {
//                    Toast.makeText(getActivity(),  response, Toast.LENGTH_LONG).show();
//                    signUpButton.isEnabled = false
//                    Log.d(TAG, response.toString())
//                }
//            },  Response.ErrorListener {
//                 fun onErrorResponse(error: VolleyError) {
//                    VolleyLog.d(TAG, "Error: " + error.message)
//                    Log.d(TAG, "" + error.message + "," + error.toString())
//                     Toast.makeText(activity, "That didn't work!", Toast.LENGTH_LONG).show();
//                }
//            }) {
//                override fun getParams(): Map<String, String>? {
//                    val params: MutableMap<String, String> = HashMap()
//                    params["email"] = emailText
//                    params["username"] = usernameText
//                    params["password"] = passwordText
//                    params["name"] = "{\n" +
//                            "                            firstname:'John',\n" +
//                            "                            lastname:'Doe'\n" +
//                            "                        }"
//                    params["address"] = "{\n" +
//                            "                            city:'kilcoole',\n" +
//                            "                            street:'7835 new road',\n" +
//                            "                            number:3,\n" +
//                            "                            zipcode:'12926-3874',\n" +
//                            "                            geolocation:{\n" +
//                            "                                lat:'-37.3159',\n" +
//                            "                                long:'81.1496'\n" +
//                            "                            }\n" +
//                            "                        }"
//                    params["phone"] = "1-570-236-7033"
//                    return params
//                }
//
//                @Throws(AuthFailureError::class)
//                override fun getHeaders(): Map<String, String> {
//                    val headers: MutableMap<String, String> = HashMap()
//                    headers["Content-Type"] = "application/x-www-form-urlencoded"
//
//                    return headers
//                }
//            }
//
//                stringRequest.tag = "SRTAG"
//        // Add the request to the RequestQueue.
//        Volley.newRequestQueue(requireContext()).add(stringRequest)
    }

//    fun signUp() { //String?//email: String, username: String, password: String): String? {
////        val email = view?.findViewById<EditText>(R.id.email) ?: return
////        val username = view?.findViewById<EditText>(R.id.username) ?: return
////        val password = view?.findViewById<EditText>(R.id.password) ?: return
//        val emailText = email.editText?.text.toString()
//        val usernameText = username.editText?.text.toString()
//        val passwordText = password.editText?.text.toString()
//
//        Toast.makeText(getActivity(), emailText, Toast.LENGTH_LONG).show();
//
//        // Create sign-up request body
//        val requestBody =
//            "{\"email\":\"$emailText\"username\":\"$usernameText\"password\":\"$passwordText\"}"
//
//            //SignUpRequest(emailText, usernameText, passwordText)
//
//        // Serialize request body to JSON
//        val jsonString = requestBody//Json.encodeToString(requestBody)
//
//        // Make POST request to sign-up endpoint
//        val url = URL("https://fakestoreapi.com/auth/signup")
//
//        val stringRequest = object : StringRequest(
//            Method.POST,
//            "https://fakestoreapi.com/auth/signup",
//            { response ->
////                "success".logErrorMessage()
//                Toast.makeText(getActivity(),  response, Toast.LENGTH_LONG).show();
//                signUpButton.isEnabled = false
////                goToProducts()
//            },
//            {
//                Toast.makeText(getActivity(), "That didn't work!", Toast.LENGTH_LONG).show();
////                "That didn't work!".logErrorMessage()
//            }
//        ) {
//            override fun getParams(): MutableMap<String, String> {
//                val params: MutableMap<String, String> = HashMap()
//                params["email"] = emailText
//                params["username"] = usernameText
//                params["password"] = passwordText
//
//                return params
//            }
//        }
//        stringRequest.tag = "SRTAG"
//        // Add the request to the RequestQueue.
//        Volley.newRequestQueue(requireContext()).add(stringRequest)
////        VolleyRequestQueue.addToRequestQueue(stringRequest)
////        (url.openConnection() as? HttpURLConnection)?.run {
////            requestMethod = "POST"
////            doOutput = true
////            outputStream.write(jsonString.toByteArray())
////
////            // Get response
////            val responseCode = responseCode
////            if (responseCode == HttpURLConnection.HTTP_OK) {
////                val responseString = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
//////                val signUpResponse = Json.decodeFromString<SignUpResponse>(responseString)
//////                return signUpResponse.token
////                Toast.makeText(getActivity(), responseString, Toast.LENGTH_LONG).show();
////
////                return responseString
////            } else {
////                println("Error: HTTP $responseCode")
////            }
////            disconnect()
////        }
////        return null
//    }

//    fun main() {
//        // Example usage
//        val token = signUp("test@example.com", "testuser", "password123")
//        println("Token: $token")
//    }

//    private fun doLogin() {
//        val editUsername = view?.findViewById<EditText>(R.id.et_username) ?: return
//        val editPassword = view?.findViewById<EditText>(R.id.et_password) ?: return
//
//        // Create a User object
//        val user = User(
//            email = "John@gmail.com",
//            username = "johnd",
//            password = "m38rmF$",
//            name = Name("John", "Doe"),
//            address = Address(
//                city = "kilcoole",
//                street = "7835 new road",
//                number = 3,
//                zipcode = "12926-3874",
//                geolocation = Geolocation(lat = "-37.3159", long = "81.1496")
//            ),
//            phone = "1-570-236-7033"
//        )
//
//        // Serialize the User object to JSON
//        val jsonString = Json.encodeToString(user)
//
//        // Make a POST request
//        val url = URL("https://fakestoreapi.com/users")
//        with(url.openConnection() as HttpURLConnection) {
//            requestMethod = "POST"
//            doOutput = true
//            outputStream.write(jsonString.toByteArray())
//
//            // Get response
//            val response = inputStream.bufferedReader().use { it.readText() }
//            println(response)
//        }
//    }


}