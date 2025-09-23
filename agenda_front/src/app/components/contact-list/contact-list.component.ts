import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ContactoServiceService } from '../../services/contacto-service.service';

@Component({
  selector: 'app-contact-list',
  imports: [CommonModule],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css',
  providers: [ContactoServiceService],
})
export class ContactListComponent {
  contactos: any[] = [];

  constructor(private contactoService: ContactoServiceService) {}

  ngOnInit(): void {
    this.loadContactos();
  }

  loadContactos(): void {
    this.contactoService.getContactos().subscribe({
      next: (data) => {
        this.contactos = data;
      },
      error: (error) => {
        console.error('Error fetching contactos:', error);
      },
    });
  }

  deleteContacto(id: number): void {
    if (confirm('¿Seguro que quieres borrar este contacto?')) {
      this.contactoService.deleteContacto(id).subscribe({
        next: () => this.loadContactos(), // recarga la lista
        error: (err) => console.error(err),
      });
    }
  }
}
