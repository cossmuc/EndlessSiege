package com.chris.game.endlessiege;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class Assets {
	public static Texture testTexture;
	public static Texture castleTexture;
	public static Texture swordGuyTexture;
	public static Texture arrowTexture;
	public static Texture archerLoadingAnimTexture;
	public static Texture archerDeathTexture;
	public static Texture skyBackgroundTexture;
	public static Texture catapultTexture;
	public static Texture catapultOnFireTexture;
	public static Texture fireTexture;
	
	public static Texture testFlingTexture;
	public static Texture testPanTexture;
	
	
	public static TextureRegion test;
	public static TextureRegion castle;
	public static TextureRegion swordGuy;
	public static TextureRegion arrow;
	public static TextureRegion sky;
	public static TextureRegion catapult;
	public static TextureRegion onFire;
	//not used
	public static TextureRegion catapultOnFire;
	
	public static TextureRegion testFling;
	public static TextureRegion testPan;
	
	public static Animation testAnimation;
	public static Animation archerLoadingAnimation;
	public static Animation deathArcherAnimation;
	
	public static Animation catapultLoadingAnimation;
	public static Animation catapultDeathAnimation;
	public static Animation catapultOnFireAnimation;
	
	public static Skin skin;
	
	public static void load()
	{
		testTexture = loadTexture("badlogic.jpg");
		castleTexture = loadTexture("Castle.png");
		swordGuyTexture = loadTexture("swordguy.png");
		arrowTexture = loadTexture("Arrow.png");
		archerLoadingAnimTexture = loadTexture("SwordGuySpritesheet.png");
		archerDeathTexture = loadTexture("SwordGuySpritesheetFire.png");
		skyBackgroundTexture = loadTexture("skyBackground.jpg");
		catapultTexture = loadTexture("catapult.png");
		fireTexture = loadTexture("Flame.png");
		//not used
		catapultOnFireTexture = loadTexture("catapultOnFire.png");
		
		testFlingTexture = loadTexture("fling.png");
		testPanTexture = loadTexture("pan.png");
		
		test = new TextureRegion(testTexture, 0, 0, 200,200);
		castle = new TextureRegion(castleTexture, 0, 0, 160, 320);
		swordGuy = new TextureRegion(swordGuyTexture, 0, 0, swordGuyTexture.getWidth(), swordGuyTexture.getHeight());
		arrow = new TextureRegion(arrowTexture, 0, 0, arrowTexture.getWidth(), arrowTexture.getHeight());
		sky = new TextureRegion(skyBackgroundTexture, 0, 0, skyBackgroundTexture.getWidth(), 763);
		catapult = new TextureRegion(catapultTexture, 0, 0, catapultTexture.getWidth() / 5, catapultTexture.getHeight());
		onFire = new TextureRegion(fireTexture, 0, 0, fireTexture.getWidth(), fireTexture.getHeight());
		
		testFling = new TextureRegion(testFlingTexture, 0, 0, testFlingTexture.getWidth(), testFlingTexture.getHeight());
		testPan = new TextureRegion(testPanTexture, 0, 0, testPanTexture.getWidth(), testPanTexture.getHeight());
		
		
		testAnimation = new Animation(0.2f, new TextureRegion(testTexture, 0,0,100,100), 
				new TextureRegion(testTexture,100,0,100,100), 
				new TextureRegion(testTexture,0,100,100,100), 
				new TextureRegion(testTexture,100,100,100,100));
		
		testAnimation.setPlayMode(PlayMode.LOOP);

		/*
		archerLoadingAnimation = new Animation(0.4f, new TextureRegion(archerLoadingAnimTexture,0,0,76,92),
														new TextureRegion(archerLoadingAnimTexture,0,0,))
														*/
		archerLoadingAnimation = loadFrames(archerLoadingAnimTexture, 1, 5, 0.4f, PlayMode.NORMAL);
		deathArcherAnimation = loadFrames(archerDeathTexture, 1, 3, 0.3f, PlayMode.NORMAL);
		
		catapultLoadingAnimation = loadFrames(catapultTexture, 1, 5, 0.4f, PlayMode.NORMAL);
		catapultDeathAnimation = loadFrames(catapultTexture, 1, 3, 0.3f, PlayMode.NORMAL);
		catapultOnFireAnimation = loadFrames(catapultOnFireTexture, 1, 5, 0.4f, PlayMode.NORMAL);
		
		skin = loadSkin("skin\\uiskin.json");
	}
	
	public static Texture loadTexture(String file)
	{
		return new Texture(Gdx.files.internal(file));
	}
	
	public static Animation loadFrames(Texture texture, int rows, int columns, float time, PlayMode playmode)
	{
		
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight() / rows);
		TextureRegion[] frames = new TextureRegion[columns * rows];
		int index = 0;
		for (int i=0; i < rows; i++)
		{
			for (int j=0; j<columns;j++)
			{
				frames[index++] = tmp[i][j];
			}
		}
		Animation newAnimation = new Animation(time, frames);
		newAnimation.setPlayMode(PlayMode.NORMAL);
		return newAnimation;
		
	}
	
	public static Skin loadSkin(String path)
	{
		return new Skin(Gdx.files.internal(path));
	}
}
