package com.chris.endlessiege.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameLabel extends Label {

	CharSequence labelText;
	
	public GameLabel(CharSequence text, Skin skin) {
		super(text, skin);
		labelText = text;
		// TODO Auto-generated constructor stub
	}
	
	public void act(float deltaTime)
	{
		
	}
	

}
