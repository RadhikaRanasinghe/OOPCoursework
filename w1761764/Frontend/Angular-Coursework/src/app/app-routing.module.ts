import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LeagueTableComponent} from './league-table/league-table.component';
import {RandomMatchComponent} from './random-match/random-match.component';
import {PlayedMatchesComponent} from './played-matches/played-matches.component';
import {DashboardHomeComponent} from './dashboard-home/dashboard-home.component';

const routes: Routes = [
  {path: 'home', component: DashboardHomeComponent},
  {path: 'league-table', component: LeagueTableComponent},
  {path: 'random-match', component: RandomMatchComponent},
  {path: 'played-matches', component: PlayedMatchesComponent},
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
