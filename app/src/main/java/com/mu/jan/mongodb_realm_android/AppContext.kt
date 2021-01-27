package com.mu.jan.mongodb_realm_android

import android.app.Application
import com.mu.jan.mongodb_realm_android.data.database.MyDatabase
import com.mu.jan.mongodb_realm_android.utils.Const
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration

class AppContext : Application(){

    //OurRealmApp (io.realm.mongodb.App)
    private lateinit var app : App

    companion object{
        private lateinit var instance : AppContext
        fun get() = instance
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        //init MongoDb-Realm
        Realm.init(this)
        //connect to our realm app
        app = App(AppConfiguration.Builder(Const.REALM_APP_ID)
                .build())
        //anonymous auth
        if(!MyDatabase.isUserSignedIn()){
            //start auth
            MyDatabase.startAnonymousAuth()
        }else {
            //init realm database
            MyDatabase.initRealm()
        }
    }
    fun realmApp() = app
}