package SpaceObjects;

import android.widget.ImageView;

/**
 * Created by anton on 2016-02-28.
 */
public class Planet extends SpaceObject
{
    String name;
    double crucialDistance;

    public Planet(double mass, double radius, double[] coordinates, ImageView appearance, String name)
    {
        super(mass, radius, coordinates, appearance);
        this.name = name;
        crucialDistance = Planet.calculateCrucialDistance(radius);
    }

    public static double calculateCrucialDistance(double radius)
    {
        return 0;
    }


}
