package com.example.augmentedfurniture.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.augmentedfurniture.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    lateinit var drawerLayout: DrawerLayout
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        drawerLayout = findViewById(R.id.drawerLayout)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navigationView = findViewById(R.id.navigationView)

        navController = findNavController(R.id.navHostFragment) //Initialising navController

        appBarConfiguration = AppBarConfiguration.Builder(R.id.navHome, R.id.navSearch,
            R.id.navWishlist, R.id.navMyProfile) //Pass the ids of fragments from nav_graph which you d'ont want to show back button in toolbar
            .setDrawerLayout(drawerLayout) //Pass the drawer layout id from activity xml
            .build()

        setSupportActionBar(toolbar) //Set toolbar

        // Navigation Back Arrow
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)

        setupActionBarWithNavController(navController, appBarConfiguration) //Setup toolbar with back button and drawer icon according to appBarConfiguration

        visibilityNavElements(navController) //If you want to hide drawer or bottom navigation configure that in this function
    }

    private fun visibilityNavElements(navController: NavController) {

        //Listen for the change in fragment (navigation) and hide or show drawer or bottom navigation accordingly if required
        //Modify this according to your need
        //If you want you can implement logic to deselect(styling) the bottom navigation menu item when drawer item selected using listener

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navSearch -> hideBothNavigation()
                R.id.navWishlist -> showBothNavigation()
                R.id.navMyCart -> showBothNavigation()
                R.id.navMyOrders -> hideBothNavigation()
                R.id.navMyProfile -> showBothNavigation()
                else -> showBothNavigation()
            }
        }

    }

    private fun hideBothNavigation() { //Hide both drawer and bottom navigation bar
        bottomNavigationView.visibility = View.GONE
        navigationView.visibility = View.GONE
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) //To lock navigation drawer so that it don't respond to swipe gesture
    }

    private fun hideBottomNavigation() { //Hide bottom navigation
        bottomNavigationView.visibility = View.GONE
        navigationView.visibility = View.VISIBLE
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED) //To unlock navigation drawer

        navigationView.setupWithNavController(navController) //Setup Drawer navigation with navController
    }

    private fun showBothNavigation() {
        bottomNavigationView.visibility = View.VISIBLE
        navigationView.visibility = View.VISIBLE
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        setupNavControl() //To configure navController with drawer and bottom navigation
    }

    private fun setupNavControl() {
        navigationView.setupWithNavController(navController) //Setup Drawer navigation with navController
        bottomNavigationView.setupWithNavController(navController) //Setup Bottom navigation with navController
    }

    override fun onSupportNavigateUp(): Boolean { //Setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        // If drawer layout is open close that on back pressed
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }
}