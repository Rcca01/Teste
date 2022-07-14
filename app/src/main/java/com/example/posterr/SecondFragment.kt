package com.example.posterr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posterr.adapter.UsersAdapter
import com.example.posterr.databinding.FragmentSecondBinding
import com.example.posterr.models.User

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var rvUser: RecyclerView
    //private lateinit var viewModel: PosterViewModel
    private lateinit var usersAdapter: UsersAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        rvUser = binding.rvUser
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        usersAdapter = UsersAdapter(createUsers())
        rvUser.adapter = usersAdapter
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvUser.layoutManager = layoutManager
    }

    private fun createUsers(): MutableList<User>{
        val listUsers = mutableListOf<User>()
        listUsers.add(0, User(1, "User 1"))
        listUsers.add(1, User(2, "User 2"))
        listUsers.add(2, User(3, "User 3"))
        listUsers.add(3, User(4, "User 4"))
        return listUsers
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}