package com.example.trainingnotebook;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {

    private AppDataBase dataBase;
    private NoteDao noteDao;
    private String login;
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        dataBase = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class,"app-db-name")
                .allowMainThreadQueries()
                .build();
        noteDao = dataBase.noteDao();
    }
    public AppDataBase getDataBase(){return dataBase;}


    public void setLogin(String login){this.login = login;
    }

    public String getLogin(){
        return this.login;
    }

    public void setDataBase(AppDataBase dataBase){this.dataBase = dataBase;}

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

}