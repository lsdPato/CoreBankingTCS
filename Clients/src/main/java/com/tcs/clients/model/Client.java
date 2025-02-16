package com.tcs.clients.model;

import com.tcs.clients.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.*;
import com.tcs.clients.enums.Gender;
import com.tcs.clients.enums.ClientStatus;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "clientes")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Client extends Person {
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;


}
