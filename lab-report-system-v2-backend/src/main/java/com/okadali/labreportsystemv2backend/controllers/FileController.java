package com.okadali.labreportsystemv2backend.controllers;

import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private StorageService service;

    @PostMapping
    public ResponseEntity<ResponseData> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        return service.uploadImage(file);
    }

    @PutMapping("/{codeName}")
    public ResponseEntity<?> updateImage(@RequestParam("image")MultipartFile file,@PathVariable String codeName) throws IOException {
        return service.updateImage(file,codeName);
    }

    @GetMapping("/{codeName}")
    public ResponseEntity<?> downloadImage(@PathVariable String codeName) {
        return service.downloadImage(codeName);
    }
}
