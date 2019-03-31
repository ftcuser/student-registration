import { Route } from '@angular/router';

import { Feature2Component } from './feature2.component';

export const feature2Route: Route = {
    path: 'feature2',
    component: Feature2Component,
    data: {
        pageTitle: 'New Feature2'
    }
};
