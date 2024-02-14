import java.util.ArrayList;

public class Sun extends CelestialBody
{
    private boolean hasGreaterVolume;
    ArrayList<Planet> planets = new ArrayList<>();

    // constructor  - no params
    Sun()
    {
        // intentionally left blank
    }

    // constructor - three params
    Sun(String thisName, double thisDiameter, double thisCircumference)
    {
        // calls CelestialBody constructor
        super(thisName, thisDiameter, thisCircumference);
    }

    // --- setters and getters ---
    public boolean getHasGreaterVolume()
    {
        return hasGreaterVolume;
    }

    public void setHasGreaterVolume(boolean thisHasGreaterVolume)
    {
        hasGreaterVolume = thisHasGreaterVolume;
    }

    // add planet object to planets arraylist
    public void addPlanet(Planet thisPlanet)
    {
        planets.add(thisPlanet);
    }

    // calculates sun and planet's volumes and compares sum
    public void calcHasGreaterVolume(double diam)
    {
        // calc sun's volume
        double sunVol = (4.0/3.0)*Math.PI*Math.pow((diam/2.0), 3);
        // calc total planet volume
        double totalPlanetVol = 0.0;
        for(Planet p: planets)
        {
            double planetVol = (4.0/3.0)*Math.PI*Math.pow((p.getDiameter()/2.0), 3);
            totalPlanetVol += planetVol;
        }
        
        // System.out.println("Total Planet Vol: " + totalPlanetVol + ", Sun Vol: " + sunVol);
        // update hasGreaterVolume value
        if (sunVol > totalPlanetVol)
        {
            setHasGreaterVolume(true);
        }
        else
        {
            setHasGreaterVolume(false);
        }

    }

    // toString method override 
    public String toString()
    {
        // call super class toString
        String str = super.toString() + "Planets: ";
        // print planet data from toString
        for(Planet p: planets)
        {
            str += "\n\t" + p.toString();
        }
        return str;
    }
}