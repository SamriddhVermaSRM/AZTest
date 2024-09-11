package com.test.AZTest.service;


import com.test.AZTest.model.User;
import com.test.AZTest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired

    private UserRepo _userRepo;

    @Override
    public ResponseEntity<?> RegisterUser(User user) {
        return new ResponseEntity<>(_userRepo.save(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> FetchAllUsers() {
        if (_userRepo.findAll().isEmpty()) {
            return new ResponseEntity<>("No User Found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(_userRepo.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> UpdateUser(User user) {
        if (_userRepo.findById(user.getId()).isEmpty()) {
            return new ResponseEntity<>("No Such User Found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(_userRepo.save(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> GetUserByID(int id) {
        if (_userRepo.findById(id).isEmpty()) {
            return new ResponseEntity<>("No Such User Found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(_userRepo.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> GetUserByUsername(String username) {
        List<User> AllUsers = _userRepo.findAll();
        for (User user : AllUsers) {
            if (Objects.equals(user.getName(), username)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No Such User Found", HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> PING(String userName, String ip) {
        List<User> AllUsers = _userRepo.findAll();
        for (User user : AllUsers) {
            if (Objects.equals(user.getName(), userName)) {
                user.setTotalVisits(user.getTotalVisits() + 1);
                System.out.println(user.getVisits());
                String visits = Objects.toString(user.getVisits(), "[]");
                String[] visitsArray = visits.substring(1, visits.length() - 1).split(",");
                ArrayList<String> visitsArrayList = new ArrayList<>();
                for (String visit : visitsArray) {
                    visitsArrayList.add(visit.trim());
                }
                if (!visitsArrayList.contains(ip)) {
                    visitsArrayList.add(ip);
                    user.setUniqueVisits(user.getUniqueVisits() + 1);
                    user.setVisits(visitsArrayList.toString());
                }
                _userRepo.save(user);
                return new ResponseEntity<>(user.getTotalVisits() + " " + user.getUniqueVisits(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No Such User Found", HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<?> DeleteUser(User user) {
        if (_userRepo.findById(user.getId()).isEmpty()) {
            return new ResponseEntity<>("No Such User Found", HttpStatus.NO_CONTENT);
        }
        _userRepo.deleteById(user.getId());
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
