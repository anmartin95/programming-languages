public class CelestialBody
{
    private String name;
    private double diameter;
    private double circumference;

    CelestialBody(String thisName, double thisDiameter, double thisCircumference)
    {
        name = thisName;
        if(thisDiameter == 0 && thisCircumference != 0)
        {
            circumference = thisCircumference;

        }
        else if (thisDiameter != 0 && thisCircumference == 0)
        {
            diameter = thisDiameter;
            
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String thisName)
    {
        name = thisName;
    }

    public double getDiameter()
    {
        return diameter;
    }

    public void setDiameter(double thisDiameter)
    {
        diameter = thisDiameter;
    }

    public double getCircumference()
    {
        return circumference;
    }

    public void setCircumference(double thisCircumference)
    {
        circumference = thisCircumference;
    }
}