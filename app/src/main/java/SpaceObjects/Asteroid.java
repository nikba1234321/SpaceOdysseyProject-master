package SpaceObjects;

import android.widget.ImageView;

/**
 * Created by anton on 2016-02-28.
 */
public class Asteroid extends SpaceObject
{
    double hp;
    int score;

    public Asteroid(double mass, double radius, double[] coordinates, ImageView appearance, double hp, int score)
    {
        super(mass, radius, coordinates, appearance);
        this.hp = hp;
        this.score = score;
    }
}
