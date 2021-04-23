package com.example.jwtdemo2.services;


import com.example.jwtdemo2.models.User;
import com.example.jwtdemo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public int updateUserById(Long id, User user){
        User originalUser=findUserById(id);

        originalUser.setEmail(user.getEmail());

        userRepository.save(originalUser);
        return 1;
    }
    public User findUserById(Long id) {

        return userRepository.findUserById(id);

    }


}
