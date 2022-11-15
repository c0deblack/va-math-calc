/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
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

    @Insert
    void insertAll(Basic_Rating... ratings);

    @Delete
    void delete(Basic_Rating user);
}
