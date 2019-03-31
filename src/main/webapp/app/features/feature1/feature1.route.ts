import { Route } from '@angular/router';

import { Feature1Component } from './feature1.component';

export const feature1Route: Route = {
    path: 'feature1',
    component: Feature1Component,
    data: {
        pageTitle: 'New Feature1'
    }
};
