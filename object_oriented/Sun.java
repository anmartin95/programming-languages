import java.util.ArrayList;

public class Sun extends CelestialBody
{
    private bool hasGreaterVolume;
    ArrayList<Planet> planets = new ArrayList<>();

    public bool getHasGreaterVolume()
    {
        return hasGreaterVolume;
    }

    public void setHasGreaterVolume(bool thisHasGreaterVolume)
    {
        hasGreaterVolume = thisHasGreaterVolume;
    }

    public void calcHasGreaterVolume()
    {

    }
}