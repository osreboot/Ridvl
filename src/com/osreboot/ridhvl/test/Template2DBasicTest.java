package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class Template2DBasicTest extends HvlTemplate2DBasic{

	public Template2DBasicTest(){
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	static HvlFontPainter2D fontPainter;
	float gradient = 0;

	@Override
	public void initialize(){		
		textureLoader.loadResource("White");
		textureLoader.loadResource("Font");

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18);
	}

	@Override
	public void update(float delta){
		gradient = gradient < 1280 ? gradient + (delta*1000) : 0;

		for(int i = 0; i < 360; i++){
			hvlDrawQuad(0, i*2, gradient - (i*2), 2, textureLoader.getResource(0), new Color(1f, ((float)i - 180)/180, (float)i/360));
		}

		hvlRotate(300f, 300f, gradient/1280f*360f);
		hvlDrawQuad(250, 250, 100, 100, textureLoader.getResource(0));
		hvlResetRotation();

		fontPainter.hvlDrawWord("test of the most basic template!", 10, 10, 1260, 100, new Color(gradient/1280f, gradient/1280f, gradient/1280f));
		fontPainter.hvlDrawWord("and rotation! yay?", 10, 100, 0.5f, new Color(1 - (gradient/1280f), 1 - (gradient/1280f), 1 - (gradient/1280f)));
	}

}
