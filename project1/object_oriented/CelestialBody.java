import java.lang.*;
import java.text.DecimalFormat;

public class CelestialBody
{
    private String name;
    private double diameter;
    private double circumference;

    // constructor - no params
    CelestialBody()
    {
        // intentionally left blank
    }

    // constructor - three params
    CelestialBody(String thisName, double thisDiameter, double thisCircumference)
    {
        name = thisName;
        if(thisDiameter == 0.0 && thisCircumference != 0.0) // case: circumference input, diameter to be calculated
        {
            circumference = thisCircumference;
            calcDiameter(thisCircumference);

        }
        else if (thisDiameter != 0.0 && thisCircumference == 0.0) // case: diameter input, circumference to be calculated
        {
            diameter = thisDiameter;
            calcCircumference(thisDiameter);
            
        }
        else if (thisDiameter != 0.0 && thisCircumference != 0.0) // case: both diameter and circumference are input
        {
            diameter = thisDiameter;
            circumference = thisCircumference;
        }
    }

    // --- setters and getters ---
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
        // update circumference if diameter changes
        calcCircumference(thisDiameter);
    }

    public double getCircumference()
    {
        return circumference;
    }

    public void setCircumference(double thisCircumference)
    {
        circumference = thisCircumference;
        // update diameter if circumference changes
        calcDiameter(thisCircumference);
    }

    // calculate diameter given circumference as param - and modify diamater field
    public void calcDiameter(double thisCircumference)
    {
        diameter = thisCircumference/Math.PI;
    }

    // calculate circumference given diameter as param - and modify circumference field
    public void calcCircumference(double thisDiameter)
    {
        circumference = thisDiameter * Math.PI;
    }

    // overrides toString - returns print message for name diamater and circumference for all celesital bodies
    public String toString()
    {
        DecimalFormat f = new DecimalFormat("##.00");
        String s = "Name: " + name + ", Diameter: " + f.format(diameter) + " km, Circumference: " + f.format(circumference) + " km, ";
        return s;
    }
}