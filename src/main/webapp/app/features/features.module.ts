import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MongoSharedModule } from '../shared';

import {
   Feature1Component,
   Feature2Component,
   Feature1Service,
   featuresState
} from './';

@NgModule({
    imports: [
        MongoSharedModule,
        RouterModule.forChild(featuresState)
    ],
    declarations: [
        Feature1Component,
        Feature2Component
    ],
    providers: [
        Feature1Service
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FeaturesModule {}
