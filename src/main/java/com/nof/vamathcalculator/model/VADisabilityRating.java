/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.model;

import com.nof.vamathcalculator.db.VAColumns;

//TODO: Create separate rating and disability objects
public class VADisabilityRating{
    private Double m_rating = null;
    private Boolean m_is_bilateral = false;
    private String m_short_name = null;
    private String m_smc_rating = null;
    private Boolean m_is_basic = false;

    private VADisabilityRating(Double rating, Boolean is_bilateral, String short_name) {
        m_rating = rating;
        m_is_bilateral = is_bilateral;
        m_short_name = short_name;
        m_is_basic = true;
    }

    private VADisabilityRating(String smc_rating, String short_name) {
        m_smc_rating = smc_rating;
        m_short_name = short_name;
        m_is_basic = false;
    }

    public static VADisabilityRating get_basic(Double rating, Boolean is_bilateral, String short_name) {
        VADisabilityRating new_rating = new VADisabilityRating(rating, is_bilateral, short_name);
        return new_rating;
    }
    public static VADisabilityRating get_smc(String smc_rating, String short_name) {
        VADisabilityRating new_rating = new VADisabilityRating(smc_rating, short_name);
        return new_rating;
    }

    public Boolean is_basic() { return m_is_basic; }
    public Boolean is_smc() { return !m_is_basic; }
    public Boolean is_bilateral() {
        if (m_is_basic) {
            return m_is_bilateral;
        } else {
            throw new IllegalStateException("Cannot check if a SMC disability is bilateral!");
        }
    }

    public Double get_basic_rating() {
        if (m_is_basic) {
            return m_rating;
        } else {
            throw new IllegalStateException("Cannot get the basic rating from an SMC disability!");
        }
    }
    public String get_smc_rating() {
        if (!m_is_basic) {
            return m_smc_rating;
        } else {
            throw new IllegalStateException("Cannot get the smc rating from an basic disability!");
        }
    }

    public VADisabilityRating set_name(String new_name) {
        if (new_name != null) {
            m_short_name = new_name;
        } else {
            throw new IllegalStateException("Cannot set short description to a null string!");
        }
        return this;
    }
    public VADisabilityRating set_basic_rating(Double new_rating) {
        if (m_is_basic) {
            if (new_rating == null) {
                throw new IllegalStateException("Attempted to set basic rating with an invalid value. Null given, Decimal expected!");
            } else {
                if (new_rating <= 0d) {
                    throw new IllegalStateException("Attempted to set basic rating with a number <= 0. A Decimal greater than 0 is expected!");
                } else {
                    m_rating = new_rating;
                }
            }
        } else {
            throw new IllegalStateException("Cannot set basic rating on SMC disability.");
        }
        return this;
    }
    public VADisabilityRating set_smc_rating(String new_name) {
        if (!m_is_basic) {
            switch (new_name) {
                case VAColumns.SMCColumns.SMC_L:
                case VAColumns.SMCColumns.SMC_M:
                case VAColumns.SMCColumns.SMC_L_1_2:
                case VAColumns.SMCColumns.SMC_N:
                case VAColumns.SMCColumns.SMC_S:
                case VAColumns.SMCColumns.SMC_O_P:
                case VAColumns.SMCColumns.SMC_M_1_2:
                case VAColumns.SMCColumns.SMC_R_1:
                case VAColumns.SMCColumns.SMC_R_2:
                case VAColumns.SMCColumns.SMC_N_1_2:
                    m_smc_rating = new_name;
                    break;
                default:
                    throw new IllegalStateException("Invalid value for SMC rating!");
            }
        } else {
            throw new IllegalStateException("Cannot set SMC rating on a basic disability!");
        }
        return this;
    }
    public VADisabilityRating set_is_bilateral(Boolean new_bilateral) {
        if (m_is_basic) {
            m_is_bilateral = new_bilateral;
        } else {
            throw new IllegalStateException("Cannot set a SMC rating to bilateral!");
        }
        return this;
    }
};