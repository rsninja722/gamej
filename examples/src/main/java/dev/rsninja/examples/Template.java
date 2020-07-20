package dev.rsninja.examples;

import dev.rsninja.gamej.GameJava;
import dev.rsninja.gamej.LoopManager;

public class Template extends GameJava {

    public Template() {
        super(640, 480, 60, 60);

        LoopManager.startLoops(this);
	}
	
	public static void main(String[] args) {
        new Template();   
    }
	
    @Override
    public void update() {
        // called at the set frame rate
    }	
    
	@Override
	public void draw() {
        // called at the set update rate
	}
    
    @Override
    public void absoluteDraw() {
        // called immediately after draw, all drawing is the same but without the camera affecting anything
    }
}