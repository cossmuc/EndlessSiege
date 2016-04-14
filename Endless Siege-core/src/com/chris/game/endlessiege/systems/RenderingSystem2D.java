package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.chris.game.endlessiege.components.TextureComponent;
import com.chris.game.endlessiege.components.TransformComponent2D;

public class RenderingSystem2D extends IteratingSystem {
	
	//static final float FRUSTUM_WIDTH = 800;
	//static final float FRUSTUM_HEIGHT = 480;
	static final float FRUSTUM_WIDTH = 1200;
	static final float FRUSTUM_HEIGHT = 800;
	
	private SpriteBatch batch;
	private Array<Entity> renderQueue;
	
	private ComponentMapper<TextureComponent> textureM;
	private ComponentMapper<TransformComponent2D> transformM;
	
	private OrthographicCamera cam;
	
	public RenderingSystem2D(SpriteBatch batch) {
		// TODO Auto-generated constructor stub
		super(Family.all(TransformComponent2D.class, TextureComponent.class).get());
		
		textureM = ComponentMapper.getFor(TextureComponent.class);
		transformM = ComponentMapper.getFor(TransformComponent2D.class);
		
		renderQueue = new Array<Entity>();
		
		this.batch = batch;
		cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
		//cam.position.set(FRUSTUM_WIDTH, FRUSTUM_HEIGHT, 0);
	}
	
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		for (Entity entity : renderQueue) {
			TextureComponent tex = textureM.get(entity);
			
			if (tex.region == null) {
				continue;
			}
			
			TransformComponent2D t = transformM.get(entity);
		
			float width = tex.region.getRegionWidth();
			float height = tex.region.getRegionHeight();
			batch.draw(tex.region, t.position.x, t.position.y);
			/*
			float originX = width * 0.5f;
			float originY = height * 0.5f;
			
			batch.draw(tex.region,
					   t.pos.x - originX, t.pos.y - originY,
					   originX, originY,
					   width, height,
					   t.scale.x * PIXELS_TO_METRES, t.scale.y * PIXELS_TO_METRES,
					   MathUtils.radiansToDegrees * t.rotation);
					   */
		}
		
		batch.end();
		renderQueue.clear();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		renderQueue.add(entity);
		
	}
	
	public OrthographicCamera getCamera()
	{
		return cam;
	}
	
	public void resetCamera()
	{
		cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
	}
}
