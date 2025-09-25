import { Component } from '@angular/core';
import { Contacto } from '../../models/contacto';
import { ContactoServiceService } from '../../services/contacto-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contact-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css',
})
export class ContactFormComponent {
  contacto: Contacto = {
    nombre: '',
    apellido: '',
    email: '',
    telefono: '',
  };

  constructor(private contactoService: ContactoServiceService) {}

  onSubmit() {
    this.contactoService.createContacto(this.contacto).subscribe({
      next: (res) => {
        console.log('Contacto creado:', res);
        alert('Contacto creado con éxito');
        this.contacto = { nombre: '', apellido: '', email: '', telefono: '' }; // Reset form
      },
      error: (err) => {
        console.error('Error creando contacto:', err);
        alert('Error creando contacto');
      },
    });
  }
}
