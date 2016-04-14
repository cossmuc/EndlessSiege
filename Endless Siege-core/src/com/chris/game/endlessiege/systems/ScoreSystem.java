package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.chris.game.endlessiege.components.ScoreComponent;

public class ScoreSystem extends EntitySystem {
	
	private ImmutableArray<Entity> entities;
	private ComponentMapper<ScoreComponent> sc;
	
	public void ScoreSystem()
	{
		sc = ComponentMapper.getFor(ScoreComponent.class);
	}
	
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(ScoreComponent.class).get());
	}
	
	public void update(float deltaTime)
	{
		Entity entity = entities.first();
		
		
	}
	
	public void updateScore(int amount)
	{
		
	}
	
}
