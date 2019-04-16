package com.nwt.locationTransport.Entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TRANSPORTATIONS")
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name ="NAME",nullable = false)
    @NotBlank(message = "{city.name.notBlank}")
    @Size(min =2, max = 25, message = "{city.name.size}")
    @NotNull
    private String name;
    @Valid
    @ManyToOne
    @JoinColumn(name = "TRANSPORTATION_TYPE_ID")
    private TransportationType transportationType;

    public Transportation(String name, TransportationType transportationType) {
        this.name = name;
        this.transportationType = transportationType;
    }
}
