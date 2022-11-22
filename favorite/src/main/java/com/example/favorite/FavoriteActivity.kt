package com.example.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.capstones.detail.DetailActivity
import com.example.capstones.di.FavoriteModuleDependencies
import com.example.core.ui.BreedsAdapter
import com.example.core.utils.EXTRA_DETAIL
import com.example.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity()   {

    private lateinit var binding: ActivityFavoriteBinding
    @Inject
    lateinit var factory: FavoriteViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }
    private lateinit var breedsAdapter: BreedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupListFavorite()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return true
    }

    private fun setupListFavorite() {
        breedsAdapter = BreedsAdapter {
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra(EXTRA_DETAIL, it)
            startActivity(intent)
        }

        with(binding.rvListFav){
            adapter = breedsAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        favoriteViewModel.getAllFavoriteBreeds().observe(this) {
            breedsAdapter.setList(it)
            binding.viewEmpty.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }


}