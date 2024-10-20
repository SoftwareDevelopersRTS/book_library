import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  public BASE_URL: string = 'http://localhost:8080/';
  public SERVER_URL: any = {
    "ALL_SECURITY_ROLES": 'api/user/all-security-roles',
    "SYSTEM_USER_LOGIN":'api/user/login',
    "DASHBOARD_ALL_COUNTS":'api/dashboard/all-counts',
    "BOOK_LIST":'api/book/list',
    "ADD_BOOK":'api/book/add',
    "CHANGE_BOOK_STATUS":'api/book/change-book-status/',
    "BOOK_CATEGORY_LIST":'api/bookcategory/list',
     "ADD_BOOK_CATEGORY":'api/bookcategory/add',
    'ALL_BOOK_CATEGORY_LIST':'api/bookcategory/list-all',
    "LIBRARY_LIST":'api/library/list',
    "ALL_LIBRARY_LIST":'api/library/list-all',
    "ADD_LIBRARY":'api/library/add',
    "GET_USER_DETAILS_BY_ID":'api/user/details-by-id/',
    "EDIT_PROFILE":"api/user/edit"
  }
  constructor(private httpClient: HttpClient) { }

  getRequest<T>(url: string): Observable<T> {
    return this.httpClient.get<T>(this.BASE_URL + url);
  }

  getRequestWithHeaders<T>(url: string, headers?: HttpHeaders): Observable<T> {
    return this.httpClient.get<T>(this.BASE_URL + url, { headers });
  }


  // POST request without headers
  postRequestWithHeaders<T>(url: string, payload: T): Observable<any> {
    return this.httpClient.post<any>(this.BASE_URL + url, payload); // No headers, using default
  }

  // POST request with optional headers
  postRequest<T>(url: string, payload: T): Observable<any> {
    const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Timezone': timezone // Add your timezone header here
    });
    return this.httpClient.post<any>(this.BASE_URL + url, payload,{headers});
  }

  // PUT request with optional headers
  putRequest<T>(url: string, payload: T): Observable<any> {
    return this.httpClient.put<any>(this.BASE_URL + url, payload);
  }

  putRequestWithHeaders<T>(url: string, payload: T, headers?: HttpHeaders): Observable<any> {
    return this.httpClient.put<any>(this.BASE_URL + url, payload, { headers });
  }

  // DELETE request with optional headers

  deleteRequestWithHeaders(url: string, headers?: HttpHeaders): Observable<any> {
    return this.httpClient.delete<any>(this.BASE_URL + url, { headers });
  }

  deleteRequest(url: string): Observable<any> {
    return this.httpClient.delete<any>(this.BASE_URL + url);
  }
}
