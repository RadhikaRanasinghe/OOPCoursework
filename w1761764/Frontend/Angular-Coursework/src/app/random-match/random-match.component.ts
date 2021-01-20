import {Component, OnInit} from '@angular/core';
import {PremierLeagueService} from '../premier-league.service';
import {Match} from '../match';
import {PremierLeagueYearService} from '../premier-league-year.service';
import {MatDialog} from '@angular/material/dialog';
import {DialogAddMatchComponent} from '../dialog-add-match/dialog-add-match.component';

@Component({
  selector: 'app-random-match',
  templateUrl: './random-match.component.html',
  styleUrls: ['./random-match.component.css']
})
export class RandomMatchComponent implements OnInit {
  randomMatch: Match;
  premierLeagueYear;

  constructor(private service: PremierLeagueService, private serviceYear: PremierLeagueYearService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.generateRandomMatch();
  }

  addMatch(): void {
    this.serviceYear.premierLeagueYear.subscribe((year) => {
      this.service.postRandomMatchData(year, this.randomMatch).subscribe();
      this.dialog.open(DialogAddMatchComponent);
    });
  }

  yearChanges(): void {
    this.serviceYear.premierLeagueYear.next(String(this.premierLeagueYear));
  }

  generateRandomMatch(): void {
    this.serviceYear.premierLeagueYear.subscribe((year) => {
      this.service.getRandomMatchData(year).subscribe((res) => {
        this.randomMatch = res;
      });
    });
  }
}

