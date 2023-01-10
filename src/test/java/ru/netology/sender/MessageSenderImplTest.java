package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    @Test
    public void sendTestForRussia() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("172."))).
                thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        String expected = "Добро пожаловать";

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn(expected);

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");

        String actual = messageSenderImpl.send(headers);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void sendTestForUSA() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.startsWith("96."))).
                thenReturn(new Location("New York", Country.USA, null,  0));

        String expected = "Welcome";

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn(expected);

        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");

        String actual = messageSenderImpl.send(headers);

        Assertions.assertEquals(expected, actual);
    }
}