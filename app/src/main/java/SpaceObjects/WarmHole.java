package SpaceObjects;

import android.widget.ImageView;

/**
 * Created by anton on 2016-02-28.
 */
public class WarmHole extends SpaceObject
{
    int level;

    public WarmHole(double mass, double radius, double[] coordinates, ImageView appearance, int level)
    {
        super(mass, radius, coordinates, appearance);
        this.level = level;
    }
}
