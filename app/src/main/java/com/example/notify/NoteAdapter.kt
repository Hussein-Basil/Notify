package com.example.notify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notify.data.NoteData

class NoteAdapter(
    private var list: List<NoteData>,
    private val listener: NoteInteractionsListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.view_note, parent, false)

        return NoteViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = list[position]

        // Use the ViewHolder to efficiently access and set data to views
        holder.titleView.text = item.icon + " " + item.title
        holder.descriptionView.text = item.description
        holder.view.findViewById<ImageButton>(R.id.deleteButton).setOnClickListener {
            listener.deleteItemAt(position)
        }
        holder.bind(item)
    }

    fun setData(data: List<NoteData>) {
        this.list = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class NoteViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.titleView)
        val descriptionView: TextView = view.findViewById(R.id.descriptionView)

        fun bind(noteData: NoteData) {

            if (titleView.text != "") {
                titleView.requestFocus()
            }

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


