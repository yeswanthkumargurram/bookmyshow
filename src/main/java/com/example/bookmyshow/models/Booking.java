package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity

public class Booking extends BaseModel{
    @Enumerated(EnumType.ORDINAL)//for creating table for enums with auto incremented sequence
    private BookingStatus bookingStatus;
    @ManyToMany
    private List<ShowSeat> showSeatList;//M:M bcoz in cancellation scenario Cancelled Booking still have show seat with cancelled status
    @ManyToOne
    private User user;
    private Date bookedAt;
    @ManyToOne
    private Show show;
    private int amount;
    @OneToMany
    private List<Payment> payments;

}
