package com.aleklew.ballot.modules.profiles.services;

import com.aleklew.ballot.modules.profiles.interfaces.IAuthenticationService;
import com.aleklew.ballot.modules.profiles.interfaces.IUserProfileService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("authenticationService")
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BaseAuthenticationService implements IAuthenticationService {

    private IUserProfileService profileRepository;

    @Autowired
    public BaseAuthenticationService(IUserProfileService profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public boolean checkLoginAvailable(String userName) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("");
    }

}
