package com.luv2code.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

}
