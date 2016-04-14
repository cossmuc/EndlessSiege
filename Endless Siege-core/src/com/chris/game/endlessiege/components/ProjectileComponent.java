package com.chris.game.endlessiege.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class ProjectileComponent implements Component {
	public Vector2 drop = Vector2.Zero;
	public int type = 0;
	public boolean multiHit = false;
	public int damage = 20;
	
}
