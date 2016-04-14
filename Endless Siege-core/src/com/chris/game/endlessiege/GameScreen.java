package com.chris.game.endlessiege;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chris.game.endlessiege.systems.AnimationSystem2D;
import com.chris.game.endlessiege.systems.ArcherSystem;
import com.chris.game.endlessiege.systems.BoundsSystem2D;
import com.chris.game.endlessiege.systems.BackgroundSystem;
import com.chris.game.endlessiege.systems.CastleSystem;
import com.chris.game.endlessiege.systems.CollisionSystem2D;
import com.chris.game.endlessiege.systems.DeathSystem;
import com.chris.game.endlessiege.systems.EnemySystem;
import com.chris.game.endlessiege.systems.HPSystem;
import com.chris.game.endlessiege.systems.MovementSystem;
import com.chris.game.endlessiege.systems.PlayerSystem;
import com.chris.game.endlessiege.systems.ProjectileSystem;
import com.chris.game.endlessiege.systems.RenderingSystem2D;
import com.chris.game.endlessiege.systems.SpawnSystem;
import com.chris.game.endlessiege.systems.SpawnSystem1;

public class GameScreen extends ScreenAdapter implements GestureListener {
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	//static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 3;
	
	private int state;
	
	EndlessSiege game;
	PooledEngine engine;
	World world;
	Stage stage;
	Skin skin;
	
	OrthographicCamera uiCam;
	Vector3 touchPoint;
	
	Rectangle flingBounds;
	Rectangle panBounds;
	
	Viewport viewport;
	
	public boolean flinged = false;
    public boolean pan = false;
    
    Vector2 repairButtonPosition = new Vector2();
	
	public GameScreen(EndlessSiege game) {
		// TODO Auto-generated constructor stub
		this.game = game;
		engine = new PooledEngine();
		world = new World(engine);
		
		uiCam = new OrthographicCamera(480, 320);
		Vector2 uiCamSize = new Vector2(800, 480);
		uiCam.position.set(uiCamSize.x / 2, uiCamSize.y / 2, 0);
		
		touchPoint = new Vector3();
		
		stage = new Stage();
		
		addSystems();
		
		engine.getSystem(BackgroundSystem.class).setCamera(engine.getSystem(RenderingSystem2D.class).getCamera());
		
		//engine.getSystem(BackgroundSystem.class).setCamera(uiCam);
		
		world.create();
		createUI();
		
		
		//viewport = new FitViewport(world.WORLD_WIDTH * 2, world.WORLD_HEIGHT * 2, engine.getSystem(RenderingSystem2D.class).getCamera());
		//viewport = new FitViewport(world.WORLD_WIDTH, world.WORLD_HEIGHT, uiCam);
		viewport = new FitViewport(uiCamSize.x, uiCamSize.y, uiCam);
		//engine.getSystem(RenderingSystem2D.class).getCamera().zoom = 2.0f;
		//stage.setViewport(new FitViewport(world.WORLD_WIDTH * 2, world.WORLD_HEIGHT * 2, engine.getSystem(RenderingSystem2D.class).getCamera()));
		stage.setViewport(viewport);
		viewport.apply();
		
		//engine.getSystem(RenderingSystem2D.class).getCamera().zoom = 2.0f;
		//viewport.update((int)world.WORLD_WIDTH, (int)world.WORLD_HEIGHT);
		/*
		engine.getSystem(RenderingSystem2D.class).getCamera().position.set(engine.getSystem(RenderingSystem2D.class).getCamera().viewportWidth/2, 
				engine.getSystem(RenderingSystem2D.class).getCamera().viewportHeight/2, 
				0);
		*/
		flingBounds = new Rectangle(32, 32, 240 - 32, 160 - 32);
		panBounds = new Rectangle(262, 32, 448 - 262, 160 - 32);
		
		GestureDetector gd = new GestureDetector(this);
        Gdx.input.setInputProcessor(gd);
        
        state = GAME_READY;
        pauseSystems();
        
	}
	
	public void addSystems()
	{
		engine.addSystem(new BackgroundSystem());
		engine.addSystem(new RenderingSystem2D(game.batch));
		
		engine.addSystem(new AnimationSystem2D());
		engine.addSystem(new MovementSystem());
		//engine.addSystem(new SpawnSystem(5, world));
		engine.addSystem(new SpawnSystem1(world));
		engine.addSystem(new BoundsSystem2D());
		engine.addSystem(new CollisionSystem2D(world));
		engine.addSystem(new ArcherSystem(world));
		engine.addSystem(new PlayerSystem(world));
		engine.addSystem(new ProjectileSystem());
		engine.addSystem(new CastleSystem(world));
		engine.addSystem(new HPSystem());
		engine.addSystem(new DeathSystem(world));
		
		
	}
	
	public void removeSystems()
	{
		engine.removeSystem(engine.getSystem(RenderingSystem2D.class));
		engine.removeSystem(engine.getSystem(AnimationSystem2D.class));
		engine.removeSystem(engine.getSystem(MovementSystem.class));
		engine.removeSystem(engine.getSystem(SpawnSystem1.class));
		engine.removeSystem(engine.getSystem(BoundsSystem2D.class));
		engine.removeSystem(engine.getSystem(CollisionSystem2D.class));
		engine.removeSystem(engine.getSystem(ArcherSystem.class));
		engine.removeSystem(engine.getSystem(PlayerSystem.class));
		engine.removeSystem(engine.getSystem(ProjectileSystem.class));
		engine.removeSystem(engine.getSystem(CastleSystem.class));
		engine.removeSystem(engine.getSystem(HPSystem.class));
		engine.removeSystem(engine.getSystem(DeathSystem.class));
	}
	
	public void update(float deltaTime)
	{
		engine.update(deltaTime);
		if (state == GAME_READY)
		{
			updateReady();
			
		}
		else if (state == GAME_RUNNING)
		{
			updateRunning();
		}
		else if (state == GAME_OVER)
		{
			updateGameOver();
		}
		//engine.getSystem(RenderingSystem2D.class).getCamera().translate(new Vector2(1.0f, 0));
		
	}
	
	public void updateReady()
	{
		if (Gdx.input.justTouched())
		{
			state = GAME_RUNNING;
			world.state = world.WORLD_STATE_RUNNING;
			resumeSystems();
			
		}
	}
	
	public void updateRunning()
	{
		if (world.state == world.WORLD_STATE_GAME_OVER)
		{
			state = GAME_OVER;
			//pauseSystems();
		}
	}
	
	public void updateGameOver()
	{
		if (Gdx.input.justTouched()) {
			restartGame();
			
		}
	}
	
	public void restartGame()
	{
		engine.removeAllEntities();
		game.create();
	}
	
	public void createUI()
	{
		Label scoreLabel = new Label("Score: " + world.score, Assets.skin);
		scoreLabel.setName("lblScore");
		//scoreLabel.setColor(Color.WHITE);
		scoreLabel.setFontScale(2.0f, 2.0f);
		//scoreLabel.setBounds(32, Gdx.graphics.getHeight() - 64, 100, 20);
		scoreLabel.setBounds(32, world.WORLD_HEIGHT - 64, 100, 20);

		Label hpLabel = new Label("HP: " + world.hp, Assets.skin);
		hpLabel.setName("lblHP");
		hpLabel.setFontScale(2.0f, 2.0f);
		hpLabel.setBounds(32, world.WORLD_HEIGHT - 32, 100, 20);
		//hpLabel.setBounds(0, 0, 100, 20);
		
		TextButton repairButton = new TextButton("Repair", Assets.skin);
		repairButtonPosition = engine.getSystem(CastleSystem.class).getCastleBounds();
		//repairButton.setBounds(repairButtonPosition.x, repairButtonPosition.y, 100, 30);
		repairButton.setBounds(0, 0, 100, 30);
		repairButton.setName("btnRepair");
		
		
		
		
		stage.addActor(scoreLabel);
		stage.addActor(hpLabel);
		stage.addActor(repairButton);
	}
	
	public void drawUI(float delta)
	{
		uiCam.update();
		Label score = stage.getRoot().findActor("lblScore");
		score.setText("Score: " + Integer.toString(world.score));
		Label hp = stage.getRoot().findActor("lblHP");
		hp.setText("HP: " + Integer.toString(world.hp));
		TextButton repair = stage.getRoot().findActor("btnRepair");
		//repairButtonPosition = engine.getSystem(CastleSystem.class).getCastleBounds();
		//repair.setBounds(repairButtonPosition.x, repairButtonPosition.y, 100, 30);
		//game.batch.setProjectionMatrix(uiCam.combined);
		
		/*
		game.batch.setProjectionMatrix(uiCam.combined);
		game.batch.begin();
		//game.batch.draw(Assets.testFling, flingBounds.x, flingBounds.y, flingBounds.width, flingBounds.height);
		//game.batch.draw(Assets.testPan, panBounds.x, panBounds.y, panBounds.width, panBounds.height);
		game.batch.end();
		*/
	}
	
	public void render(float delta)
	{
		update(delta);
		drawUI(delta);
		stage.act(delta);
		stage.draw();
	}
	
	public void resize(int width, int height)
	{
		viewport.update(width, height);
		
		engine.getSystem(RenderingSystem2D.class).getCamera().position.set(engine.getSystem(RenderingSystem2D.class).getCamera().viewportWidth/2, 
				engine.getSystem(RenderingSystem2D.class).getCamera().viewportHeight/2, 
				0);
	}
	
	private void pauseSystems()
	{
		/*
		engine.getSystem(systemType)(new RenderingSystem2D(game.batch));
		engine.addSystem(new AnimationSystem2D());
		engine.addSystem(new MovementSystem());
		//engine.addSystem(new SpawnSystem(5, world));
		engine.addSystem(new SpawnSystem1(world));
		engine.addSystem(new BoundsSystem2D());
		engine.addSystem(new CollisionSystem2D(world));
		engine.addSystem(new ArcherSystem(world));
		engine.addSystem(new PlayerSystem(world));
		engine.addSystem(new ProjectileSystem());
		engine.addSystem(new HPSystem());
		engine.addSystem(new DeathSystem());
		*/
		engine.getSystem(AnimationSystem2D.class).setProcessing(false);
		engine.getSystem(MovementSystem.class).setProcessing(false);
		engine.getSystem(SpawnSystem1.class).setProcessing(false);
		engine.getSystem(BoundsSystem2D.class).setProcessing(false);
		engine.getSystem(CollisionSystem2D.class).setProcessing(false);
		engine.getSystem(ArcherSystem.class).setProcessing(false);
		engine.getSystem(PlayerSystem.class).setProcessing(false);
		engine.getSystem(ProjectileSystem.class).setProcessing(false);
		engine.getSystem(CastleSystem.class).setProcessing(false);
		engine.getSystem(HPSystem.class).setProcessing(false);
		engine.getSystem(DeathSystem.class).setProcessing(false);
		
	}
	
	private void resumeSystems()
	{
		engine.getSystem(AnimationSystem2D.class).setProcessing(true);
		engine.getSystem(MovementSystem.class).setProcessing(true);
		engine.getSystem(SpawnSystem1.class).setProcessing(true);
		engine.getSystem(BoundsSystem2D.class).setProcessing(true);
		engine.getSystem(CollisionSystem2D.class).setProcessing(true);
		engine.getSystem(ArcherSystem.class).setProcessing(true);
		engine.getSystem(PlayerSystem.class).setProcessing(true);
		engine.getSystem(ProjectileSystem.class).setProcessing(true);
		engine.getSystem(CastleSystem.class).setProcessing(true);
		engine.getSystem(HPSystem.class).setProcessing(true);
		engine.getSystem(DeathSystem.class).setProcessing(true);
		
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		//Vector3 touchpoint = engine.getSystem(RenderingSystem2D.class).getCamera().unproject(new Vector3(x, y, 0));
		Vector3 touchpoint = new Vector3();
		
		Gdx.app.log("x touch", "x: " + x);
		
		Gdx.app.log("x position", "x cam: " + engine.getSystem(RenderingSystem2D.class).getCamera().position.x);
		
		engine.getSystem(RenderingSystem2D.class).getCamera().unproject(touchPoint.set(x, y, 0));
		
		//if (touchpoint.x < engine.getSystem(RenderingSystem2D.class).getCamera().viewportWidth / 2)
		Gdx.app.log("x touch unproj", "x un: " + touchPoint.x);
		if (touchPoint.x - (engine.getSystem(RenderingSystem2D.class).getCamera().position.x - world.WORLD_WIDTH / 2) < world.WORLD_WIDTH / 1.5)
		//if (flingBounds.contains(touchPoint.x, touchPoint.y))
		{
			flinged = true;
			pan = false;
		}
		//else if (touchpoint.x > engine.getSystem(RenderingSystem2D.class).getCamera().viewportWidth / 2)
		else if (touchPoint.x - (engine.getSystem(RenderingSystem2D.class).getCamera().position.x - world.WORLD_WIDTH / 2) > world.WORLD_WIDTH / 1.5)
		//else if (panBounds.contains(touchPoint.x, touchPoint.y))
		{
			pan = true;
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		engine.getSystem(RenderingSystem2D.class).resetCamera();
		pan = false;
		flinged = false;
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		//engine.getSystem(RenderingSystem2D.class).getCamera().translate(new Vector2(velocityX, velocityY));
		//engine.getSystem(PlayerSystem.class).setAngleAndPower(new Vector2(velocityX, velocityY));
		//Gdx.app.log("MyTag", "flinged: ");
		if (flinged)
		{
			engine.getSystem(PlayerSystem.class).fireWeapon(velocityX, velocityY);
			flinged = false;
		}
		
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		if (pan)
		{
			engine.getSystem(RenderingSystem2D.class).getCamera().translate(new Vector2(-deltaX, 0));
			
		}
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		//engine.getSystem(RenderingSystem2D.class).resetCamera();
		pan = false;
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
