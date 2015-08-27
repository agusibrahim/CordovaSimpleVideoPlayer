package com.irdev.cordova.simplevideoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import android.media.*;
public class SimpleVideoPlayerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("", "onCreate");
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_video_player);
		//VideoView videoView = (VideoView) findViewById(R.id.videoPlayerView);
		
		LinearLayout linearLayout = new LinearLayout(this);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		linearLayout.setLayoutParams(llp);
		linearLayout.setBackgroundColor(Color.parseColor("#000000"));
		
		VideoView videoView = new VideoView(this);
		LinearLayout.LayoutParams vLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		vLayout.gravity = Gravity.CENTER;
		videoView.setLayoutParams(vLayout);
		
		linearLayout.addView(videoView);
		setContentView(linearLayout);
		
		
		String uri = getIntent().getExtras().getString("video_url");
		Boolean useControls = getIntent().getExtras().getBoolean("user_controls");
		
		
		if(useControls){
			MediaController mediaController = new MediaController(this);
			videoView.setMediaController(mediaController);
			//mediaController.setAnchorView(videoView);
		}
		String fileName = "android.resource://"+  getPackageName() + "/raw/"+uri;
		if(uri.toLowerCase().startsWith("file://")||uri.toLowerCase().startsWith("http://")||uri.toLowerCase().startsWith("https://")){
			fileName=uri;
		}
		videoView.setVideoURI(Uri.parse(fileName));
		videoView.requestFocus();
		videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
			{           
				public void onCompletion(MediaPlayer mp) 
				{
					finish();
					// Do whatever u need to do here

				}           
			});   
		videoView.start();
		
	}


}
