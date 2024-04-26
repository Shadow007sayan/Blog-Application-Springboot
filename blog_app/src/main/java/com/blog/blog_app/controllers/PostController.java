package com.blog.blog_app.controllers;

import com.blog.blog_app.config.AppConstants;
import com.blog.blog_app.payloads.ApiResponse;
import com.blog.blog_app.payloads.PostDto;
import com.blog.blog_app.payloads.PostResponse;
import com.blog.blog_app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;

    //create

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ){
        PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
        return  new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    //get posts by user

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
    {
        List<PostDto> postDtos =this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }


    //get posts by user

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> postDtos =this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    //get All Posts

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value="pageNumber",defaultValue= AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy" ,defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue =  AppConstants.SORT_DIR,required = false) String sortDir

    ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    //get Post details by Id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return  new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost((postId));
        return new ApiResponse("Post is successfully deleted !!",true);
    }

    //Update Post

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId){
          PostDto updatedPost = this.postService.updatePost(postDto,postId);
          return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    //Search post
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
            @PathVariable("keywords") String keywords
    ){
       List<PostDto> result = this.postService.searchPosts(keywords);
       return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }
}
