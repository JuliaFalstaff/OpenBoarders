package com.example.androidprofessional.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.FragmentMainBinding
import com.example.androidprofessional.ui.adapter.MainAdapter
import com.example.androidprofessional.viewmodel.MainViewModel
import com.example.core.BaseFragment
import com.example.module.AppState
import com.example.module.data.DataModel
import com.example.utils.fragmentViewById
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MainFragment : BaseFragment<AppState>(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    val viewModel: MainViewModel by inject()
    override val model: com.example.core.BaseViewModel<AppState>
        get() = viewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var adapter: MainAdapter? = null
    private val horizontalProgressBar by fragmentViewById<ProgressBar>(R.id.progressBarHorizontal)

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

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
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
                    addRecyclerDecorator()
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    horizontalProgressBar.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                } else {
                    horizontalProgressBar.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun addRecyclerDecorator() {
        val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.recycler_separator, null))
        binding.mainActivityRecyclerview.addItemDecoration(itemDecoration)
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

    override fun onStop() {
        scope.close()
        super.onStop()
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
        private const val TAG_SEARCH = "Search"
    }
}