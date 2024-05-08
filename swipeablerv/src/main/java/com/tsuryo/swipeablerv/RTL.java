package com.tsuryo.swipeablerv;

import java.util.Locale;

/**
 * Created by Tsur Yohananov on 08/05/2024.
 */
public final class RTL {

    public static boolean isRTL() {
        return isRTL(null);
    }

    public static boolean isRTL(Locale locale) {
        String displayName;
        if (locale == null)
            displayName = Locale.getDefault().getDisplayName();
        else
            displayName = locale.getDisplayName();

        final int directionality = Character.getDirectionality(displayName.charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

}
