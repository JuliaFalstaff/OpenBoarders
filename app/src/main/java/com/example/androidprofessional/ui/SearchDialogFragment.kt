package com.example.androidprofessional.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidprofessional.databinding.SearchDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchDialogFragment : BottomSheetDialogFragment() {
    private var _binding: SearchDialogFragmentBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnSearchClickListener? = null
    private val textWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = with(binding) {
            if (searchEditText.text != null && searchEditText.text.toString().isNotEmpty()) {
                searchButton.isEnabled = true
                clearTextImageView.visibility = View.VISIBLE
            } else {
                searchButton.isEnabled = false
                clearTextImageView.visibility = View.GONE
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {}
    }

    private val onSearchButtonClickListener = View.OnClickListener {
        onSearchClickListener?.onClick(binding.searchEditText.text.toString())
        dismiss()
    }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = SearchDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener(onSearchButtonClickListener)
        binding.searchEditText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    private fun addOnClearClickListener() = with(binding) {
        clearTextImageView.setOnClickListener {
            searchEditText.setText("")
            searchButton.isEnabled = false
        }
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }

    companion object {
        fun newInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }
}