package com.minmax.samplenavigationexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.minmax.samplenavigationexample.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    lateinit var drawerLayout:DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar:Toolbar
    lateinit var appBarConfiguration:AppBarConfiguration
    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar.apply {
            title = getString(R.string.main_title)
            setSupportActionBar(this)
        }

        setUpNavigation()
    }

    private fun setUpNavigation(){
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        navController = findNavController(R.id.nav_host_fragment)

        navigationView.setNavigationItemSelectedListener(this)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        when(id){
            R.id.nav_settings ->navController.navigate(R.id.settingsFragment, null, options)
            R.id.nav_about ->navController.navigate(R.id.aboutFragment, null, options)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)|| super.onSupportNavigateUp()
    }
}
