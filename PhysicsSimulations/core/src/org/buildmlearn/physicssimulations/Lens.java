package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Locale;

public class Lens extends SimulationType {
    private com.badlogic.gdx.graphics.OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    private Skin skin;
    private TextureAtlas atlas;

    private Stage stage;

    private ShapeRenderer shapeRenderer;

    private float W;
    private float H;

    private Label focusValue;
    private Label heightValue;

    private Slider focusSlider;
    private Slider heightSlider;

    private Table table;

    Image arrow;
    Image secArrow;

    Image lens;

    ImageButton divergingButton;
    ImageButton convergingButton;

    Vector2 leftFocal;
    Vector2 rightFocal;

    Label distanceValue;
    Label imageDistanceValue;
    Label imageHeightValue;

    boolean isConv = true;

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

        Texture conTexture = new Texture(Gdx.files.internal("lens/covlens.png"), true);
        conTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion conRegion = new TextureRegion(conTexture);
        final Image conlens = new Image(conRegion);
        conlens.setPosition(W/2f, H/2f - conlens.getHeight()/2f);
        lens = conlens;

        Texture divTexture = new Texture(Gdx.files.internal("lens/divlens.png"), true);
        divTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion divRegion = new TextureRegion(divTexture);
        final Image divlens = new Image(divRegion);
        divlens.setPosition(W/2f, H/2f - divlens.getHeight()/2f);

        Texture arrowTexture = new Texture(Gdx.files.internal("lens/arrow.png"), true);
        arrowTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        final TextureRegion arrowRegion = new TextureRegion(arrowTexture);
        arrow = new Image(arrowRegion);
        arrow.setPosition(W/6f, H/2f);
        arrow.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                arrow.moveBy(x - arrow.getWidth() / 2, 0);
                distanceValue.setText(String.format(Locale.US, "%.0f cm" , (lens.getX() + lens.getWidth()/2f - arrow.getX())/10f));
            }
        });

        secArrow = new Image(arrowRegion);
        secArrow.setPosition(W*5/6f, H/2f);

        Texture convergingTexture = new Texture(Gdx.files.internal("lens/converging.png"), true);
        final Texture convergingPressed= new Texture(Gdx.files.internal("lens/converging_press.png"), true);
        convergingButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(convergingTexture)),
                new TextureRegionDrawable(new TextureRegion(convergingTexture)), new TextureRegionDrawable(new TextureRegion(convergingPressed)));
        convergingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (convergingButton.isChecked()) {
                    divergingButton.setChecked(false);
                    lens.remove();
                    lens = conlens;
                    stage.addActor(lens);
                    isConv = true;
                }
                else {
                    divergingButton.setChecked(true);
                    lens.remove();
                    lens = divlens;
                    stage.addActor(lens);
                    isConv = false;
                }
            }
        });

        Texture divergingTexture = new Texture(Gdx.files.internal("lens/diverging.png"), true);
        final Texture divergingPressed= new Texture(Gdx.files.internal("lens/diverging_press.png"), true);
        divergingButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(divergingTexture)),
                new TextureRegionDrawable(new TextureRegion(divergingTexture)), new TextureRegionDrawable(new TextureRegion(divergingPressed)));
        divergingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (divergingButton.isChecked()) {
                    convergingButton.setChecked(false);
                    lens.remove();
                    lens = divlens;
                    stage.addActor(lens);
                    isConv = false;
                }
                else {
                    convergingButton.setChecked(true);
                    lens.remove();
                    lens = conlens;
                    stage.addActor(lens);
                    isConv = true;
                }
            }
        });
        convergingButton.setChecked(true);

        Label focusLabel = new Label("Focus:", labelStyle);
        Label heightLabel = new Label("Height:", labelStyle);
        Label distanceLabel = new Label("Distance:", labelStyle);
        Label imageDistanceLabel = new Label("Image distance:", labelStyle);
        Label imageHeightLabel   = new Label("Image height:", labelStyle);

        focusValue = new Label("7.5 cm", skin);
        heightValue = new Label("12 cm", skin);
        distanceValue = new Label(String.format(Locale.US, "%.0f cm" , (conlens.getX() + conlens.getWidth()/2f - arrow.getX())/10f), skin);
        imageDistanceValue = new Label("", skin);
        imageHeightValue = new Label("", skin);

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = skin.getDrawable("knob_03");
        sliderStyle.background = skin.getDrawable("slider_back_hor");

        heightSlider = new Slider(5, 15, 1f, false, sliderStyle);
        heightSlider.setValue(12f);
        heightSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.0f cm" , heightSlider.getValue());
                heightValue.setText(text);
                arrow.setHeight(heightSlider.getValue()*10);
            }
        });

        leftFocal = new Vector2(conlens.getX()+conlens.getWidth()/2f - 75f, H/2f);
        rightFocal = new Vector2(conlens.getX()+conlens.getWidth()/2f + 75f, H/2f);
        focusSlider = new Slider(7.5f, 15f, 0.1f, false, sliderStyle);
        focusSlider.setValue(7.5f);
        focusSlider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String text = String.format(Locale.US, "%.1f cm" , focusSlider.getValue());
                focusValue.setText(text);
                leftFocal = new Vector2(lens.getX()+lens.getWidth()/2f - focusSlider.getValue()*10, H/2f);
                rightFocal = new Vector2(lens.getX()+lens.getWidth()/2f + focusSlider.getValue()*10, H/2f);

            }
        });

        table = new Table();
        table.setDebug(false);
        table.left().bottom().padLeft(50).padBottom(50);
        table.setFillParent(true);

        table.add(focusLabel).padRight(20).align(Align.left);
        table.add(focusSlider).width(W/5);
        table.add(focusValue).padLeft(20).padRight(20).center();

        table.row().padTop(5);
        table.add(heightLabel).padRight(20).align(Align.left);
        table.add(heightSlider).width(W/5);
        table.add(heightValue).padLeft(20).padRight(20).center();

        table.add(imageHeightLabel).padLeft(20).padRight(20).align(Align.left);
        table.add(imageHeightValue).padLeft(20).padRight(20).center();

        table.row().padTop(5);
        table.add(distanceLabel).padRight(20).align(Align.left);
        table.add();
        table.add(distanceValue).padLeft(20).padRight(20).center();

        table.add(imageDistanceLabel).padLeft(20).padRight(20).align(Align.left);
        table.add(imageDistanceValue).padLeft(20).padRight(20).center();

        Table switchTable = new Table();
        switchTable.setDebug(true);
        switchTable.top().right().padRight(30).padTop(30);
        switchTable.setFillParent(true);
        switchTable.add(convergingButton);
        switchTable.add(divergingButton);

        stage.addActor(table);
        stage.addActor(switchTable);
        stage.addActor(arrow);
        stage.addActor(secArrow);
        stage.addActor(lens);

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
        float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);

//        debugRenderer.render(b2world, camera.combined);
//        stage.getBatch().setProjectionMatrix(camera.combined);

        stage.act(delta);
        stage.draw();

        float ax = arrow.getX()+arrow.getWidth()/2f;
        float ay = arrow.getY()+arrow.getHeight();

        float cx = lens.getX()+lens.getWidth()/2f;
        float cy = H/2f;

        shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rectLine(0f, H/2f, W, H/2f, 2f);
        shapeRenderer.setColor(Color.RED);

        shapeRenderer.circle(leftFocal.x, leftFocal.y, 10);
        shapeRenderer.circle(rightFocal.x, rightFocal.y, 10);

        if (isConv) {
            //up and left
            shapeRenderer.rectLine(ax, ay, cx, ay, 2f);

            //up and right
            float x = W;
            float y = (rightFocal.y - ay)*(x - cx)/(rightFocal.x - cx) + ay;
            shapeRenderer.rectLine(cx, ay, x, y, 2f);

            //center
            x = W;
            y = (cy - ay)*(x - ax)/(cx - ax) + ay;
            shapeRenderer.rectLine(ax, ay, x, y, 2f);

            //down and left
            x = cx;
            y = (leftFocal.y - ay)*(x - ax)/(leftFocal.x - ax) + ay;
            shapeRenderer.rectLine(ax, ay, x, y, 2f);

            //down and right
            x = W;
            shapeRenderer.rectLine(cx, y, x, y, 2f);

            //intersection
            y = (rightFocal.y - ay)*(x - cx)/(rightFocal.x - cx) + ay;
            float yy = (cy - ay)*(x - ax)/(cx - ax) + ay;
            Vector2 in = new Vector2();
            Intersector.intersectLines(cx, ay, x, y, ax, ay, x, yy, in);
            secArrow.setRotation(180);
            secArrow.setPosition(in.x + secArrow.getWidth()/2f, H/2f);
            float rate = secArrow.getHeight() / arrow.getHeight();
            secArrow.setHeight(H/2f - in.y);
            secArrow.setWidth(arrow.getWidth()*rate);

            imageDistanceValue.setText(String.format(Locale.US, "%.0f cm" , (secArrow.getX() - lens.getX() - lens.getWidth()/2f)/10f));
            imageHeightValue.setText(String.format(Locale.US, "%.0f cm" , secArrow.getHeight()/10f));
        }
        else {
            //up and left
            shapeRenderer.rectLine(ax, ay, cx, ay, 2f);

            //up and reflect
            float x = W;
            float y = (leftFocal.y - ay)*(x - cx)/(leftFocal.x - cx) + ay;
            shapeRenderer.rectLine(cx, ay, x, y, 2f);
            shapeRenderer.rectLine(cx, ay, leftFocal.x, leftFocal.y, 2f);

            //center
            float yy = (cy - ay)*(x - ax)/(cx - ax) + ay;
            shapeRenderer.rectLine(ax, ay, x, yy, 2f);
            Vector2 in = new Vector2();
            Intersector.intersectLines(cx, ay, x, y, ax, ay, x, yy, in);
            secArrow.setRotation(0);
            float rate = secArrow.getHeight() / arrow.getHeight();
            secArrow.setHeight(in.y - H/2f);
            secArrow.setWidth(arrow.getWidth()*rate);
            secArrow.setPosition(in.x - secArrow.getWidth()/2f, H/2f);
        }

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
    }

}
