package com.nof.vamathcalculator.db;

import static com.nof.vamathcalculator.db.VAColumns.DEPEND_STATUS_COL_NAME;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_L;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_L_1_2;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_M;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_M_1_2;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_N;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_N_1_2;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_O_P;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_R_1;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_R_2;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.SMC_S;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {
        @Index(value = DEPEND_STATUS_COL_NAME, unique = true)
})
public class SMC_Rating {

    @PrimaryKey(autoGenerate = true)
    public int _id;

    @ColumnInfo(name = DEPEND_STATUS_COL_NAME)
    public String depend_status;

    @ColumnInfo(name = SMC_L)
    public Double smc_l;

    @ColumnInfo(name = SMC_L_1_2)
    public Double smc_l_1_2;

    @ColumnInfo(name = SMC_M)
    public Double smc_m;

    @ColumnInfo(name = SMC_M_1_2)
    public Double smc_m_1_2;

    @ColumnInfo(name = SMC_N)
    public Double smc_n;

    @ColumnInfo(name = SMC_N_1_2)
    public Double smc_n_1_2;

    @ColumnInfo(name = SMC_O_P)
    public Double smc_o_p;

    @ColumnInfo(name = SMC_R_1)
    public Double smc_r_1;

    @ColumnInfo(name = SMC_R_2)
    public Double smc_r_2;

    @ColumnInfo(name = SMC_S)
    public Double smc_s;
}
