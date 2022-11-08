/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.model;

import android.util.Log;

import com.nof.vamathcalculator.db.BirthDefectChild;

import java.util.ArrayList;
import java.util.List;

public class VAMathUser {

    private static VAMathUser INSTANCE;
    // rating information
    private static Double m_combined_rating = null;
    private static final List<VADisabilityRating> m_ratings_list = new ArrayList<>();
    private static final List<VAMathBirthDefectChild> m_child_birth_defect_list = new ArrayList<>();
    // SMC info
    private static boolean m_has_smc = false;
    //spouse info
    private static boolean m_has_spouse = false;
    //parent info
    private static boolean m_has_parents = false;
    private static Integer m_num_parents = 0;
    // child info
    private static boolean m_has_Child = false;
    private static boolean m_has_child_birth_defect = false;
    private static boolean m_has_child_education = false;
    private static Integer m_num_children = 0;
    private static Integer m_num_children_birth_defect = 0;
    private static Integer m_num_children_education = 0;
    // aid & attendance
    private static boolean m_has_aid = false;

    private VAMathUser(){}

    public static VAMathUser getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new VAMathUser();
        }
        return INSTANCE;
    }

    public Boolean add_basic_disability(Double rating, Boolean is_bilateral, String short_name) {
        VADisabilityRating new_rating = VADisabilityRating.get_basic(rating, is_bilateral, short_name);
        m_ratings_list.add(new_rating);
        return true;
    }
    public Boolean add_smc_rating(String rating, String short_name) {
        if(m_has_smc) {
            Log.w("VAMathUser", "Attempt to add a second SMC rating. A user may only have one SMC rating in the disability list.");
            return false;
        }
        VADisabilityRating new_rating = VADisabilityRating.get_smc(rating, short_name);
        m_ratings_list.add(new_rating);
        m_has_smc = true;
        return true;
    }

    public void calc_combined_disability(){}

    // functions to set User details
    public VAMathUser set_spouse(Boolean has_spouse) {
        m_has_spouse = has_spouse;
        return this;
    }
    public Boolean has_spouse(){return m_has_spouse; }

    public VAMathUser set_parents(Integer num_parents) {
        if (num_parents != null && num_parents >= 0) {
            m_num_parents = num_parents;
            m_has_parents = true;
        } else {
            throw new IllegalStateException("Setting user parent information requires a valid positive integer!");
        }
        return this;
    }
    public Boolean has_parents(){return m_has_parents; }
    public Integer num_parents(){return m_num_parents; }

    public VAMathUser set_children(Integer num_children) {
        if (num_children != null && num_children >= 0) {
            m_num_children = num_children;
            m_has_Child = true;
        } else {
            throw new IllegalStateException("Setting user child information requires a valid positive integer!");
        }
        return this;
    }
    public Boolean has_children(){return m_has_Child; }
    public Integer num_children(){return m_num_children; }

    public VAMathUser set_children_education(Integer num_children) {
        if (num_children != null && num_children >= 0) {
            m_num_children_education = num_children;
            m_has_child_education = true;
        } else {
            throw new IllegalStateException("Setting user child education information requires a valid positive integer!");
        }
        return this;
    }
    public Boolean has_children_education(){return m_has_child_education; }
    public Integer num_children_education(){return m_num_children_education; }

    public VAMathUser set_children_with_defect(Integer num_children) {
        if (num_children != null && num_children >= 0) {
            m_num_children_birth_defect = num_children;
            m_has_child_birth_defect = true;
        } else {
            throw new IllegalStateException("Setting child(ren) with birth defect requires an valid positive integer!");
        }
        return this;
    }
    public VAMathUser add_child_birth_defect(BirthDefectChild child) {
    if(child != null){
        VAMathBirthDefectChild new_child = null;
        if(child.is_spinafida) {
            new_child = new VABirthDefectSpinaFida(child.level, child.short_name);
        } else {
            new_child = new VABirthDefectOther(child.level, child.short_name);
        }
        m_has_child_birth_defect = true;
        m_child_birth_defect_list.add(new_child);
        //m_num_children_birth_defect++;
    } else {
        throw new IllegalStateException("Cannot add a child with birth defects from a child of type null. Type BirthDefectChild is expected.");
    }
        return this;
    }
    public List<VAMathBirthDefectChild> get_all_defect_children() {return m_child_birth_defect_list; }
    public Integer num_defect_children(){return m_num_children_birth_defect; }

    public VAMathUser set_aid(Boolean has_aid) {
        m_has_aid = has_aid;
        return this;
    }
    public Boolean get_aid(){return m_has_aid; }

}
