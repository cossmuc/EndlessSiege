package com.chris.game.endlessiege.components;

import com.badlogic.ashley.core.Component;

public class KillableComponent implements Component {
	public int DYING_STATE = 0;
	public int DEAD_STATE = 1;
	public boolean dying = false;
	public boolean alive = true;
	public float deathTime = 0.0f;
}
