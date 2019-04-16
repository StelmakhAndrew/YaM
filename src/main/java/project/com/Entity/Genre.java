package project.com.Entity;

import java.util.Arrays;

/**
 *
 */
public enum Genre {
    CRIME,
    DETECTIVE,
    FANTASY,
    ROMANCE,
    HORROR,
    CLASSIC,
    FAIRY_TALE,
    HISTORICAL,
    HUMOR;


    public Integer getGenre() {
        return EnumUtils.getDatabaseId(this);
    }

    public static Genre getGenre(Integer statusId) {
        if (statusId == null) {
            return null;
        }
        return Arrays.stream(values())
                .filter(value -> value.getGenre() == statusId)
                .findFirst().orElse(null);
    }
    }

