/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */
package entities;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is Test class of the PremierLeagueManager Class
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-12-27
 */
class PremierLeagueManagerTest {

    /**
     * Test method to add a club in a given year
     */
    @Test
    void addClub() {
        // Clearing the clubs in the premier league
        PremierLeagueManager.getInstance().getClubsInLeague().clear();

        // Valid Test Case for adding a club
        FootballClub clubLiverpool = new FootballClub("Liverpool", "Liverpool");
        PremierLeagueManager.getInstance().addClub(clubLiverpool); // Actual size
        assertEquals(1, PremierLeagueManager.getInstance().getClubsInLeague().size());

        // Valid Test Case for adding a club
        FootballClub evertonClub = new FootballClub("Everton", "Liverpool");
        PremierLeagueManager.getInstance().addClub(evertonClub);
        assertEquals(2, PremierLeagueManager.getInstance().getClubsInLeague().size());

        //Adding 20 clubs to the league
        for (int i = 0; i < 20; i++) {
            PremierLeagueManager.getInstance().addClub(new FootballClub("club " + i, "location " + i));
        }
        assertEquals(20, PremierLeagueManager.getInstance().getClubsInLeague().size());

    }

    /**
     * Test method to delete a club in given year
     */
    @Test
    void deleteClub() {
        // Clearing the clubs in the premier league
        PremierLeagueManager.getInstance().getClubsInLeague().clear();

        // Entering a correct name to delete a club
        FootballClub evertonClub = new FootballClub("Everton", "Liverpool");
        PremierLeagueManager.getInstance().addClub(evertonClub);
        PremierLeagueManager.getInstance().deleteClub("Everton");
        assertEquals(0, PremierLeagueManager.getInstance().getClubsInLeague().size());

        // Entering a wrong name to delete a delete a club
        FootballClub liverpoolClub = new FootballClub("Liverpool", "Liverpool");
        PremierLeagueManager.getInstance().addClub(liverpoolClub);
        PremierLeagueManager.getInstance().deleteClub("liver");
        assertEquals(1, PremierLeagueManager.getInstance().getClubsInLeague().size());

    }

    /**
     * Test method to get statistics of a club
     */
    @Test
    void getStatsOfAClub() {
        // Clearing the clubs in the premier league
        PremierLeagueManager.getInstance().getClubsInLeague().clear();

        // Getting a statistics of a valid club
        FootballClub club = new FootballClub("Test a", "test a", 1, 2, 3, 4, 5, 6, 7, 8);
        PremierLeagueManager.getInstance().addClub(club);
        String output = "\n" +
                "Statistics of the club Test a (test a)\n" +
                "\t> Number of Wins\t: 1\n" +
                "\t> Number of Draws\t: 3\n" +
                "\t> Number of Defeats\t: 2\n" +
                "\t> Goals Scored\t\t: 4\n" +
                "\t> Goals Against\t\t: 5\n" +
                "\t> Goal Difference\t: -1\n" +
                "\t> Matched Played\t: 8\n" +
                "\t> Current Points\t: 7";
        assertEquals(output, PremierLeagueManager.getInstance().getStatsOfAClub(club));
    }

    /**
     * Test method to add a match in a given year
     */
    @Test
    void addMatch() {
        // Clearing the clubs in the premier league
        PremierLeagueManager.getInstance().getClubsInLeague().clear();

        // Entering a valid match
        FootballClub clubA = new FootballClub("club A", "location A"); // Home Club
        FootballClub clubB = new FootballClub("club B", "location B"); // Away Club
        PremierLeagueManager.getInstance().addClub(clubA);
        PremierLeagueManager.getInstance().addClub(clubB);
        Match<FootballClub> match = new Match<FootballClub>(clubA, clubB, 5, 4, new Date(4, 5, 2020));
        PremierLeagueManager.getInstance().addMatch(match);
        assertEquals(1, PremierLeagueManager.getInstance().getMatchesInLeague().size());
        // Check if the respective clubs are updated accordingly
        assertAll(
                () -> assertEquals(1, clubA.getWins()),
                () -> assertEquals(1, clubB.getDefeats()),
                () -> assertEquals(3, clubA.getPoints()),
                () -> assertEquals(0, clubB.getPoints()),
                () -> assertEquals(1, clubA.getMatchesPlayed()),
                () -> assertEquals(1, clubB.getMatchesPlayed()),
                () -> assertEquals(5, clubA.getGoalsScored()),
                () -> assertEquals(4, clubB.getGoalsScored()),
                () -> assertEquals(4, clubA.getGoalsReceived()),
                () -> assertEquals(5, clubB.getGoalsReceived())
        );

        // Entering the same match but doesn't get added to the match array list
        PremierLeagueManager.getInstance().addMatch(match);
        assertEquals(1, PremierLeagueManager.getInstance().getMatchesInLeague().size());

        // Entering a draw match
        Match<FootballClub> match2 = new Match<FootballClub>(clubA, clubB, 3, 3, new Date(30, 10, 2020));
        PremierLeagueManager.getInstance().addMatch(match2);

        // Check if the respective clubs are updated accordingly
        assertAll(
                () -> assertEquals(1, clubA.getDraws()),
                () -> assertEquals(1, clubB.getDraws()),
                () -> assertEquals(4, clubA.getPoints()),
                () -> assertEquals(1, clubB.getPoints()),
                () -> assertEquals(2, clubA.getMatchesPlayed()),
                () -> assertEquals(2, clubB.getMatchesPlayed()),
                () -> assertEquals(8, clubA.getGoalsScored()),
                () -> assertEquals(7, clubB.getGoalsScored()),
                () -> assertEquals(7, clubA.getGoalsReceived()),
                () -> assertEquals(8, clubB.getGoalsReceived())
        );
    }

    /**
     * Test method to get statistics of all clubs
     */
    @Test
    void getStatsOfAllClubs() {
        // Clearing the clubs in the premier league
        PremierLeagueManager.getInstance().getClubsInLeague().clear();

        FootballClub clubA = new FootballClub("club A", "location A"); // Home Club
        FootballClub clubB = new FootballClub("club B", "location B"); // Away Club
        PremierLeagueManager.getInstance().addClub(clubA);
        PremierLeagueManager.getInstance().addClub(clubB);
        Match<FootballClub> match = new Match<FootballClub>(clubA, clubB, 4, 5, new Date(4, 5, 2020));
        PremierLeagueManager.getInstance().addMatch(match);
        String output = "\n" +
                "\t\tT H E   P R E M I E R   L E A G U E   T A B L E\n" +
                "\n" +
                ">\tWins are shown with '+' \n" +
                ">\tLosses are shown with '-' \n" +
                ">\tDraws are shown with '*'\n" +
                ">\tMatch data not found is shown with '/' \n" +
                "\n" +
                "+----------+-----------+---------------+----------------+-----+------+-------+----+----+----+--------+-----------------+\n" +
                "| Position | Club Name | Club Location | Played Matches | Won | Loss | Drawn | GF | GA | GD | Points | Last 5 Matches  |\n" +
                "+----------+-----------+---------------+----------------+-----+------+-------+----+----+----+--------+-----------------+\n" +
                "| 1        | club B    | location B    | 1              | 1   | 0    | 0     | 5  | 4  | 1  | 3      |  +  /  /  /  /  |\n" +
                "| 2        | club A    | location A    | 1              | 0   | 1    | 0     | 4  | 5  | -1 | 0      |  -  /  /  /  /  |\n" +
                "+----------+-----------+---------------+----------------+-----+------+-------+----+----+----+--------+-----------------+\n";

        assertEquals(output, PremierLeagueManager.getInstance().getStatsOfAllClubs());

    }

    /**
     * Test method to unit test four methods of storing data
     * saveClubData()
     * saveMatchData()
     * retrieveClubData()
     * retrieveMatchData()
     */
    @Test
    void databaseTest() {
        // Clearing the clubs in the premier league
        PremierLeagueManager.getInstance().getClubsInLeague().clear();
        PremierLeagueManager.getInstance().getMatchesInLeague().clear();

        // Adding two clubs and a match to the Premier league
        FootballClub clubA = new FootballClub("club A", "location A"); // Home Club
        FootballClub clubB = new FootballClub("club B", "location B"); // Away Club
        PremierLeagueManager.getInstance().addClub(clubA);
        PremierLeagueManager.getInstance().addClub(clubB);

        Match<FootballClub> matchA = new Match<FootballClub>(clubA, clubB, 3, 8, new Date(4, 5, 2020));
        PremierLeagueManager.getInstance().addMatch(matchA);

        // Saving the clubs and matches in the premier league
        try {
            PremierLeagueManager.getInstance().saveClubData("clubDataFile" + 500 + ".txt");
            PremierLeagueManager.getInstance().saveMatchData("matchDataFile" + 500 + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Clearing the clubs in the premier league
        PremierLeagueManager.getInstance().getClubsInLeague().clear();
        PremierLeagueManager.getInstance().getMatchesInLeague().clear();

        // Loading the clubs and matches in the premier league
        try {
            PremierLeagueManager.getInstance().retrieveClubData("clubDataFile" + 500 + ".txt");
            PremierLeagueManager.getInstance().retrieveMatchData("matchDataFile" + 500 + ".txt");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        // If the saving and loading works correctly, clubA should be load back to index 0
        assertEquals(clubA, PremierLeagueManager.getInstance().getClubsInLeague().get(0));

        // If the saving and loading works correctly, clubB should be load back to index 1
        assertEquals(clubB, PremierLeagueManager.getInstance().getClubsInLeague().get(1));

        // If the saving and loading works correctly, size should be equal to 2
        assertEquals(2, PremierLeagueManager.getInstance().getClubsInLeague().size());

        // If the saving and loading works correctly, match should be load back to index 0
        assertEquals(matchA, PremierLeagueManager.getInstance().getMatchesInLeague().get(0));

        // If the saving and loading works correctly, size should be equal to 1
        assertEquals(1, PremierLeagueManager.getInstance().getMatchesInLeague().size());


    }

    /**
     * Method to test the randomly generated match
     */
    @Test
    void generateRandomMatch() {
        // Clearing the clubs in the premier league
        PremierLeagueManager.getInstance().getClubsInLeague().clear();

        //When no clubs are there in the premier league, a match is not generated
        Match<FootballClub> matchRand1 = PremierLeagueManager.getInstance().generateRandomMatch(2020);
        assertNull(matchRand1);

        //If only one club is there,a match is not generated
        FootballClub clubA = new FootballClub("club A", "location A");
        PremierLeagueManager.getInstance().addClub(clubA);
        Match<FootballClub> matchRand2 = PremierLeagueManager.getInstance().generateRandomMatch(2020);
        assertNull(matchRand2);

        //there should be more than 2 clubs in the premier league to generate a random match
        FootballClub clubB = new FootballClub("club B", "location B");

        // the return type of the method is proved as follows
        PremierLeagueManager.getInstance().addClub(clubB);
        Match<FootballClub> matchRand3 = PremierLeagueManager.getInstance().generateRandomMatch(2020);
        assertEquals(Match.class, matchRand3.getClass());

    }

}