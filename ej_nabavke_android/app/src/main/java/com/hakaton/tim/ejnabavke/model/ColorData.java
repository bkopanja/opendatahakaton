package com.hakaton.tim.ejnabavke.model;

import android.support.annotation.NonNull;

/**
 * Created by bojankopanja on 12/5/15.
 */
public class ColorData {

    private final int mColorInt;
    private final ColorGroup mColorGroup;

    public ColorData(int colorInt, @NonNull ColorGroup colorGroup) {
        mColorInt = colorInt;
        mColorGroup = colorGroup;
    }

    public int getIntValue() {
        return mColorInt;
    }

    public ColorGroup getColorGroup() {
        return mColorGroup;
    }

    @Override
    public String toString() {
        return String.format("#%06X", (0xFFFFFF & mColorInt));
    }
}
