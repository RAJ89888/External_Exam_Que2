package com.example.external_exam_que2_rajvohera;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    Switch sound,vibration,led,showbanners,showcontent,lockscreen;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sound=findViewById(R.id.sound);
        vibration=findViewById(R.id.vibration);
        led=findViewById(R.id.led);
        showbanners=findViewById(R.id.showBanner);
        showcontent=findViewById(R.id.ShowContent);
        lockscreen=findViewById(R.id.LockScreen);
        Save=findViewById(R.id.Save);
        SharedPreferences sharedPreferences=getSharedPreferences("switch_data", Context.MODE_PRIVATE);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity.this);
                View bottomsheetview= LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_bottom_sheet,null);
                bottomSheetDialog.setContentView(bottomsheetview);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                Button saved=bottomsheetview.findViewById(R.id.save);
                saved.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(sound.isChecked()==true)
                        {

                            editor.putString("sound","true");
                        }
                        if(vibration.isChecked()==true)
                        {
                            editor.putString("vibration","true");

                        } if(led.isChecked()==true)
                        {
                            editor.putString("led","true");

                        } if(showbanners.isChecked()==true)
                        {
                            editor.putString("showbanners","true");

                        }if(showcontent.isChecked()==true)
                        {
                            editor.putString("showcontent","true");

                        }if(lockscreen.isChecked()==true)
                        {
                            editor.putString("lockscreen","true");
                        }
                        else {
                            editor.putString("sound","false");
                            editor.putString("showcontent","false");
                            editor.putString("showbanners","false");
                            editor.putString("led","false");
                            editor.putString("vibration","false");

                            editor.putString("lockscreen","false");

                        }
                        editor.apply();
                        bottomSheetDialog.cancel();
                    }
                });

                bottomSheetDialog.show();
            }
        });
        SharedPreferences getshared=getSharedPreferences("switch_data", Context.MODE_PRIVATE);
        sound.setChecked(Boolean.parseBoolean(getshared.getString("sound",null)));
        led.setChecked(Boolean.parseBoolean(getshared.getString("led",null)));
        showcontent.setChecked(Boolean.parseBoolean(getshared.getString("showcontent",null)));
        lockscreen.setChecked(Boolean.parseBoolean(getshared.getString("lockscreen",null)));
        showbanners.setChecked(Boolean.parseBoolean(getshared.getString("showbanners",null)));
        vibration.setChecked(Boolean.parseBoolean(getshared.getString("vibration",null)));

    }
}