package com.blog.blog_app.services.impl;

import com.blog.blog_app.entities.Category;
import com.blog.blog_app.entities.Post;
import com.blog.blog_app.entities.User;
import com.blog.blog_app.exceptions.ResourceNotFoundException;
import com.blog.blog_app.payloads.PostDto;
import com.blog.blog_app.payloads.PostResponse;
import com.blog.blog_app.repositories.CategoryRepo;
import com.blog.blog_app.repositories.PostRepo;
import com.blog.blog_app.repositories.UserRepo;
import com.blog.blog_app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","User Id",userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        //postDto only have title and content field so other parameters of post need to be set
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getTitle());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
      Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Post Id",postId));
      this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }
        else {
           sort= Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePosts = this.postRepo.findAll(p);
        List<Post> allPosts =pagePosts.getContent();
        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());  //give long type so we change in postResponse int to long
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
      Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Post Id",postId));

      return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
       Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId));
       List<Post> posts = this.postRepo.findAllByCategory(cat);
       List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
       return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","user id",userId));
        List<Post> posts = this.postRepo.findAllByUser(user);
        List<PostDto> userDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
