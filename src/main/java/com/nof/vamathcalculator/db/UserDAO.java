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

    @Query("Select * FROM user LIMIT 1")
    User getUserRecord();

    @Query("UPDATE user SET " +
            VAColumns.UserColumns.BASIC_RATING + " = NULL, " +
            VAColumns.UserColumns.SMC_RATING + " = NULL, " +
            VAColumns.UserColumns.HAS_SMC + " = 0, " +
            VAColumns.UserColumns.HAS_SPOUSE + " = 0, " +
            VAColumns.UserColumns.NUM_CHILD_DEFECT + " = NULL, " +
            VAColumns.UserColumns.NUM_CHILD_EDUCATION + " = NULL, " +
            VAColumns.UserColumns.NUM_CHILDREN + " = NULL, " +
            VAColumns.UserColumns.NUM_PARENTS + " = NULL, " +
            VAColumns.UserColumns.REQUIRES_AID + " = 0")
    void setToDefault();

    @Query("DELETE FROM user")
    void deleteAll();

    @Update
    void updateUser(User... users);

    @Insert
    void insertAll(User... ratings);

    @Delete
    void delete(User user);
}
