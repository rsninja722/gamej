package game;

import java.awt.Color;

import game.drawing.Draw;

// manages timing and calls update, draw, and absouluteDraw
public class LoopManager {

	private static GameJava mainGameClass;

	// variables for how long to wait between each frame
	public static final double nanosecondsPerSecond = 1000000000;
	public static double nanosPerFrame;
	private static double lastDrawTime = System.nanoTime();

	public static double nanosPerUpdate;
	private static double lastUpdateTime = System.nanoTime();

	// fps counting
	private static double fpsCounter = 0;
	private static double timeSinceLastSecond = System.nanoTime();

	public static double averageFps = 0;

	public static void startLoops(GameJava mainGameObject) {
		System.out.println("[LoopManager] starting loops with: " + mainGameObject.toString());
		mainGameClass = mainGameObject;

		nanosPerFrame = nanosecondsPerSecond / GameJava.framePerSecond;
		nanosPerUpdate = nanosecondsPerSecond / GameJava.updatesPerSecond;

		while (GameJava.running) {
			// updating
			double currentTime = System.nanoTime();
			if (currentTime - lastUpdateTime >= nanosPerUpdate) {
				// full screen toggle
				if (Input.keyClick(KeyCodes.F11)) {
					Draw.toggleFullSreen();
				}
				// debug info toggle
				if (Input.keyClick(KeyCodes.F3)) {
					Utils.debugMode = !Utils.debugMode;
				}
				// calculate mouse position in world
				Input.setMousePosition((int) Input.rawMousePos.x, (int) Input.rawMousePos.y);
				// reset debug string
				Utils.debugString.setLength(0);
				// call update and reset time
				mainGameClass.update();
				lastUpdateTime = currentTime;
				// increment count
				GameJava.updateCount++;
				// set clicked keys to held
				Input.handleHolding();
				// add fps to debug
				Utils.putInDebugMenu("FPS", LoopManager.averageFps);
			}

			// drawing
			currentTime = System.nanoTime();
			if (currentTime - lastDrawTime >= nanosPerFrame) {
				synchronized (Draw.panel) {
					// set up buffers
					Draw.preRender();
					// draw using camera
					mainGameClass.draw();
					Draw.renderCameraMovement();

					// set offset to 0 and reselect buffer
					Draw.preAbsoluteRender();
					// draw without camera
					mainGameClass.absoluteDraw();
					// draw debug text
					if (Utils.debugMode) {
						String[] debugMessages = new String(Utils.debugString).split("\n");
						Draw.setFontSize(1);
						for (int i = 0; i < debugMessages.length; i++) {
							Draw.setColor(new Color(0.2f, 0.2f, 0.2f, 0.7f));
							int textW = Draw.getWidthOfText(debugMessages[i]);
							Draw.rect(textW / 2 + 2, 5 + i * 9, textW + 4, 9);
							Draw.setColor(Color.WHITE);
							Draw.text(debugMessages[i], 2, 9 + i * 9);
						}
					}
					// draw buffer to panel
					Draw.renderToScreen();
				}
				lastDrawTime = currentTime;
				// increment count
				GameJava.frameCount++;
				// increment fps count
				fpsCounter++;
			}
			// fps counting
			if (currentTime - timeSinceLastSecond >= nanosecondsPerSecond) {
				averageFps = fpsCounter;
				fpsCounter = 0;
				timeSinceLastSecond = currentTime;
			}
		}
	}
}