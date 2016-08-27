package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Locale;

/**
 * The <code>Atwood</code> class draws the screen for Atwood Machine Simulation
 * @author  Costin Giorgian
 */
public class Atwood extends SimulationType implements InputProcessor {

    private com.badlogic.gdx.graphics.OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    private Skin skin;
    private TextureAtlas atlas;

    private Stage stage;
    Stage stage2;
    private Table table;

    private BallActor blueBlock;
    private BallActor redBlock;
    private BallActor pulley;
    World b2world;

    Label redMassValue;
    Label blueMassValue;

    Slider redMassSlider;
    Slider blueMassSlider;
    private Texture blueBlockTexture;

    PulleyJoint pulleyJoint;
    DistanceJoint distanceJoint;

    ShapeRenderer shapeRenderer;
    ShapeRenderer shapeRenderer2;

    float W;
    float H;

    public static final float RATE = 160f;

    @Override
    public void create() {

        // next we create the box2d debug renderer
        debugRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer2 = new ShapeRenderer();

        atlas = new TextureAtlas(Gdx.files.internal("data/ui-blue.atlas"));

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        skin.addRegions(atlas);

        BitmapFont font = new BitmapFont(Gdx.files.internal("data/arial_30_bold.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);

        stage = new Stage(new ScreenViewport());


        W = Gdx.graphics.getWidth();
        H = Gdx.graphics.getHeight();

        Texture pulleyTexture = new Texture(Gdx.files.internal("pully.png"), true);
        pulleyTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion pulleyRegion = new TextureRegion(pulleyTexture);
        pulley = new BallActor(pulleyRegion);
        pulley.setPosition(W/4/RATE-pulley.getWidth()/2f, H/RATE-pulley.getHeight());

        blueBlockTexture = new Texture(Gdx.files.internal("blue_block.png"), true);
        blueBlockTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion blueBlockRegion = new TextureRegion(blueBlockTexture);
        blueBlock = new BallActor(blueBlockRegion);
        blueBlock.scaleBy(1 / 3f);
        blueBlock.setPosition(pulley.getX()+pulley.getWidth()-blueBlock.getWidth()/2f, 2f);

        Texture redBlockTexture = new Texture(Gdx.files.internal("red_block.png"), true);
        redBlockTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion redBlockRegion = new TextureRegion(redBlockTexture);
        redBlock = new BallActor(redBlockRegion);
        redBlock.scaleBy(2 / 3f);
        redBlock.setPosition(pulley.getX()-redBlock.getWidth()/2f, 2f);

        this.world();

        Label redMassLabel = new Label("Red block mass:", labelStyle);
        final Label blueMassLabel = new Label("Blue block mass:", labelStyle);
        final Label tensionLabel = new Label("Tension:", labelStyle);
        final Label accLabel = new Label("Acceleration:", labelStyle);

        redMassValue = new Label("2.0 kg", skin);
        blueMassValue = new Label("1.0 kg", skin);
        final Label tensionValue = new Label("13.08 N", skin);
        final Label accValue = new Label("3.27 m/s2", skin);

        SliderStyle sliderStyle = new SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03");
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        redMassSlider = new Slider(1, 4, 1, false, sliderStyle);
        redMassSlider.setAnimateDuration(0);
        redMassSlider.setValue(2);
        redMassSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                redMassValue.setText(redMassSlider.getValue() + " kg");
                redBlock.scaleBy(redMassSlider.getValue() / 3f);

                MassData massData = redBlock.body.getMassData();
                float scaleFactor = redMassSlider.getValue()/ massData.mass;
                massData.mass *= scaleFactor;
                massData.I *= scaleFactor;
                redBlock.body.setMassData(massData);
                redBlock.body.setAwake(true);

                float tension = 2 * G * blueMassSlider.getValue() * redMassSlider.getValue()
                        / (blueMassSlider.getValue() + redMassSlider.getValue());
                String text = String.format(Locale.US, "%.2f N" , tension);
                tensionValue.setText(text);

                float acc = G * Math.abs(blueMassSlider.getValue() - redMassSlider.getValue())
                        / (blueMassSlider.getValue() + redMassSlider.getValue());
                text = String.format(Locale.US, "%.2f m/s2" , acc);
                accValue.setText(text);
            }
        });

        blueMassSlider = new Slider(1, 4, 1, false, sliderStyle);
        blueMassSlider.setAnimateDuration(0);
        blueMassSlider.setValue(1);
        blueMassSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                blueMassValue.setText(blueMassSlider.getValue() + " kg");
                blueBlock.scaleBy(blueMassSlider.getValue() / 3f);

                MassData massData = blueBlock.body.getMassData();
                float scaleFactor = blueMassSlider.getValue()/ massData.mass;
                massData.mass *= scaleFactor;
                massData.I *= scaleFactor;
                blueBlock.body.setMassData(massData);
                blueBlock.body.setAwake(true);

                float tension = 2 * G * blueMassSlider.getValue() * redMassSlider.getValue()
                        / (blueMassSlider.getValue() + redMassSlider.getValue());
                String text = String.format(Locale.US, "%.2f N" , tension);
                tensionValue.setText(text);

                float acc = G * Math.abs(blueMassSlider.getValue() - redMassSlider.getValue())
                        / (blueMassSlider.getValue() + redMassSlider.getValue());
                text = String.format(Locale.US, "%.2f m/s2" , acc);
                accValue.setText(text);
            }
        });


        table = new Table();
        table.setDebug(false);
        table.right().top().padRight(H/10).padTop(H/10);
        table.setFillParent(true);

        table.add(redMassLabel).padRight(10).align(Align.left);
        table.add(redMassSlider).width(W / 4).colspan(2);
        table.add(redMassValue).padLeft(10).align(Align.left);

        table.row().padTop(30);
        table.add(blueMassLabel).padRight(10).align(Align.left);
        table.add(blueMassSlider).width(W / 4).colspan(2);
        table.add(blueMassValue).padLeft(10).align(Align.left);

        table.row().padTop(30);
        table.add(tensionLabel).padRight(10).align(Align.left);
        table.add().colspan(2);
        table.add(tensionValue).padLeft(10).align(Align.left);

        table.row().padTop(30);
        table.add(accLabel).padRight(10).align(Align.left);
        table.add().colspan(2);
        table.add(accValue).padLeft(10).align(Align.left);

        stage.addActor(table);

        stage2 = new Stage(new FitViewport(W/RATE, H/RATE));
        camera = (OrthographicCamera)stage2.getCamera();

        stage2.addActor(pulley);
        stage2.addActor(blueBlock);
        stage2.addActor(redBlock);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    void world() {
        b2world = new World(new Vector2(0, -9.81f), true);

        //Blue
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(blueBlock.getX() + blueBlock.getWidth()/2f,
                blueBlock.getY() + blueBlock.getHeight()/2f));
        bodyDef.fixedRotation = true;
        Body body = b2world.createBody(bodyDef);
        body.setType(BodyDef.BodyType.DynamicBody);
        blueBlock.body = body;

        PolygonShape polygonShape = new PolygonShape();
        float halfWidth = blueBlock.getWidth() / 2f;
        float halfHeight = blueBlock.getHeight() / 2f;
        polygonShape.setAsBox(halfWidth, halfHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);
        polygonShape.dispose();
        MassData m = body.getMassData();
        m.mass = 1f;
        body.setMassData(m);

        //Red
        BodyDef bodyDef3 = new BodyDef();
        bodyDef3.position.set(new Vector2(redBlock.getX() + redBlock.getWidth()/2f,
                redBlock.getY() + redBlock.getHeight()/2f));
        bodyDef3.fixedRotation = true;
        Body body3 = b2world.createBody(bodyDef3);
        body3.setType(BodyDef.BodyType.DynamicBody);
        redBlock.body = body3;

        PolygonShape polygonShape3 = new PolygonShape();
        float halfWidth3 = redBlock.getWidth() / 2f;
        float halfHeight3 = redBlock.getHeight() / 2f;
        polygonShape.setAsBox(halfWidth3, halfHeight3);

        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.shape = polygonShape;
        body3.createFixture(fixtureDef);
        polygonShape3.dispose();
        m = body3.getMassData();
        m.mass = 2f;
        body3.setMassData(m);

        //PIN POINT 1
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.position.set(new Vector2(0,0));
        Body body2 = b2world.createBody(bodyDef2);
        body2.setType(BodyDef.BodyType.StaticBody);

        PolygonShape polygonShape2 = new PolygonShape();
        float halfWidth2 = W/2f;
        float halfHeight2 = 1f;
        polygonShape2.setAsBox(halfWidth2, halfHeight2);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = polygonShape2;
        body2.createFixture(fixtureDef2);
        polygonShape2.dispose();

        //Joint
        PulleyJointDef pulleyJointDef = new PulleyJointDef();
        pulleyJointDef.groundAnchorA.set(pulley.getX(), pulley.getY() + pulley.getHeight()/2f);
        pulleyJointDef.groundAnchorB.set(pulley.getX() + pulley.getWidth(), pulley.getY() + pulley.getHeight()/2f);
        pulleyJointDef.bodyA = body3;
        pulleyJointDef.bodyB = body;
        pulleyJointDef.lengthA = 2f;
        pulleyJointDef.lengthB = 2f;
        pulleyJointDef.localAnchorA.set(0, 0);
        pulleyJointDef.localAnchorB.set(0, 0);
        pulleyJointDef.collideConnected = false;
        pulleyJoint = (PulleyJoint) b2world.createJoint(pulleyJointDef);

//        DistanceJointDef distanceJointDef= new DistanceJointDef();
//        distanceJointDef.bodyA = body;
//        distanceJointDef.bodyB = body2;
//        distanceJointDef.localAnchorA.set(0, 0);
//        distanceJointDef.localAnchorB.set(0, 0);
//        distanceJointDef.length = 2f;
//        distanceJointDef.collideConnected = false;
//        distanceJoint = (DistanceJoint) b2world.createJoint(distanceJointDef);

    }

    @Override
    public void resize(int width, int height) {
        stage2.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
        this.table.setFillParent(true);
        this.table.invalidate();
    }

    static final float G = 9.81f;

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);

//        debugRenderer.render(b2world, camera.combined);
//        stage.getBatch().setProjectionMatrix(camera.combined);

        b2world.step(delta, 8, 3);

        blueBlock.updateImage();
        redBlock.updateImage();

        stage.act(delta);
        stage.draw();

        stage2.act(delta);
        stage2.draw();

        shapeRenderer2.setProjectionMatrix(camera.combined);
        shapeRenderer2.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer2.setColor(Color.GRAY);
        shapeRenderer2.rectLine(new Vector2(pulley.getX(), pulley.getY()+pulley.getHeight()/2f),
                new Vector2(redBlock.getX() + redBlock.getWidth()/2f, redBlock.getY()+redBlock.getHeight()), 0.02f);
        shapeRenderer2.rectLine(new Vector2(pulley.getX()+pulley.getWidth()-0.01f, pulley.getY()+pulley.getHeight()/2f),
                new Vector2(blueBlock.getX() + blueBlock.getWidth()/2f-0.01f, blueBlock.getY()+blueBlock.getHeight()), 0.02f);
        shapeRenderer2.end();

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
        blueBlockTexture.dispose();
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

    MouseJoint mouseJoint;

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int newParam) {
        Vector3 v = stage2.getCamera().unproject(new Vector3(screenX, screenY, 0));
        if (blueBlock.getX() <= v.x && v.x <= blueBlock.getX()+blueBlock.getWidth()) {
            if (blueBlock.getY() <= v.y && v.y <= blueBlock.getY()+blueBlock.getHeight()) {
                MouseJointDef def = new MouseJointDef();
                def.bodyA = redBlock.body;
                def.bodyB = blueBlock.body;
                def.collideConnected = true;
                def.target.set(0, v.y);
                def.maxForce = 1000.0f * blueBlock.body.getMass();
                mouseJoint = (MouseJoint) b2world.createJoint(def);
                blueBlock.body.setAwake(true);
            }
        }
        if (redBlock.getX() <= v.x && v.x <= redBlock.getX()+redBlock.getWidth()) {
            if (redBlock.getY() <= v.y && v.y <= redBlock.getY()+redBlock.getHeight()) {
                MouseJointDef def = new MouseJointDef();
                def.bodyA = blueBlock.body;
                def.bodyB = redBlock.body;
                def.collideConnected = true;
                def.target.set(0, v.y);
                def.maxForce = 1000.0f * redBlock.body.getMass();
                mouseJoint = (MouseJoint) b2world.createJoint(def);
                redBlock.body.setAwake(true);
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        if (mouseJoint != null) {
            Vector3 v = stage2.getCamera().unproject(new Vector3(screenX, screenY, 0));
            mouseJoint.setTarget(new Vector2(0, v.y));
        }
        return false;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        if (mouseJoint != null) {
            b2world.destroyJoint(mouseJoint);
            mouseJoint = null;
            redBlock.body.setLinearVelocity(new Vector2(0,0));
            blueBlock.body.setLinearVelocity(new Vector2(0,0));
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