import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CovidTest {

	@Test
	public void testThailand() {
		CovidData thailandData = new CovidData("Thailand.csv");
		
		assertEquals(50, thailandData.find("3/7/20"));
		assertEquals(-1, thailandData.find("3/07/20"));
		assertEquals(599, thailandData.find("3/22/20"));
		assertEquals(2, thailandData.find("1/22/20"));
	}
	
	@Test
	public void testItaly() {
		CovidData italyData = new CovidData("Italy.csv");
		assertEquals(17660, italyData.find("3/13/20"));
		assertEquals(59138, italyData.find("3/22/20"));
		assertEquals(0, italyData.find("1/22/20"));
	}
	
	@Test
	public void testUS() {
		CovidData usData = new CovidData("US.csv");
		assertEquals(11, usData.find("2/8/20"));
		assertEquals(33272, usData.find("3/22/20"));
		assertEquals(1, usData.find("1/22/20"));
	}

}
