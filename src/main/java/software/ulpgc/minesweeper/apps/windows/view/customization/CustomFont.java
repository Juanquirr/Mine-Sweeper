package software.ulpgc.minesweeper.apps.windows.view.customization;

import java.awt.*;
import java.io.File;
import java.util.Objects;

public class CustomFont {
    private final Font customFont;
    public CustomFont() {
        try {
            this.customFont = Font.createFont(
                            Font.TRUETYPE_FONT,
                            new File(
                                    Objects.requireNonNull(
                                            CustomFont.class.getClassLoader().getResource("LuckiestGuy-Regular.ttf")
                                    ).getFile()
                            )
                    )
                    .deriveFont(24f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font", e);
        }
    }

    public Font loadFont() {
        return customFont;
    }
}
