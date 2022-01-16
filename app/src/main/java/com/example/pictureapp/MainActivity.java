package com.example.pictureapp;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pictureapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final int pic_id = 123;


    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ImageView clickImageId;
    private TextRecognizer tr;
    private SharedPreferences prefs;
    static private List<CharSequence> arr = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getSharedPreferences("PhotoAppPreferences", Context.MODE_PRIVATE);
        tr = new TextRecognizer.Builder(getApplicationContext()).build();


        this.clickImageId = (ImageView)this.findViewById(R.id.imageView1);

        setSupportActionBar(binding.toolbar);

        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePicIntent, pic_id);
                } catch (ActivityNotFoundException e) {
                    System.out.println("Error!");
                }
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, binding.textView.getText(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + binding.textView.getText())));
            }
        });


        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence s = binding.textView.getText();
                String sFinal = String.valueOf(s);
                if(!sFinal.isEmpty()) {
                    arr.add(binding.textView.getText());
                    Toast.makeText(MainActivity.this, "Added item to list.", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putInt("list_size", arr.size());
                    for (int i = 0; i < arr.size(); i++)
                        edit.putString("item_" + i, String.valueOf(arr.get(i)));
                    edit.commit();
                } else {
                    Toast.makeText(MainActivity.this, "Error! No text detected.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // after on CLick we are using finish to close and then just after that
                // we are calling startactivity(getIntent()) to open our application
                finish();
                startActivity(getIntent());

                // this basically provides animation
                overridePendingTransition(0, 0);
                String time = System.currentTimeMillis() + "";

                // Showing a toast message at the time when we are capturing screenshot
                Toast.makeText(MainActivity.this, "Current time in milliseconds after app restart" + time, Toast.LENGTH_SHORT).show();
                arr.clear();
                prefs.edit().clear().commit();

            }
        });

        int size = prefs.getInt("list_size", 0);

        arr.clear();
        for(int i=0; i<size; i++) {
            arr.add(prefs.getString("item_" + i, null));
        }




    }

    protected static List<CharSequence> getArr() {
        return arr;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            clickImageId.setImageBitmap(photo);
            Frame imageFrame = new Frame.Builder()
                    .setBitmap(photo)
                    .build();
            String imageText = "";
            SparseArray<TextBlock> textBlocks = tr.detect(imageFrame);

            for (int i = 0; i < textBlocks.size(); i++) {
                TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));
                imageText = textBlock.getValue();
                binding.textView.setText(imageText);
            }

            if(imageText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Error! No text detected.", Toast.LENGTH_SHORT).show();
            }



        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}