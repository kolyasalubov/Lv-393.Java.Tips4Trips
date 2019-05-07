package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.Tips4TripsApplication;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;

import com.softserve.academy.Tips4Trips.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class FileStorageService {

    private final String IMAGES_DIRECTORY = "images";
    private final String DEFAULT_IMAGE = "default_image.png";

    private Path rootLocation;

    private ImageService imageService;
    private AccountRepository accountRepository;
    private UserRepository userRepository;

    @Autowired
    public FileStorageService(ImageService imageService,
                              AccountRepository accountRepository,
                              UserRepository userRepository) {
        String path = Tips4TripsApplication.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        path = path.substring(1, path.lastIndexOf("/"));
        path = path.substring(0, path.lastIndexOf("/") + 1);
        rootLocation = Paths.get(path, IMAGES_DIRECTORY);
        this.imageService = imageService;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Image store(MultipartFile file) throws FileIOException {
        try {
            String fileName = file.getOriginalFilename();
            Image image = createImage(fileName, getImageCreator());
            String filepath = image.getId() + image.getFormat();
            Files.copy(file.getInputStream(), Paths.get(rootLocation.toString(),
                    filepath));
            return image;
        } catch (Exception e) {
            throw new FileIOException("Failed to save file!");
        }
    }

    public Resource loadFile(Long id) throws FileIOException {
        try {
            String path;
            try {
                Image image = imageService.getImageById(id);
                path = id + image.getFormat();
            } catch (DataNotFoundException e) {
                path = DEFAULT_IMAGE;
            }
            Path file = Paths.get(rootLocation.toString(), path);
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

    public void deleteFile(Long id)
            throws FileIOException, DataNotFoundException {
        try {
            String imageName = id + imageService.getImageById(id).getFormat();
            Files.delete(Paths.get(rootLocation.toString(), imageName));
            imageService.deleteImage(id);
        } catch (IOException e) {
            throw new FileIOException("No such file!");
        }
    }

    public void init() throws FileIOException {
        try {
            if(!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new FileIOException("Could not initialize storage!");
        }
    }

    private Image createImage(String fileName, Account creator)
            throws FileIOException {
        try {
            Image image = new Image();
            image.setName(fileName);
            image.setFormat(fileName.substring(fileName.lastIndexOf("."),
                    fileName.length()));
            image.setCreator(creator);
            image.setUploadDate(new Date());
            image = imageService.createImage(image);

            return image;
        } catch (NullPointerException e) {
            throw new FileIOException("Invalid file format!");
        }
    }

    private Account getImageCreator() {
        return accountRepository.findByUsersContains(userRepository
                .findByLogin("minato7878").get()).get();
        /*UserDetails userDetails = (UserDetails) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return accountRepository.findByUsersContains(userRepository
                .findByLogin(userDetails.getUsername()).get()).get();*/
    }
}
