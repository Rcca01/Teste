package com.example.posterr.bottomSheetDialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.posterr.PosterViewModel
import com.example.posterr.models.Poster
import com.example.posterr.R


const val MAX_LENGTH = 777

class DialogNewPoster(addPoster: Boolean = true, position: Int?): DialogFragment() {

    private lateinit var tvTitle: TextView
    private lateinit var edtTextPost: EditText
    private lateinit var tvCountText: TextView
    private lateinit var btnPositive: AppCompatButton
    private lateinit var btnNegative: AppCompatButton
    private lateinit var btnComment: AppCompatButton
    private val addPoster = addPoster
    private val posterPosition = position
    private val viewModel: PosterViewModel by lazy {
        activity?.run {
            ViewModelProviders.of(this).get(PosterViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    companion object {

        const val TAG = "SimpleDialog"

        private const val KEY_TITLE = "KEY_TITLE"

        fun newInstance(title: String, isPoster: Boolean = true, position:Int?): DialogNewPoster {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            val fragment = DialogNewPoster(isPoster, position)
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_new_poster, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle = view.findViewById(R.id.tvTitle)
        tvCountText = view.findViewById(R.id.tvCountText)
        edtTextPost = view.findViewById(R.id.edtTextPost)
        btnPositive = view.findViewById(R.id.btnPositive)
        btnNegative = view.findViewById(R.id.btnNegative)
        btnComment = view.findViewById(R.id.btnComment)

        if(addPoster) {
            btnPositive.visibility = View.VISIBLE
            btnComment.visibility = View.INVISIBLE
        } else {
            btnPositive.visibility = View.INVISIBLE
            btnComment.visibility = View.VISIBLE
        }

        setupView()
        setupClickListeners()
        managerCountCaracters()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView() {
        tvTitle.text = arguments?.getString(KEY_TITLE)
    }

    private fun setupClickListeners() {
        btnPositive.isEnabled = false
        btnPositive.setOnClickListener {
            viewModel.addNewPoster(Poster(edtTextPost.text.toString(), mutableListOf()))
            dismiss()
        }
        btnComment.setOnClickListener {
            viewModel.addNewComment(posterPosition!!, edtTextPost.text.toString())
            dismiss()
        }
        btnNegative.setOnClickListener {
            dismiss()
        }
    }

    private fun managerCountCaracters() {
        edtTextPost.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val length = s?.length ?: 0
                val convert = "${length}/${MAX_LENGTH}"
                btnPositive.isEnabled = length in 1..MAX_LENGTH
                tvCountText.text = convert
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
}