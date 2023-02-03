package com.example.hotel.Controler;

import com.example.hotel.Entiti.Hotel;
import com.example.hotel.Entiti.Room;
import com.example.hotel.ModelDto.RoomDto;
import com.example.hotel.Repasitory.HotelRepository;
import com.example.hotel.Repasitory.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomControler {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;
    @GetMapping("/room")
    public List<Room> getAll() {
        List<Room> all = roomRepository.findAll();
        return all;
    }
    @GetMapping("/room/{id}")
    public Page<Room> getByHotelId(@PathVariable Integer id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<Room> allByHotelId = roomRepository.findAllByHotelId(id, pageable);
        return allByHotelId;
    }
    @PostMapping("/room/{id}")
    public boolean addRoom(@RequestBody RoomDto roomDto, @PathVariable Integer id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Hotel hotel = byId.get();
            roomRepository.save(new Room(null,roomDto.getNumber(),roomDto.getFloor(),roomDto.getSize(),hotel));
            return true;
        }
        return false;
    }
    @PutMapping("/room/{id}")
    public boolean putRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> byId = roomRepository.findById(id);
        Optional<Hotel> byId1 = hotelRepository.findById(id);
        if (byId.isPresent() && byId1.isPresent()) {
            Hotel hotel = byId1.get();
            Room room = byId.get();
            hotelRepository.save(hotel);
            room.setHotel(hotel);
            room.setNumber(roomDto.getNumber());
            room.setSize(roomDto.getSize());
            room.setFloor(roomDto.getFloor());
            roomRepository.save(room);
            return true;
        }
        return false;
    }
    @DeleteMapping("/room/{id}")
    public boolean gelRoom(@PathVariable Integer id) {
        roomRepository.deleteById(id);
        return true;
    }
}
