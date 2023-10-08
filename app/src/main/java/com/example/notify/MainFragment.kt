package com.example.notify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notify.data.DataManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainFragment : Fragment(), NoteInteractionsListener {

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteAdapter = NoteAdapter(DataManager.notes, this)

        val notesRecyclerView = view.findViewById<RecyclerView>(R.id.notesRV)
        notesRecyclerView.adapter = noteAdapter

        view.findViewById<RecyclerView>(R.id.recentNotesRV).adapter =
            RecentNoteAdapter(DataManager.notes)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_MainFragment_to_NoteFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)?.title = ""

        handleEmpty()
    }

    override fun deleteItemAt(index: Int) {
        DataManager.deleteNoteAt(index)
        noteAdapter.setData(DataManager.notes)

        handleEmpty()
    }

    private fun handleEmpty() {
        if (DataManager.notes.isEmpty()) {
            view?.findViewById<ImageView>(R.id.error)?.visibility = android.view.View.VISIBLE
            view?.findViewById<ScrollView>(R.id.body)?.visibility = android.view.View.GONE
        }
    }
}