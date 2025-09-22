package com.agenda.agenda_hexagonal;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactosRepository extends JpaRepository<Contactos, Integer> {

    Contactos findByEmail(String email);
 }
