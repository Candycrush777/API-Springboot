import { Component, inject, signal, computed, effect, input, output } from '@angular/core';
import { Contacto } from '../../models/contacto';
import { ContactoServiceService } from '../../services/contacto-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css',
})
export class ContactFormComponent {
  private contactoService = inject(ContactoServiceService);

  contactoInput = input<Contacto | null>(null);
  contactoGuardado = output<void>();
  cancelar = output<void>();

  formState = signal<Contacto>(this.getEmptyContacto());
  isEditing = computed(() => !!this.formState().id);
  mostrarCancelar = computed(() => this.isEditing() && !!this.contactoInput());

  private syncEffect = effect(() => {
    const contactoFromParent = this.contactoInput();
    if (contactoFromParent) {
      this.formState.set({ ...contactoFromParent });
    } else {
      this.formState.set(this.getEmptyContacto());
    }
  });

  private getEmptyContacto(): Contacto {
    return {
      nombre: '',
      apellido: '',
      email: '',
      telefono: ''
    };
  }

  onSubmit() {
    const currentContacto = this.formState();

    if (this.isEditing()) {
      this.contactoService.updateContacto(currentContacto.id!, currentContacto).subscribe({
        next: () => {
          Swal.fire({
            title: '¡Éxito!',
            text: 'Contacto actualizado correctamente',
            icon: 'success',
            confirmButtonText: 'Aceptar',
            confirmButtonColor: '#362f7e'
          });
          this.formState.set(this.getEmptyContacto());
          this.contactoGuardado.emit();
        },
        error: (err) => {
          Swal.fire({
            title: 'Error',
            text: 'No se pudo actualizar el contacto',
            icon: 'error',
            confirmButtonText: 'Aceptar',
            confirmButtonColor: '#e91009'
          });
        }
      });
    } else {
      this.contactoService.createContacto(currentContacto).subscribe({
        next: () => {
          Swal.fire({
            title: '¡Éxito!',
            text: 'Contacto creado correctamente',
            icon: 'success',
            confirmButtonText: 'Aceptar',
            confirmButtonColor: '#362f7e'
          });
           this.formState.set(this.getEmptyContacto());
          this.contactoGuardado.emit();
        },
        error: (err) => {
          Swal.fire({
            title: 'Error',
            text: 'No se pudo crear el contacto',
            icon: 'error',
            confirmButtonText: 'Aceptar',
            confirmButtonColor: '#e91009'
          });
        }
      });
    }
  }

  onCancel() {
    this.cancelar.emit();
  }
}
