package com.example.trainingnotebook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Note>> noteLiveData =
            App.getInstance().getNoteDao().getAllLiveData(App.getInstance().getLogin());

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }
}