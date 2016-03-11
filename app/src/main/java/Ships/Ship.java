package Ships;

import android.widget.ImageView;

/**
 * Created by anton on 2016-02-29.
 */
public abstract class Ship
{
    public double[] position;
    public double[] velocity;

    public ImageView appearance;

    public Ship(ImageView appearance, double[] position)
    {
        this.appearance = appearance;
        this.position = position;
        velocity = new double[2];

        repaint();
    }

    public void repaint()
    {
        this.appearance.setX((float) position[0]);
        this.appearance.setY((float) position[1]);
    }



}
