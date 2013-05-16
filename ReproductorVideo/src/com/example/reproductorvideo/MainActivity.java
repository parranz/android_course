package com.example.reproductorvideo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
	private VideoView mVideoView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		// de forma alternative si queremos un streaming usar
		// mVideoView.setVideoURI(Uri.parse(URLstring));
		mVideoView.setVideoPath("/mnt/sdcard/P8170014.mp4");
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.start();
		mVideoView.requestFocus();
	}
}
