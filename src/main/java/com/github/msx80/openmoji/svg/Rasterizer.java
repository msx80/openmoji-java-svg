package com.github.msx80.openmoji.svg;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

class Rasterizer {

    public static BufferedImage transcode(InputStream is, int width, int height) throws Exception 
    {
        Transcoder t = new PNGTranscoder();

        t.addTranscodingHint(PNGTranscoder.KEY_WIDTH, (float) width);
        t.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, (float) height);

        TranscoderInput input = new TranscoderInput(is);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TranscoderOutput output = new TranscoderOutput(outputStream);

        t.transcode(input, output);

        outputStream.flush();
        outputStream.close();

        byte[] imgData = outputStream.toByteArray();
        return ImageIO.read(new ByteArrayInputStream(imgData));
        
        
    }

}