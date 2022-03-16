package com.example.favouritescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.core.BaseFragment
import com.example.favouritescreen.databinding.FragmentFavourtesBinding
import com.example.module.AppState
import com.example.module.data.DataModel
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

    private val onListItemClickListener: IOnListItemClickListener = object : IOnListItemClickListener {
        override fun onItemClick(data: DataModel) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                        .replace(
                                R.id.container,
                                DetailedInfoFragment.newInstance(Bundle().apply {
                                    putParcelable(DetailedInfoFragment.WORD_INFO, data)
                                })
                        )
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
            }
        }
    }



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
                binding.favouriteRecyclerView.adapter = dataModel?.let { list ->
                    FavouriteAdapter(
                            list.sortedWith(compareBy { it.text }), onListItemClickListener
                    )
                }
                binding.favouriteRecyclerView.layoutManager = LinearLayoutManager(context)
                adapter.let {
                    if (dataModel != null) {
                        it?.setData(dataModel)
                    }
                }
                addRecyclerDecorator()
            }
        }
    }

    private fun addRecyclerDecorator() {
        val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.recycler_separator, null))
        binding.favouriteRecyclerView.addItemDecoration(itemDecoration)
    }

    override fun onStop() {
        scope.close()
        super.onStop()
    }

    companion object {
        const val WORD_INFO = "WORD"
        fun newInstance(bundle: Bundle): DetailedInfoFragment {
            val fragment = DetailedInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}