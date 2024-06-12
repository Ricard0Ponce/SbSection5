package com.uam.s5;

import com.uam.s5.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
// Inyecta una lista de usuarios en el contexto de Spring
// Esto es una base de datos en memoria
@Bean
List<User> Users() {
    List<User> users = new ArrayList<>();
    users.add(new User(1L, "John", "Doe"));
    users.add(new User(2L, "Jane", "Doe"));
    users.add(new User(3L, "Alice", "Smith"));
    users.add(new User(4L, "Bob", "Smith"));
    users.add(new User(5L, "Charlie", "Brown"));
    users.add(new User(6L, "Daisy", "Duck"));
return users;
}

}
