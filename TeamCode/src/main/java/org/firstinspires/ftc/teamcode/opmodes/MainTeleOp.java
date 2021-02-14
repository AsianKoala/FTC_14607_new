package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.control.stupidrobot;
import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.movement.Odometry;
import org.firstinspires.ftc.teamcode.util.MathUtil;

import static org.firstinspires.ftc.teamcode.movement.PPController.goToPosition;
import static org.firstinspires.ftc.teamcode.movement.PPController.pointAngle;

@TeleOp(name = "teleop")
public class MainTeleOp extends stupidrobot {

    private boolean goToShootingPoint = false;
    private boolean turnReached = false;
    private boolean shootingZoneReached = false;

    private boolean intakeOn = false;

    private double actuatorStartTime = 0;
    private boolean shootingStateMachine = false;
    private double shootingStateMachineStartTime = 0;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        super.loop();
        controlJoystickMovement();
        controlIntakePower();
        controlShooterSequence();
        controlWobbleGoal();
        controlShootingMovement();
        telemetryVars();
    }

    public void controlJoystickMovement() {
        double driveScale = 0.7 - (gamepad1.left_bumper ? 0.4 : 0);
        DriveTrain.movementY = -gamepad1.left_stick_y * driveScale;
        DriveTrain.movementX = gamepad1.left_stick_x * driveScale;
        DriveTrain.movementTurn = -gamepad1.right_stick_x * driveScale;
    }

    public void controlIntakePower() {
        if(gamepad1.right_bumper) {
            intakeOn = !intakeOn;
        }
        if(intakeOn) {
            intake.turnOn();
        } else {
            intake.turnOff();
        }
    }

    public void controlShooterSequence() {
        if(gamepad1.left_trigger > 0.7) {
            shootingStateMachine = true;
            shootingStateMachineStartTime = System.currentTimeMillis();
            shooter.turnOn();
        }

        if(shootingStateMachine) {
            if(System.currentTimeMillis() - shootingStateMachineStartTime > 1000) { // rev time
                actuatorStartTime = System.currentTimeMillis();
                actuator.push();
            }

            if(System.currentTimeMillis() - actuatorStartTime > 1500) { // push time
                actuator.reset();
                shooter.turnOff();
                shootingStateMachine = false;
            }
        }
    }

    public void controlWobbleGoal() {
        if(gamepad1.dpad_up) {
            wobbleGoal.in();
        }
        if(gamepad1.dpad_down) {
            wobbleGoal.out();
        }
        if(gamepad1.dpad_left) {
            wobbleGoal.grab();
        }
        if(gamepad1.dpad_right) {
            wobbleGoal.release();
        }
    }


    public void controlShootingMovement() {
        if (gamepad1.right_trigger > 0.7) {
            goToShootingPoint = true;
        }

        if(goToShootingPoint) {
            if(!shootingZoneReached) {
                boolean done = goToPosition(0, 0, 0.6, Math.toRadians(90), 0.6, Math.toRadians(45), 0.6, 2, true).withinBounds;
                if(done) {
                    DriveTrain.stopMovement();
                    shootingZoneReached = true;
                }
            } else if(!turnReached) {
                pointAngle(Math.toRadians(90), 0.8, Math.toRadians(45));
                if(isTurnedTowardsGoal()) {
                    DriveTrain.stopMovement();
                    turnReached = true;
                }
            } else {
                DriveTrain.stopMovement();
                shootingZoneReached = false;
                turnReached = false;
                goToShootingPoint = false;
            }
        }
    }


    private boolean isTurnedTowardsGoal() {
        return Math.abs(MathUtil.angleWrap(Math.toRadians(90) - Odometry.currentPosition.heading)) < Math.toRadians(2);
    }


    public void telemetryVars() {
        telemetry.addLine("goToShootingPoint: " + goToShootingPoint);
        telemetry.addLine("90diff: " + Math.toDegrees(MathUtil.angleWrap(Math.toRadians(90) - Odometry.currentPosition.heading)));
        telemetry.addLine("shootingZoneReached: " + shootingZoneReached);
        telemetry.addLine("turnReached: " + turnReached);
    }
}
