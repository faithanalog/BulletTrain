package com.unknownloner.btrain;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class Util {

    public static String readText(InputStream in) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader((in)));
        StringBuilder b = new StringBuilder();
        String ln;
        while ((ln = r.readLine()) != null) {
            b.append(ln).append('\n');
        }
        //Cut off last newline
        return b.substring(0, b.length() - 1);
    }

    public static String readText(String path) throws IOException {
        return readText(Util.class.getResourceAsStream(path));
    }

    public static BufferedImage readImage(String path) throws IOException {
        return ImageIO.read(Util.class.getResourceAsStream(path));
    }

    public static boolean isPowerOf2(int num) {
        int bits = 0;
        for (int i = 0; i < 32; i++) {
            bits += (num & 1);
            num >>>= 1;
        }
        return bits == 1;
    }

    /**
     * Converts quads to triangles
     * @param in Quads
     * @param elems Amount of elements per vertex
     * @return Triangles
     */
    public static float[] toTris(float[] in, int elems) {
        float[] out = new float[in.length * 6 / 4];

        FloatBuffer o = FloatBuffer.wrap(out);

        int inNum = in.length / elems;
        int numQuads = inNum / 4;
        for (int quad = 0; quad < numQuads; quad++) {
            int i = quad * elems * 4;
            o.put(in, i + elems * 0, elems);
            o.put(in, i + elems * 1, elems);
            o.put(in, i + elems * 2, elems);
            o.put(in, i + elems * 2, elems);
            o.put(in, i + elems * 3, elems);
            o.put(in, i + elems * 0, elems);
        }
        return out;
    }

    public static BufferedImage scale(BufferedImage src, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawImage(src, 0, 0, w, h, null);
        g.dispose();
        return img;
    }

    public static ByteBuffer imgBytesRGBA(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        ByteBuffer bytes = BufferUtils.createByteBuffer(w * h * 4);

        int[] d = new int[w * h];
        img.getRGB(0, 0, w, h, d, 0, w);
        bytes.asIntBuffer().put(d);
        return bytes;
    }

    public static int stringWidth(String str, int scale) {
        return str.length() * 6 * scale;
    }


}
