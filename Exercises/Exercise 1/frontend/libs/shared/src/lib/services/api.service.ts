import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Card } from '../models/card';
import { CardCreationRequest } from '../requests/card-creation';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly baseApiUrl = 'http://localhost:8080/api/v1/';
  private http: HttpClient = inject(HttpClient);

  public shouldRefresh = new Subject<boolean>();

  getCards(): Observable<Card[]> {
    return this.http.get<Card[]>(
      this.baseApiUrl + 'cards'
    );
  }

  createCard(request: CardCreationRequest) {
    return this.http.put<Card>(
      this.baseApiUrl + 'cards/create',
      request
    );
  }

  clearAllCards() {
    return this.http.delete(
      this.baseApiUrl + 'cards/deleteAll'
    );
  }
}
