package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.chris.game.endlessiege.components.ArcherComponent;
import com.chris.game.endlessiege.components.Enemy;

public class EnemySystem extends IteratingSystem {

	ComponentMapper<ArcherComponent> ac;
	ComponentMapper<Enemy> ec;
	
	public EnemySystem() {
		super(Family.all(ArcherComponent.class, Enemy.class).get());
		ac = ComponentMapper.getFor(ArcherComponent.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		/*
		ArcherComponent archer = ac.get(entity);
		Gdx.app.log("MyLog", "Enemy Archers: " + this.getEntities().size());
		if (archer.state == archer.READY_STATE)
		{
			Gdx.app.log("MyLog", "enemy entity state: " + entity.getComponent(ArcherComponent.class).state + " " + entity.getId());
			Gdx.app.log("TestLog", " " + "Player Archer State " + this.getEngine().getEntity(3).getComponent(ArcherComponent.class).state);
			archer.powerAngle.x = 50;
			archer.powerAngle.y = 50;
			archer.state = archer.SHOOT_STATE;
			Gdx.app.log("MyLog", "enemy entity state: " + entity.getComponent(ArcherComponent.class).state + " " + entity.getId());
			Gdx.app.log("TestLog", " " + "Player Archer State " + this.getEngine().getEntity(3).getComponent(ArcherComponent.class).state);
		}
		
		
	*/
	}
	
}
