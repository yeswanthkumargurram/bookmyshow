package com.example.bookmyshow.services;

import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.ShowSeatType;
import com.example.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceCalculatorService {

    ShowSeatTypeRepository showSeatTypeRepository;
    public int calculatePrice(List<ShowSeat> showSeatList, Show show){
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllBy(show);
        int amount =0;
        for(ShowSeat showSeat :showSeatList){
            for(ShowSeatType seatType1 : showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(seatType1.getSeatType())){
                    amount += seatType1.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
