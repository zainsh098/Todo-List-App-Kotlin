package com.example.todolistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.RecyclerView.MyAdapter
import com.example.todolistapp.RecyclerView.NotesClickInterface
import com.example.todolistapp.RoomDatabase.Notes
import com.example.todolistapp.databinding.FragmentAllTaskBinding
import com.example.todolistapp.databinding.FragmentCompletedTaskBinding
import com.example.todolistapp.viewmodel.NoteViewModel

class CompletedTaskFragment : Fragment(), NotesClickInterface {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: FragmentCompletedTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCompletedTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_completed_task)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyAdapter(requireContext(), this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)
        viewModel.completedNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClicked(notes: Notes) {
        notes.statusTask = !notes.statusTask
        viewModel.updateNoteStatus(notes)
        Toast.makeText(context, if (notes.statusTask) "Completed" else "Incomplete", Toast.LENGTH_SHORT).show()
    }
}
