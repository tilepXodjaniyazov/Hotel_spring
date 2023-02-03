package com.example.hotel.Controler;

import com.example.hotel.Entiti.Hotel;
import com.example.hotel.ModelDto.HotelDto;
import com.example.hotel.Repasitory.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HotelControler {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/hotel")
    public List<Hotel> getHotel() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList;
    }
    @GetMapping("/hotel/{id}")
    public Optional<Hotel> getHotel(@PathVariable Integer id) {
        Optional<Hotel> hotelList = hotelRepository.findById(id);
        return hotelList;
    }

    @PostMapping("/hotel")
    public String postHotel(@RequestBody HotelDto hotelDto) {
        hotelRepository.save(new Hotel(null,hotelDto.getName()));
        return "added";
    }

    @PutMapping("/hotel/{id}")
    public boolean putHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Hotel hotel = byId.get();
            hotel.setName(hotelDto.getName());
            return true;
        }
        return false;
    }
    @DeleteMapping("/hotel/{id}")
    public boolean delHotel(@PathVariable Integer id) {
        hotelRepository.deleteById(id);
        return true;
    }
}
