package com.uam.s5.services;

import com.uam.s5.models.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired // Aqui los datos del bean Users() de AppConfig.java son inyectados en esta lista
    private List<User> users;

    @Override
    public List<User> findAllUsers() {
        return users;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        //Explicacion:
        //users.stream() -> convierte la lista de usuarios en un stream
        //filter( usr -> usr.getId().equals(id) ) -> filtra el stream para que solo contenga el usuario con el id que se busca
        //findFirst() -> obtiene el primer elemento del stream, si no hay elementos, devuelve un Optional vacio
        return users.stream().filter( usr -> usr.getId().equals(id) ).findFirst();
    }
}
