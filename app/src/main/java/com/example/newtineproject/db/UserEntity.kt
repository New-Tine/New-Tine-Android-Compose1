package com.example.newtineproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName="User")
data class User(

    //user personal info
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="user_pk") val user_pk:Int,

    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name="id") val id:String,
    @ColumnInfo(name="email") val email:String,

    )


@Entity(tableName="UserHabbit", foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["user_pk"], childColumns = ["user_pk"], onDelete = ForeignKey.CASCADE)]
)
data class UserHabbit(

    //user habbit
    //@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "habbit_pk") val habbit_pk: Int,
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name="habbit_pk") val user_pk: Int,
    //@ColumnInfo(name = "user_pk") val user_pk: Int,

    @ColumnInfo(name="day") val day:String,
    @ColumnInfo(name="time") val time:Date,//String으로 할지 고민,,
    @ColumnInfo(name="selected_goal") val selected_goal:Int,

)
