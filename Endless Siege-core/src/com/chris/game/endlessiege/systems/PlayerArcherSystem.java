package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.chris.game.endlessiege.World;
import com.chris.game.endlessiege.components.ArcherComponent;
import com.chris.game.endlessiege.components.Enemy;
import com.chris.game.endlessiege.components.PlayerArcherComponent;
import com.chris.game.endlessiege.components.PlayerComponent;
import com.chris.game.endlessiege.components.StateComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;

public class PlayerArcherSystem extends IteratingSystem {

	private ComponentMapper<PlayerArcherComponent> ac;
	private ComponentMapper<StateComponent> sc;
	private ComponentMapper<TransformComponent2D> tc;
	private ComponentMapper<PlayerComponent> pc;
	World world;
	
	public PlayerArcherSystem(World world) {
		super(Family.all(PlayerArcherComponent.class).get());
		this.world = world;
		ac = ComponentMapper.getFor(PlayerArcherComponent.class);
		tc = ComponentMapper.getFor(TransformComponent2D.class);
		pc = ComponentMapper.getFor(PlayerComponent.class);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		//Gdx.app.log("MyLog", "0 state: " + this.getEntities().get(0).getComponent(ArcherComponent.class).state + " " + this.getEntities().get(0).getId());
		//Gdx.app.log("MyLog", "1 state: " + this.getEntities().get(1).getComponent(ArcherComponent.class).state + " " + this.getEntities().get(1).getId());
		//Gdx.app.log("MyLog", "2 state: " + this.getEntities().get(2).getComponent(ArcherComponent.class).state + " " + this.getEntities().get(2).getId());
		
		//Gdx.app.log("MyLog", "entity state: " + entity.getComponent(ArcherComponent.class).state + " " + entity.getId());

		
		// TODO Auto-generated method stub
		PlayerArcherComponent archer = ac.get(entity);
		TransformComponent2D transform = tc.get(entity);

		//archer.accumulator += deltaTime;
		//archer.accumulator += deltaTime;
		//Gdx.app.log("MyTags1", "State" + archer.state);
		//if (archer.accumulator >= archer.interval && archer.state == archer.LOADING_STATE)
		//if (archer.state == archer.LOADING_STATE)
		{
			/*
			TransformComponent2D transform = ac.get(entity);
			world.createFootman((float)Math.random() * 300 + transform.position.x, spawn.troopType);
			*/
			//archer.state = archer.READY_STATE;
			//archer.accumulator = 0;
			//archer.state = archer.SHOOT_STATE;
		}
		
		/*
		if (archer.state == archer.READY_STATE)
		{
			archer.state = archer.SHOOT_STATE;
		}
		*/
		
		//if (archer.state == archer.SHOOT_STATE)
		//{
			
			//archer.powerAngle
			//Gdx.app.log("TotalArchers","" + this.getEntities().size());
			//Gdx.app.log("MyTag1", "x:" + archer.powerAngle.x + archer.powerAngle.x + "y:" + archer.powerAngle.y);
			
			//world.createProjectile(transform.position.x + 72, transform.position.y + 92, 
					//-archer.powerAngle.x /100, archer.powerAngle.y /100);
			/*
			world.createProjectile(transform.position.x + 72, transform.position.y + 92, 
					50, 50);
				*/	
			/*
			world.createProjectile(transform.position.x + 72, transform.position.y + 92, 
					20, archer.powerAngle.y);
			*/
			//archer.state = archer.LOADING_STATE;
			//archer.state = archer.LOADING_STATE;
			//archer.accumulator = 0;
			
		}
		
		//Gdx.app.log("MyTags2", "State2" + archer.state);
	//}
	
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
