import java.util.ArrayList;

public class Planet extends CelestialBody
{
    private double distanceFromSun;
    private double orbitalPeriod;
    public ArrayList<Moon> moons = new ArrayList<>();

    Planet(String thisName, double thisDiameter, double thisCircumference, double thisDistance, double thisOrbitalPeriod)
    {
        super(thisName, thisDiameter, thisCircumference);
        distanceFromSun = thisDistance;
        orbitalPeriod = thisOrbitalPeriod;
    }

    public double getDistanceFromSun()
    {
        return distanceFromSun;
    }

    public void setDistanceFromSun(double thisDistance)
    {
        distanceFromSun = thisDistance;
    }

    public double getOrbitalPeriod()
    {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double thisOrbitalPeriod)
    {
        orbitalPeriod = thisOrbitalPeriod;
    }

    public void addMoon(Moon thisMoon)
    {
        moons.add(thisMoon);
    }

    public String toString()
    {
        String str = super.toString() + "Distance From Sun: " + distanceFromSun + ", Orbital Period: " + orbitalPeriod;
        if (!moons.isEmpty())
            str += ", Moons: ";
            for(Moon m: moons)
            {
                str += "\n\t\t" + m.toString();
            }
        return str;
    }

}