package com.example.favouritescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.BaseFragment
import com.example.favouritescreen.databinding.FragmentFavourtesBinding
import com.example.module.AppState
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class FavouriteFragment : BaseFragment<AppState>(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentFavourtesBinding? = null
    private val binding get() = _binding!!
    private val adapter: FavouriteAdapter? = null
    val viewModel: FavouriteViewModel by inject()
    override val model: com.example.core.BaseViewModel<AppState>
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
                binding.favouriteRecyclerView.adapter = dataModel?.let {
                    FavouriteAdapter(
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

    override fun onStop() {
        scope.close()
        super.onStop()
    }
}