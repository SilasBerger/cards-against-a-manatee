package ch.silasberger.cardswebproto.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class NonceId {

    public static final String charset = "ABCDEFGHJKLMNPQRSTUVWXYZ1234567890";
    public static final int NUM_BLOCKS = 1;
    public static final int BLOCK_LENGTH = 4;
    public static final char BLOCK_DELIMITER = '-';

    public final String value;

    private NonceId(String value) {
        this.value = value;
    }

    public static NonceId random() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NUM_BLOCKS; i++) {
            for (int j = 0; j < BLOCK_LENGTH; j++) {
                int charIndex = random.nextInt(charset.length());
                sb.append(charset.charAt(charIndex));
            }
            if (i < (NUM_BLOCKS - 1)) {
                sb.append(BLOCK_DELIMITER);
            }
        }
        return new NonceId(sb.toString());
    }

    public static NonceId fromString(String s) {
        if (!isValid(s)) {
            throw new IllegalArgumentException("Not a valid Id: " + s);
        }
        return new NonceId(s);
    }

    public static boolean isValid(String s) {
        List<String> blocks = Arrays.asList(s.split("-"));
        if (blocks.size() != NUM_BLOCKS) {
            return false;
        }
        // TODO: Check that all blocks only contain characters in the specified charset.
        long numInvalidBlocks = blocks
                .stream()
                .filter(block -> block.length() != BLOCK_LENGTH)
                .count();
        return numInvalidBlocks == 0;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonceId nonceId = (NonceId) o;
        return Objects.equals(value, nonceId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
