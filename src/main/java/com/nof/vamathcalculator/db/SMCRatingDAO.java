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
public interface SMCRatingDAO {
    @Query("SELECT * FROM smc_rating")
    List<SMC_Rating> getAll();

    @Query("SELECT * FROM smc_rating WHERE depend_status LIKE :depend_status")
    SMC_Rating findAllCompsForDepStatus(String depend_status);

    @Query("SELECT :rating FROM smc_rating WHERE depend_status LIKE :depend_status")
    Double findCompensation(String depend_status, String rating);

    @Insert
    void insertAll(SMC_Rating... ratings);

    @Delete
    void delete(SMC_Rating rating);
}
