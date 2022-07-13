package com.weatherinloop.frontend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


import lombok.Data;


@Data
@Entity
@Table(name= "User")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)    
    @Column(name = "userID")
    private int userID;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_favs",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "userID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "fav_id", referencedColumnName = "favID")})
    private Set<Favorites> favorites = new HashSet<>();
}
