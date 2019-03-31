import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';
import { SevisPerson } from './feature1.model';
import { createRequestOption} from '../../shared/model/request-util';

@Injectable()
export class Feature1Service  {
    constructor(private http: HttpClient) { }

    query(req: any): Observable<HttpResponse<SevisPerson[]>> {
        const params: HttpParams = createRequestOption(req);

        const requestURL = SERVER_API_URL + 'sevis/listPersons';       
        return this.http.get<SevisPerson[]>(requestURL, {
            params,
            observe: 'response'
        });
    }

    create(person: SevisPerson): Observable<HttpResponse<SevisPerson>> {
        const createURL = SERVER_API_URL + 'sevis/createPerson';
        return this.http.post<SevisPerson>(createURL, person, { observe: 'response' });
    }

    update(person: SevisPerson): Observable<HttpResponse<SevisPerson>> {
        const updateURL = SERVER_API_URL + 'sevis/updatePerson';
        return this.http.put<SevisPerson>(updateURL, person, { observe: 'response' });
    }
    
    delete(id: string): Observable<HttpResponse<any>> {
        const deleteURL = SERVER_API_URL + 'sevis/deletePerson/' + id;
        return this.http.delete(deleteURL, { observe: 'response' });
    }
}
