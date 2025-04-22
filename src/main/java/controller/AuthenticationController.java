package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDto;
import dto.UserRequest;
import dto.UserResponse;
import service.AuthenticationService;

@RestController
@RequestMapping

public class AuthenticationController {

	private AuthenticationService authenticationService;
	@PostMapping
	public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto){
		return ResponseEntity.ok(authenticationService.save(userDto));
	}
	
	public ResponseEntity<UserResponse> auth(@RequestBody UserRequest userRequest){
		return ResponseEntity.ok(authenticationService.auth(userRequest));
	}
}
