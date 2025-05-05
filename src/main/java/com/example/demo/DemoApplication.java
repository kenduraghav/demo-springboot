package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}



@RestController
@RequestMapping("/api/v1/users")
class UserControllerV1{
	
	@Operation(summary = "Get User (v1)", description = "Returns user as plain text")
	@GetMapping
	String getusers() {
		return "Users from V1";
	}
}



@RestController
@RequestMapping("/api/v2/users")
class UserControllerV2 {
	
	
	List<UserResponseDto> users = getUserResponseDtos();
	
	
	@GetMapping
	List<UserResponseDto> getUsers(){
		return  users;
		
		
	}
	
	
	private List<UserResponseDto> getUserResponseDtos() {
		return List.of(
				new UserResponseDto("user1", "user1@gmail.com"),
				new UserResponseDto("user2", "user2@gmail.com"),
				new UserResponseDto("user3", "user3@gmail.com"),
				new UserResponseDto("user4", "user4@gmail.com"),
				new UserResponseDto("user5", "user5@gmail.com")
				);
	}


	@PostMapping
	UserResponseDto saveUsers(@Valid @RequestBody UserRequestDto dto) {
		UserResponseDto userResponseDto = new UserResponseDto(dto.name(),dto.email());
		return userResponseDto;
	}
	
}

record UserRequestDto(
		@NotBlank 
		String name,
		
		@NotBlank
		@Email
		String email) {
	
}



record UserResponseDto(String name,String email) {}