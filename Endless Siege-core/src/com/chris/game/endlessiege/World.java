package com.chris.game.endlessiege;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.chris.game.endlessiege.components.AnimationComponent2D;
import com.chris.game.endlessiege.components.ArcherComponent;
import com.chris.game.endlessiege.components.BackgroundComponent;
import com.chris.game.endlessiege.components.BoundsComponent2D;
import com.chris.game.endlessiege.components.CameraComponent2D;
import com.chris.game.endlessiege.components.Castle;
import com.chris.game.endlessiege.components.Enemy;
import com.chris.game.endlessiege.components.FlammableComponent;
import com.chris.game.endlessiege.components.HPComponent;
import com.chris.game.endlessiege.components.InfantryComponent;
import com.chris.game.endlessiege.components.KillableComponent;
import com.chris.game.endlessiege.components.PlayerArcherComponent;
import com.chris.game.endlessiege.components.PlayerComponent;
import com.chris.game.endlessiege.components.ProjectileComponent;
import com.chris.game.endlessiege.components.SpawnComponent;
import com.chris.game.endlessiege.components.StateComponent;
import com.chris.game.endlessiege.components.TestComponent;
import com.chris.game.endlessiege.components.TextureComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;
import com.chris.game.endlessiege.components.VelocityComponent;
import com.chris.game.endlessiege.systems.RenderingSystem2D;
import com.sun.corba.se.spi.orbutil.fsm.State;

public class World {

	private PooledEngine engine;
	public static final float WORLD_WIDTH = 800;
	public static final float WORLD_HEIGHT = 480;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_GAME_OVER = 1;
	
	public int TROOPTYPE_SWORD = 0;
	public int TROOPTYPE_ARCHER = 1;
	
	public float playerPower = 0.0f;
	public float playerAngle = 0.0f;
	
	public float testArcherTimeMod = 0.0f;
	
	public int score = 0;
	public int hp = 0;
	public int state;
	
	public World(PooledEngine engine) {
		this.engine = engine;
	}
	
	public void create()
	{
		createCamera();
		//createTest();
		createBackground();
		
		createFootman(0, 280, 1, 0);
		createFootman(5, 280, 1, 0);
		createFootman(30, 280, 1, 0);
		createFootman(75, 280, 1, 0);
		
		createCastle();
		
		//createFootman(1000, 0, 1, 1);
		createFootman(900, 0, 2, 1);
		createSpawnPoint(3000.0f, 0, 2.0f);
		createSpawnPoint(3000.0f, 1, 6.0f);
		//createSpawnPoint(1200.0f, 1, 4.0f);
		
		this.state = WORLD_STATE_RUNNING;
	}
	
	public void createBackground()
	{
		Entity entity = engine.createEntity();
		TransformComponent2D position = engine.createComponent(TransformComponent2D.class);
		TextureComponent texture = engine.createComponent(TextureComponent.class);
		BackgroundComponent background = engine.createComponent(BackgroundComponent.class);
		
		texture.region = Assets.sky;
		
		entity.add(position);
		entity.add(texture);
		entity.add(background);
		
		engine.addEntity(entity);
	}
	
	public void createCastle()
	{
		Entity entity = engine.createEntity();
		TransformComponent2D position = engine.createComponent(TransformComponent2D.class);
		TextureComponent texture = engine.createComponent(TextureComponent.class);
		BoundsComponent2D bounds = engine.createComponent(BoundsComponent2D.class);
		StateComponent state = engine.createComponent(StateComponent.class);
		Castle castle = engine.createComponent(Castle.class);
		bounds.bounds.width = castle.WIDTH;
		bounds.bounds.height = castle.HEIGHT;
		
		HPComponent hitpoint = engine.createComponent(HPComponent.class);
		hitpoint.hitpoints = 100;
		hp = hitpoint.hitpoints;
		
		KillableComponent killable = engine.createComponent(KillableComponent.class);
		killable.deathTime = 1.0f;
		//Temporary states+
		
		killable.DYING_STATE = 5;
		killable.DEAD_STATE = 6;
		
		texture.region = Assets.castle;
		entity.add(position);
		entity.add(texture);
		entity.add(bounds);
		entity.add(state);
		entity.add(castle);
		entity.add(hitpoint);
		entity.add(killable);
		
		
		engine.addEntity(entity);
	}
	
	public void createFootman(float x, float y, int troopType, int team)
	{
		Entity entity = engine.createEntity();
		
		if (troopType == 0)
		{
			TransformComponent2D position = engine.createComponent(TransformComponent2D.class);
			TextureComponent texture = engine.createComponent(TextureComponent.class);
			VelocityComponent velocity = engine.createComponent(VelocityComponent.class);
			BoundsComponent2D bounds = engine.createComponent(BoundsComponent2D.class);
			StateComponent state = engine.createComponent(StateComponent.class);
			//Enemy enemy = engine.createComponent(Enemy.class);
			AnimationComponent2D animation = engine.createComponent(AnimationComponent2D.class);
			HPComponent hitPoints = engine.createComponent(HPComponent.class);
			InfantryComponent infantry = engine.createComponent(InfantryComponent.class);
			animation.animations.put(infantry.IDLE_STATE, null);
			animation.animations.put(infantry.RUNNING_STATE, null);
			animation.animations.put(infantry.ATTACKING_STATE, null);
			animation.animations.put(3, null);
			animation.animations.put(4, null);
			animation.animations.put(infantry.DYING_STATE, Assets.deathArcherAnimation);
			animation.animations.put(infantry.DEAD_STATE, null);
			
			KillableComponent killable = engine.createComponent(KillableComponent.class);
			killable.deathTime = 0.9f;
			killable.DYING_STATE = infantry.DYING_STATE;
			killable.DEAD_STATE = infantry.DEAD_STATE;
			
			infantry.dps = 10.0f;
			
			position.position.x = x;
			position.position.y = y;
			velocity.velocity.x = -3.0f;
			velocity.velocity.y = 0.0f;
			bounds.bounds.width = 76.0f;
			bounds.bounds.height = 92.0f;
			
			hitPoints.hitpoints = 30;
			
			texture.region = Assets.swordGuy;
			entity.add(position);
			entity.add(texture);
			entity.add(velocity);
			entity.add(bounds);
			entity.add(state);
			entity.add(animation);
			entity.add(infantry);
			entity.add(killable);
			entity.add(hitPoints);
			//entity.add(enemy);
			//engine.addEntity(entity);
		}
		else if (troopType == 1)
		{
			//Entity entity = engine.createEntity();
			TransformComponent2D position = engine.createComponent(TransformComponent2D.class);
			TextureComponent texture = engine.createComponent(TextureComponent.class);
			VelocityComponent velocity = engine.createComponent(VelocityComponent.class);
			BoundsComponent2D bounds = engine.createComponent(BoundsComponent2D.class);
			StateComponent state = engine.createComponent(StateComponent.class);
			HPComponent hitPoints = engine.createComponent(HPComponent.class);
			//entity.add(new HPComponent());
			
			AnimationComponent2D animation = engine.createComponent(AnimationComponent2D.class);
			animation.animations.put(ArcherComponent.IDLE_STATE, null);
			animation.animations.put(ArcherComponent.WALKING_STATE, null);
			animation.animations.put(ArcherComponent.LOADING_STATE, Assets.archerLoadingAnimation);
			animation.animations.put(ArcherComponent.READY_STATE, null);
			animation.animations.put(ArcherComponent.SHOOT_STATE, null);
			animation.animations.put(ArcherComponent.DYING_STATE, Assets.deathArcherAnimation);
			animation.animations.put(ArcherComponent.DEAD_STATE, null);
			
			position.position.x = x;
			position.position.y = y;
			//velocity.velocity.x = -1.5f;
			velocity.velocity.x = 0.0f;
			velocity.velocity.y = 0.0f;
			bounds.bounds.width = 76.0f;
			bounds.bounds.height = 92.0f;
			//state.state = ArcherComponent.LOADING_STATE;
			state.state = ArcherComponent.WALKING_STATE;
			
			hitPoints.hitpoints = 30;
			
			texture.region = Assets.swordGuy;
			entity.add(position);
			entity.add(texture);
			entity.add(velocity);
			entity.add(bounds);
			entity.add(state);
			entity.add(animation);
			entity.add(hitPoints);
			
			//entity.add(enemy);
			//engine.addEntity(entity);
		}
		else if (troopType == 2)
		{
			//Entity entity = engine.createEntity();
			TransformComponent2D position = engine.createComponent(TransformComponent2D.class);
			TextureComponent texture = engine.createComponent(TextureComponent.class);
			VelocityComponent velocity = engine.createComponent(VelocityComponent.class);
			BoundsComponent2D bounds = engine.createComponent(BoundsComponent2D.class);
			StateComponent state = engine.createComponent(StateComponent.class);
			HPComponent hitPoints = engine.createComponent(HPComponent.class);
			FlammableComponent flammable = engine.createComponent(FlammableComponent.class);
			//entity.add(new HPComponent());
			
			AnimationComponent2D animation = engine.createComponent(AnimationComponent2D.class);
			animation.animations.put(ArcherComponent.IDLE_STATE, null);
			animation.animations.put(ArcherComponent.WALKING_STATE, null);
			animation.animations.put(ArcherComponent.LOADING_STATE, Assets.catapultLoadingAnimation);
			animation.animations.put(ArcherComponent.READY_STATE, null);
			animation.animations.put(ArcherComponent.SHOOT_STATE, null);
			animation.animations.put(ArcherComponent.DYING_STATE, Assets.catapultDeathAnimation);
			//animation.animations.put(ArcherComponent.ON_FIRE_STATE, Assets.catapultOnFireAnimation);
			//animation.animations.put(key, value)
			
			position.position.x = x;
			position.position.y = y;
			//velocity.velocity.x = -1.5f;
			velocity.velocity.x = 0.0f;
			velocity.velocity.y = 0.0f;
			
			//state.state = ArcherComponent.LOADING_STATE;
			state.state = ArcherComponent.WALKING_STATE;
			
			hitPoints.hitpoints = 200;
			
			texture.region = Assets.catapult;
			
			bounds.bounds.width = texture.region.getRegionWidth();
			bounds.bounds.height = texture.region.getRegionHeight();
			
			entity.add(position);
			entity.add(texture);
			entity.add(velocity);
			entity.add(bounds);
			entity.add(state);
			entity.add(animation);
			entity.add(hitPoints);
			entity.add(flammable);
		}
		
		if (team == 0)
		{
			if (troopType == 1)
			{
				
				PlayerArcherComponent archer = engine.createComponent(PlayerArcherComponent.class);
				//Enemy enemy = engine.createComponent(Enemy.class);
				archer.interval = 2.0f + testArcherTimeMod;
				//archer.interval = 2.0f;
				testArcherTimeMod += 1.0f;
				entity.getComponent(StateComponent.class).state = ArcherComponent.LOADING_STATE;
				//archer.state = archer.LOADING_STATE;
				entity.add(archer);
				//////
				KillableComponent killable = engine.createComponent(KillableComponent.class);
				killable.deathTime = 0.9f;
				ArcherComponent archer1 = engine.createComponent(ArcherComponent.class);
				killable.DYING_STATE = archer1.DYING_STATE;
				killable.DEAD_STATE = archer1.DEAD_STATE;
				entity.add(killable);
				//////
			}
			PlayerComponent player = engine.createComponent(PlayerComponent.class);
			entity.add(player);
		}
		else if (team == 1)
		{
			if (troopType == 1)
			{
				ArcherComponent archer = engine.createComponent(ArcherComponent.class);
				
				//Enemy enemy = engine.createComponent(Enemy.class);
				archer.interval = 2.0f + testArcherTimeMod;
				//archer.interval = 2.0f;
				testArcherTimeMod += 1.0f;
				archer.range += testArcherTimeMod * 10.0f;
				//archer.state = archer.LOADING_STATE;
				entity.add(archer);
				KillableComponent killable = engine.createComponent(KillableComponent.class);
				killable.deathTime = 0.9f;
				killable.DYING_STATE = archer.DYING_STATE;
				killable.DEAD_STATE = archer.DEAD_STATE;
				entity.add(killable);
				
				
			}
			else if (troopType == 2)
			{
				ArcherComponent archer = engine.createComponent(ArcherComponent.class);
				archer.range = 800;
				//Enemy enemy = engine.createComponent(Enemy.class);
				archer.interval = 2.0f + testArcherTimeMod;
				//archer.interval = 2.0f;
				testArcherTimeMod += 1.0f;
				archer.range += testArcherTimeMod * 10.0f;
				//archer.state = archer.LOADING_STATE;
				entity.add(archer);
				KillableComponent killable = engine.createComponent(KillableComponent.class);
				killable.deathTime = 0.9f;
				killable.DYING_STATE = archer.DYING_STATE;
				killable.DEAD_STATE = archer.DEAD_STATE;
				entity.add(killable);
			}
			Enemy enemy = engine.createComponent(Enemy.class);
			enemy.points = 10 + 20 * troopType;
			entity.add(enemy);
		}
		
		engine.addEntity(entity);
	}
	
	public void createSpawnPoint(float x, int troopType, float interval)
	{
		Entity entity = engine.createEntity();
		TransformComponent2D position = engine.createComponent(TransformComponent2D.class);
		SpawnComponent spawn = engine.createComponent(SpawnComponent.class);
		
		position.position.x = x;
		spawn.troopType = troopType;
		spawn.interval = interval;
		
		entity.add(position);
		entity.add(spawn);
		engine.addEntity(entity);
	}
	
	public void createTest()
	{
		Entity entity = engine.createEntity();
		TransformComponent2D position = engine.createComponent(TransformComponent2D.class);
		TextureComponent texture = engine.createComponent(TextureComponent.class);
		AnimationComponent2D animation = engine.createComponent(AnimationComponent2D.class);
		StateComponent state = engine.createComponent(StateComponent.class);
		TestComponent test = engine.createComponent(TestComponent.class);
		
		texture.region = Assets.test;
		animation.animations.put(TestComponent.STATE_TEST, Assets.testAnimation);
		
		entity.add(position);
		entity.add(texture);
		entity.add(state);
		entity.add(test);
		entity.add(animation);
		
		engine.addEntity(entity);
	}
	
	public void createProjectile(float x, float y, float velocityX, float velocityY)
	{
		Entity entity = engine.createEntity();
		TransformComponent2D position = engine.createComponent(TransformComponent2D.class);
		TextureComponent texture = engine.createComponent(TextureComponent.class);
		VelocityComponent velocity = engine.createComponent(VelocityComponent.class);
		BoundsComponent2D bounds = engine.createComponent(BoundsComponent2D.class);
		StateComponent state = engine.createComponent(StateComponent.class);
		ProjectileComponent projectile = engine.createComponent(ProjectileComponent.class);
		
		position.position.x = x;
		position.position.y = y;
		velocity.velocity.x = velocityX;
		velocity.velocity.y = velocityY;
		bounds.bounds.width = 66.0f;
		bounds.bounds.height = 16.0f;
		
		projectile.drop.y = -1.0f;
		
		texture.region = Assets.arrow;
		
		entity.add(position);
		entity.add(texture);
		entity.add(velocity);
		entity.add(bounds);
		entity.add(state);
		entity.add(projectile);
		
		engine.addEntity(entity);
	}
	
	public void createFlame()
	{
		Entity entity = engine.createEntity();
		
	}
	
	
	private void createCamera()
	{
		Entity entity = engine.createEntity();
		CameraComponent2D camera = new CameraComponent2D();
		camera.camera = engine.getSystem(RenderingSystem2D.class).getCamera();
		entity.add(camera);
		engine.addEntity(entity);
	}

}
