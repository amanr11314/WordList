package com.amantech.wordlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amantech.wordlist.database.WordEntity

class WordListAdapter(context: Context) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mWords: List<WordEntity>? = null

    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wordItemView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
//        val itemView: View =            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        val itemView: View = mInflater.inflate(R.layout.recyclerview_item, parent, false)
//            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (mWords != null) {
            val current: WordEntity = mWords!![position]
            holder.wordItemView.text = current.word
        } else {
            holder.wordItemView.setText(R.string.Noword)
        }
    }

    fun setWords(words: List<WordEntity>) {
        mWords = words
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return if (mWords != null)
            mWords!!.size;
        else 1;
    }
}