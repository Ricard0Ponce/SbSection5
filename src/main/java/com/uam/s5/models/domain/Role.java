package com.uam.s5.models.domain;

import lombok.Data;

// Usamos esta clase como ejemplo para poder ver el error
// Como en la creacion de los objetos jamas indicamos el rol
// Entonmces sera nulo siempre
@Data
public class Role {

    private String name;
}
