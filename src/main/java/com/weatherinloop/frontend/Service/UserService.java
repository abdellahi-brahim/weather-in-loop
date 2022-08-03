package com.weatherinloop.frontend.Service;

import com.weatherinloop.frontend.Model.Favorites;
import com.weatherinloop.frontend.Model.User;
import com.weatherinloop.frontend.Repository.FavoritesRepository;
import com.weatherinloop.frontend.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoritesRepository favoritesRepository;

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

    public boolean addFavorite(String cityName){
        Favorites favorite = new Favorites();
        favorite.setCity(cityName);
        favorite.setUsers(userRepository.findByName());
        favorite = favoritesRepository.save(favorite);
        return favorite != null && favorite.getFavID() > 0;
    }

    public List<String> getFavorites(String username){
        return favoritesRepository.findFavoritesByUsername(username);
    }
}
