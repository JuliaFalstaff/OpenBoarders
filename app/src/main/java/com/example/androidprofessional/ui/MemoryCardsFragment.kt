package com.example.androidprofessional.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.FragmentMemoryCardBinding
import com.example.androidprofessional.utils.ExoPlayerFactory
import com.example.androidprofessional.viewmodel.MemoryCardsViewModel
import com.example.core.BaseFragment
import com.example.core.BaseViewModel
import com.example.module.AppState
import com.example.module.data.DataModel
import com.github.terrakok.cicerone.Router
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

class MemoryCardsFragment : BaseFragment<AppState>(), KoinScopeComponent {
    override val scope: Scope by lazy { createScope(this) }
    val viewModel: MemoryCardsViewModel by inject()
    override val model: BaseViewModel<AppState>
        get() = viewModel
    private var _binding: FragmentMemoryCardBinding? = null
    private val binding get() = _binding!!
    private var player: ExoPlayer? = null
    private val router by inject<Router>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMemoryCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        initButtons()
    }

    private fun initViewModel() {
        viewModel.getFavouriteData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getCardsLiveData()
    }

    private fun initButtons() {
        binding.nextCardButton.setOnClickListener {
            initViewModel()
            binding.cardDescriptionWordTextView.visibility = View.INVISIBLE
        }
        binding.showTranslationButton.setOnClickListener {
            binding.cardDescriptionWordTextView.visibility = View.VISIBLE
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessCard -> {
                val dataModel = appState.data
                if (dataModel == null) {
                    Toast.makeText(requireContext(), R.string.error_null_data, Toast.LENGTH_SHORT).show()

                } else {
                    setData(dataModel)
                }
            }
            is AppState.Loading -> {
                Toast.makeText(requireContext(), R.string.loading, Toast.LENGTH_SHORT).show()
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), appState.error.message.toString(), Toast.LENGTH_SHORT).show()
                showErrorPicture()
            }
        }
    }


    private fun showErrorPicture() = with(binding){
        cardHeader.text = getString(R.string.error_no_favourites_words)
        cardPlaySoundButton.visibility = View.INVISIBLE
        cardTranscriptionTextView.visibility = View.INVISIBLE
        showTranslationButton.visibility = View.INVISIBLE
        nextCardButton.visibility = View.INVISIBLE
        cardWordPictureImageView.setImageResource(R.drawable.no_favs_data)

    }

    @SuppressLint("SetTextI18n")
    private fun setData(dataModel: DataModel) = with(binding) {
        cardWordTextView.text = dataModel.text
        cardDescriptionWordTextView.text =
                dataModel.meanings?.joinToString { it.translation?.translation.toString() }
        cardTranscriptionTextView.text = "[${dataModel.meanings?.firstOrNull()?.transcription}]"

        val imageLink = dataModel.meanings?.firstOrNull()?.imageUrl
        usePicassoToLoadPhoto(cardWordPictureImageView, imageLink)

        val urlSound = dataModel.meanings?.firstOrNull()?.soundUrl.toString()
        cardPlaySoundButton.setOnClickListener {
            useExoPlayerToLoadSoundUrl(urlSound)
        }
    }

    private fun usePicassoToLoadPhoto(imageView: ImageView, imageLink: String?) {
        Picasso.get()
                .load("https:$imageLink")
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.no_favs_data)
                .into(imageView)
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory().create(requireContext())
    }

    private fun useExoPlayerToLoadSoundUrl(url: String) {
        val mediaItem = MediaItem.fromUri(Uri.parse("$url"))
        player?.run {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }

    override fun onDestroy() {
        super.onDestroy()
        player = null
        _binding = null
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }

    companion object {
        fun newInstance(): MemoryCardsFragment = MemoryCardsFragment()
    }
}