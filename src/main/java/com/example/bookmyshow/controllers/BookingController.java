package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.BookMovieRequestDto;
import com.example.bookmyshow.dtos.BookMovieResponseDto;
import com.example.bookmyshow.dtos.ResponseStatus;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;
    public BookMovieResponseDto bookMovie(BookMovieRequestDto requestDto){
        BookMovieResponseDto responseDto = new BookMovieResponseDto();
        Booking booking;
        try{
            booking = bookingService.bookMovie(requestDto.getUserId(),
                    requestDto.getShowSeatIds(),
                    requestDto.getShowId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setBookingId(booking.getId());
            responseDto.setAmount(booking.getAmount());
        }catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
