package com.weatherinloop.frontend.Repository;

import com.weatherinloop.frontend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weatherinloop.frontend.Model.Favorites;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {
    @Query(value = "SELECT city FROM favorites WHERE favid IN(SELECT fav_id FROM user_favs WHERE user_id = (SELECT userid FROM user WHERE name = ?1))", nativeQuery = true)
    List<String> findFavoritesByUsername(String username);
}
