package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.database.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private CasinoUserRepository casinoUserRepository;
    private UserRepository userRepository;

    public UserServiceImpl(CasinoUserRepository casinoUserRepository, UserRepository userRepository) {
        this.casinoUserRepository = casinoUserRepository;
        this.userRepository = userRepository;
    }

    // == Registration ==
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    //used when we want to commit after executing all queries
    // (software breaking in the middle of the code will not make one query be saved into db while the other is not)
    @Override
    public User registerNewUserAccount(UserDto accountDto, boolean admin) {

        log.info("registerNewUserAccount() started");

        CasinoUser casinoUser = new CasinoUser();
        casinoUser.setBalance(0.00);
        casinoUser.setNickname(accountDto.getUsername());
        casinoUser.setIsactive(admin);
        casinoUserRepository.save(casinoUser);

        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setEnabled(true);
        user.setAuthority(admin? "ROLE_ADMIN" : "ROLE_USER");
        log.info("user = {}", user);
        userRepository.save(user);

        return user;
    }

    public void changePassword(PasswordDto passwordDto) {
        log.info("changePassword() in service started");
        User user = userRepository.findByUsername(passwordDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(passwordDto.getPassword()));
        userRepository.save(user);
        log.info("changePassword() in service finished");
    }

    // == Game ==
    public CasinoUser findCasinoUserByUsername(String nickname) {
        return casinoUserRepository.findByNickname(nickname);
    }

    public void updateCasinoUserBalance(double balance, String nickname) {
        log.info("updateCasinoUserBalance() started");
        CasinoUser user = casinoUserRepository.findByNickname(nickname);
        user.setBalance(balance);
        casinoUserRepository.save(user);
    }

    public void depositToCasinoUser(double balance, String nickname) {
        log.info("depositToCasinoUser() started");
        CasinoUser user = casinoUserRepository.findByNickname(nickname);
        user.setBalance(user.getBalance() + balance);
        casinoUserRepository.save(user);
    }

    public void updateCasinoUserInformation(CasinoUser casinoUser) {
        CasinoUser existingUser = casinoUserRepository.findByNickname(casinoUser.getNickname());
        existingUser.setFirstname(casinoUser.getFirstname());
        existingUser.setSecondname(casinoUser.getSecondname());
        existingUser.setBirthdate(casinoUser.getBirthdate());
        existingUser.setCountry(casinoUser.getCountry());
        casinoUserRepository.save(existingUser);
    }

    public boolean isEnoughMoney(String nickname, double minNeeded) {
        CasinoUser user = casinoUserRepository.findByNickname(nickname);
        return user.getBalance()>=minNeeded;
    }

    public boolean doPasswordsMatch(String oldPassword,String userName) {
        return BCrypt.checkpw(oldPassword, userRepository.findByUsername(userName).getPassword());
    }
}
