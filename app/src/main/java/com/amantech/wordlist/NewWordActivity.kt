package com.amantech.wordlist

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class NewWordActivity : AppCompatActivity() {
    companion object {
        val EXTRA_REPLY = "com.amantech.wordlist.REPLY"
    }

    lateinit var mEditWordView: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        mEditWordView = findViewById(R.id.edit_word)

        val button: Button = findViewById(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(mEditWordView.text)) {
                setResult(RESULT_CANCELED, replyIntent)
            }
            else {
                val word = mEditWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}