package com.master.fitnessjourney.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "progresses")
data class Progress(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: Date,
//    val status: Boolean,
    @ColumnInfo("email_user")
    val emailUser: String
)
