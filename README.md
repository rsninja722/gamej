# gamej
game.js but without the s

## TODO
    make a better readme
    line collition
    recurseive folder opening for image loading
    text wrapping
    scrolling
    add sounds
    set name and icon
    make fullscreen not lag

 ## known issues
 * draw limiting probably doesn't work
 * rapidly resizeing window or spamming f11 makes the screen go blank

# usage

## debugging
there is a built in debug menu, press f3 ingame to toggle. fps is automatically added to it.

call `Utils.putInDebugMenu()` somewhere in update to add something

## physics 
create a new Circle/Rect/Point (all are centered on x,y instead of x,y being top left)

use the collision methods in Physics to determine collisions

## Utils
it has cool stuff, javadoc comments can help you

## Input
clicked = true for only the first update something is pressed

down = true for the whole time something is held down

example useage: 
```java
if(keyClick(KeyCodes.B)) {} // b key clicked
if(mouseDown(0)) {} // left mouse held
```
default input: 
 * f3 - toggle debug menu
 * f11 - toggle fullscreen

## Drawing
thing that can be drawn currently:
 * rectangles
 * circles
 * lines
 * text
 * images

set color with `Draw.setColor()`

set line width with `Draw.setLineWidth()`

set text size with `Draw.setFontSize()`

images require a sprite to draw. Any png files in `assets/images/` will be loaded as a sprite. access them with `Sprites.get(name of png without the .png)`

## Camera
everything in the main draw method is offset by the camera

x, y, angle, and zoom can be set directly or with `Camera.centerOn()` or `Camera.move()`