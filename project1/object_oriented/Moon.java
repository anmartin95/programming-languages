public class Moon extends CelestialBody
{
    // moon constructor - three params
    Moon(String thisName, double thisDiameter, double thisCircumference)
    {
        super(thisName, thisDiameter, thisCircumference);
    }

    // overrides toString method - calls CelestialBody toString to print data
    public String toString()
    {
        return super.toString();
    }
}