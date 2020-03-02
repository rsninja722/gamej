package game.physics;

/** circle object used for drawing and physics */
public class Circle {
	public int x;
	public int y;
	public int r;
    
    /**
     * Creates a new circle object centered on x,y that can be used in physics and drawing
     * @param x center x
     * @param y center y
     * @param r radius
     */
	public Circle(int x,int y,int r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
}
