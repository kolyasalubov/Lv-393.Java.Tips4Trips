package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.HotelController;
import com.softserve.academy.Tips4Trips.controller.RestaurantController;
import com.softserve.academy.Tips4Trips.dto.details.HotelDetailsDTO;
import com.softserve.academy.Tips4Trips.entity.place.Hotel;
import com.softserve.academy.Tips4Trips.service.CityService;
import com.softserve.academy.Tips4Trips.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Component
public class HotelConverter implements Converter<Hotel, HotelDetailsDTO> {


    private final int MAX_DESCRIPTION_LENGTH = 100;
    private final CityConverter cityConverter;
    private final CityService cityService;
    private final ImageConverter imageConverter;
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());


    @Autowired
    public HotelConverter(ImageConverter imageConverter,
                          CityConverter cityConverter,
                          CityService cityService) {
        this.cityConverter = cityConverter;
        this.cityService = cityService;
        this.imageConverter = imageConverter;
    }


    @Override
    public HotelDetailsDTO convertToDTO(Hotel hotel) {
        HotelDetailsDTO hotelDetailsDTO = new HotelDetailsDTO();
        hotelDetailsDTO.setId(hotel.getId());
        hotelDetailsDTO.setName(hotel.getName());
        hotelDetailsDTO.setDescription(hotel.getDescription());
        hotelDetailsDTO.setSelf(ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(HotelController.class)
                        .getById(hotel.getId())
        ).withSelfRel().getHref().replace("{countryId}", hotel.getCity().getCountry().getId().toString())
                .replace("{cityId}", hotel.getCity().getId().toString()));
        hotelDetailsDTO.setAddress(hotel.getAddress());
        hotelDetailsDTO.setWorkingDays(hotel.getWorkingDays());
        hotelDetailsDTO.setWebSite(hotel.getWebSite());
        hotelDetailsDTO.setTelephone(hotel.getTelephone());
        hotelDetailsDTO.setOpeningTime(formatter.format(hotel.getOpeningTime()));
        hotelDetailsDTO.setClosingTime(formatter.format(hotel.getClosingTime()));
        hotelDetailsDTO.setImage(imageConverter.convertToDTO(hotel.getImage()));
        hotelDetailsDTO.setPosition(hotel.getPosition());
        hotelDetailsDTO.setCityDTO(cityConverter.convertToDTO(cityService.findById(hotel.getCity().getId())));
        hotelDetailsDTO.setMinimumPrice(hotel.getMinimumPrice());
        hotelDetailsDTO.setMaximumPrice(hotel.getMaximumPrice());
        hotelDetailsDTO.setCategory(hotel.getCategory());
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
        hotel.setImage(imageConverter
                .convertToEntity(hotelDetailsDTO.getImage()));
        hotel.setCity(cityConverter
                .convertToEntity(hotelDetailsDTO.getCityDTO()));
        hotel.setWorkingDays(hotelDetailsDTO.getWorkingDays());
        hotel.setWebSite(hotelDetailsDTO.getWebSite());
        hotel.setTelephone(hotelDetailsDTO.getTelephone());
        try {
            hotel.setOpeningTime(formatter.parse(hotelDetailsDTO.getOpeningTime()));
            hotel.setClosingTime(formatter.parse(hotelDetailsDTO.getClosingTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hotel.setMinimumPrice(hotelDetailsDTO.getMinimumPrice());
        hotel.setMaximumPrice(hotelDetailsDTO.getMaximumPrice());
        return hotel;
    }

}
