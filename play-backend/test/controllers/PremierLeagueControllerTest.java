/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */
package controllers;

import akka.util.ByteString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import play.Application;
import play.http.HttpEntity;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static play.mvc.Http.Status.CREATED;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

/**
 * This is Test class of the PremierLeagueController Class
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-12-27
 */
class PremierLeagueControllerTest {
    // Declaring a Test year
    private final int year = 200;

    // Declaring four test clubs
    private FootballClub clubA;
    private FootballClub clubB;
    private FootballClub clubC;
    private FootballClub clubD;

    // Declaring three test matches
    private Match<FootballClub> matchA;
    private Match<FootballClub> matchB;
    private Match<FootballClub> matchC;

    /**
     * Initialize test fixtures before each test method
     */
    @BeforeEach
    void creation() {
        // Declaring PremierLeagueManager called 'premierLeagueManager'
        PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

        //Clearing the club array and match array
        premierLeagueManager.getClubsInLeague().clear();
        premierLeagueManager.getMatchesInLeague().clear();

        // Adding test clubs to the premier league
        clubA = new FootballClub("club A", "location A");
        clubB = new FootballClub("club B", "location B");
        clubC = new FootballClub("club C", "location C");
        clubD = new FootballClub("club D", "location D");
        PremierLeagueManager.getInstance().addClub(clubA);
        PremierLeagueManager.getInstance().addClub(clubB);
        PremierLeagueManager.getInstance().addClub(clubC);
        PremierLeagueManager.getInstance().addClub(clubD);

        // Adding test matches to the premier league
        matchA = new Match<FootballClub>(clubA, clubB, 5, 4, new Date(30, 10, year));
        PremierLeagueManager.getInstance().addMatch(matchA);

        matchB = new Match<FootballClub>(clubC, clubD, 6, 3, new Date(27, 9, year));
        PremierLeagueManager.getInstance().addMatch(matchB);

        matchC = new Match<FootballClub>(clubB, clubC, 2, 2, new Date(5, 5, year));
        PremierLeagueManager.getInstance().addMatch(matchC);

        // Saving a test file to the premier league
        try {
            premierLeagueManager.saveClubData("clubDataFile" + year + ".txt");
            premierLeagueManager.saveMatchData("matchDataFile" + year + ".txt");
        } catch (IOException ignored) {
        }
    }

    /**
     * Test method to retrieve random Match on the given year
     */
    @Test
    void retrieveMatch() {
        Result result = new PremierLeagueController().retrieveMatch(year);
        assertEquals(OK, result.status());
    }


    /**
     * Test method to retrieve all the clubs in the premier league in the given year
     */
    @Test
    void listClubs() {
        try {
            // Result output given out by the listClubs method
            Result result = new PremierLeagueController().listClubs(year);

            // Taking the body of the result which is a ByteString
            ByteString responseBody = ((HttpEntity.Strict) result.body()).data();
            // Decoding the body of the result to string
            String res = responseBody.decodeString("UTF-8");

            // Declaring an Object Mapper
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(res);

            // Taking only the field response out of the Json
            JsonNode body = actualObj.get("response");
            // Adding each Football club to a List
            ArrayList<SportsClub> list = new ArrayList<>();
            for (int i = 0; i < body.size(); i++) {
                FootballClub footballClub = Json.fromJson(body.get(i), FootballClub.class);
                list.add(footballClub);
            }

            // After sorting by points, the expected outcomes are as follows
            assertEquals(clubC, list.get(0));
            assertEquals(clubA, list.get(1));
            assertEquals(clubB, list.get(2));
            assertEquals(clubD, list.get(3));

            // Checking match status
            assertEquals(OK, result.status());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test method to retrieve all the matches in the premier league in the given year
     */
    @Test
    void listMatches() {
        try {
            // Result output given out by the listMatches method
            Result result = new PremierLeagueController().listMatches(year);

            // Taking the body of the result which is a ByteString
            ByteString responseBody = ((HttpEntity.Strict) result.body()).data();
            // Decoding the body of the result to string
            String res = responseBody.decodeString("UTF-8");

            // Declaring an Object Mapper
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(res);

            // Taking only the field response out of the Json
            JsonNode body = actualObj.get("response");

            ArrayList<Match<FootballClub>> list = new ArrayList<>();

            for (int i = 0; i < body.size(); i++) {
                Match<FootballClub> match = Json.fromJson(body.get(i), Match.class);
                list.add(match);
            }

            // The added matches are listed as follows
            assertEquals(matchA, list.get(0));
            assertEquals(matchB, list.get(1));
            assertEquals(matchC, list.get(2));

            // Checking match status
            assertEquals(OK, result.status());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test method to retrieve all the clubs sorted according goals in the given year
     */
    @Test
    void sortByGoals() {
        try {
            // Result output given out by the sortByGoals method
            Result result = new PremierLeagueController().sortByGoals(year);

            // Taking the body of the result which is a ByteString
            ByteString responseBody = ((HttpEntity.Strict) result.body()).data();
            // Decoding the body of the result to string
            String res = responseBody.decodeString("UTF-8");

            // Declaring an Object Mapper
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(res);

            // Taking only the field response out of the Json
            JsonNode body = actualObj.get("response");
            // Adding each Football club to a List
            ArrayList<SportsClub> list = new ArrayList<>();
            for (int i = 0; i < body.size(); i++) {
                FootballClub footballClub = Json.fromJson(body.get(i), FootballClub.class);
                list.add(footballClub);
            }

            // After sorting by goals, the expected outcomes are as follows
            assertEquals(clubC, list.get(0));
            assertEquals(clubB, list.get(1));
            assertEquals(clubA, list.get(2));
            assertEquals(clubD, list.get(3));

            // Checking match status
            assertEquals(OK, result.status());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method to retrieve all the clubs sorted according wins in the given year
     */
    @Test
    void sortByWins() {
        try {
            // Result output given out by the sortByWins method
            Result result = new PremierLeagueController().sortByWins(year);

            // Taking the body of the result which is a ByteString
            ByteString responseBody = ((HttpEntity.Strict) result.body()).data();
            // Decoding the body of the result to string
            String res = responseBody.decodeString("UTF-8");

            // Declaring an Object Mapper
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(res);

            // Taking only the field response out of the Json
            JsonNode body = actualObj.get("response");
            // Adding each Football club to a List
            ArrayList<SportsClub> list = new ArrayList<>();
            for (int i = 0; i < body.size(); i++) {
                FootballClub footballClub = Json.fromJson(body.get(i), FootballClub.class);
                list.add(footballClub);
            }

            // After sorting by wins, the expected outcomes are as follows
            assertEquals(clubA, list.get(0));
            assertEquals(clubC, list.get(1));
            assertEquals(clubB, list.get(2));
            assertEquals(clubD, list.get(3));

            // Checking match status
            assertEquals(OK, result.status());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method to create a a POST request with the random match in the given year
     */
    @Test
    void createRandomMatch() {
        // Using Guice for dependency injection
        Application application = new GuiceApplicationBuilder().build();
        // Starts the test
        Helpers.start(application);
        // Generating a random match to be added
        Match<FootballClub> randMatch = PremierLeagueManager.getInstance().generateRandomMatch(year);
        //Converting the match to a JsonNode
        JsonNode jsonNode = Json.toJson(randMatch);

        // Creating a request to send the JsonNode as a Post request
        Http.RequestBuilder request = new Http.RequestBuilder()
                .bodyJson(jsonNode)
                .method(POST)
                .uri(routes.PremierLeagueController.createRandomMatch(year).url());
        Result result = route(application, request);
        assertEquals(CREATED, result.status());
    }
}