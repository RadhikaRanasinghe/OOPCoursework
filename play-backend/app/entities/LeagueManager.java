/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */

package entities;

/**
 * This is the Interface LeagueManager which contains all the abstract methods of the functionalities done by a league.
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-11-15
 */
public interface LeagueManager<T extends SportsClub> {

    /**
     * Method that adds a sports club to the league
     */
    boolean addClub(T club);

    /**
     * Method that deletes a sports club from the league
     */
    boolean deleteClub(String clubName);

    /**
     * Method that adds a played match to the league
     *
     * @param match the match that is to be added to league
     * @return match object containing the match added  to the league
     */
    Match<T> addMatch(Match<T> match);

    /**
     * Method that returns the stats of the given saved club.
     *
     * @param club the club which the stats belong to
     */
    String getStatsOfAClub(T club);

    /**
     * Method that returns the stats of all the clubs saved
     *
     * @return ArrayList containing Strings of stats of all the clubs
     */
    String getStatsOfAllClubs();

}

