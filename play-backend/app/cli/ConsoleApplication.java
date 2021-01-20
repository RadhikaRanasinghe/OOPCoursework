/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */
package cli;

import entities.*;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains the Console Application to run the Premier League Manager.
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-11-15
 */

public class ConsoleApplication {
    private static PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    /**
     * This method contains the main method of the ConsoleApplication
     *
     * @param args String array of arguments
     */
    public static void main(String[] args) {

        System.out.println("W E L C O M E   T O   T H E   P R E M I E R   L E A G U E   M A N A G E R");
        // Declaring an int 'seasonYear' and assigning 0
        int seasonYear = 0;
        try {
            // Prompting the user to input the season start year
            seasonYear = getUserInputInt("Enter season start year of the Premier League");
        } catch (InputMismatchException e) {
            // Ensuring the entered year is a valid integer
            System.out.println("Invalid input, Please enter an Integer!");
        }
        // Until the user inputs an year after 1992, prompting to input season year
        while (seasonYear < 1992) {
            System.out.println("The season start year can be only be an year after 1992!");
            try {
                seasonYear = getUserInputInt("Enter season start year of the Premier League");
            } catch (InputMismatchException e) {
                // Catching if the user inputs anything else other an integer
                System.out.println("Invalid input, Please enter an Integer!");
            }
        }

        // Declaring two strings as 'clubFileName' and 'matchFileName' and assigning them as follows;
        String clubFileName = ("clubDataFile" + seasonYear + ".txt");
        String matchFileName = ("matchDataFile" + seasonYear + ".txt");

        // Retrieving all the data stored
        try {
            premierLeagueManager.retrieveClubData(clubFileName);
            premierLeagueManager.retrieveMatchData(matchFileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Printing the menu
        menuLoop:
        while (true) {
            System.out.println("\nMenu of the Premier League Manager" +
                    "\n1:   Add a Football Club to the Premier League" +
                    "\n2:   Delete an existing Football Club from the Premier League" +
                    "\n3:   Display statistics of a Football Club in the Premier League" +
                    "\n4:   Display the Premier League Table" +
                    "\n5:   Add a played match to the Premier League" +
                    "\n6:   Open GUI from the console" +
                    "\n7:   Exit the Premier League Manager");
            try {
                // Prompting the user a message and getting the selection
                int userChoice = getUserInputInt("\nEnter the selection");

                // For the selection the respective methods are called
                switch (userChoice) {
                    case 1:
                        // Adding a club method is called
                        addClub();
                        try {
                            // After addition of a club the files are updated
                            premierLeagueManager.saveClubData(clubFileName);
                            premierLeagueManager.saveMatchData(matchFileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        // Deletion of a club method is called
                        deleteClub();
                        try {
                            // After deletion of a club the file are updated
                            premierLeagueManager.saveClubData(clubFileName);
                            premierLeagueManager.saveMatchData(matchFileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        //Displaying statistics of a club method is called
                        displayStatsOfAClub();
                        break;
                    case 4:
                        // Displaying premier league table method is called
                        displayPremierLeagueTable();
                        break;
                    case 5:
                        // Adding a played match method is called
                        addAPlayedMatch(seasonYear);
                        try {
                            // After addition the files are updated
                            premierLeagueManager.saveClubData(clubFileName);
                            premierLeagueManager.saveMatchData(matchFileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        // The GUI is opened in browser
                        String url = "http://localhost:4200/";

                        // Checking if the desktop supported on current platform
                        if (Desktop.isDesktopSupported()) {
                            Desktop desktop = Desktop.getDesktop();
                            try {
                                // Laughing browser
                                desktop.browse(new URI(url));
                            } catch (IOException | URISyntaxException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Runtime runtime = Runtime.getRuntime();
                            try {
                                runtime.exec("xdg-open " + url);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 7:
                        try {
                            // Saving into all data files
                            premierLeagueManager.saveClubData(clubFileName);
                            premierLeagueManager.saveMatchData(matchFileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break menuLoop;
                    default:
                        // Checking if the user inputs a number between 1 and 7
                        if (userChoice == 0) {
                            break;
                        } else {
                            System.out.println("Please enter a number between 1 and 7!");
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, Please enter an Integer!");
            }
        }

    }

    /**
     * This method takes inputs from the user to add club and then validated before adding to the league
     */
    public static void addClub() {
        // Declaring a String called 'clubName' and calling method getUserInputString()
        String clubName = getUserInputString("Enter the name of the club");

        // Declaring a String called 'clubLocation' and calling method getUserInputString()
        String clubLocation = getUserInputString("Enter club location");

        // Declaring a boolean called 'isPresent' and assigning false
        boolean isPresent = false;

        // Declaring a boolean called 'isAdded' and assigning false
        boolean isAdded = false;

        // Declaring a String called 'regexName'
        String regexName = "^[a-zA-Z\\s]+";

        // Declaring a String called 'regexLocation'
        String regexLocation = "[a-zA-z]*([,\\s]+[a-z]*)*";

        // Declaring a Pattern called 'patternName'
        Pattern patternName = Pattern.compile(regexName, Pattern.CASE_INSENSITIVE);

        // Declaring a Pattern called 'patternLocation'
        Pattern patternLocation = Pattern.compile(regexLocation, Pattern.CASE_INSENSITIVE);

        // Declaring a Matcher called 'matcherName'
        Matcher matcherName = patternName.matcher(clubName);

        // Declaring a Matcher called 'matcherLocation'
        Matcher matcherLocation = patternLocation.matcher(clubLocation);

        //Iterating through the clubs in premier league
        for (SportsClub club : premierLeagueManager.getClubsInLeague()) {
            // Checking if club with same name and location exists
            // Continue if the name and location is null
            if (club.getName() == null || club.getLocation() == null) continue;
            // If found, notify with the boolean isPresent
            if (club.getName().equals(clubName) && club.getLocation().equals(clubLocation)) {
                isPresent = true;
                break;
            }
        }
        // If the club is not present
        if (!isPresent) {
            if (matcherName.matches() && matcherLocation.matches()) {
                FootballClub club = new FootballClub(clubName, clubLocation);
                // Sending to the PremierLeagueManager to add the club
                isAdded = premierLeagueManager.addClub(club);
            } else {
                // If the name doesn't match regex
                System.out.println("Invalid Club Name or Club Location!");
            }
        }

        // If the club is there all registered in the premier league, printing this
        if (isAdded) {
            System.out.println("The football club has been added to the Premier League!");
        } else if (premierLeagueManager.getClubsInLeague().size() >= 20) {
            // If the club is full with the maximum capacity
            System.out.println("The Premier League is full!");
        }

    }

    /**
     * Method that takes in the user input of the club name to be deleted and passes the club name string to delete
     */
    public static void deleteClub() {
        // Checking if there are clubs to be deleted
        if (premierLeagueManager.getClubsInLeague().size() > 0) {
            System.out.println("List of Football clubs in the Premier League");

            // Listing out the clubs registered with the premier league manager
            for (SportsClub club : premierLeagueManager.getClubsInLeague()) {
                System.out.println("\t" + (premierLeagueManager.getClubsInLeague().indexOf(club) + 1) + ": " + club.getName());
            }
            // Declaring a String called 'clubName' and calling method getUserInputString()
            String clubName = getUserInputString("Enter name of the club to be deleted from the list");

            // Passing the clubName to delete from the PremierLeagueManager and taking the boolean returned from it as isDeleted
            boolean isDeleted = premierLeagueManager.deleteClub(clubName);

            // Checking if isDeleted is true
            if (isDeleted) {
                // Printed this if true
                System.out.println("Successfully deleted the club " + clubName + "!");
            } else {
                // Printed this if false
                System.out.println("A Football club with the name '" + clubName + "' is not registered in the Premier League");
            }
        } else {
            // If there no clubs to be deleted
            System.out.println("No registered clubs with the Premier League!");
        }
    }

    /**
     * Method to take in user inputs to add a played match and validate the data
     *
     * @param seasonYear the year user inputs as the season year
     */
    public static void addAPlayedMatch(int seasonYear) {
        try {
            // Checking if there are more than 1 club to play a match
            if (premierLeagueManager.getClubsInLeague().size() > 1) {

                // Declaring a FootballClub called 'homeClub'
                FootballClub homeClub;

                // Declaring a FootballClub called 'awayClub'
                FootballClub awayClub;

                // Listing out the Football clubs in the Premier league
                System.out.println("List of Football clubs in the Premier League");
                for (SportsClub club : premierLeagueManager.getClubsInLeague()) {
                    System.out.println("\t" + (premierLeagueManager.getClubsInLeague().indexOf(club) + 1) + ": " + club.getName());
                }

                // Getting the user inputs as int from the list shown
                int homeClubNum = getUserInputInt("Enter number of the Home Club from the list");
                int awayClubNum = getUserInputInt("Enter number of the Away Club from the list");

                // Ensuring the taken inputs are not equal numbers
                if (homeClubNum == awayClubNum) {
                    System.out.println("Home club and the away club cannot be the same club!");

                } else if (homeClubNum > premierLeagueManager.getClubsInLeague().size() || homeClubNum < 0 ||
                        awayClubNum > premierLeagueManager.getClubsInLeague().size() || awayClubNum < 0) {
                    // The club numbers has to be within the range shown, else this will be printed
                    System.out.println("Invalid Club number!");
                } else {
                    int[] commonDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                    int[] leapDays = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                    // Assigning the selected clubs by the user
                    homeClub = (FootballClub) premierLeagueManager.getClubsInLeague().get(homeClubNum - 1);
                    awayClub = (FootballClub) premierLeagueManager.getClubsInLeague().get(awayClubNum - 1);

                    System.out.println();

                    // Getting user inputs for goals scored by each club as integers
                    int homeGoals = getUserInputInt("Enter number of goals scored by the home club");
                    int awayGoals = getUserInputInt("Enter number of goals scored by the away club");

                    // Declaring a boolean called 'isLeapYear' and assigning it to false
                    boolean isLeapYear = false;

                    // Getting user input for the year
                    int year = getUserInputInt("Enter year");

                    // The year can only be the season year and one year after the season year
                    if (year != seasonYear && year != (seasonYear + 1)) {
                        System.out.println("Please re-enter the match data!");
                    } else {
                        // Checking if the year is leap year
                        if (year % 4 == 0) {
                            if (year % 100 == 0) {
                                if (year % 400 == 0) {
                                    isLeapYear = true;
                                }
                            } else {
                                isLeapYear = true;
                            }
                        }
                        // Getting user input for the month
                        int month = getUserInputInt("Enter month");

                        // Checking if the month is in range
                        if (month > 13 || month < 0) {
                            System.out.println("Please re-enter the match data!");
                        } else {
                            // Getting user inputs for the day
                            int day = getUserInputInt("Enter day");

                            // Declaring a boolean to only added a match if the data is correct
                            boolean isCorrect = true;
                            if (isLeapYear) {
                                // Checking if the day is in range
                                if (day > leapDays[month] || day < 0) {
                                    System.out.println("Please re-enter the match data!");
                                    isCorrect = false;
                                }
                            } else {
                                // Checking if the day is in range
                                if (day > commonDays[month] || day < 0) {
                                    System.out.println("Please re-enter the match data!");
                                    isCorrect = false;
                                }
                            }

                            // Checking if the data is correct
                            if (isCorrect) {
                                // Declaring a match with the user input match data
                                Match<FootballClub> match = new Match<>(homeClub, awayClub, homeGoals, awayGoals,
                                        new Date(day, month, year));
                                boolean isAdded = false;

                                for (Match<FootballClub> matchInList : premierLeagueManager.getMatchesInLeague()) {
                                    // Checking if the match is already added to the premier league
                                    if (matchInList.equals(match)) {
                                        isAdded = true;
                                        System.out.println("Match has been already added to the Premier League!");
                                        break;
                                    }
                                }
                                // If its not already added
                                if (!isAdded) {
                                    // Then it's being added
                                    premierLeagueManager.addMatch(match);
                                    System.out.println("Successfully added the match!");
                                }
                            }
                        }
                    }
                }
            } else {
                // Printing this if less than one club is there
                System.out.println("Not enough registered clubs to play a match!");
            }
        } catch (InputMismatchException e) {
            // Catching when the user inputs anything when asked for an integer
            System.out.println("Invalid input, Please enter an Integer!");
        }
    }

    /**
     * Method that takes in the user input of which club they prefer to get statistics of
     */
    public static void displayStatsOfAClub() {
        // Checking if the premier league has registered clubs
        if (premierLeagueManager.getClubsInLeague().size() > 0) {
            // listing out all the registered clubs in the league
            System.out.println("\nList of Football clubs in the Premier League");
            for (SportsClub club : premierLeagueManager.getClubsInLeague()) {
                System.out.println("\t" + (premierLeagueManager.getClubsInLeague().indexOf(club) + 1) + ": " + club.getName());
            }
            try {
                // Getting the user input for user selection from the list
                int userSelection = getUserInputInt("\nEnter the number of the preferred club");

                // Checking if the user selection is in range
                if (userSelection != 0) {
                    // Displaying the statistics
                    String displayStat = premierLeagueManager.getStatsOfAClub((FootballClub) premierLeagueManager.getClubsInLeague().get(userSelection - 1));
                    System.out.println(displayStat);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Selection!");
            }
        } else {
            // Printing this if less than one club is there
            System.out.println("No registered clubs with the Premier League!");
        }
    }

    /**
     * Method to display the premier league table to the console
     */
    public static void displayPremierLeagueTable() {
        // Checking if the premier league has registered clubs
        if (premierLeagueManager.getClubsInLeague().size() > 0) {
            // Printing the Premier league table
            System.out.println(premierLeagueManager.getStatsOfAllClubs());
        } else {
            // Printing this if less than one club is there
            System.out.println("No registered clubs with the Premier League!");
        }
    }

    /**
     * Method to get a String input from the user
     *
     * @param promptMessage the message to prompt when getting the input
     * @return String input given by the user
     */
    public static String getUserInputString(String promptMessage) {
        // Declaring a String called 'inputLine'
        String inputLine;

        // Declaring a Scanner called 'scanner'
        Scanner scanner = new Scanner(System.in);

        // Prompting a message to the user
        System.out.print(promptMessage + ": ");

        // Taking the user's input from the scanner
        inputLine = scanner.nextLine();
        return inputLine;
    }

    /**
     * Method to get user inputs of Integers
     *
     * @param promptMessage message to display when getting the input from the user
     * @return int the input provided by the user
     * @throws InputMismatchException indicates that the token retrieved does not match the pattern for the expected type
     */
    public static int getUserInputInt(String promptMessage) throws InputMismatchException {
        // Declaring a int called 'userChoice'
        int userChoice = 0;

        // Declaring a Scanner called 'scanner'
        Scanner scanner = new Scanner(System.in);

        // Prompting a message to the user
        System.out.print(promptMessage + ": ");

        // Taking the user's input from the scanner
        userChoice = scanner.nextInt();
        return userChoice;
    }


}

