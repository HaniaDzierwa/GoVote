import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GeneralParamsService {
  public static API_TEST = '/test';

  public getUrl(url: string, type: ServicesType): string {
    return GeneralParamsService.apiAddress() + this.getServiceTypeUrl(type) + url;
  }

  getServiceTypeUrl(serviceType: ServicesType): string | undefined {
    switch (serviceType) {
      case ServicesType.API_TEST: {
        return GeneralParamsService.API_TEST;
      }
      default: {
      }
    }
    return;
  }

  public static apiAddress() {
    console.log(location.origin)
    if (!location.origin.includes('localhost')) {
      return 'https://ballot-polsl.herokuapp.com/api'; // 'http://localhost:8080/api'; //
    }
    return "http://localhost:8080/api";
  }

}

export enum ServicesType {
  API_TEST
}
