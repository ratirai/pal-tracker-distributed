package io.pivotal.pal.tracker.accounts;

import io.pivotal.pal.tracker.accounts.data.AccountDataGateway;
import io.pivotal.pal.tracker.users.data.UserDataGateway;
import io.pivotal.pal.tracker.users.data.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
Logger logger= LoggerFactory.getLogger(RegistrationService.class);
    private final UserDataGateway userDataGateway;
    private final AccountDataGateway accountDataGateway;

    public RegistrationService(UserDataGateway userDataGateway, AccountDataGateway accountDataGateway) {
        this.userDataGateway = userDataGateway;
        this.accountDataGateway = accountDataGateway;
    }

    @Transactional
    public UserRecord createUserWithAccount(String name) {
        logger.info("inside createUserWithAccount() method");
        UserRecord user = userDataGateway.create(name);
        accountDataGateway.create(user.id, String.format("%s's account", name));
        return user;
    }
}
