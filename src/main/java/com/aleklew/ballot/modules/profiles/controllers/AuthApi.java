package com.aleklew.ballot.modules.profiles.controllers;

import com.aleklew.ballot.modules.general.infrastructure.HelperMethods;
import com.aleklew.ballot.modules.general.interfaces.ISharedMailingService;
import com.aleklew.ballot.modules.general.services.SharedMailingService;
import com.aleklew.ballot.modules.profiles.dbmodels.User;
import com.aleklew.ballot.modules.profiles.models.LoginRequest;
import com.aleklew.ballot.modules.profiles.models.LoginUserResponse;
import com.aleklew.ballot.modules.profiles.models.RegisterRequest;
import com.aleklew.ballot.modules.profiles.services.UserProfileService;
import com.aleklew.ballot.security.JwtTokenUtil;
import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "api/v1/public")
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final UserProfileService userProfileService;
    private final JwtTokenUtil jwtTokenUtil;
    private final ISharedMailingService sharedMailingService;

    public AuthApi(AuthenticationManager authenticationManager,
                   JwtTokenUtil jwtTokenUtil,
                   UserProfileService userProfileService,
                   ISharedMailingService sharedMailingService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userProfileService = userProfileService;
        this.sharedMailingService = sharedMailingService;
    }

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody @Validated RegisterRequest request) {
        try {
            User user = this.userProfileService.createUser(request);
            if (user != null) {
                sharedMailingService.sendActivateAccountMail(user.getEmail(), user.getActivationCode());
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("activate")
    public ResponseEntity<String> activateAccount(@RequestParam String activationCode) {
        try {
            if (userProfileService.activateAccount(activationCode)) {
                return ResponseEntity.status(HttpStatus.OK).body("Konto zostało aktywowane");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("resendActivationEmail")
    public ResponseEntity<String> resendActivationEmail(@RequestParam String emailAddress) {
        try {
            User user = userProfileService.validateForActivationEmail(emailAddress);
            if (user != null) {
                sharedMailingService.sendActivateAccountMail(user.getEmail(), user.getActivationCode());
                return ResponseEntity.status(HttpStatus.OK).body("Wysłano email aktywacyjny");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // need method to resend activation email

    @PostMapping("login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody @Validated LoginRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUserName(), HelperMethods.passwordToHash(request.getPassword(), request.getUserName())
                            )
                    );

            User user = (User)authenticate.getPrincipal();

            LoginUserResponse response = new LoginUserResponse(jwtTokenUtil.generateToken(user),
                    new Date(System.currentTimeMillis() + JwtTokenUtil.JWT_TOKEN_VALIDITY * 1000));

            return ResponseEntity.ok(response);

        } catch (DisabledException dex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}