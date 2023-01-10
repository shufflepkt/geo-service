package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static ru.netology.geo.GeoServiceImpl.*;

public class GeoServiceImplTest {
    @MethodSource("methodSource")
    @ParameterizedTest
    public void byIpTest(String ip, Location expected) {
        GeoServiceImpl globalServiceImpl = new GeoServiceImpl();

        Location actual = globalServiceImpl.byIp(ip);

        // Почему-то не получается сравнить объекты. Поля одинаковые, но сравнение ведется по ссылкам на объекты
        // В итоге, ниже сделал сравнение по всем полям объектов. Однако в этом варианте не работает проверка для null
//
//        Assertions.assertEquals(expected, actual);

        Assertions.assertEquals(expected.getCountry(), actual.getCountry());
        Assertions.assertEquals(expected.getCity(), actual.getCity());
        Assertions.assertEquals(expected.getStreet(), actual.getStreet());
        Assertions.assertEquals(expected.getBuiling(), actual.getBuiling());
    }

    public static Stream<Arguments> methodSource() {
        return  Stream.of(
                Arguments.of(LOCALHOST, new Location(null, null, null, 0)),
                Arguments.of(MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null,  0))/*,
                Arguments.of("111", null)*/
        );
    }

    @Test
    public void byCoordinatesTest() {
        GeoServiceImpl globalServiceImpl = new GeoServiceImpl();
        double latitude = 0.12334;
        double longitude = 123.243;


        Assertions.assertThrows(RuntimeException.class, () -> {globalServiceImpl.byCoordinates(latitude, longitude);});
    }
}