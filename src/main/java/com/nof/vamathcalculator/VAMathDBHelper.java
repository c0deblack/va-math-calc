/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */

package com.nof.vamathcalculator;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Facilitates the creation and updating of the app SQLite database.
 *
 * This class implements functions that will create and upgrade the database if required. An
 * instantiation of this class is used to get a reference to the database object. The db object is
 * used to insert, update, and delete records as well as retrieving records via a cursor object.
 */
public class VAMathDBHelper extends SQLiteOpenHelper {

    public static final String BASIC_TABLE_NAME            = "BASIC";
    public static final String SPECIAL_TABLE_NAME          = "SPECIAL";
    public static final String DEPEND_STATUS_COLUMN_NAME   = "Dependency_Status";

    private static final String DB_NAME = "va_math_calc";
    private static final int    DB_VER  = 1;

    /*
    VAMathDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    */

    // TODO: pass DB_NAME as second parameter. Setting to null creates memory only / testing db.
    VAMathDBHelper(Context context) {
        super(context, null, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        databaseTableHelper(db, 0, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
        databaseTableHelper(db, old_version, new_version);
    }

    // Helper function that deals with creating and updating database tables
    private void databaseTableHelper(SQLiteDatabase db, int old_version, int new_version) {
        if ( new_version == 1 ) {
            createBasicTable(db);
            createSMCTable(db);
        }
    }

    // Helper function used to insert basic compensation rates into the compensation table
    private void insertBasicRecord(
            SQLiteDatabase db,
            String depend_status,
            double rate_30,
            double rate_40,
            double rate_50,
            double rate_60,
            double rate_70,
            double rate_80,
            double rate_90,
            double rate_100
    ) {
        // Add a single row to the basic compensation table
        ContentValues basic_values = new ContentValues();
        basic_values.put(DEPEND_STATUS_COLUMN_NAME, depend_status);
        basic_values.put(VARates.Basic.Rating.RATING_30.name(), rate_30);
        basic_values.put(VARates.Basic.Rating.RATING_40.name(), rate_40);
        basic_values.put(VARates.Basic.Rating.RATING_50.name(), rate_50);
        basic_values.put(VARates.Basic.Rating.RATING_60.name(), rate_60);
        basic_values.put(VARates.Basic.Rating.RATING_70.name(), rate_70);
        basic_values.put(VARates.Basic.Rating.RATING_80.name(), rate_80);
        basic_values.put(VARates.Basic.Rating.RATING_90.name(), rate_90);
        basic_values.put(VARates.Basic.Rating.RATING_100.name(), rate_100);
        try {
            db.insert(BASIC_TABLE_NAME, null, basic_values);
        } catch (SQLiteException e) {
            Log.e("insertBasicRecord", e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper function used to insert SMC compensation rates into the compensation table
    private void insertSpecialRecord(
            SQLiteDatabase db,
            String depend_status,
            double SMC_L,
            double SMC_L_1_2,
            double SMC_M,
            double SMC_M_1_2,
            double SMC_N,
            double SMC_N_1_2,
            double SMC_O_P,
            double SMC_R_1,
            double SMC_R_2,
            double SMC_S
    ) {
        // Add a single row to the SMC compensation table
        ContentValues special_values = new ContentValues();
        special_values.put(DEPEND_STATUS_COLUMN_NAME, depend_status);
        special_values.put(VARates.Special.Rating.SMC_L.name(), SMC_L);
        special_values.put(VARates.Special.Rating.SMC_L_1_2.name(), SMC_L_1_2);
        special_values.put(VARates.Special.Rating.SMC_M.name(), SMC_M);
        special_values.put(VARates.Special.Rating.SMC_M_1_2.name(), SMC_M_1_2);
        special_values.put(VARates.Special.Rating.SMC_N.name(), SMC_N);
        special_values.put(VARates.Special.Rating.SMC_N_1_2.name(), SMC_N_1_2);
        special_values.put(VARates.Special.Rating.SMC_O_P.name(), SMC_O_P);
        special_values.put(VARates.Special.Rating.SMC_R_1.name(), SMC_R_1);
        special_values.put(VARates.Special.Rating.SMC_R_2.name(), SMC_R_2);
        special_values.put(VARates.Special.Rating.SMC_S.name(), SMC_S);
        try {
            db.insert(SPECIAL_TABLE_NAME, null, special_values);
        } catch (SQLiteException e) {
            Log.e("insertSpecialRecord", e.getMessage());
            e.printStackTrace();
        }

    }

    // This function creates the basic compensation table
    private void createBasicTable(SQLiteDatabase db) {

        // Create the basic table columns
        db.execSQL("CREATE TABLE " + BASIC_TABLE_NAME + " ("
                    +" _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DEPEND_STATUS_COLUMN_NAME + " TEXT UNIQUE NOT NULL, "
                    + VARates.Basic.Rating.RATING_30.name() + " REAL NOT NULL, "
                    + VARates.Basic.Rating.RATING_40.name() + " REAL NOT NULL, "
                    + VARates.Basic.Rating.RATING_50.name() + " REAL NOT NULL, "
                    + VARates.Basic.Rating.RATING_60.name() + " REAL NOT NULL, "
                    + VARates.Basic.Rating.RATING_70.name() + " REAL NOT NULL, "
                    + VARates.Basic.Rating.RATING_80.name() + " REAL NOT NULL, "
                    + VARates.Basic.Rating.RATING_90.name() + " REAL NOT NULL, "
                    + VARates.Basic.Rating.RATING_100.name() + " REAL NOT NULL);");


        //@@@@@@@@@@@@@@@@@@@@@@@
        /* NO CHILDREN SECTION */
        //@@@@@@@@@@@@@@@@@@@@@@@

        // Veteran alone
        insertBasicRecord(
                db,
                VARates.Basic.Dependent_Status.Alone_No_Depends.name(),
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
                db,
                VARates.Basic.Dependent_Status.Spouse_No_Depends.name(),
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
                db,
                VARates.Basic.Dependent_Status.Spouse_One_Parent.name(),
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
                db,
                VARates.Basic.Dependent_Status.Spouse_Two_Parents.name(),
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
                db,
                VARates.Basic.Dependent_Status.One_Parent.name(),
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
                db,
                VARates.Basic.Dependent_Status.Two_Parents.name(),
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
                db,
                VARates.Basic.Dependent_Status.Child_Only.name(),
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
                db,
                VARates.Basic.Dependent_Status.Child_and_Spouse.name(),
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
                db,
                VARates.Basic.Dependent_Status.Child_Spouse_One_Parent.name(),
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
                db,
                VARates.Basic.Dependent_Status.Child_Spouse_Two_Parents.name(),
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
                db,
                VARates.Basic.Dependent_Status.Child_One_Parent.name(),
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
                db,
                VARates.Basic.Dependent_Status.Child_Two_Parents.name(),
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
                db,
                VARates.Basic.Dependent_Status.Spouse_Aid_Attendance.name(),
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
                db,
                VARates.Basic.Dependent_Status.Additional_Child.name(),
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
                db,
                VARates.Basic.Dependent_Status.Child_Education.name(),
                89.00,
                119.00,
                149.00,
                178.00,
                208.00,
                238.00,
                268.00,
                298.18
        );

    }

    private void createSMCTable(SQLiteDatabase db) {

        // Create the SMC table columns
        db.execSQL("CREATE TABLE " + SPECIAL_TABLE_NAME + " ("
                    +" _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DEPEND_STATUS_COLUMN_NAME + " TEXT UNIQUE NOT NULL, "
                    + VARates.Special.Rating.SMC_L.name() + " REAL NOT NULL, "
                    + VARates.Special.Rating.SMC_L_1_2.name() + " REAL NOT NULL, "
                    + VARates.Special.Rating.SMC_M.name() + " REAL NOT NULL, "
                    + VARates.Special.Rating.SMC_M_1_2.name() + " REAL NOT NULL, "
                    + VARates.Special.Rating.SMC_N.name() + " REAL NOT NULL, "
                    + VARates.Special.Rating.SMC_N_1_2.name() + " REAL NOT NULL, "
                    + VARates.Special.Rating.SMC_O_P.name() + " REAL NOT NULL, "
                    + VARates.Special.Rating.SMC_R_1.name() + " REAL NOT NULL, "
                    + VARates.Special.Rating.SMC_R_2.name() + " REAL NOT NULL, "
                    + VARates.Special.Rating.SMC_S.name() + " REAL NOT NULL);");

        //@@@@@@@@@@@@@@@@@@@@@@@
        /* NO CHILDREN SECTION */
        //@@@@@@@@@@@@@@@@@@@@@@@

        // Veteran alone
        insertSpecialRecord(
                db,
                VARates.Special.Dependent_Status.Alone_No_Depends.name(),
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
                db,
                VARates.Special.Dependent_Status.Spouse_No_Depends.name(),
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
                db,
                VARates.Special.Dependent_Status.Spouse_One_Parent.name(),
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
                db,
                VARates.Special.Dependent_Status.Spouse_Two_Parents.name(),
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
                db,
                VARates.Special.Dependent_Status.One_Parent.name(),
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
                db,
                VARates.Special.Dependent_Status.Two_Parents.name(),
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
                db,
                VARates.Special.Dependent_Status.Child_Only.name(),
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
                db,
                VARates.Special.Dependent_Status.Child_and_Spouse.name(),
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
                db,
                VARates.Special.Dependent_Status.Child_Spouse_One_Parent.name(),
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
                db,
                VARates.Special.Dependent_Status.Child_Spouse_Two_Parents.name(),
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
                db,
                VARates.Special.Dependent_Status.Child_One_Parent.name(),
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
                db,
                VARates.Special.Dependent_Status.Child_Two_Parents.name(),
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
                db,
                VARates.Special.Dependent_Status.Spouse_Aid_Attendance.name(),
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
                db,
                VARates.Special.Dependent_Status.Additional_Child.name(),
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
                db,
                VARates.Special.Dependent_Status.Child_Education.name(),
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
    }
}
