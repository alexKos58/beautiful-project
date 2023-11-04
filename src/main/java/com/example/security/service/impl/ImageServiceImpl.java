package com.example.security.service.impl;

import com.example.security.domain.entity.Image;
import com.example.security.domain.repository.ImageRepository;
import com.example.security.domain.repository.ProductRepository;
import com.example.security.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    public int addImage(int id){
        Image image = new Image();
        image.setProduct(productRepository.findById(id).orElseThrow());
        imageRepository.save(image);
        return image.getId();
    }
}
