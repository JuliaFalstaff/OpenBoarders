package com.example.androidprofessional.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidprofessional.databinding.FragmentFavourtesBinding
import com.example.androidprofessional.navigation.AndroidScreens
import com.example.androidprofessional.ui.DetailedInfoFragment
import com.example.androidprofessional.ui.adapters.FavouriteAdapter
import com.example.core.BaseFragment
import com.example.module.AppState
import com.example.module.data.DataModel
import com.github.terrakok.cicerone.Router
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
    private val router: Router by inject()
    private val screens: AndroidScreens by inject()

    private val onListItemClickListener: IOnListItemClickListener = object :
        IOnListItemClickListener {
        override fun onItemClick(data: DataModel) {
            router.navigateTo(screens.detailedFragment(Bundle().apply {
                putParcelable(
                    DetailedInfoFragment.WORD_INFO, data)
            }))
        }

        override fun onItemDelete(data: DataModel) {
            viewModel.deleteFavouriteWord(data)
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
                binding.favouriteRecyclerView.visibility = View.VISIBLE
                val dataModel = appState.data
                if (dataModel.isNullOrEmpty()) showEmptyDataPicture()
                binding.favouriteRecyclerView.adapter = dataModel?.let { list ->
                    FavouriteAdapter(
                            list.sortedWith(compareBy { it.text }), onListItemClickListener
                    )
                }

                binding.favouriteRecyclerView.layoutManager = LinearLayoutManager(context)
                adapter.let {
                    if (dataModel != null) {
                        it?.setFavoriteData(dataModel)
                    }
                }
            }
        }
    }

    private fun showEmptyDataPicture() {
        binding.favouriteRecyclerView.visibility = View.INVISIBLE
        binding.noFavDataImageView.visibility = View.VISIBLE
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
        fun newInstance(): FavouriteFragment = FavouriteFragment()
    }
}