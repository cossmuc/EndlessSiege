package com.chris.game.endlessiege.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

public class AnimationComponent2D implements Component {
	public IntMap<Animation> animations = new IntMap<Animation>();
}
