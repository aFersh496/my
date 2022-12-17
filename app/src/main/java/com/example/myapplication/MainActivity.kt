package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object {
        const val START_CREATE_CODE = 1
        const val EXTRA_ID = "id"
    }

    private val dbHelper = DBHelper(this)
    private val list = mutableListOf<Person>()

    lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list.addAll(dbHelper.getAll())


        // создаём инстанс адаптера, отдаём ему список
        adapter = RecyclerAdapter() {
            /*Log.d("123", it.toString())
            dbHelper.remove(list[it].id)
            list.removeAt(it)
            adapter.notifyItemRemoved(it)*/
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra(EXTRA_ID, list[it].id)
            startActivity(intent)

        }
        adapter.updateList(list)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // у нас будет линейный список
        recyclerView.layoutManager = LinearLayoutManager(this)
        // прикручиваем адаптер к RecyclerView
        recyclerView.adapter = adapter

        val editText: EditText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button_create)
        button.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivityForResult(intent, START_CREATE_CODE)
        }

        editText.addTextChangedListener { filter ->
            val filterStr = filter.toString()
            if (filterStr.isBlank()) {
                adapter.updateList(list)
            } else {
                val filteredList = list.filter {
                    it.firstname.contains(filterStr, true) || it.lastname.contains(filterStr, true)
                }
                adapter.updateList(filteredList)
            }
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == START_CREATE_CODE && resultCode == Activity.RESULT_OK) {
            list.clear()
            list.addAll(dbHelper.getAll())
            adapter.notifyDataSetChanged()
        }

    }


}