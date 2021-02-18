package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.control.Robot;
import org.openftc.revextensions2.ExpansionHubServo;

import java.util.ArrayList;

@TeleOp(name = "Servo Programmer")
public class BetterServoProgrammer extends Robot {
    public ServoData ourServoData;
    // 0.6  left grabber closed
    // 0.25 right grabber closed
    // 0.25 left grabber open
    // 0.6  right grabber open
    // 0.7  left pivot out
    // 0.1  right pivot out
    // 0.0  left pivot in
    // 0.8  right pivot in

    // 0.8 right in
    // 0.15 right out
    // 0.65 left out
    // 0.0 left in

    @Override
    public void init() {
        super.init();
        ExpansionHubServo servo = hardwareMap.get(ExpansionHubServo.class, "leftPivot"); // example
        ourServoData = new ServoData(servo, 0.05);
    }

    @Override
    public void loop() {
        super.loop();
        sleep();
        telemetry.addLine(ourServoData.update(gamepad1.x, gamepad1.b, gamepad1.a));
    }


    private void sleep() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ServoData {
    private final ExpansionHubServo servo;
    private double val;
    private final double increment;
    ServoData(ExpansionHubServo servo, double increment) {
        this.servo = servo;
        val = 0.5;
        this.increment = increment;
    }

    public String update(boolean isDecrease, boolean isIncrease, boolean set) {
        if(isDecrease) {
            val = Range.clip(val - increment, 0, 1);
        } else if(isIncrease) {
            val = Range.clip(val + increment, 0, 1);
        }

        if(set) {
            servo.setPosition(val);
        }

        return servo.getDeviceName() + ": " + val;
    }
}