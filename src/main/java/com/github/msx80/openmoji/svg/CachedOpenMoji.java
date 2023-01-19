package com.github.msx80.openmoji.svg;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class CachedOpenMoji {

	private Map<String, BufferedImage> cache = new HashMap<>();
	
	public BufferedImage image(String emoji, int width, int height)
	{
		String key = width+"x"+height+"-"+emoji; 
				
		BufferedImage res = cache.computeIfAbsent(key, k -> OpenMoji.image(emoji, width, height));
		
		return res;
	}
	
	public BufferedImage image(String emoji, Font font, float scale)
	{
		int size = OpenMoji.calculateHeight(font, scale);
		return image(emoji, size, size);
	}
	
}
