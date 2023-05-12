package com.example.prakc192

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<User>
    private lateinit var nameText: EditText
    private lateinit var ageText: EditText
    private lateinit var users: ArrayList<User>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.name)
        ageText = findViewById(R.id.age)
        listView = findViewById(R.id.list_item)
        users = ArrayList()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        listView.adapter = adapter
    }

    fun addUser(view: View) {
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
        val user = User(name, age)
        users.add(user)
        adapter.notifyDataSetChanged()
    }

    fun save(view: View) {
        val result = JSONHelper.exportToJSON(this, users)
        if (result) {
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show()
        }
    }

    fun open(view: View) {
        users = JSONHelper.importFromJSON(this) as ArrayList<User>
        if (users != null) {
            adapter.clear()
            adapter.addAll(users)
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Не удалось открыть данные", Toast.LENGTH_LONG).show()
        }
    }

}
