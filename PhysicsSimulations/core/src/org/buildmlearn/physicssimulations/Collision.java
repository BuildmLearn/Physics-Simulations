package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Locale;

/**
 * The <code>Collision</code> class draws the screen for Collision Simulation
 * @author  Costin Giorgian
 */
public class Collision extends SimulationType {
    private com.badlogic.gdx.graphics.OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    private Skin skin;
    private TextureAtlas atlas;

    private Stage stage;
    private Stage stage2;
    World b2world;

    private ImageButton playButton;

    private ShapeRenderer shapeRenderer;

    private float W;
    private float H;

    private BallActor leftCar;
    private BallActor rightCar;

    private Label speedLeftValue;
    private Label speedRightValue;
    private Label massLeftValue;
    private Label massRightValue;

    private Slider speedLeftSlider;
    private Slider speedRightSlider;
    private Slider massLeftSlider;
    private Slider massRightSlider;

    private Texture playTexture;
    private Texture resetTexture;

    private Table table;

    ImageButton elasticImage;
    ImageButton inelasticImage;

    public static final float RATE = 160f;

    @Override
    public void create() {

        debugRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();

        atlas = new TextureAtlas(Gdx.files.internal("data/ui-blue.atlas"));

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.addRegions(atlas);

        stage = new Stage(new ScreenViewport());

        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();

        BitmapFont font = new BitmapFont(Gdx.files.internal("data/arial_30_bold.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);

        Texture leftCarTexture = new Texture(Gdx.files.internal("car1.png"), true);
        leftCarTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion leftCarRegion = new TextureRegion(leftCarTexture);
        leftCar = new BallActor(leftCarRegion);
        leftCar.setPosition(0f, 2f);

        Texture rightCarTexture = new Texture(Gdx.files.internal("car3.png"), true);
        rightCarTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion rightCarRegion = new TextureRegion(rightCarTexture);
        rightCar = new BallActor(rightCarRegion);
        rightCar.setPosition(W/RATE - rightCar.getWidth(), 2f);

        Texture roadTexture = new Texture(Gdx.files.internal("road.png"), true);
        roadTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion roadRegion = new TextureRegion(roadTexture);
        Image road = new Image(roadRegion);
        road.setWidth(W/RATE);
        road.setHeight(road.getHeight()/RATE);
        road.setPosition(0f, 2f-road.getHeight()/2f);


        world();

        playTexture = new Texture(Gdx.files.internal("play.png"), true);
        Texture pauseTexture = new Texture(Gdx.files.internal("pause.png"), true);
        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)),
                new TextureRegionDrawable(new TextureRegion(playTexture)), new TextureRegionDrawable(new TextureRegion(pauseTexture)));
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                leftCar.body.setLinearVelocity(speedLeftSlider.getValue(),0);
                rightCar.body.setLinearVelocity(speedRightSlider.getValue(),0);
            }
        });

        resetTexture = new Texture(Gdx.files.internal("restart.png"), true);
        final ImageButton restartButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(resetTexture)));
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                leftCar.body.setLinearVelocity(0,0);
                leftCar.setPosition(0f, 2f);
                leftCar.updateBody();
                rightCar.body.setLinearVelocity(0,0);
                rightCar.setPosition(W/RATE - rightCar.getWidth(), 2f);
                rightCar.updateBody();
                playButton.setChecked(false);
                String text = String.format(Locale.US, "%.1f m/s" , speedLeftSlider.getValue());
                speedLeftValue.setText(text);
                text = String.format(Locale.US, "%.1f m/s" , speedRightSlider.getValue());
                speedRightValue.setText(text);
            }
        });

        Texture inelasticTexture = new Texture(Gdx.files.internal("inelastic.png"), true);
        final Texture inelasticPressed= new Texture(Gdx.files.internal("inelastic_press.png"), true);
        inelasticImage = new ImageButton(new TextureRegionDrawable(new TextureRegion(inelasticTexture)),
                new TextureRegionDrawable(new TextureRegion(inelasticTexture)), new TextureRegionDrawable(new TextureRegion(inelasticPressed)));
        inelasticImage.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (inelasticImage.isChecked()) {
                    elasticImage.setChecked(false);
                    leftCar.body.getFixtureList().get(0).setRestitution(0.0f);
                    rightCar.body.getFixtureList().get(0).setRestitution(0.0f);
                }
                else {
                    elasticImage.setChecked(true);
                    leftCar.body.getFixtureList().get(0).setRestitution(0.5f);
                    rightCar.body.getFixtureList().get(0).setRestitution(0.5f);
                }
            }
        });

        Texture elasticTexture = new Texture(Gdx.files.internal("elastic.png"), true);
        final Texture elasticPressed = new Texture(Gdx.files.internal("elastic_press.png"), true);
        elasticImage = new ImageButton(new TextureRegionDrawable(new TextureRegion(elasticTexture)),
                new TextureRegionDrawable(new TextureRegion(elasticTexture)), new TextureRegionDrawable(new TextureRegion(elasticPressed)));
        elasticImage.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (elasticImage.isChecked()) {
                    inelasticImage.setChecked(false);
                    leftCar.body.getFixtureList().get(0).setRestitution(0.5f);
                    rightCar.body.getFixtureList().get(0).setRestitution(0.5f);
                }
                else {
                    inelasticImage.setChecked(true);
                    leftCar.body.getFixtureList().get(0).setRestitution(0.0f);
                    rightCar.body.getFixtureList().get(0).setRestitution(0.0f);
                }
            }
        });
        inelasticImage.setChecked(true);

        Label speedLabel = new Label("Speed", labelStyle);
        Label massLabel = new Label("Mass", labelStyle);

        speedLeftValue = new Label("2.5 m/s", skin);
        speedRightValue = new Label("-3.4 m/s", skin);
        massLeftValue = new Label("50 kg", skin);
        massRightValue = new Label("150 kg", skin);

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03");
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        massLeftSlider = new Slider(50, 200, 1f, false, sliderStyle);
        massLeftSlider.setValue(50f);
        massLeftSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.0f kg" , massLeftSlider.getValue());
                massLeftValue.setText(text);

                MassData massData = leftCar.body.getMassData();
                float scaleFactor = massLeftSlider.getValue() / massData.mass;
                massData.mass *= scaleFactor;
                massData.I *= scaleFactor;
                leftCar.body.setMassData(massData);
                leftCar.body.setAwake(true);
            }
        });

        speedLeftSlider = new Slider(0f, 15, 0.1f, false, sliderStyle);
        speedLeftSlider.setValue(2.5f);
        speedLeftSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.1f m/s" , speedLeftSlider.getValue());
                speedLeftValue.setText(text);
            }
        });

        massRightSlider = new Slider(50, 200, 1f, false, sliderStyle);
        massRightSlider.setValue(150f);
        massRightSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.0f kg" , massRightSlider.getValue());
                massRightValue.setText(text);

                MassData massData = rightCar.body.getMassData();
                float scaleFactor = massRightSlider.getValue() / massData.mass;
                massData.mass *= scaleFactor;
                massData.I *= scaleFactor;
                rightCar.body.setMassData(massData);
                rightCar.body.setAwake(true);
            }
        });

        speedRightSlider = new Slider(-15f, 0f, 0.1f, false, sliderStyle);
        speedRightSlider.setValue(-3.4f);
        speedRightSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.1f m/s" , speedRightSlider.getValue());
                speedRightValue.setText(text);
            }
        });

        table = new Table();
        table.setDebug(false);
        table.center().bottom().padLeft(50).padBottom(50);
        table.setFillParent(true);

        table.add(speedLeftValue).padRight(20);
        table.add(speedLeftSlider).width(W/5);
        table.add(speedLabel).padLeft(20).padRight(20).center();
        table.add(speedRightSlider).width(W/5);
        table.add(speedRightValue).padLeft(20);

        table.row().padTop(5);
        table.add(massLeftValue).padRight(20);
        table.add(massLeftSlider).width(W/5);
        table.add(massLabel).padLeft(20).padRight(20).center();
        table.add(massRightSlider).width(W/5);
        table.add(massRightValue).padLeft(20);

        Table buttonsTable = new Table();
        buttonsTable.setDebug(false);
        buttonsTable.top().left().padLeft(30);
        buttonsTable.setFillParent(true);
        buttonsTable.add(playButton).width(100).height(100);
        buttonsTable.add(restartButton).width(100).height(100).padLeft(10);

        Table switchTable = new Table();
        switchTable.setDebug(true);
        switchTable.top().right().padRight(30).padTop(30);
        switchTable.setFillParent(true);
        switchTable.add(inelasticImage);
        switchTable.add(elasticImage);

        stage.getDebugColor().set(Color.BLACK);
        stage.addActor(table);
        stage.addActor(buttonsTable);
        stage.addActor(switchTable);


        stage2 = new Stage(new FitViewport(W/RATE, H/RATE));
        camera = (OrthographicCamera)stage2.getCamera();

        stage2.addActor(road);
        stage2.addActor(leftCar);
        stage2.addActor(rightCar);

        Gdx.input.setInputProcessor(stage);
    }

    void world() {
        b2world = new World(new Vector2(0, 0f), true);

        //car
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(rightCar.getX() + rightCar.getWidth()/2f,
                rightCar.getY() + rightCar.getHeight()/2f));
        bodyDef.fixedRotation = true;
        Body body = b2world.createBody(bodyDef);
        body.setType(BodyDef.BodyType.DynamicBody);
        rightCar.body = body;

        PolygonShape polygonShape = new PolygonShape();
        float halfWidth = rightCar.getWidth() / 2f;
        float halfHeight = rightCar.getHeight() / 2f;
        polygonShape.setAsBox(halfWidth, halfHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);
        polygonShape.dispose();
        MassData m = body.getMassData();
        m.mass = 150f;
        body.setMassData(m);

        //PIN POINT
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.position.set(new Vector2(leftCar.getX() + leftCar.getWidth()/2f,
                leftCar.getY() + leftCar.getHeight()/2f));
        bodyDef2.fixedRotation = true;
        Body body2 = b2world.createBody(bodyDef2);
        body2.setType(BodyDef.BodyType.DynamicBody);
        leftCar.body = body2;

        PolygonShape polygonShape2 = new PolygonShape();
        float halfWidth2 = leftCar.getWidth() / 2f;
        float halfHeight2 = leftCar.getHeight() / 2f;
        polygonShape2.setAsBox(halfWidth2, halfHeight2);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = polygonShape2;
        body2.createFixture(fixtureDef);
        polygonShape2.dispose();
        m = body2.getMassData();
        m.mass = 50f;
        body2.setMassData(m);
    }

    @Override
    public void resize(int width, int height) {
        stage2.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
        this.table.setFillParent(true);
        this.table.invalidate();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float delta = 1/30f;

//        debugRenderer.render(b2world, camera.combined);
//        stage.getBatch().setProjectionMatrix(camera.combined);
        if (playButton.isChecked()) {
            b2world.step(delta, 8, 3);
            leftCar.updateImage();
            rightCar.updateImage();
            String text = String.format(Locale.US, "%.1f m/s" , leftCar.body.getLinearVelocity().x);
            speedLeftValue.setText(text);
            text = String.format(Locale.US, "%.1f m/s" , rightCar.body.getLinearVelocity().x);
            speedRightValue.setText(text);
        }

        stage.act(delta);
        stage.draw();

        stage2.act(delta);
        stage2.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
    }

}
