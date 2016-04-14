package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.chris.game.endlessiege.components.Enemy;
import com.chris.game.endlessiege.components.HPComponent;
import com.chris.game.endlessiege.components.KillableComponent;
import com.chris.game.endlessiege.components.StateComponent;
import com.chris.game.endlessiege.components.VelocityComponent;

public class HPSystem extends IteratingSystem {

	ComponentMapper<HPComponent> hp;
	ComponentMapper<StateComponent> sc;
	ComponentMapper<KillableComponent> kc;
	
	public HPSystem()
	{
		super(Family.all(HPComponent.class, StateComponent.class, KillableComponent.class).get());
		hp = ComponentMapper.getFor(HPComponent.class);
		sc = ComponentMapper.getFor(StateComponent.class);
		kc = ComponentMapper.getFor(KillableComponent.class);
	}
	
	public HPSystem(Family family) {
		super(family);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		HPComponent hitpoints = hp.get(entity);
		StateComponent state = sc.get(entity);
		KillableComponent kill = kc.get(entity);
		if (hitpoints.hitpoints <= 0)// && state.state != kill.DYING_STATE && state.state != kill.DEAD_STATE)
		{
			state.state = kill.DYING_STATE;
			state.time = 0;
			entity.remove(VelocityComponent.class);
			entity.remove(HPComponent.class);

		}
	}
	
	public void subtractHP(long id, int hp)
	{
		this.getEngine().getEntity(id).getComponent(HPComponent.class).hitpoints -= hp;
	}
}
