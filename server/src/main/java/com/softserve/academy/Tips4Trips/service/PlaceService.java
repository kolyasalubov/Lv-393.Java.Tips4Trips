package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.City;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.entity.place.Place;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.repository.PlaceRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlaceService {

    private static final Logger logger = Logger.getLogger(PlaceService.class);

    PlaceRepository repository;
    private FileStorageService fileStorageService;

    @Autowired
    public PlaceService(PlaceRepository repository,
                        FileStorageService fileStorageService) {
        this.repository = repository;
        this.fileStorageService = fileStorageService;
    }

    public Place findById(Long id) {
        Optional<Place> place = repository.findById(id);
        if (place.isPresent()) {
            return place.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<Place> findByCity(City city) {
        return repository.findByCity(city);
    }

    public List<Place> findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    public Place update(Place place) {
        return repository.save(place);
    }

    public Place createImageForAccount(MultipartFile image, Long id)
            throws FileIOException {
        Place place = findById(id);
        if (place.getImage() != null) {
            throw new FileIOException("Place image already exists! Try " +
                    "updating it.");
        }
        Image newImage = fileStorageService.store(image);
        place.setImage(newImage);
        return update(place);
    }

    public void deletePlaceImage(Long id) throws FileIOException,
            DataNotFoundException {
        try {
            Place place = findById(id);
            Long imageId = place.getImage().getId();
            place.setImage(null);
            update(place);
            fileStorageService.deleteFile(imageId);
        } catch (NullPointerException e) {
            throw new DataNotFoundException("Image doesn't exist");
        }
    }

    public Place updatePlaceImage(Long id, MultipartFile newImage)
            throws FileIOException, DataNotFoundException {
        deletePlaceImage(id);
        Image image = fileStorageService.store(newImage);
        Place place = findById(id);
        place.setImage(image);
        return update(place);
    }
}
