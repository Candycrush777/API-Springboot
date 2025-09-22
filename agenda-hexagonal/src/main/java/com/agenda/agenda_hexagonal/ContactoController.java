package com.agenda.agenda_hexagonal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contactos")
public class ContactoController {

    private final ContactosService contactosService;

    // Inyección por constructor del Service
    public ContactoController(ContactosService contactosService) {
        this.contactosService = contactosService;
    }

    // GET /contactos
    @GetMapping
    public ResponseEntity<List<Contactos>> getAll() {
        List<Contactos> lista = contactosService.getAllContactos();
        return ResponseEntity.ok(lista);
    }

    // GET /contactos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Contactos> getById(@PathVariable Integer id) {
        return contactosService.getContactoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /contactos
    @PostMapping
    public ResponseEntity<Contactos> create(@Valid @RequestBody Contactos contacto) {
        Contactos saved = contactosService.createContacto(contacto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    // DELETE /contactos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (contactosService.getContactoById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        contactosService.deleteContacto(id);
        return ResponseEntity.noContent().build();
    }

    // GET /contactos/email?email=algo@ejemplo.com
@GetMapping("/email")
public ResponseEntity<Contactos> getByEmail(@RequestParam String email) {
    Contactos contacto = contactosService.buscarPorEmail(email);
    if (contacto != null) {
        return ResponseEntity.ok(contacto);
    } else {
        return ResponseEntity.notFound().build();
    }
}

@PutMapping("/{id}")
public ResponseEntity<Contactos> actualizar(@PathVariable Integer id, @RequestBody Contactos contacto) {
    Contactos updated = contactosService.updateContacto(id, contacto);
    if (updated != null) {
        return ResponseEntity.ok(updated);
    } else {
        return ResponseEntity.notFound().build();
    }
}


}

