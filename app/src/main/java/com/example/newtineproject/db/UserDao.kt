package com.example.newtineproject.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.Date

@Dao
interface UserDao {
    @Insert
    fun insertUser(user:User)

    @Delete
    fun deleteUser(user:User)

}

@Dao
interface UserHabbitDao {
    @Insert
    fun insertUserHabbit(userHabbit: UserHabbit)

    @Query("UPDATE UserHabbit SET day= :day WHERE habbit_pk=:user_pk")
    fun UpdateHabbitDay(user_pk:Int,day:String):Unit

    @Query("UPDATE UserHabbit SET time= :time WHERE habbit_pk=:user_pk")
    fun UpdateHabbitTime(user_pk:Int,time:Date):Unit

    @Query("SELECT * FROM UserHabbit WHERE habbit_pk=:user_pk")
    fun getUserHabbits(user_pk: Int): UserHabbit
}
