package software.ulpgc.minesweeper.apps.windows.view.customization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomizedButton extends JToggleButton {

    public CustomizedButton() {
        setBackground(Color.DefaultButtonColor.getColor());
        setForeground(Color.White.getColor());
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected()) {
                    setBackground(Color.HoverButtonColor.getColor());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected()) {
                    setBackground(Color.DefaultButtonColor.getColor());
                }
            }
        });

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) setBackground(Color.SelectedButtonColor.getColor());
        else setBackground(Color.DefaultButtonColor.getColor());
    }

    public CustomizedButton personalizeButton(String text) {
        setText(text);
        setActionCommand(text);
        setFont(new CustomFont().loadFont().deriveFont(30f));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }
}
