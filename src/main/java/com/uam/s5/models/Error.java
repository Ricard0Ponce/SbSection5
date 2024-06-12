package com.uam.s5.models;

import lombok.Data;

import java.util.Date;
@Data
// Utilizamos esta clase para manejar los errores de la aplicación
public class Error {
    private String message;
    private String errors;
    private int status;
    private Date date;
}
