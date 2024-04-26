package com.blog.blog_app.controllers;

import com.blog.blog_app.entities.Category;
import com.blog.blog_app.payloads.ApiResponse;
import com.blog.blog_app.payloads.CategoryDto;
import com.blog.blog_app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory (@Valid @RequestBody CategoryDto categoryDto){
      CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
      return  new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory (@Valid @RequestBody CategoryDto categoryDto,@PathVariable("catId") Integer categoryId){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,categoryId);
        return  new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory (@PathVariable("catId") Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully !!",true), HttpStatus.OK);
    }
    //get
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory (@PathVariable("catId") Integer categoryId){
        CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
        return  new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }
    //get All
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories (){
      List<CategoryDto> categories = this.categoryService.getCategories();
    return ResponseEntity.ok(categories);
    }
}
