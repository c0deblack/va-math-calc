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
public interface DisabilityDAO {

    @Query("SELECT * FROM disability")
    public LiveData<List<Disability>> getAll();

    @Query("SELECT * FROM disability")
    public List<Disability> getDisabilityList();

    @Query("SELECT * FROM disability WHERE _id = :id")
    public Disability getDisabilityFromID(int id);

    @Query("Select * FROM disability WHERE SHORT_NAME = :short_name LIMIT 1")
    public LiveData<Disability> getDisabilityFromShortName(String short_name);

    @Query("DELETE FROM disability")
    public void deleteAll();

    @Update
    public void updateDisability(Disability... disabilities);

    @Insert
    public void insertAll(Disability... disabilities);

    @Delete
    public void delete(Disability disability);
}
