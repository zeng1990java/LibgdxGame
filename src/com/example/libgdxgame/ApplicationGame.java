package com.example.libgdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ApplicationGame extends Game {

	private Screen mScreen;
	
	@Override
	public void create() {
		setScreen(mScreen);
	}

	public ApplicationGame(Screen screen){
		this.mScreen = screen;
	}
	
	@Override
	public void resume() {
		super.resume();
	}
}
