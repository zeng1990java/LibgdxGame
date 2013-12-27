package com.example.libgdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class ProgressBar extends Actor implements Disposable {

	private Texture mPlatform;
	private Texture mBar;
	private int mHeight;
	private int mWidth;
	private float mProgress;
	
	private float mPowerX;
	private float mPowerY;
	
	public ProgressBar(int x, int y){
		super();
		mPlatform = new Texture(Gdx.files.internal("black.png"));
		mBar = new Texture(Gdx.files.internal("green.png"));
		mHeight = Gdx.graphics.getHeight();
		mWidth = Gdx.graphics.getWidth();
		
		mPowerX = mWidth / 400f;
		mPowerY = mHeight / 800f;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(mPlatform, (Gdx.graphics.getWidth()-mBar.getWidth()*mPowerX)/2, 0,mPlatform.getWidth()*mPowerX,mPlatform.getHeight()*mPowerY);
	    batch.draw(mBar,(Gdx.graphics.getWidth()-mBar.getWidth()*mPowerX)/2,0,mBar.getWidth()*mProgress/100f*mPowerX,mBar.getHeight()*mPowerY);
	}
	
	public void setProgress(float progress){
		this.mProgress = progress;
	}
	
	public float getProgress(){
		return this.mProgress;
	}
	
	@Override
	public Actor hit(float x, float y, boolean touchable) {
		return null;
	}
	
	@Override
	public void dispose() {
		mPlatform.dispose();
		mBar.dispose();
	}

}
