package com.agenda.agenda_hexagonal;

public record ContactoDTO( Integer id,
    String nombre,
    String apellido,
    String telefono,
    String email) {


} 
//este archivo es para probar con postman o bien en el navegador, ademas con record genera solo los getter y setter 
