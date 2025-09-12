package com.agenda.agenda_hexagonal;

import org.springframework.http.ResponseEntity;
/* import org.springframework.beans.factory.annotation.Autowired; */
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/contactos")
public class ContactoController {
    
    /* @Autowired  @Autowired inyecta el bean contactosRepository (dependencia).

Recomendación: usar inyección por constructor en lugar de @Autowired sobre el campo. Es más fácil de testear y evita problemas.*/
    private ContactosRepository contactosRepository;

    public ContactoController(ContactosRepository contactosRepository) {
        this.contactosRepository = contactosRepository;
    }




/* 
    @GetMapping("/{id}")
    public Optional<Contactos> obtenerContactoPorId(@PathVariable Integer id) {
        return contactosRepository.findById(id);
    } */

    @GetMapping("/{id}")
public ResponseEntity<Contactos> getById(@PathVariable Integer id) {
    return contactosRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}


    @GetMapping
   /*  public Iterable<Contactos> obtenerTodosLosContactos() {
        return contactosRepository.findAll();
    }  vamos hacerlo mas moderno en un entorno real 
      Devuelve ResponseEntity<List<Contactos>>

ResponseEntity te da control total sobre la respuesta HTTP:

Status code (200 OK, 201 Created, 404, etc.)

Headers personalizados

Body (tu lista de contactos)

Usa List<Contactos> en lugar de Iterable<Contactos>

Es más práctico en Java moderno porque puedes usar métodos de List como size(), sort(), stream(), etc.

JpaRepository devuelve List<T> en findAll(), así que funciona perfecto. */

    public ResponseEntity<List<Contactos>>getAll(){

        List<Contactos> lista = contactosRepository.findAll();
        return ResponseEntity.ok(lista);

    }

 /*    @PostMapping
    public Contactos crearContacto(@RequestBody Contactos contacto) {
        return contactosRepository.save(contacto);
    } */

    @PostMapping
public ResponseEntity<Contactos> create(@Valid @RequestBody Contactos contacto) {
    Contactos saved = contactosRepository.save(contacto);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saved.getId())
            .toUri();

    return ResponseEntity.created(location).body(saved);
}

// DELETE /contactos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!contactosRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contactosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
 


/* 
    @DeleteMapping("/{id}")
    public void eliminarContactoPorId(@PathVariable Integer id) {
        contactosRepository.deleteById(id);
    } */
}
