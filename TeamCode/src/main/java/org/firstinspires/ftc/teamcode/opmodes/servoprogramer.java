package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.stupidrobot;

@TeleOp(name = "servo prgrm")
public class servoprogramer extends stupidrobot {
    public double lowerBound = 0.5;
    public double upperBound = 0.5;
    public static final double finalLower = 0.2;
    public static final double finalUpper = 0.45;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        super.loop();
        try {
            controlServo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        telemetry.addLine("lowerBound: " + lowerBound);
        telemetry.addLine("upperBound: "+ upperBound);
    }

    public void controlServo() throws InterruptedException {
        if(gamepad1.left_bumper) {
            lowerBound += 0.05;
            sleep();
        }
        if(gamepad1.left_trigger > 0.7) {
            lowerBound -= 0.05;
            sleep();
        }
        if(gamepad1.right_bumper) {
            upperBound += 0.05;
            sleep();
        }
        if(gamepad1.right_trigger > 0.7) {
            upperBound -= 0.05;
            sleep();
        }
        if(gamepad1.a) {
//            actuator.setPosition(lowerBound);
        }
        if(gamepad1.b) {
//            actuator.setPosition(upperBound);
        }
    }

    void sleep() throws InterruptedException {
        Thread.sleep(1000);
    }
}
