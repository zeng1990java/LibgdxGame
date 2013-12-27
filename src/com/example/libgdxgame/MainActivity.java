package com.example.libgdxgame;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class MainActivity extends AndroidApplication  {

	Progress mProgress;
	MyGame mMyGame;
	ApplicationGame mAppGame;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mProgress = new Progress();
		mMyGame = new MyGame(this);
		mAppGame = new ApplicationGame(mMyGame);
		initialize(mAppGame, false);
	}

}
