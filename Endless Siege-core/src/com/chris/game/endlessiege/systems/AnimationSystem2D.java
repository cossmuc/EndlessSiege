package com.chris.game.endlessiege.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.chris.game.endlessiege.components.AnimationComponent2D;
import com.chris.game.endlessiege.components.StateComponent;
import com.chris.game.endlessiege.components.TextureComponent;

public class AnimationSystem2D extends IteratingSystem {
	
	ComponentMapper<TextureComponent> tex;
	ComponentMapper<AnimationComponent2D> anim;
	ComponentMapper<StateComponent> state;
	
	public AnimationSystem2D()
	{
		super(Family.all(TextureComponent.class, AnimationComponent2D.class, StateComponent.class).get());
		
		tex = ComponentMapper.getFor(TextureComponent.class);
		anim = ComponentMapper.getFor(AnimationComponent2D.class);
		state = ComponentMapper.getFor(StateComponent.class);
	}
	
	public AnimationSystem2D(Family family) {
		super(family);
		// TODO Auto-generated constructor stub
	}

	public AnimationSystem2D(Family family, int priority) {
		super(family, priority);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		TextureComponent texture = tex.get(entity);
		AnimationComponent2D animation = anim.get(entity);
		StateComponent statecom = state.get(entity);
		
		Animation entityAnimation = animation.animations.get(statecom.state);
		
		if (animation != null && entityAnimation != null)
		{
			texture.region = entityAnimation.getKeyFrame(statecom.time);
		}
		
		statecom.time += deltaTime;
	}

}
