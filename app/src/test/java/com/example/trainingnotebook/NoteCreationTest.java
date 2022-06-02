package com.example.trainingnotebook;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import org.junit.Assert;
import org.junit.Test;

public class NoteCreationTest {

    @Test
    public void NoteTest(){
        Note note = new Note();
        note.login="testLogin";
        note.done=true;
        note.number=3;
        note.uid=12345;
        note.text="testText";
        Note secondNote = note;
        Assert.assertEquals(note, secondNote);
    }
}
