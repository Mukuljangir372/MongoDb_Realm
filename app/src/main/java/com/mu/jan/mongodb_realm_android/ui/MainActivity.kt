package com.mu.jan.mongodb_realm_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mu.jan.mongodb_realm_android.R
import com.mu.jan.mongodb_realm_android.data.database.MyDatabase
import com.mu.jan.mongodb_realm_android.data.database.model.Task
import com.mu.jan.mongodb_realm_android.databinding.ActivityMainBinding
import com.mu.jan.mongodb_realm_android.helper.Logger
import io.realm.RealmResults

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.insertBtn onClick { insert() }
        binding.readBtn onClick { readAll() }
    }
    private infix fun View.onClick(onClicked:(View)->Unit){
        setOnClickListener {
            onClicked(it)
        }
    }

    //operations
    private fun insert(){
        MyDatabase.insert(Task("New Task","Project 0"))
    }
    private fun readAll(){
        val results : RealmResults<Task>? = MyDatabase.readAll()
//        Logger.log("${results?.get(0)?.name}")
//        Logger.log("${results?.get(1)?.name}")
//        Logger.log("${results?.get(2)?.name}")
//        Logger.log("${results?.get(3)?.name}")
        Logger.log("${results?.size}")
    }

}