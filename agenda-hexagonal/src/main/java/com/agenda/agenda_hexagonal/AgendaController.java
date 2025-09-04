package com.agenda.agenda_hexagonal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController



public class AgendaController {

    
    private AgendaService agendaService;

    public AgendaController(AgendaService agendaService){
        this.agendaService=agendaService;
    }


}
