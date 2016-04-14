package com.chris.game.endlessiege.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class ArcherComponent implements Component{
	//public static int state = 0;
	public static final int IDLE_STATE = 0;
	public static final int WALKING_STATE = 1;
	public static final int LOADING_STATE = 2;
	public static final int READY_STATE = 3;
	public static final int SHOOT_STATE = 4;
	public static final int DYING_STATE = 5;
	public static final int DEAD_STATE = 6;
	public static final int ON_FIRE_STATE = 7;
	//public float shootInterval = 0;
	public float interval = 0;
	public float range = 500.0f;
	public float velocity = 2.0f;
	//public float accumulator = 0;
	public Vector2 powerAngle = Vector2.Zero;
}
