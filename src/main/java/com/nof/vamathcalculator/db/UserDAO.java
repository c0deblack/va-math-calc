/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("Select * FROM user")
    LiveData<List<User>> getAll();

    @Query("Select * FROM user LIMIT 1")
    LiveData<User> getUserData();

    @Update
    void updateUser(User... users);

    @Insert
    void insertAll(User... ratings);

    @Delete
    void delete(User user);
}
