package com.blog.blog_app.services;

import com.blog.blog_app.entities.Post;
import com.blog.blog_app.payloads.PostDto;
import com.blog.blog_app.payloads.PostResponse;
import jakarta.persistence.metamodel.ListAttribute;

import java.util.List;

public interface PostService {

    //create
     PostDto createPost (PostDto postDto,Integer userId,Integer categoryId);
     //update
     PostDto updatePost(PostDto postDto,Integer postId);

     //delete
    void deletePost(Integer postId);

    //get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get single post
    PostDto getPostById(Integer postId);

    //get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    //search posts
    List<PostDto> searchPosts(String keyword);

}
