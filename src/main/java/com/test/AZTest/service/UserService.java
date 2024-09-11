package com.test.AZTest.service;

import com.test.AZTest.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> RegisterUser(User user);

    ResponseEntity<?> FetchAllUsers();

    ResponseEntity<?> UpdateUser(User user);

    ResponseEntity<?> GetUserByID(int id);

    ResponseEntity<?> GetUserByUsername(String UserName);

    ResponseEntity<?> DeleteUser(User user);

    ResponseEntity<?> PING(String UserName, String ip);
}
