package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val dbHelper = DBHelper(this)
    lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // заполняем список
        val list = mutableListOf<Todo>()
        list.addAll(dbHelper.getAll())


        // создаём инстанс адаптера, отдаём ему список
        adapter = RecyclerAdapter(list) {
            Log.d("123", it.toString())
            dbHelper.remove(list[it].id)
            list.removeAt(it)
            adapter.notifyItemRemoved(it)
        }



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // у нас будет линейный список
        recyclerView.layoutManager = LinearLayoutManager(this)
        // прикручиваем адаптер к RecyclerView
        recyclerView.adapter = adapter

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // так можно менять текст кнопки
            val text = editText.text.toString()

            val id = dbHelper.add(text)
            val todo = Todo(id, text)
            list.add(todo)
            adapter.notifyItemInserted(list.lastIndex)

        }


    }
}