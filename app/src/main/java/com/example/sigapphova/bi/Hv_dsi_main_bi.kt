package com.example.sigapphova.bi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sigapphova.R
import com.example.sigapphova.fragments.hv_home_fragment
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import com.example.sigapphova.fragments.contact_hospital_hv
import com.google.android.gms.dynamic.SupportFragmentWrapper

class Hv_dsi_main_bi : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dsi_main_bi)
        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_item)
        navigationView.setNavigationItemSelectedListener(this)

        val toogle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation, R.string.close_navigation)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, hv_home_fragment()).commit()
            navigationView.setCheckedItem(R.id.nav_item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.dsiHome -> supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, hv_home_fragment()).commit()
           R.id.dsiCon -> supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, contact_hospital_hv()).commit()
       }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            onBackPressedDispatcher.onBackPressed()
        }
    }



}