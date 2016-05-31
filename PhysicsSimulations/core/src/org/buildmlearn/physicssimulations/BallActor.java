package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BallActor extends Actor {
    float unscaledWidth;
    float unscaledHeight;
    Image ballImage;
    Body body;

    BallActor(TextureRegion textureRegion) {
        this.ballImage = new Image(textureRegion);
        this.unscaledWidth = ballImage.getWidth();
        this.unscaledHeight = ballImage.getHeight();
        setWidth(unscaledWidth);
        setHeight(unscaledHeight);
        setRotation(ballImage.getRotation());
    }

    void updateImage() {
        setPosition(body.getPosition().x, body.getPosition().y);
        setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        ballImage.setPosition(body.getPosition().x, body.getPosition().y);
        ballImage.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    }

    void updateBody() {
        body.setTransform(ballImage.getX(), ballImage.getY(),
                ballImage.getRotation() * MathUtils.degreesToRadians);
    }

    @Override
    public void scaleBy(float scale) {
        float w = ballImage.getWidth() / 2f;
        float h = ballImage.getHeight() / 2f;
        setSize(unscaledWidth * scale, unscaledHeight * scale);
        float ww = ballImage.getWidth() / 2f;
        float hh = ballImage.getHeight() / 2f;
        moveBy(w - ww, h - hh);
        updateBody();
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        ballImage.setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ballImage.draw(batch, parentAlpha);
    }

    @Override
    public void moveBy(float x, float y) {
        super.moveBy(x, y);
        ballImage.moveBy(x, y);
    }
}
