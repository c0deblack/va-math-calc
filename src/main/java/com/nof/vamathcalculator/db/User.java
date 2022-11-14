/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.db;

import static com.nof.vamathcalculator.db.VAColumns.UserColumns.*;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int _id;

    @ColumnInfo(name = HAS_SMC)
    public boolean has_smc;

    @ColumnInfo(name = SMC_RATING)
    public String smc_rating;

    @ColumnInfo(name = BASIC_RATING)
    public Double basic_rating;

    @ColumnInfo(name = HAS_SPOUSE)
    public boolean has_spouse;

    @ColumnInfo(name = NUM_PARENTS)
    public Integer num_parents;

    @ColumnInfo(name = NUM_CHILDREN)
    public Integer num_Child;

    @ColumnInfo(name = NUM_CHILD_DEFECT)
    public Integer num_child_birth_defect;

    @ColumnInfo(name = NUM_CHILD_EDUCATION)
    public Integer num_child_education;

    @ColumnInfo(name = REQUIRES_AID)
    public boolean has_aid;
}
