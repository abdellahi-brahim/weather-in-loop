package com.weatherinloop.frontend.Service;

import com.weatherinloop.frontend.Model.User;
import com.weatherinloop.frontend.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean createNewUser(User user) {
        boolean isSaved = false;
        // Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        // person.setRoles(role);
        user = userRepository.save(user);
        if (null != user && user.getUserID() > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
