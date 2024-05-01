package com.example.etalonmobileapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.setupWithNavController
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.etalonmobileapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(main())



        val host : NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navContainer) as NavHostFragment? ?: return
        val navController = host.navController


        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout = findViewById(R.id.drawer_layout))
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setupWithNavController(navController,appBarConfiguration)

        val bottomNav = findViewById(R.id.bottomBar) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> {
                    loadFragment(main())
                    true
                }
                R.id.tasks -> {
                    loadFragment(tasks())
                    true
                }
                R.id.goals -> {
                    loadFragment(profile())
                    true
                }

                else -> { false}
            }
        }
    }



    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.navContainer,fragment)
        transaction.commit()
    }




}