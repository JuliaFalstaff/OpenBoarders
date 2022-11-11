package com.example.androidprofessional.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.FragmentHistoryListBinding
import com.example.androidprofessional.navigation.AndroidScreens
import com.example.androidprofessional.ui.DetailedInfoFragment
import com.example.core.BaseFragment
import com.example.core.BaseViewModel
import com.example.module.AppState
import com.example.module.data.DataModel
import com.github.terrakok.cicerone.Router
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class HistoryFragment : BaseFragment<AppState>(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentHistoryListBinding? = null
    private val binding get() = _binding!!
    private val adapter: HistoryAdapter? = null
    val viewModel: HistoryViewModel by inject()
    override val model: BaseViewModel<AppState>
        get() = viewModel
    private val router: Router by inject()
    private val screens: AndroidScreens by inject()

    private val onListItemClickListener: IOnListItemClickListener =
        object : IOnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                router.navigateTo(screens.detailedFragment(Bundle().apply {
                    putParcelable(
                        DetailedInfoFragment.WORD_INFO, data
                    )
                }))
            }
        }

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
        viewModel.getHistoryData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }

    private fun initView() {
        binding.historyRecyclerView.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewSuccess()
                val dataModel = appState.data
                if (dataModel.isNullOrEmpty()) showEmptyDataPicture()
                binding.historyRecyclerView.adapter =
                    dataModel?.let { HistoryAdapter(it, onListItemClickListener) }
                binding.historyRecyclerView.layoutManager = LinearLayoutManager(context)
                adapter.let { adapter ->
                    if (dataModel != null) {
                        adapter?.setData(dataModel.sortedWith(compareBy { it.text }))
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

    private fun showEmptyDataPicture() {
        binding.historyRecyclerView.visibility = View.INVISIBLE
        binding.noHistoryDataImageView.visibility = View.VISIBLE
    }

    private fun showErrorScreen(error: String?) = with(binding) {
        showViewError()
        errorTextView.text = error ?: getString(R.string.undefined_error)
        reloadButton.setOnClickListener {
            viewModel.getHistoryData().observe(viewLifecycleOwner, { renderData(it) })
            viewModel.getData()
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

    override fun onStop() {
        scope.close()
        super.onStop()
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance(): HistoryFragment = HistoryFragment()
    }
}