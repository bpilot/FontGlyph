package com.radicalninja.fontmaker;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.lang.model.SourceVersion;

public class FontMaker {

    // TODO: Add in dynamic arguments for font / config files [IcoMoonSelection.DEFAULT_CONFIG_FILENAME]

    public static final String FONT_DIR = "../font/";

    public static final String ASSETS_DIR = "../library/src/main/assets/";
    public static final String RES_DIR = "../library/src/main/res/values/";
    public static final String SRC_DIR = "../library/src/main/java/com/radicalninja/fontglyph/";

    public static final String TEMPLATES_DIR = "./templates/";
    public static final String GLYPH_TEMPLATE = "Glyph.java.template";
    public static final String ATTRS_TEMPLATE = "attrs_glyphs.xml.template";

    public static class GlyphEntry {

        public Integer code;
        public String name;

        public GlyphEntry(Integer code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    public static void main(String[] args) {

        boolean success = new FontMaker().makeFont();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Font Creation Result - Successful: " + success);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public boolean makeFont() {

        try {
            String json = FileUtils.readFile(FONT_DIR + IcoMoonSelection.DEFAULT_CONFIG_FILENAME);
            IcoMoonSelection selection = new Gson().fromJson(json, IcoMoonSelection.class);

            List<GlyphEntry> glyphs = selection.getGlyphs();
            String filename = selection.getFontFilename();
            int resetPoint = selection.getResetPoint();

            int lessReset = 0;
            if (resetPoint == 0) {
                lessReset = glyphs.get(0).code;
                resetPoint = lessReset;
            }
            for (GlyphEntry glyph : glyphs) {
                glyph.code -= lessReset;
                glyph.name = glyph.name.replace("-","_");
                if (SourceVersion.isKeyword(glyph.name)) {
                    glyph.name = "_".concat(glyph.name);
                }
            }

            writeGlyphFile(glyphs, filename, resetPoint);
            writeAttrsFile(glyphs);
            copyFontFile(filename);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void writeGlyphFile(final List<GlyphEntry> glyphs,
                                final String filename, final int resetPoint) {

        try {
            final String template = FileUtils.readFile(TEMPLATES_DIR + GLYPH_TEMPLATE);
            StringBuilder sb = new StringBuilder();
            for (GlyphEntry glyph : glyphs) {
                sb.append(String.format("%s(%d), ", glyph.name.toUpperCase(), glyph.code));
            }
            String enums = sb.substring(0, sb.length()-2);
            String output = String.format(template, enums, resetPoint, filename);
            FileUtils.writeFile(SRC_DIR + GLYPH_TEMPLATE.replace(".template",""), output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeAttrsFile(final List<GlyphEntry> glyphs) {

        try {
            final String template = FileUtils.readFile(TEMPLATES_DIR + ATTRS_TEMPLATE);
            StringBuilder sb = new StringBuilder();
            for (GlyphEntry glyph : glyphs) {
                sb.append(String.format("<enum name=\"%s\" value=\"%d\" />", glyph.name.toLowerCase(), glyph.code));
            }
            String output = String.format(template, sb.toString());
            FileUtils.writeFile(RES_DIR + ATTRS_TEMPLATE.replace(".template",""), output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFontFile(final String filename) {

        try {
            FileUtils.copyFile(FONT_DIR + filename, ASSETS_DIR + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
