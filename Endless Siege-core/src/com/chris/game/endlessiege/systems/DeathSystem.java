package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.chris.game.endlessiege.World;
import com.chris.game.endlessiege.components.Enemy;
import com.chris.game.endlessiege.components.KillableComponent;
import com.chris.game.endlessiege.components.StateComponent;

public class DeathSystem extends IteratingSystem {

	ComponentMapper<KillableComponent> kc;
	ComponentMapper<StateComponent> sc;
	World world;
	
	public DeathSystem(World world)
	{
		super(Family.all(KillableComponent.class, StateComponent.class).get());
		kc = ComponentMapper.getFor(KillableComponent.class);
		sc = ComponentMapper.getFor(StateComponent.class);
		this.world = world;
	}
	
	public DeathSystem(Family family) {
		super(family);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		KillableComponent kill = kc.get(entity);
		StateComponent state = sc.get(entity);
		
		if (state.state == kill.DYING_STATE && state.time < kill.deathTime)
		{
			state.time += deltaTime;
		}
		else if (state.state == kill.DYING_STATE && state.time > kill.deathTime)
		{
			state.state = kill.DEAD_STATE;
			if (entity.getComponent(Enemy.class) != null)
			{
				int points = entity.getComponent(Enemy.class).points;
				world.score += points;
			}
			this.getEngine().removeEntity(entity);
			
		}
		
	}

}
