package com.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private ResponseStatus responseStatus;
    private int amount;
    private long bookingId;

}
