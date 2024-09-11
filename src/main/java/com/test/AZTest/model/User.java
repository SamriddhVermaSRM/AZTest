package com.test.AZTest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "test")
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    @Column(name = "total_visits")
    private int totalVisits;
    @Getter
    @Setter
    @Column(name = "unique_visits")
    private int uniqueVisits;
    @Getter
    @Setter
    private String visits;
}
