import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParamsOptions } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private http: HttpClient) { }

  public request(method: string, url: string, requestObj: any, headers: any): Observable<any> | undefined {
    return this.chooseMethod(method, url, requestObj, headers);
  }

  private chooseMethod(method: string, url: string, requestObj: any, headers: any): Observable<any> | undefined {
    switch (method) {
      case 'post':
        return this.post(url, requestObj, headers);
      case 'get':
        return this.get(url, headers);
      case 'delete':
        return this.delete(url, headers);
    }
    return;
  }

  private get(url: string, headers: any): Observable<any> {
    return this.http.get(url, headers);
  }

  private post(url: string, obj: any, headers: any): Observable<any> {
    return this.http.post(url, obj, headers);
  }

  private delete(url: string, headers: any): Observable<any> {
    return this.http.delete(url, headers);
  }
}
