package com.aleklew.ballot.modules.profiles.interfaces;

import jdk.jshell.spi.ExecutionControl;

public interface IAuthenticationService {

    boolean checkLoginAvailable(String userName) throws ExecutionControl.NotImplementedException;

}
