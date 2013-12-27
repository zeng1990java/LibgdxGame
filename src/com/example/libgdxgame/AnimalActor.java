package com.example.libgdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

public class AnimalActor extends Actor implements Disposable {

	ArrayList<Texture> mTexArr = new ArrayList<Texture>();
	ArrayList<TextureRegion> mTexReArr = new ArrayList<TextureRegion>();
	Animation mAnimation;
	
	AssetManager mManager;
	float mStateTime;
	TextureRegion currentFrame;
	TextureRegion[] walksFrame;
	
	public AnimalActor(AssetManager manager){
		super();
		this.mManager = manager;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		mStateTime += Gdx.graphics.getDeltaTime();
		
		// 得到下一帧
		currentFrame = mAnimation.getKeyFrame(mStateTime, true);
		// 以(0,0)绘制为起点（左下角为0,0）画出动画，大小128*128
		batch.draw(currentFrame, 0, 0, 128, 128);
	}
	
	@Override
	public Actor hit(float x, float y, boolean touchable) {
		return this;
	}
	
	public void initResource(){
		Texture tex;
		int j;
		
		for(int i = 1; i < 30; i++){
			mTexArr.add(mManager.get("animal/"+i+".png", Texture.class));
		}
		
		for (int i = 0; i < mTexArr.size(); i++) {
			tex = mTexArr.get(i);
			TextureRegion tempTexRe = new TextureRegion(tex);
			mTexReArr.add(tempTexRe);
		}
		
		j = mTexReArr.size();
		
		walksFrame = new TextureRegion[j];
		for (int i = 0; i < j; i++) {
			walksFrame[i] = mTexReArr.get(i);
		}
		
		// 设置为0.06s一帧
		mAnimation = new Animation(0.06f, walksFrame);
	}

	
	@Override
	public void dispose() {
		// 释放所有资源
		for (int i = 0; i < mTexArr.size(); i++) {
			mTexArr.get(i).dispose();
		}
	}


}
