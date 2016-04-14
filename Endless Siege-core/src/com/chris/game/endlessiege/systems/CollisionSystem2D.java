package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.chris.game.endlessiege.World;
import com.chris.game.endlessiege.components.BoundsComponent2D;
import com.chris.game.endlessiege.components.Castle;
import com.chris.game.endlessiege.components.Enemy;
import com.chris.game.endlessiege.components.HPComponent;
import com.chris.game.endlessiege.components.InfantryComponent;
import com.chris.game.endlessiege.components.KillableComponent;
import com.chris.game.endlessiege.components.PlayerArcherComponent;
import com.chris.game.endlessiege.components.ProjectileComponent;
import com.chris.game.endlessiege.components.StateComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;
import com.chris.game.endlessiege.components.VelocityComponent;

public class CollisionSystem2D extends EntitySystem {
	
	private ComponentMapper<BoundsComponent2D> bm;
	private ComponentMapper<TransformComponent2D> tm;
	private ComponentMapper<StateComponent> st;
	private ComponentMapper<VelocityComponent> vc;
	private ComponentMapper<ProjectileComponent> pc;
	private ComponentMapper<KillableComponent> kc;
	private ComponentMapper<HPComponent> hp;
	private ComponentMapper<InfantryComponent> ic;
	
	private ImmutableArray<Entity> castle;
	private ImmutableArray<Entity> enemies;
	private ImmutableArray<Entity> players;
	private ImmutableArray<Entity> projectiles;
	
	private Engine engine;
	private World world;
	
	
	public CollisionSystem2D(World world)
	{
		bm = ComponentMapper.getFor(BoundsComponent2D.class);
		tm = ComponentMapper.getFor(TransformComponent2D.class);
		st = ComponentMapper.getFor(StateComponent.class);
		vc = ComponentMapper.getFor(VelocityComponent.class);
		pc = ComponentMapper.getFor(ProjectileComponent.class);
		kc = ComponentMapper.getFor(KillableComponent.class);
		hp = ComponentMapper.getFor(HPComponent.class);
		ic = ComponentMapper.getFor(InfantryComponent.class);
		this.world = world;
	}
	
	public void addedToEngine(Engine engine)
	{
		this.engine = engine;
		castle = engine.getEntitiesFor(Family.all(Castle.class, TransformComponent2D.class, BoundsComponent2D.class,StateComponent.class).get());
		enemies = engine.getEntitiesFor(Family.all(Enemy.class, TransformComponent2D.class, BoundsComponent2D.class,StateComponent.class, VelocityComponent.class, HPComponent.class, KillableComponent.class).get());
		players = engine.getEntitiesFor(Family.all(PlayerArcherComponent.class, TransformComponent2D.class, BoundsComponent2D.class,StateComponent.class, VelocityComponent.class, HPComponent.class, KillableComponent.class).get());
		projectiles = engine.getEntitiesFor(Family.all(ProjectileComponent.class, TransformComponent2D.class, BoundsComponent2D.class, VelocityComponent.class).get());
	}
	
	public void update(float deltaTime)
	{
		//Gdx.app.log("MyTag", "castle size:" + castle.size());
		//Gdx.app.log("MyTag", "enemies size: " + enemies.size());
		for (int i = 0; i < castle.size(); i++)
		{
			Entity theCastle = castle.get(i);
			BoundsComponent2D castleBounds = bm.get(theCastle);
			HPComponent hitpoints = hp.get(theCastle);
		
			for (int j = 0; j < enemies.size(); j++)
			{
				Entity enemy = enemies.get(j);
				BoundsComponent2D enemyBounds = bm.get(enemy);
				InfantryComponent infantry = ic.get(enemy);
				
				if (enemyBounds.bounds.overlaps(castleBounds.bounds))
				{
					hitpoints.hitpoints -= (int)infantry.dps;
					world.hp = hitpoints.hitpoints;
					engine.removeEntity(enemy);
					
				}
			}
		
		}
		
		for (int i = 0; i < projectiles.size(); i++)
		{
			Entity theProjectile = projectiles.get(i);
			BoundsComponent2D projectileBounds = bm.get(theProjectile);
			VelocityComponent velocity = vc.get(theProjectile);
			ProjectileComponent projectileCom = pc.get(theProjectile);
			
			for (int j = 0; j < enemies.size(); j++)
			{
				Entity enemy = enemies.get(j);
				BoundsComponent2D enemyBounds = bm.get(enemy);
				StateComponent state = st.get(enemy);
				KillableComponent kill = kc.get(enemy);
				HPComponent hitpoints = hp.get(enemy);
				Gdx.app.log("hitpoints", "hitpoints left: " + enemy.getId() + "hitpoints: " + hitpoints.hitpoints);
				if (enemyBounds.bounds.overlaps(projectileBounds.bounds) && velocity.velocity.x > 0)
				{
					//enemy.remove(VelocityComponent.class);
					
					//Gdx.app.log("hitpoints", "hitpoints left: " + enemy.getId() + "hitpoints: " + hitpoints.hitpoints);
					
					hitpoints.hitpoints -= projectileCom.damage;
					//world.score += 10;
					
					//enemy.getComponent(HPComponent.class).hitpoints -= projectileCom.damage;
					//Gdx.app.log("hitpoints", "hitpoints left aftet: " +  enemy.getId() + "hitpoints: " + hitpoints.hitpoints);
					/*
					if (hitpoints.hitpoints <= 0)
					//if(enemy.getComponent(HPComponent.class).hitpoints <= 0)
					{
						
						//engine.removeEntity(enemy);
						state.state = kill.DYING_STATE;
						state.time = 0;
						enemy.remove(VelocityComponent.class);
					}
					//HPComponent hitpoints = hp.get(enemy);
					//hitpoints.hitpoints -= projectileCom.damage;
					 */
					 
					if (!projectileCom.multiHit)
					{
						engine.removeEntity(theProjectile);
						break;
					}
				}
			}
			
			if (theProjectile != null)
			{
				for (int l = 0; l < players.size(); l++)
				{
					Entity player = players.get(l);
					BoundsComponent2D playerBounds = bm.get(player);
					StateComponent state = st.get(player);
					KillableComponent kill = kc.get(player);
					HPComponent hitpoints = hp.get(player);
					
					if (playerBounds.bounds.overlaps(projectileBounds.bounds) && velocity.velocity.x < 0)
					{
						hitpoints.hitpoints -= projectileCom.damage;
						
						if (!projectileCom.multiHit)
						{
							engine.removeEntity(theProjectile);
							break;
						}
					}
				}
			}
		}
	}
}
