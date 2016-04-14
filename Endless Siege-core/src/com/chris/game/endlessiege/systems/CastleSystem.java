package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.chris.game.endlessiege.World;
import com.chris.game.endlessiege.components.BoundsComponent2D;
import com.chris.game.endlessiege.components.Castle;
import com.chris.game.endlessiege.components.HPComponent;
import com.chris.game.endlessiege.components.KillableComponent;
import com.chris.game.endlessiege.components.ScoreComponent;

public class CastleSystem extends EntitySystem {
	
	private ImmutableArray<Entity> entities;
	private ComponentMapper<Castle> castle;
	//private ComponentMapper<KillableComponent> death;
	private ComponentMapper<HPComponent> hp;
	private ComponentMapper<BoundsComponent2D> bc;
	
	private World world;
	
	public CastleSystem(World world)
	{
		castle = ComponentMapper.getFor(Castle.class);
		//death = ComponentMapper.getFor(KillableComponent.class);
		hp = ComponentMapper.getFor(HPComponent.class);
		bc = ComponentMapper.getFor(BoundsComponent2D.class);
		this.world = world;
	}
	
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(Castle.class, HPComponent.class).get());
	}
	
	public void update(float deltaTime)
	{
		/*
		Entity entity = entities.first();
		//KillableComponent kc = death.get(entity);
		HPComponent hitpoints = hp.get(entity);
		
		if (hitpoints.hitpoints <= 0)
		{
			world.state = world.WORLD_STATE_GAME_OVER;
		}
		*/
		
		Entity entity;
			if (entities.size() > 0)
			{
				entity = entities.first();
			//KillableComponent kc = death.get(entity);
			HPComponent hitpoints = hp.get(entity);
			
			if (hitpoints.hitpoints <= 0)
			{
				world.state = world.WORLD_STATE_GAME_OVER;
			}
		
		}
		
		
	}
	
	public Vector2 getCastleBounds()
	{
		Entity entity = entities.first();
		BoundsComponent2D bounds = bc.get(entity);
		return bounds.bounds.getCenter(new Vector2(bounds.bounds.x, bounds.bounds.y));
		
		/*
		
		if (entities.size() >= 0)
		{
			Entity entity = entities.first();
			BoundsComponent2D bounds = bc.get(entity);
			return bounds.bounds.getCenter(new Vector2(bounds.bounds.x, bounds.bounds.y));
		}
		else
		{
			return new Vector2(0,0);
		}
		*/
	}
	
	public void modifyHP(int amount)
	{
		for (Entity entity : entities)
		{
			
		}
	}

}
