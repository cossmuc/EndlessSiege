package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.chris.game.endlessiege.components.CameraComponent2D;

public class CameraSystem2D extends IteratingSystem {

	private ComponentMapper<CameraComponent2D> cm;
	private Vector2 cameraPosition;
	
	public CameraSystem2D()
	{
		super(Family.all(CameraComponent2D.class).get());
		cm = ComponentMapper.getFor(CameraComponent2D.class);
	}
	
	public CameraSystem2D(Family family) {
		super(family);
	}

	public CameraSystem2D(Family family, int priority) {
		super(family, priority);
	}
	
	public void setCameraPosition(Vector2 newPosition)
	{
		
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		CameraComponent2D cam = cm.get(entity);

	}

}
