package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.chris.game.endlessiege.World;
import com.chris.game.endlessiege.components.SpawnComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;

public class SpawnSystem extends IntervalIteratingSystem {
	
	private ComponentMapper<SpawnComponent> sc;
	private ComponentMapper<TransformComponent2D> tc;
	ImmutableArray<Entity> entities;
	
	//private ComponentMapper<>
	
	
	private World world;
	
	/*
	public SpawnSystem(float interval) {
		super(family, interval);
		sc = ComponentMapper.getFor(SpawnComponent.class);
		// TODO Auto-generated constructor stub
	}
	*/
	
	public SpawnSystem(float interval, World world) {
		super((Family.all(SpawnComponent.class).get()), interval);
		sc = ComponentMapper.getFor(SpawnComponent.class);
		tc = ComponentMapper.getFor(TransformComponent2D.class);
		this.world = world;
		// TODO Auto-generated constructor stub
	}
	
	/*
	@Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(SpawnComponent.class).get());
    }
    */
	
	/*
	@Override
	protected void updateInterval() {
		// TODO Auto-generated method stub
		
		
		for (Entity entity : entities)
		{
			SpawnComponent spawn = sc.get(entity);
			
			if (spawn.)
			
			TransformComponent2D transform = tc.get(entity);
			world.createFootman((float)Math.random() * 300 + transform.position.x);
		}
		
	}
	*/
	/*
	@Override
	protected void updateInterval() {
		
	}
	*/
	
	@Override
	protected void processEntity(Entity entity) {
		// TODO Auto-generated method stub
		SpawnComponent spawn = sc.get(entity);
		TransformComponent2D transform = tc.get(entity);
		world.createFootman((float)Math.random() * 300 + transform.position.x, 0, spawn.troopType, 1);
	}

}
