package com.prorider.controllers.auth;

import com.prorider.DTOs.request.auth.UserAccRequest;
import com.prorider.DTOs.response.auth.LoginDTO;
import com.prorider.DTOs.update.auth.UserAccUpdate;
import com.prorider.entities.auth.UserAcc;
import com.prorider.services.auth.UserAccService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserAccController {

    private final UserAccService userAccService;

    public UserAccController(UserAccService userAccService) {
        this.userAccService = userAccService;
    }

    /// EndPoint for registration of Users
    @PostMapping("/")
    public Boolean register(@Valid @RequestBody UserAccRequest userAccRequest){
        return userAccService.register(userAccRequest);
    }

    /// EndPoint for registration of StoreUsers
    @PostMapping("/registerStoreUser")
    public UserAcc registerStoreUser(@Valid @RequestBody UserAccRequest userAccRequest){
        return userAccService.registerStoreUser(userAccRequest);
    }

    /// Login
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDTO userLogin){
        return userAccService.verify(userLogin);
    }

    /// Edit Profile
    @PostMapping("/profileEdit")
    public UserAcc editProfile(@Valid @RequestBody UserAccUpdate userAccUpdate){
        return userAccService.EditProfile(userAccUpdate);
    }
}
