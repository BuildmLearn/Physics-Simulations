package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Locale;

public class Wave extends SimulationType{

    private Skin skin;
    private TextureAtlas atlas;

    private Stage stage;

    private ShapeRenderer shapeRenderer;

    private float W;
    private float H;
    private Vector2 center;

    private Label freq;
    private Label period;
    private Label speed;
    private Label length;
    private Label amplitude;

    private Slider freqSlider;
    private Slider speedSlider;
    private Slider amplitudeSlider;

    private Table slidersTable;
    private Table labelsTable;

    @Override
    public void create() {

        shapeRenderer = new ShapeRenderer();

        atlas = new TextureAtlas(Gdx.files.internal("data/ui-blue.atlas"));

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.addRegions(atlas);

        stage = new Stage(new ScreenViewport());

        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();
        initialY = 3f*H/4f;
        center = new Vector2(W/3f, H/2f);


        freq = new Label("Frequency: 0.50 Hz", skin);
        period = new Label("Period: 2.00 s", skin);
        speed = new Label("Speed: 100.00 cm/s", skin);
        length = new Label("Length: 200.00 cm", skin);
        amplitude = new Label("Amplitude: 2.00 cm", skin);

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03");
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        freqSlider = new Slider(0, 1, 0.01f, false, sliderStyle);
        freqSlider.setValue(0.50f);
        freqSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "Frequency: %.2f Hz" , freqSlider.getValue());
                freq.setText(text);
                text = String.format(Locale.US, "Period: %.2f s" , 1f / freqSlider.getValue());
                period.setText(text);
                text = String.format(Locale.US, "Length: %.2f cm" , speedSlider.getValue() / freqSlider.getValue());
                length.setText(text);
                init(0);
            }
        });

        speedSlider = new Slider(100, 200, 0.1f, false, sliderStyle);
        speedSlider.setValue(100);
        speedSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "Speed: %.2f cm/s" , speedSlider.getValue());
                speed.setText(text);
                text = String.format(Locale.US, "Length: %.2f cm" , speedSlider.getValue() / freqSlider.getValue());
                length.setText(text);
                init(0);
            }
        });

        amplitudeSlider = new Slider(0.5f, 2.0f, 0.5f, false, sliderStyle);
        amplitudeSlider.setValue(20);
        amplitudeSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "Amplitude: %.2f cm" , amplitudeSlider.getValue());
                amplitude.setText(text);
                init(0);
            }
        });


        labelsTable = new Table();
        labelsTable.setDebug(false);
        labelsTable.bottom().left().padLeft(50).padBottom(50);
        labelsTable.setFillParent(true);
        labelsTable.add(freq).align(Align.left);
        labelsTable.add(freqSlider).padLeft(20).width(W/4);

        labelsTable.row().padTop(5);
        labelsTable.add(period).align(Align.left);
        labelsTable.row().padTop(5);
        labelsTable.add(speed).align(Align.left);
        labelsTable.add(speedSlider).padLeft(20).width(W/4);

        labelsTable.row().padTop(5);
        labelsTable.add(length).align(Align.left);
        labelsTable.row().padTop(5);
        labelsTable.add(amplitude).align(Align.left);
        labelsTable.add(amplitudeSlider).padLeft(20).width(W/4);

        slidersTable = new Table();
//        slidersTable.setDebug(false);
//        slidersTable.bottom().right().padRight(50).padBottom(50);
//        slidersTable.setFillParent(true);
//        table.row().expandX();
//        slidersTable.add(labelsTable);
//        slidersTable.add(freqSlider).padLeft(50);
//        slidersTable.add(speedSlider).padLeft(50);
//        slidersTable.add(amplitudeSlider).padLeft(50);

        stage.addActor(labelsTable);
        //stage.addActor(slidersTable);
        Gdx.input.setInputProcessor(stage);


        init(0);
    }
    int NUM = 720;

    float[] xPoss = new float[NUM];
    float[] yPoss = new float[NUM];
    float oscilation = 0.01f * MathUtils.random(0.1f, 2.0f);
    float initialY;
    float time = 0;
    float offsetX = 0;

    float PIXEL_TO_CM = 37.795f;
    void init(float time2) {
        float delta = 1 / 30f;
        for (int i = 0; i < NUM; i++) {
            xPoss[i] = speedSlider.getValue() * time2 + offsetX;
            yPoss[i] = (PIXEL_TO_CM * amplitudeSlider.getValue())
                    * MathUtils.sin(freqSlider.getValue()*0.02f * xPoss[i]) + initialY;
            time2 += delta;
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        this.labelsTable.setFillParent(true);
        this.labelsTable.invalidate();
        this.slidersTable.setFillParent(true);
        this.slidersTable.invalidate();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = 1/30f;

        time += delta;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        for (int i = 0; i < NUM; i++) {
             float xPos = xPoss[i] - time * speedSlider.getValue()*2;
            shapeRenderer.circle(xPos, yPoss[i], 10);

        }
        if (xPoss[NUM-1] - time * speedSlider.getValue()*2 < W) {
            //init(time);
            time = 0;
        }

        shapeRenderer.end();

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
