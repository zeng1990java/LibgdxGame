package com.example.libgdxgame;

import java.util.ArrayList;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class MyGame implements Screen {

	private static final String TAG = "Game";
	
	private SpriteBatch mSpriteBatch;
	private BitmapFont mBitmapFont;
	
	private ParticleEffect mParticle;
	private ParticleEffect mTemp;
	private ParticleEffectPool mParticlePool;
	private ArrayList<ParticleEffect> mParticleList;
	
	private Texture tex1;
	private Texture tex2;
	private Texture tex3;
	
	private MainActivity mMainActivity;
	// 需要一个舞台
	private Stage mStage;
	
	private Button mButton;
	
	public MyGame(MainActivity mainActivity){
		this.mMainActivity = mainActivity;
	}
	
	/** 当程序第一次创建的时候此方法被调用 */
	@Override
	public void show() {
		Log.i(TAG, "create");
		
		mSpriteBatch = new SpriteBatch();
		mBitmapFont = new BitmapFont();
		
		mParticle = new ParticleEffect();
		mParticle.load(Gdx.files.internal("particle.p"), Gdx.files.internal(""));
		mParticlePool = new ParticleEffectPool(mParticle, 5, 10);
		mParticleList = new ArrayList<ParticleEffect>();
		
		
		tex1 = new Texture(Gdx.files.internal("button1_480.png"));
		tex2 = new Texture(Gdx.files.internal("button2_480.png"));
		tex3 = new Texture(Gdx.files.internal("button3_480.png"));
		
		NinePatch n1 = new NinePatch(tex1, 14, 14, 18, 18);
		NinePatch n2 = new NinePatch(tex2, 14, 14, 18, 18);
		NinePatch n3 = new NinePatch(tex3, 14, 14, 18, 18);
		Drawable drawable1 = new NinePatchDrawable(n1);
		Drawable drawable2 = new NinePatchDrawable(n2);
		Drawable drawable3 = new NinePatchDrawable(n3);
		ButtonStyle buttonStyle = new ButtonStyle(drawable1, drawable2, drawable3);
		mButton = new Button(buttonStyle);
		mButton.setName("Start");
		
		mButton.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				mMainActivity.mAppGame.setScreen(mMainActivity.mProgress);
			}
			
		});
		
		mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true);
		mStage.addActor(mButton);
		Gdx.input.setInputProcessor(mStage);
		
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
	public void render(float arg0) {
		Log.i(TAG, "render");
		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		
		mStage.act(Gdx.graphics.getDeltaTime());
		mStage.draw();
		
		mSpriteBatch.begin();
		
		mBitmapFont.draw(mSpriteBatch, "Hello libGdx!", Gdx.graphics.getWidth()*0.4f, Gdx.graphics.getHeight()/2);
		
		mSpriteBatch.end();
		
		if(Gdx.input.isTouched()){
			//当此触摸点与上一触摸点距离大于一定值的时候触发新的粒子系统，由此减小系统负担
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
		
		// 清除已经播放完成的粒子
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

	/** 在此保存游戏状态 */
	@Override
	public void pause() {
		Log.i(TAG, "pause");
	}
	
	/** 释放游戏资源 */
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
		
		mStage.dispose();
		mButton.clear();
	}

	@Override
	public void hide() {
		
	}

	
	
}