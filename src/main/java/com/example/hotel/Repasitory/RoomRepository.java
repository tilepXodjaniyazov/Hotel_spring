package com.example.hotel.Repasitory;

import com.example.hotel.Entiti.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
//    Page<Room> findAllByHotelId(Integer hotel_id, Pageable pageable);

    Page<Room> findAllByHotelId(Integer id,Pageable pageable);
}
