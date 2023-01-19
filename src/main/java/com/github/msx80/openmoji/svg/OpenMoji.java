package com.github.msx80.openmoji.svg;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenMoji {
	

	private static Map<String, Integer> fontHeights = new HashMap<>();
	private static CachedOpenMoji cached = new CachedOpenMoji();

	private static String padLeftZeros(String inputString, int length) {
	    if (inputString.length() >= length) {
	        return inputString;
	    }
	    StringBuilder sb = new StringBuilder();
	    while (sb.length() < length - inputString.length()) {
	        sb.append('0');
	    }
	    sb.append(inputString);

	    return sb.toString();
	}
	
	public static BufferedImage image(String emoji, int width, int height) 
	{
		try {
			return image2(emojiStringToFilename(emoji), width, height);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static BufferedImage image(String emoji, Font font, float scale)
	{
		try {
			return image2(emojiStringToFilename(emoji), font, scale);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String emojiStringToFilename(String emoji) {
		
		return emoji
			.codePoints()
			.mapToObj(Integer::toHexString)
			.map(String::toUpperCase)
			.map(s -> padLeftZeros(s, 4))
			.collect(Collectors.joining("-"));
	}

	private static BufferedImage image2(String filename, Font font, float scale) throws Exception
	{
		int size = calculateHeight(font, scale);
		
		return image2(filename, size, size);
		
	}

	static int calculateHeight(Font font, float scale) {
		String fontKey = font.toString();
		int size = fontHeights.computeIfAbsent(fontKey, f -> heightFromFont(font));
		
		size = (int) (size * scale);
		return size;
	}
	
	private static BufferedImage image2(String filename, int width, int height) throws Exception
	{
		try(InputStream is = OpenMoji.class.getResourceAsStream("/openmoji-svg-color/"+filename+".svg"))
		{
			if (is == null) {
				throw new RuntimeException("Unable to find "+filename);
			}
			return Rasterizer.transcode(is, width, height);
		}
	}
	
	private static int heightFromFont(Font font)
	{
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(font);
		int height = fm.getHeight();
		return height;
	}
	
	public static CachedOpenMoji cached()
	{
		return cached ;
	}
}
