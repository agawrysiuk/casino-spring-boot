package com.agawrysiuk.casino.user;

import com.agawrysiuk.casino.casinouser.CasinoUser;
import com.agawrysiuk.casino.casinouser.CasinoUserRepository;
import com.agawrysiuk.casino.model.database.*;
import com.agawrysiuk.casino.user.exception.UserDoesntExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final CasinoUserRepository casinoUserRepository;
    private final UserRepository userRepository;

    // == Registration ==
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserDoesntExistException::new);
    }

    @Transactional
    //used when we want to commit after executing all queries
    // (software breaking in the middle of the code will not make one query be saved into db while the other is not)
    public User registerNewUserAccount(UserDto userDto, boolean admin) {

        log.info("User registration for {} started", userDto.getUsername());

        User user = User.builder()
                .username(userDto.getUsername())
                .password(new BCryptPasswordEncoder().encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .enabled(true)
                .authority(admin? "ROLE_ADMIN" : "ROLE_USER")
                .build();
        userRepository.save(user);

        CasinoUser casinoUser = CasinoUser.builder()
                .balance(0.00)
                .nickname(userDto.getUsername())
                .isactive(true)
                .build();
        casinoUserRepository.save(casinoUser);

        log.info("User registration completed! User = {}", user);

        return user;
    }

    public void changePassword(PasswordDto passwordDto) {
        log.info("changePassword() in service started");
        User user = userRepository.findByUsername(passwordDto.getUsername()).orElseThrow(UserDoesntExistException::new);
        user.setPassword(new BCryptPasswordEncoder().encode(passwordDto.getPassword()));
        userRepository.save(user);
        log.info("changePassword() in service finished");
    }

    // == Game ==
    public CasinoUser findCasinoUserByUsername(String nickname) {
        return casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);
    }

    public void updateCasinoUserBalance(double balance, String nickname) {
        log.info("updateCasinoUserBalance() started");
        CasinoUser user = casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);
        user.setBalance(balance);
        casinoUserRepository.save(user);
    }

    public void depositToCasinoUser(double balance, String nickname) {
        log.info("depositToCasinoUser() started");
        CasinoUser user = casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);
        user.setBalance(user.getBalance() + balance);
        casinoUserRepository.save(user);
    }

    public void updateCasinoUserInformation(CasinoUser casinoUser) {
        CasinoUser existingUser = casinoUserRepository.findByNickname(casinoUser.getNickname())
                .orElseThrow(UserDoesntExistException::new);
        existingUser.setFirstname(casinoUser.getFirstname());
        existingUser.setSecondname(casinoUser.getSecondname());
        existingUser.setBirthdate(casinoUser.getBirthdate());
        existingUser.setCountry(casinoUser.getCountry());
        casinoUserRepository.save(existingUser);
    }

    public boolean isEnoughMoney(String nickname, double minNeeded) {
        CasinoUser user = casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);
        return user.getBalance()>=minNeeded;
    }

    public boolean doPasswordsMatch(String oldPassword,String userName) {
        return BCrypt.checkpw(oldPassword, userRepository.findByUsername(userName).orElseThrow(UserDoesntExistException::new).getPassword());
    }
}
