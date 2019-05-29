package utilities;

import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class GetFullPageScreenShot {
	public static String capture(WebDriver driver, String screenShotName) throws Exception {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		String dest = System.getProperty("user.dir") + "\\src\\test\\resources\\errorscreenshots\\" + screenShotName
				+ ".png";
		ImageIO.write(screenshot.getImage(), "PNG", new File(dest));
		return dest;
	}
}
