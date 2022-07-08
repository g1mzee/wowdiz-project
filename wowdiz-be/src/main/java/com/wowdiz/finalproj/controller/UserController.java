package com.wowdiz.finalproj.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wowdiz.finalproj.dto.UserDto;
import com.wowdiz.finalproj.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserDto> signup(@Valid @RequestBody UserDto userDto){
		return ResponseEntity.ok(userService.signup(userDto));
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<UserDto> getMyUserInfo(){
		return ResponseEntity.ok(userService.getUserWithAuthorities().get());
	}
	
	@GetMapping("/user/{user_email}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<UserDto> getMyUserInfo(@PathVariable String user_email){
		System.out.println(user_email);
		return ResponseEntity.ok(userService.getUserWithAuthorities(user_email).get());
	}
		
}
