package com.weatherinloop.frontend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherinloop.frontend.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
