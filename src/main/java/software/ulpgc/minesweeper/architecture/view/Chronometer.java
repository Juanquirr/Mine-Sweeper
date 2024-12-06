package software.ulpgc.minesweeper.architecture.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Chronometer extends JPanel {
    private final JLabel label;
    private final Timer timer;
    private final AtomicInteger counter = new AtomicInteger(0);

    public Chronometer() {
        add(this.label = new JLabel("00:00"));
        this.label.setBorder(new LineBorder(new Color(0,0,10), 4));
        this.label.setFont(new Font("Arial", Font.PLAIN, 26));
        this.timer = new Timer(1000, _ -> {
            this.label.setText(normalize());
            counter.getAndIncrement();
        });
    }

    public void start(){
        this.timer.start();
    }

    public void stop(){
        this.timer.stop();
        this.label.setText("00:00");
        counter.set(0);
    }

    public String normalize(){
        return String.format("%02d:%02d", counter.get() / 60, counter.get() % 60);
    }
}
