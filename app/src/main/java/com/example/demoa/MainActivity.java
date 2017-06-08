package com.example.demoa;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
private MediaPlayer player;
private boolean isPause;
private File file;
private TextView hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }
s
    private void findViews() {
		final Button button1 = (Button) findViewById(R.id.button1);
		final Button button2 = (Button) findViewById(R.id.button2);
		final Button button3 = (Button) findViewById(R.id.button3);
	    hint = (TextView) findViewById(R.id.hint);
	    file = new File("");
	    if(file.exists()){
	    	player = MediaPlayer.create(this, Uri.parse(file.getAbsolutePath()));
	    }else{
	    	hint.setText("文件不存在");
	    	button1.setEnabled(false);
	    	return;
	    }
	    private void play(){
	    	try{
	    		player.reset();
	    		player.setDataSource(file.getAbsolutePath());
	    		player.prepare();
	    		player.start();
	    		hint.setText("正在播放音频");
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    player.setOnCompletionListener(new OnCompletionListener(){
	    public void OnCompletion(MediaPlayer mp){
	    	play();
	    }
	    	
	    });
	    button1.setOnClickListener(new OnClickListener(){
	    	public void onClick(View v){
	    		play();
	    		if(isPause){
	    			button2.setText("暂停");
	    			isPause = false;
	    		}
	    		button2.setEnabled(true);
	    		button3.setEnabled(true);
	    		button1.setEnabled(false);
	    	}
	    });
	    button2.setOnClickListener(new OnClickListener(){
	    	public void onClick(View v){
	    		if(play.isPlaying()&&!isPause){
	    			player.pause();
	    			isPause = true;
	    			((Button)v).setText("继续");
	    			hint.setText("暂停播放");
	    			button1.setEnabled(true);
	    		}else{
	    			player.start();
	    			((Button)v).setText("暂停");
	    			hint.setText("正在播放音频");
	    			isPause = false;
	    			button1.setEnabled(false);
	    		}
	    	}
	    });
	    button3.setOnClickListener(new OnClickListener(){
	    	public void onClick(View v){
	    		player.stop();
	    		hint.setText("停止播放");
	    		button2.setEnabled(false);
	    		button3.setEnabled(false);
	    		button1.setEnables(true);
	    	}
	    });
	    protected void onDestory(){
	    	if(player.isPlaying()){
	    		player.stop();
	    	}
	    	player.release();
	    	super.onDestroy();
	    }
	}
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
