package com.softserve.academy.Tips4Trips.controller.image;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.service.FileStorageService;
import com.softserve.academy.Tips4Trips.service.ImageService;
import com.sun.xml.internal.ws.api.pipe.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

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
