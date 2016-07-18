package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Locale;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class LightInterference extends SimulationType {

    private Skin skin;
    private TextureAtlas atlas;

    private Texture bulbTexture;

    private Stage stage;

    private ShapeRenderer shapeRenderer;

    private float W;
    private float H;

    private Label distanceValue;
    private Label wavelengthValue;
    private Label separationValue;

    private Slider distanceSlider;
    private Slider wavelengthSlider;
    private Slider separationSlider;

    private Table table;

    private RayHandler rayHandler;

    private Color color = Color.BLUE;

    private float middle;

    @Override
    public void create() {

        shapeRenderer = new ShapeRenderer();

        atlas = new TextureAtlas(Gdx.files.internal("data/ui-blue.atlas"));

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.addRegions(atlas);

        stage = new Stage(new ScreenViewport());

        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();
        middle = H/3+(H-H/3-30)/2f;

        BitmapFont font = new BitmapFont(Gdx.files.internal("data/arial_30_bold.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);

        Label distance = new Label("Screen distance", labelStyle);
        Label wavelength = new Label("Wavelength", labelStyle);
        Label separation = new Label("Split separation", labelStyle);

        distanceValue = new Label("3.0 m", skin);
        wavelengthValue = new Label("450 nm", skin);
        separationValue = new Label("0.4 mm", skin);

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03");
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        distanceSlider = new Slider(1, 7, 0.1f, false, sliderStyle);
        distanceSlider.setValue(3);
        distanceSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.1f m" , distanceSlider.getValue());
                distanceValue.setText(text);
            }
        });

        wavelengthSlider = new Slider(400, 700, 1f, false, sliderStyle);
        wavelengthSlider.setValue(450);
        wavelengthSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.0f nm" , wavelengthSlider.getValue());
                wavelengthValue.setText(text);
                setColor();
            }
        });

        separationSlider = new Slider(0.1f, 1.0f, 0.1f, false, sliderStyle);
        separationSlider.setValue(0.4f);
        separationSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.1f mm" , separationSlider.getValue());
                separationValue.setText(text);
            }
        });


        bulbTexture = new Texture(Gdx.files.internal("transparent_bulb.png"), true);
        bulbTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion bulbRegion = new TextureRegion(bulbTexture);
        final Image bulbImage = new Image(bulbRegion);
        bulbImage.setHeight(bulbImage.getHeight()/3f);
        bulbImage.setWidth(bulbImage.getWidth()/3f);
        bulbImage.setPosition(10, middle - bulbImage.getHeight()/2f);

        table = new Table();
        table.setDebug(false);
        table.center().bottom().padBottom(50);
        table.setFillParent(true);

        table.add(distance).align(Align.left);
        table.add();
        table.add(wavelength).align(Align.left).padLeft(20);
        table.add();
        table.add(separation).align(Align.left).padLeft(20);


        table.row().padTop(5);
        table.add(distanceSlider).width(W/5).align(Align.left);
        table.add(distanceValue).padLeft(20).align(Align.left);
        table.add(wavelengthSlider).padLeft(20).width(W/5).align(Align.left);
        table.add(wavelengthValue).padLeft(20).align(Align.left);
        table.add(separationSlider).padLeft(20).width(W/5).align(Align.left);
        table.add(separationValue).padLeft(20).align(Align.left);

        stage.addActor(bulbImage);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);


//        World world = new World(new Vector2(0,0), false);
//        rayHandler = new RayHandler(world);
//        rayHandler.setCombinedMatrix(stage.getCamera().combined);
//        rayHandler.setShadows(false);
//        new PointLight(rayHandler, 1000, Color.BLUE, 120, W/2, H/2);
//        rayHandler.useDiffuseLight(true);
//        rayHandler.setAmbientLight(1f, 1f, 1f, 0.1f);
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

        float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        float startPoint = W-200-distanceSlider.getValue()*40;

        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rectLine(W/4, H/3, W/4, H-30, 20);
        shapeRenderer.rectLine(startPoint, H/3, startPoint, H-30, 20);

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rectLine(W/4, middle-2.5f, W/4, middle+2.5f, 20);
        float dis = 50 * separationSlider.getValue();
        shapeRenderer.rectLine(startPoint, middle+dis, startPoint, middle+dis+5, 20);
        shapeRenderer.rectLine(startPoint, middle-dis, startPoint, middle-dis-5, 20);

        float f = (wavelengthSlider.getValue() * distanceSlider.getValue()) / separationSlider.getValue();
        f /= 100;
        f /= 2;
        int i = 0;
        while (middle + f*i < H-30) {
            shapeRenderer.rect(W-200, middle + f*i    , 150, f, color, color, Color.BLACK, Color.BLACK);
            shapeRenderer.rect(W-200, middle - f*(i+1), 150, f, Color.BLACK, Color.BLACK, color, color);
            i++;
            shapeRenderer.rect(W-200, middle + f*i    , 150, f, Color.BLACK, Color.BLACK, color, color);
            shapeRenderer.rect(W-200, middle - f*(i+1), 150, f, color, color, Color.BLACK, Color.BLACK);
            i++;
        }

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(W-200, H-30, 150, 30);
        shapeRenderer.rect(W-200, 0, 150, H/3);

        shapeRenderer.end();

        //rays
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        //shapeRenderer.circle(W/4+40, middle, 30);
        for (int j = 0; j <= 40; j+=10) {
            arc(W / 4+3*j, middle, 80+j, 360 - (40+j)/2, 40+j);
//            arc(W / 4 + 30, middle, 90, 335, 50);
//            arc(W / 4 + 60, middle, 100, 330, 60);
//            arc(W / 4 + 90, middle, 110, 325, 70);
//            arc(W / 4 + 120, middle, 120, 320, 80);
        }

        shapeRenderer.end();

        stage.act(delta);
        stage.draw();

        //rayHandler.updateAndRender();
    }

    void arc(float x, float y, float radius, float start, float degrees) {

        int segments = (int)(20 * (float)Math.cbrt(radius) * (degrees / 360.0f));

        float theta = (2 * MathUtils.PI * (degrees / 360.0f)) / segments;
        float cos = MathUtils.cos(theta);
        float sin = MathUtils.sin(theta);
        float cx = radius * MathUtils.cos(start * MathUtils.degreesToRadians);
        float cy = radius * MathUtils.sin(start * MathUtils.degreesToRadians);

        for (int i = 0; i < segments; i++) {
            shapeRenderer.circle(x + cx, y + cy, 10);
            float temp = cx;
            cx = cos * cx - sin * cy;
            cy = sin * temp + cos * cy;
            shapeRenderer.circle(x + cx, y + cy, 10);
        }
    }

    void setColor() {
        int light = (int)wavelengthSlider.getValue();
        if (light <= 450) {
            color =  Color.BLUE.cpy().lerp(Color.VIOLET, (450f - light)/(450-400));
        }
        else if (light <= 490) {
            color = Color.CYAN.cpy().lerp(Color.BLUE, (490f - light) / (490-450));
        }
        else if (light <= 520) {
            color = Color.GREEN.cpy().lerp(Color.CYAN, (520f - light) / (520-490));
        }
        else if (light <= 560) {
            color = Color.YELLOW.cpy().lerp(Color.GREEN, (560f - light) / (560-520));
        }
        else if (light <= 590) {
            color = Color.ORANGE.cpy().lerp(Color.YELLOW, (590f - light) / (590-560));
        }
        else if (light <= 635) {
            color = Color.RED.cpy().lerp(Color.ORANGE, (635f - light) / (635-590));
        }
        else if (light <= 700) {
            color = Color.RED.cpy().lerp(Color.RED, (700f - light) / (700-635));
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
        //rayHandler.dispose();
    }
}
