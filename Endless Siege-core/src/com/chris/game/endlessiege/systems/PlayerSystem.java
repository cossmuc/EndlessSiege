package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.chris.game.endlessiege.World;
import com.chris.game.endlessiege.components.ArcherComponent;
import com.chris.game.endlessiege.components.PlayerArcherComponent;
import com.chris.game.endlessiege.components.PlayerComponent;
import com.chris.game.endlessiege.components.StateComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;

import javafx.concurrent.Worker.State;

//public class PlayerSystem extends IteratingSystem {
public class PlayerSystem extends EntitySystem {

	ComponentMapper<PlayerComponent> pc;
	ComponentMapper<PlayerArcherComponent> ac;
	ComponentMapper<TransformComponent2D> tc;
	ComponentMapper<StateComponent> sc;
	Vector2 newAngleAndPower = Vector2.Zero;
	//public Array<Entity> archerQueue;
	private ImmutableArray<Entity> archers;
	private Engine engine;
	public World world;
	public boolean readyToShoot = false;
	
	
	public PlayerSystem(World world) {
		//super(Family.all(PlayerComponent.class, ArcherComponent.class).get());
		ac = ComponentMapper.getFor(PlayerArcherComponent.class);
		pc = ComponentMapper.getFor(PlayerComponent.class);
		tc = ComponentMapper.getFor(TransformComponent2D.class);
		sc = ComponentMapper.getFor(StateComponent.class);
		this.world = world;
		//archerQueue = new Array<Entity>();
		// TODO Auto-generated constructor stub
	}
	
	public void addedToEngine(Engine engine)
	{
		this.engine = engine;
		archers = engine.getEntitiesFor(Family.all(PlayerComponent.class, PlayerArcherComponent.class, TransformComponent2D.class).get());
	}

	
	public void update(float deltaTime)
	{
		//super.update(deltaTime);
		
		/*
		if (archers.size() > 0)
		{
			Entity thisArcher = archers.first();
			PlayerArcherComponent archer = ac.get(thisArcher);
			TransformComponent2D transform = tc.get(thisArcher);
			archer.accumulator += deltaTime;
			if (archer.accumulator >= archer.interval && archer.state == archer.LOADING_STATE)
			{
				archer.state = archer.READY_STATE;
				archer.accumulator = 0;
				//archer.state = archer.SHOOT_STATE;
			}
		}
		*/
		
		
		for (int i = 0; i < archers.size(); i++)
		{
			Entity thisArcher = archers.get(i);
			PlayerArcherComponent archer = ac.get(thisArcher);
			TransformComponent2D transform = tc.get(thisArcher);
			StateComponent state = sc.get(thisArcher);
			//archer.accumulator += deltaTime;
			state.time += deltaTime;
			/*
			if (archer.accumulator >= archer.interval && archer.state == archer.LOADING_STATE)
			{
				archer.state = archer.READY_STATE;
				archer.accumulator = 0;
				readyToShoot = true;
				//archer.state = archer.SHOOT_STATE;
			}
			*/
			
			if (state.time >= archer.interval && state.state == archer.LOADING_STATE)
			{
				state.state = archer.READY_STATE;
				state.time = 0;
				readyToShoot = true;
			}
			
			//state.state = archer.state;
			//state.time = archer.accumulator;
		}
		
		
		
			
			/*
			if (archer.state == archer.READY_STATE)
			{
				archer.state = archer.SHOOT_STATE;
			}
			*/
			/*
			if (archer.state == archer.SHOOT_STATE)
			{
				/*
				world.createProjectile(transform.position.x + 72, transform.position.y + 92, 
						-archer.powerAngle.x /100, archer.powerAngle.y /100);
				
				world.createProjectile(transform.position.x + 72, transform.position.y + 92, 
						50, 50);
					
				archer.state = archer.LOADING_STATE;
				
			}
		
		}
		*/
	}
	
	/*
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		/*
		// TODO Auto-generated method stub
		ArcherComponent archer = ac.get(entity);
		if (archer.state == archer.READY_STATE)
		{
			
			archer.state = archer.SHOOT_STATE;
		}
		*/
		//archerQueue.add(entity);

	//}
	/*
	public void fireWeapon(float x, float y)
	{

	}
	*/
	
	public void fireWeapon(float x, float y)
	{
		//for (Entity entity : archerQueue)
		if (readyToShoot)
		{
			for (int i = 0; i < archers.size(); i++)
			{
				
				Entity thisArcher = archers.get(i);
				
				//Entity thisArcher1 = archers.get(1);
				PlayerArcherComponent archer = ac.get(thisArcher);
				TransformComponent2D transform = tc.get(thisArcher);
				StateComponent state = sc.get(thisArcher);
	
				//if (archer.state == archer.READY_STATE)
				//{
				
					//for (int j = 0; j < archers.size(); j++)
					//{
				//if (readyToShoot)
				//{
				archer.powerAngle.x = x;
				archer.powerAngle.y = y;
				
					world.createProjectile(transform.position.x + 72, transform.position.y + 92, 
							-archer.powerAngle.x /200, archer.powerAngle.y /200);
					//archer.state = archer.LOADING_STATE;
					//archer.accumulator = 0;
					state.state = archer.LOADING_STATE;
					state.time = 0;
					
				//}
					//}
				//}
			}
			readyToShoot = false;
		}
	}
	
	
	public void setAngleAndPower(Vector2 anglePower)
	{
		newAngleAndPower = anglePower;
		
	}
	
	public void setAngle(float angle)
	{
		
		
	}
	
	public void setPower(float power)
	{
		
	}

}
