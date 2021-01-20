/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */
package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import utils.ApplicationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static play.mvc.Results.created;
import static play.mvc.Results.ok;

/**
 * This is class includes all the controller functionalities.
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-12-12
 */
@SuppressWarnings("unchecked")
public class PremierLeagueController {

    private static final Logger LOGGER = LoggerFactory.getLogger("controller");

    /**
     * Method to add the already generated random match on given year by the user
     *
     * @param year    given year by the user
     * @param request Http.Request
     * @return Result containing response created by application util
     */
    public Result createRandomMatch(int year, Http.Request request) {
        try {
            // Loading the data files according year user has given
            PremierLeagueManager.getInstance().retrieveClubData("clubDataFile" + year + ".txt");
            PremierLeagueManager.getInstance().retrieveMatchData("matchDataFile" + year + ".txt");

        } catch (IOException | ClassNotFoundException e) {
            LOGGER.debug("An exception has occurred");
        }

        // Taking request's body only
        JsonNode jsonNode = request.body().asJson();

        // Declaring a Match called 'match' and assigning the jsonNode to it
        Match<FootballClub> match = Json.fromJson(jsonNode, Match.class);

        // Adding the match to Premier League
        Match<FootballClub> addedMatch = PremierLeagueManager.getInstance().addMatch(match);

        try {

            // Saving the data files according to year user has given
            PremierLeagueManager.getInstance().saveClubData("clubDataFile" + year + ".txt");
            PremierLeagueManager.getInstance().saveMatchData("matchDataFile" + year + ".txt");

        } catch (IOException e) {
            LOGGER.debug("An exception has occurred");
        }
        // Creating a JsonNode with match
        JsonNode reply = Json.toJson(addedMatch);
        return created(ApplicationUtil.createResponse(reply, true));
    }

    /**
     * Method to generate a random match on a given year by the user
     *
     * @param year given year by the user
     * @return Result containing response created by application util
     */
    public Result retrieveMatch(int year) {
        try {
            // Loading the data files according year user has given
            PremierLeagueManager.getInstance().retrieveClubData("clubDataFile" + year + ".txt");
            PremierLeagueManager.getInstance().retrieveMatchData("matchDataFile" + year + ".txt");

        } catch (IOException | ClassNotFoundException e) {
            LOGGER.debug("An exception has occurred");
        }
        // Declaring a JsonNode called 'jsonObject' and generating Random match and converting to Json
        JsonNode jsonObject = Json.toJson(PremierLeagueManager.getInstance().generateRandomMatch(year));
        LOGGER.debug("In EmployeeController.retrieve(), result is: {}", jsonObject.toString());
        return ok(ApplicationUtil.createResponse(jsonObject, true));
    }

    /**
     * Method to get all the clubs in the premier league registered in the year given by the user
     *
     * @param year given by the user
     * @return Result containing response created by application util
     */
    public Result listClubs(int year) {
        try {
            // Loading the data files according year user has given
            PremierLeagueManager.getInstance().retrieveClubData("clubDataFile" + year + ".txt");
            PremierLeagueManager.getInstance().retrieveMatchData("matchDataFile" + year + ".txt");

        } catch (IOException | ClassNotFoundException e) {
            LOGGER.debug("An exception has occurred");

        }
        // Getting the clubs to the list
        List<SportsClub> result = PremierLeagueManager.getInstance().getClubsInLeague();

        // Sort the by the points
        result.sort(Collections.reverseOrder());
        LOGGER.debug("In PremierLeagueManager.listFootballClubs(), result is: {}", result.toString());

        //Declaring an object mapper
        ObjectMapper mapper = new ObjectMapper();

        // Giving the result to object mapper to convert JsonNode
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

    /**
     * Method to get all the added matches played by the clubs registered in Premier league in the year given by the user
     *
     * @param year given by the user
     * @return Result containing response created by application util
     */
    public Result listMatches(int year) {
        try {
            // Loading the data files according year user has given
            PremierLeagueManager.getInstance().retrieveClubData("clubDataFile" + year + ".txt");
            PremierLeagueManager.getInstance().retrieveMatchData("matchDataFile" + year + ".txt");
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.debug("An exception has occurred");
        }
        // Getting the clubs to the list
        List<Match<FootballClub>> result = PremierLeagueManager.getInstance().getMatchesInLeague();

        // Sort the by the date
        Collections.sort(result);

        LOGGER.debug("In PremierLeagueManager.listMatches(), result is: {}", result.toString());

        //Declaring an object mapper
        ObjectMapper mapper = new ObjectMapper();

        // Giving the result to object mapper to convert JsonNode
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

    /**
     * Method to sort the clubs registered in the Premier League by their Goals Scored
     *
     * @param year given by the user
     * @return Result containing response created by application util
     */
    public Result sortByGoals(int year) {
        try {
            // Loading the data files according year user has given
            PremierLeagueManager.getInstance().retrieveClubData("clubDataFile" + year + ".txt");
            PremierLeagueManager.getInstance().retrieveMatchData("matchDataFile" + year + ".txt");
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.debug("An exception has occurred");
        }
        // Getting the clubs to the list
        List<SportsClub> result = PremierLeagueManager.getInstance().getClubsInLeague();

        // Creating a Comparator to compare by goals
        Comparator<SportsClub> compareByGoals = Comparator.comparingInt(o -> ((FootballClub) o).getGoalsScored());

        // Taking the result and sorting in the descending order
        result.sort(compareByGoals.reversed());

        //Declaring an object mapper
        ObjectMapper mapper = new ObjectMapper();

        // Giving the result to object mapper to convert JsonNode
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

    /**
     * Method to sort the clubs registered in the Premier League by their Wins
     *
     * @param year given by the user
     * @return Result containing response created by application util
     */
    public Result sortByWins(int year) {
        try {
            // Loading the data files according year user has given
            PremierLeagueManager.getInstance().retrieveClubData("clubDataFile" + year + ".txt");
            PremierLeagueManager.getInstance().retrieveMatchData("matchDataFile" + year + ".txt");
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.debug("An exception has occurred");
        }
        // Getting the clubs to the list
        List<SportsClub> result = PremierLeagueManager.getInstance().getClubsInLeague();

        // Creating a Comparator to compare by wins
        Comparator<SportsClub> compareByWins = Comparator.comparingInt(o -> ((FootballClub) o).getWins());

        // Taking the result and sorting in the descending order
        result.sort(compareByWins.reversed());

        //Declaring an object mapper
        ObjectMapper mapper = new ObjectMapper();

        // Giving the result to object mapper to convert JsonNode
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ApplicationUtil.createResponse(jsonData, true));
    }

}
