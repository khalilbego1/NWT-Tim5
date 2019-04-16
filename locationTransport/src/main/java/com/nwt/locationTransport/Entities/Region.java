package com.nwt.locationTransport.Entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REGIONS")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "{city.name.notBlank}")
    @Size(min =2, max = 25, message = "{city.name.size}")
    @Column (name = "NAME",nullable = false)
    @NotNull
    private String name;

    @Valid
    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<>();

    public Region(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
