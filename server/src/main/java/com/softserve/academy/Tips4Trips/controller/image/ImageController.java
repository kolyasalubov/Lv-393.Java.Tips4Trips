package com.softserve.academy.Tips4Trips.controller.image;

import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.service.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImageController {

    @Autowired
    FileStorageService storageService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable Long id)
            throws FileIOException {
        Resource file = storageService.loadFile(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(file);
    }

}
