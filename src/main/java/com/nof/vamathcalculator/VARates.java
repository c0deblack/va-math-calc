package com.nof.vamathcalculator;
/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */

/**
 * Contains data related to the va disability ratings.
 *
 * This class holds data used to categorize different disability statuses. There are 4 constants
 * used in situations where they are no variations and a flat dollar amount is added to a veteran's
 * claim total. Other portions of the class define enumerations that are used within the disability
 * monthly reward tables.
 *
 * The enumerations define column names and row values that correspond with
 * specific situations. These values are used in a lookup table provided by va.gov to obtain
 * specific monthly compensation dollar amounts. These amounts are used to calculate a veteran's
 * total compensation.
 *
 * @see <a href="https://www.va.gov/disability/compensation-rates/veteran-rates/">Basic Rates</a>
 * @see <a href="https://www.va.gov/disability/compensation-rates/special-monthly-compensation-rates/">SMC Rates</a>
 * @see <a href="https://www.va.gov/disability/compensation-rates/special-benefit-allowance-rates/">Home Care Allowance</a>
 * @see <a href="https://www.va.gov/disability/compensation-rates/birth-defect-rates/">Birth Defect Comp Rates</a>
 */
public final class VARates {

    // Basic ratings that depend on disability rating alone.
    public static final class Basic {

        // Disability ratings between 10% and 20% get a flat rate
        public static final double RATING_10 = 152.64;
        public static final double RATING_20 = 301.74;

        // These represent column names (titles) in the compensation table
        enum Rating {
            RATING_30   ("p30"),
            RATING_40   ("p40"),
            RATING_50   ("p50"),
            RATING_60   ("p60"),
            RATING_70   ("p70"),
            RATING_80   ("p80"),
            RATING_90   ("p90"),
            RATING_100  ("p100")
            ;

            private final String mRating;

            Rating(String rating) {
                this.mRating = rating;
            }

            public String getRating() {
                return this.mRating;
            }
        }

        // Each status belongs to a different row in the compensation table.
        enum Dependent_Status {

            // With a dependent spouse or parent, but no children

            Alone_No_Depends    ("Basic veteran alone with no dependents"),
            Spouse_No_Depends   ("Basic veteran with spouse and no parents or children"),
            Spouse_One_Parent   ("Basic veteran with spouse and one dependent parent"),
            Spouse_Two_Parents  ("Basic veteran with spouse and two dependent parents"),
            One_Parent          ("Basic veteran with no spouse or children and one dependent parent"),
            Two_Parents         ("Basic veteran with no spouse or children and two dependent parents"),

            // With dependents, including children
            Child_Only          ("Basic veteran with child(ren) and no dependent spouse or parents"),
            Child_and_Spouse    ("Basic veteran with child(ren) and spouse"),
            Child_Spouse_One_Parent ("Basic veteran with child(ren) spouse and one dependent parent"),
            Child_Spouse_Two_Parents ("Basic veteran with child(ren) spouse and two dependent parents"),
            Child_One_Parent    ("Basic veteran with child(ren) and one parent but no spouse"),
            Child_Two_Parents    ("Basic veteran with child(ren) and two parents but no spouse"),

            // Additional amounts
            Spouse_Aid_Attendance ("Basic veteran requires help with daily activities"),
            Additional_Child      ("Basic veteran each additional amount per child under 18"),
            Child_Education       ("Basic veteran each additional child in qualified school")

            ;

            private final String mStatus;

            Dependent_Status(String status) {
                this.mStatus = status;
            }

            public String getStatus() {
                return this.mStatus;
            }
        }

    }

    // Special ratings added to the basic rate when certain conditions are met.
    public static final class Special {

        // Special designations that add a fixed amount to qualified veterans

        public static final double SMC_K = 118.33;
        public static final double SMC_Q = 67.00;

        // These represent columns in the compensation table
        enum Rating {
            SMC_L       ("SMC_L"),
            SMC_L_1_2   ("SMC_L_1_2"),
            SMC_M       ("SMC_M"),
            SMC_M_1_2   ("SMC_M_1_2"),
            SMC_N       ("SMC_N"),
            SMC_N_1_2   ("SMC_N_1_2"),
            SMC_O_P     ("SMC_O_P"),
            SMC_R_1     ("SMC_R_1"),
            SMC_R_2     ("SMC_R_2_T"),
            SMC_S       ("SMC_S")
            ;

            private final String mRating;

            Rating(String rating) {
                this.mRating = rating;
            }

            public String getRating() {
                return this.mRating;
            }
        }

        // Each status belongs to a different row in the compensation table.
        enum Dependent_Status {

            // With a dependent spouse or parent, but no children

            Alone_No_Depends    ("Special veteran alone with no dependents"),
            Spouse_No_Depends   ("Special veteran with spouse and no parents or children"),
            Spouse_One_Parent   ("Special veteran with spouse and one dependent parent"),
            Spouse_Two_Parents  ("Special veteran with spouse and two dependent parents"),
            One_Parent          ("Special veteran with no spouse or children and one dependent parent"),
            Two_Parents         ("Special veteran with no spouse or children and two dependent parents"),

            // With dependents, including children
            Child_Only          ("Special veteran with child(ren) and no dependent spouse or parents"),
            Child_and_Spouse    ("Special veteran with child(ren) and spouse"),
            Child_Spouse_One_Parent ("Special veteran with child(ren) spouse and one dependent parent"),
            Child_Spouse_Two_Parents ("Special veteran with child(ren) spouse and two dependent parents"),
            Child_One_Parent    ("Special veteran with child(ren) and one parent but no spouse"),
            Child_Two_Parents    ("Special veteran with child(ren) and two parents but no spouse"),

            // Additional amounts
            Spouse_Aid_Attendance ("Special veteran requires help with daily activities"),
            Additional_Child      ("Special veteran each additional amount per child under 18"),
            Child_Education       ("Special veteran each additional child in qualified school")
            ;

            private final String mStatus;

            Dependent_Status(String status) {
                this.mStatus = status;
            }
            public String getStatus() {
                return this.mStatus;
            }
        }

    }
}