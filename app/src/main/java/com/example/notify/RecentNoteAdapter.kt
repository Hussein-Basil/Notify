package com.example.notify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notify.data.NoteData

class RecentNoteAdapter(private val list: List<NoteData>) :
    RecyclerView.Adapter<RecentNoteAdapter.RecentNoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentNoteViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.view_pinned_note, parent, false)
        return RecentNoteViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RecentNoteViewHolder, position: Int) {
        val item = list[position]

        // Use the ViewHolder to efficiently access and set data to views
        holder.titleView.text = item.title
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RecentNoteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.titleView)

        fun bind(noteData: NoteData) {
            titleView.text = noteData.title

            view.setOnClickListener { v ->
                val bundle = Bundle()
                bundle.putString("title", noteData.title)
                bundle.putString("description", noteData.description)

                Navigation.findNavController(v)
                    .navigate(R.id.action_MainFragment_to_NoteFragment, bundle)
            }
        }
    }
}