package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.chris.game.endlessiege.World;
import com.chris.game.endlessiege.components.ArcherComponent;
import com.chris.game.endlessiege.components.BoundsComponent2D;
import com.chris.game.endlessiege.components.Enemy;
import com.chris.game.endlessiege.components.PlayerComponent;
import com.chris.game.endlessiege.components.StateComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;
import com.chris.game.endlessiege.components.VelocityComponent;

public class ArcherSystem extends IteratingSystem {

	private ComponentMapper<ArcherComponent> ac;
	private ComponentMapper<StateComponent> sc;
	private ComponentMapper<TransformComponent2D> tc;
	private ComponentMapper<PlayerComponent> pc;
	private ComponentMapper<VelocityComponent> vc;
	//private ComponentMapper<BoundsComponent2D> bc;
	World world;
	
	public ArcherSystem(World world) {
		super(Family.all(ArcherComponent.class, Enemy.class).get());
		this.world = world;
		ac = ComponentMapper.getFor(ArcherComponent.class);
		sc = ComponentMapper.getFor(StateComponent.class);
		tc = ComponentMapper.getFor(TransformComponent2D.class);
		pc = ComponentMapper.getFor(PlayerComponent.class);
		vc = ComponentMapper.getFor(VelocityComponent.class);
		//bc = ComponentMapper.getFor(BoundsComponent2D.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		//Gdx.app.log("MyLog", "0 state: " + this.getEntities().get(0).getComponent(ArcherComponent.class).state + " " + this.getEntities().get(0).getId());
		//Gdx.app.log("MyLog", "1 state: " + this.getEntities().get(1).getComponent(ArcherComponent.class).state + " " + this.getEntities().get(1).getId());
		//Gdx.app.log("MyLog", "2 state: " + this.getEntities().get(2).getComponent(ArcherComponent.class).state + " " + this.getEntities().get(2).getId());
		
		//Gdx.app.log("MyLog", "entity state: " + entity.getComponent(ArcherComponent.class).state + " " + entity.getId());

		
		// TODO Auto-generated method stub
		ArcherComponent archer = ac.get(entity);
		TransformComponent2D transform = tc.get(entity);
		StateComponent state = sc.get(entity);
		VelocityComponent velocity = vc.get(entity);
		
		state.time += deltaTime;
		//archer.accumulator += deltaTime;
		//Gdx.app.log("MyTags1", "State" + archer.state);
		//if (archer.accumulator >= archer.interval && archer.state == archer.LOADING_STATE)
		if (state.state == archer.WALKING_STATE && world.hp > 0)
		{
			velocity.velocity.x = -2.5f;
			Vector2 castlePosition = this.getEngine().getSystem(CastleSystem.class).getCastleBounds();
			if (Math.abs(castlePosition.x - transform.position.x) < archer.range)
			{
				state.state = archer.LOADING_STATE;
				velocity.velocity.x = 0f;
			}
		}
		
		if (state.time >= archer.interval && state.state == archer.LOADING_STATE)
		{
			/*
			TransformComponent2D transform = ac.get(entity);
			world.createFootman((float)Math.random() * 300 + transform.position.x, spawn.troopType);
			*/
			//archer.state = archer.READY_STATE;
			//archer.accumulator = 0;
			state.state = archer.READY_STATE;
			state.time = 0;
			
			//archer.state = archer.SHOOT_STATE;
		}
		
		/*
		if (archer.state == archer.READY_STATE)
		{
			archer.state = archer.SHOOT_STATE;
		}
		*/
		
		if (state.state == archer.READY_STATE)
		{
			state.state = archer.SHOOT_STATE;
		}
		
		/*
		if (archer.state == archer.SHOOT_STATE)
		{
			
			
			world.createProjectile(transform.position.x + 72, transform.position.y + 92, 
					-20, 20);
				
			
			//world.createProjectile(transform.position.x + 72, transform.position.y + 92, 
					//20, archer.powerAngle.y);
			
			archer.state = archer.LOADING_STATE;
			
		}
		*/
		
		if (state.state == archer.SHOOT_STATE)
		{
			world.createProjectile(transform.position.x + 72, transform.position.y + 92, 
					-25, 22);
			state.state = archer.LOADING_STATE;
		}
		
		//state.time = archer.accumulator;
		//state.state = archer.state;
		
		//Gdx.app.log("MyTags2", "State2" + archer.state);
	}
	
	public void createProjectile(float x, float y, float velocityX, float velocityY)
	{
		
	}
	
	public void setAngleAndPower(Vector2 anglePower)
	{
		
	}
	
	public void setAngle(float angle)
	{
		
		
	}
	
	public void setPower(float power)
	{
		
	}

}
