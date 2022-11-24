package ddb;


import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TestDatabase {
    @Test
    public void TestDDBWrite() {
        Database ddb = new Database("https://cscb07finalproject-default-rtdb.firebaseio.com/", "a");
        ddb.write("a");
        assertEquals("a", ddb.read());
    }
}
