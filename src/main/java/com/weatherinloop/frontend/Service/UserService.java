package com.weatherinloop.frontend.Service;

import com.weatherinloop.frontend.Model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.weatherinloop.frontend.Model.Forecast;
import com.weatherinloop.frontend.Model.Weather;
import com.weatherinloop.frontend.Proxy.WeatherProxy;
import com.weatherinloop.frontend.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean createNewUser(User user){
        boolean isSaved = false;
        //Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        //person.setRoles(role);
        user = userRepository.save(user);
        if (null != user && user.getUserID() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}
