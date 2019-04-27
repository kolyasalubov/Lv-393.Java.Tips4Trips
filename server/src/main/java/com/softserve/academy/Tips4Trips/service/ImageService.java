package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.Tips4TripsApplication;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.exception.FileIOException;

import com.softserve.academy.Tips4Trips.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ImageService {

    private ImageRepository repository;

    @Autowired
    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    private Image createImage(Image image) {
        return repository.save(image);
    }

    private void deleteImage(Long id) throws NoSuchElementException {
        repository.delete(getImageById(id));
    }

    private Image getImageById(Long id) throws NoSuchElementException {
        Optional<Image> image = repository.findById(id);
        image.orElseThrow(() -> new NoSuchElementException("Image doesn't " +
                "exist!"));
        return image.get();
    }
}
