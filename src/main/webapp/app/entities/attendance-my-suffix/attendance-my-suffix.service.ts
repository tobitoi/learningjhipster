import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { AttendanceMySuffix } from './attendance-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AttendanceMySuffixService {

    private resourceUrl = SERVER_API_URL + 'api/attendances';

    constructor(private http: Http) { }

    create(attendance: AttendanceMySuffix): Observable<AttendanceMySuffix> {
        const copy = this.convert(attendance);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(attendance: AttendanceMySuffix): Observable<AttendanceMySuffix> {
        const copy = this.convert(attendance);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<AttendanceMySuffix> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to AttendanceMySuffix.
     */
    private convertItemFromServer(json: any): AttendanceMySuffix {
        const entity: AttendanceMySuffix = Object.assign(new AttendanceMySuffix(), json);
        return entity;
    }

    /**
     * Convert a AttendanceMySuffix to a JSON which can be sent to the server.
     */
    private convert(attendance: AttendanceMySuffix): AttendanceMySuffix {
        const copy: AttendanceMySuffix = Object.assign({}, attendance);
        return copy;
    }
}
