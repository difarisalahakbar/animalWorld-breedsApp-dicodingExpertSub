package com.example.capstones.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.capstones.databinding.FragmentHomeBinding
import com.example.capstones.detail.DetailActivity
import com.example.capstones.search.SearchActivity
import com.example.core.data.Resource
import com.example.core.domain.model.Category
import com.example.core.ui.BreedsAdapter
import com.example.core.ui.CategoryAdapter
import com.example.core.utils.EXTRA_DETAIL
import com.example.core.utils.Helper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var breedsAdapter: BreedsAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnClickListener {
            startActivity(Intent(requireActivity(), SearchActivity::class.java))
        }

        setupCategory()
        setupListBreeds()

    }

    private fun setupListBreeds() {
        breedsAdapter = BreedsAdapter {
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra(EXTRA_DETAIL, it)
            startActivity(intent)
        }

        binding.shimmerRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.shimmerRecyclerView.adapter = breedsAdapter

        homeViewModel.getAllBreeds().observe(viewLifecycleOwner) { breeds ->
            if (breeds != null) {
                when (breeds) {
                    is Resource.Loading -> {
                        binding.btnRetry.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                        binding.shimmerRecyclerView.showShimmerAdapter()
                    }

                    is Resource.Success -> {
                        binding.shimmerRecyclerView.hideShimmerAdapter()
                        binding.btnRetry.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                        breedsAdapter.setList(breeds.data)
                    }

                    is Resource.Error -> {
                        binding.shimmerRecyclerView.hideShimmerAdapter()
                        binding.btnRetry.visibility = View.VISIBLE
                        binding.btnRetry.setOnClickListener {
                            setupListBreeds()
                        }
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = breeds.message
                    }
                }
            }
        }
    }

    private fun setupCategory() {
        categoryAdapter = CategoryAdapter()

        with(binding.rvCategory) {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(requireActivity(), 4)
            setHasFixedSize(true)
        }

        val list = ArrayList<Category>()

        Helper.addDummyCategory(list)

        categoryAdapter.setList(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}