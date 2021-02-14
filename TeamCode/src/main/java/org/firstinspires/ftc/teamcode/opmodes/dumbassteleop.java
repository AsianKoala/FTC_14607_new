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
        controlLauncher();
        controlActuator();
    }

    public void controlJoystickMovement() {
        double driveScale = 0.5;
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

    public void controlLauncher() {
        if(gamepad1.a) {
            shooter.turnOn();
        } else if(gamepad1.b) {
            shooter.reverse();
        } else {
            shooter.turnOff();
        }
    }

    public void controlActuator() {
        if(gamepad1.x) {
            actuator.push();
        }
        if(gamepad1.y) {
            actuator.reset();
        }
    }
}
