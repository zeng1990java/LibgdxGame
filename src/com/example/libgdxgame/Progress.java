package com.example.libgdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Progress implements ApplicationListener {

	private ProgressBar mProgressBar;
	private Stage mStage;
	
	@Override
	public void create() {
		mProgressBar = new ProgressBar(0, 0);
		mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mStage.addActor(mProgressBar);
	}

	@Override
	public void dispose() {
		mProgressBar.dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 1f, 1f, 0f);
		
		mStage.act(Gdx.graphics.getDeltaTime());
		mStage.draw();
		if(mProgressBar.getProgress() < 100){
			mProgressBar.setProgress(mProgressBar.getProgress()+0.5f);
		}
		
		if(mProgressBar.getProgress()>=100){
			mProgressBar.setProgress(0f);
		}
	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {

	}

}
