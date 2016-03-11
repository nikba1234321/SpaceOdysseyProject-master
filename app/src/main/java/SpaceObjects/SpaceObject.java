package SpaceObjects;

import android.widget.ImageView;

/**
 * Created by anton on 2016-02-28.
 */
public abstract class SpaceObject
{
    double mass;
    double radius;
    double[] coordinates;
    ImageView appearance;

    public SpaceObject(double mass, double radius, double[] coordinates, ImageView appearance)
    {
        this.mass = mass;
        this.radius = radius;
        this.coordinates = coordinates;
        this.appearance = appearance;
    }



}
