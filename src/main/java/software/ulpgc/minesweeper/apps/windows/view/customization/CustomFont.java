package software.ulpgc.minesweeper.apps.windows.view.customization;

import java.awt.*;
import java.io.InputStream;

public class CustomFont {
    private final Font customFont;

    public CustomFont() {
        try (InputStream fontStream = CustomFont.class.getClassLoader().getResourceAsStream("LuckiestGuy-Regular.ttf")) {
            if (fontStream == null) {
                throw new RuntimeException("Font file 'LuckiestGuy-Regular.ttf' not found in resources.");
            }
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(24f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font", e);
        }
    }

    public Font loadFont() {
        return customFont;
    }
}
