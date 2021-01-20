/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */
package entities;

import java.io.*;
import java.util.*;

/**
 * This is the concrete class PremierLeagueManager which implements the LeagueManager Interface and
 * inherits all the abstract methods from it.
 * Also it includes Accessors and Mutators of the instance variables and the toString method of the class.
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-11-15
 */
public class PremierLeagueManager implements LeagueManager<FootballClub> {
    // Declaring a constant called 'MAX_COUNT' and assigning to 20
    public static final int MAX_COUNT = 20;
    // Declaring a PremierLeagueManager called 'manager' and assigning to null
    private static PremierLeagueManager manager = null;
    // Declaring a List of type SpotsClub called 'clubsInLeague' and assigning to ArrayList
    private List<SportsClub> clubsInLeague = new ArrayList<>();
    // Declaring a List of type Match called 'matchesInLeague' and assigning to ArrayList
    private List<Match<FootballClub>> matchesInLeague = new ArrayList<>();

    /**
     * Default constructor of the concrete class PremierLeagueManager
     */
    private PremierLeagueManager() {
    }

    /**
     * Constructor of the concrete class PremierLeagueManager
     *
     * @param clubsInLeague   an ArrayList of all the clubs that are registered for the Premier League
     * @param matchesInLeague an Arraylist of all the matches played in the Premier League
     */
    public PremierLeagueManager(ArrayList<SportsClub> clubsInLeague, ArrayList<Match<FootballClub>> matchesInLeague) {
        this.clubsInLeague = clubsInLeague;
        this.matchesInLeague = matchesInLeague;
    }

    /**
     * Initializes a newly created object if the object is not created
     *
     * @return PremierLeagueManager object
     */
    public static PremierLeagueManager getInstance() {
        //Lock is only needed if the object is null
        if (manager == null) {
            //To ensure no two threads entered at the same time
            synchronized (PremierLeagueManager.class) {
                if (manager == null) {
                    manager = new PremierLeagueManager();
                }
            }
        }
        return manager;
    }

    /**
     * Method that adds a sports club to the league
     *
     * @param club the club that is to be added to the Premier League
     * @return boolean value which states if the club is added or not
     */
    @Override
    public boolean addClub(FootballClub club) {
        // Declaring a boolean and assigning true
        boolean isAdded = true;
        // Ensuring if the capacity if there to add another club
        if (clubsInLeague.size() < MAX_COUNT) {
            // Adding the football club to the premier league
            clubsInLeague.add(club);
        } else {
            // Assigning the boolean value to false
            isAdded = false;
        }
        return isAdded;

    }

    /**
     * Method that deletes a sports club from the premier league
     *
     * @param clubName the name of the club that is to be deleted
     * @return boolean value which states if the club is deleted or not
     */
    @Override
    public boolean deleteClub(String clubName) {
        // Declaring a boolean and assigning false
        boolean hasFound = false;
        // Looping within the clubs in the league
        for (SportsClub club : clubsInLeague) {
            // Checks if the the user input matches with iteration name
            if (club.getName().equalsIgnoreCase(clubName)) {
                // Removes the club from the premier league
                hasFound = clubsInLeague.remove(club);
                // Break the for loop
                break;
            }
        }
        return hasFound;
    }

    /**
     * Method that returns the stats of the given saved club.
     *
     * @param club the club which the stats belong to
     * @return String value having statistics of the given club
     */
    @Override
    public String getStatsOfAClub(FootballClub club) {
        return "\nStatistics of the club " + club.getName() + " (" + club.getLocation() + ")" +
                "\n\t> Number of Wins\t: " + club.getWins() + "\n\t> Number of Draws\t: " + club.getDraws() +
                "\n\t> Number of Defeats\t: " + club.getDefeats() + "\n\t> Goals Scored\t\t: " + club.getGoalsScored() +
                "\n\t> Goals Against\t\t: " + club.getGoalsReceived() + "\n\t> Goal Difference\t: " + club.getGoalDifference() +
                "\n\t> Matched Played\t: " + club.getMatchesPlayed() + "\n\t> Current Points\t: " + club.getPoints();
    }

    /**
     * Method that adds a played match to the premier league
     *
     * @param match containing the user input data about the match
     * @return Match object containing the match added by the user
     */
    public Match<FootballClub> addMatch(Match<FootballClub> match) {
        // Declaring a FootballClub called homeClub and assigning to a Football
        FootballClub homeClub = new FootballClub();
        // Declaring a FootballClub called awayClub and assigning to a Football
        FootballClub awayClub = new FootballClub();


        // For each loop to iterate through clubs in the premier league
        for (SportsClub club : clubsInLeague) {
            // To check if the name and location of the iteration matches the name and the location of the home club of the match
            if (club.getName().equals(match.getHomeClub().getName()) &&
                    club.getLocation().equals(match.getHomeClub().getLocation())) {
                homeClub = (FootballClub) club; //Assigning homeClub variable to the correspondent football club
            }
            // To check if the name and location of the iteration matches the name and the location of the away club of the match
            if (club.getName().equals(match.getAwayClub().getName()) &&
                    club.getLocation().equals(match.getAwayClub().getLocation())) {
                awayClub = (FootballClub) club; //Assigning awayClub variable to the correspondent football club
            }
        }
        //Ensuring if the match if already exists in the premier league
        if (!matchesInLeague.contains(match)) {
            //if homeClub has scored more than the awayClub
            if (match.getGoalsHomeScored() > match.getGoalsAwayScored()) {
                homeClub.setWins(homeClub.getWins() + 1); // homeClub's wins increases by 1
                awayClub.setDefeats(awayClub.getDefeats() + 1); // awayClub defeats increases by 1
                homeClub.setPoints(homeClub.getPoints() + 3); // homeClub's points increases by 3

                //if awayClub has scored more than the homeClub
            } else if (match.getGoalsHomeScored() < match.getGoalsAwayScored()) {
                awayClub.setWins(match.getAwayClub().getWins() + 1); // awayClub's wins increases by 1
                homeClub.setDefeats(match.getHomeClub().getDefeats() + 1); // homeClub defeats increases by 1
                awayClub.setPoints(match.getAwayClub().getPoints() + 3); // awayClub's points increases by 3

            } else {
                //if awayClub and homeClub scored the same
                homeClub.setDraws(match.getHomeClub().getDraws() + 1); // homeClub's draws increases by 1
                awayClub.setDraws(match.getAwayClub().getDraws() + 1);  // awayClub's draws increases by 1
                homeClub.setPoints(match.getHomeClub().getPoints() + 1); // homeClub's points increases by 1
                awayClub.setPoints(match.getAwayClub().getPoints() + 1); // awayClub's points increases by 1
            }
            // Other user inputs will be increased respectively
            homeClub.setMatchesPlayed(match.getHomeClub().getMatchesPlayed() + 1);
            homeClub.setGoalsScored(match.getHomeClub().getGoalsScored() + match.getGoalsHomeScored());
            homeClub.setGoalsReceived(match.getHomeClub().getGoalsReceived() + match.getGoalsAwayScored());
            awayClub.setMatchesPlayed(match.getAwayClub().getMatchesPlayed() + 1);
            awayClub.setGoalsScored(match.getAwayClub().getGoalsScored() + match.getGoalsAwayScored());
            awayClub.setGoalsReceived(match.getAwayClub().getGoalsReceived() + match.getGoalsHomeScored());
            matchesInLeague.add(match); // The match is added afterwards
            return match;
        }
        return null;
    }

    /**
     * Method that returns the stats of all the clubs saved
     *
     * @return ArrayList containing Strings of stats of all the clubs
     */
    public String getStatsOfAllClubs() {
        // Declaring a List called tempList and assigning to ArrayList
        List<FootballClub> tempList = new ArrayList<>();
        //Adding the clubs of the premier league
        for (SportsClub club : clubsInLeague) {
            tempList.add((FootballClub) club);
        }
        //Sorting by points
        tempList.sort(Collections.reverseOrder());
        List<String[]> rows = new ArrayList<>();  // Declaring a List called rows and assigning to a ArrayList
        StringBuilder sb = new StringBuilder();  // Declaring a StringBuilder called tempList and assigning to a StringBuilder
        sb.append("\n\t\tT H E   P R E M I E R   L E A G U E   T A B L E\n");
        sb.append("\n>\tWins are shown with '+' \n>\tLosses are shown with '-' \n>\tDraws are shown with '*'\n>\tMatch data not found is shown with '/' \n\n");
        String[] headers = {"Position", "Club Name",
                "Club Location",
                "Played Matches",
                "Won",
                "Loss",
                "Drawn",
                "GF",
                "GA",
                "GD",
                "Points",
                "Last 5 Matches"};
        // Maximum width the columns will take up
        int[] maxWidths = Arrays.stream(headers).mapToInt(String::length).toArray();
        //Adding columns to the table
        for (FootballClub club : tempList) {
            rows.add(new String[]{
                    String.valueOf(tempList.indexOf(club) + 1),
                    club.getName(),
                    club.getLocation(),
                    String.valueOf(club.getMatchesPlayed()),
                    String.valueOf(club.getWins()),
                    String.valueOf(club.getDefeats()),
                    String.valueOf(club.getDraws()),
                    String.valueOf(club.getGoalsScored()),
                    String.valueOf(club.getGoalsReceived()),
                    String.valueOf(club.getGoalDifference()),
                    String.valueOf(club.getPoints()),
                    displayLastFiveMatches(club)});
        }


        for (String[] cells : rows) {
            for (int i = 0; i < cells.length; i++) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }
        }

        for (int i = 0; i < maxWidths.length; i++) {
            String line = String.join("", Collections.nCopies(maxWidths[i] +
                    "|".length() + 1, "-"));
            sb.append("+").append(line).append(i == maxWidths.length - 1 ? "+" : "");
        }
        sb.append("\n");
        for (int i = 0; i < headers.length; i++) {
            String s = headers[i];
            String verStrTemp = i == headers.length - 1 ? "|" : "";
            String headingLine = String.format("%s %-" + maxWidths[i] + "s %s", "|", s, verStrTemp);
            sb.append(headingLine);
        }
        sb.append("\n");
        for (int i = 0; i < maxWidths.length; i++) {
            String line = String.join("", Collections.nCopies(maxWidths[i] +
                    "|".length() + 1, "-"));
            sb.append("+").append(line).append(i == maxWidths.length - 1 ? "+" : "");
        }
        sb.append("\n");
        for (String[] cells : rows) {
            for (int i = 0; i < cells.length; i++) {
                String s = cells[i];
                String verStrTemp = i == cells.length - 1 ? "|" : "";
                String rowLine = String.format("%s %-" + maxWidths[i] + "s %s", "|", s, verStrTemp);
                sb.append(rowLine);
            }
            sb.append("\n");
        }
        for (int i = 0; i < maxWidths.length; i++) {
            String line = String.join("", Collections.nCopies(maxWidths[i] +
                    "|".length() + 1, "-"));
            sb.append("+").append(line).append(i == maxWidths.length - 1 ? "+" : "");
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Method to save the data of the Football clubs to file obtained by the user
     *
     * @param fileName the string taken as the file name to save data
     * @throws IOException handles failures related to reading, writing and searching for the called file
     */
    public void saveClubData(String fileName) throws IOException {
        try {
            // Opening the streams
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Iterating through the arrayList and writing Object by object to the file
            for (SportsClub club : clubsInLeague) {
                objectOutputStream.writeObject(club);
            }
            // Clears the stream
            objectOutputStream.flush();

            // Closing the streams
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to retrieve the data of the Football clubs from the file saved
     *
     * @param fileName the string taken as the file name of the data retrieval file
     * @throws IOException            handles failures related to reading, writing and searching for the called file
     * @throws ClassNotFoundException handles when a particular class tries to load and doesn't find the requested class in classpath
     */
    public void retrieveClubData(String fileName) throws IOException, ClassNotFoundException {
        clubsInLeague.clear(); // Clearing the arrayList
        try {
            // Opening the streams
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Iterating through the file object by object and adding to the arrayList called 'clubsInLeague'
            for (; ; ) {
                try {
                    clubsInLeague.add((FootballClub) objectInputStream.readObject());
                } catch (EOFException e) {
                    //When it reaches the end of the file, the loop breaks
                    break;
                }
            }
            // Closing the streams
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException ignored) {

        }
    }

    /**
     * Method to save the data of the Matches played by the football clubs to file obtained by the user
     *
     * @param fileName the string taken as the file name to save data
     * @throws IOException handles failures related to reading, writing and searching for the called file
     */
    public void saveMatchData(String fileName) throws IOException {
        // Opening the streams
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // Iterating through the arrayList and writing Object by object to the file
        for (Match<FootballClub> match : matchesInLeague) {
            objectOutputStream.writeObject(match);
        }
        //Clearing the stream
        objectOutputStream.flush();
        // Closing the streams
        fileOutputStream.close();
        objectOutputStream.close();
    }

    /**
     * Method to retrieve the data of the Matches played by the football clubs from the file saved
     *
     * @param fileName the string taken as the file name to save data
     * @throws IOException            handles failures related to reading, writing and searching for the called file
     * @throws ClassNotFoundException handles when a particular class tries to load and doesn't find the requested class in classpath
     */
    public void retrieveMatchData(String fileName) throws IOException, ClassNotFoundException {
        matchesInLeague.clear(); // Clearing the arrayList
        try {
            // Opening the streams
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Iterating through the file object by object and adding to the arrayList called 'matchesInLeague'
            for (; ; ) {
                try {
                    Match<FootballClub> match = (Match<FootballClub>) objectInputStream.readObject();
                    matchesInLeague.add(match);
                } catch (EOFException e) {
                    //When it reaches the end of the file, the loop breaks
                    break;
                }
            }
            // Closing the streams
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException ignored) {
        }
    }

    /**
     * Getter/Accessor to get all the registered clubs in the premier league
     *
     * @return ArrayList containing all the clubs which are registered for the premier league
     */
    public List<SportsClub> getClubsInLeague() {
        return clubsInLeague;
    }

    /**
     * Setter/Mutator to set all the registered clubs in the premier league
     *
     * @param clubsInLeague containing all the clubs which are registered for the premier league
     */
    public void setClubsInLeague(ArrayList<SportsClub> clubsInLeague) {
        this.clubsInLeague = clubsInLeague;
    }

    /**
     * Getter/Accessor to get all the matches played in the premier league
     *
     * @return ArrayList containing all matches played in the premier league
     */
    public List<Match<FootballClub>> getMatchesInLeague() {
        return matchesInLeague;
    }

    /**
     * Setter/Mutator to set all the matches played in the premier league
     *
     * @param matchesInLeague containing all matches played in the premier league
     */
    public void setMatchesInLeague(ArrayList<Match<FootballClub>> matchesInLeague) {
        this.matchesInLeague = matchesInLeague;
    }

    /**
     * Method to display the last 5 matches of the selected club
     *
     * @param footballClub selected club to display
     * @return String containing an overview of the last 5 matches
     */
    public String displayLastFiveMatches(FootballClub footballClub) {
        // Declaring a List called 'matchesPlayedByClub' and assigning to capacity to 5
        List<Match<FootballClub>> matchesPlayedByClub = new ArrayList<>(5);
        matchesInLeague.sort(Collections.reverseOrder()); // soring by compareTo

        for (Match<FootballClub> match : matchesInLeague) {
            // Checking if either the home club or the away club of the match is the selected club and the size of the arrayList is below 5
            if ((match.getHomeClub().equals(footballClub) || match.getAwayClub().equals(footballClub)) && matchesPlayedByClub.size() <= 5) {
                matchesPlayedByClub.add(match);
            }
        }
        StringBuilder sb = new StringBuilder();
        String won = " + ";
        String lost = " - ";
        String draw = " * ";
        String noMatch = " / ";

        //Iterating through the selected 5 latest matches to be displayed
        for (Match<FootballClub> match : matchesPlayedByClub) {
            //If the football club taken in by the parameter is equal to the home club of the match of the iteration
            if (footballClub.equals(match.getHomeClub())) {
                // if the home club has scored more than the away club
                if (match.getGoalsHomeScored() > match.getGoalsAwayScored()) {
                    // the home club has won
                    sb.append(won);
                    //If the away club has scored more than home club
                } else if (match.getGoalsHomeScored() < match.getGoalsAwayScored()) {
                    // the away club has won
                    sb.append(lost);
                } else {
                    // if both clubs has scored the same
                    sb.append(draw);
                }
                //If the football club taken in by the parameter is equal to the away club of the match of the iteration
            } else {
                // if the away club has scored more than the home club
                if (match.getGoalsAwayScored() > match.getGoalsHomeScored()) {
                    // the away club has won
                    sb.append(won);
                    // if the home club has scored more than the away club
                } else {
                    // the home club has won
                    sb.append(lost);
                }
            }
        }
        // Checking the arrayList has only has 5 or less than 5 items
        if (matchesPlayedByClub.size() <= 5) {
            int noMatchTimes = 5 - matchesPlayedByClub.size();
            sb.append(noMatch.repeat(noMatchTimes));
        }
        return sb.toString();
    }

    /**
     * Generates a random match containing a two random clubs from the premier league
     * and generates a random match date from given year
     *
     * @param year given season year
     * @return match random generated
     */
    public Match<FootballClub> generateRandomMatch(int year) {
        // Declaring a 'Random' called rand and assigning to Random
        Random rand = new Random();
        // Declaring a List called 'randomTeams' and assigning to an ArrayList
        List<SportsClub> randomTeams = new ArrayList<>();
        // Declaring a List called 'scores' and assigning to an ArrayList
        List<Integer> scores = new ArrayList<>();

        int listOfElements = 2;

        // Checking if the premier league has enough clubs to generate match out of
        if (PremierLeagueManager.getInstance().getClubsInLeague().size() >= 2) {
            //Iterating two times
            for (int i = 0; i < listOfElements; i++) {
                // Getting a random club index
                int randClubIndex = rand.nextInt(PremierLeagueManager.getInstance().getClubsInLeague().size());
                // Getting a random score below 11
                int randScore = rand.nextInt(11);

                // Selecting and adding them to the arrayList
                randomTeams.add(PremierLeagueManager.getInstance().getClubsInLeague().get(randClubIndex));
                scores.add(randScore);
                if (randomTeams.size() > 1) {
                    // If the selected home club and away club is equal, different clubs are selected
                    while (randomTeams.get(0) == randomTeams.get(1)) {
                        //Removing the away club
                        randomTeams.remove(1);
                        //Selecting a random index within the size of the arrayList
                        randClubIndex = rand.nextInt(PremierLeagueManager.getInstance().getClubsInLeague().size());
                        randomTeams.add(PremierLeagueManager.getInstance().getClubsInLeague().get(randClubIndex));
                    }
                }
            }
            boolean isLeapYear = false;
            //Checking if the year is a leap year
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0) {
                        isLeapYear = true;
                    }
                } else {
                    isLeapYear = true;
                }
            }
            // Getting a number between 1 and 12 (includes)
            int randMonth = rand.nextInt(12) + 1;
            // Getting a number between 1 and 31 (includes)
            int randDay = rand.nextInt(31) + 1;
            // if it's a leap year and the month is february
            if (isLeapYear && randMonth == 2) {
                // Getting a number between 1 and 29 (includes)
                randDay = rand.nextInt(29) + 1;
            }
            // For the months having 30 a days, getting a number between 1 and 30
            if (randMonth == 4 || randMonth == 6 || randMonth == 9 || randMonth == 11) {
                randDay = rand.nextInt(30) + 1;
            }
            return new Match<>((FootballClub) randomTeams.get(0), (FootballClub) randomTeams.get(1), scores.get(0), scores.get(1), new Date(randDay, randMonth, year));
        }
        return null;
    }

    /**
     * toString method of the class concrete class PremierLeagueManager
     */
    @Override
    public String toString() {
        return "PremierLeagueManager[" +
                "clubsInLeague= " + clubsInLeague +
                ", matchesInLeague= " + matchesInLeague +
                "]";
    }

    /**
     * Equals method of the PremierLeagueManager
     *
     * @param o object containing any type
     * @return a boolean if the object is an instance of PremierLeagueManager
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PremierLeagueManager)) return false;
        PremierLeagueManager that = (PremierLeagueManager) o;
        return Objects.equals(getClubsInLeague(), that.getClubsInLeague()) && Objects.equals(getMatchesInLeague(), that.getMatchesInLeague());
    }

    /**
     * Hashcode method of the class PremierLeagueManager
     *
     * @return int containing the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getClubsInLeague(), getMatchesInLeague());
    }

}
