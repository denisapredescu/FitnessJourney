package com.master.fitnessjourney

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    lateinit var sharedPreferences: SharedPreferences
    lateinit var optionMenu: Menu
    var shouldShowOptionsMenu: Boolean = false

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
            toggleOptionsMenuVisibility(true)
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
        bottomNavigationView.menu.clear()
        bottomNavigationView.inflateMenu(R.menu.bottom_logged_user_menu)
    }

    fun unlogggedBottomNavigation() {
        bottomNavigationView.menu.clear()
        bottomNavigationView.inflateMenu(R.menu.bottom_unlogged_user_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if(shouldShowOptionsMenu) {
            menuInflater.inflate(R.menu.context_menu, menu);
            return super.onCreateOptionsMenu(menu)
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_menu_profile -> {
                navController.navigate(R.id.navigation_profile)
                true
            }
            R.id.option_menu_exit -> {
                toggleOptionsMenuVisibility(false)
                navController.navigate(R.id.navigation_login)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun toggleOptionsMenuVisibility(shouldShow: Boolean) {
        shouldShowOptionsMenu = shouldShow
        invalidateOptionsMenu() // This will force recreate the options menu
    }
}