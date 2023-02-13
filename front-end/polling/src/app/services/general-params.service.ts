import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class GeneralParamsService {
  public static API_TEST = '/test';
  public static BASE_API = '/api/v1/public';

  public getUrl(url: string, type: ServicesType): string {
    return (
      GeneralParamsService.apiAddress() + this.getServiceTypeUrl(type) + url
    );
  }

  getServiceTypeUrl(serviceType: ServicesType): string | undefined {
    switch (serviceType) {
      case ServicesType.API_TEST: {
        return GeneralParamsService.API_TEST;
      }
      case ServicesType.BASE_API: {
        return GeneralParamsService.BASE_API;
      }
      default: {
      }
    }
    return;
  }

  public static apiAddress() {
    if (!location.origin.includes('localhost')) {
      return 'https://ballot-polsl.herokuapp.com/api'; // 'http://localhost:8080/api'; //
    }
    return window.location.origin;
  }
}

export enum ServicesType {
  API_TEST, BASE_API
}
