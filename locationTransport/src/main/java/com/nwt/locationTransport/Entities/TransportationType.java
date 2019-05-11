package com.nwt.locationTransport.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TRANSPORTATION_TYPES")
public class TransportationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="NAME",nullable = false)
    @NotNull
    private String name;
    @OneToMany(mappedBy = "transportationType",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transportation> transportationSet = new HashSet<>();

    public TransportationType(String name) {
        this.name = name;
    }
    public TransportationType() {
        this.name = name;
    }
}
