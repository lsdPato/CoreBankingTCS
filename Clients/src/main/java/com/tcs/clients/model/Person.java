package com.tcs.clients.model;


import com.tcs.clients.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "prospecto")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder

public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String ci;//cedula de identidad

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;


}
