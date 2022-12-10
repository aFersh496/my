package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class InfoActivity : AppCompatActivity() {

    val dbHelper = DBHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)

        val firstname = findViewById<EditText>(R.id.infofirstname)
        val lastname = findViewById<EditText>(R.id.infolastname)
        val bday = findViewById<EditText>(R.id.infobday)
        val phone = findViewById<EditText>(R.id.infophone)
        val id = intent.getLongExtra(MainActivity.EXTRA_ID, 0)

        val person = dbHelper.getById(id)
        firstname.setText(person?.firstname)
        lastname.setText(person?.lastname)
        bday.setText(person?.bday)
        phone.setText(person?.phonenumber)


        val buttonlast = findViewById<Button>(R.id.buttonlast)
        buttonlast.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonedit = findViewById<Button>(R.id.buttoneditinfo)
        buttonedit.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

        val buttondelete = findViewById<Button>(R.id.buttondelete)
        buttondelete.setOnClickListener{
            val person_id = intent.getLongExtra(MainActivity.EXTRA_ID, -1L)
            if (person_id == -1L) {
                return@setOnClickListener
            }
            dbHelper.remove(person_id)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
