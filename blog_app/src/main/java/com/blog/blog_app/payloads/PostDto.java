package com.blog.blog_app.payloads;

import com.blog.blog_app.entities.Category;
import com.blog.blog_app.entities.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer postId;

    private String title;

    private String content;

    //No need
   private String imageName="default";
    private Date addedDate;

    private CategoryDto category;

    private UserDto user;
}
