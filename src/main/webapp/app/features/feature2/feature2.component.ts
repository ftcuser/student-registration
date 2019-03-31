import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { JhiParseLinks } from 'ng-jhipster';
import { ITEMS_PER_PAGE } from '../../shared';

@Component({
  selector: 'jhi-feature2',
  templateUrl: './feature2.component.html'
})
export class Feature2Component implements OnInit {
    constructor(
        private parseLinks: JhiParseLinks
    ) {
    }
    ngOnInit() {}
}
