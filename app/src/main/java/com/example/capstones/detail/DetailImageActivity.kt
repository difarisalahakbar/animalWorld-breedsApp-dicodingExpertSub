package com.example.capstones.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import com.example.capstones.databinding.ActivityDetailImageBinding
import com.example.core.domain.model.Images
import com.example.core.ui.SliderAdapter
import com.example.core.utils.EXTRA_DETAIL_IMAGE
import com.example.core.utils.EXTRA_DETAIL_IMAGES
import com.example.core.utils.EXTRA_DETAIL_POSITION
 import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType

class DetailImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailImageBinding
    private lateinit var listImages: ArrayList<Images>
    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        listImages = intent.getParcelableArrayListExtra<Images>(EXTRA_DETAIL_IMAGE) as ArrayList<Images>

        sliderAdapter = SliderAdapter { }

        binding.imgDetail.setSliderAdapter(sliderAdapter)
        binding.imgDetail.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderAdapter.setDetail(true)
        sliderAdapter.setList(listImages)

        var getPosition = intent.getIntExtra(EXTRA_DETAIL_IMAGES, 0)
        binding.imgDetail.currentPagePosition = getPosition

        val resultIntent = Intent()

        resultIntent.putExtra(EXTRA_DETAIL_POSITION, getPosition)

        binding.imgDetail.setCurrentPageListener{ position ->
            getPosition = position
            resultIntent.putExtra(EXTRA_DETAIL_POSITION, getPosition)
        }

        setResult(RESULT_CODE, resultIntent)

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    companion object{
        const val RESULT_CODE = 101
    }
}