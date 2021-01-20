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
 * This is the concrete class Date which includes Accessors and Mutators of the instance variables and
 * the toString method of the class.
 *
 * @author Radhika Ranasinghe
 * @version 1.0
 * @since 2020-11-15
 */
public class Date implements Serializable {
    private int day;
    private int month;
    private int year;

    /**
     * Default constructor of the Date concrete class
     */
    public Date() {
    }

    /**
     * Constructor of the Date concrete class
     *
     * @param day   the given day
     * @param month the given month
     * @param year  the given year
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Getter/Accessor of the day instance variable
     *
     * @return int the day of the instance variable
     */
    public int getDay() {
        return day;
    }

    /**
     * Setter/Mutator of the day instance variable
     *
     * @param day the day to be set as the instance variable
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Getter/Accessor of the month instance variable
     *
     * @return int the month of the instance variable
     */
    public int getMonth() {
        return month;
    }

    /**
     * Setter/Mutator of the month instance variable
     *
     * @param month the month to be set as the instance variable
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Getter/Accessor of the year instance variable
     *
     * @return int the year of the instance variable
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter/Mutator of the year instance variable
     *
     * @param year the year to be set as the instance variable
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * toString method of the class concrete class Date
     *
     * @return a string containing all the instance variable with the respective instantiation
     */
    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    /**
     * Equals method of the Date class
     *
     * @param o object containing any type
     * @return a boolean if the object is an instance of Date
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return getDay() == date.getDay() && getMonth() == date.getMonth() && getYear() == date.getYear();
    }

    /**
     * Hashcode method of the Date class
     *
     * @return int containing the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getDay(), getMonth(), getYear());
    }

    /**
     * compareTo method of the class Date
     *
     * @return int containing the integer comparison of the Date class
     */
    public int compareTo(Date date) {
        if (this.year > date.getYear()) {
            return 1;
        } else if (this.year < date.getYear()) {
            return -1;
        } else {
            if (this.month > date.getMonth()) {
                return 1;
            } else if (this.month < date.getMonth()) {
                return -1;
            } else {
                return Integer.compare(this.day, date.getDay());
            }
        }
    }
}
