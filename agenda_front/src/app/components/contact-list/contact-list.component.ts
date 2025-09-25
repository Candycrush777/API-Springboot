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
editContacto(_t5: any) {
throw new Error('Method not implemented.');
}
  contactos: any[] = [];
   private heroImages: string[] = [
    '/batman.webp',
    '/superman.jpg',
    '/flash.jpg',
    '/lobezno.webp',
    '/ironman.webp',
    '/capitanamerica.webp',
    '/hulk.webp',
    '/spiderman.jpg',
    
  ];

  constructor(private contactoService: ContactoServiceService) {}

  ngOnInit(): void {
    this.loadContactos();
  }

loadContactos(): void {
    this.contactoService.getContactos().subscribe({
      next: (data) => {
        // Asignamos imagen a cada contacto según el índice
        this.contactos = data.map((c, index) => ({
          ...c,
          imagen: this.heroImages[index % this.heroImages.length] // Repite si hay más de 9
        }));
      },
      error: (err) => console.error(err)
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
