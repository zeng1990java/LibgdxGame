package com.example.libgdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Progress implements Screen {

	private ProgressBar mProgressBar;
	private Stage mStage;
	private AnimalActor mAnimalActor;
	private AssetManager mAssetManager;
	private boolean mHasInit = false;

	@Override
	public void show() {

		mProgressBar = new ProgressBar(0, 0);
		mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mStage.addActor(mProgressBar);
		// ����AssetManager������initResource����Դ�Żᱻ��ʼ��
		mAssetManager = new AssetManager();
		mAnimalActor = new AnimalActor(mAssetManager);
		// ����Դ���������б�
		for (int i = 1; i < 30; i++) {
			mAssetManager.load("animal/" + i + ".png", Texture.class);
		}
	}

	@Override
	public void dispose() {
		mProgressBar.dispose();
		mAnimalActor.dispose();
		mAssetManager.clear();
		mAssetManager.dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 1f, 1f, 0f);

		mStage.act(Gdx.graphics.getDeltaTime());
		mStage.draw();

		if (!mAssetManager.update()) {
			mProgressBar.setProgress(mAssetManager.getProgress()*100);
		}else{
			mProgressBar.setProgress(100);
		}

		// ���������֮ǰû�г�ʼ����AnimalActor�������ִ�����Ļʱ��ʼ��AnimalActor����������������̨���Ƴ���������AnimalActor����
		if (!mHasInit && mAssetManager.update() && Gdx.input.isTouched()) {
			mStage.getRoot().removeActor(mProgressBar);
			mAnimalActor.initResource();
			mStage.addActor(mAnimalActor);
			mHasInit = true;
		}

		if (!mAssetManager.update()) {
			System.out.println("QueuedAssets:"
					+ mAssetManager.getQueuedAssets());
			System.out.println("LoadedAssets:"
					+ mAssetManager.getLoadedAssets());
			System.out.println("Progress:" + mAssetManager.getProgress());
		}
		// if(mProgressBar.getProgress() < 100){
		// mProgressBar.setProgress(mProgressBar.getProgress()+0.5f);
		// }
		//
		// if(mProgressBar.getProgress()>=100){
		// mProgressBar.setProgress(0f);
		// }
	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		
	}

}
