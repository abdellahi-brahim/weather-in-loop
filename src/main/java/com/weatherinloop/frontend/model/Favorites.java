package com.weatherinloop.frontend.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Favorites")
public class Favorites {

    @Id
    @Column(name = "favID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favID;

    @Column(name = "city")
    private String city;

    @ManyToMany(mappedBy = "favorites", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<User> users = new HashSet<>();

}
