/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */
package entities;

/**
 * This is the concrete class UniversityFootballClub which extends FootballClub class and inherits those
 * instance variables and methods.
 * Also it includes Accessors and Mutators of the instance variables and
 * the toString method of the class.
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-11-15
 */
public class UniversityFootballClub extends FootballClub {

    private String universityName;
    private int underAge;

    /**
     * Default constructor of the concrete class FootballClub
     */
    public UniversityFootballClub() {
    }

    /**
     * Constructor of the concrete class UniversityFootballClub.
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
     * @param universityName name of the university which owns the football club
     * @param underAge       under which age category the football club exists
     */
    public UniversityFootballClub(String name, String location, int wins, int defeats, int draws, int goalsScored, int goalsReceived, int goalDifference, int points, int matchesPlayed, String universityName, int underAge) {
        super(name, location, wins, defeats, draws, goalsScored, goalsReceived, goalDifference, points, matchesPlayed);
        this.universityName = universityName;
        this.underAge = underAge;
    }

    /**
     * Constructor of the concrete class UniversityFootballClub.
     *
     * @param universityName name of the university which owns the football club
     * @param underAge       under which age category the football club exists
     */
    public UniversityFootballClub(String universityName, int underAge) {
        this.universityName = universityName;
        this.underAge = underAge;
    }

    /**
     * Getter/Accessor of the name of the university instance variable of the UniversityFootballClub
     *
     * @return String a String containing the name of the University of the UniversityFootballClub
     */
    public String getUniversityName() {
        return universityName;
    }

    /**
     * Setter/Mutator of the name of the university instance variable of the UniversityFootballClub
     *
     * @param universityName the name of the University of the UniversityFootballClub
     */
    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    /**
     * Getter/Accessor of the under age category the football club exists instance variable of the UniversityFootballClub
     *
     * @return int age of the under age category of the UniversityFootballClub
     */
    public int getUnderAge() {
        return underAge;
    }

    /**
     * Setter/Mutator of the under age category the football club exists instance variable of the UniversityFootballClub
     *
     * @param underAge age of the under age category of the UniversityFootballClub
     */
    public void setUnderAge(int underAge) {
        this.underAge = underAge;
    }

    /**
     * toString method of the class concrete class UniversityFootballClub
     *
     * @return a string containing all the instance variable with the respective instantiation
     */
    public String toString() {
        return "UniversityFootballClub[" +
                "clubName= '" + super.getName() + "'" +
                ", clubLocation= '" + super.getLocation() + "'" +
                ", numOfWins= " + super.getWins() +
                ", numOfDefeats= " + super.getDefeats() +
                ", numOfDraws= " + super.getDraws() +
                ", numOfGoalsReceived= " + super.getGoalsReceived() +
                ", numOfGoalsScored= " + super.getGoalsScored() +
                ", currentNumOfPoints= " + super.getPoints() +
                ", numOfMatchesPlayed= " + super.getMatchesPlayed() +
                ", universityName= '" + universityName + "'" +
                ", underAge= " + underAge +
                "]";
    }

    /**
     * Equals method of the UniversityFootballClub class
     *
     * @param o object containing any type
     * @return a boolean if the object is an instance of UniversityFootballClub
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UniversityFootballClub)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UniversityFootballClub universityFootballClub = (UniversityFootballClub) o;
        return getUnderAge() == universityFootballClub.getUnderAge() &&
                getUniversityName().equals(universityFootballClub.getUniversityName());
    }

    /**
     * Hashcode method of the class UniversityFootballClub
     *
     * @return int containing the hashcode
     */
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.underAge;
        result = prime * result + ((this.universityName == null) ? 0 : this.universityName.hashCode());
        return result;
    }

    /**
     * compareTo method of the class UniversityFootballClub
     *
     * @return int containing the integer comparison of the UniversityFootballClub class
     */
    public int compareTo(UniversityFootballClub otherFootballClub) {
        return Integer.compare(super.getPoints(), otherFootballClub.getPoints());
    }
}
