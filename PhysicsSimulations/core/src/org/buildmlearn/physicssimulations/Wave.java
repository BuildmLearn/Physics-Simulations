package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
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

public class Wave extends SimulationType {

    private Skin skin;
    private TextureAtlas atlas;

    private Stage stage;

    private ShapeRenderer shapeRenderer;

    private float W;
    private float H;

    private Label freqValue;
    private Label periodValue;
    private Label speedValue;
    private Label lengthValue;
    private Label amplitudeValue;

    private Slider freqSlider;
    private Slider speedSlider;
    private Slider amplitudeSlider;

    private Table table;

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

        BitmapFont font = new BitmapFont(Gdx.files.internal("data/arial_30_bold.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);

        Label freq = new Label("Frequency:", labelStyle);
        Label period = new Label("Period:", labelStyle);
        Label speed = new Label("Speed:", labelStyle);
        Label length = new Label("Length:", labelStyle);
        Label amplitude = new Label("Amplitude:", labelStyle);

        freqValue = new Label("0.50 Hz", skin);
        periodValue = new Label("2.00 s", skin);
        speedValue = new Label("100.00 cm/s", skin);
        lengthValue = new Label("200.00 cm", skin);
        amplitudeValue = new Label("2.00 cm", skin);

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03");
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        freqSlider = new Slider(0, 1, 0.01f, false, sliderStyle);
        freqSlider.setValue(0.50f);
        freqSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.2f Hz" , freqSlider.getValue());
                freqValue.setText(text);
                text = String.format(Locale.US, "%.2f s" , 1f / freqSlider.getValue());
                periodValue.setText(text);
                text = String.format(Locale.US, "%.2f cm" , speedSlider.getValue() / freqSlider.getValue());
                lengthValue.setText(text);
                init(0);
            }
        });

        speedSlider = new Slider(100, 200, 0.1f, false, sliderStyle);
        speedSlider.setValue(100);
        speedSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.2f cm/s" , speedSlider.getValue());
                speedValue.setText(text);
                text = String.format(Locale.US, "%.2f cm" , speedSlider.getValue() / freqSlider.getValue());
                lengthValue.setText(text);
                init(0);
            }
        });

        amplitudeSlider = new Slider(0.5f, 2.0f, 0.5f, false, sliderStyle);
        amplitudeSlider.setValue(20);
        amplitudeSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.2f cm" , amplitudeSlider.getValue());
                amplitudeValue.setText(text);
                init(0);
            }
        });


        table = new Table();
        table.setDebug(false);
        table.bottom().left().padLeft(50).padBottom(50);
        table.setFillParent(true);

        table.add(freq).align(Align.left);
        table.add(freqSlider).padLeft(20).width(W/4);
        table.add(freqValue).padLeft(20).align(Align.left);

        table.row().padTop(5);
        table.add(period).align(Align.left);
        table.add();
        table.add(periodValue).padLeft(20).align(Align.left);

        table.row().padTop(5);
        table.add(speed).align(Align.left);
        table.add(speedSlider).padLeft(20).width(W/4);
        table.add(speedValue).padLeft(20).align(Align.left);


        table.row().padTop(5);
        table.add(length).align(Align.left);
        table.add();
        table.add(lengthValue).padLeft(20).align(Align.left);

        table.row().padTop(5);
        table.add(amplitude).align(Align.left);
        table.add(amplitudeSlider).padLeft(20).width(W/4);
        table.add(amplitudeValue).padLeft(20).align(Align.left);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        init(0);
    }
    int NUM = 1440;
    float[] xPoss = new float[NUM];
    float[] yPoss = new float[NUM];
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
        this.table.setFillParent(true);
        this.table.invalidate();
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
