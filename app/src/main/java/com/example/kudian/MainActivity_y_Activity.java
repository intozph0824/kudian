package com.example.kudian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity_y_Activity extends AppCompatActivity implements View.OnClickListener{
    ImageView nextIv,playIv,lastIv;
    TextView singerTv,songTv;
    RecyclerView musicRv;
    //数据源
    List<LocalMusicBean_y_Activity>mDatas;
    private LocalMusicAdapterActivity adapter;
    private int position;
    //记录当前正在播放的音乐的位置
    int currnetPlayPosition = -1;
    //记录暂停音乐时进度条位置
    int currentPausePositionInSong = 0;
    MediaPlayer mediaPlayer;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_y);
        verifyStoraPermissions(this);

        initView();
        mediaPlayer =new MediaPlayer();
        mDatas = new ArrayList<>();
        //创建适配器对象
        adapter = new LocalMusicAdapterActivity(this, mDatas);
        musicRv.setAdapter(adapter);
        //设置布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        musicRv.setLayoutManager(layoutManager);

        //加载本地数据源
        loadLocalMusicDate();
        //设置每一项点击事件
        setEventListener();

    }

    public static void verifyStoraPermissions(MainActivity_y_Activity mainActivity_y_activity) {
        try{
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(mainActivity_y_activity,"android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(mainActivity_y_activity,PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void setEventListener() {
        /*设置每一项的点击事件*/
        adapter.setOnItemClickListener(new LocalMusicAdapterActivity.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                currnetPlayPosition = position;
                LocalMusicBean_y_Activity musicBean =mDatas.get(position);
                /*根据传入对象播放音乐*/
                //设置底部显示的歌手名和歌曲名
                playMusicInMusicBean(musicBean);
            }
        });
    }

    public void playMusicInMusicBean(LocalMusicBean_y_Activity musicBean) {
        singerTv.setText(musicBean.getSinger());
        songTv.setText(musicBean.getSong());
        stopMusic();
        //重置多媒体播放器
        mediaPlayer.reset();
        //设置新的播放路径
        try {
            mediaPlayer.setDataSource(musicBean.getPath());
            playMusic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*点击播放按钮播放音乐，或者暂停重新播放
     * 播放音乐有两种情况
     * 1.从暂停到播放
     * 2.从停止到播放*/
    private void playMusic() {
        /*播放音乐的函数*/
        if (mediaPlayer!=null&&!mediaPlayer.isPlaying()) {
            if (currentPausePositionInSong == 0) {
                try {
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                //从暂停到播放
                mediaPlayer.seekTo(currentPausePositionInSong);
                mediaPlayer.start();
            }

            playIv.setImageResource(R.mipmap.icon_pause);
        }
    }
    private void pauseMusic() {
        /*暂停音乐的函数*/
        if (mediaPlayer!=null&&mediaPlayer.isPlaying()) {
            currentPausePositionInSong = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
            playIv.setImageResource(R.mipmap.icon_play);
        }
    }

    private void stopMusic() {
        /*停止音乐的函数*/
        if(mediaPlayer!=null){
            currentPausePositionInSong =0;
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
            mediaPlayer.stop();
            playIv.setImageResource(R.mipmap.icon_play);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMusic();
    }

    private void loadLocalMusicDate() {
        /*加载本地存储当中的音乐文件到集合当中*/
        //获取ContentResolver对象
        ContentResolver resolver =getContentResolver();
        //获取本地音乐存储的Urid地址
        Uri uri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        //开始查询地址
        Cursor cursor = resolver.query(uri, null, null, null, null);
        //便利cursor
        int id=0;
        while (cursor.moveToNext()) {
            String song=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String singer=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String album=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            id++;
            String sid=String.valueOf(id);
            String path=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            long duration=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            SimpleDateFormat sdf=new SimpleDateFormat("mm;ss");
            String time=sdf.format(new Date(duration));
            //将一行当中的数据封装到对象当中
            LocalMusicBean_y_Activity bean=new LocalMusicBean_y_Activity(sid,song,singer,album,time,path);
            mDatas.add(bean);
        }
        //数据源变化，提示适配器更新
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        /*初始化控件函数*/
        nextIv = findViewById(R.id.local_music_bottom_iv_next);
        playIv = findViewById(R.id.local_music_bottom_iv_play);
        lastIv = findViewById(R.id.local_music_bottom_iv_last);
        singerTv = findViewById(R.id.local_music_bottom_tv_singer);
        songTv = findViewById(R.id.local_music_bottom_tv_song);
        musicRv = findViewById(R.id.local_music_rv);
        nextIv.setOnClickListener(this);
        lastIv.setOnClickListener(this);
        playIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.local_music_bottom_iv_last:
                if (currnetPlayPosition == 0) {
                    Toast.makeText(this,"没有上一首了",Toast.LENGTH_SHORT).show();
                    return;
                }
                currnetPlayPosition =currnetPlayPosition-1;
                LocalMusicBean_y_Activity lastBean=mDatas.get(currnetPlayPosition);
                playMusicInMusicBean(lastBean);
                break;
            case R.id.local_music_bottom_iv_next:
                if (currnetPlayPosition == mDatas.size()-1) {
                    Toast.makeText(this,"这是最后一首歌",Toast.LENGTH_SHORT).show();
                    return;
                }
                currnetPlayPosition =currnetPlayPosition+1;
                LocalMusicBean_y_Activity nextBean=mDatas.get(currnetPlayPosition);
                playMusicInMusicBean(nextBean);
                break;
            case R.id.local_music_bottom_iv_play:
                if (currnetPlayPosition == -1) {
                    //并没有选中要播放的音乐
                    Toast.makeText(this,"请选择想要播放的音乐",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mediaPlayer.isPlaying()) {
                    //此时处于播放状态，需要暂停音乐
                    pauseMusic();
                }else {
                    //此时没有播放音乐，点击开始播放音乐
                }
                break;
        }

    }


}
