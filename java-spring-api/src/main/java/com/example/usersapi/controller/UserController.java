package com.example.usersapi.controller;

import com.example.usersapi.model.User;
import com.example.usersapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository _UserRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET) //get
    public List<User> Get() {
        return _UserRepository.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET) //get by id
    public ResponseEntity<User> getReferenceById(User ID) {
        Optional<User> user = _UserRepository.findById(ID.getId());
        if (user.isPresent())
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/user/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return _UserRepository.findById(id).map(user -> {
                    user.setName(newUser.getName());
                    user.setPhone(newUser.getPhone());
                    user.setEmail(newUser.getEmail());
                    user.setActive(newUser.isActive());
                    return _UserRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return _UserRepository.save(newUser);
                });
    }

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return _UserRepository.save(newUser);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        _UserRepository.deleteById(id);
    }

}

//http://localhost:8080/swagger-ui.html#/ -> Documentation API
//http://localhost:8080/user