package com.blog.blog_app.services.impl;

import com.blog.blog_app.entities.Category;
import com.blog.blog_app.exceptions.ResourceNotFoundException;
import com.blog.blog_app.payloads.CategoryDto;
import com.blog.blog_app.repositories.CategoryRepo;
import com.blog.blog_app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCat = categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));
          cat.setCategoryTitle(categoryDto.getCategoryTitle());
          cat.setCategoryDescription(categoryDto.getCategoryDescription());
          Category updadetdcat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updadetdcat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
       Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));
       this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));

        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories =this.categoryRepo.findAll();
      List<CategoryDto> categoryDtos = categories.stream().map(category -> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
