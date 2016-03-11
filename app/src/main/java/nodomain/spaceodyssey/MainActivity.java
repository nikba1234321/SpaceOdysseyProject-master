package nodomain.spaceodyssey;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Ships.MainShip;

public class MainActivity extends Activity implements SensorEventListener
{
    private MainShip mainShip;

    public int score = 0;
    public int level = 0;

    private TextView textViewSpeedX;
    private TextView textViewSpeedY;
    private TextView textViewSpeed;
    private TextView textViewPositionX;
    private TextView textViewPositionY;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);

        TextView textViewPlay = (TextView) findViewById(R.id.textView);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(150);
        animation.setStartOffset(10);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        textViewPlay.startAnimation(animation);

        View layoutLoadingScreen = findViewById(R.id.loading_screen);
        layoutLoadingScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initiateMain_Menu();
            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    public void initiateMain_Menu()
    {
        setContentView(R.layout.main_menu);

        TextView textViewScore = (TextView) findViewById(R.id.textViewScore);
        TextView textViewLevel = (TextView) findViewById(R.id.textViewLevel);
        Button buttonNewGame = (Button) findViewById(R.id.buttonNewGame);
        Button  buttonContinue = (Button) findViewById(R.id.buttonContinue);

        textViewScore.setText(getString(R.string.score, score));
        textViewLevel.setText(getString(R.string.level, level));

        if (level == 0)
        {
            buttonContinue.setEnabled(false);
            textViewScore.setEnabled(false);
            textViewLevel.setEnabled(false);
        }

        buttonNewGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newGame();
            }
        });
    }

    public void newGame()
    {
        setContentView(R.layout.game);

        mainShip = new MainShip((ImageView) findViewById(R.id.imageViewShip), new double[]{875, 450});

        textViewSpeedX = (TextView) findViewById(R.id.textViewSpeedX);
        textViewSpeedY = (TextView) findViewById(R.id.textViewSpeedY);
        textViewSpeed = (TextView) findViewById(R.id.textViewSpeed);

        textViewPositionX = (TextView) findViewById(R.id.textViewPositionX);
        textViewPositionY = (TextView) findViewById(R.id.textViewPositionY);



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (mainShip != null)
        {
            double thetaX = event.values[1];
            double thetaY = event.values[2];

            double minTheta = 2;

            double maxThetaX = 30;
            double maxThetaY = 30;

            double delta = mainShip.maxSpeed;
            thetaX = thetaX > 0 ? Math.min(thetaX, maxThetaX) : Math.max(thetaX, -maxThetaX);
            thetaY = thetaY > 0 ? Math.min(thetaY, maxThetaY) : Math.max(thetaY, -maxThetaY);

            double coefficient = 2 * (Math.max(Math.abs(thetaX), Math.abs(thetaY))) / (maxThetaX + maxThetaY);

            mainShip.velocity[0] = thetaX > minTheta || thetaX < -minTheta ? -coefficient * thetaX / Math.pow(thetaX * thetaX + thetaY * thetaY, 0.5) : 0;
            mainShip.velocity[1] = thetaY > minTheta || thetaY < -minTheta ? coefficient * thetaY / Math.pow(thetaX * thetaX + thetaY * thetaY, 0.5) : 0;

            mainShip.position[0] += mainShip.velocity[0] * delta;
            mainShip.position[1] += mainShip.velocity[1] * delta;

            textViewSpeedX.setText(getString(R.string.velocityX, mainShip.velocity[0]));
            textViewSpeedY.setText(getString(R.string.velocityY, mainShip.velocity[1]));
            textViewSpeed.setText(getString(R.string.velocity, Math.pow(Math.pow(mainShip.velocity[0], 2) + Math.pow(mainShip.velocity[1], 2), 0.5)));
            textViewPositionX.setText(getString(R.string.positionX, mainShip.position[0]));
            textViewPositionY.setText(getString(R.string.positionY, mainShip.position[1]));
            mainShip.repaint();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
