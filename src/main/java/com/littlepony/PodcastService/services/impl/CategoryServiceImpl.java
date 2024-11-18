package com.littlepony.PodcastService.services.impl;

import com.littlepony.PodcastService.models.dtos.CategoryDTO;
import com.littlepony.PodcastService.models.entities.Category;
import com.littlepony.PodcastService.repositories.CategoryRepository;
import com.littlepony.PodcastService.services.CategoryService;
import com.littlepony.PodcastService.utils.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Mapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDTO findById(UUID id) {
        return mapper.convertCategoryToCategoryDto(
                categoryRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found"))
        );
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(mapper::convertCategoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category category = mapper.convertCategoryDtoToCategory(dto);
        return mapper.convertCategoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO update(CategoryDTO dto) {
        if (!categoryRepository.existsById(dto.getId())) {
            throw new EntityNotFoundException("Category not found");
        }
        Category category = mapper.convertCategoryDtoToCategory(dto);
        return mapper.convertCategoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO findByName(String name) {
        return mapper.convertCategoryToCategoryDto(
                categoryRepository.findByName(name)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found"))
        );
    }
}
