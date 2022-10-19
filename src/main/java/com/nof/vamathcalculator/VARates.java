package com.nof.vamathcalculator;

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
 * @see https://www.va.gov/disability/compensation-rates/veteran-rates/
 * @see https://www.va.gov/disability/compensation-rates/special-monthly-compensation-rates/
 * @see https://www.va.gov/disability/compensation-rates/special-benefit-allowance-rates/
 * @see https://www.va.gov/disability/compensation-rates/birth-defect-rates/
 */
public final class VARates {

    // Basic ratings that depend on disability rating alone.
    public static final class Basic {

        // Disability ratings between 10% and 20% get a flat rate
        public static final double RATING_10 = 152.64;
        public static final double RATING_20 = 301.74;

        // These represent columns in the compensation table
        enum Rating {
            RATING_30   ("30%"),
            RATING_40   ("40%"),
            RATING_50   ("50%"),
            RATING_60   ("60%"),
            RATING_70   ("70%"),
            RATING_80   ("80%"),
            RATING_90   ("90%"),
            RATING_100   ("100%")
            ;

            private String mRating;

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
            Child_Spouse_Two_Parent ("Basic veteran with child(ren) spouse and two dependent parents"),
            Child_One_Parent    ("Basic veteran with child(ren) and one parent but no spouse"),
            Child_Two_Parent    ("Basic veteran with child(ren) and two parents but no spouse"),

            // Additional amounts
            Spouse_Aid_Attendance ("Basic veteran requires help with daily activities"),
            Additional_Child      ("Basic veteran each additional amount per child under 18"),
            Child_Education       ("Basic veteran each additional child in qualified school")

            ;

            private String mStatus;

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
            SMC_L       ("SMC-L"),
            SMC_L_1_2   ("SMC-L-1/2"),
            SMC_M       ("SMC-M"),
            SMC_M_1_2   ("SMC-M-1/2"),
            SMC_N       ("SMC-N"),
            SMC_N_1_2   ("SMC-N-1/2"),
            SMC_O_P     ("SMC-O/P"),
            SMC_R_1     ("SMC-R.1"),
            SMC_R_2     ("SMC-R.2/T"),
            SMC_S       ("SMC-S")
            ;

            private String mRating;

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
            Child_Spouse_Two_Parent ("Special veteran with child(ren) spouse and two dependent parents"),
            Child_One_Parent    ("Special veteran with child(ren) and one parent but no spouse"),
            Child_Two_Parent    ("Special veteran with child(ren) and two parents but no spouse"),

            // Additional amounts
            Spouse_Aid_Attendance ("Special veteran requires help with daily activities"),
            Additional_Child      ("Special veteran each additional amount per child under 18"),
            Child_Education       ("Special veteran each additional child in qualified school")
            ;

            private String mStatus;

            Dependent_Status(String status) {
                this.mStatus = status;
            }
            public String getStatus() {
                return this.mStatus;
            }
        }

    }
}