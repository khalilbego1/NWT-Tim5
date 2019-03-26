package com.nwt.locationTransport.Entities;

import javax.persistence.*;
@Entity
@Table(name = "TRANSPORTATIONS")
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "TRANSPORTATION_TYPE_ID")
    private TransportationType transportationType;
}
