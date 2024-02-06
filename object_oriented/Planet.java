import java.util.ArrayList;

public class Planet extends CelestialBody
{
    private double distanceFromSun;
    private double orbitalPeriod;
    private ArrayList<Moon> moons = new ArrayList<>();

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

    public setOrbitalPeriod(double thisOrbitalPeriod)
    {
        orbitalPeriod = thisOrbitalPeriod;
    }

}