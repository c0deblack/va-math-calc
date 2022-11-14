/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.nof.vamathcalculator.db.VAColumns.DisabilityColumns.*;

@Entity
public class Disability {
    @PrimaryKey(autoGenerate = true)
    public int _id;

    @ColumnInfo(name = BASIC_RATING)
    public Double rating;

    @ColumnInfo(name = IS_BILATERAL)
    public Boolean is_bilateral;

    @ColumnInfo(name = SHORT_NAME)
    public String short_name;

    @ColumnInfo(name = SMC_RATING)
    public String smc_rating;

    @ColumnInfo(name = IS_BASIC)
    public Boolean is_basic;
}
