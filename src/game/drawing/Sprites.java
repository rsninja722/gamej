package game.drawing;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;

import game.GameJava;

/** loads and stores all png files drom the assets/images folder in a hashmap */
public class Sprites {

    // hash map of all the sprites
    private static HashMap<String, Sprite> spriteList = new HashMap<String, Sprite>();

    // absolute path of the images folder
    private static String imagesDirectory = GameJava.baseDirectory + "\\images\\";

    // finds images, loads them, and puts them in the hashmap
    public static void loadSprites() {
        System.out.println("[Sprites] loading sprites from: " + imagesDirectory);
        // create file to get all other files in directory
        File dir = new File(imagesDirectory);

        // get array of other files
        String[] children = dir.list();

        System.out.println(Arrays.toString(children));

        // go through all files
        for (int i = 0; i < children.length; i++) {
            String name = children[i];

            // if it is a portable network graphic, create a sprite with the image, and add it to the hashmap
            if (name.endsWith(".png")) {
                String spriteName = name.substring(0, name.indexOf("."));
                spriteList.put(spriteName, new Sprite(imagesDirectory + name));
            }
        }
    }

    /**
     * get a sprite from the hashmap of sprites
     * @param spriteName name of the image without .png
     * @return {@link game.drawing.Sprite#Sprite(String) Sprite}
     */
    public static Sprite get(String spriteName) {
        Sprite s = spriteList.get(spriteName);
        if (s == null) {
            System.out.println("error: " + spriteName + " not found");
        }
        return spriteList.get(spriteName);
    }
}
