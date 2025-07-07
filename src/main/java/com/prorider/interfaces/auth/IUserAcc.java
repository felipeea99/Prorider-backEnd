package com.prorider.interfaces.auth;

import com.prorider.entities.auth.UserAcc;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserAcc{
UserAcc findByUserName(String username);
UserAcc findByUserId(UUID userId);
}
