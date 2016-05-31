package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class Pendulum extends SimulationType {

    //NOU
    /** the camera **/
    private com.badlogic.gdx.graphics.OrthographicCamera camera;
    /** the immediate mode renderer to output our debug drawings **/
    private ShapeRenderer renderer;
    /** box2d debug renderer **/
    private Box2DDebugRenderer debugRenderer;


    private Skin skin;
    private TextureAtlas atlas;
    private Stage stage;
    private Table table;
    private BallActor ballActor;
    World b2world;

    Label lengthLabel;

    private Texture ballTexture;

    Button button;

    @Override
    public void create() {

        //NOU
        camera = new OrthographicCamera(48, 32);
        camera.position.set(0, 16, 0);
        // next we setup the immediate mode renderer
        renderer = new ShapeRenderer();
        // next we create the box2d debug renderer
        debugRenderer = new Box2DDebugRenderer();







        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        atlas = new TextureAtlas(Gdx.files.internal("data/ui-blue.atlas"));
        stage = new Stage(new ScreenViewport());

        skin.addRegions(atlas);

        float W = Gdx.graphics.getWidth();
        float H = Gdx.graphics.getHeight();



        button = new Button(skin);
        


        ballTexture = new Texture(Gdx.files.internal("ball.png"));
        final TextureRegion ballRegion = new TextureRegion(ballTexture);
        ballActor = new BallActor(ballRegion);
        ballActor.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                ballActor.moveBy(x - ballActor.getWidth() / 2, y - ballActor.getHeight() / 2);
            }
        });
        this.world();

        lengthLabel = new Label("Length: 0.5 m", skin);
        lengthLabel.setColor(Color.WHITE);

        final Label massLabel = new Label("Mass: 1.0 kg", skin);

        SliderStyle sliderStyle = new SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03");
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        final Slider lengthSlider = new Slider(0, 15, 1, false, sliderStyle);
        lengthSlider.setAnimateDuration(0);
        lengthSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("UITest", "slider: " + lengthSlider.getValue());
                lengthLabel.setText("Length: "+ (0.5f + lengthSlider.getValue() / 10f) + " m");
            }
        });

        final Slider massSlider = new Slider(2, 18, 1, false, sliderStyle);
        massSlider.setAnimateDuration(0);
        massSlider.setValue(18);
        massSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //Gdx.app.log("UITest", "slider: " + massSlider.getValue());
                massLabel.setText("Mass: " + massSlider.getValue() / 2f + " kg");
                ballActor.scaleBy(massSlider.getValue() / 18f);
            }
        });
        ballActor.debug();

        table = new Table();
        //table.setDebug(true);
        table.right().top().padRight(100).padTop(100);
        table.setFillParent(true);
        //table.row().expandX();
        table.add(lengthLabel).padRight(10);
        table.add(lengthSlider).width(W / 4).fillX();

        table.row().padTop(30);
        table.add(massLabel).padRight(10);
        table.add(massSlider).width(W / 4).fillX();

        stage.addActor(table);
        stage.addActor(ballActor);
        Gdx.input.setInputProcessor(stage);

    }

    void world() {
        b2world = new World(new Vector2(0, -9.81f), true);

        //BALL
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(ballActor.getX(), ballActor.getY()));
        bodyDef.position.add(ballActor.getX(), ballActor.getY());
        Body body = b2world.createBody(bodyDef);
        body.setType(BodyDef.BodyType.DynamicBody);
        ballActor.body = body;

        PolygonShape polygonShape = new PolygonShape();
        float halfWidth = ballActor.getWidth() / 2.0f;
        float halfHeight = ballActor.getHeight() / 2.0f;
        polygonShape.setAsBox(halfWidth, halfHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 50;
        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.5f;
        body.createFixture(fixtureDef);
        polygonShape.dispose();

        //PIN POINT
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.position.set(new Vector2(500, 500));
        bodyDef2.position.add(500, 500);
        Body body2 = b2world.createBody(bodyDef2);
        body2.setType(BodyDef.BodyType.StaticBody);

        RopeJointDef ropeJointDef = new RopeJointDef();
        ropeJointDef.bodyA = body2;
        ropeJointDef.bodyB = body;
        ropeJointDef.maxLength = 0.2f;
        ropeJointDef.localAnchorA.set(0,0);
        ropeJointDef.localAnchorB.set(0,0);

        b2world.createJoint(ropeJointDef);

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

        //NOU
        camera.update();


        b2world.step(delta, 8, 3);

        DragListener dragListener = (DragListener) ballActor.getListeners().get(0);
        if (!dragListener.isDragging())
            ballActor.updateImage();
        else
            ballActor.updateBody();

        stage.act(delta);
        stage.draw();

        //NOU
        debugRenderer.render(b2world, camera.combined);
        // finally we render all contact points
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Point);
        renderer.setColor(0, 1, 0, 1);
        for (int i = 0; i < b2world.getContactCount(); i++) {
            Contact contact = b2world.getContactList().get(i);
            // we only render the contact if it actually touches
            if (contact.isTouching()) {
                // get the world manifold from which we get the
                // contact points. A manifold can have 0, 1 or 2
                // contact points.
                WorldManifold manifold = contact.getWorldManifold();
                int numContactPoints = manifold.getNumberOfContactPoints();
                for (int j = 0; j < numContactPoints; j++) {
                    Vector2 point = manifold.getPoints()[j];
                    renderer.point(point.x, point.y, 0);
                }
            }
        }
        renderer.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
    }
}
