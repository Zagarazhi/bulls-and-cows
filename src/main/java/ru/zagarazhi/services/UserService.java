package ru.zagarazhi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.zagarazhi.entities.User;
import ru.zagarazhi.entities.dto.UserRegistrationDto;
import ru.zagarazhi.repositories.UserRepository;

//Сервси, обеспечивающий работу с пользователями
@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<String> find(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName()).get();
        List<String> ans = new ArrayList<>();
        ans.add(user.getUsername());
        ans.add(Long.toString(user.getRating()));
        return ans;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean save(UserRegistrationDto registration) {
        Optional<User> oUser = userRepository.findByUsername(registration.getUsername());
        if(oUser.isPresent()){
            return false;
        }
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setRating((long) 0);
        user.setPassword(passwordEncoder().encode(registration.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = findByUsername(username);
        if(oUser.isEmpty()){
            throw new UsernameNotFoundException(String.format("user \"%s\" not found", username));
        }
        User userEntity = oUser.get();
        return org.springframework.security.core.userdetails.User
                            .withUsername(userEntity.getUsername())
                            .password(userEntity.getPassword())
                            .authorities("ROLE_USER") //Все зарегистрированные пользователи имеют роль юзера
                            .build();
    }
}
