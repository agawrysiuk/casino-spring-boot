package com.agawrysiuk.casino.user;

import com.agawrysiuk.casino.casinouser.CasinoUser;
import com.agawrysiuk.casino.casinouser.CasinoUserRepository;
import com.agawrysiuk.casino.user.dto.CreditCardObjectDto;
import com.agawrysiuk.casino.user.dto.PasswordDto;
import com.agawrysiuk.casino.user.dto.UserDto;
import com.agawrysiuk.casino.user.exception.UserDoesntExistException;
import com.agawrysiuk.casino.user.exception.WrongCreditCardException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;

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
                .balance(BigDecimal.valueOf(0))
                .nickname(userDto.getUsername())
                .isactive(true)
                .build();
        casinoUserRepository.save(casinoUser);

        log.info("User registration completed! User = {}", user);

        return user;
    }

    public ResponseEntity<?> changePassword(PasswordDto passwordDto) {
        log.info("changePassword() in service started for user {}", passwordDto.getUsername());
        User user = userRepository.findByUsername(passwordDto.getUsername()).orElseThrow(UserDoesntExistException::new);
        user.setPassword(new BCryptPasswordEncoder().encode(passwordDto.getPassword()));
        userRepository.save(user);
        log.info("changePassword() in service completed for user {}", passwordDto.getUsername());
        return ResponseEntity.ok().build();
    }

    // == Game ==
    public CasinoUser findCasinoUserByUsername(String nickname) {
        return casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);
    }

    public void updateCasinoUserBalance(BigDecimal balance, String nickname) {
        log.info("updateCasinoUserBalance() started");
        CasinoUser user = casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);
        user.setBalance(balance);
        casinoUserRepository.save(user);
    }

    @Transactional
    public ResponseEntity<?> depositToCasinoUser(Principal principal, CreditCardObjectDto card) {
        log.info("depositToCasinoUser() started, details = {}", card);
        CasinoUser user = casinoUserRepository.findByNickname(principal.getName())
                .orElseThrow(UserDoesntExistException::new);
        if(!user.getFirstname().equals(card.getFirstName()) || !user.getSecondname().equals(card.getSurname())) {
            throw new WrongCreditCardException();
        }
        user.setBalance(user.getBalance().add(card.getDepositAmount()));
        casinoUserRepository.save(user);
        log.info("depositToCasinoUser() finished, deposited = {}", card.getDepositAmount());
        return ResponseEntity.ok().build();
    }

    public boolean isEnoughMoney(String nickname, BigDecimal minNeeded) {
        CasinoUser user = casinoUserRepository.findByNickname(nickname)
                .orElseThrow(UserDoesntExistException::new);
        return user.getBalance().compareTo(minNeeded) >= 0;
    }

    public boolean isEnoughMoney(BigDecimal balance, BigDecimal minNeeded) {
        return balance.compareTo(minNeeded) >= 0;
    }

    public boolean doPasswordsMatch(String oldPassword,String userName) {
        return BCrypt.checkpw(oldPassword, userRepository.findByUsername(userName).orElseThrow(UserDoesntExistException::new).getPassword());
    }
}
