package com.prorider.services.auth;

import com.prorider.entities.auth.UserAcc;
import com.prorider.entities.auth.UserAccPrincipal;
import com.prorider.interfaces.auth.IUserAcc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private IUserAcc iUserAcc;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAcc user = iUserAcc.findByUserName(username);

        if(user == null){
            throw  new UsernameNotFoundException("User not found");
        }

        return new UserAccPrincipal(user);
    }
}
