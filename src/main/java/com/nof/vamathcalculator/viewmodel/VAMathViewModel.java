/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.viewmodel;

import android.app.Application;
import android.os.DeadObjectException;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nof.vamathcalculator.VARates;
import com.nof.vamathcalculator.VAUtils;
import com.nof.vamathcalculator.data.AppRepository;
import com.nof.vamathcalculator.db.BirthDefectChild;
import com.nof.vamathcalculator.db.DependStatus;
import com.nof.vamathcalculator.db.Disability;
import com.nof.vamathcalculator.db.User;
import com.nof.vamathcalculator.db.VAColumns;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class VAMathViewModel extends AndroidViewModel {

    private AppRepository data;
    private LiveData<User> m_user;
    private LiveData<List<Disability>> m_disabilities;

    public VAMathViewModel(Application application) {
        super(application);
        data = new AppRepository(application);
    }

    public Double get_basic_compensation_from_status(DependStatus status, String rating) {
       return data.get_basic_compensation(status, rating);
    }
    public Double get_basic_compensation_from_status(DependStatus status, double rating){
        String string_rating = "";
        switch((int)rating) {
            case 0:
                return 0.0;
            case 10:
                string_rating = VAColumns.BasicColumns.RATING_10;
                break;
            case 20:
                string_rating = VAColumns.BasicColumns.RATING_20;
                break;
            case 30:
                string_rating = VAColumns.BasicColumns.RATING_30;
                break;
            case 40:
                string_rating = VAColumns.BasicColumns.RATING_40;
                break;
            case 50:
                string_rating = VAColumns.BasicColumns.RATING_50;
                break;
            case 60:
                string_rating = VAColumns.BasicColumns.RATING_60;
                break;
            case 70:
                string_rating = VAColumns.BasicColumns.RATING_70;
                break;
            case 80:
                string_rating = VAColumns.BasicColumns.RATING_80;
                break;
            case 90:
                string_rating = VAColumns.BasicColumns.RATING_90;
                break;
            case 100:
                string_rating = VAColumns.BasicColumns.RATING_100;
                break;
        }
        return this.get_basic_compensation_from_status(status, string_rating);
    }
    public Double get_smc_compensation_from_status(DependStatus status, String rating) {
        return data.get_smc_compensation(status, rating);
    }

    public Double get_combined_rating(){
        List<Disability> dList = data.getDisabilityList();

        double bilateral_rating = VAUtils.GetBilateralRating(
                VAUtils.GetBilateralDisabilitiesList(dList)
        );

        List<Double> ratings_list = new ArrayList<>();
        if(bilateral_rating > 0.0) ratings_list.add(bilateral_rating);

        for(Disability disability : VAUtils.GetNonBilateralDisabilitiesList(dList)){
            ratings_list.add(disability.rating);
        }

        Collections.sort(ratings_list, (o1, o2) -> {
            return Double.compare(o2, o1);
        });

        return VAUtils.GetFinalCombinedRating(ratings_list);

    }

    public DependStatus getDependStatus(){
        User user = data.getUserRecord();

        if(user.num_parents == null) user.num_parents = 0;
        if(user.num_Child == null) user.num_Child = 0;

        if(user.has_spouse == false
        && (user.num_parents == null || user.num_parents == 0)
        && (user.num_Child == null || user.num_Child == 0) ){
            return DependStatus.Alone_No_Depends;
        }
        if(user.has_spouse == false
                && (user.num_parents == 1)
                && (user.num_Child == null || user.num_Child == 0) ){
            return DependStatus.One_Parent;
        }
        if(user.has_spouse == false
                && (user.num_parents == 2)
                && (user.num_Child == null || user.num_Child == 0) ){
            return DependStatus.Two_Parents;
        }
        if(user.has_spouse == true
                && (user.num_parents == null || user.num_parents == 0)
                && (user.num_Child == null || user.num_Child == 0) ){
            return DependStatus.Spouse_No_Depends;
        }
        if(user.has_spouse == true
                && (user.num_parents == 1)
                && (user.num_Child == null || user.num_Child == 0) ){
            return DependStatus.Spouse_One_Parent;
        }
        if(user.has_spouse == true
                && (user.num_parents == 2)
                && (user.num_Child == null || user.num_Child == 0) ){
            return DependStatus.Spouse_Two_Parents;
        }



        if(user.has_spouse == false
                && (user.num_parents == null || user.num_parents == 0)
                && (user.num_Child > 0) ){
            return DependStatus.Child_Only;
        }
        if(user.has_spouse == true
                && (user.num_parents == null || user.num_parents == 0)
                && (user.num_Child > 0) ){
            return DependStatus.Child_and_Spouse;
        }
        if(user.has_spouse == true
                && (user.num_parents == 1)
                && (user.num_Child > 0) ){
            return DependStatus.Child_Spouse_One_Parent;
        }
        if(user.has_spouse == true
                && (user.num_parents == 2)
                && (user.num_Child > 0) ){
            return DependStatus.Child_Spouse_Two_Parents;
        }
        if(user.has_spouse == false
                && (user.num_parents == 1)
                && (user.num_Child > 0) ){
            return DependStatus.Child_One_Parent;
        }
        if(user.has_spouse == false
                && (user.num_parents == 2)
                && (user.num_Child > 0) ){
            return DependStatus.Child_Two_Parents;
        }

        Log.e("getDependStatus", "No matching status: returning DependStatus.Alone_No_Depends.");
        return DependStatus.Alone_No_Depends;
    }

    public double getFullCompensation(){
        double combined_rating = get_combined_rating();
        DependStatus status = getDependStatus();
        double compensation = this.get_basic_compensation_from_status(
                status,
                combined_rating
        );

        User user = data.getUserRecord();

        if(user.has_smc) {
            compensation = compensation +
                    this.get_smc_compensation_from_status(status, user.smc_rating);
        }
        if(user.num_child_birth_defect != null && user.num_child_birth_defect > 0){
            List<BirthDefectChild> bd_children = data.getAllBirthDefects();
            for(BirthDefectChild bd_child: bd_children) {
                if(bd_child.compensation != null) {
                    compensation = compensation + bd_child.compensation;
                } else {
                    Log.e("getFullCompensation", "A BirthDefectChild record is missing its compensation field. Null returned but expecting a double. Was the compensation value properly inserted when the record was created?");
                }
            }
        }

        if(combined_rating > 20.0){

            if(user.num_Child != null && user.num_Child > 1) {
                int extra_children = user.num_Child - 1;
                double addition_per_child
                        = this.get_basic_compensation_from_status(
                                DependStatus.Additional_Child,
                                combined_rating
                        );

                compensation = compensation + (extra_children * addition_per_child);
            }

            if(user.num_child_education != null && user.num_child_education > 0){
                double addition_per_education
                        = this.get_basic_compensation_from_status(
                                DependStatus.Child_Education,
                                combined_rating
                        );

                compensation = compensation + (user.num_child_education * addition_per_education);
            }

            if(user.has_aid) {
                compensation = compensation +
                        this.get_basic_compensation_from_status(
                                DependStatus.Spouse_Aid_Attendance,
                                combined_rating
                        );
            }

        }

        return compensation;
    }
    public LiveData<User> get_user(){
        if(data != null) { m_user = data.getUser(); }
        return m_user;
    }

    public User getUserRecord(){ return data.getUserRecord(); }

    public void update_user(User user) {
        if(user != null) {
            data.updateUser(user);
        } else {
            Log.e("VAMathViewModel", "A null reference was passed to the update_user() method.");
        }
    }

    public LiveData<List<Disability>> get_disabilities(){
        if(data != null) { m_disabilities = data.getDisabilities(); }
        else { Log.e("get_disabilities", "Reference to AppRepository is null!"); }
        return m_disabilities;
    }

    public List<Disability> get_disabilities_list(){
        if(data != null) { m_disabilities = data.getDisabilities(); }
        else { Log.e("get_disabilities", "Reference to AppRepository is null!"); }
        return m_disabilities.getValue();
    }

    public Disability get_disability_from_id(int id) {
        if(id < 0) {
            throw new IllegalArgumentException("An integer value of greater than or equal to 0 expected. Negative id provided.");
        }
        return data.getDisabilityFromID(id);
    }
    public void insert_disability(Disability disability){
        data.insertDisability(disability);
    }

    public void delete_disability(Disability disability){
        data.deleteDisability(disability);
    }

    public void update_disability(Disability... disabilities) { data.updateDisability(disabilities);}

    public LiveData<List<BirthDefectChild>> get_all_children_with_birth_defects(){
        return data.getChildren_birth_defects();
    }

    public void insert_birth_defect(BirthDefectChild... children_w_birth_defects){
        for(BirthDefectChild child : children_w_birth_defects) {
            // set the compensation value for each child
            if(child.is_spinafida){
                switch(child.level){
                    case 1:
                        child.compensation = 362.00;
                        break;
                    case 2:
                        child.compensation = 1231.00;
                        break;
                    case 3:
                        child.compensation = 2096.00;
                        break;
                }
            } else {
                switch(child.level){
                    case 1:
                        child.compensation = 169.00;
                        break;
                    case 2:
                        child.compensation = 362.00;
                        break;
                    case 3:
                        child.compensation = 1231.00;
                        break;
                    case 4:
                        child.compensation = 2096.00;
                        break;
                }
            }
        }
        data.insertChildWithBirthDefect(children_w_birth_defects);
    }

    public void delete_birth_defect(BirthDefectChild child_with_defect){
        data.deleteChildWithBirthDefect(child_with_defect);
        User user = data.getUserRecord();
        if(user.num_child_birth_defect != null && user.num_child_birth_defect > 0){
            user.num_child_birth_defect++;
            data.updateUser(user);
        }
    }

    public BirthDefectChild get_defect_from_id(int id) {
        return data.getDefectFromID(id);
    }

    public void update_child_with_birth_defect(BirthDefectChild child) {
        data.updateChildWithBirthDefect(child);
    }

    public void delete_all_user_records(){
        //this.delete_all_users();
        this.clear_user_data();
        this.delete_all_disabilities();
        this.delete_all_birth_defect();
    }
    public void delete_all_users(){
        data.deleteAllUsers();
    }
    public void clear_user_data(){
        data.clearUser();
    }
    public void delete_all_disabilities(){
        data.deleteAllDisabilities();
    }
    public void delete_all_birth_defect(){
        data.deleteAllBirthDefects();
    }
}
