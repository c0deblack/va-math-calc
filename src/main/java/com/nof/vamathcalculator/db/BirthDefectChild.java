/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.nof.vamathcalculator.db.VAColumns.ChildWithBirthDefectColumns.*;

@Entity
public class BirthDefectChild {
    @PrimaryKey(autoGenerate = true)
    public int _id;

    @ColumnInfo(name = SHORT_NAME)
    public String short_name;

    @ColumnInfo(name = COMPENSATION)
    public Double compensation;

    @ColumnInfo(name = LEVEL)
    public Integer level;

    @ColumnInfo(name = IS_SPINAFIDA)
    public boolean is_spinafida;
}
