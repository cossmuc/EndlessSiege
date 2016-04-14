package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.chris.game.endlessiege.components.TransformComponent2D;
import com.chris.game.endlessiege.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {

	private ComponentMapper<TransformComponent2D> tm;
	private ComponentMapper<VelocityComponent> vm;
	private Vector2 newMovement = new Vector2();
	
	public MovementSystem()
	{
		super(Family.all(TransformComponent2D.class, VelocityComponent.class).get());
		tm = ComponentMapper.getFor(TransformComponent2D.class);
		vm = ComponentMapper.getFor(VelocityComponent.class);
	}
	
	public MovementSystem(Family family) {
		super(Family.all(TransformComponent2D.class, VelocityComponent.class).get());
		tm = ComponentMapper.getFor(TransformComponent2D.class);
		vm = ComponentMapper.getFor(VelocityComponent.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		TransformComponent2D position = tm.get(entity);
		VelocityComponent velocity = vm.get(entity);
		
		position.position.add(velocity.velocity);
		
	}

}
