import java.awt.Color;

import game.*;
import game.audio.Sounds;
import game.drawing.Camera;
import game.drawing.Draw;
import game.physics.Circle;
import game.physics.Physics;
import game.physics.Point;
import game.physics.Rect;

public class UsageExample extends GameJava {

    Circle ball = new Circle(400, 300, 10);
    Point ballVelocity = new Point(Utils.rand(-2, 2),Utils.rand(-2, 2));

    public UsageExample() {
        super(800, 600, 144, 144);

        frameTitle = "examples of stuff that can be done";

        Draw.allowFullScreen = true;
        Draw.alphaBetweenFrames = 0.2f;
        Draw.antialiasing = true;
        Draw.frame.setResizable(false);

        Sounds.ajustGain("putAudioHere", 0.95f);

        LoopManager.startLoops(this);
	}
	
	public static void main(String[] args) {
        new UsageExample();   
    }

	@Override
	public void update() {
        ball.x += ballVelocity.x;
        if( !Physics.circlerect(ball, new Rect(gw/2,gh/2,gw/4,gh/4))) {
            ballVelocity.x *= -1;
        }
        ball.y += ballVelocity.y;
        if( !Physics.circlerect(ball, new Rect(gw/2,gh/2,gw/4,gh/4))) {
            ballVelocity.y *= -1;
        }

        if(Input.keyDown(KeyCodes.A)) {
            Camera.centerOn(400,300);
            Camera.angle = 0.0f;
            Camera.zoom = 1.0f;
        } else {
            Camera.x = (int) (Math.sin(updateCount/100.0) * 100.0);
            Camera.y = (int) (Math.cos(updateCount/100.0) * 100.0);
            Camera.zoom = (float) (2.0 + Math.sin(frameCount/150.0) * 2.0);
            Camera.angle = (float) Math.tan(frameCount/1000.0);
        }

        if(Input.mouseClick(0)) {
            Sounds.play("putAudioHere");
        }

        Utils.putInDebugMenu("label", "s is pressed: " + (Input.keyDown(KeyCodes.S) ? "true" : "false"));
    }

    @Override
	public void draw() {
        Draw.image("putImagesHere", 300, 300, updateCount/100.0, 1.0 + Math.tan(updateCount/100.0)*50.0);
        
        Draw.setColor(Color.CYAN);
        Draw.setLineWidth(5);
        Draw.rectOutline(gw/2, gh/2, gw/4, gh/4);

        Draw.setColor(new Color(124,215,70));
        Draw.circle(ball);

        Draw.setFontSize(2);
        Draw.text("Hold A to stop camera!", 500,400);
    }
    
    @Override
    public void absoluteDraw() {
        Draw.setColor(Color.GRAY);
        Draw.setFontSize(3);
        Draw.text("frame: " + frameCount, 10, 50);

        Draw.setFontSize(2);
        Draw.text("f3 to show debug", 600, 500);
    }
}