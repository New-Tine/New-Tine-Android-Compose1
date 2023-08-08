package com.example.newtineproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class],[UserHabbit::class],version=1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun userDao():UserDao
    abstract fun userHabbitDao():UserHabbitDao

    companion object{
        private var appDataBase:AppDataBase?=null

        @Synchronized
        fun getInstance(context: Context):AppDataBase?{
            if(appDataBase==null){
                synchronized(AppDataBase::class.java){
                    appDataBase= Room.databaseBuilder(
                        context.applicationContext,AppDataBase::class.java,
                        "App-DataBase"
                    ).allowMainThreadQueries().build()
                }
            }

            return appDataBase
        }

    }



}