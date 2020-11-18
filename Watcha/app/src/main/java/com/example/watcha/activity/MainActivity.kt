package com.example.watcha.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.watcha.Fragment.HomeFragment
import com.example.watcha.R
import com.example.watcha.api.MovieRetrofit
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {
    val retrofit:MovieRetrofit by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()
        bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_home ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment())
                    .commit()
            }

            R.id.menu_category ->{

            }

            R.id.menu_star ->{

            }

            R.id.menu_page ->{

            }
        }

        return true

    }


}