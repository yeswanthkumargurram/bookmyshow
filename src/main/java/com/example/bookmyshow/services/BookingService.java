package com.example.bookmyshow.services;

import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.BookingRepository;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class BookingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private PriceCalculatorService priceCalculatorService;
    @Autowired
    private BookingRepository bookingRepository;
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, List<Long> seatIds, Long showId){
        //get the user with user id
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException();
        }
        User bookedBy = userOptional.get();

        //get the show with showid
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new RuntimeException();
        }
        Show show = optionalShow.get();

        //---start the transaction-----
        //get the seats with seatid
        List<ShowSeat> optionalShowSeat = showSeatRepository.findAllById(seatIds);
        for(ShowSeat seat : optionalShowSeat){
            if(!(seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) ||
            seat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                    Duration.between(seat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes() >15){
                throw new RuntimeException();
            }
        }
        //check the availability of seats
        //if no return error
        //if yes, mark the status of the seats as locked
        List<ShowSeat> savedShowSeat = new ArrayList<>();
        for(ShowSeat seat : optionalShowSeat){
            seat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeat.add(showSeatRepository.save(seat)); // we can use saveAll as well
        }
        //save the updated seat status in the DB
        //-----end the transaction------
        //return success
        //create corresponding booking object
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeatList(savedShowSeat);
        booking.setUser(bookedBy);
        booking.setBookedAt(new Date());
        booking.setShow(show);
        booking.setAmount(priceCalculatorService.calculatePrice(savedShowSeat, show));

        bookingRepository.save(booking);
        return booking;

        //to take the transaction in between the flow we may need more springboot knowledge that we can see in project module
        // as of now we are taking whole book ticket as one transaction with @Transactional annotaion
    }
}
