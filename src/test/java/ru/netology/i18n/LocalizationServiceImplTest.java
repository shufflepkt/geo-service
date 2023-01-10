package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTest {
    @MethodSource("methodSource")
    @ParameterizedTest
    public void localeTest(Country country, String expected) {
        LocalizationServiceImpl localizationServiceImpl = new LocalizationServiceImpl();

        String actual = localizationServiceImpl.locale(country);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(GERMANY, "Welcome"),
                Arguments.of(USA, "Welcome"),
                Arguments.of(BRAZIL, "Welcome")
        );
    }
}