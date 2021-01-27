package com.mu.jan.mongodb_realm_android.data.database

import android.util.Log
import com.mu.jan.mongodb_realm_android.AppContext
import com.mu.jan.mongodb_realm_android.data.database.model.Task
import com.mu.jan.mongodb_realm_android.helper.Logger
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.where
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration

object MyDatabase {
    //MongoDb-Realm
    //realm instance
    private lateinit var realm : Realm

    fun initRealm(){
        val partitionValue = "Project 0" //REPLACE
        val user = AppContext.get().realmApp().currentUser()
        val config = SyncConfiguration.Builder(user,partitionValue)
                .build()
        realm = Realm.getInstance(config)
        Logger.log("init Realm")

    }
    //Anonymous Authenticate a user
    fun startAnonymousAuth(){
        val credentials = Credentials.anonymous()
        AppContext.get().realmApp().loginAsync(credentials){
            if(it.isSuccess){
                Logger.log("anonymous auth success")
                //init realm
                initRealm()
            }else {
                Logger.log("failed to anonymous auth ")
            }
        }
    }
    fun isUserSignedIn() : Boolean{
        return AppContext.get().realmApp().currentUser()!=null
    }
    //Reads and Writes
    fun insert(task : Task){
        if(this::realm.isInitialized){
            //insert task
            Logger.log("Inserting...")
            realm.executeTransactionAsync {
                it.insert(task)
                Logger.log("Insert success")
            }
        }
    }
    fun readAll() = if(this::realm.isInitialized){
        //realAll
        realm.where<Task>().findAll()
    }else {
        null
    }


}