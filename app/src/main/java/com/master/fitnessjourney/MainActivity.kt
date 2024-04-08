package com.master.fitnessjourney

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.master.fitnessjourney.R

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("CONTEXT_DETAILS", Context.MODE_PRIVATE)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(
            bottomNavigationView, navController
        )

        if (sharedPreferences.getString("email", "").equals("")) {  // user not logged
            unlogggedBottomNavigation();
        } else {
            logggedBottomNavigation();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.navigation_home -> {

                    navController.navigate(R.id.navigation_home)
                    true
                }

                R.id.navigation_login -> {
                    navController.navigate(R.id.navigation_login)
                    true
                }

                R.id.navigation_register -> {
                    navController.navigate(R.id.navigation_register)
                    true
                }

                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile)
                    true
                }

                R.id.navigation_find_exercices -> {
                    navController.navigate(R.id.navigation_find_exercices)
                    true
                }

                R.id.navigation_in_progress_exercices -> {
                    navController.navigate(R.id.navigation_in_progress_exercices)
                    true
                }

                R.id.navigation_statistics -> {
                    navController.navigate(R.id.navigation_statistics)
                    true
                }

                else -> false
            }
        }
    }

    fun logggedBottomNavigation() {
//        val headerView: View = navigationView.getHeaderView(0)
//        val emailNavbar = headerView.findViewById<TextView>(R.id.emailNavbar)
//        emailNavbar.text = email
        bottomNavigationView.menu.clear()
        bottomNavigationView.inflateMenu(R.menu.bottom_logged_user_menu)
//        bottomNavigationView.getMenu().add(R.id.navigation_home)
//        bottomNavigationView.getMenu().add(R.id.navigation_profile)
//        bottomNavigationView.getMenu().add(R.id.navigation_find_exercices)
//        bottomNavigationView.getMenu().add(R.id.navigation_in_progress_exercices)
//        bottomNavigationView.getMenu().add(R.id.navigation_statistics)
////        bottomNavigationView.getMenu().findItem(R.id.navigation_login).setVisible(false)
////        bottomNavigationView.getMenu().findItem(R.id.navigation_register).setVisible(false)
//        bottomNavigationView.getMenu().removeItem(R.id.navigation_login)
//        bottomNavigationView.getMenu().removeItem(R.id.navigation_register)
    }

    fun unlogggedBottomNavigation() {
//        val headerView: View = navigationView.getHeaderView(0)
//        val emailNavbar = headerView.findViewById<TextView>(R.id.emailNavbar)
//        emailNavbar.text = ""
        bottomNavigationView.menu.clear()
        bottomNavigationView.inflateMenu(R.menu.bottom_unlogged_user_menu)
//        bottomNavigationView.getMenu().add(R.id.navigation_home)
////        bottomNavigationView.getMenu().findItem(R.id.navigation_profile).setVisible(false)
////        bottomNavigationView.getMenu().findItem(R.id.navigation_statistics).setVisible(false)
////        bottomNavigationView.getMenu().findItem(R.id.navigation_find_exercices).setVisible(false)
////        bottomNavigationView.getMenu().findItem(R.id.navigation_in_progress_exercices).setVisible(false)
//        bottomNavigationView.getMenu().add(R.id.navigation_login)
//        bottomNavigationView.getMenu().add(R.id.navigation_register)
//
//        bottomNavigationView.getMenu().removeItem(R.id.navigation_profile)
//        bottomNavigationView.getMenu().removeItem(R.id.navigation_statistics)
//        bottomNavigationView.getMenu().removeItem(R.id.navigation_find_exercices)
//        bottomNavigationView.getMenu().removeItem(R.id.navigation_in_progress_exercices)
    }
}