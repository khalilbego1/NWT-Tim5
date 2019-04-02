package com.nwt.locationTransport.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TRANSPORTATIONS")
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="NAME",nullable = false)
    @NotNull
    private String name;
    @ManyToOne
    @JoinColumn(name = "TRANSPORTATION_TYPE_ID")
    private TransportationType transportationType;

    public Transportation(String name, TransportationType transportationType) {
        this.name = name;
        this.transportationType = transportationType;
    }
}
