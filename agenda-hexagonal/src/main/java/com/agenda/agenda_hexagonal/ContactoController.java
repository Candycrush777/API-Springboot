package com.agenda.agenda_hexagonal;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactos")

public class ContactoController {

private List<ContactoDTO> contactos= List.of(


 new ContactoDTO(1, "Jose", "Pérez", "600123456", "jose@example.com"),
    new ContactoDTO(2, "David", "López", "600654321", "david@example.com"),
    new ContactoDTO(3, "Maria", "García", "611223344", "maria@example.com"),
    new ContactoDTO(4, "Fernando", "Sánchez", "622334455", "fernando@exampl")

);





 @GetMapping("/{id}")
    public List<ContactoDTO> getAllContactos(){
    return contactos;
    }

}
