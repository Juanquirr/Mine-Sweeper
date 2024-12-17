package software.ulpgc.minesweeper.apps.windows.view.customization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class CustomizedButton extends JToggleButton {

    public CustomizedButton() {
        Map<Color, Color> colorMap = new HashMap<>();
        colorMap.put(new Color(153, 204, 255), Color.darkGray);
        colorMap.put(Color.darkGray, new Color(153, 204, 255));


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

    public CustomizedButton personalizeButton(String text){
        setText(text);
        setActionCommand(text);
        setFont(new CustomFont().loadFont().deriveFont(30f));
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setFocusPainted(false);
        setBorderPainted(false);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }
}
