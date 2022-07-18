package com.weatherinloop.frontend.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.weatherinloop.frontend.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

}
