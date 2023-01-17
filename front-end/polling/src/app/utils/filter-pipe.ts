import { Pipe, PipeTransform } from '@angular/core';
import { PollModel } from '../model/poll-model';

@Pipe({
  name: 'filter',
  pure: false,
})
export class FilterPipe implements PipeTransform {
  transform(items: PollModel[], arg: string): any {
    return items.filter((item) => item.ballotName.indexOf(arg) !== -1);
  }
}
