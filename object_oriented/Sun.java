import java.util.ArrayList;

public class Sun extends CelestialBody
{
    private boolean hasGreaterVolume;
    ArrayList<Planet> planets = new ArrayList<>();

    Sun()
    {

    }

    Sun(String thisName, double thisDiameter, double thisCircumference)
    {
        super(thisName, thisDiameter, thisCircumference);
    }

    public boolean getHasGreaterVolume()
    {
        return hasGreaterVolume;
    }

    public void setHasGreaterVolume(boolean thisHasGreaterVolume)
    {
        hasGreaterVolume = thisHasGreaterVolume;
    }

    public void addPlanet(Planet thisPlanet)
    {
        planets.add(thisPlanet);
    }

    public void calcHasGreaterVolume()
    {

    }

    public String toString()
    {
        String str = super.toString() + "Has Greater Volume: " + hasGreaterVolume + ", Planets: ";
        for(Planet p: planets)
        {
            str += "\n\t" + p.toString();
        }
        return str;
    }
}