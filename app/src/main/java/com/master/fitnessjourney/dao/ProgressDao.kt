package com.master.fitnessjourney.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.master.fitnessjourney.entities.Progress

@Dao
public interface ProgressDao {

    @Insert
    fun insertProgress(model: Progress)

}