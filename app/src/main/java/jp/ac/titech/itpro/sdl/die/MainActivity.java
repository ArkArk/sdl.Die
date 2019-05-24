package jp.ac.titech.itpro.sdl.die;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private final static String TAG = MainActivity.class.getSimpleName();

    private GLSurfaceView glView;
    private SimpleRenderer renderer;

    private Cube cube;
    private Pyramid pyramid;
    private Cylinder cylinder;

    private boolean touchingSeekBar = false;
    private int MAX_TIME = 60; // 1sec
    private int timeToAutoRotation = MAX_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        glView = findViewById(R.id.gl_view);
        final SeekBar seekBarX = findViewById(R.id.seekbar_x);
        final SeekBar seekBarY = findViewById(R.id.seekbar_y);
        final SeekBar seekBarZ = findViewById(R.id.seekbar_z);
        seekBarX.setMax(360);
        seekBarY.setMax(360);
        seekBarZ.setMax(360);
        seekBarX.setOnSeekBarChangeListener(this);
        seekBarY.setOnSeekBarChangeListener(this);
        seekBarZ.setOnSeekBarChangeListener(this);

        renderer = new SimpleRenderer(new Runnable(){
            public void run(){
                if (!touchingSeekBar) {
                    timeToAutoRotation--;
                }
                if (timeToAutoRotation <= 0) {
                    int newProgressX = seekBarX.getProgress() + 1;
                    if (newProgressX > seekBarX.getMax()) {
                        newProgressX = 0;
                    }
                    seekBarX.setProgress(newProgressX);

                    int newProgressY = seekBarY.getProgress() + 2;
                    if (newProgressY > seekBarY.getMax()) {
                        newProgressY = 0;
                    }
                    seekBarY.setProgress(newProgressY);

                    int newProgressZ = seekBarZ.getProgress() + 3;
                    if (newProgressZ > seekBarZ.getMax()) {
                        newProgressZ = 0;
                    }
                    seekBarZ.setProgress(newProgressZ);
                }
            }
        });
        cube = new Cube();
        pyramid = new Pyramid();
        cylinder = new Cylinder(1, 2, 8);
        renderer.setObj(cube);
        glView.setRenderer(renderer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        glView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        glView.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected");
        switch (item.getItemId()) {
        case R.id.menu_cube:
            renderer.setObj(cube);
            break;
        case R.id.menu_pyramid:
            renderer.setObj(pyramid);
            break;
        case R.id.menu_cylinder:
            renderer.setObj(cylinder);
            break;
        }
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
        case R.id.seekbar_x:
            renderer.rotateObjX(progress);
            break;
        case R.id.seekbar_y:
            renderer.rotateObjY(progress);
            break;
        case R.id.seekbar_z:
            renderer.rotateObjZ(progress);
            break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        timeToAutoRotation = MAX_TIME;
        touchingSeekBar = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        touchingSeekBar = false;
    }
}
