package com.nwt.locationTransport.Entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "DESTINATIONS")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    public Destination(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Destination() {
    }
}
