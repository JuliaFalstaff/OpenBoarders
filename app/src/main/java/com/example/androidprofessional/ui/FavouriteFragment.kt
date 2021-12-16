package com.example.androidprofessional.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidprofessional.databinding.FragmentFavourtesBinding
import com.example.historyscreen.HistoryAdapter
import com.example.androidprofessional.viewmodel.FavouriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment: com.example.core.BaseFragment<com.example.module.AppState>() {

    private var _binding: FragmentFavourtesBinding? = null
    private val binding get() = _binding!!
    private val adapter: com.example.historyscreen.HistoryAdapter? = null
    val viewModel: FavouriteViewModel by viewModel()
    override val model: com.example.core.BaseViewModel<com.example.module.AppState>
        get() = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavourtesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getFavouriteData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }


    private fun initView() {
        binding.favouriteRecyclerView.adapter = adapter
    }

    override fun renderData(appState: com.example.module.AppState) {
        when (appState) {
            is com.example.module.AppState.Success -> {
                val dataModel = appState.data
                binding.favouriteRecyclerView.adapter = dataModel?.let {
                    com.example.historyscreen.HistoryAdapter(
                        it
                    )
                }
                binding.favouriteRecyclerView.layoutManager = LinearLayoutManager(context)
                adapter.let {
                    if (dataModel != null) {
                        it?.setData(dataModel)
                    }
                }
            }
        }
    }
}