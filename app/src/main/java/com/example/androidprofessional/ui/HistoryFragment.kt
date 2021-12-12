package com.example.androidprofessional.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.FragmentHistoryListBinding
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.ui.adapter.HistoryAdapter
import com.example.androidprofessional.ui.adapter.MainAdapter
import com.example.androidprofessional.viewmodel.BaseViewModel
import com.example.androidprofessional.viewmodel.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<AppState>() {

    private var _binding: FragmentHistoryListBinding? = null
    private val binding get() = _binding!!
    private val adapter: HistoryAdapter ? = null
    val viewModel: HistoryViewModel by viewModel()
    override val model: BaseViewModel<AppState>
        get() = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHistoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getHistoryData().observe(viewLifecycleOwner, { renderData(it)})
        viewModel.getAllHistory()
    }


    private fun initView() {
        binding.historyRecyclerView.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel.isNullOrEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.historyRecyclerView.layoutManager =
                            LinearLayoutManager(context)
                        binding.historyRecyclerView.adapter =
                            HistoryAdapter()
                    } else {
                        adapter.let { it?.setData(dataModel) }
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarRound.visibility = View.GONE
                } else {
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) = with(binding) {
        showViewError()
        errorTextView.text = error ?: getString(R.string.undefined_error)
        reloadButton.setOnClickListener {
            viewModel.getHistoryData()
                .observe(viewLifecycleOwner, Observer { renderData(it) })
        }
    }

    private fun showViewSuccess() = with(binding) {
        successFrameLayout.visibility = View.VISIBLE
        loadingFrameLayout.visibility = View.GONE
        errorLinearLayout.visibility = View.GONE
    }

    private fun showViewLoading() = with(binding) {
        successFrameLayout.visibility = View.GONE
        loadingFrameLayout.visibility = View.VISIBLE
        errorLinearLayout.visibility = View.GONE
    }

    private fun showViewError() = with(binding) {
        successFrameLayout.visibility = View.GONE
        loadingFrameLayout.visibility = View.GONE
        errorLinearLayout.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance(): HistoryFragment = HistoryFragment()
    }
}