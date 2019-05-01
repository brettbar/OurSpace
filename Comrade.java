// Brett Barinaga and Andrew Flagstead
// CPSC 450 
// OurSpace
// Comrade.java

import java.util.ArrayList;

public class Comrade {
    private String name;
    private int age;
    private String occupation;
    private String city;
    private int partyLoyalty;
    private ArrayList<Connection> comrades;

    Comrade (String theName, int theAge, String theOccupation, String theCity, int Loyalty) {
        this.name = theName;
        this.age = theAge;
        this.occupation = theOccupation;
        this.city = theCity;
        this.partyLoyalty = Loyalty;
        this.comrades = new ArrayList<Connection>();
    }

    Comrade () {
        this.name = "";
        this.age = 0;
        this.occupation = "";
        this.city = "";
        this.partyLoyalty = -1;
        this.comrades = new ArrayList<Connection>();
    }
    
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getOcc() {
        return this.occupation;
    }

    public String getCity() {
        return this.city;
    }

    public int getPartyLoyalty() {
        return this.partyLoyalty;
    }

    public ArrayList<Connection> getComrades() {
        return this.comrades;
    }

    public ArrayList<Comrade> getJustComrades() {
        ArrayList<Comrade> comradeNames = new ArrayList<Comrade>();
        for (Connection con : comrades) {
            comradeNames.add(con.getComrade());
        }
        return comradeNames;
    }
}
