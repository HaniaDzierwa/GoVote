package com.aleklew.ballot.modules.general.interfaces;

public interface ISharedMailingService {
    void sendActivateAccountMail(String targetEmailAddress, String activationCode);
}
