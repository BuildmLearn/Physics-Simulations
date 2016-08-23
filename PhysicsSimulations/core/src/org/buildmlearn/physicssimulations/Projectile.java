package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Locale;

public class Projectile extends SimulationType {
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

    private Image cannon;
    private BallActor ball;

    private Label speedValue;
    private Label massValue;
    private Label angleValue;
    private Label rangeValue;

    private Slider speedSlider;
    private Slider massSlider;

    private Texture playTexture;
    private Texture resetTexture;

    private Table table;

    Vector3 v;

    boolean firstPlay = true;

    public static final float RATE = 160f;

    @Override
    public void create() {

        debugRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();

        atlas = new TextureAtlas(Gdx.files.internal("data/ui-blue.atlas"));

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.addRegions(atlas);

        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();

        stage = new Stage(new ScreenViewport());
        stage2 = new Stage(new FitViewport(W/RATE, H/RATE));
        camera = (OrthographicCamera)stage2.getCamera();

        BitmapFont font = new BitmapFont(Gdx.files.internal("data/arial_30_bold.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);

        Texture cannonTexture = new Texture(Gdx.files.internal("cannon.png"), true);
        cannonTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion cannonRegion = new TextureRegion(cannonTexture);
        cannon = new Image(cannonRegion);
        cannon.setSize(cannon.getWidth()/3f, cannon.getHeight()/3f);
        cannon.setPosition(cannon.getWidth(), H/3f);
        cannon.setOrigin(215/3f, 105/3f);
        cannon.setRotation(30f);
        cannon.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                if (y > 0 && cannon.getRotation() < 90)
                    cannon.rotateBy(2);
                else if (y <= 0 && cannon.getRotation() > 0)
                    cannon.rotateBy(-2);
                angleValue.setText(String.format(Locale.US, "%.0f °" , cannon.getRotation()));
            }
        });

        Texture ballTexture = new Texture(Gdx.files.internal("ball.png"), true);
        ballTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion ballRegion = new TextureRegion(ballTexture);
        ball = new BallActor(ballRegion);
        ball.scaleBy(0.2f);
        ball.setPosition(-1f, 0);

        Texture backgroundTexture = new Texture(Gdx.files.internal("clouds.png"), true);
        backgroundTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion backgroundRegion = new TextureRegion(backgroundTexture);
        final Image background = new Image(backgroundRegion);
        background.setWidth(2*W/RATE);
        background.setHeight(H/RATE/3*2f);
        background.setPosition(0f, H/RATE/3*1f);

        playTexture = new Texture(Gdx.files.internal("play.png"), true);
        Texture pauseTexture = new Texture(Gdx.files.internal("pause.png"), true);
        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)),
                new TextureRegionDrawable(new TextureRegion(playTexture)), new TextureRegionDrawable(new TextureRegion(pauseTexture)));
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (firstPlay) {
                    firstPlay = false;
                    float speed = speedSlider.getValue();
                    float rot = cannon.getRotation();
                    ball.setPosition(v.x, v.y);
                    ball.updateBody();
                    ball.body.setLinearVelocity(MathUtils.cosDeg(rot) * speed, MathUtils.sinDeg(rot) * speed);
//                    ball.body.applyForceToCenter(new Vector2(RATE*MathUtils.cosDeg(rot) * speed, RATE*MathUtils.sinDeg(rot) * speed), true);
//                    ball.body.applyLinearImpulse(new Vector2(MathUtils.cosDeg(rot) * speed, MathUtils.sinDeg(rot) * speed), ball.body.getWorldCenter(), true);
                }
            }
        });

        resetTexture = new Texture(Gdx.files.internal("restart.png"), true);
        final ImageButton restartButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(resetTexture)));
        restartButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playButton.setChecked(false);
                firstPlay = true;
                ball.body.setLinearVelocity(0,0);
                ball.setPosition(-1, 0);
                ball.updateBody();
                camera.position.set(W/RATE/2f, camera.position.y, 0);
                cannon.setPosition(cannon.getWidth(), H/3f);
                rangeValue.setText(String.format(Locale.US, "%.1f m" , 0f));
            }
        });

        Label speedLabel = new Label("Speed", labelStyle);
        Label massLabel = new Label("Mass", labelStyle);
        Label angleLabel = new Label("Angle:", labelStyle);
        Label rangeLabel = new Label("Range:", labelStyle);

        speedValue = new Label("2.5 m/s", skin);
        massValue = new Label("10 kg", skin);
        angleValue = new Label("30 °", skin);
        rangeValue = new Label("0.0 m", skin);

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03");
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        massSlider = new Slider(5, 25, 1f, false, sliderStyle);
        massSlider.setValue(10f);
        massSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.0f kg" , massSlider.getValue());
                massValue.setText(text);

                MassData massData = ball.body.getMassData();
                float scaleFactor = massSlider.getValue() / massData.mass;
                massData.mass *= scaleFactor;
                massData.I *= scaleFactor;
                ball.body.setMassData(massData);
                ball.body.setAwake(true);
            }
        });

        speedSlider = new Slider(1f, 10, 0.1f, false, sliderStyle);
        speedSlider.setValue(2.5f);
        speedSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.1f m/s" , speedSlider.getValue());
                speedValue.setText(text);
            }
        });

        table = new Table();
        table.setDebug(false);
        table.center().bottom().padLeft(50).padBottom(50);
        table.setFillParent(true);

        table.add(speedLabel).padRight(20);
        table.add(speedSlider).width(W/5);
        table.add(speedValue).padLeft(20).padRight(20).center();
        table.add(angleLabel).padLeft(40).align(Align.left);
        table.add(angleValue).padLeft(20);

        table.row().padTop(5);
        table.add(massLabel).padRight(20);
        table.add(massSlider).width(W/5);
        table.add(massValue).padLeft(20).padRight(20).center();
        table.add(rangeLabel).padLeft(40).align(Align.left);
        table.add(rangeValue).padLeft(20);

        Table buttonsTable = new Table();
        buttonsTable.setDebug(false);
        buttonsTable.bottom().left().padLeft(30).padBottom(50);
        buttonsTable.setFillParent(true);
        buttonsTable.add(playButton).width(100).height(100);
        buttonsTable.add(restartButton).width(100).height(100).padLeft(10);

        stage.addActor(table);
        stage.addActor(buttonsTable);
        stage.addActor(cannon);

        stage2.addActor(background);
        stage2.addActor(ball);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(stage2);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    static final float G = 9.81f;

    void world() {
        b2world = new World(new Vector2(0, -G), true);

        //ball
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(ball.getX() + ball.getWidth()/2f,
                ball.getY() + ball.getHeight()/2f));
        bodyDef.fixedRotation = true;
        Body body = b2world.createBody(bodyDef);
        body.setType(BodyDef.BodyType.DynamicBody);
        ball.body = body;

        PolygonShape polygonShape = new PolygonShape();
        float halfWidth = ball.getWidth() / 2f;
        float halfHeight = ball.getHeight() / 2f;
        polygonShape.setAsBox(halfWidth, halfHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = 1f;
        body.createFixture(fixtureDef);
        polygonShape.dispose();
        MassData m = body.getMassData();
        m.mass = 10f;
        body.setMassData(m);

        //PIN POINT 1
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.position.set(new Vector2(0,0));
        Body body2 = b2world.createBody(bodyDef2);
        body2.setType(BodyDef.BodyType.StaticBody);

        PolygonShape polygonShape2 = new PolygonShape();
        float halfWidth2 = W;
        float halfHeight2 = v.y;
        polygonShape2.setAsBox(halfWidth2, halfHeight2);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = polygonShape2;
        fixtureDef2.friction = 1f;
        body2.createFixture(fixtureDef2);
        polygonShape2.dispose();

    }

    @Override
    public void resize(int width, int height) {
        stage2.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
        this.table.setFillParent(true);
        this.table.invalidate();
        v = stage.getCamera().unproject(new Vector3(cannon.getX(), cannon.getY(), 0));
        v = stage2.getCamera().unproject(v);
        world();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float delta = 1/30f;

        debugRenderer.render(b2world, camera.combined);
        stage.getBatch().setProjectionMatrix(camera.combined);

        if (playButton.isChecked()) {
            b2world.step(delta, 8, 3);
            ball.updateImage();
            rangeValue.setText(String.format(Locale.US, "%.1f m" , ball.getX()-v.x));
        }

        if (ball.getX() > W/RATE/2f) {
            if (camera.position.x != ball.getX()) {
                cannon.moveBy(-(ball.getX()-camera.position.x)*RATE, 0);
            }
            camera.position.set(ball.getX(), camera.position.y, 0);
            camera.update();
        }

        stage2.act(delta);
        stage2.draw();

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
