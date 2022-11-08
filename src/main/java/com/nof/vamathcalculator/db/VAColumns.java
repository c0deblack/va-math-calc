/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.db;

// TODO: use an enumeration to create a Rating Type
public class VAColumns {
    private VAColumns(){}

    public final static String DEPEND_STATUS_COL_NAME = "depend_status";

    public static class BasicColumns {
        public final static String RATING_10 = "RATING_10";
        public final static String RATING_20 = "RATING_20";
        public final static String RATING_30 = "RATING_30";
        public final static String RATING_40 = "RATING_40";
        public final static String RATING_50 = "RATING_50";
        public final static String RATING_60 = "RATING_60";
        public final static String RATING_70 = "RATING_70";
        public final static String RATING_80 = "RATING_80";
        public final static String RATING_90 = "RATING_90";
        public final static String RATING_100 = "RATING_100";
    }

    public static class SMCColumns {
        public static final String SMC_L = "SMC_L";
        public static final String SMC_L_1_2 = "SMC_L_1_2";
        public static final String SMC_M = "SMC_M";
        public static final String SMC_M_1_2 = "SMC_M_1_2";
        public static final String SMC_N = "SMC_N";
        public static final String SMC_N_1_2 = "SMC_N_1_2";
        public static final String SMC_O_P = "SMC_O_P";
        public static final String SMC_R_1 = "SMC_R_1";
        public static final String SMC_R_2 = "SMC_R_2";
        public static final String SMC_S = "SMC_S";
    }

    public static class UserColumns {
        public static final String HAS_SMC      = "HAS_SMC";
        public static final String SMC_RATING   = "SMC_RATING";
        public static final String BASIC_RATING = "BASIC_RATING";
        public static final String HAS_SPOUSE   = "HAS_SPOUSE";
        public static final String NUM_PARENTS  = "NUM_PARENTS";
        public static final String NUM_CHILDREN = "NUM_CHILDREN";
        public static final String NUM_CHILD_EDUCATION = "NUM_CHILD_EDUCATION";
        public static final String NUM_CHILD_DEFECT = "NUM_CHILD_DEFECT";
        public static final String REQUIRES_AID = "REQUIRES_AID";
    }

        public static class DisabilityColumns {
            public static final String BASIC_RATING = "BASIC_RATING";
            public static final String IS_BILATERAL = "BILATERAL";
            public static final String SHORT_NAME   = "SHORT_NAME";
            public static final String SMC_RATING   = "SMC_RATING";
            public static final String IS_BASIC     = "IS_BASIC";
        }

    public static class ChildWithBirthDefectColumns {
        public static final String COMPENSATION = "COMPENSATION";
        public static final String LEVEL = "LEVEL";
        public static final String SHORT_NAME   = "SHORT_NAME";
        public static final String IS_SPINAFIDA = "IS_SPINAFIDA";
    }
}
