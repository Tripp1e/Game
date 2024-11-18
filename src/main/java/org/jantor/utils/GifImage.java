package org.jantor.utils;

import greenfoot.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GifImage {
    private GreenfootImage[] images;
    private int[] delay;
    private int currentIndex;
    private long time;
    private final boolean pause;


    public GifImage(String file) {
        pause = false;
        if (file.toLowerCase().endsWith(".gif")) {
            loadImages(file);
        } else {
            images = new GreenfootImage[]{new GreenfootImage(file)};
            delay = new int[]{1000};
            currentIndex = 0;
            time = System.currentTimeMillis();
        }
    }


    public GreenfootImage getCurrentImage() {
        long delta = System.currentTimeMillis() - time;

        while (delta >= delay[currentIndex] && !pause) {
            delta -= delay[currentIndex];
            time += delay[currentIndex];
            currentIndex = (currentIndex + 1) % images.length;
        }
        return images[currentIndex];
    }


    private void loadImages(String file) {
        GifDecoder decode = new GifDecoder();
        decode.read(file);
        int numFrames = decode.getFrameCount();
        if (numFrames > 0) {
            images = new GreenfootImage[numFrames];
            delay = new int[numFrames];
        } else {
            images = new GreenfootImage[1];
            images[0] = new GreenfootImage(1, 1);
        }

        for (int i = 0; i < numFrames; i++) {
            GreenfootImage image = new GreenfootImage(decode.getFrame(i).getWidth(), decode.getFrame(i).getHeight());
            image.drawImage(decode.getFrame(i), 0, 0);
            delay[i] = decode.getDelay(i);
            images[i] = image;
        }
        time = System.currentTimeMillis();
    }


    private static class Rectangle {
        public int x;
        public int y;
        public int width;
        public int height;

        public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }


    private static class GifDecoder {

        public static final int STATUS_OK = 0;

        public static final int STATUS_FORMAT_ERROR = 1;

        public static final int STATUS_OPEN_ERROR = 2;

        private BufferedInputStream in;

        private int status;

        private int width;

        private int height;

        private boolean gctFlag;

        private int gctSize;

        private int[] gct;

        private int[] lct;

        private int[] act;

        private int bgIndex;

        private Color bgColor;

        private Color lastBgColor;

        private boolean interlace;

        private int ix, iy, iw, ih;

        private Rectangle lastRect;

        private GreenfootImage image;

        private GreenfootImage lastImage;

        private final byte[] block = new byte[256];

        private int blockSize = 0;

        private int dispose = 0;

        private int lastDispose = 0;

        private boolean transparency = false;

        private int delay = 0;

        private int transIndex;

        private static final int MaxStackSize = 4096;


        private short[] prefix;

        private byte[] suffix;

        private byte[] pixelStack;

        private byte[] pixels;

        private ArrayList<GifFrame> frames;

        private int frameCount;


        private static class GifFrame {
            public GifFrame(GreenfootImage im, int del) {
                image = im;
                delay = del;
            }

            private final GreenfootImage image;

            private final int delay;
        }


        private Color colorFromInt(int rgb) {
            int r = (rgb & 0xFF0000) >> 16;
            int g = (rgb & 0xFF00) >> 8;
            int b = (rgb & 0xFF);
            return new Color(r, g, b);
        }


        public int getDelay(int n) {

            delay = -1;
            if ((n >= 0) && (n < frameCount)) {
                delay = (frames.get(n)).delay;
            }
            return delay;
        }


        public int getFrameCount() {
            return frameCount;
        }


        protected void setPixels() {

            if (lastDispose > 0) {
                if (lastDispose == 3) {

                    int n = frameCount - 2;
                    if (n > 0) {
                        lastImage = getFrame(n - 1);
                    } else {
                        lastImage = null;
                    }
                }

                if (lastImage != null) {
                    image.clear();
                    image.drawImage(lastImage, 0, 0);


                    if (lastDispose == 2) {

                        Color c;
                        if (transparency) {
                            c = new Color(0, 0, 0, 0);
                        } else {
                            c = lastBgColor;
                        }
                        for (int x = 0; x < lastRect.width; x++) {
                            for (int y = 0; y < lastRect.height; y++) {
                                image.setColorAt(lastRect.x + x, lastRect.y + y, c);
                            }
                        }
                    }
                }
            }

            int pass = 1;
            int inc = 8;
            int iline = 0;
            for (int i = 0; i < ih; i++) {
                int line = i;
                if (interlace) {
                    if (iline >= ih) {
                        pass++;
                        switch (pass) {
                            case 2:
                                iline = 4;
                                break;
                            case 3:
                                iline = 2;
                                inc = 4;
                                break;
                            case 4:
                                iline = 1;
                                inc = 2;
                        }
                    }
                    line = iline;
                    iline += inc;
                }
                line += iy;
                if (line < height) {
                    int dlim = Math.min(ix + iw, width);
                    int sx = i * iw;

                    for (int dx = ix; dx < dlim; dx++) {
                        int index = ((int) pixels[sx++]) & 0xff;
                        int c = act[index];
                        if (c != 0) {
                            image.setColorAt(dx, line, colorFromInt(c));
                        }
                    }
                }
            }
        }


        public GreenfootImage getFrame(int n) {
            GreenfootImage im = null;
            if ((n >= 0) && (n < frameCount)) {
                im = frames.get(n).image;
            }
            return im;
        }


        public int read(BufferedInputStream is) {
            init();
            if (is != null) {
                in = is;
                readHeader();
                if (!err()) {
                    readContents();
                    if (frameCount < 0) {
                        status = STATUS_FORMAT_ERROR;
                    }
                }
            } else {
                status = STATUS_OPEN_ERROR;
            }
            try {
                assert is != null;
                is.close();
            } catch (IOException ignored) {
            }
            return status;
        }


        public void read(String name) {
            status = STATUS_OK;
            InputStream resource = this.getClass().getResourceAsStream(name);
            if (resource == null) {
                name = "/images/" + name;
                resource = this.getClass().getResourceAsStream(name);
                if (resource == null) {
                    throw new RuntimeException("The gif file \"" + name + "\" doesn't exist.");
                }
            }
            in = new BufferedInputStream(resource);
            status = read(in);

        }


        protected void decodeImageData() {
            int NullCode = -1;
            int npix = iw * ih;
            int available, clear, code_mask, code_size, end_of_information, in_code, old_code, bits, code, count, i, datum, data_size, first, top, bi, pi;

            if ((pixels == null) || (pixels.length < npix)) {
                pixels = new byte[npix];
            }
            if (prefix == null) prefix = new short[MaxStackSize];
            if (suffix == null) suffix = new byte[MaxStackSize];
            if (pixelStack == null) pixelStack = new byte[MaxStackSize + 1];


            data_size = read();
            clear = 1 << data_size;
            end_of_information = clear + 1;
            available = clear + 2;
            old_code = NullCode;
            code_size = data_size + 1;
            code_mask = (1 << code_size) - 1;
            for (code = 0; code < clear; code++) {
                prefix[code] = 0;
                suffix[code] = (byte) code;
            }


            datum = bits = count = first = top = pi = bi = 0;

            for (i = 0; i < npix; ) {
                if (top == 0) {
                    if (bits < code_size) {

                        if (count == 0) {

                            count = readBlock();
                            if (count <= 0) break;
                            bi = 0;
                        }
                        datum += (((int) block[bi]) & 0xff) << bits;
                        bits += 8;
                        bi++;
                        count--;
                        continue;
                    }


                    code = datum & code_mask;
                    datum >>= code_size;
                    bits -= code_size;


                    if ((code > available) || (code == end_of_information)) break;
                    if (code == clear) {

                        code_size = data_size + 1;
                        code_mask = (1 << code_size) - 1;
                        available = clear + 2;
                        old_code = NullCode;
                        continue;
                    }
                    if (old_code == NullCode) {
                        pixelStack[top++] = suffix[code];
                        old_code = code;
                        first = code;
                        continue;
                    }
                    in_code = code;
                    if (code == available) {
                        pixelStack[top++] = (byte) first;
                        code = old_code;
                    }
                    while (code > clear) {
                        pixelStack[top++] = suffix[code];
                        code = prefix[code];
                    }
                    first = ((int) suffix[code]) & 0xff;


                    if (available >= MaxStackSize) break;
                    pixelStack[top++] = (byte) first;
                    prefix[available] = (short) old_code;
                    suffix[available] = (byte) first;
                    available++;
                    if (((available & code_mask) == 0) && (available < MaxStackSize)) {
                        code_size++;
                        code_mask += available;
                    }
                    old_code = in_code;
                }


                top--;
                pixels[pi++] = pixelStack[top];
                i++;
            }

            for (i = pi; i < npix; i++) {
                pixels[i] = 0;
            }

        }


        protected boolean err() {
            return status != STATUS_OK;
        }


        protected void init() {
            status = STATUS_OK;
            frameCount = 0;
            frames = new ArrayList<>();
            gct = null;
            lct = null;
        }


        protected int read() {
            int curByte = 0;
            try {
                curByte = in.read();
            } catch (IOException e) {
                status = STATUS_FORMAT_ERROR;
            }
            return curByte;
        }


        protected int readBlock() {
            blockSize = read();
            int n = 0;
            if (blockSize > 0) {
                try {
                    int count;
                    while (n < blockSize) {
                        count = in.read(block, n, blockSize - n);
                        if (count == -1) break;
                        n += count;
                    }
                } catch (IOException ignored) {
                }

                if (n < blockSize) {
                    status = STATUS_FORMAT_ERROR;
                }
            }
            return n;
        }


        protected int[] readColorTable(int ncolors) {
            int nbytes = 3 * ncolors;
            int[] tab = null;
            byte[] c = new byte[nbytes];
            int n = 0;
            try {
                n = in.read(c);
            } catch (IOException ignored) {
            }
            if (n < nbytes) {
                status = STATUS_FORMAT_ERROR;
            } else {
                tab = new int[256];
                int i = 0;
                int j = 0;
                while (i < ncolors) {
                    int r = ((int) c[j++]) & 0xff;
                    int g = ((int) c[j++]) & 0xff;
                    int b = ((int) c[j++]) & 0xff;
                    tab[i++] = 0xff000000 | (r << 16) | (g << 8) | b;
                }
            }
            return tab;
        }


        protected void readContents() {

            boolean done = false;
            while (!(done || err())) {
                int code = read();
                switch (code) {

                    case 0x2C:
                        readImage();
                        break;

                    case 0x21:
                        code = read();
                        switch (code) {
                            case 0xf9:
                                readGraphicControlExt();
                                break;

                            case 0xff:
                                readBlock();
                                StringBuilder app = new StringBuilder();
                                for (int i = 0; i < 11; i++) {
                                    app.append((char) block[i]);
                                }
                                if (app.toString().equals("NETSCAPE2.0")) {
                                    readNetscapeExt();
                                } else skip();
                                break;

                            default:
                                skip();
                        }
                        break;

                    case 0x3b:
                        done = true;
                        break;

                    case 0x00:
                        break;

                    default:
                        status = STATUS_FORMAT_ERROR;
                }
            }
        }


        protected void readGraphicControlExt() {
            read();
            int packed = read();
            dispose = (packed & 0x1c) >> 2;
            if (dispose == 0) {
                dispose = 1;
            }
            transparency = (packed & 1) != 0;
            delay = readShort() * 10;
            transIndex = read();
            read();
        }


        protected void readHeader() {
            StringBuilder id = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                id.append((char) read());
            }
            if (!id.toString().startsWith("GIF")) {
                status = STATUS_FORMAT_ERROR;
                return;
            }

            readLSD();
            if (gctFlag && !err()) {
                gct = readColorTable(gctSize);
                bgColor = colorFromInt(gct[bgIndex]);
            }
        }


        protected void readImage() {
            ix = readShort();
            iy = readShort();
            iw = readShort();
            ih = readShort();

            int packed = read();
            boolean lctFlag = (packed & 0x80) != 0;
            interlace = (packed & 0x40) != 0;


            int lctSize = 2 << (packed & 7);

            if (lctFlag) {
                lct = readColorTable(lctSize);
                act = lct;
            } else {
                act = gct;
                if (bgIndex == transIndex) bgColor = colorFromInt(0);
            }
            int save = 0;
            if (transparency) {
                save = act[transIndex];
                act[transIndex] = 0;
            }

            if (act == null) {
                status = STATUS_FORMAT_ERROR;
            }

            if (err()) return;

            decodeImageData();
            skip();

            if (err()) return;

            frameCount++;

            image = new GreenfootImage(width, height);

            setPixels();

            frames.add(new GifFrame(image, delay));

            if (transparency) {
                act[transIndex] = save;
            }
            resetFrame();

        }


        protected void readLSD() {

            width = readShort();
            height = readShort();

            int packed = read();
            gctFlag = (packed & 0x80) != 0;


            gctSize = 2 << (packed & 7);

            bgIndex = read();
            read();
        }


        protected void readNetscapeExt() {
            do {
                readBlock();
            } while ((blockSize > 0) && !err());
        }


        protected int readShort() {

            return read() | (read() << 8);
        }


        protected void resetFrame() {
            lastDispose = dispose;
            lastRect = new Rectangle(ix, iy, iw, ih);
            lastImage = image;
            lastBgColor = bgColor;
            lct = null;
        }


        protected void skip() {
            do {
                readBlock();
            } while ((blockSize > 0) && !err());
        }
    }

}