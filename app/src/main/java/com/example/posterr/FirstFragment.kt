package com.example.posterr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posterr.adapter.PosterAdapter
import com.example.posterr.bottomSheetDialog.DialogNewPoster
import com.example.posterr.databinding.FragmentFirstBinding
import com.example.posterr.models.Poster

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var rvPoster: RecyclerView
    private lateinit var viewModel: PosterViewModel
    private lateinit var posterAdapter: PosterAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        rvPoster = binding.rvPoster
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
                ViewModelProviders.of(this).get(PosterViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        startObservableInsertNewPoster()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        posterAdapter = PosterAdapter(
            viewModel.getListPosters(),
            this::addComment,
            this::rePost
        )
        rvPoster.adapter = posterAdapter
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvPoster.layoutManager = layoutManager
    }

    private fun startObservableInsertNewPoster(){
        viewModel.listPosters().observe(viewLifecycleOwner, Observer {
            posterAdapter.notifyItemInserted(0)
            rvPoster.scrollToPosition(0)
        })
        viewModel.reloadItemList().observe(viewLifecycleOwner, Observer {
            posterAdapter.notifyItemChanged(it)
        })
    }

    private fun addComment(posterPosition:Int) {
        val dialog = DialogNewPoster.newInstance("New Comment", false, posterPosition)
        dialog.show(requireActivity().supportFragmentManager, "FirstFragment")
    }

    private fun rePost(textMessage: String, posterPosition:Int){
        viewModel.rePost(posterPosition)
        viewModel.addNewPoster(Poster(textMessage, mutableListOf()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listPosters().removeObservers(viewLifecycleOwner)
        _binding = null
    }
}