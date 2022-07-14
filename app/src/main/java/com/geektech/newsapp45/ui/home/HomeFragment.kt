package com.geektech.newsapp45.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp45.R
import com.geektech.newsapp45.databinding.FragmentHomeBinding
import com.geektech.newsapp45.models.News

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter()
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.newsFragment)
        }
        parentFragmentManager.setFragmentResultListener("rk_news",viewLifecycleOwner){ requestKey, bundle ->
            val news = bundle.getSerializable("news") as News
            adapter.addItem(news)
            Log.e("Home", "text: ${news.title }")
            Log.e("Home", "news: $news")

        }
        
        binding.recyclerView.adapter = adapter

    }

}