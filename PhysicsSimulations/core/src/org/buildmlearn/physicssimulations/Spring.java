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
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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


public class Spring extends SimulationType implements InputProcessor {

    private com.badlogic.gdx.graphics.OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    private Skin skin;
    private TextureAtlas atlas;

    private Stage stage;
    Stage stage2;
    private Table table;

    private BallActor block;
    private Image spring;
    World b2world;

    Label stiffnessValue;
    Label massValue;

    Slider stiffnessSlider;
    Slider massSlider;
    private Texture blockTexture;

    DistanceJoint distanceJoint;

    LineActor line1Actor, line2Actor, line3Actor, line4Actor;

    ShapeRenderer shapeRenderer;
    ShapeRenderer shapeRenderer2;

    Label keValue, peeValue, pegravValue, totalValue;

    Body body2;

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

        Texture pulleyTexture = new Texture(Gdx.files.internal("spring.png"), true);
        pulleyTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion pulleyRegion = new TextureRegion(pulleyTexture);
        spring = new Image(pulleyRegion);
        spring.setSize(spring.getWidth()/RATE, spring.getHeight()/RATE);
        spring.setPosition(W/4/RATE-spring.getWidth()/2f, H/RATE-spring.getHeight());

        blockTexture = new Texture(Gdx.files.internal("blue_block.png"), true);
        blockTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion blockRegion = new TextureRegion(blockTexture);
        block = new BallActor(blockRegion);
        block.scaleBy(3/2f);
        block.setPosition(W/4/RATE - block.getWidth()/2f, H/RATE/2f);

        this.world();

        Label StiffnessLabel = new Label("Stiffness:", labelStyle);
        final Label massLabel = new Label("Mass:", labelStyle);

        stiffnessValue = new Label("0.5 N/m", skin);
        massValue = new Label("1.0 kg", skin);

        keValue = new Label("0.3 J", labelStyle);
        peeValue = new Label("1.0 J", labelStyle);
        pegravValue = new Label("0.5 J", labelStyle);
        totalValue = new Label("1.8 J", labelStyle);

        SliderStyle sliderStyle = new SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03")  ;
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        stiffnessSlider = new Slider(0.4f, 1.0f, 0.1f, false, sliderStyle);
        stiffnessSlider.setAnimateDuration(0);
        stiffnessSlider.setValue(0.5f);
        stiffnessSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                stiffnessValue.setText(String.format(Locale.US, "%.1f N/m" , stiffnessSlider.getValue()));
                distanceJoint.setFrequency(stiffnessSlider.getValue());
            }
        });

        massSlider = new Slider(1, 4, 1, false, sliderStyle);
        massSlider.setAnimateDuration(0);
        massSlider.setValue(2);
        massSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                massValue.setText(massSlider.getValue() + " kg");
                block.scaleBy(massSlider.getValue() / 2f);

                MassData massData = block.body.getMassData();
                float scaleFactor = massSlider.getValue() / massData.mass;
                massData.mass *= scaleFactor;
                massData.I *= scaleFactor;
                block.body.setMassData(massData);
                block.body.setAwake(true);
            }
        });

        line1Actor = new LineActor(shapeRenderer);
        line1Actor.setColor(Color.RED);
        line2Actor = new LineActor(shapeRenderer);
        line2Actor.setColor(Color.BLUE);
        line3Actor = new LineActor(shapeRenderer);
        line3Actor.setColor(Color.GREEN);
        line4Actor = new LineActor(shapeRenderer);
        line4Actor.setColor(Color.PURPLE);
        LineActor line5Actor = new LineActor(shapeRenderer);
        line5Actor.setColor(Color.BLACK);

        table = new Table();
        table.setDebug(false);
        table.right().top().padRight(H/10).padTop(H/10);
        table.setFillParent(true);

        table.add(StiffnessLabel).padRight(10).align(Align.left);
        table.add(stiffnessSlider).width(W / 4).colspan(2);
        table.add(stiffnessValue).padLeft(10).align(Align.left);

        table.row().padTop(30);
        table.add(massLabel).padRight(10).align(Align.left);
        table.add(massSlider).width(W / 4).colspan(2);
        table.add(massValue).padLeft(10).align(Align.left);

        table.row().padTop(30);
        table.add(line1Actor).width(20).align(Align.center|Align.bottom).height(H/4f);
        table.add(line2Actor).width(20).align(Align.center|Align.bottom).maxHeight(H/4f);
        table.add(line3Actor).width(20).align(Align.center|Align.bottom).maxHeight(H/4f);
        table.add(line4Actor).width(20).align(Align.center|Align.bottom).maxHeight(H/4f);

        table.row();
        table.add(line5Actor).height(10).colspan(4).fillX();

        table.row().padTop(30);
        table.add(new Label("KE", skin)).align(Align.center);
        table.add(new Label("PEelas", skin)).align(Align.center);
        table.add(new Label("PEgrav", skin)).align(Align.center);
        table.add(new Label("Total", skin)).align(Align.center);


//        stage.setDebugAll(true);
        stage.addActor(table);

        stage2 = new Stage(new FitViewport(W/RATE, H/RATE));
        camera = (OrthographicCamera)stage2.getCamera();

        stage2.addActor(spring);
        stage2.addActor(block);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    void world() {
        b2world = new World(new Vector2(0, -G), true);

        //Blue
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(block.getX() + block.getWidth()/2f,
                block.getY() + block.getHeight()/2f));
        bodyDef.fixedRotation = true;
        Body body = b2world.createBody(bodyDef);
        body.setType(BodyDef.BodyType.DynamicBody);
        block.body = body;

        PolygonShape polygonShape = new PolygonShape();
        float halfWidth = block.getWidth() / 2f;
        float halfHeight = block.getHeight() / 2f;
        polygonShape.setAsBox(halfWidth, halfHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);
        polygonShape.dispose();
        MassData m = body.getMassData();
        m.mass = 1f;
        body.setMassData(m);

        //PIN POINT
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.position.set(new Vector2(W/4/RATE, H/RATE-0.2f));
        body2 = b2world.createBody(bodyDef2);
        body2.setType(BodyDef.BodyType.StaticBody);

        PolygonShape polygonShape2 = new PolygonShape();
        float halfWidth2 = 0.1f;
        float halfHeight2 = 0.1f;
        polygonShape2.setAsBox(halfWidth2, halfHeight2);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = polygonShape2;
        body2.createFixture(fixtureDef2);
        polygonShape2.dispose();

        //JOINT
        DistanceJointDef distanceJointDef= new DistanceJointDef();
        distanceJointDef.bodyA = body2;
        distanceJointDef.bodyB = body;
        distanceJointDef.localAnchorA.set(0, 0);
        distanceJointDef.localAnchorB.set(0, 0);
        distanceJointDef.length = 2f;
        distanceJointDef.frequencyHz = 0.5f;
        distanceJointDef.dampingRatio = 0f;
        distanceJointDef.collideConnected = true;
        distanceJoint = (DistanceJoint) b2world.createJoint(distanceJointDef);
    }

    @Override
    public void resize(int width, int height) {
        stage2.getViewport().update(width, height, true);
        stage.getViewport().update(width, height, true);
        this.table.setFillParent(true);
        this.table.invalidate();
    }

    static final float G = 9.81f;

    double KE, PEe, PEg, TME;

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);

//        debugRenderer.render(b2world, camera.combined);
//        stage.getBatch().setProjectionMatrix(camera.combined);

        block.updateImage();
        spring.setHeight(H/RATE-(block.getY()+block.getHeight()));
        spring.setY(block.getY()+block.getHeight());

        float x = body2.getPosition().y - block.body.getPosition().y - 2f;
        float mass = massSlider.getValue();
        float k = stiffnessSlider.getValue();

        KE = Math.abs(block.body.getLinearVelocity().y) * mass * 5;
        PEe = 0.5 * k * x*x * 100;
        PEg = G * mass * (block.body.getPosition().y < 0 ? 0 : block.body.getPosition().y);
        TME = KE+PEe+PEg;
        float maxTME = 9.0f * G * 2.0f;
        float r =  H / 4f / maxTME;
        line1Actor.setHeight((float)KE*r);
        line2Actor.setHeight((float)PEe*r);
        line3Actor.setHeight((float)PEg*r);
        line4Actor.setHeight((float)(TME)*r);

        b2world.step(delta, 8, 3);

        stage.act(delta);
        stage.draw();

        stage2.act(delta);
        stage2.draw();


        shapeRenderer2.setProjectionMatrix(camera.combined);
        shapeRenderer2.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer2.setColor(Color.BLUE);
//        shapeRenderer2.circle(body2.getPosition().x, body2.getPosition().y, 0.05f, 360);
//        shapeRenderer2.rectLine(new Vector2(pulley.getX()+pulley.getWidth()-0.01f, pulley.getY()+pulley.getHeight()/2f),
//                new Vector2(block.getX() + block.getWidth()/2f-0.01f, block.getY()+ block.getHeight()), 0.02f);
        shapeRenderer2.end();

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
        blockTexture.dispose();
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
        if (block.getX() <= v.x && v.x <= block.getX()+ block.getWidth()) {
            if (block.getY() <= v.y && v.y <= block.getY()+ block.getHeight()) {
                MouseJointDef def = new MouseJointDef();
                def.bodyA = body2;
                def.bodyB = block.body;
                def.collideConnected = true;
                def.target.set(0, v.y);
                def.maxForce = 100.0f * block.body.getMass();
                mouseJoint = (MouseJoint) b2world.createJoint(def);
                block.body.setAwake(true);
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
            //block.body.setLinearVelocity(new Vector2(0,0));
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