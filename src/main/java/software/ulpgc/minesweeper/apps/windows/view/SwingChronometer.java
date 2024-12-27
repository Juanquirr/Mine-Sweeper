package software.ulpgc.minesweeper.apps.windows.view;

import software.ulpgc.minesweeper.architecture.view.Chronometer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.atomic.AtomicLong;

public class SwingChronometer extends JPanel implements Chronometer {
    private final JLabel label;
    private final Timer timer;
    private final AtomicLong counter = new AtomicLong(0);

    public SwingChronometer() {
        add(this.label = new JLabel("00:00"));
        this.label.setBorder(new LineBorder(new Color(0,0,10), 4));
        this.label.setFont(new Font("Arial", Font.PLAIN, 20));
        this.timer = createTimer();
    }

    @Override
    public void start(){
        this.timer.start();
    }

    @Override
    public long currentTime() {
        return counter.get();
    }

    @Override
    public void stop(){
        this.timer.stop();
        this.label.setText("00:00");
        counter.set(0);
    }

    private Timer createTimer() {
        return new Timer(10, e -> {
            if (counter.get() % 100 == 0) this.label.setText(normalize());
            counter.getAndIncrement();
        });
    }

    private String normalize(){
        return String.format("%02d:%02d", (counter.get() / 100) / 60, (counter.get() / 100) % 60);
    }
}
