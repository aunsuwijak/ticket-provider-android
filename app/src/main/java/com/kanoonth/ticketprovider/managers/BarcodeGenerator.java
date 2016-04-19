package com.kanoonth.ticketprovider.managers;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by suwijakchaipipat on 4/10/2016 AD.
 */
public class BarcodeGenerator {

    private static BarcodeGenerator instance = null;

    private static final int COLOR_WHITE = 0xFFFFFFFF;
    private static final int COLOR_BLACK = 0xFF000000;

    public static BarcodeGenerator getInstance() {
        if (instance == null)
            instance = new BarcodeGenerator();
        return instance;
    }

    public Bitmap generateBitmapBarcode(String content, BarcodeFormat format, int width, int height) {
        if (content == null) return null;

        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(content.toCharArray());

        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result = null;

        try {
            result = writer.encode(content, format, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        width = result.getWidth();
        height = result.getHeight();

        int[] pixels = new int[width * height];

        for (int y = 0 ; y < height ; y++) {
            int offset = y * width;
            for (int x = 0 ; x < width ; x++) {
                pixels[offset + x] = result.get(x, y) ? COLOR_BLACK : COLOR_WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return bitmap;
    }

    private String guessAppropriateEncoding(char[] contents) {
        for (char c : contents) {
            if (c > 0xFF) return "UTF-8";
        }
        return null;
    }
}
