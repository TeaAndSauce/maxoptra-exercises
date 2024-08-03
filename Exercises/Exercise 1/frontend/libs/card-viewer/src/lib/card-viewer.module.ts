import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Route } from '@angular/router';
import { cardViewerRoutes } from './lib.routes';
import { ViewerComponent } from './components/viewer/viewer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(cardViewerRoutes),
    RouterModule,
  ],
  declarations: [ViewerComponent],
})
export class CardViewerModule {}
