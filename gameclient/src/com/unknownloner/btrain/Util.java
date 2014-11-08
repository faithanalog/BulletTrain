package com.unknownloner.btrain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

}
