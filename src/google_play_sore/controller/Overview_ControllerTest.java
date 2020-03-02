package google_play_sore.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Overview_ControllerTest {

	String expected = "https://www.kaggle.com/lava18/google-play-store-apps#googleplaystore.csv";

	String actual = "https://www.kaggle.com/lava18/google-play-store-apps#googleplaystore.csv";

	@Test
	void testOpen_hyperlink() {
		Overview_Controller detail = new Overview_Controller();
		detail.open_hyperlink(actual);
		assertEquals(expected, actual);
	}

}
