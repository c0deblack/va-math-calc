/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.db;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Singleton class that provides a shared instance to the database
 */
public class VAMathDB {
    private static VAMathRoomDB INSTANCE = null;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public VAMathDB() {}

    public static VAMathRoomDB getDB(Context app_context) {
        if (INSTANCE == null) {
            databaseWriteExecutor.execute(() -> {
                synchronized (VAMathRoomDB.class) {
                     INSTANCE = Room.databaseBuilder(
                    //INSTANCE = Room.inMemoryDatabaseBuilder(
                            app_context,
                     //     VAMathRoomDB.class
                            VAMathRoomDB.class,
                            "VA-Math-DB"
                    ).build();
                }
            });
        }
        return INSTANCE;
    }
}
