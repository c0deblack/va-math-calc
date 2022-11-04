package com.nof.vamathcalculator.db;

import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_10;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_100;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_20;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_30;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_40;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_50;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_60;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_70;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_80;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.RATING_90;
import static com.nof.vamathcalculator.db.VAColumns.DEPEND_STATUS_COL_NAME;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {
        @Index(value = DEPEND_STATUS_COL_NAME, unique = true)
})
public class Basic_Rating {

    @PrimaryKey(autoGenerate = true)
    public int _id;

    @ColumnInfo(name = DEPEND_STATUS_COL_NAME)
    public String depend_status;

    @ColumnInfo(name = RATING_10)
    public Double rating_10;

    @ColumnInfo(name = RATING_20)
    public Double rating_20;

    @ColumnInfo(name = RATING_30)
    public Double rating_30;

    @ColumnInfo(name = RATING_40)
    public Double rating_40;

    @ColumnInfo(name = RATING_50)
    public Double rating_50;

    @ColumnInfo(name = RATING_60)
    public Double rating_60;

    @ColumnInfo(name = RATING_70)
    public Double rating_70;

    @ColumnInfo(name = RATING_80)
    public Double rating_80;

    @ColumnInfo(name = RATING_90)
    public Double rating_90;

    @ColumnInfo(name = RATING_100)
    public Double rating_100;
}
