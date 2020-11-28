package io.acepirit.ppmtool.services;

import io.acepirit.ppmtool.domain.User;
import io.acepirit.ppmtool.exceptions.UsernameAlreadyExistsResponse;
import io.acepirit.ppmtool.exceptions.UsrnameAlreadyExistException;
import io.acepirit.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser){
        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            return userRepository.save(newUser);
        }catch (Exception e){
            throw new UsrnameAlreadyExistException("Username '"+newUser.getUsername()+"' already Exists");
        }

    }

}
