package com.example.androidprofessional.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.FragmentFavourtesBinding
import com.example.androidprofessional.databinding.FragmentHistoryListBinding
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.ui.adapter.HistoryAdapter
import com.example.androidprofessional.viewmodel.BaseViewModel
import com.example.androidprofessional.viewmodel.FavouriteViewModel
import com.example.androidprofessional.viewmodel.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment: BaseFragment<AppState>() {

    private var _binding: FragmentFavourtesBinding? = null
    private val binding get() = _binding!!
    private val adapter: HistoryAdapter? = null
    val viewModel: FavouriteViewModel by viewModel()
    override val model: BaseViewModel<AppState>
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

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                binding.favouriteRecyclerView.adapter = dataModel?.let { HistoryAdapter(it) }
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