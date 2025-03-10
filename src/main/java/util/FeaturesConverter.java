package util;

import entity.Features;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FeaturesConverter implements AttributeConverter<Features, String> {
    @Override
    public String convertToDatabaseColumn(Features features) {
        return features.getValue();
    }

    @Override
    public Features convertToEntityAttribute(String dbData) {
        Features[] values = Features.values();
        for (Features feature : values) {
            if (feature.getValue().equals(dbData)) {
                return feature;
            }
        }
        return null;
    }
}