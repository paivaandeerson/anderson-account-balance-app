package com.anderson.accountbalance.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uuid;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="created")
    private ZonedDateTime created;
    
    @Column(nullable = false)
    private boolean active = true;
}