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
 * This is the concrete class Match which includes Accessors and Mutators of the instance variables and
 * the toString method of the class.
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-11-15
 */
public class Match<T extends SportsClub> implements Serializable, Comparable<Match<FootballClub>> {
    private T homeClub;
    private T awayClub;
    private int goalsHomeScored;
    private int goalsAwayScored;
    private Date matchDate;

    /**
     * Default constructor of the Match concrete class
     */
    public Match() {
    }

    /**
     * Constructor of the concrete class Match
     *
     * @param homeClub        the club that played as the home team
     * @param awayClub        the club that played as the away team
     * @param goalsHomeScored number of goals scored by the home club
     * @param goalsAwayScored number of goals scored by the away club
     * @param matchDate       the date the match was played on
     */
    public Match(T homeClub, T awayClub, int goalsHomeScored, int goalsAwayScored, Date matchDate) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.goalsHomeScored = goalsHomeScored;
        this.goalsAwayScored = goalsAwayScored;
        this.matchDate = matchDate;
    }

    /**
     * Getter/Accessor of the home club from the played match
     *
     * @return SportsClub the club that played as the home team
     */
    public T getHomeClub() {
        return homeClub;
    }

    /**
     * Setter/Mutator of the home club from the played match
     *
     * @param homeClub the club that played as the home team
     */
    public void setHomeClub(T homeClub) {
        this.homeClub = homeClub;
    }

    /**
     * Getter/Accessor of the away club from the played match
     *
     * @return SportsClub the club that played as the away team
     */
    public T getAwayClub() {
        return awayClub;
    }

    /**
     * Setter/Mutator of the away club from the played match
     *
     * @param awayClub the club that played as the away team
     */
    public void setAwayClub(T awayClub) {
        this.awayClub = awayClub;
    }

    /**
     * Getter/Accessor of the goals scored by the home club from the played match
     *
     * @return int the number of goals scored by the club that played as the home team
     */
    public int getGoalsHomeScored() {
        return goalsHomeScored;
    }

    /**
     * Setter/Mutator of the goals scored by the home club from the played match
     *
     * @param goalsHomeScored the number of goals scored by the club that played as the home team
     */
    public void setGoalsHomeScored(int goalsHomeScored) {
        this.goalsHomeScored = goalsHomeScored;
    }

    /**
     * Getter/Accessor of the goals scored by the away club from the played match
     *
     * @return int the number of goals scored by the club that played as the away team
     */
    public int getGoalsAwayScored() {
        return goalsAwayScored;
    }

    /**
     * Setter/Mutator of the goals scored by the away club from the played match
     *
     * @param goalsAwayScored the number of goals scored by the club that played as the away team
     */
    public void setGoalsAwayScored(int goalsAwayScored) {
        this.goalsAwayScored = goalsAwayScored;
    }

    /**
     * Getter/Accessor of the date of the played match
     *
     * @return Date the date which the match was played on
     */
    public Date getMatchDate() {
        return matchDate;
    }

    /**
     * Setter/Mutator of the date of the played match
     *
     * @param matchDate the date which the match was played on
     */
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    /**
     * toString method of the class concrete class Match
     *
     * @return a string containing all the instance variable with the respective instantiation
     */
    @Override
    public String toString() {
        return "Match[" +
                "homeClub= " + homeClub +
                ", awayClub= " + awayClub +
                ", goalsHomeScored= " + goalsHomeScored +
                ", goalsAwayScored= " + goalsAwayScored +
                ", matchDate= " + matchDate +
                "]";
    }

    /**
     * Equals method of the Match class
     *
     * @param o object containing any type
     * @return a boolean if the object is an instance of SportsClub
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match<?> match = (Match<?>) o;
        return getGoalsHomeScored() == match.getGoalsHomeScored() && getGoalsAwayScored() == match.getGoalsAwayScored() && Objects.equals(getHomeClub(), match.getHomeClub()) && Objects.equals(getAwayClub(), match.getAwayClub()) && Objects.equals(getMatchDate(), match.getMatchDate());
    }

    /**
     * Hashcode method of the class Match
     *
     * @return int containing the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getHomeClub(), getAwayClub(), getGoalsHomeScored(), getGoalsAwayScored(), getMatchDate());
    }

    /**
     * compareTo method of the class Match
     *
     * @return int containing the integer comparison of the match class
     */
    @Override
    public int compareTo(Match<FootballClub> match) {
        if (this.matchDate.getYear() > match.getMatchDate().getYear()) {
            return 1;
        } else if (this.matchDate.getYear() < match.matchDate.getYear()) {
            return -1;
        } else {
            if (this.matchDate.getMonth() > match.matchDate.getMonth()) {
                return 1;
            } else if (this.matchDate.getMonth() < match.matchDate.getMonth()) {
                return -1;
            } else {
                return Integer.compare(this.matchDate.getDay(), match.matchDate.getDay());
            }
        }
    }
}
