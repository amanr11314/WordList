package com.amantech.wordlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amantech.wordlist.database.WordEntity
import com.amantech.wordlist.viewmodel.WordViewModel
import com.amantech.wordlist.viewmodel.WordViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.sql.Time


class MainActivity : AppCompatActivity() {

    private lateinit var mWordViewModel: WordViewModel
    private lateinit var mWordViewModelFactory: WordViewModelFactory
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: WordListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        //init View Model obejct
        //use of view model factory to pass parameter to viewmodel
        mWordViewModelFactory = WordViewModelFactory(application)
        mWordViewModel =
            ViewModelProvider(this, mWordViewModelFactory).get(WordViewModel::class.java)

        //Init UI
        recyclerView = findViewById(R.id.recyclerView)
        adapter = WordListAdapter(this, listOf<WordEntity>())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Add click listener to FAB
        //TODO: INSERT WORD INTO DB OPERATION ON FAB CLICK
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //observer for livedata for wordlist
        mWordViewModel.allWords.observe(this, {
            adapter.setWords(it!!)
        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}