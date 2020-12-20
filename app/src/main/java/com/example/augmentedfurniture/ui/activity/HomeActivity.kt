package com.example.augmentedfurniture.ui.activity

import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.augmentedfurniture.R
import com.example.augmentedfurniture.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun getLayoutResId(): Int {
        return R.layout.activity_home
    }

    override fun initViews() {

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Bottom Navigation
        navController = findNavController(R.id.navHostFragment)
        bottomNavigationView.setupWithNavController(navController)

        // Navigation Back Arrow
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(navigationView, navController)
    }

    // BottomNavigation and NavDrawer
    override fun onSupportNavigateUp(): Boolean {

       return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun setListeners() {
    }
}