import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { JhiParseLinks } from 'ng-jhipster';
import { ITEMS_PER_PAGE } from '../../shared';

import { SevisPerson } from './feature1.model';
import { Feature1Service } from './feature1.service';

@Component({
  selector: 'jhi-feature1',
  templateUrl: './feature1.component.html'
})
export class Feature1Component implements OnInit {

    persons: SevisPerson[];
    selectedPerson: SevisPerson;
    editMode: boolean;

    constructor(
      private feature1Service: Feature1Service,
      private parseLinks: JhiParseLinks
    ) {}

    ngOnInit() {
        this.feature1Service.query({page: 1}).subscribe((res) => {
          this.persons = res.body;
        });
        this.editMode = false;
    }

    editPerson( id ) {
      for ( let i = 0; i < this.persons.length; i++) {
          if ( this.persons[i].id === id ) {
            this.selectedPerson = this.persons[i];
            break;
          }
      }
      this.editMode = true;
    }

    deletePerson( id ) {
      this.feature1Service.delete(id).subscribe((response) => this.onDeleteSuccess(response), () => this.onDeleteError());
    }
      
    private onDeleteSuccess(result) {
      this.feature1Service.query({page: 1}).subscribe((res) => {
        this.persons = res.body;
      }); 
    }

    private onDeleteError() {
      
    }

    savePerson( ) {
      if (this.selectedPerson.id !== "") {
        this.feature1Service.update(this.selectedPerson).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
      } else {         
        this.feature1Service.create(this.selectedPerson).subscribe((response) => this.onSaveSuccess(response), () => this.onSaveError());
      }
    }
   
    private onSaveSuccess(result) {
      this.feature1Service.query({page: 1}).subscribe((res) => {
        this.persons = res.body;
      }); 
      this.editMode = false;
    }
    private onSaveError() {
      this.editMode = true;
    }
    cancelSavePerson( ) {
      this.editMode = false;
    }

    addPerson( ) {
      this.selectedPerson = new SevisPerson( "", "",  "",  0, "", "", "" );
      this.editMode = true;
    }

}
