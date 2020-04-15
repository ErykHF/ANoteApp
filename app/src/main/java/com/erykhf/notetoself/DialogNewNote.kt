package com.erykhf.notetoself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_new_note.*

class DialogNewNote :DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater

        val dialogView = inflater.inflate(R.layout.dialog_new_note, null)

        val editTitle = dialogView.findViewById(R.id.editTitle) as EditText

        val editDescription = dialogView.findViewById<EditText>(R.id.editDescription)

        val checkBoxIdea = dialogView.findViewById<CheckBox>(R.id.checkBoxIdea)

        val checkBoxTodo = dialogView.findViewById<CheckBox>(R.id.checkBoxTodo)

        val checkBoxImportant = dialogView.findViewById<CheckBox>(R.id.checkBoxImportant)

        val btnCancel = dialogView.findViewById(R.id.buttonCancel) as Button

        val btnOK = dialogView.findViewById(R.id.buttonOK) as Button


        builder.setView(dialogView).setMessage("Add a new note")

        btnCancel.setOnClickListener {
            dismiss()
        }

        btnOK.setOnClickListener {

            //            Create  a new note
            val newNote = Note()

            newNote.title = editTitle.text.toString()

            newNote.description = editDescription.text.toString()

            newNote.idea = checkBoxIdea.isChecked
            newNote.todo = checkBoxTodo.isChecked
            newNote.important = checkBoxImportant.isChecked

            //            Get a reference to MainActivity
            val callingActivity = activity as MainActivity

            //            Pass newNote back to MainActivity
            callingActivity.createNewNote(newNote)

            dismiss()
        }


        return builder.create()
    }
}