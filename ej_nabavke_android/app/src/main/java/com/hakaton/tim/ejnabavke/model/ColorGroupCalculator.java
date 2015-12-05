package com.hakaton.tim.ejnabavke.model;

import android.support.annotation.NonNull;

/**
 * Created by bojankopanja on 12/5/15.
 */
class ColorGroupCalculator {

    @NonNull
    public ColorGroup getColorGroup(float hue) {
        for (ColorGroup group : ColorGroup.values()) {
            if (group.containsHue((int) hue)) {
                return group;
            }
        }

        throw new NullPointerException("Could not classify hue into Color Group!");
    }
}
