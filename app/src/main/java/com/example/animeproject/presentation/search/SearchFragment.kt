package com.example.animeproject.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeproject.databinding.FragmentSearchBinding
import com.example.animeproject.domain.response.MultResponse
import com.example.animeproject.presentation.search.dialog.FilterDialogFragment
import com.example.animeproject.presentation.search.filter.Filter
import com.example.animeproject.presentation.search.repository.SearchRepository
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : MvpAppCompatFragment(), SearchView {
    lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchRepository: SearchRepository

    private val presenter: SearchPresenter by moxyPresenter {
        SearchPresenter(
            searchRepository,
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Filter.changeGenre(0)
        binding.svNameAnime.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (Filter.all){
                    presenter.getMultByName(query!!, binding)
                    binding.pbLoading.isVisible = true
                }else{
                    presenter.getMultByGenre(query!!, binding)
                    binding.pbLoading.isVisible = true
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (Filter.all){
                    presenter.getMultByName(newText!!, binding)
                    binding.pbLoading.isVisible = true
                }else{
                    presenter.getMultByGenre(newText!!, binding)
                    binding.pbLoading.isVisible = true
                }
                return true
            }
        })
        binding.btnFilter.setOnClickListener {
            val filterDialogFragment = FilterDialogFragment { _ ->
                presenter.getMultByGenre("a", binding)
            }
            filterDialogFragment.show(parentFragmentManager.beginTransaction(), "Dialog")
        }

    }

    override fun getMultByName(multResponse: MultResponse, bind: FragmentSearchBinding) {
        binding = bind
        binding.pbLoading.isVisible = multResponse.data.isEmpty()
        val adapter = SearchAdapter(multResponse.data)
        binding.rvAnime.layoutManager =
            LinearLayoutManager(
                activity?.applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        binding.rvAnime.adapter = adapter
    }

    override fun getMultByGenre(multResponse: MultResponse, bind: FragmentSearchBinding) {
        binding = bind
        binding.pbLoading.isVisible = multResponse.data.isEmpty()
        val adapter = SearchAdapter(multResponse.data)
        binding.rvAnime.layoutManager =
            LinearLayoutManager(
                activity?.applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        binding.rvAnime.adapter = adapter
    }

}