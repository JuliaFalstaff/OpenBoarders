package com.example.androidprofessional.ui

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.androidprofessional.R
import com.example.androidprofessional.databinding.FragmentDetailedInfoBinding
import com.example.androidprofessional.model.data.DataModel
import com.squareup.picasso.Picasso

class DetailedInfoFragment : Fragment() {

    private var _binding: FragmentDetailedInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var wordBundle: DataModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordBundle = arguments?.getParcelable(WORD_INFO) ?: DataModel()
        setData()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() = with(binding) {
        wordTextView.text = wordBundle.text
        descriptionWordTextView.text = wordBundle.meanings?.joinToString { it.translation?.translation.toString() }
        transcriptionTextView.text = "[${wordBundle.meanings?.firstOrNull()?.transcription}]"
        mnemonicTextView.text = wordBundle.meanings?.firstOrNull()?.mnemonics.toString()
        val imageLink = wordBundle.meanings?.firstOrNull()?.imageUrl
        usePicassoToLoadPhoto(wordPictureImageView, imageLink)
     }

    private fun usePicassoToLoadPhoto(imageView: ImageView, imageLink: String?) {
        Picasso.get()
            .load("https:$imageLink")
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_load_error_vector)
            .into(imageView)
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