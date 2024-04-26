package com.blog.blog_app.controllers;

import com.blog.blog_app.payloads.ApiResponse;
import com.blog.blog_app.payloads.UserDto;
import com.blog.blog_app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //POST-create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //PUT - update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateuser(@RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
       UserDto updatedUser = this.userService.updateUser(userDto,uid);
       return ResponseEntity.ok(updatedUser);
    }


    //DELETE - delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true), HttpStatus.OK);
    }

    //GET - get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable("userId") Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

}
