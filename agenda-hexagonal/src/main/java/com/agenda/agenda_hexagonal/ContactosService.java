package com.agenda.agenda_hexagonal;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactosService {

    private final ContactosRepository contactosRepository;

    public List<Contactos> getAllContactos() {
        return contactosRepository.findAll();
    }

    public Optional<Contactos> getContactoById(Integer id) {
        return contactosRepository.findById(id);
    }

    public Contactos createContacto(Contactos contacto) {
        return contactosRepository.save(contacto);
    }

    public void deleteContacto(Integer id) {
        contactosRepository.deleteById(id);
    }

    public Contactos buscarPorEmail(String email) {
        return contactosRepository.findByEmail(email);
    }
}
