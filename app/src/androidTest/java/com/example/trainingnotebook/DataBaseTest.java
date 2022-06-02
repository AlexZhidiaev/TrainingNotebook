package com.example.trainingnotebook;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DataBaseTest{
    protected volatile AppDataBase dataBase;
    private NoteDao noteDao;

    private static final int NUMBER_OF_THREADS=4;
    protected static final ExecutorService databaseWriteExecutor=
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public NoteDao getNoteDao(){
        return dataBase.noteDao();
    }

    @Before
    public void setUp(){
        dataBase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDataBase.class
        ).build();
    }
    @After
    public void close(){
        dataBase.close();
    }




}
