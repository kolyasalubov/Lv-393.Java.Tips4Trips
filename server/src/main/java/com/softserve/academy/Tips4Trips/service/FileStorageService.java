package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.Tips4TripsApplication;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorageService {

    private final String IMAGES_DIRECTORY = "/images";

    private Path rootLocation;

    private ImageService imageService;

    @Autowired
    public FileStorageService(ImageService imageService) {
        String path = Tips4TripsApplication.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        path = path.substring(1, path.lastIndexOf("/"));
        path = path.substring(0, path.lastIndexOf("/") + 1);
        rootLocation = Paths.get(path + IMAGES_DIRECTORY);
        this.imageService = imageService;
    }

    public void store(MultipartFile file) throws FileIOException {
        try {
            String fileName = file.getOriginalFilename();
            String filepath = rootLocation + file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get(filepath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileIOException("Failed to save file! ");
        }
    }

    public Resource loadFile(String filename) throws FileIOException {
        try {
            Path file = Paths.get(rootLocation + filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileIOException("File doesn't exist or is corrupt!");
            }
        } catch (MalformedURLException e) {
            throw new FileIOException("Wrong file path format!");
        }
    }

    public void deleteFile(String filename) throws FileIOException {
        try {
            Files.delete(rootLocation);
        } catch (IOException e) {
            throw new FileIOException("No such file!");
        }
    }

    public void init() throws FileIOException {
        try {
            if(Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new FileIOException("Could not initialize storage!");
        }
    }
}
