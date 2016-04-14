package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.chris.game.endlessiege.World;
import com.chris.game.endlessiege.components.SpawnComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;

public class SpawnSystem1 extends IteratingSystem {

	private ComponentMapper<SpawnComponent> sc;
	private ComponentMapper<TransformComponent2D> tc;
	
	private World world;
	
	public SpawnSystem1(World world) {
		super(Family.all(SpawnComponent.class).get());
		sc = ComponentMapper.getFor(SpawnComponent.class);
		tc = ComponentMapper.getFor(TransformComponent2D.class);
		this.world = world;
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		
		SpawnComponent spawn = sc.get(entity);
		spawn.accumulator += deltaTime;
		if (spawn.accumulator >= spawn.interval)
		{
			TransformComponent2D transform = tc.get(entity);
			world.createFootman((float)Math.random() * 300 + transform.position.x, 0, spawn.troopType, 1);
			spawn.accumulator = 0;
		}
	}

}
