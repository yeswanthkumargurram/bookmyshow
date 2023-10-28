package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name ="seats")
public class Seat extends BaseModel{
    private String num;
    @ManyToOne
    private SeatType seatType;
    private int row;
    private int col;
}
