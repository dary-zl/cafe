package com.example.cafe.Service;

import com.example.cafe.DataBase.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByName(String name);
}
