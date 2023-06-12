package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("45.94.119.107");
    assertEquals(ipLocation, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
  }

  @Test
  public void testInvalidIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("45.94.119.xxx");
    assertEquals(ipLocation, "<GeoIP><Country>US</Country><State>CA</State></GeoIP>");
  }
}
