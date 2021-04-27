package com.example.jwtdemo2.services;


import com.example.jwtdemo2.models.User;
import com.example.jwtdemo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public int updateUserById(Long id, User user,int email){
        if(email==1) {
            userRepository.updateUserEmail(id, user.getEmail());
        }
        userRepository.updateUserFirst(id,user.getFirstName());
        userRepository.updateUserLast(id,user.getLastName());
        return 1;
    }
    public Optional<User> findUserById(Long id) {

        return userRepository.findById(id);

    }
    public boolean existsEmail(String email){
        return userRepository.existsByEmail(email);
    }


}
