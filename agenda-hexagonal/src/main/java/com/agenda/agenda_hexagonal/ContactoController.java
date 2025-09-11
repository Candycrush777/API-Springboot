package com.agenda.agenda_hexagonal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactos")

public class ContactoController {

private List<ContactoDTO> contactos= new ArrayList<>( List.of(


 new ContactoDTO(1, "Jose", "Pérez", "600123456", "jose@example.com"),
    new ContactoDTO(2, "David", "López", "600654321", "david@example.com"),
    new ContactoDTO(3, "Maria", "García", "611223344", "maria@example.com"),
    new ContactoDTO(4, "Fernando", "Sánchez", "622334455", "fernando@exampl")) 

);
    @GetMapping("/{id}") 
    public ResponseEntity<ContactoDTO> getContactoById(@PathVariable("id") int id) {
        for (ContactoDTO contacto : contactos) {
            if (contacto.id() == id) {
                return ResponseEntity.ok(contacto); // 200 OK
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found si no existe
    }

    @GetMapping
public ResponseEntity<List<ContactoDTO>> getAllContactos() {
    return ResponseEntity.ok(contactos);
}


    @PostMapping 

public ResponseEntity<Void> postContacto(@RequestBody ContactoDTO contactoDTO){

contactos.add(contactoDTO);
return ResponseEntity.status(HttpStatus.CREATED).build();
}

}
