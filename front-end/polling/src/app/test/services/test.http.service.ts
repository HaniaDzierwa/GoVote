import { HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { GeneralParamsService, ServicesType} from "../../services/general-params.service";
import { HttpClientService} from "../../services/http-client.service";

@Injectable({
  providedIn: 'root'
})
export class TestHttpService {

  constructor(private http: HttpClientService, private general: GeneralParamsService) { }

  public test(): Promise<any> {
    return this.http.request(
      'post',
      this.general.getUrl(`/bajo`,
        ServicesType.API_TEST),
      null, {}
    )!.toPromise();
  }
}
