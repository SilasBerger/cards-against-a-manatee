package ch.silasberger.cardswebproto.util;

import java.util.UUID;

public class GeneralUtils {

    public static UUID parseUUID(String idString) {
        try {
            return UUID.fromString(idString);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

}
