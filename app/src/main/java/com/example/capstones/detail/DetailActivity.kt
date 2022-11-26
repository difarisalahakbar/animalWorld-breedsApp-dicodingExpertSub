package com.example.capstones.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.capstones.R
import com.example.capstones.databinding.ActivityDetailBinding
import com.example.core.data.Resource
import com.example.core.domain.model.Breeds
import com.example.core.domain.model.Images
import com.example.core.ui.SliderAdapter
import com.example.core.utils.EXTRA_DETAIL
import com.example.core.utils.EXTRA_DETAIL_IMAGE
import com.example.core.utils.EXTRA_DETAIL_IMAGES
import com.example.core.utils.EXTRA_DETAIL_POSITION
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var listImage: ArrayList<Images>
    private lateinit var sliderAdapter: SliderAdapter
    private var currentPosition = 0
    private var onceLoad = false

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == DetailImageActivity.RESULT_CODE && result.data != null) {
                val getPosition = result.data?.getIntExtra(EXTRA_DETAIL_POSITION, 0) as Int
                onceLoad = true
                binding.imgDetail.currentPagePosition = getPosition
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val breeds = intent.getParcelableExtra<Breeds>(EXTRA_DETAIL) as Breeds

        listImage = ArrayList()

        sliderAdapter = SliderAdapter { listImage ->
            val intent = Intent(this@DetailActivity, DetailImageActivity::class.java)
            intent.putExtra(EXTRA_DETAIL_IMAGE, listImage)
            intent.putExtra(EXTRA_DETAIL_IMAGES, currentPosition)
            resultLauncher.launch(intent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        setupDetail(breeds)
        setupSlider(breeds)
    }


    private fun setupSlider(breeds: Breeds) {
        detailViewModel.getAllImage(breeds.id).observe(this) { images ->
            if (images != null) {
                when (images) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.btnRetry.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnRetry.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE

                        listImage.clear()

                        if (!onceLoad) {

                            if (images.data != null) {
                                var image: Images
                                for (i in 0 until images.data!!.size) {
                                    image = images.data!![i]
                                    listImage.add(image)
                                }
                            }

                            sliderAdapter.setList(listImage)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnRetry.visibility = View.VISIBLE
                        binding.btnRetry.setOnClickListener {
                            setupSlider(breeds)
                        }
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = images.message
                    }

                }
            }
        }
    }

    private fun setupDetail(breeds: Breeds) {
        with(binding) {
            with(breeds) {
                tvName.text = name
                tvCountry.text = origin
                tvDescription.text = description
                tvTemperament.text = temperament

                var state = isFavorite
                setFavoriteIcon(state)

                imgFavorite.setOnClickListener {
                    state = !state
                    detailViewModel.setFavorite(this, state)
                    setFavoriteIcon(state)
                }
                sliderAdapter.setDetail(false)
                imgDetail.setSliderAdapter(sliderAdapter)
                binding.imgDetail.setIndicatorAnimation(IndicatorAnimationType.WORM)
                binding.imgDetail.setCurrentPageListener { position -> currentPosition = position }

            }
        }
    }

    private fun setFavoriteIcon(state: Boolean) {
        if (state) {
            binding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

}

