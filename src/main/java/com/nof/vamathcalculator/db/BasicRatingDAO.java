package com.nof.vamathcalculator.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BasicRatingDAO {
    @Query("SELECT * FROM basic_rating")
    List<Basic_Rating> getAll();

    @Query("SELECT * FROM basic_rating WHERE depend_status LIKE :depend_status")
    Basic_Rating findAllCompsForDepStatus(String depend_status);

    @Query("SELECT :rating FROM basic_rating WHERE depend_status LIKE :depend_status")
    Double findCompensation(String depend_status, String rating);

    @Insert
    void insertAll(Basic_Rating... users);

    @Delete
    void delete(Basic_Rating user);
}
