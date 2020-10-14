package com.ldq.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ldq.appnhac.Fragment.Fragment_Dia_Nhac;
import com.ldq.appnhac.Fragment.Fragment_Play_Danh_Sach_Bai_Hat;
import com.ldq.appnhac.Fragment.ViewPagerPlaylistNhac;
import com.ldq.appnhac.Model.BaiHat;
import com.ldq.appnhac.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txttimesong,txttotaltimesong;
    SeekBar sktime;
    ImageButton imgplay,imgrepeat,imgpre,imgrandom,imgnext;
    ViewPager viewpagerplaynhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Bai_Hat fragment_play_danh_sach_bai_hat;
    MediaPlayer mediaPlayer;

    int position =0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    public static  ArrayList<BaiHat> arrayBaihat = new ArrayList<>();

    public ViewPagerPlaylistNhac adapternhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        GetDataFromIntent();
        init();
        eventClick();


    }

    private void eventClick() {
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1)!=null){
                    if (arrayBaihat.size()>0){
                        fragment_dia_nhac.PlayNhac(arrayBaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else{
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);

        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat==false){
                   if(checkrandom==true) {
                       checkrandom = false;
                       imgrepeat.setImageResource(R.drawable.iconsyned);
                       imgrandom.setImageResource(R.drawable.iconsuffle);
                   }
                   imgrepeat.setImageResource(R.drawable.iconsyned);
                   repeat =true;
                }else{
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat=false;
                }
            }
        });

        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom==false){
                    if(repeat==true) {
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom =true;
                }else{
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom=false;
                }
            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayBaihat.size()>0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position<(arrayBaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat==true){
                            if (position ==0){
                                position=arrayBaihat.size();
                            }
                            position-=1;
                        }
                        if (checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(arrayBaihat.size());
                            if(index==position){
                                position=index-1;
                            }
                            position=index;
                        }
                        if (position>arrayBaihat.size()-1){
                            position=0;
                        }
                        new PlayMp3().execute(arrayBaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.PlayNhac(arrayBaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(arrayBaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });


        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayBaihat.size()>0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position<(arrayBaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position<0){
                            position=arrayBaihat.size()-1;
                        }

                        if (repeat==true){
                            position+=1;
                        }
                        if (checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(arrayBaihat.size());
                            if(index==position){
                                position=index-1;
                            }
                            position=index;
                        }
                        new PlayMp3().execute(arrayBaihat.get(position).getLinkbaihat());
                        fragment_dia_nhac.PlayNhac(arrayBaihat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(arrayBaihat.get(position).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });
    }


    private void GetDataFromIntent() {
        Intent intent = getIntent();
        arrayBaihat.clear();
        if(intent!=null){
            if(intent.hasExtra("cakhuc")){
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                arrayBaihat.add(baiHat);
            }

            if(intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> arrayListbaihat = intent.getParcelableArrayListExtra("cacbaihat");
                arrayBaihat = arrayListbaihat;
            }
        }
    }

    private void init() {

        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txttimesong = findViewById(R.id.textviewtimesong);
        txttotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgpre = findViewById(R.id.imagebuttonpreview);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        viewpagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        imgnext = findViewById(R.id.imagebuttonnext);

        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                arrayBaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac= new Fragment_Dia_Nhac();
        fragment_play_danh_sach_bai_hat = new Fragment_Play_Danh_Sach_Bai_Hat();

        adapternhac = new ViewPagerPlaylistNhac(getSupportFragmentManager());

        adapternhac.addFragment(fragment_play_danh_sach_bai_hat);
        adapternhac.addFragment(fragment_dia_nhac);

        viewpagerplaynhac.setAdapter(adapternhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);

        if(arrayBaihat.size()>0){
            getSupportActionBar().setTitle(arrayBaihat.get(0).getTenbaihat());
            new PlayMp3().execute(arrayBaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }

    }

    class PlayMp3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            try {
                super.onPostExecute(baihat);
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                    mediaPlayer.setDataSource(baihat);
                    mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }

    public void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer!=null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);

        final  Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next==true){
                    if(arrayBaihat.size()>0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position<(arrayBaihat.size())){
                            imgplay.setImageResource(R.drawable.iconpause);
                            position++;
                            if (repeat==true){
                                if (position ==0){
                                    position=arrayBaihat.size();
                                }
                                position-=1;
                            }
                            if (checkrandom==true){
                                Random random = new Random();
                                int index = random.nextInt(arrayBaihat.size());
                                if(index==position){
                                    position=index-1;
                                }
                                position=index;
                            }
                            if (position>arrayBaihat.size()-1){
                                position=0;
                            }
                            new PlayMp3().execute(arrayBaihat.get(position).getLinkbaihat());
                            fragment_dia_nhac.PlayNhac(arrayBaihat.get(position).getHinhbaihat());
                            getSupportActionBar().setTitle(arrayBaihat.get(position).getTenbaihat());

                        }
                    }
                    imgpre.setClickable(false);
                    imgnext.setClickable(false);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgpre.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    },5000);
                    next=false;
                    handler1.removeCallbacks(this);
                }else{
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }


}
