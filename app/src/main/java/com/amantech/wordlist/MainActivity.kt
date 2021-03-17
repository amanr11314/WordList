package com.amantech.wordlist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amantech.wordlist.database.WordEntity
import com.amantech.wordlist.viewmodel.WordViewModel
import com.amantech.wordlist.viewmodel.WordViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    private lateinit var mWordViewModel: WordViewModel
    private lateinit var mWordViewModelFactory: WordViewModelFactory
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: WordListAdapter
    lateinit var fabDelete: FloatingActionButton

    val uiscope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        //Init UI
        recyclerView = findViewById(R.id.recyclerView)
        adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        fabDelete = findViewById(R.id.fabDelete)

        //Add click listener to FAB
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }

        fabDelete.setOnClickListener {
            mWordViewModel.deleteAll()
        }


        //init View Model obejct
        //use of view model factory to pass parameter to view model
        mWordViewModelFactory = WordViewModelFactory(application)
        mWordViewModel =
            ViewModelProvider(this, mWordViewModelFactory).get(WordViewModel::class.java)

        //observer for LiveData for wordlist
        mWordViewModel.allWords.observe(this, {
            adapter.setWords(it!!)
            //launch suspend fun in a coroutine scope
            uiscope.launch {
                updateFABDelete(it.isNotEmpty())
            }
        })

    }

    //to enable or disable delete all button
    private suspend fun updateFABDelete(value: Boolean) {
        //update ui on main thread
        withContext(Dispatchers.Main) {
            fabDelete.isEnabled = value
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val word = WordEntity(data!!.getStringExtra(NewWordActivity.EXTRA_REPLY)!!)
            mWordViewModel.insert(word)
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
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