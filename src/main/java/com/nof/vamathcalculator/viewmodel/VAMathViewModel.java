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
import com.nof.vamathcalculator.model.VAMathUser;

import java.util.List;

public class VAMathViewModel extends AndroidViewModel {

    private AppRepository data;
    private VAMathUser user;
    private LiveData<User> m_user;
    private LiveData<List<Disability>> m_disabilities;

    public VAMathViewModel(Application application) {
        super(application);
        data = new AppRepository(application);
        user = VAMathUser.getInstance();

        User savedUser = data.getUser().getValue();
        if(savedUser != null) {
            user.set_spouse(savedUser.has_spouse);
            user.set_children(savedUser.num_Child);
            user.set_parents(savedUser.num_parents);
            user.set_children_education(savedUser.num_child_education);
            user.set_aid(savedUser.has_aid);
            user.set_children_with_defect(savedUser.num_child_birth_defect);

            List<Disability> savedDisabilities = data.getDisabilities().getValue();
            if(savedDisabilities.size() > 0) {
                for(Disability disability : savedDisabilities) {
                    if (disability.is_basic) {
                        user.add_basic_disability(
                                disability.rating,
                                disability.is_bilateral,
                                disability.short_name);
                    }
                }
            }

            List<BirthDefectChild> birth_defect_children = data.getChildren_birth_defects().getValue();
            if(birth_defect_children.size() > 0){
                for(BirthDefectChild child : birth_defect_children){
                    user.add_child_birth_defect(child);
                }
            }
        }
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
}
