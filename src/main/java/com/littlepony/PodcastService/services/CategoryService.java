package com.littlepony.PodcastService.services;

import com.littlepony.PodcastService.models.dtos.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDTO findById(UUID id);
    List<CategoryDTO> findAll();
    CategoryDTO create(CategoryDTO dto);
    CategoryDTO update(CategoryDTO dto);
    void delete(UUID id);
    CategoryDTO findByName(String name);
}