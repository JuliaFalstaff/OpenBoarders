package com.example.androidprofessional.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.FragmentHistoryListBinding
import com.example.androidprofessional.navigation.AndroidScreens
import com.example.androidprofessional.ui.DetailedInfoFragment
import com.example.androidprofessional.ui.adapters.HistoryAdapter
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
    private lateinit var adapter: HistoryAdapter
    private val viewModel: HistoryViewModel by inject()
    override val model: BaseViewModel<AppState>
        get() = viewModel
    private val router: Router by inject()
    private val screens: AndroidScreens by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getHistoryData().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun initView() {
        adapter = HistoryAdapter()
        binding.historyRecyclerView.adapter = adapter
        setAdapterClickListener()
    }

    private fun setAdapterClickListener() {
        adapter.onItemClick = { data ->
            router.navigateTo(screens.detailedFragment(Bundle().apply {
                putParcelable(
                    DetailedInfoFragment.WORD_INFO, data
                )
            }))
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.historyRecyclerView.visibility = View.VISIBLE
                binding.progressBarHistory.visibility = View.INVISIBLE
                val dataModel = appState.data
                if (dataModel.isNullOrEmpty()) showEmptyDataPicture()
                if (dataModel != null) {
                    adapter.submitList(dataModel.sortedWith(compareBy { it.text }))
                }
            }
            is AppState.Loading -> {
                binding.historyRecyclerView.visibility = View.INVISIBLE
                binding.progressBarHistory.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                showErrorToast()
            }
            else -> {
                showErrorToast()
            }
        }
    }

    private fun showErrorToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.undefined_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showEmptyDataPicture() {
        binding.historyRecyclerView.visibility = View.INVISIBLE
        binding.noHistoryDataImageView.visibility = View.VISIBLE
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