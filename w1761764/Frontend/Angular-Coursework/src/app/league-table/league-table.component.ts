import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable} from '@angular/material/table';
import {Footballclub} from '../footballclub';
import {LeagueTableDataSource} from './league-table-datasource';
import {PremierLeagueService} from '../premier-league.service';
import {PremierLeagueYearService} from '../premier-league-year.service';

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})
export class LeagueTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<Footballclub>;
  dataSource: LeagueTableDataSource;
  clubData: Footballclub;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['name', 'location', 'matchesPlayed', 'wins', 'defeats', 'draws', 'goalsScored', 'goalsReceived', 'goalDifference', 'points'];
  premierLeagueYear;

  /**
   * constructor of league table
   */
  constructor(private service: PremierLeagueService, private serviceYear: PremierLeagueYearService) {
  }

  /**
   * when the constructor is called sortByPoints() is called
   */
  ngOnInit(): any {
    this.sortByPoints();
  }

  sortByWins(): void {
    this.serviceYear.premierLeagueYear.subscribe((year) => {
      this.service.getFootballClubDataSortedByWins(year).subscribe((r: Footballclub[]) => {
        this.dataSource = new LeagueTableDataSource();
        this.dataSource.data = r;
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        this.table.dataSource = this.dataSource;
      });
    });
  }

  sortByGoals(): void {
    this.serviceYear.premierLeagueYear.subscribe((year) => {
      this.service.getFootballClubDataSortedByGoals(year).subscribe((r: Footballclub[]) => {
        this.dataSource = new LeagueTableDataSource();
        this.dataSource.data = r;
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        this.table.dataSource = this.dataSource;
      });
    });
  }

  sortByPoints(): void {
    this.serviceYear.premierLeagueYear.subscribe((year) => {
      this.service.getFootballClubData(year).subscribe((r: Footballclub[]) => {
        this.dataSource = new LeagueTableDataSource();
        this.dataSource.data = r;
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        this.table.dataSource = this.dataSource;
      });
    });
  }

  yearChanges(): void {
    this.serviceYear.premierLeagueYear.next(String(this.premierLeagueYear));
  }

}
