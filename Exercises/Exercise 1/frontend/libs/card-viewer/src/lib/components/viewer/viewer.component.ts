import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '@frontend/shared';
import { CardCreationRequest } from '@frontend/shared';
import { map, Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'frontend-viewer',
  templateUrl: './viewer.component.html',
  styleUrls: ['./viewer.component.css'],
})
export class ViewerComponent implements OnInit, OnDestroy {
  private readonly api: ApiService = inject(ApiService);
  
  cards$ = this.api.getCards();
  cardCount$ = this.api.getCards().pipe(map(cards => cards.length > 0));
  destroyed$ = new Subject<void>();
  
  cardFormControl = new FormGroup({
    bank: new FormControl('', Validators.required),
    cardNumber: new FormControl('', [Validators.required, Validators.pattern('([0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4})')]),
    expiryDate: new FormControl('', [Validators.required, Validators.pattern('([a-zA-Z]{3}-[0-9]{4})')])
  });
  
  ngOnInit(): void {
    this.newDataSubscription();
  }

  ngOnDestroy(): void {
      this.destroyed$.next();
      this.destroyed$.complete();
  }

  public newDataSubscription() {
    this.api.shouldRefresh
      .pipe(takeUntil(this.destroyed$))
      .subscribe({
        next: (data: boolean) => {
          if (data)
            this.cards$ = this.api.getCards();
        }
      })
  }

  createCard() {
    this.api.createCard(this.cardFormControl.value as CardCreationRequest)
    .subscribe({
      next: () => {
        console.log("Completed!");
        this.api.shouldRefresh.next(true);
      },
      error: (e: Error) => console.log(e)
    });
  }

  clearAllCards() {
    this.api.clearAllCards()
      .subscribe({
        next: () => {
          console.log("Cards cleared!");
          this.api.shouldRefresh.next(true);
        },
        error: (e: Error) => console.log(e)
      })
  }
}
