package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.chris.game.endlessiege.World;
import com.chris.game.endlessiege.components.ProjectileComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;
import com.chris.game.endlessiege.components.VelocityComponent;

public class ProjectileSystem extends IteratingSystem {

	World world;
	ComponentMapper<TransformComponent2D> tc;
	ComponentMapper<VelocityComponent> vc;
	ComponentMapper<ProjectileComponent> pc;
	
	
	public ProjectileSystem() {
		super(Family.all(ProjectileComponent.class, TransformComponent2D.class, VelocityComponent.class).get());
		tc = ComponentMapper.getFor(TransformComponent2D.class);
		vc = ComponentMapper.getFor(VelocityComponent.class);
		pc = ComponentMapper.getFor(ProjectileComponent.class);
	}
	
	public ProjectileSystem(Family family) {
		super(family);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		ProjectileComponent projectile = pc.get(entity);
		VelocityComponent velocity = vc.get(entity);
		velocity.velocity.y += projectile.drop.y;
		
		TransformComponent2D position = tc.get(entity);
		if (position.position.y < 0)
		{
			this.getEngine().removeEntity(entity);
		}
	}

}
