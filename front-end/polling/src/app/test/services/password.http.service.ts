import { Injectable } from "@angular/core";
import { GeneralParamsService, ServicesType} from "../../services/general-params.service";
import { HttpClientService} from "../../services/http-client.service";

@Injectable({
  providedIn: 'root'
})
export class PasswordHttpService {

  constructor(private http: HttpClientService, private general: GeneralParamsService) { }

  public requestPasswordRecovery(email: string): Promise<any> {
    return this.http.request('post', this.general.getUrl(`/requestPasswordRecovery`, ServicesType.BASE_API), {
        email: email,
    }, { responseType: 'text' })!.toPromise();
  }

  public recoverPassword(activationCode: string, newPassword: string): Promise<any> {
    return this.http.request('post', this.general.getUrl(`/recoverPassword`, ServicesType.BASE_API), {
        activationCode: activationCode,
        newPassword: newPassword
    }, {responseType: 'text'})!.toPromise();
  }
}
