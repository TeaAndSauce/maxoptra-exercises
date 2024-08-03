import { Route } from '@angular/router';
import { ViewerComponent } from './components/viewer/viewer.component';

export const cardViewerRoutes: Route[] = [
  {
    path: '',
    component: ViewerComponent,
    title: 'Exercise 1 - Bank Cards'
  }
];
