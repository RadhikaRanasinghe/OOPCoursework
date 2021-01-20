import {Component, OnInit} from '@angular/core';
import {PremierLeagueService} from '../premier-league.service';
import {PremierLeagueYearService} from '../premier-league-year.service';

@Component({
  selector: 'app-played-matches',
  templateUrl: './played-matches.component.html',
  styleUrls: ['./played-matches.component.css']
})
export class PlayedMatchesComponent implements OnInit {
  userInputDate: string;
  matchesPlayed = [];
  matchesPlayedMaster = [];
  premierLeagueYear;

  constructor(private service: PremierLeagueService, private serviceYear: PremierLeagueYearService) {
  }

  ngOnInit(): void {
    this.serviceYear.premierLeagueYear.subscribe((year) => {
      this.userInputDate = this.service.getPlayedMatchesData(year).subscribe((res) => {
        this.matchesPlayed = this.matchesPlayedMaster = res;
      });
    });
  }


  search(): void {
    console.log(this.userInputDate);

    const date = this.userInputDate.split('-');

    this.matchesPlayed = [];
    this.matchesPlayedMaster.forEach(playedMatch => {
      if (playedMatch.matchDate.year === Number(date[0])
        && playedMatch.matchDate.month === Number(date[1])
        && playedMatch.matchDate.day === Number(date[2])
      ) {
        this.matchesPlayed.push(playedMatch);
      }
    });
    console.log(this.matchesPlayed);
  }

  yearChanges(): void {
    this.serviceYear.premierLeagueYear.next(String(this.premierLeagueYear));
  }
}
