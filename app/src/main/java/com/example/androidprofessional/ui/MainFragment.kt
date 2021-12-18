package com.example.androidprofessional.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.FragmentMainBinding
import com.example.module.AppState
import com.example.module.data.DataModel
import com.example.androidprofessional.ui.adapter.MainAdapter
import com.example.utils.isOnline
import com.example.androidprofessional.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : com.example.core.BaseFragment<com.example.module.AppState>() {

    val viewModel: MainViewModel by viewModel()
    override val model: com.example.core.BaseViewModel<com.example.module.AppState>
        get() = viewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var adapter: MainAdapter? = null

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                activity?.supportFragmentManager?.apply {
                    beginTransaction().replace(
                        R.id.container,
                        DetailedInfoFragment.newInstance(Bundle().apply {
                            putParcelable(DetailedInfoFragment.WORD_INFO, data)
                        })
                    )
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                }
            }

            override fun addToFav(data: DataModel) {
                viewModel.saveToFav(data)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.startTextViewBeforeSearch.visibility = View.VISIBLE
        binding.searchFab.setOnClickListener { openDialogFragmentsAndSearch() }
    }

    private fun openDialogFragmentsAndSearch() {
        val searchDialogFragment = SearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(object :
            SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = com.example.utils.isOnline(context)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                        .observe(viewLifecycleOwner, Observer { renderData(it) })
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.error_no_internet),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        searchDialogFragment.show(parentFragmentManager.beginTransaction(), TAG_SEARCH)
    }

    override fun renderData(appState: com.example.module.AppState) {
        when (appState) {
            is com.example.module.AppState.Success -> {
                val dataModel = appState.data
                if (dataModel.isNullOrEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(context)
                        binding.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter.let { it?.setData(dataModel) }
                    }
                }
            }
            is com.example.module.AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is com.example.module.AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) = with(binding) {
        showViewError()
        errorTextView.text = error ?: getString(R.string.undefined_error)
        reloadButton.setOnClickListener {
            model.getData(getString(R.string.empty), isNetworkAvailable)
                .observe(viewLifecycleOwner, Observer { renderData(it) })
        }
    }

    private fun showViewSuccess() = with(binding) {
        successLinearLayout.visibility = View.VISIBLE
        loadingFrameLayout.visibility = View.GONE
        errorLinearLayout.visibility = View.GONE
        startTextViewBeforeSearch.visibility = View.GONE
    }

    private fun showViewLoading() = with(binding) {
        successLinearLayout.visibility = View.GONE
        loadingFrameLayout.visibility = View.VISIBLE
        errorLinearLayout.visibility = View.GONE
        startTextViewBeforeSearch.visibility = View.GONE
    }

    private fun showViewError() = with(binding) {
        successLinearLayout.visibility = View.GONE
        loadingFrameLayout.visibility = View.GONE
        errorLinearLayout.visibility = View.VISIBLE
        startTextViewBeforeSearch.visibility = View.GONE
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
        private const val TAG_SEARCH = "Search"
    }
}