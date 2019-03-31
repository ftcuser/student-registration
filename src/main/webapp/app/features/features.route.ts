import { Routes } from '@angular/router';

import {
    feature1Route,
    feature2Route
} from './';

const FEATURES_ROUTES = [
    feature1Route,
    feature2Route
];

export const featuresState: Routes = [{
    path: '',
    children: FEATURES_ROUTES
}];
