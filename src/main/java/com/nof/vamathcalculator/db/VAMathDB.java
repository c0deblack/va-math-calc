package com.nof.vamathcalculator.db;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Singleton class that provides a shared instance to the database
 */
public class VAMathDB {
    private static VAMathRoomDB m_db = null;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private VAMathDB() {}

    public static VAMathDB getDB(Context app_context) {
        VAMathDB db = new VAMathDB();
        if (m_db == null) {
            synchronized (VAMathRoomDB.class) {
                // m_db = Room.databaseBuilder(
                m_db = Room.inMemoryDatabaseBuilder(
                        app_context,
                        VAMathRoomDB.class
                        //        VAMathRoomDB.class,
                        //        "VA-Math-DB"
                ).build();
            }
        }
        return db;
    }

    public VAMathRoomDB db() { return m_db; }
}
