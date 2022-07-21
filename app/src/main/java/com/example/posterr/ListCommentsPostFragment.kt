package com.example.posterr

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.posterr.models.Poster
import com.example.posterr.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class ListCommentsPostFragment : Fragment() {

    private lateinit var itemPost: Poster
    private lateinit var viewModel: PosterViewModel
    private lateinit var rvList: RecyclerView
    private lateinit var tvTextPostComment: TextView
    private lateinit var btnModalListComments: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_comments_post_list, container, false)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(PosterViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        tvTextPostComment = view.findViewById(R.id.tvTextPostComment)
        btnModalListComments = view.findViewById(R.id.btnModalListComments)
        btnModalListComments.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        rvList = view.findViewById(R.id.list)
        val positionPost = requireArguments().getInt("postPosition")
        itemPost = viewModel.getItemPost(positionPost)
        tvTextPostComment.text = itemPost.text
        rvList.layoutManager = LinearLayoutManager(context)
        rvList.adapter = CommentsPostViewAdapter(itemPost.list)
        return view
    }
}