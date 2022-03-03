package com.example.androidprofessional.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.FragmentDetailedInfoBinding
import com.example.androidprofessional.utils.ExoPlayerFactory
import com.example.module.data.DataModel
import com.example.utils.OnlineLiveData
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util
import com.squareup.picasso.Picasso

class DetailedInfoFragment : Fragment() {

    private var _binding: FragmentDetailedInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var wordBundle: DataModel
    private var player: ExoPlayer? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailedInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordBundle = arguments?.getParcelable(WORD_INFO) ?: DataModel()
        startLoadingOrShowError()
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

    private fun startLoadingOrShowError() {
        OnlineLiveData(requireContext()).observe(viewLifecycleOwner, Observer {
            if (it) {
                setData()
            } else {
                Toast.makeText(context, getString(R.string.error_no_internet), Toast.LENGTH_SHORT)
                        .show()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setData() = with(binding) {
        wordTextView.text = wordBundle.text
        descriptionWordTextView.text =
                wordBundle.meanings?.joinToString { it.translation?.translation.toString() }
        transcriptionTextView.text = "[${wordBundle.meanings?.firstOrNull()?.transcription}]"

        val imageLink = wordBundle.meanings?.firstOrNull()?.imageUrl
        usePicassoToLoadPhoto(wordPictureImageView, imageLink)

        val urlSound = wordBundle.meanings?.firstOrNull()?.soundUrl.toString()
        playSoundButton.setOnClickListener {
            useExoPlayerToLoadSoundUrl(urlSound)
        }
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory().create(requireContext())
    }

    private fun useExoPlayerToLoadSoundUrl(url: String) {
        val mediaItem = MediaItem.fromUri(Uri.parse("https://$url"))
        player?.run {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    private fun usePicassoToLoadPhoto(imageView: ImageView, imageLink: String?) {
        Picasso.get()
                .load("https:$imageLink")
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.ic_load_error_vector)
                .into(imageView)
    }


    override fun onDestroy() {
        super.onDestroy()
        player = null
        _binding = null
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