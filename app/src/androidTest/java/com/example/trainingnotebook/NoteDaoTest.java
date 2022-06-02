package com.example.trainingnotebook;

import androidx.lifecycle.LiveData;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Future;

public class NoteDaoTest extends DataBaseTest {

    @Test
    public void writeAndRead() throws Exception{
        Note noteTest = new Note();
        noteTest.text="test";
        noteTest.login="TestLogin";
        noteTest.uid=1;
        int testingReturnValue = 1;

        Future<Integer> note = DataBaseTest.databaseWriteExecutor
                .submit(new Runnable() {
                    @Override
                    public void run() {
                        getNoteDao().insert(noteTest);
                    }
                },testingReturnValue);

        int returningValue = note.get();
        Note returnNote = getNoteDao().findById(1);
        Assert.assertEquals(noteTest.text,returnNote.text);
        Assert.assertEquals(testingReturnValue,returningValue);
    }
}
