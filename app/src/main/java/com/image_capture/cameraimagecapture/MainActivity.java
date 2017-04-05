package com.image_capture.cameraimagecapture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Long Press Action", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    public void changeRingerMode(View v){
        AudioManager audioManager =
                (AudioManager) getSystemService
                        (Context.AUDIO_SERVICE);
        int ringMode = AudioManager.RINGER_MODE_NORMAL;

        switch(v.getId()){
            case R.id.button1:
                ringMode = AudioManager.RINGER_MODE_SILENT;
                break;
            case R.id.button2:
                ringMode = AudioManager.RINGER_MODE_NORMAL;
                break;
            case R.id.button3:
                ringMode = AudioManager.RINGER_MODE_VIBRATE;
                break;
        }
        audioManager.setMode(ringMode);

        Toast.makeText(MainActivity.this, "Ringer Mode Changed", Toast.LENGTH_LONG).show();

        audioManager.adjustVolume(AudioManager.ADJUST_RAISE, 0);
    }

    public void captureImage(View v){
        Intent intent =
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 8000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 8000){
            Bitmap bitmap =
                    (Bitmap) data.getExtras().get("data");

            ImageButton imv =
                    (ImageButton) findViewById(R.id.imageButton);
            imv.setImageBitmap(bitmap);
        }
    }
}
