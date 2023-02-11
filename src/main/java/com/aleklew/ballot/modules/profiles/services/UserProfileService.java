package com.aleklew.ballot.modules.profiles.services;

import com.aleklew.ballot.modules.general.models.validation.FormValidationResult;
import com.aleklew.ballot.modules.profiles.dbmodels.Role;
import com.aleklew.ballot.modules.profiles.dbmodels.User;
import com.aleklew.ballot.modules.profiles.interfaces.IUserProfileService;
import com.aleklew.ballot.modules.profiles.interfaces.RoleRepository;
import com.aleklew.ballot.modules.profiles.interfaces.UserRepository;
import com.aleklew.ballot.modules.profiles.models.RecoverPasswordRequest;
import com.aleklew.ballot.modules.profiles.models.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service("profileService")
public class UserProfileService implements IUserProfileService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserProfileService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUserName(username);
        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    public boolean activateAccount(String activationCode) {
        Optional<User> account = userRepository.findByActivationCode(activationCode);
        if(account.isPresent()) {
            account.get().setAccountStatus(2);
            userRepository.save(account.get());
            return true;
        }

        return false;
    }

    public User changeUserPassword(RecoverPasswordRequest request) {
        FormValidationResult result = request.validateRequest();
        if (!result.isValid()) {
            return null;
        }

        User user = userRepository.findUserWithPasswordActionCode(request.getActivationCode());
        if (user == null) {
            return null;
        }

        user.setPassword(request.getNewPassword());
        user.setChangePasswordCode(null);
        user.setPasswordCodeExpDate(null);

        userRepository.save(user);
        return user;
    }

    public User generatePasswordRecoveryCode(String email) {
        Optional<User> account = userRepository.findByEmail(email);
        if (!account.isPresent()) {
            return null;
        }

        User user = account.get();
        user.setChangePasswordCode(UUID.randomUUID().toString());
        user.setPasswordCodeExpDate(new Date(Calendar.getInstance().getTimeInMillis() + (15 * 60 * 1000)));

        userRepository.save(user);
        return user;
    }

    public User validateForActivationEmail(String email) {
        Optional<User> account = userRepository.findByEmail(email);
        if(account.isPresent() && account.get().getAccountStatus() == 0) {
            return account.get();
        }

        return null;
    }

    public User createUser(RegisterRequest registerRequest) {
        FormValidationResult result = registerRequest.validateRequest();
        if (!result.isValid()) {

            return null; // BAD REQUEST + SHOW INFO ???
        }

        Optional<User> user = userRepository.findByUserName(registerRequest.getLogin());
        if (user.isPresent()) {
            // user already exists display error
            return null;
        }

        user = userRepository.findByEmail(registerRequest.getEmail());
        if (user.isPresent()) {
            // email address should be taken
            return null;
        }

        User userToRegister = new User(registerRequest.getLogin(), registerRequest.getPassword(), registerRequest.getName(), registerRequest.getSurname(),
                registerRequest.getPESEL(), registerRequest.getEmail());
        userToRegister.setActivationCode(String.valueOf(UUID.randomUUID()));

        Role role = roleRepository.getReferenceById(2); // Normal user
        userToRegister.setRole(role);


        return userRepository.save(userToRegister);
    }
}
