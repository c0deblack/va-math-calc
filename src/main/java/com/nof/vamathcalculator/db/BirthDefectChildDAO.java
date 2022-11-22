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
public interface BirthDefectChildDAO {
    @Query("Select * FROM birthdefectchild")
    public LiveData<List<BirthDefectChild>> getAll();

    @Query("Select * FROM birthdefectchild")
    public List<BirthDefectChild> getAllBirthDefects();

    @Query("Select * FROM birthdefectchild WHERE _id = :id")
    public BirthDefectChild getBirthDefectFromID(int id);

    @Query("Select * FROM birthdefectchild WHERE birthdefectchild.SHORT_NAME = :short_name LIMIT 1")
    public LiveData<BirthDefectChild> getDefectFromShortName(String short_name);

    @Query("DELETE FROM birthdefectchild")
    public void deleteAll();

    @Update
    public void updateBirthDefect(BirthDefectChild... children);

    @Insert
    public void insertAll(BirthDefectChild... children);

    @Delete
    public void delete(BirthDefectChild child);
}
