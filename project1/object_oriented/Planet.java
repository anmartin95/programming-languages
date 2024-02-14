import java.util.ArrayList;
import java.lang.*;
import java.text.DecimalFormat;

public class Planet extends CelestialBody
{
    private double distanceFromSun;
    private double orbitalPeriod;
    public ArrayList<Moon> moons = new ArrayList<>();

    // planet constructor - five params
    Planet(String thisName, double thisDiameter, double thisCircumference, double thisDistance, double thisOrbitalPeriod)
    {
        super(thisName, thisDiameter, thisCircumference);
        if(thisDistance == 0.0 && thisOrbitalPeriod != 0.0) // case: orbital period input, distance to be calculated
        {
            orbitalPeriod = thisOrbitalPeriod;
            calcDistance(thisOrbitalPeriod);

        }
        else if (thisDistance != 0.0 && thisOrbitalPeriod == 0.0) // case: distance input, orbital period to be calculated
        {
            distanceFromSun = thisDistance;
            calcOrbitalPeriod(thisDistance);
            
        }
        else if (thisDistance != 0.0 && thisOrbitalPeriod != 0.0) // case: both distance and orbital period are input
        {
            distanceFromSun = thisDistance;
            orbitalPeriod = thisOrbitalPeriod;
        }
    }

    // --- setters and getters ---
    public double getDistanceFromSun()
    {
        return distanceFromSun;
    }

    public void setDistanceFromSun(double thisDistance)
    {
        distanceFromSun = thisDistance;
        calcOrbitalPeriod(thisDistance);
    }

    public double getOrbitalPeriod()
    {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double thisOrbitalPeriod)
    {
        orbitalPeriod = thisOrbitalPeriod;
        calcDistance(thisOrbitalPeriod);
    }

    // calculates distance given orbital period as param
    public void calcDistance(double thisOrbitalPeriod)
    {
        distanceFromSun = Math.pow((Math.pow(thisOrbitalPeriod, 2)), (1.0/3.0));
    }

    // calculates orbital period given distance as param
    public void calcOrbitalPeriod(double thisDistance)
    {
        orbitalPeriod = Math.pow(Math.pow(thisDistance, 3), 0.5);
    }

    // adds moon object to moons arraylist
    public void addMoon(Moon thisMoon)
    {
        moons.add(thisMoon);
    }

    // overrides toString method - calls CelestialBody toString and adds Planet fields
    public String toString()
    {
        DecimalFormat f = new DecimalFormat("##.00");
        String str = super.toString() + "Distance From Sun: " + f.format(distanceFromSun) + " au, Orbital Period: " + f.format(orbitalPeriod) + " yr";
        // prints moon data
        if (!moons.isEmpty())
            str += ", Moons: ";
            for(Moon m: moons)
            {
                str += "\n\t\t" + m.toString();
            }
        return str;
    }

}