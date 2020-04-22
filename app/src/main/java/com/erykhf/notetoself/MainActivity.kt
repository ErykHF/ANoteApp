package com.erykhf.notetoself

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var mSerializer: JSONSerializer
    private var noteList = ArrayList<Note>()
    lateinit var noteAdapter: NoteAdapter
    private var showDividers: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val dialog = DialogNewNote()
            dialog.show(supportFragmentManager, "")
        }

        mSerializer = JSONSerializer("NoteToSelf.json", applicationContext)

        try {
            noteList = mSerializer.load()
        }catch (e: Exception){
            noteList = ArrayList()
            Log.e("Error loading notes:", "", e)
        }

        noteAdapter = NoteAdapter(this, this.noteList)

        recyclerView.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = noteAdapter

        }

    }

    private fun saveNotes(){
        try {
            mSerializer.save(this.noteList)
        }catch (e: Exception){
            Log.e("Error saving notes", "", e)
        }
    }

    override fun onResume() {
        super.onResume()

        val prefs = getSharedPreferences("Note to self", Context.MODE_PRIVATE)

        showDividers = prefs.getBoolean("dividers", true)


        if (showDividers){
            recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))

        }else{
            if (recyclerView.itemDecorationCount > 0){
                recyclerView.removeItemDecorationAt(0)
            }
        }
    }

    override fun onPause() {
        super.onPause()

        saveNotes()
    }


    fun createNewNote(n: Note) {
        noteList.add(n)
        noteAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showNote(noteToShow: Int) {
        val dialog = DialogShowNote()
        dialog.sendNoteSelected(noteList[noteToShow])
        dialog.show(
            supportFragmentManager, ""
        )
    }
}
