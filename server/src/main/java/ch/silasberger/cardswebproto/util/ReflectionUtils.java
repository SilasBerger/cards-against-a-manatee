package ch.silasberger.cardswebproto.util;

import ch.silasberger.cardswebproto.util.datastructures.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReflectionUtils {

    public static class ClassName {
        public static String qualifiedAfter(Class<?> clazz, String afterPackage) {
            return partiallyQualified(clazz, afterPackage, 1);
        }

        public static String qualifiedFrom(Class<?> clazz, String fromPackage) {
            return partiallyQualified(clazz, fromPackage, 0);
        }

        public static String simpleName(String qualifiedName) {
            Pair<String, String> split = splitPackageAndClassName(qualifiedName);
            if (split == null) {
                return null;
            }
            return split.second();
        }

        public static String packageName(String qualifiedName) {
            Pair<String, String> split = splitPackageAndClassName(qualifiedName);
            if (split == null) {
                return null;
            }
            return split.first();
        }

        public static Pair<String, String> splitPackageAndClassName(String qualifiedName) {
            if (qualifiedName == null) {
                return null;
            }
            int splitIndex = qualifiedName.lastIndexOf('.');
            if (splitIndex < 0) {
                return null;
            }
            return new Pair<>(qualifiedName.substring(0, splitIndex), qualifiedName.substring(splitIndex + 1));
        }

        /**
         * TODO: Make this more generic, not just for snake case.
         * Splits class name of a qualified name at upper case letters, removes parts that match the removeRegex
         * pattern, joins remaining parts as snake_case, and returns edited qualified name.
         * @param qualifiedName qualified name to be edited
         * @param removeRegex   pattern that matches name parts which should be removed from the class name
         * @return              formatted qualified name, or null of supplied input is not a qualified name
         */
        public static String formatClassName(String qualifiedName, String removeRegex) {
            Pair<String, String> packageAndClass = splitPackageAndClassName(qualifiedName);
            if (packageAndClass == null) {
                return null;
            }

            List<String> nameParts = Arrays.asList(packageAndClass.second().split("(?=\\p{Lu})"));
            Pattern ignorePattern = Pattern.compile(removeRegex);
            List<String> cleanedParts = nameParts
                    .stream()
                    .filter(part -> !ignorePattern.matcher(part).matches())
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            return packageAndClass.first() + "." + String.join("_", cleanedParts);
        }

        private static String partiallyQualified(Class<?> clazz, String targetPackage, int skipAfterTarget) {
            if (clazz == null || targetPackage == null) {
                return null;
            }
            List<String> packageParts = Arrays.asList(clazz.getPackageName().split("\\."));
            int targetIndex = packageParts.indexOf(targetPackage);
            if (targetIndex < 0) {
                // Target package not found.
                return null;
            }
            StringBuilder sb = new StringBuilder();
            packageParts
                    .stream()
                    .skip(targetIndex + skipAfterTarget)
                    .forEach(part -> sb.append(part).append("."));
            sb.append(clazz.getSimpleName());
            return sb.toString();
        }
    }



}
