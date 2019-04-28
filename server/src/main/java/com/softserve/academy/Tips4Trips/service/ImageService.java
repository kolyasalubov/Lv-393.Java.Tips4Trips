package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;

import com.softserve.academy.Tips4Trips.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ImageService {

    private ImageRepository repository;

    @Autowired
    public ImageService(ImageRepository repository) {
        this.repository = repository;
    }

    public Image createImage(Image image) {
        return repository.save(image);
    }

    public void deleteImage(Long id) throws DataNotFoundException {
        repository.delete(getImageById(id));
    }

    public Image getImageById(Long id) throws DataNotFoundException {
        Optional<Image> image = repository.findById(id);
        image.orElseThrow(() -> new DataNotFoundException("Image doesn't " +
                "exist!"));
        return image.get();
    }
}
