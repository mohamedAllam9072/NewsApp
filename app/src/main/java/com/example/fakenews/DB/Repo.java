package com.example.fakenews.DB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.fakenews.DB.models.Article;
import com.example.fakenews.DB.models.Source2;
import com.example.fakenews.DB.room.ArticleDao;
import com.example.fakenews.DB.room.Source2Dao;
import com.example.fakenews.DB.room.mDatabase;

import java.util.List;


public class Repo {
    private Source2Dao source2Dao;
    private ArticleDao articleDao;
    private LiveData<List<Source2>> allSources;
    private LiveData<List<Article>> allArticles;

    /**
     * =Application is subclass of Context
     * =create instance of RoomDatabase here
     * =connect with noteDao abstract method in NoteDatabase.class
     * normally we cannot call abstract methods because it didn't have body
     * but since we build our database instance builder auto_generate needed code as a body for abstract method
     */

    public Repo(Application application) {
        mDatabase mDatabase = com.example.fakenews.DB.room.mDatabase.getInstance(application);
        source2Dao = mDatabase.source2Dao();
        articleDao = mDatabase.articleDao();
        allSources = source2Dao.getAllSources();
        allArticles = articleDao.getAllArticles();

    }

    /**
     * then create methods for our Dao every method
     * = room execute all database operations in background thread so you don't have take care with this
     * but for anther database operations we have execute code by our self in background because ROOM doesn't
     * allow database operations in Main Thread since it Freeze our app
     * if you do this without using async task your app will crash.
     */
    public void insert(Source2 source2) {
        new InsertAsyncTask(source2Dao).execute(source2);
    }

    public void insertArticle(Article article) {
        new InsertArticlesAsyncTask(articleDao).execute(article);
    }

    public void update(Source2 source2) {
        new UpdateAsyncTask(source2Dao).execute(source2);
    }

    public void delete(Source2 source2) {
        new DeleteAsyncTask(source2Dao).execute(source2);
    }

    public void deleteAllSources() {
        new DeleteAllSourcesAsyncTask(source2Dao).execute();
    }


    public LiveData<List<Source2>> getAllSources() {
        return allSources;
    }

    public LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }


    /**
     * =make NoteDao Member we need it to make our Database operations
     * =because of our inner class is static we can use NoteDao from class Repository
     */
    private static class InsertAsyncTask extends AsyncTask<Source2, Void, Void> {

        private Source2Dao source2Dao;

        private InsertAsyncTask(Source2Dao source2Dao) {
            this.source2Dao = source2Dao;
        }

        @Override
        protected Void doInBackground(Source2... source2s) {
            source2Dao.insert(source2s[0]);
            return null;
        }
    }

    private static class InsertArticlesAsyncTask extends AsyncTask<Article, Void, Void> {

        private ArticleDao articleDao;

        private InsertArticlesAsyncTask(ArticleDao articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            articleDao.insert(articles[0]);
            return null;
        }
    }


    private static class UpdateAsyncTask extends AsyncTask<Source2, Void, Void> {

        private Source2Dao source2Dao;

        private UpdateAsyncTask(Source2Dao source2Dao) {
            this.source2Dao = source2Dao;
        }

        @Override
        protected Void doInBackground(Source2... source2s) {
            source2Dao.update(source2s[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Source2, Void, Void> {

        private Source2Dao source2Dao;

        private DeleteAsyncTask(Source2Dao source2Dao) {
            this.source2Dao = source2Dao;
        }

        @Override
        protected Void doInBackground(Source2... source2s) {
            source2Dao.delete(source2s[0]);
            return null;
        }
    }

    private static class DeleteAllSourcesAsyncTask extends AsyncTask<Void, Void, Void> {

        private Source2Dao source2Dao;

        private DeleteAllSourcesAsyncTask(Source2Dao source2Dao) {
            this.source2Dao = source2Dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            source2Dao.deleteAllSources();
            return null;
        }
    }


}
