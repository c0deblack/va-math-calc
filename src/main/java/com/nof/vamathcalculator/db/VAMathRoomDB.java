/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities =
    {
        Basic_Rating.class,
        SMC_Rating.class,
        Disability.class,
        User.class,
        BirthDefectChild.class
    }, version = 1, exportSchema = true)
public abstract class VAMathRoomDB extends RoomDatabase {
    public abstract BasicRatingDAO basic_ratingDAO();
    public abstract SMCRatingDAO   smc_ratingDAO();
    public abstract DisabilityDAO disability_DAO();
    public abstract UserDAO user_DAO();
    public abstract BirthDefectChildDAO birth_defectDAO();

    private static volatile VAMathRoomDB INSTANCE = null;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static VAMathRoomDB getDB(final Context context) {
        if(INSTANCE == null){
            /*
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                            VAMathRoomDB.class)
                    .allowMainThreadQueries()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
             */
            synchronized (VAMathRoomDB.class) {
                INSTANCE = Room.databaseBuilder(
                                context.getApplicationContext(),
                                VAMathRoomDB.class,
                                "VA-Math-DB")
                        .allowMainThreadQueries()
                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        final List<Basic_Rating> basic_ratings = new ArrayList<>();
        final List<SMC_Rating> smc_ratings = new ArrayList<>();

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                // Create a User record with default values
                List<User> saved_user = INSTANCE.user_DAO().getAll().getValue();
                if(saved_user == null || saved_user.size() == 0){
                    User user = new User();
                    user.has_smc = false;
                    user.has_aid = false;
                    user.has_spouse = false;
                    user.num_parents = 0;
                    user.num_Child = 0;
                    user.num_child_birth_defect = 0;
                    user.num_child_education = 0;
                    user.smc_rating = null;
                    user.basic_rating = null;
                    INSTANCE.user_DAO().insertAll(user);
                }

                // Pre-populate the database with rating values
                List<Basic_Rating> saved_ratings = INSTANCE.basic_ratingDAO().getAll();
                if(saved_ratings == null || saved_ratings.size() == 0){

                    //@@@@@@@@@@@@@@@@@@@@@@@
                    /* NO CHILDREN SECTION */
                    //@@@@@@@@@@@@@@@@@@@@@@@

                    // Veteran alone
                    insertBasicRecord(
                            
                            DependStatus.Alone_No_Depends.getStatus(),
                            467.39,
                            673.28,
                            958.44,
                            1214.03,
                            1529.95,
                            1778.43,
                            1998.52,
                            3332.06
                    );

                    // With spouse, no dependents
                    insertBasicRecord(
                            
                            DependStatus.Spouse_No_Depends.getStatus(),
                            522.39,
                            747.28,
                            1050.44,
                            1325.03,
                            1659.95,
                            1926.43,
                            2165.52,
                            3517.84
                    );

                    // With spouse, one parent
                    insertBasicRecord(
                            
                            DependStatus.Spouse_One_Parent.getStatus(),
                            566.39,
                            806.28,
                            1124.44,
                            1414.03,
                            1763.95,
                            2045.43,
                            2299.52,
                            3666.94
                    );

                    // With spouse, two parent
                    insertBasicRecord(
                            
                            DependStatus.Spouse_Two_Parents.getStatus(),
                            610.39,
                            865.28,
                            1198.44,
                            1503.03,
                            1867.95,
                            2164.43,
                            2433.52,
                            3816.04
                    );

                    // No spouse, one parent
                    insertBasicRecord(
                            
                            DependStatus.One_Parent.getStatus(),
                            511.39,
                            732.28,
                            1032.44,
                            1303.03,
                            1633.95,
                            1897.43,
                            2132.52,
                            3481.16
                    );

                    // No spouse, two parents
                    insertBasicRecord(
                            
                            DependStatus.Two_Parents.getStatus(),
                            555.39,
                            791.28,
                            1106.44,
                            1392.03,
                            1737.95,
                            2016.43,
                            2266.52,
                            3630.26
                    );

                    //@@@@@@@@@@@@@@@@@@@@@@@
                    /* WITH CHILDREN SECTION */
                    //@@@@@@@@@@@@@@@@@@@@@@@

                    // One Child, no spouse or parents
                    insertBasicRecord(
                            
                            DependStatus.Child_Only.getStatus(),
                            504.39,
                            722.28,
                            1020.44,
                            1288.03,
                            1615.95,
                            1877.43,
                            2109.52,
                            3456.30
                    );

                    // One Child and spouse, no parents
                    insertBasicRecord(
                            
                            DependStatus.Child_and_Spouse.getStatus(),
                            563.39,
                            801.28,
                            1118.44,
                            1407.03,
                            1754.95,
                            2035.43,
                            2287.52,
                            3653.89
                    );

                    // One Child and spouse, and one parent
                    insertBasicRecord(
                            
                            DependStatus.Child_Spouse_One_Parent.getStatus(),
                            607.39,
                            860.28,
                            1192.44,
                            1496.03,
                            1858.95,
                            2154.43,
                            2421.52,
                            3802.99
                    );

                    // One Child and spouse, and two parent
                    insertBasicRecord(
                            
                            DependStatus.Child_Spouse_Two_Parents.getStatus(),
                            651.39,
                            919.28,
                            1266.44,
                            1585.03,
                            1962.95,
                            2273.43,
                            2555.52,
                            3952.09
                    );

                    // One Child and one parent
                    insertBasicRecord(
                            
                            DependStatus.Child_One_Parent.getStatus(),
                            548.39,
                            781.28,
                            1094.44,
                            1377.03,
                            1719.95,
                            1996.43,
                            2243.52,
                            3605.40
                    );

                    // One Child and two parents
                    insertBasicRecord(
                            
                            DependStatus.Child_Two_Parents.getStatus(),
                            592.39,
                            840.28,
                            1168.44,
                            1466.03,
                            1823.95,
                            2115.43,
                            2377.52,
                            3754.50
                    );

                    //@@@@@@@@@@@@@@@@@@@@@@@
                    /* ADDED AMOUNT SECTION */
                    //@@@@@@@@@@@@@@@@@@@@@@@

                    // Aid and attendance compensation
                    insertBasicRecord(
                            
                            DependStatus.Spouse_Aid_Attendance.getStatus(),
                            51.00,
                            68.00,
                            86.00,
                            102.00,
                            119.00,
                            136.00,
                            153.00,
                            170.38
                    );


                    // Each additional child under 18
                    insertBasicRecord(
                            
                            DependStatus.Additional_Child.getStatus(),
                            27.00,
                            36.00,
                            46.00,
                            55.00,
                            64.00,
                            73.00,
                            83.00,
                            92.31
                    );

                    // Each additional child over 18 in qualifying school
                    insertBasicRecord(
                            
                            DependStatus.Child_Education.getStatus(),
                            89.00,
                            119.00,
                            149.00,
                            178.00,
                            208.00,
                            238.00,
                            268.00,
                            298.18
                    );

                    INSTANCE.basic_ratingDAO().insertAll(basic_ratings.toArray(new Basic_Rating[0]));

                    insertSpecialRecord(
                            
                            DependStatus.Alone_No_Depends.getStatus(),
                            4146.13,
                            4360.47,
                            4575.68,
                            4890.07,
                            5205.17,
                            5511.35,
                            5818.09,
                            8313.61,
                            9535.91,
                            3729.64
                    );

                    // With spouse, no parents
                    insertSpecialRecord(
                            
                            DependStatus.Spouse_No_Depends.getStatus(),
                            4331.91,
                            4546.25,
                            4761.46,
                            5075.85,
                            5390.95,
                            5697.13,
                            6003.87,
                            8499.39,
                            9712.69,
                            3915.42
                    );

                    // With spouse, one parents
                    insertSpecialRecord(
                            
                            DependStatus.Spouse_One_Parent.getStatus(),
                            4481.01,
                            4695.35,
                            4910.56,
                            5224.95,
                            5540.05,
                            5846.23,
                            6152.97,
                            8648.49,
                            9870.79,
                            4046.52
                    );

                    // With spouse, two parents
                    insertSpecialRecord(
                            
                            DependStatus.Spouse_Two_Parents.getStatus(),
                            4630.11,
                            4844.45,
                            5059.66,
                            5374.05,
                            5689.15,
                            5995.33,
                            6302.07,
                            8797.59,
                            10019.89,
                            4213.62
                    );

                    // No spouse, one parent
                    insertSpecialRecord(
                            
                            DependStatus.One_Parent.getStatus(),
                            4295.23,
                            4509.57,
                            4724.78,
                            5039.17,
                            5354.27,
                            5660.45,
                            5967.19,
                            8462.71,
                            9685.01,
                            3878.74
                    );

                    // No spouse, two parent
                    insertSpecialRecord(
                            
                            DependStatus.Two_Parents.getStatus(),
                            4444.33,
                            4658.67,
                            4873.88,
                            5188.27,
                            5503.37,
                            5809.55,
                            6116.29,
                            8611.81,
                            9834.11,
                            4027.84
                    );

                    //@@@@@@@@@@@@@@@@@@@@@@@
                    /* WITH CHILDREN SECTION */
                    //@@@@@@@@@@@@@@@@@@@@@@@

                    // Children, no spouse or parents
                    insertSpecialRecord(
                            
                            DependStatus.Child_Only.getStatus(),
                            4270.37,
                            4484.71,
                            4699.92,
                            5014.31,
                            5329.41,
                            5635.59,
                            5942.33,
                            8437.85,
                            9660.15,
                            3853.88
                    );

                    // Spouse and child, no parents
                    insertSpecialRecord(
                            
                            DependStatus.Child_and_Spouse.getStatus(),
                            4467.96,
                            4682.30,
                            4897.51,
                            5211.90,
                            5527.00,
                            5833.18,
                            6139.92,
                            8635.44,
                            9857.74,
                            4051.47
                    );

                    // Spouse, child, and one parents
                    insertSpecialRecord(
                            
                            DependStatus.Child_Spouse_One_Parent.getStatus(),
                            4617.06,
                            4831.40,
                            5046.61,
                            5361.00,
                            5676.10,
                            5982.28,
                            6289.02,
                            8784.54,
                            10006.84,
                            4200.57
                    );

                    // Spouse, child, and two parents
                    insertSpecialRecord(
                            
                            DependStatus.Child_Spouse_Two_Parents.getStatus(),
                            4766.16,
                            4980.50,
                            5195.71,
                            5510.10,
                            5825.20,
                            6131.38,
                            6438.12,
                            8933.64,
                            10155.94,
                            4349.67
                    );

                    // Child and one parent, no spouse
                    insertSpecialRecord(
                            
                            DependStatus.Child_One_Parent.getStatus(),
                            4419.47,
                            4633.81,
                            4849.02,
                            5163.41,
                            5478.51,
                            5784.69,
                            6091.43,
                            8586.95,
                            9809.25,
                            4002.98
                    );

                    // Child and two parents, no spouse
                    insertSpecialRecord(
                            
                            DependStatus.Child_Two_Parents.getStatus(),
                            4568.57,
                            4782.91,
                            4998.12,
                            5312.51,
                            5627.61,
                            5933.79,
                            6240.53,
                            8736.05,
                            9958.35,
                            4152.08
                    );

                    //@@@@@@@@@@@@@@@@@@@@@@@
                    /* ADDED AMOUNT SECTION */
                    //@@@@@@@@@@@@@@@@@@@@@@@

                    // Aid and attendance
                    insertSpecialRecord(
                            
                            DependStatus.Spouse_Aid_Attendance.getStatus(),
                            170.38,
                            170.38,
                            170.38,
                            170.38,
                            170.38,
                            170.38,
                            170.38,
                            170.38,
                            170.38,
                            170.38
                    );

                    // Each additional child under 18
                    insertSpecialRecord(
                            
                            DependStatus.Additional_Child.getStatus(),
                            92.31,
                            92.31,
                            92.31,
                            92.31,
                            92.31,
                            92.31,
                            92.31,
                            92.31,
                            92.31,
                            92.31
                    );

                    // Aid and attendance
                    insertSpecialRecord(
                            
                            DependStatus.Child_Education.getStatus(),
                            298.18,
                            298.18,
                            298.18,
                            298.18,
                            298.18,
                            298.18,
                            298.18,
                            298.18,
                            298.18,
                            298.18
                    );
                    INSTANCE.smc_ratingDAO().insertAll(smc_ratings.toArray(new SMC_Rating[0]));
                }
            });
        }
        private void insertBasicRecord(
                String depend_status,
                Double rate30,
                Double rate40,
                Double rate50,
                Double rate60,
                Double rate70,
                Double rate80,
                Double rate90,
                Double rate100
        ) {
            Basic_Rating rating = new Basic_Rating();
            rating.depend_status = depend_status;
            rating.rating_10 = 152.64;
            rating.rating_20 = 301.74;
            rating.rating_30 = rate30;
            rating.rating_40 = rate40;
            rating.rating_50 = rate50;
            rating.rating_60 = rate60;
            rating.rating_70 = rate70;
            rating.rating_80 = rate80;
            rating.rating_90 = rate90;
            rating.rating_100 = rate100;

            this.basic_ratings.add(rating);
        }

        private void insertSpecialRecord(
                String depend_status,
                Double SMC_L,
                Double SMC_L_1_2,
                Double SMC_M,
                Double SMC_M_1_2,
                Double SMC_N,
                Double SMC_N_1_2,
                Double SMC_O_P,
                Double SMC_R_1,
                Double SMC_R_2,
                Double SMC_S
        ) {
            SMC_Rating rating = new SMC_Rating();
            rating.depend_status = depend_status;
            rating.smc_l = SMC_L;
            rating.smc_l_1_2 = SMC_L_1_2;
            rating.smc_m = SMC_M;
            rating.smc_m_1_2 = SMC_M_1_2;
            rating.smc_n = SMC_N;
            rating.smc_n_1_2 = SMC_N_1_2;
            rating.smc_o_p = SMC_O_P;
            rating.smc_r_1 = SMC_R_1;
            rating.smc_r_2 = SMC_R_2;
            rating.smc_s = SMC_S;

            this.smc_ratings.add(rating);
        }
    };
    

}

