package com.radicalninja.fontglyph;

public enum Glyph {

    FILE_O(0),
    IMAGE(1),
    EXCLAMATION_TRIANGLE(2),
    FOLDER(3),
    FOLDER_OPEN(4),
    ARROW_CIRCLE_UP(5),
    FILE_TEXT_O(6),
    FOLDER_O(7),
    FOLDER_OPEN_O(8),
    FILE(9),
    FILE_TEXT(10),
    FILE_IMAGE_O(11),
    FILE_ARCHIVE_O(12),
    FILE_AUDIO_O(13),
    FILE_MOVIE_O(14),
    FILE_CODE_O(15);

    public static final int _BASE = 58880;

    public final int value;

    public static Glyph valueOf(int value) {
        return Glyph.values()[value];
    }

    private Glyph(int value) {
        this.value = value;
    }

}
