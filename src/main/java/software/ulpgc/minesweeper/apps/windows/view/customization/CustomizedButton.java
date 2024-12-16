package software.ulpgc.minesweeper.apps.windows.view.customization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CustomizedButton extends JToggleButton {
    private final Font customFont;

    public CustomizedButton(String text) {
        Map<Color, Color> colorMap = new HashMap<>();
        colorMap.put(new Color(153, 204, 255), Color.darkGray);
        colorMap.put(Color.darkGray, new Color(153, 204, 255));

        try {
            customFont = Font.createFont(
                            Font.TRUETYPE_FONT,
                            new File("LuckiestGuy-Regular.ttf")
                    )
                    .deriveFont(24f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load font", e);
        }

        setText(text);
        setActionCommand(text);
        setFont(customFont.deriveFont(30f));
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(true);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isSelected()) return;
                setBackground(colorMap.get(getBackground()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (isSelected()) return;
                setBackground(colorMap.get(getBackground()));
            }
        });
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    }

    public Font getCustomFont() {
        return customFont;
    }
}
