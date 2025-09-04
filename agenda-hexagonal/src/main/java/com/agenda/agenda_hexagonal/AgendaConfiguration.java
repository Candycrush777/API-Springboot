package com.agenda.agenda_hexagonal;

import org.springframework.context.annotation.Configuration;

@Configuration



public class AgendaConfiguration {

    public AgendaVersion crearVersion(){

        return new AgendaVersion("1.0.0");
    }

}
