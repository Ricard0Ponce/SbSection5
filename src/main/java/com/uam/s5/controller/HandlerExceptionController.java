package com.uam.s5.controller;

import com.uam.s5.exceptions.UserNotFoundException;
import com.uam.s5.models.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Usaremos esta clase para manejar las excepciones de la aplicación
@RestControllerAdvice // Esta anotación nos permite manejar las excepciones de la aplicación
public class HandlerExceptionController {
    // ResponseEntity es una clase que nos permite manejar las respuestas de la aplicación
    // En los <> se pone el tipo de objeto que se va a regresar
    // Como parametro pasamos la excepción que queremos manejar
    @ExceptionHandler(ArithmeticException.class) // Esta anotacion nos permite manejar las excepciones de tipo ArithmeticException
    public ResponseEntity<Error> divisionByZero(Exception e) {
        Error error = new Error(); // Creamos un objeto de tipo Error
        error.setDate(new Date()); // Asignamos la fecha actual al objeto error
        error.setErrors("Error division por cero"); // Asignamos un mensaje al objeto error
        error.setMessage(e.getMessage()); // Asignamos el mensaje de la excepción al objeto error
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // Asignamos un status 500 al objeto error
        // Esta es la forma 1
        // return ResponseEntity.internalServerError().body(error); // Regresamos un objeto de tipo ResponseEntity con el objeto error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error); // Forma 2
    }

    // Mapeamos el segundo tipo de excepción
    //Ahora pasaremos la clase NoHandlerFoundException
    // Como paso de parametros se pasa la misma excepción
    // Para hacerlo funcionar se debe realizar un cambio en el aplication Properties.
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> noFoundException(NoHandlerFoundException e) {
        Error error = new Error(); // Creamos un objeto de tipo Error
        error.setDate(new Date()); // Asignamos la fecha actual al objeto error
        error.setErrors("Api Rest no encontrado"); // Asignamos un mensaje al objeto error
        error.setMessage(e.getMessage()); // Asignamos el mensaje de la excepción al objeto error
        error.setStatus(HttpStatus.NOT_FOUND.value()); // Cambiamos el status a 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error); // Regresamos un objeto de tipo ResponseEntity con el objeto error
    }

    // Ejemplo que maneja una excepcion personalizada
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Cambiamos el status a 500, esta es la forma en la que se devuelve el status
    public Map<String,Object> numberFormatException(NumberFormatException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("errors", "Error en el formato del número");
        error.put("message", e.getMessage());
        error.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error; // Regresamos el objeto error.
    }


    // Ahora manejado un error de un objeto, este error es completamente personalizado
    @ExceptionHandler({NullPointerException.class, HttpMessageNotWritableException.class
    , UserNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Cambiamos el status a 500, esta es la forma en la que se devuelve el status
    public Map<String,Object> userNotFoundException(Exception e) {
        Map<String, Object> error = new HashMap<>();
        error.put("date", new Date());
        error.put("errors", "Error el usuario o rol no existe");
        error.put("messages", e.getMessage());
        error.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error; // Regresamos el objeto error.
    }

}
