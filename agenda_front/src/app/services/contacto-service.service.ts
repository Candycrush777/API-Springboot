import { Injectable } from '@angular/core';
import { Contacto } from '../models/contacto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactoServiceService {

  private apiUrl = 'http://localhost:8080/contactos';

  constructor(private http: HttpClient) { }


  getContactos(): Observable<Contacto[]> {
    return this.http.get<Contacto[]>(this.apiUrl);
  }

  getContactoById(id: number): Observable<Contacto> {
    return this.http.get<Contacto>(`${this.apiUrl}/${id}`);
  }

  createContacto(contacto: Contacto): Observable<Contacto> {
    return this.http.post<Contacto>(this.apiUrl, contacto);
  }

  updateContacto(id: number, contacto: Contacto): Observable<Contacto> {
    return this.http.put<Contacto>(`${this.apiUrl}/${id}`, contacto);
  }

  deleteContacto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
