package com.example.androidprofessional.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.R
import com.example.androidprofessional.adapter.MainAdapter
import com.example.androidprofessional.databinding.ActivityMainBinding
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.presenter.MainPresenterImpl
import com.example.androidprofessional.view.Contract

class MainActivity : BaseActivity<AppState>() {

    private lateinit var binding: ActivityMainBinding

    private var adapter: MainAdapter? = null
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
            object : MainAdapter.OnListItemClickListener {
                override fun onItemClick(data: DataModel) {
                    Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
                }
            }

    override fun createPresenter(): Contract.Presenter<AppState, Contract.View> {
        return MainPresenterImpl()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                    SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    presenter.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                                LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                                MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter.let { it?.setData(dataModel) }
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
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
            presenter.getData("hi", true)
        }
    }

    private fun showViewSuccess() = with(binding) {
        successLinearLayout.visibility = VISIBLE
        loadingFrameLayout.visibility = GONE
        errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() = with(binding) {
        successLinearLayout.visibility = GONE
        loadingFrameLayout.visibility = VISIBLE
        errorLinearLayout.visibility = GONE
    }

    private fun showViewError() = with(binding) {
        successLinearLayout.visibility = GONE
        loadingFrameLayout.visibility = GONE
        errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
                "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}
