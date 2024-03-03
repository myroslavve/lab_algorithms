package org.verstyukhnutov.kmateam.utils;

public class Validation {
    /**
     * Checks if the string is empty
     *
     * @param string string to check
     * @return true if the string is empty, false otherwise
     */
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * Checks if course is valid
     *
     * @param course course to check
     * @return true if the course is valid, false otherwise
     */
    public static boolean isValidCourse(int course) {
        return course > 0 && course < 5;
    }

    /**
     * Checks if group is valid
     *
     * @param group group to check
     * @return true if the group is valid, false otherwise
     */
    public static boolean isValidGroup(int group) {
        return group > 0 && group < 7;
    }

    /**
     * Checks if name is of format "P_I_B" - where "P" is a surname, "I" is a name, "B" is a patronymic
     * @param name name to check
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }

        String[] parts = name.split("_");

        // Check if the number of parts is not 3
        if (parts.length != 3) {
            return false;
        }

        if (parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
            return false;
        }

        // Check if all characters in all parts are letters
        for (String part : parts) {
            for (char c : part.toCharArray()) {
                if (!Character.isLetter(c)) {
                    return false;
                }
            }
        }

        return true;
    }
}
