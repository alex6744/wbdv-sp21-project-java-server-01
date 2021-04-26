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
        userRepository.updateUser(id,user.getEmail());
        return 1;
    }
    public User findUserById(Long id) {

        return userRepository.findUserById(id);

    }


}
