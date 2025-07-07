package com.prorider.interfaces.auth;

import com.prorider.entities.auth.VerificationToken;

public interface IVerificationToken {
    VerificationToken createToken(String storeName);
    Boolean authenticateTokenAccount(String token);

}
