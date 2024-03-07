package btvn;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ClockFrame extends JFrame implements Runnable {
    private JLabel timeLabel;
    private int timezoneOffset;

    public ClockFrame(int timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
        setTitle("World Clock");
        setSize(200, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        timeLabel = new JLabel();
        updateTime();

        add(timeLabel);

        // Khởi tạo và bắt đầu luồng để cập nhật thời gian
        Thread updateTimeThread = new Thread(this);
        updateTimeThread.start();
    }

    // Phương thức để cập nhật thời gian trong luồng độc lập
    @Override
    public void run() {
        while (true) {
            updateTime();
            try {
                // Đợi 1 giây trước khi cập nhật thời gian lại
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        long currentTime = System.currentTimeMillis() + timezoneOffset * 3600 * 1000;
        timeLabel.setText(dateFormat.format(new Date(currentTime)));
    }
}
