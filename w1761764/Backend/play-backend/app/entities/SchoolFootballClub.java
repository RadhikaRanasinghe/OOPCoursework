/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */
package entities;

import java.util.Objects;

/**
 * This is the concrete class SchoolFootballClub which extends FootballClub class and inherits those
 * instance variables and methods.
 * Also it includes Accessors and Mutators of the instance variables and
 * the toString method of the class.
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-11-15
 */
public class SchoolFootballClub extends FootballClub {

    private String schoolName;
    private int underAge;

    /**
     * Default constructor of the SchoolFootballClub concrete class
     */
    public SchoolFootballClub() {
    }

    /**
     * Constructor of the SchoolFootballClub concrete class
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
     * @param schoolName     name of the school which owns the football club
     * @param underAge       under which age category the football club exists
     */
    public SchoolFootballClub(String name, String location, int wins, int defeats, int draws, int goalsScored, int goalsReceived, int goalDifference, int points, int matchesPlayed, String schoolName, int underAge) {
        super(name, location, wins, defeats, draws, goalsScored, goalsReceived, goalDifference, points, matchesPlayed);
        this.schoolName = schoolName;
        this.underAge = underAge;
    }

    /**
     * Constructor of the SchoolFootballClub concrete class
     *
     * @param schoolName name of the school which owns the football club
     * @param underAge   under which age category the football club exists
     */
    public SchoolFootballClub(String schoolName, int underAge) {
        this.schoolName = schoolName;
        this.underAge = underAge;
    }

    /**
     * Getter/Accessor of the name of the school instance variable of the SchoolFootballClub
     *
     * @return String a String containing the name of the School of the SchoolFootballClub
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Setter/Mutator of the name of the school instance variable of the SchoolFootballClub
     *
     * @param schoolName the name of the school of the SchoolFootballClub
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * Getter/Accessor of the under age category the football club exists instance variable of the SchoolFootballClub
     *
     * @return int age of the under age category of the SchoolFootballClub
     */
    public int getUnderAge() {
        return underAge;
    }

    /**
     * Setter/Mutator of the under age category the football club exists instance variable of the SchoolFootballClub
     *
     * @param underAge age of the under age category of the SchoolFootballClub
     */
    public void setUnderAge(int underAge) {
        this.underAge = underAge;
    }

    /**
     * toString method of the class concrete class SchoolFootballClub
     *
     * @return a string containing all the instance variable with the respective instantiation
     */
    public String toString() {
        return "SchoolFootballClub[ " +
                "clubName= '" + super.getName() + "'" +
                ", clubLocation= '" + super.getLocation() + "'" +
                ", numOfWins= " + super.getWins() +
                ", numOfDefeats= " + super.getDefeats() +
                ", numOfDraws= " + super.getDraws() +
                ", numOfGoalsReceived= " + super.getGoalsReceived() +
                ", numOfGoalsScored= " + super.getGoalsScored() +
                ", currentNumOfPoints= " + super.getPoints() +
                ", numOfMatchesPlayed= " + super.getMatchesPlayed() +
                ", schoolName= '" + schoolName + "'" +
                ", underAge= " + underAge +
                "]";
    }

    /**
     * Equals method of the SchoolFootballClub
     *
     * @param o object containing any type
     * @return a boolean if the object is an instance of SportsClub
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolFootballClub)) return false;
        if (!super.equals(o)) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return getUnderAge() == that.getUnderAge() && getSchoolName().equals(that.getSchoolName());
    }

    /**
     * Hashcode method of the class SchoolFootballClub
     *
     * @return int containing the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSchoolName(), getUnderAge());
    }

    /**
     * compareTo method of the class SchoolFootballClub
     *
     * @return int containing the integer comparison of the SchoolFootballClub class
     */
    public int compareTo(SchoolFootballClub otherFootballClub) {
        return Integer.compare(super.getPoints(), otherFootballClub.getPoints());
    }
}
