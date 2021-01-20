/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */
package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is the concrete class FootballClub which extends SportsClub class and inherits those
 * instance variables and methods.
 * Also it includes Accessors and Mutators of the instance variables and
 * the toString method of the class.
 *
 * @author Radhika Ranasinghe
 * @version 2.0
 * @since 2020-11-15
 */
public class FootballClub extends SportsClub implements Serializable {
    private int wins;
    private int defeats;
    private int draws;
    private int matchesPlayed;
    private int goalsReceived;
    private int goalsScored;
    private int goalDifference;
    private int points;

    /**
     * Default constructor of the Football class
     */
    public FootballClub() {
    }

    public FootballClub(String name, String location) {
        super(name, location);
    }

    /**
     * Constructor of the Football class
     *
     * @param wins           number of win achieved by the club
     * @param defeats        number of defeats accounted by the club
     * @param draws          number of draws achieved by the club
     * @param goalsScored    number of goals scored by the club
     * @param goalsReceived  number of goals received by the club
     * @param goalDifference number of goals difference
     * @param points         number of points club currently has
     * @param matchesPlayed  number of matches played by the club
     */
    public FootballClub(int wins, int defeats, int draws, int goalsScored, int goalsReceived, int goalDifference, int points, int matchesPlayed) {
        this.wins = wins;
        this.defeats = defeats;
        this.draws = draws;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
        this.goalDifference = goalDifference;
        this.points = points;
        this.matchesPlayed = matchesPlayed;
    }

    /**
     * Constructor of the Football class
     *
     * @param name           name of the club
     * @param location       location of the club
     * @param wins           number of win achieved by the club
     * @param defeats        number of defeats accounted by the club
     * @param draws          number of draws achieved by the club
     * @param goalsScored    number of goals scored by the club
     * @param goalsReceived  number of goals received by the club
     * @param goalDifference number of goals difference
     * @param points         number of points club currently has
     * @param matchesPlayed  number of matches played by the club
     */
    public FootballClub(String name, String location, int wins, int defeats, int draws, int goalsScored, int goalsReceived, int goalDifference, int points, int matchesPlayed) {
        super(name, location);
        this.wins = wins;
        this.defeats = defeats;
        this.draws = draws;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
        this.goalDifference = goalDifference;
        this.points = points;
        this.matchesPlayed = matchesPlayed;
    }

    /**
     * Getter/Accessor of the number of goals received instance variable of the FootballClub
     *
     * @return int the number of goals received by the FootballClub
     */
    public int getGoalsReceived() {
        return goalsReceived;
    }

    /**
     * Setter/Mutator of the number of goals received instance variable of the FootballClub
     *
     * @param goalsReceived the number of goals received by the FootballClub
     */
    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    /**
     * Getter/Accessor of the number of goals scored instance variable of the FootballClub
     *
     * @return int the number of goals scored by the FootballClub
     */
    public int getGoalsScored() {
        return goalsScored;
    }

    /**
     * Setter/Mutator of the number of goals scored instance variable of the FootballClub
     *
     * @param goalsScored the number of goals scored by the FootballClub
     */
    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    /**
     * Getter/Accessor of the number of current points instance variable of the FootballClub
     *
     * @return int the number of current point the FootballClub has
     */
    public int getPoints() {
        return points;
    }

    /**
     * Setter/Mutator of the number of current points instance variable of the FootballClub
     *
     * @param points the number of current point the FootballClub has
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Getter/Accessor of the number of matches played by the FootballClub
     *
     * @return the number of matches played by the SportsClub
     */
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /**
     * Setter/Mutator of the number of matches played by the FootballClub
     *
     * @param matchesPlayed the number of matches played by the SportsClub
     */
    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    /**
     * Getter/Accessor of the number of wins of the SportClub
     *
     * @return the number of wins the SportsClub has achieved
     */
    public int getWins() {
        return wins;
    }

    /**
     * Setter/Mutator of the number of wins of the SportsClub
     *
     * @param wins the number of wins the SportsClub has achieved
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Getter/Accessor of the number of defeats of the SportClub
     *
     * @return the number of defeats the SportsClub has accounted
     */
    public int getDefeats() {
        return defeats;
    }

    /**
     * Setter/Mutator of the number of defeats of the SportsClub
     *
     * @param defeats the number of defeats the SportsClub has accounted
     */
    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    /**
     * Getter/Accessor of the number of draws of the SportClub
     *
     * @return the number of draws the SportsClub has achieved
     */
    public int getDraws() {
        return draws;
    }

    /**
     * Setter/Mutator of the number of draws of the SportsClub
     *
     * @param draws the number of draws the SportsClub has achieved
     */
    public void setDraws(int draws) {
        this.draws = draws;
    }

    /**
     * toString method of the class concrete class FootballClub
     *
     * @return a string containing all the instance variable with the respective instantiation
     */
    public int getGoalDifference() {
        return goalsScored - goalsReceived;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    /**
     * toString method of the class Football CLub
     *
     * @return a string containing all the instance variables with the respective instantiation
     */
    @Override
    public String toString() {
        return "FootballClub[" +
                "name=" + super.getName() +
                ", location=" + super.getLocation() +
                ", wins=" + wins +
                ", defeats=" + defeats +
                ", draws=" + draws +
                ", matchesPlayed=" + matchesPlayed +
                ", goalsReceived=" + goalsReceived +
                ", goalsScored=" + goalsScored +
                ", goalDifference=" + goalDifference +
                ", points=" + points +
                ']';
    }

    /**
     * Equals method of the FootballClub
     *
     * @param o object containing any type
     * @return a boolean if the object is an instance of FootballClub
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FootballClub)) return false;
        if (!super.equals(o)) return false;
        FootballClub that = (FootballClub) o;
        return getWins() == that.getWins() && getDefeats() == that.getDefeats() && getDraws() == that.getDraws() && getMatchesPlayed() == that.getMatchesPlayed() && getGoalsReceived() == that.getGoalsReceived() && getGoalsScored() == that.getGoalsScored() && getGoalDifference() == that.getGoalDifference() && getPoints() == that.getPoints();
    }

    /**
     * Hashcode method of the class FootballClub
     *
     * @return int containing the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWins(), getDefeats(), getDraws(), getMatchesPlayed(), getGoalsReceived(), getGoalsScored(), getGoalDifference(), getPoints());
    }

    /**
     * compareTo method of the class FootballClub
     *
     * @return int containing the integer comparison of points of the instances of Football class
     */
    @Override
    public int compareTo(SportsClub otherFootballClub) {
        if (this.points > ((FootballClub) otherFootballClub).getPoints()) {
            return 1;
        } else if (this.points < ((FootballClub) otherFootballClub).getPoints()) {
            return -1;
        } else {
            return Integer.compare(this.getGoalDifference(), ((FootballClub) otherFootballClub).getGoalDifference());
        }
    }

}
