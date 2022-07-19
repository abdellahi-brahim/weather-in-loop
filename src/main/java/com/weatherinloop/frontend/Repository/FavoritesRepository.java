package com.weatherinloop.frontend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherinloop.frontend.Model.Favorites;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {

}
