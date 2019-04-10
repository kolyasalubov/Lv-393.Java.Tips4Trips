package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.HotelDTO;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelConverter implements Converter<Hotel, HotelDTO> {

    private HotelService hotelService;

    @Autowired
    public HotelConverter (HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @Override
    public HotelDTO convertToDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setDescription(hotel.getDescription());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setPosition(hotel.getPosition());
        hotelDTO.setPhotoPath(hotel.getPhotoPath());
        hotelDTO.setCityId(hotel.getCity().getId());
        hotelDTO.setMaximumPrice(hotel.getMaximumPrice());
        hotelDTO.setMinimumPrice(hotel.getMinimumPrice());
        return hotelDTO;
    }

    @Override
    public Hotel convertToEntity(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setPosition(hotelDTO.getPosition());
        hotel.setPhotoPath(hotelDTO.getPhotoPath());
        hotel.setMaximumPrice(hotelDTO.getMaximumPrice());
        hotel.setMinimumPrice(hotelDTO.getMinimumPrice());
        return hotel;
    }

}
