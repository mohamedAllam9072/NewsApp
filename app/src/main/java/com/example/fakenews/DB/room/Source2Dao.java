package com.example.fakenews.DB.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fakenews.DB.models.Source2;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface Source2Dao {
    @Insert(onConflict = REPLACE)
    void insert(Source2 source2);
    @Delete
    void delete(Source2 source2);

    @Update
    void update(Source2 source2);

    @Query("DELETE FROM source2_table")
    void deleteAllSources();

    @Query("SELECT * FROM source2_table")
    LiveData<List<Source2>> getAllSources();
}
