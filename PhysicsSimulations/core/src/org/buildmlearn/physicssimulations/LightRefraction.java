package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LightRefraction extends SimulationType{

	private Skin skin;
	private TextureAtlas atlas;

    private Stage stage;

	private Table table;

	private Texture laserTexture;

    private ShapeRenderer shapeRenderer;

    private float W;
    private float H;
    private Vector2 center;

    private Image laser;

    private Color baseColor = Color.WHITE;
    private Color firstColor = Color.WHITE;
    private Color secondColor = Color.CYAN;

    @Override
	public void create() {

		shapeRenderer = new ShapeRenderer();

		atlas = new TextureAtlas(Gdx.files.internal("data/ui-blue.atlas"));

		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		skin.addRegions(atlas);

		stage = new Stage(new ScreenViewport());

		W = Gdx.graphics.getWidth();
		H = Gdx.graphics.getHeight();
        center = new Vector2(W/3f, H/2f);

        laserTexture = new Texture(Gdx.files.internal("data/laser.png"), true);
		laserTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
		final TextureRegion laserRegion = new TextureRegion(laserTexture);

        laser = new Image(laserRegion);
		laser.addListener(new DragListener() {
			public void drag(InputEvent event, float x, float y, int pointer) {
                if (y > 0  && laser.getRotation() > -90) {
                    laser.rotateBy(-2);
                } else if (y <= 0  && laser.getRotation() < 0) {
                    laser.rotateBy(2);
                }
            }
		});
        laser.setOrigin(center.x, laser.getHeight()/2);
        laser.setPosition(0, H/2f - laser.getHeight()/2f);
        laser.debug();

		Label firstAngleLabel = new Label("Angle of incidence: 30°", skin);
		Label secondAngleLabel = new Label("Angle of refraction: 30°", skin);

		final Label firstIndexLabel = new Label("Index of Refraction: 1.00", skin);
		firstIndexLabel.setColor(Color.WHITE);

		final Label secondIndexLabel = new Label("Index of Refraction: 1.33", skin);
		secondIndexLabel.setColor(Color.WHITE);

		Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
		sliderStyle.knob = skin.getDrawable("knob_03");
		sliderStyle.background = skin.getDrawable("slider_back_hor");

		final Slider firstSlider = new Slider(100, 200, 1, false, sliderStyle);
		firstSlider.setAnimateDuration(0);
		firstSlider.setValue(100);
		firstSlider.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
                String text = String.format("Index of Refraction: %.2f" , firstSlider.getValue() / 100f);
				firstIndexLabel.setText(text);
                firstColor = baseColor.cpy().lerp(Color.CYAN, (firstSlider.getValue()/100f - 1f) / 1.5f);
        }
		});

		final Slider secondSlider = new Slider(100, 200, 1, false, sliderStyle);
		secondSlider.setAnimateDuration(0);
		secondSlider.setValue(133);
		secondSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format("Index of Refraction: %.2f" , secondSlider.getValue() / 100f);
                secondIndexLabel.setText(text);
                secondColor = baseColor.cpy().lerp(Color.CYAN, (secondSlider.getValue()/100f - 1f) / 1.5f);
            }
		});

        final SelectBox firstSelectBox = new SelectBox(skin);
        firstSelectBox.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                switch (firstSelectBox.getSelectedIndex()) {
                    case 0:
                        firstSlider.setValue(100);
                        break;
                    case 1:
                        firstSlider.setValue(133);
                        break;
                    case 2:
                        firstSlider.setValue(150);
                        break;
                    default:
                        break;
                }
            }
        });
        firstSelectBox.setItems("Air", "Water", "Glass");
        firstSelectBox.setSelected("Air");

        final SelectBox secondSelectBox = new SelectBox(skin);
        secondSelectBox.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                switch (secondSelectBox.getSelectedIndex()) {
                    case 0:
                        secondSlider.setValue(100);
                        break;
                    case 1:
                        secondSlider.setValue(133);
                        break;
                    case 2:
                        secondSlider.setValue(150);
                        break;
                    default:
                        break;
                }
            }
        });
        secondSelectBox.setItems("Air", "Water", "Glass");
        secondSelectBox.setSelected("Water");

		table = new Table();
		table.setDebug(false);
		table.center().right().padRight(W/100);
		table.setFillParent(true);
		//table.row().expandX();

        table.add(firstAngleLabel).align(Align.left);
        table.row().padTop(5);
		table.add(firstIndexLabel).align(Align.left);
        table.add(firstSelectBox);
        table.row().padTop(5);
		table.add(firstSlider).width(W / 4).colspan(3);

		table.row().padTop(30);

        table.add(secondAngleLabel).align(Align.left);
        table.row().padTop(5);
        table.add(secondIndexLabel).align(Align.left);
        table.add(secondSelectBox);
        table.row().padTop(5);
        table.add(secondSlider).width(W / 4).colspan(3);

		stage.addActor(table);
        stage.addActor(laser);
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		this.table.setFillParent(true);
		this.table.invalidate();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(firstColor);
        shapeRenderer.rect(0, H / 2, W, H / 2f);
        shapeRenderer.setColor(secondColor);
        shapeRenderer.rect(0, 0, W, H / 2f);

        shapeRenderer.setColor(Color.BLACK);
        //shapeRenderer.line(1, center.y-1, W, center.y);
        for(float y = H-H/4; y > H/4; y -= 20) {
            shapeRenderer.line(center.x, y, center.x, y-10);
        }

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rectLine(laser.getX()+laser.getWidth(),
                laser.getY()+laser.getHeight()/2f, center.x, center.y, 5);
        shapeRenderer.end();

		float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		atlas.dispose();
	}
}
