import java.io.*;
import java.lang.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// compile & run:
// javac -cp "lib/*" CelestialBody.java Sun.java Planet.java Moon.java Main.java
// java -cp "lib/*:." Main   
public class Main {
    public static void main(String[] args) {
        // load and parse json data
        JSONParser parser = new JSONParser();
        ArrayList<CelestialBody> cbodies = new ArrayList<CelestialBody>();
        try {
            Object obj = parser.parse(new FileReader("JSONPlain.txt"));
            JSONObject sun = (JSONObject)obj;
            // add sun data to Sun object
            String name = (String)sun.get("Name");
            double diameter = sun.containsKey("Diameter") ? (double)sun.get("Diameter") : 0.0;
            double circumference = sun.containsKey("Circumference") ? (double)sun.get("Circumference") : 0.0;
            Sun newSun = new Sun(name, diameter, circumference); 
            // add planet data to planet objects
            JSONArray planets = (JSONArray) sun.get("Planets");
            for (Object planetObj : planets) {
                JSONObject planet = (JSONObject) planetObj;
                String planetName = (String) planet.get("Name");
                double planetDiameter = planet.containsKey("Diameter") ? (double)planet.get("Diameter") : 0.0;
                double planetCircumference = planet.containsKey("Circumference") ? (double)planet.get("Circumference") : 0.0;
                double orbitalPeriod = planet.containsKey("OrbitalPeriod") ? (double)planet.get("OrbitalPeriod") : 0.0;
                double distanceFromSun = planet.containsKey("DistanceFromSun") ? (double)planet.get("DistanceFromSun") : 0.0;
                JSONArray moonsArray = planet.containsKey("Moons") ? (JSONArray) planet.get("Moons") : null;
                Planet newPlanet = new Planet(planetName, planetDiameter, planetCircumference, distanceFromSun, orbitalPeriod);
                // add moon data to moon objects
                if (moonsArray != null) {
                    for (Object moonObj : moonsArray) {
                        JSONObject moon = (JSONObject) moonObj;
                        String moonName = (String) moon.get("Name");
                        double moonDiameter = moon.containsKey("Diameter") ? (double)moon.get("Diameter") : 0.0;
                        double moonCircumference = moon.containsKey("Circumference") ? (double)moon.get("Circumference") : 0.0;
                        Moon newMoon = new Moon(moonName, moonDiameter, moonCircumference);
                        // add moon object to planet's moon arraylist
                        newPlanet.addMoon(newMoon);
                    }
                }
                // add planet object to sun's planet arraylist
                newSun.addPlanet(newPlanet);
            }
            // print all solar system data
            System.out.println(" ---- Solar System ---- \nSun:");
            System.out.println(newSun);
            
            // has greater vol calculations
            newSun.calcHasGreaterVolume(newSun.getDiameter());
            System.out.println("All the planets’ volumes added together could fit in the Sun: " + newSun.getHasGreaterVolume());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}