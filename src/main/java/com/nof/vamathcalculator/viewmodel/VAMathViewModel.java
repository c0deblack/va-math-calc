/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nof.vamathcalculator.data.AppRepository;
import com.nof.vamathcalculator.db.BirthDefectChild;
import com.nof.vamathcalculator.db.DependStatus;
import com.nof.vamathcalculator.db.Disability;
import com.nof.vamathcalculator.db.User;

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
    public Double get_smc_compensation_from_status(DependStatus status, String rating) {
        return data.get_smc_compensation(status, rating);
    }

    public LiveData<User> get_user(){
        if(data != null) { m_user = data.getUser(); }
        return m_user;
    }

    public void update_user(User user) {
        if(user != null) {
            data.updateUser(user);
        } else {
            Log.e("VAMathViewModel", "A null reference was passed to the update_user() method.");
        }
    }

    public LiveData<List<Disability>> get_disabilities(){
        if(data != null) { m_disabilities = data.getDisabilities(); }
        return m_disabilities;
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
        data.insertChildWithBirthDefect(children_w_birth_defects);
    }

    public void delete_birth_defect(BirthDefectChild child_with_defect){
        data.deleteChildWithBirthDefect(child_with_defect);
    }

    public BirthDefectChild get_defect_from_id(int id) {
        return data.getDefectFromID(id);
    }

    public void update_child_with_birth_defect(BirthDefectChild child) {
        data.updateChildWithBirthDefect(child);
    }

}
