import {Footballclub} from './footballclub';

export interface Match {
  homeClub: Footballclub;
  awayClub: Footballclub;
  goalsHomeScored: number;
  goalsAwayScored: number;
  matchDate: {
    day: number;
    month: number;
    year: number;
  };
}
