import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { ContactoServiceService } from '../../services/contacto-service.service';
import { ContactFormComponent } from '../contact-form/contact-form.component';
import { Contacto } from '../../models/contacto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [CommonModule, ContactFormComponent],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css',
})
export class ContactListComponent {
  contactos: any[] = [];
  
  contactoSeleccionado = signal<Contacto | null>(null);
  mostrarFormulario = signal(false);

  private heroImages: string[] = [
    '/batman.webp',
    '/superman.jpg',
    '/flash.jpg',
    '/wwww.jpg',
    '/ironman.webp',
    '/capitanamerica.webp',
    '/hulk.webp',
    '/spiderman.jpg',
    '/thor.jpeg'
  ];

  constructor(private contactoService: ContactoServiceService) {}

  ngOnInit(): void {
    this.loadContactos();
  }

  loadContactos(): void {
    this.contactoService.getContactos().subscribe({
      next: (data) => {
        this.contactos = data.map((c, index) => ({
          ...c,
          imagen: this.heroImages[index % this.heroImages.length]
        }));
      },
      error: (err) => {
        Swal.fire({
          title: 'Error',
          text: 'No se pudieron cargar los contactos',
          icon: 'error',
          confirmButtonColor: '#e91009'
        });
      }
    });
  }

  deleteContacto(id: number): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: "¡No podrás revertir esta acción!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#e91009',
      cancelButtonColor: '#362f7e',
      confirmButtonText: 'Sí, borrar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.contactoService.deleteContacto(id).subscribe({
          next: () => {
            this.loadContactos();
            Swal.fire({
              title: '¡Borrado!',
              text: 'El contacto ha sido eliminado correctamente.',
              icon: 'success',
              confirmButtonColor: '#362f7e'
            });
          },
          error: (err) => {
            Swal.fire({
              title: 'Error',
              text: 'No se pudo eliminar el contacto',
              icon: 'error',
              confirmButtonColor: '#e91009'
            });
          }
        });
      }
    });
  }

  editContacto(contacto: Contacto): void {
    this.contactoSeleccionado.set(contacto);
    this.mostrarFormulario.set(true);
  }

  onContactoGuardado(): void {
    this.mostrarFormulario.set(false);
    this.contactoSeleccionado.set(null);
    this.loadContactos();
    
    Swal.fire({
      title: '¡Éxito!',
      text: 'Contacto guardado correctamente',
      icon: 'success',
      confirmButtonColor: '#362f7e'
    });
  }

  cancelarEdicion(): void {
    this.mostrarFormulario.set(false);
    this.contactoSeleccionado.set(null);
  }
}
