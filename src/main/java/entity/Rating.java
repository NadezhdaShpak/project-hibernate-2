package entity;

import lombok.Getter;

import static java.util.Objects.isNull;

@Getter
public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    public static Rating getRatingByString(String value) {
        if (isNull(value) || value.isEmpty()) {
            return G;
        }
        for (Rating rating : Rating.values()) {
            if (rating.value.equalsIgnoreCase(value)) {
                return rating;
            }
        }
        return G;
    }
}
