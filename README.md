# openmoji-java-svg

This repo provides some helper classes to rasterize SVG icons of the wonderful [OpenMoji](https://openmoji.org/) library.



# Usage

```java
// basic usage: just pass the emoji string and width/height

BufferedImage img = OpenMoji.image("🍆", 32, 32);

// you can also use a Font to size the image accordingly:

BufferedImage img = OpenMoji.image("🥑", myLabel.getFont(), 1f);


// to use a cache and avoid regenerating images multiple times, use this:

BufferedImage img = OpenMoji.cached().image("🌽", 32, 32);
BufferedImage img = OpenMoji.cached().image("🥬", myLabel.getFont(), 1f);



```

# Credits

This repo includes all icons from OpenMoji.
