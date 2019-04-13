package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.details.HotelDetailsDTO;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelConverter implements Converter<Hotel, HotelDetailsDTO> {

    private HotelService hotelService;
    private CityConverter cityConverter;
    private PlaceConverter placeConverter;
    private CityService cityService;

    @Autowired
    public HotelConverter (HotelService hotelService,CityConverter cityConverter, PlaceConverter placeConverter,CityService cityService) {
        this.hotelService = hotelService;
        this.cityConverter = cityConverter;
        this.placeConverter = placeConverter;
        this.cityService = cityService;
    }


    @Override
    public HotelDetailsDTO convertToDTO(Hotel hotel) {
        HotelDetailsDTO hotelDetailsDTO = (HotelDetailsDTO)
                placeConverter.convertToInfoDTO(hotel);

        hotelDetailsDTO.setId(hotel.getId());
        hotelDetailsDTO.setName(hotel.getName());
        hotelDetailsDTO.setDescription(hotel.getDescription());
        hotelDetailsDTO.setAddress(hotel.getAddress());
        hotelDetailsDTO.setWorkingDays(hotel.getWorkingDays());
        hotelDetailsDTO.setWebSite(hotel.getWebSite());
        hotelDetailsDTO.setTelephone(hotel.getTelephone());
        hotelDetailsDTO.setType(hotel.getType());
        hotelDetailsDTO.setOpeningTime(hotel.getOpeningTime());
        hotelDetailsDTO.setClosingTime(hotel.getClosingTime());
        hotelDetailsDTO.setPhotoPath(hotel.getPhotoPath());
        hotelDetailsDTO.setPosition(hotel.getPosition());
        hotelDetailsDTO.setPhotoPath(hotel.getPhotoPath());
        hotelDetailsDTO.setCityDTO(cityConverter.convertToDTO(cityService.findById(hotel.getCity().getId())));
        hotelDetailsDTO.setMinimumPrice(hotel.getMinimumPrice());
        hotelDetailsDTO.setMaximumPrice(hotel.getMaximumPrice());
        return hotelDetailsDTO;
    }

    @Override
    public Hotel convertToEntity(HotelDetailsDTO hotelDetailsDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDetailsDTO.getId());
        hotel.setName(hotelDetailsDTO.getName());
        hotel.setDescription(hotelDetailsDTO.getDescription());
        hotel.setAddress(hotelDetailsDTO.getAddress());
        hotel.setPosition(hotelDetailsDTO.getPosition());
        hotel.setPhotoPath(hotelDetailsDTO.getPhotoPath());
        hotel.setCity(cityConverter.convertToEntity(hotelDetailsDTO.getCityDTO()));
        hotel.setWorkingDays(hotelDetailsDTO.getWorkingDays());
        hotel.setWebSite(hotelDetailsDTO.getWebSite());
        hotel.setType(hotelDetailsDTO.getType());
        hotel.setOpeningTime(hotelDetailsDTO.getOpeningTime());
        hotel.setClosingTime(hotelDetailsDTO.getClosingTime());
        hotel.setMinimumPrice(hotelDetailsDTO.getMinimumPrice());
        hotel.setMaximumPrice(hotelDetailsDTO.getMaximumPrice());
        return hotel;
    }

}
