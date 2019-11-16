package com.agawrysiuk.casino.service;

import com.agawrysiuk.casino.model.database.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final CasinoUserRepository casinoUserRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(CasinoUserRepository casinoUserRepository, UserRepository userRepository) {
        this.casinoUserRepository = casinoUserRepository;
        this.userRepository = userRepository;
    }


    // == Registration ==
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto) {

        log.info("registerNewUserAccount() started");

        CasinoUser casinoUser = new CasinoUser();
        casinoUser.setBalance(0.00);
        casinoUser.setNickname(accountDto.getUsername());
        casinoUser.setIsactive(false);
        casinoUserRepository.save(casinoUser);

        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setEnabled(true);
        user.setAuthority("ROLE_USER");
        log.info("user = {}",user);
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
        casinoUserRepository.updateBalance(balance, nickname);
    }

    public void updateCasinoUserInformation(CasinoUser casinoUser) {
        CasinoUser existingUser = casinoUserRepository.findByNickname(casinoUser.getNickname());
        existingUser.setFirstname(casinoUser.getFirstname());
        existingUser.setSecondname(casinoUser.getSecondname());
        existingUser.setBirthdate(casinoUser.getBirthdate());
        existingUser.setCountry(casinoUser.getCountry());
        casinoUserRepository.save(existingUser);
    }
}
