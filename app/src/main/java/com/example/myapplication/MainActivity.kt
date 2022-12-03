package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val dbHelper = DBHelper(this)
    lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // заполняем список
        val list = mutableListOf<Person>()
        list.addAll(dbHelper.getAll())


        // создаём инстанс адаптера, отдаём ему список
        adapter = RecyclerAdapter(list) {
            /*Log.d("123", it.toString())
            dbHelper.remove(list[it].id)
            list.removeAt(it)
            adapter.notifyItemRemoved(it)*/
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // у нас будет линейный список
        recyclerView.layoutManager = LinearLayoutManager(this)
        // прикручиваем адаптер к RecyclerView
        recyclerView.adapter = adapter

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button_main)
        button.setOnClickListener {
            // так можно менять текст кнопки
           //val text = editText.text.toString()

/*
            val id = dbHelper.add(firstname)
            val todo = Todo(id,firstname,lastname,bday, phonenumber)
            list.add(todo)
            finish()
            adapter.notifyItemInserted(list.lastIndex)
*/
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
            /*
            val intent1 = Intent(this, InfoActivity::class.java)
            startActivity(intent)*/


        }


    }
}