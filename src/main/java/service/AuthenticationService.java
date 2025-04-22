package service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import dto.UserDto;
import dto.UserRequest;
import dto.UserResponse;
import entity.User;
import enums.Role;
import lombok.RequiredArgsConstructor;
import repository.IUserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final IUserRepository userRepository = null;
	
	private final JwtService jwtService = new JwtService();
	private final AuthenticationManager authenticationManager = null;
	
    public UserResponse save(UserDto userDto) {
    	User user = User.builder()
    		    .username(userDto.getUsername())
    		    .password(userDto.getPassword())
    		    .nameSurname(userDto.getNameSurname())
    		    .role(Role.User)
    		    .build();

    	userRepository.save(user);
    	
    	var token = jwtService.generateToken(user);
    	
    	return UserResponse.builder().token(token).build();
    }

	public UserResponse auth(UserRequest userRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername, userRequest.getPassword));
		User user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
		String token= jwtService.generateToken(user);
		return UserResponse.builder().token(token).build();
				
	}
    
    
    
}
