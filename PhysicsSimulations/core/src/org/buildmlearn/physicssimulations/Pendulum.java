package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Locale;


public class Pendulum extends SimulationType implements InputProcessor {

    private com.badlogic.gdx.graphics.OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;


    LineActor line1Actor;
    LineActor line2Actor;
    LineActor line3Actor;

    private Skin skin;
    private TextureAtlas atlas;

    private Stage stage;
    Stage stage2;
    Slider massSlider;

    private Table table;
    private BallActor ballActor;
    World b2world;

    Label lengthValue;
    Label angleValue;
    Slider lengthSlider;
    private Texture ballTexture;

    Body body2;

    RopeJoint ropeJoint;

    ShapeRenderer shapeRenderer;
    ShapeRenderer shapeRenderer2;

    float W;
    float H;

    public static final float RATE = 160f;

    double PE;
    double KE;
    double TME;

    @Override
    public void create() {

        // next we create the box2d debug renderer
        debugRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer2 = new ShapeRenderer();

        atlas = new TextureAtlas(Gdx.files.internal("data/ui-blue.atlas"));

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.addRegions(atlas);

        stage = new Stage(new ScreenViewport());


        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();

        ballTexture = new Texture(Gdx.files.internal("ball.png"), true);
        ballTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion ballRegion = new TextureRegion(ballTexture);

        ballActor = new BallActor(ballRegion);
        ballActor.scaleBy(4 / 18f);
        ballActor.debug();
        ballActor.setPosition(0f,2f);
        this.world();

        Label angleLabel = new Label("Angle:", skin);
        Label lengthLabel = new Label("Length:", skin);
        Label massLabel = new Label("Mass:", skin);
        Label energyLabel = new Label("Energy", skin);

        angleValue = new Label("30.0°", skin);
        lengthValue = new Label("2.0 m", skin);
        final Label massValue = new Label("2.0 kg  ", skin);


        SliderStyle sliderStyle = new SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03");
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        lengthSlider = new Slider(0, 15, 1, false, sliderStyle);
        lengthSlider.setAnimateDuration(0);
        lengthSlider.setValue(15);
        lengthSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                float length = (0.5f + lengthSlider.getValue() / 10f);
                lengthValue.setText(length + " m");
                ropeJoint.setMaxLength(length);
            }
        });

        massSlider = new Slider(2, 18, 1, false, sliderStyle);
        massSlider.setAnimateDuration(0);
        massSlider.setValue(4);
        massSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                massValue.setText("" + massSlider.getValue() / 2f + " kg  ");
                ballActor.scaleBy(massSlider.getValue() / 18f);
            }
        });

        line1Actor = new LineActor(shapeRenderer);
        line1Actor.setColor(Color.RED);
        line2Actor = new LineActor(shapeRenderer);
        line2Actor.setColor(Color.BLUE);
        line3Actor = new LineActor(shapeRenderer);
        line3Actor.setColor(Color.GREEN);
        LineActor line4Actor = new LineActor(shapeRenderer);
        line4Actor.setColor(Color.BLACK);

        table = new Table();
        table.setDebug(false);
        table.right().bottom().padRight(H/10).padBottom(H/10);
        table.setFillParent(true);
        //table.row().expandX();

        table.add(lengthLabel).padRight(10).align(Align.left);
        table.add(lengthSlider).width(W / 4).colspan(2);
        table.add(lengthValue).padLeft(10).align(Align.left);


        table.row().padTop(30);
        table.add(massLabel).padRight(10).align(Align.left);
        table.add(massSlider).width(W / 4).colspan(2);
        table.add(massValue).padLeft(10).align(Align.left);


        table.row().padTop(30);
        table.add(angleLabel).padRight(10).align(Align.left);
        table.add().colspan(2);
        table.add(angleValue).padLeft(10).align(Align.left);

        table.row().padTop(30);
        table.add(energyLabel).height(200);
        table.add(line1Actor).width(20).align(Align.center|Align.bottom);
        table.add(line2Actor).width(20).align(Align.center|Align.bottom);
        table.add(line3Actor).width(20).align(Align.center|Align.bottom);

        table.row();
        table.add();
        table.add(line4Actor).height(10).colspan(3).fillX();

        table.row().padTop(10);
        table.add();
        table.add(new Label("KE", skin)).align(Align.center);
        table.add(new Label("PE", skin)).align(Align.center);
        table.add(new Label("TME", skin)).align(Align.center);

        stage.addActor(table);

        stage2 = new Stage(new FitViewport(W/RATE, H/RATE));
        camera = (OrthographicCamera)stage2.getCamera();

        stage2.addActor(ballActor);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    void world() {
        b2world = new World(new Vector2(0, -9.81f), true);

        //BALL
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(ballActor.getX() + ballActor.getWidth()/2f,
                ballActor.getY() + ballActor.getHeight()/2f));
        bodyDef.fixedRotation = true;
        Body body = b2world.createBody(bodyDef);
        body.setType(BodyDef.BodyType.DynamicBody);
        ballActor.body = body;

        PolygonShape polygonShape = new PolygonShape();
        float halfWidth = ballActor.getWidth() / 2f;
        float halfHeight = ballActor.getHeight() / 2f;
        polygonShape.setAsBox(halfWidth, halfHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 200f;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.5f;
        body.createFixture(fixtureDef);
        polygonShape.dispose();

        //PIN POINT
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.position.set(new Vector2(W/4/RATE, H/RATE-0.2f));
        body2 = b2world.createBody(bodyDef2);
        body2.setType(BodyDef.BodyType.KinematicBody);

        PolygonShape polygonShape2 = new PolygonShape();
        float halfWidth2 = 0.25f;
        float halfHeight2 = 0.25f;
        polygonShape2.setAsBox(halfWidth2, halfHeight2);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = polygonShape2;
        body2.createFixture(fixtureDef2);
        polygonShape2.dispose();

        //ROPE
        RopeJointDef ropeJointDef = new RopeJointDef();
        ropeJointDef.bodyA = body;
        ropeJointDef.bodyB = body2;
        ropeJointDef.localAnchorA.set(0, 0);
        ropeJointDef.localAnchorB.set(0, 0);
        ropeJointDef.maxLength = 2f;
        ropeJointDef.collideConnected = false;
        ropeJoint = (RopeJoint) b2world.createJoint(ropeJointDef);

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
        float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);

        debugRenderer.render(b2world, camera.combined);
        stage.getBatch().setProjectionMatrix(camera.combined);

        //update angle
        double angle;
        float op = ballActor.body.getPosition().x - body2.getPosition().x;
        float ad = body2.getPosition().y - ballActor.body.getPosition().y;
        angle = Math.toDegrees(Math.atan(op/ad));
        float mass = massSlider.getValue() / 2f;
        float length = 0.5f + lengthSlider.getValue() / 10f;
        float g = 9.81f;
        double freq = Math.sqrt(g/length)/2*Math.PI;
        TME = g * length; //*mass;
        PE = (float)(mass * g * length * (1 - Math.cos(Math.toRadians((angle)))));
        KE = TME - PE;
//        line1Actor.setHeight((float)KE*5f);
//        line2Actor.setHeight((float)PE*5f);
//        line3Actor.setHeight((float)TME*5f);

        angleValue.setText(String.format(Locale.US, "%+5.1f°", angle));

        b2world.step(delta, 8, 3);

        if (!ballActor.isDragging)
            ballActor.updateImage();
        else
            ballActor.updateBody();

        stage.act(delta);
        stage.draw();

        shapeRenderer2.setProjectionMatrix(camera.combined);
        shapeRenderer2.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer2.setColor(Color.BLACK);
        shapeRenderer2.rectLine(body2.getPosition(), ballActor.body.getPosition(), 0.01f);
        shapeRenderer2.setColor(Color.BLUE);
        shapeRenderer2.circle(body2.getPosition().x, body2.getPosition().y, 0.05f, 360);
        shapeRenderer2.end();

        stage2.act(delta);
        stage2.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //Gdx.app.log("PEN", "touchDown " + screenX + " " + screenY);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //Gdx.app.log("PEN", "touchUp " + screenX + " " + screenY);
        ballActor.isDragging = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //Gdx.app.log("PEN", "touchDragged " + screenX + " " + screenY);
        Vector3 v = stage2.getCamera().unproject(new Vector3(screenX, screenY, 0));
        if (ballActor.getX() <= v.x && v.x <= ballActor.getX()+ballActor.getWidth()) {
            if (ballActor.getY() <= v.y && v.y <= ballActor.getY()+ballActor.getHeight()) {
                ballActor.isDragging = true;
                ballActor.setPosition(v.x - ballActor.getWidth() / 2, v.y - ballActor.getHeight() / 2);
            }
        }

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}