package com.example.fakenews.DB.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fakenews.DB.models.Article;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface ArticleDao {
    @Insert(onConflict = REPLACE)
    void insert(Article article);

    @Delete
    void delete(Article article);

    @Update
    void update(Article article);

    @Query("DELETE FROM articlesTable")
    void deleteAllArticles();

    @Query("SELECT * FROM articlesTable")
    LiveData<List<Article>> getAllArticles();
}
