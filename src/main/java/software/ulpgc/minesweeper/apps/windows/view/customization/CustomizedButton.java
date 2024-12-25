package software.ulpgc.minesweeper.apps.windows.view.customization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomizedButton extends JToggleButton {

    private final Color defaultColor = new Color(0, 144, 0);
    private final Color hoverColor = new Color(0, 200, 50);
    private final Color selectedColor = new Color(101, 67, 33);

    public CustomizedButton() {
        setBackground(defaultColor);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected()) {
                    setBackground(hoverColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected()) {
                    setBackground(defaultColor);
                }
            }
        });

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) setBackground(selectedColor);
        else setBackground(defaultColor);
    }

    public CustomizedButton personalizeButton(String text) {
        setText(text);
        setActionCommand(text);
        setFont(new CustomFont().loadFont().deriveFont(30f));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }
}
