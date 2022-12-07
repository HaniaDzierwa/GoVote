import { Component } from '@angular/core';
import { TestHttpService } from './modules/test/services/test.http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'polling';

  isSidePanelOpened: boolean = false;

  constructor(private testHttpService: TestHttpService) {
  }

  ngOnInit() {
    this.testHttpService.test().then(resp => {
      console.log("response is ... ", resp);
    }, err =>{
      console.log(err);
    });
  }
}
