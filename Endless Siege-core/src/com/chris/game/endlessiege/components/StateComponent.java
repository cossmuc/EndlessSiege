package com.chris.game.endlessiege.components;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {
	//Archer_LOADING=
	//Archer_READY=
	//Archer_FIRE=
	public int state = 0;
	public float time = 0.0f;
}
