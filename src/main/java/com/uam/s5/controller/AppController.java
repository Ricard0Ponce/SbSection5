package com.uam.s5.controller;

import com.uam.s5.models.domain.User;
import com.uam.s5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uam.s5.exceptions.UserNotFoundException;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping("/app")
    public String index() {
        // int val = 100 / 0;
        int val = Integer.parseInt("10x");
        System.out.println("val: " + val);
        return "Ok 200";
    };

    @GetMapping("/show/{id}")
    public User showUser(@PathVariable(name = "id") Long id) {
        // Lo que regresamos es un objeto opcional de tipo User, en el que si no se encuentra el usuario, lanzamos una excepción
        //Forma 1
        User user = userService.findUserById(id).orElseThrow(() -> new UserNotFoundException("El usuario con el id " + id + " no existe"));
        // Forma 2
        /*
        * Optional<User> optionalUser = userService.findUserById(id);
        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalUser.orElseThrow()); // Regresamos un objeto de tipo ResponseEntity con el objeto user
        * */

        // Si no lo encuentra regresa un status 404
        return  user;
    }


}
