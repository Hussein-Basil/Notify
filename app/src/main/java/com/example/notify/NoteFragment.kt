package com.example.notify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.notify.data.DataManager
import com.example.notify.data.NoteData
import com.google.android.material.textfield.TextInputEditText


class NoteFragment : Fragment() {
    override fun onStart() {
        super.onStart()

        val title = arguments?.getString("title")
        val description = arguments?.getString("description")

        val titleInput = view?.findViewById<TextInputEditText>(R.id.titleView)
        val descriptionInput = view?.findViewById<TextInputEditText>(R.id.descriptionView)

        if (title == null) {
            // focus when opening new note
            titleInput?.requestFocus()
        } else {
            // display saved note
            titleInput?.setText(title)
            descriptionInput?.setText(description)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)?.title = ""


        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.edit_note_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_save -> {
                        saveNote()
                        view.findNavController()
                            .navigate(R.id.action_NoteFragment_to_MainFragment)
                        return true
                    }
                }
                return false
            }
        }, viewLifecycleOwner)
    }

    private fun saveNote() {
        val isOld = arguments?.getString("title").toBoolean()

        val titleTextEdit = view?.findViewById<TextInputEditText>(R.id.titleView)
        val descriptionTextEdit = view?.findViewById<TextInputEditText>(R.id.descriptionView)

        val title = titleTextEdit?.text.toString()
        val description = descriptionTextEdit?.text.toString()

        if (isOld) {
            // updateNote()
            // NOT IMPLEMENTED YET
        } else {
            val newNote = NoteData(title, description)
            DataManager.addNote(newNote)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).findViewById<Toolbar>(R.id.toolbar)?.title = ""
    }
}