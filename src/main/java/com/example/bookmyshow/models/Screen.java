package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;
    @OneToMany
    private List<Seat> seatList;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection // spring will create mapping table when enum is in collection
    private List<Feature> featureList;
}
