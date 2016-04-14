package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.chris.game.endlessiege.components.BoundsComponent2D;
import com.chris.game.endlessiege.components.TransformComponent2D;

public class BoundsSystem2D extends IteratingSystem 
{
	
	private ComponentMapper<TransformComponent2D> tm;
	private ComponentMapper<BoundsComponent2D> bm;
	

	public BoundsSystem2D()
	{
		super(Family.all(BoundsComponent2D.class, TransformComponent2D.class).get());
		
		tm = ComponentMapper.getFor(TransformComponent2D.class);
		bm = ComponentMapper.getFor(BoundsComponent2D.class);
	}
	
	public BoundsSystem2D(Family family) {
		super(family);
		// TODO Auto-generated constructor stub
	}

	public BoundsSystem2D(Family family, int priority) {
		super(family, priority);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		TransformComponent2D pos = tm.get(entity);
		BoundsComponent2D bounds = bm.get(entity);
		
		bounds.bounds.x = pos.position.x - bounds.bounds.width * 0.5f;
		bounds.bounds.y = pos.position.y - bounds.bounds.height * 0.5f;
	}

}
