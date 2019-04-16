package com.nwt.locationTransport.Entities;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COUNTRIES")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "{country.name.notBlank}")
    @Size(min =2, max = 25, message = "{country.name.size}")
    @Column(name ="NAME",nullable = false)
    @NotNull
    private String name;
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Region> regions = new HashSet<>();

    public Country(String name) {
        this.name = name;
    }
}
