package com.example.hotel.ModelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.ref.PhantomReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Integer number;
    private Integer floor;
    private double size;
}
