import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Route } from '@angular/router';
import { sharedRoutes } from './lib.routes';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [CommonModule, RouterModule.forChild(sharedRoutes), RouterModule, HttpClientModule],
})
export class SharedModule {}
