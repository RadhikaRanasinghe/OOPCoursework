import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {Routes} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NavSideBarComponent} from './nav-side-bar/nav-side-bar.component';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {LeagueTableComponent} from './league-table/league-table.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {RandomMatchComponent} from './random-match/random-match.component';
import {MatCardModule} from '@angular/material/card';
import {PlayedMatchesComponent} from './played-matches/played-matches.component';
import {HttpClientModule} from '@angular/common/http';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import {DialogAddMatchComponent} from './dialog-add-match/dialog-add-match.component';
import {MonthFormatPipe} from './month-format.pipe';
import {MonthsFormatPipe} from './months-format.pipe';
import {DashboardHomeComponent} from './dashboard-home/dashboard-home.component';


const routes: Routes = [
  {}
];

@NgModule({
  declarations: [
    AppComponent,
    NavSideBarComponent,
    LeagueTableComponent,
    RandomMatchComponent,
    PlayedMatchesComponent,
    DialogAddMatchComponent,
    MonthFormatPipe,
    MonthsFormatPipe,
    DashboardHomeComponent
  ],
  entryComponents: [DialogAddMatchComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
