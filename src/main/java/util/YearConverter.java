package util;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;
import java.time.Year;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Short> {
    @Override
    public Short convertToDatabaseColumn(Year year) {
        if (year != null) {
            return (short) year.getValue();
        }
        return null;
    }

    @Override
    public Year convertToEntityAttribute(Short shortYear) {
        if (shortYear != null) {
            return Year.of(shortYear);
        }
        return null;
    }
}