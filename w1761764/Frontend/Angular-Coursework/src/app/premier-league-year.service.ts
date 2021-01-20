import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PremierLeagueYearService {
  premierLeagueYear: BehaviorSubject<string> = new BehaviorSubject<string>('');
}
