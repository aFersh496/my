package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {


    val dbHelper = DBHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit)

        val buttonCancel = findViewById<Button>(R.id.buttonCancel)
        buttonCancel.setOnClickListener {
            //finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
        val editTextName = findViewById<EditText>(R.id.editname)
        val editTextLast = findViewById<EditText>(R.id.editlastname)
        val editTextBay = findViewById<EditText>(R.id.editbday)
        val editTextPhone = findViewById<EditText>(R.id.editphone)
        val button = findViewById<Button>(R.id.buttonSave)
        button.setOnClickListener {
            // так можно менять текст кнопки


            val firstname = editTextName.text.toString()
            val lastname = editTextLast.text.toString()
            val bday = editTextBay.text.toString()
            val phonenumber = editTextPhone.text.toString()
            dbHelper.add(lastname, firstname, bday, phonenumber)

            val returnIntent = Intent()
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}