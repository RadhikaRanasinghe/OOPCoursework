/*
 * Name     :   Radhika Ranasinghe
 * UoW ID   :   w1761764
 * "I confirm that I understand what plagiarism / collusion / contract cheating is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely
 * my own. Any work from other authors is duly referenced and acknowledged."
 */

package entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is the abstract class SportsClub which includes Accessors and Mutators of the instance variables and
 * the toString method of the class.
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-11-15
 */
@JsonDeserialize(as = FootballClub.class)
public abstract class SportsClub implements Serializable, Comparable<SportsClub> {
    private String name;
    private String location;


    /**
     * Default constructor of the abstract class SportClub
     */
    public SportsClub() {
    }

    /**
     * Constructor of the abstract class SportsClub.
     *
     * @param name     name of the club
     * @param location location of the club
     */
    public SportsClub(String name, String location) {
        this.name = name;
        this.location = location;

    }

    /**
     * Getter/Accessor of the name of the SportsClub
     *
     * @return the name of the SportsClub
     */
    public String getName() {
        return name;
    }

    /**
     * Setter/Mutator of the name of the SportsClub
     *
     * @param name the name to be set as the name of the SportsClub
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Getter/Accessor of the location of the SportClub
     *
     * @return the location of the SportClub
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter/Mutator of the location of the SportsClub
     *
     * @param location the location of the SportClub
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * toString method of the class SportsClub
     *
     * @return a string containing all the instance variable with the respective instantiation
     */
    @Override
    public String toString() {
        return "SportsClub[" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ']';
    }

    /**
     * Equals method of the SportsClub class
     *
     * @param o object containing any type
     * @return a boolean if the object is an instance of SportsClub
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportsClub)) return false;
        SportsClub club = (SportsClub) o;
        return getName().equals(club.getName()) && getLocation().equals(club.getLocation());
    }

    /**
     * Hashcode method of the class SportsClub
     *
     * @return int containing the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLocation());
    }
}
