// Brett Barinaga and Andrew Flagstead
// CPSC 450 
// OurSpace
// Connection.java

public class Connection 
{
    private Comrade person;
    private int camraderie;

    public Connection(Comrade theComrade, int theCamraderie) {
        this.person = theComrade;
        this.camraderie = theCamraderie;
    }

    public Comrade getComrade() {
        return person;
    }

    public int getCamraderie() {
        return camraderie;
    }
}