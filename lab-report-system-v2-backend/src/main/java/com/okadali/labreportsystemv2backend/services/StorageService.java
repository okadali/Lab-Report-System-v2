package com.okadali.labreportsystemv2backend.services;

import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.exceptions.FileCodeNotFoundException;
import com.okadali.labreportsystemv2backend.exceptions.InvalidBodyException;
import com.okadali.labreportsystemv2backend.models.ImageData;
import com.okadali.labreportsystemv2backend.repositories.StorageRepository;
import com.okadali.labreportsystemv2backend.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public class StorageService {
    @Autowired
    private StorageRepository repository;

    public ResponseEntity<ResponseData> uploadImage(MultipartFile file) throws IOException {
        checkProperFileFormat(file);

        String randomId = ImageUtils.createRandomId();
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .code(randomId)
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        ResponseData responseData = new ResponseData(true,"file uploaded successfully",imageData.getCode());

        return new ResponseEntity<>(responseData,HttpStatus.CREATED);
    }
    
    public ResponseEntity downloadImage(String codeName) {
        Optional<ImageData> dbImageData = repository.findByCode(codeName);

        if(!dbImageData.isPresent()) throw new FileCodeNotFoundException("Please provide a valid file code");

        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(dbImageData.get().getType())).body(images);
    }

    public ResponseEntity<ResponseData> updateImage(MultipartFile file, String codeName) throws IOException {
        checkProperFileFormat(file);

        Optional<ImageData> dbImageData = repository.findByCode(codeName);
        if(!dbImageData.isPresent()) {
            throw new FileCodeNotFoundException("There is no file with the code: "+codeName);
        }

        ImageData imageData = dbImageData.get();
        imageData.setImageData(ImageUtils.compressImage(file.getBytes()));
        imageData.setName(file.getName());
        imageData.setType(file.getContentType());
        repository.save(dbImageData.get());


        ResponseData responseData = new ResponseData(true,codeName+" updated successfully",null);
        ResponseEntity responseEntity = new ResponseEntity(responseData,HttpStatus.OK);
        return responseEntity;
    }

    private void checkProperFileFormat(MultipartFile file) {
        if(!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
            throw new InvalidBodyException("Only png or jpeg/jpg is acceptable as a file type");
        }
    }
}
