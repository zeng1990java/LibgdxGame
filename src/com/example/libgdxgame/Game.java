package com.example.libgdxgame;

import java.util.ArrayList;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements ApplicationListener {

	private static final String TAG = "Game";
	
	private SpriteBatch mSpriteBatch;
	private BitmapFont mBitmapFont;
	
	private ParticleEffect mParticle;
	private ParticleEffect mTemp;
	private ParticleEffectPool mParticlePool;
	private ArrayList<ParticleEffect> mParticleList;
	
	/** �������һ�δ�����ʱ��˷��������� */
	@Override
	public void create() {
		Log.i(TAG, "create");
		
		mSpriteBatch = new SpriteBatch();
		mBitmapFont = new BitmapFont();
		
		mParticle = new ParticleEffect();
		mParticle.load(Gdx.files.internal("particle.p"), Gdx.files.internal(""));
		mParticlePool = new ParticleEffectPool(mParticle, 5, 10);
		mParticleList = new ArrayList<ParticleEffect>();
	}

	@Override
	public void resize(int arg0, int arg1) {
		Log.i(TAG, "resize");
	}

	@Override
	public void resume() {
		Log.i(TAG, "resume");
	}

	@Override
	public void render() {
		Log.i(TAG, "render");
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		
		mSpriteBatch.begin();
		
		mBitmapFont.draw(mSpriteBatch, "Hello libGdx!", Gdx.graphics.getWidth()*0.4f, Gdx.graphics.getHeight()/2);
		
		mSpriteBatch.end();
		
		if(Gdx.input.isTouched()){
			//���˴���������һ������������һ��ֵ��ʱ�򴥷��µ�����ϵͳ���ɴ˼�Сϵͳ����
			mTemp=mParticlePool.obtain();
			mTemp.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
			mParticleList.add(mTemp);
		}
		
		mSpriteBatch.begin();
		int count = mParticleList.size();
		for(int i = 0; i < count; i++){
			mParticleList.get(i).draw(mSpriteBatch, Gdx.graphics.getDeltaTime());
		}
		mSpriteBatch.end();
		
		// ����Ѿ�������ɵ�����
		count = mParticleList.size();
		ArrayList<ParticleEffect> liveParticles = new ArrayList<ParticleEffect>();
		for(int i = 0; i < count; i++){
			ParticleEffect tempParticle = mParticleList.get(i);
			if(!tempParticle.isComplete()){
				liveParticles.add(tempParticle);
			}
		}
		mParticleList = liveParticles;
	}

	/** �ڴ˱�����Ϸ״̬ */
	@Override
	public void pause() {
		Log.i(TAG, "pause");
	}
	
	/** �ͷ���Ϸ��Դ */
	@Override
	public void dispose() {
		Log.i(TAG, "dispose");
		mSpriteBatch.dispose();
		mBitmapFont.dispose();
		
		mParticle.dispose();
		
		if(mTemp != null){
			mTemp.dispose();
		}
		
		mParticlePool.clear();
	}

	
	
}