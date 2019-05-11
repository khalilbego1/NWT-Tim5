package com.nwt.locationTransport.Entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "CITIES")
public class City {
    public City(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column (name = "NAME",nullable = false)
    @NotNull
    @NotBlank(message = "{city.name.notBlank}")
    @Size(min =2, max = 25, message = "{city.name.size}")
    private String name;

    @Valid
    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Destination> destinations = new HashSet<>();

    public City(String name, Region region) {
        this.name = name;
        this.region = region;
    }
}

