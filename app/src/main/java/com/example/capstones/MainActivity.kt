package com.example.capstones

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.capstones.databinding.ActivityMainBinding
import com.example.capstones.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, HomeFragment())
                .commit()
        }

        @Suppress("DEPRECATION")
        navView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_favorite -> {
                try {
                    val intent = Intent(
                        this@MainActivity,
                        Class.forName("com.example.favorite.FavoriteActivity")
                    )
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, getString(R.string.not_supported), Toast.LENGTH_SHORT).show()

                    Log.d("Navigation", "Activity not found")
                }
                // val uri = Uri.parse("example://favorite")
                // startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
            R.id.navigation_news -> {
                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_home -> {
                fragment = HomeFragment()
            }
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, fragment)
                .commit()
        }

        return true
    }
}