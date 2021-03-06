package com.erykhf.notetoself

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.listitem.view.*

class NoteAdapter(private val mainActivity: MainActivity, private val noteList: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.ListItemHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ListItemHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)

        return ListItemHolder(itemView)

    }

    override fun getItemCount(): Int {

        if (noteList != null){
            return noteList.size
        }

        return -1
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val note = noteList[position]
        holder.title.text  = note.title

        holder.description.text = note.description

        when {
            note.idea -> holder.status.text = mainActivity.resources.getString(R.string.ideas_text)

            note.important -> holder.status.text = mainActivity.resources.getString(R.string.important_text)

            note.todo -> holder.status.text = mainActivity.resources.getString(R.string.todo_text)
        }
    }

    inner class ListItemHolder (itemsView: View) : RecyclerView.ViewHolder(itemsView), View.OnClickListener{

        internal var title = itemsView.textViewTitle

        internal var description = itemsView.textViewDescription

        internal var status = itemsView.textViewStatus


        init {

            itemsView.isClickable = true
            itemsView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            mainActivity.showNote(adapterPosition)
        }

    }

}