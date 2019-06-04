package com.nwt.locationTransport.Entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "TRANSPORTATION_TYPES")
public class TransportationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="NAME",nullable = false)
    @NotNull
    private String name;

    public TransportationType(String name) {
        this.name = name;
    }
    public TransportationType() {
        this.name = name;
    }
}
