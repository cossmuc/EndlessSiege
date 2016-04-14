package com.chris.game.endlessiege;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chris.game.endlessiege.systems.RenderingSystem2D;

public class EndlessSiege extends Game {
	SpriteBatch batch;
	//GameScreen 
	//PooledEngine engine;
	//Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Assets.load();
		setScreen(new GameScreen(this));
		/*
		engine = new PooledEngine();
		
		RenderingSystem2D renderingSystem = new RenderingSystem2D(batch);
		engine.addSystem(renderingSystem);
		*/
		//img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
	}
	
	
}
