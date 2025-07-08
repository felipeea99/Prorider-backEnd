package com.prorider.services.auth;

import com.prorider.DTOs.request.auth.UserAccRequest;
import com.prorider.DTOs.response.auth.LoginDTO;
import com.prorider.DTOs.response.auth.UserResponse;
import com.prorider.entities.auth.UserAcc;
import com.prorider.entities.auth.UserAccPrincipal;
import com.prorider.exceptions.BadRequestException;
import com.prorider.exceptions.UnauthorizedException;
import com.prorider.interfaces.auth.IUserAcc;
import com.prorider.repository.auth.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserAccService implements IUserAcc {

    private final JWTService jwtService;
    private final AuthenticationManager authManager;
    private final UserRepository userAccRepository;

    //Password encryption object and the strength is the rounds hashed
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserAccService(JWTService jwtService, AuthenticationManager authManager, UserRepository userAccRepository) {
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.userAccRepository = userAccRepository;
    }

    /// User-Store Creation, returns the object saved to be available to used on the StoreService on "createStore" method
    @Transactional
    public UserAcc registerStoreUser(UserAccRequest userAccRequest){
        /// Email Validation, checks if exist or not
        String email = userAccRequest.getEmail().toLowerCase();
        UserAcc existingUser = userAccRepository.findByUserName(email);
        if (existingUser != null) {
            throw new BadRequestException("Email already exists");
        }
        ///Set values to lowe case
        userAccRequest.setEmail(userAccRequest.getEmail().toLowerCase());
        userAccRequest.setName(userAccRequest.getName().toLowerCase());
        userAccRequest.setUserLastName1(userAccRequest.getUserLastName1().toLowerCase());
        userAccRequest.setUserLastName2(userAccRequest.getUserLastName2().toLowerCase());
        userAccRequest.setUserName(userAccRequest.getEmail());// sets the userName to the value of email

        ///password encryption
        userAccRequest.setPassword(encoder.encode(userAccRequest.getPassword()));
        UserAcc userAcc = ToUserObject(userAccRequest);
        return userAccRepository.save(userAcc);
    }

    /// User Creation, receives a storeName from the route, then it creates a customer object on the database (customerId, User, store)
    @Transactional
    public Boolean register(UserAccRequest userAccRequest) {
        /// Email Validation, checks if exist or not
        String email = userAccRequest.getEmail().toLowerCase();
        UserAcc existingUser = userAccRepository.findByUserName(email);
        if (existingUser != null) {
            throw new BadRequestException("Email already exists");
        }

        ///Set values to lowe case
        userAccRequest.setEmail(userAccRequest.getEmail().toLowerCase());
        userAccRequest.setName(userAccRequest.getName().toLowerCase());
        userAccRequest.setUserLastName1(userAccRequest.getUserLastName1().toLowerCase());
        userAccRequest.setUserLastName2(userAccRequest.getUserLastName2().toLowerCase());
        userAccRequest.setUserName(userAccRequest.getEmail());// sets the userName to the value of email

        ///password encryption & Save
        userAccRequest.setPassword(encoder.encode(userAccRequest.getPassword()));
        UserAcc userAcc = ToUserObject(userAccRequest);
        userAccRepository.save(userAcc);

        return true;
    }


    public String verify(LoginDTO user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUserName());
        }
        return "Fail";
    }

    public UUID authenticateUserAccess(UUID resourceOwnerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserAccPrincipal userPrincipal) {
            UUID loggedUserId = userPrincipal.getUserId();
            String role = userPrincipal.getRole();

            if (loggedUserId.equals(resourceOwnerId)) {
                /// the userId matches, so it gets the userId
                return loggedUserId;
            }

            if ("ADMIN".equalsIgnoreCase(role)) {
                /// It has admin role so it gets the UserId
                return loggedUserId;
            }

            // userId does not match and role is NOT ADMIN
            throw new UnauthorizedException("You cant do this action");
        }

        throw new UnauthorizedException("Invalid user");
    }

    public UserResponse ToUserResponse(UserAcc userAcc){
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userAcc.getUserId());
        userResponse.setUserName(userAcc.getUserName());
        userResponse.setName(userAcc.getName());
        userResponse.setUserLastName1(userAcc.getUserLastName1());
        userResponse.setUserLastName2(userAcc.getUserLastName2());
        userResponse.setPhoneNumber(userAcc.getPhoneNumber());
        userResponse.setEmail(userAcc.getEmail());
        return userResponse;
    }

    public UserAcc ToUserObject(UserAccRequest userAccRequest){
        UserAcc userAcc = new UserAcc();
        userAcc.setUserName(userAccRequest.getUserName());
        userAcc.setName(userAccRequest.getName());
        userAcc.setUserLastName1(userAccRequest.getUserLastName1());
        userAcc.setUserLastName2(userAccRequest.getUserLastName2());
        userAcc.setPhoneNumber(userAccRequest.getPhoneNumber());
        userAcc.setEmail(userAccRequest.getEmail());
        userAcc.setPassword(userAccRequest.getPassword());
        return userAcc;
    }

    @Override
    public UserAcc findByUserName(String username) {
        return userAccRepository.findByUserName(username);
    }

    @Override
    public UserAcc findByUserId(UUID userId) {
       return userAccRepository.findByUserId(userId);
    }
}
