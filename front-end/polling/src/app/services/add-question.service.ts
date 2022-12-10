import {Injectable} from "@angular/core";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AddQuestionService{

  addQuestionClicked$ = new Subject<boolean>();
  constructor() { }

  addQuestionClick(): void {
    this.addQuestionClicked$.next(true);
  }
}
