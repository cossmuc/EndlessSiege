package com.chris.game.endlessiege.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PlayerArcherComponent implements Component {
	//public static int state = 0;
	public static final int IDLE_STATE = 0;
	public static final int WALKING_STATE = 1;
	public static final int LOADING_STATE = 2;
	public static final int READY_STATE = 3;
	public static final int SHOOT_STATE = 4;
	//public float shootInterval = 0;
	public float interval = 0;
	//public float accumulator = 0;
	public Vector2 powerAngle = Vector2.Zero;
}
