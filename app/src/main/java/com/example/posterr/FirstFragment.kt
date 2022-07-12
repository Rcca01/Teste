package com.example.posterr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posterr.adapter.PosterAdapter
import com.example.posterr.databinding.FragmentFirstBinding
import com.example.posterr.models.Poster

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var posters = mutableListOf<Poster>()
    private var adapter = PosterAdapter(posters)
    private lateinit var rvPoster: RecyclerView

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
        initRecyclerView()
        binding.buttonFirst.setOnClickListener {
            val poster = Poster("testing recycleview")
            posters.add(poster)
            adapter.notifyItemInserted(posters.lastIndex)
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun initRecyclerView() {
        rvPoster.adapter = adapter
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvPoster.layoutManager = layoutManager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}