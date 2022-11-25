package com.example.capstones.search

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.capstones.databinding.ActivitySearchBinding
import com.example.capstones.detail.DetailActivity
import com.example.core.ui.BreedsAdapter
import com.example.core.utils.EXTRA_DETAIL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding
    private lateinit var breedsAdapter: BreedsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupSearch()

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    private fun setupSearch() {
        breedsAdapter = BreedsAdapter {
            val intent = Intent(this@SearchActivity, DetailActivity::class.java)
            intent.putExtra(EXTRA_DETAIL, it)
            startActivity(intent)
        }

        with(binding.rvList) {
            adapter = breedsAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        binding.textInput.apply {
            editText?.let { editText ->
                setEndIconOnClickListener {
                    editText.setText("")
                }

                editText.setOnKeyListener { _, keyId, _ ->
                    if (keyId == KeyEvent.KEYCODE_ENTER) {

                        showDataSearch(editText.text.toString().trim())

                        return@setOnKeyListener true
                    }
                    return@setOnKeyListener false

                }

                editText.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                        showDataSearch(editText.text.toString().trim())

                        return@setOnEditorActionListener true
                    }
                    return@setOnEditorActionListener false
                }

            }
            requestFocus()
        }
    }

    private fun showDataSearch(textSearch: String) {
        binding.viewHow.root.visibility = View.GONE
        searchViewModel.getSearchName(textSearch)
            .observe(this@SearchActivity) {
                breedsAdapter.setList(it)
                binding.viewEmptys.root.visibility =
                    if (it.isNotEmpty()) View.GONE else View.VISIBLE
            }
    }
}

