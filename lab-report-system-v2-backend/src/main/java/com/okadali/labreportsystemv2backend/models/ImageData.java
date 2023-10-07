package com.okadali.labreportsystemv2backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String code;
    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;
}
