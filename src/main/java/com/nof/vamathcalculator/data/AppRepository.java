/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.nof.vamathcalculator.db.BasicRatingDAO;
import com.nof.vamathcalculator.db.Basic_Rating;
import com.nof.vamathcalculator.db.BirthDefectChild;
import com.nof.vamathcalculator.db.BirthDefectChildDAO;
import com.nof.vamathcalculator.db.DependStatus;
import com.nof.vamathcalculator.db.Disability;
import com.nof.vamathcalculator.db.DisabilityDAO;
import com.nof.vamathcalculator.db.SMCRatingDAO;
import com.nof.vamathcalculator.db.SMC_Rating;
import com.nof.vamathcalculator.db.User;
import com.nof.vamathcalculator.db.UserDAO;
import com.nof.vamathcalculator.db.VAMathRoomDB;
import static com.nof.vamathcalculator.db.VAColumns.BasicColumns.*;
import static com.nof.vamathcalculator.db.VAColumns.SMCColumns.*;


import java.util.List;

/**
 * Main application database repository.
 *
 * Provides a level of abstraction from the lower level data sources API. Currently the only
 * data source is the database. The AppRepository is used directly in the
 * {@link com.nof.vamathcalculator.viewmodel.VAMathViewModel}
 */
public class AppRepository {
    private final BasicRatingDAO basic_table;
    private final SMCRatingDAO smc_table;
    private final DisabilityDAO disability_table;
    private final BirthDefectChildDAO birth_defect_table;
    private final UserDAO user_table;

    private final List<Basic_Rating> all_basic_ratings;
    private final List<SMC_Rating> all_smc_ratings;

    private final LiveData<User> user;
    private final LiveData<List<Disability>> disabilities;
    private final LiveData<List<BirthDefectChild>> children_birth_defects;

    public AppRepository(Application application) {
        basic_table = VAMathRoomDB.getDB(application).basic_ratingDAO();
        smc_table = VAMathRoomDB.getDB(application).smc_ratingDAO();
        disability_table = VAMathRoomDB.getDB(application).disability_DAO();
        birth_defect_table = VAMathRoomDB.getDB(application).birth_defectDAO();
        user_table = VAMathRoomDB.getDB(application).user_DAO();

        all_basic_ratings = basic_table.getAll();
        all_smc_ratings = smc_table.getAll();
        user = user_table.getUserData();
        disabilities = disability_table.getAll();
        children_birth_defects = birth_defect_table.getAll();
    }

    /**
     * Inserts a {@link Basic_Rating} object into the basic rating table.
     * @param ratings One or more {@link Basic_Rating} objects that each define a complete row
     *                in the table.
     */
    public void insert(Basic_Rating... ratings) {
        basic_table.insertAll(ratings);
    }

    /**
     *  Inserts a {@link SMC_Rating} object into the SMC rating table.
     * @param ratings One or more {@link SMC_Rating} objects that each define a complete row in the
     *                table.
     */
    public void insert(SMC_Rating... ratings) {
        smc_table.insertAll(ratings);
    }

    /**
     * Retrieves a {@link Basic_Rating} object from the database. Each object represents an entire
     * row int he table. The object is chosen based on the value in its' `depend_status` column.
     * @param status The unique dependency status used to filter which object to return. This value
     *               is a string. All of the value values are in the {@link DependStatus} enumeration.
     * @return A {@link Basic_Rating} object that defines an entire row in the basic ratings table.
     */
    public Basic_Rating get_basic_rating(DependStatus status) {
        String sStatus = status.getStatus();
        return basic_table.findAllCompsForDepStatus(sStatus);
    }

    /**
     * Retrieves a {@link SMC_Rating} object that represents an entire row in the SMC table. The
     * row return is chosen based on the unique value in its' `depend_status` column.
     * @param status The unique dependency status assigned to the row.
     * @return A {@link SMC_Rating} object representing a row of the SMC_Rating table.
     */
    public SMC_Rating get_smc_rating(DependStatus status) {
        String sStatus = status.getStatus();
        return smc_table.findAllCompsForDepStatus(sStatus);
    }

    // TODO: use a type instead of string for 2nd param for compile time checking
    /**
     * Returns a Double number representing the compensation received based on the given
     * dependency status and basic disability rating.
     * @param status The dependency status of the type {@link DependStatus}.
     * @param rating A String representing the basic disability rating.
     * @return The basic compensation a veteran receives based on dependency status and disability
     * rating
     */
    public Double get_basic_compensation(DependStatus status, @NonNull String rating) {
        Basic_Rating rating_row = this.get_basic_rating(status);
        Double compensation;
        switch (rating) {
            case RATING_10:
                compensation = rating_row.rating_10;
                break;
            case RATING_20:
                compensation = rating_row.rating_20;
                break;
            case RATING_30:
                compensation = rating_row.rating_30;
                break;
            case RATING_40:
                compensation = rating_row.rating_40;
                break;
            case RATING_50:
                compensation = rating_row.rating_50;
                break;
            case RATING_60:
                compensation = rating_row.rating_60;
                break;
            case RATING_70:
                compensation = rating_row.rating_70;
                break;
            case RATING_80:
                compensation = rating_row.rating_80;
                break;
            case RATING_90:
                compensation = rating_row.rating_90;
                break;
            case RATING_100:
                compensation = rating_row.rating_100;
                break;
            default:
                throw new IllegalArgumentException("Invalid basic disability rating value. Must provide a rating defined in the VAColumns.BasicColumns class.");
        }
        return compensation;
    }

    // TODO: use a type instead of string for 2nd param for compile time checking
    /**
     * Returns a Double representing the compensation received for a given dependency status
     * and Special Monthly Compensation disability rating.
     * @param status A {@link DependStatus} representing the dependency status.
     * @param rating A String representing the SMC rating.
     * @return
     */
    public Double get_smc_compensation(DependStatus status, @NonNull String rating) {
        SMC_Rating rating_row = this.get_smc_rating(status);
        Double compensation;
        switch (rating) {
            case SMC_L:
                compensation = rating_row.smc_l;
                break;
            case SMC_L_1_2:
                compensation = rating_row.smc_l_1_2;
                break;
            case SMC_M:
                compensation = rating_row.smc_m;
                break;
            case SMC_M_1_2:
                compensation = rating_row.smc_m_1_2;
                break;
            case SMC_N:
                compensation = rating_row.smc_n;
                break;
            case SMC_N_1_2:
                compensation = rating_row.smc_n_1_2;
                break;
            case SMC_O_P:
                compensation = rating_row.smc_o_p;
                break;
            case SMC_R_1:
                compensation = rating_row.smc_r_1;
                break;
            case SMC_R_2:
                compensation = rating_row.smc_r_2;
                break;
            case SMC_S:
                compensation = rating_row.smc_s;
                break;
            default:
                throw new IllegalArgumentException("Invalid SMC disability rating value. Must provide a rating defined in the VAColumns.SMCColumns class.");
        }
        return compensation;
    }
    private List<Basic_Rating> get_all_basic_ratings() {
        return all_basic_ratings;
    }
    private List<SMC_Rating> get_all_smc_ratings() {
        return all_smc_ratings;
    }

    public LiveData<User> getUser() { return user; }
    public User getUserRecord() { return user_table.getUserRecord(); }

    public LiveData<List<Disability>> getDisabilities() { return disabilities; }
    public List<Disability> getDisabilityList(){ return disability_table.getDisabilityList(); }
    public Disability getDisabilityFromID(int id) {return disability_table.getDisabilityFromID(id); }

    public LiveData<List<BirthDefectChild>> getChildren_birth_defects() {return children_birth_defects; }

    public void insertUser(User user) {
        if(user_table.getAll().getValue().size() == 0) {
            user_table.insertAll(user);
        } else {
            throw new IllegalStateException("A user already exists in the database. There can only be one. Either delete the current user and insert a new one or update instead.");
        }
    }
    public void updateUser(User user) {
        user_table.updateUser(user);
    }
    public void deleteUser(User user) {
        user_table.delete(user);
    }
    public void deleteAllUsers(){user_table.deleteAll(); }
    public void clearUser(){ user_table.setToDefault(); }

    public void insertDisability(Disability... disabilities) {
        disability_table.insertAll(disabilities);
    }
    public void updateDisability(Disability... disabilities) {
        disability_table.updateDisability(disabilities);
    }
    public void deleteDisability(Disability disability) {
        disability_table.delete(disability);
    }
    public void deleteAllDisabilities(){
        disability_table.deleteAll();
    }

    public List<BirthDefectChild> getAllBirthDefects(){return birth_defect_table.getAllBirthDefects(); }

    public void insertChildWithBirthDefect(BirthDefectChild... children) {
        birth_defect_table.insertAll(children);
    }
    public void updateChildWithBirthDefect(BirthDefectChild... children) {
        birth_defect_table.updateBirthDefect(children);
    }
    public void deleteChildWithBirthDefect(BirthDefectChild child) {
        birth_defect_table.delete(child);
    }
    public BirthDefectChild getDefectFromID(int id){
        return birth_defect_table.getBirthDefectFromID(id);
    }
    public void deleteAllBirthDefects(){
        birth_defect_table.deleteAll();
    }
}
