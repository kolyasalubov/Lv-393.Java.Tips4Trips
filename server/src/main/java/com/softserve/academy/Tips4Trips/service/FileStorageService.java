package com.softserve.academy.Tips4Trips.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;

import com.softserve.academy.Tips4Trips.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.Date;

@Service
public class FileStorageService {

    private final String BUCKET_NAME = "images-t4t";
    private final String BUCKET_URL = "https://s3.us-east-2.amazonaws.com/images-t4t/";
    private final BasicAWSCredentials awsCredentials = new BasicAWSCredentials("accessKey",
            "secretKey");
    private final AmazonS3 s3 = AmazonS3Client.builder()
            .withRegion("us-east-2")
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();

    private final String DEFAULT_IMAGE = "default_image.png";

    private ImageService imageService;
    private AccountRepository accountRepository;
    private UserRepository userRepository;

    @Autowired
    public FileStorageService(ImageService imageService,
                              AccountRepository accountRepository,
                              UserRepository userRepository) {
        this.imageService = imageService;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Image store(MultipartFile file) throws FileIOException {
        try {
            String fileName = file.getOriginalFilename();
            Image image = createImage(fileName, getImageCreator());
            String filepath = image.getId() + image.getFormat();
            s3.putObject(BUCKET_NAME, filepath, file.getInputStream(), null);
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
            Resource resource = new UrlResource(BUCKET_URL + path);
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
            s3.deleteObject(BUCKET_NAME, imageName);
            imageService.deleteImage(id);
        } catch (AmazonServiceException e) {
            throw new FileIOException("No such file!");
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
//@minato7878
    private Account getImageCreator() {
        return accountRepository.findById(1L).get();
//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.
//                getContext().getAuthentication().getPrincipal();
//        return accountRepository.findById(userDetails.getId()).get();
    }
}
