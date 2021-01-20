import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {map} from 'rxjs/operators';
import {Observable} from 'rxjs/index';
import {Match} from './match';

@Injectable({
  providedIn: 'root'
})
export class PremierLeagueService {

  private randomMatchUrl = 'http://localhost:9000/random-match/';
  private footballClubUrl = 'http://localhost:9000/football-clubs/';
  private footballMatchesUrl = 'http://localhost:9000/football-matches/';
  private footballMatchesSortWinsUrl = 'http://localhost:9000/football-clubs-sort-by-wins/';
  private footballMatchesSortGoalsUrl = 'http://localhost:9000/football-clubs-sort-by-goals/';

  constructor(private http: HttpClient) {
  }

  public getFootballClubData(year: string): any {
    return this.http.get(this.footballClubUrl + year).pipe(
      map((res: ResType) => res.response)
    );
  }

  public getRandomMatchData(year: string): any {
    return this.http.get(this.randomMatchUrl + year).pipe(
      map((res: ResType) => res.response)
    );
  }

  public getPlayedMatchesData(year: string): any {
    return this.http.get(this.footballMatchesUrl + year).pipe(
      map((res: ResType) => res.response)
    );
  }

  public getFootballClubDataSortedByWins(year: string): any {
    return this.http.get(this.footballMatchesSortWinsUrl + year).pipe(
      map((res: ResType) => res.response)
    );
  }

  public getFootballClubDataSortedByGoals(year: string): any {
    return this.http.get(this.footballMatchesSortGoalsUrl + year).pipe(
      map((res: ResType) => res.response)
    );
  }

  /**
   * this should be corrected with correct URL used in PLAY framework
   */
  public postRandomMatchData(year: string, randMatch: Match): Observable<Match> {
    return this.http.post<Match>(this.randomMatchUrl + year, randMatch);
  }
}

interface ResType {
  status: boolean;
  response: any[];
}
