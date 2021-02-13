package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.stupidrobot;
import org.firstinspires.ftc.teamcode.hardware.DriveTrain;

@TeleOp(name = "teleop")
public class dumbassteleop extends stupidrobot {

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        super.loop();
        controlJoystickMovement();
        controlIntakePower();
        controlIntakeTransferPower();
    }

    public void controlJoystickMovement() {
        double driveScale = 0.5 + (gamepad1.right_bumper ? 0.5 : 0) - (gamepad1.left_bumper ? 0.2 : 0);
        DriveTrain.movementY = -gamepad1.left_stick_y * driveScale;
        DriveTrain.movementX = gamepad1.left_stick_x * driveScale;
        DriveTrain.movementTurn = -gamepad1.right_stick_x * driveScale;
    }

    public void controlIntakePower() {
        if(gamepad1.left_bumper) {
            intake.reverse();
        } else if(gamepad1.left_trigger > 0.7) {
            intake.turnOn();
        } else {
            intake.turnOff();
        }
    }

    public void controlIntakeTransferPower() {
        if(gamepad1.right_bumper) {
            intakeTransfer.turnOn();
        } else if(gamepad1.right_trigger > 0.7) {
            intakeTransfer.reverse();
        } else {
            intakeTransfer.turnOff();
        }
    }
}
