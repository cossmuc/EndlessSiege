package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.chris.game.endlessiege.components.BackgroundComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;

public class BackgroundSystem extends IteratingSystem {

	private OrthographicCamera camera;
	private ComponentMapper<TransformComponent2D> tc;
	
	
	public BackgroundSystem()
	{
		super(Family.all(BackgroundComponent.class).get());
		tc = ComponentMapper.getFor(TransformComponent2D.class);
	}
	
	public void setCamera(OrthographicCamera camera)
	{
		this.camera = camera;
	}
	
	public BackgroundSystem(Family family) {
		super(family);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		TransformComponent2D transform = tc.get(entity);
		transform.position.set(camera.position.x, camera.position.y);
	}

}
