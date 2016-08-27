package org.buildmlearn.physicssimulations;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * The <code>LineActor</code> is an {@link Actor} that represent a line
 * @author  Costin Giorgian
 */
public class LineActor extends Actor {
    ShapeRenderer shapeRenderer;
    LineActor(ShapeRenderer shapeRenderer) {
        super();
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(getColor());
        shapeRenderer.rect(getX(), getY(), getWidth(), getHeight());
        shapeRenderer.end();
        batch.begin();
    }
}
