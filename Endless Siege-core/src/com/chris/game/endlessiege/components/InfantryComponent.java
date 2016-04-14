package com.chris.game.endlessiege.components;

import com.badlogic.ashley.core.Component;

public class InfantryComponent implements Component {
	public static final int IDLE_STATE = 0;
	public static final int RUNNING_STATE = 1;
	public static final int ATTACKING_STATE = 2;
	public static final int DYING_STATE = 5;
	public static final int DEAD_STATE = 6;
	
	public float dps = 0.0f;
}
