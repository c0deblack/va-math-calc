package com.nof.vamathcalculator.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Basic_Rating.class, SMC_Rating.class}, version = 1)
public abstract class VAMathRoomDB extends RoomDatabase {
    public abstract BasicRatingDAO basic_ratingDAO();
    public abstract SMCRatingDAO   smc_ratingDAO();
}

