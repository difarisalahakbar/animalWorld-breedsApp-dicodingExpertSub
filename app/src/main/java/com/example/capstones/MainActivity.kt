package com.example.capstones

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.capstones.databinding.ActivityMainBinding
import com.example.capstones.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, HomeFragment())
                .commit()
        }
        binding.bottomNavigation.setNavigationChangeListener { _, position ->
            var fragment: Fragment? = null
            when (position) {
                0 ->  fragment = HomeFragment()
                1 -> {
                    val favoriteFragment = try {
                        Class.forName("com.example.favorite.FavoriteFragment")
                            .newInstance() as Fragment
                    } catch (e: Exception) {
                        Toast.makeText(this, getString(R.string.not_supported), Toast.LENGTH_SHORT)
                            .show()
                        null
                    }
                    fragment = favoriteFragment
                    // val uri = Uri.parse("example://favorite")
                    // startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
                2 -> Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()

            }
            if (fragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, fragment)
                    .commit()
            }


        }
    }
}