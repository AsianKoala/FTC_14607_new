package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.Actuator;
import org.firstinspires.ftc.teamcode.hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.IntakeTransfer;
import org.firstinspires.ftc.teamcode.hardware.Shooter;
import org.firstinspires.ftc.teamcode.hardware.WobbleGoal;
import org.openftc.revextensions2.ExpansionHubMotor;
import org.openftc.revextensions2.ExpansionHubServo;

public class stupidrobot extends OpMode {
    public DriveTrain driveTrain;
    public Intake intake;
    public IntakeTransfer intakeTransfer;
    public Shooter shooter;
    public Actuator actuator;
    public WobbleGoal wobbleGoal;

    @Override
    public void init() {
        ExpansionHubMotor frontLeft, frontRight, backLeft, backRight, intakeMotor, intakeTransferMotor, launcher1, launcher2;
        ExpansionHubServo actuatorServo, leftPivot, rightPivot, leftGrabber, rightGrabber;
        frontLeft = hardwareMap.get(ExpansionHubMotor.class, "FL");
        frontRight = hardwareMap.get(ExpansionHubMotor.class, "FR");
        backLeft = hardwareMap.get(ExpansionHubMotor.class, "BL");
        backRight = hardwareMap.get(ExpansionHubMotor.class, "BR");
        intakeMotor = hardwareMap.get(ExpansionHubMotor.class, "intake");
        intakeTransferMotor = hardwareMap.get(ExpansionHubMotor.class, "intakeTransfer");
        launcher1 = hardwareMap.get(ExpansionHubMotor.class, "launcher1");
        launcher2 = hardwareMap.get(ExpansionHubMotor.class, "launcher2");
        driveTrain = new DriveTrain(frontLeft, frontRight, backLeft, backRight);
        intake = new Intake(intakeMotor);
        intakeTransfer = new IntakeTransfer(intakeTransferMotor);
        shooter = new Shooter(launcher1, launcher2);

        actuatorServo = hardwareMap.get(ExpansionHubServo.class, "actuator");
        leftPivot = hardwareMap.get(ExpansionHubServo.class, "leftPivot");
        rightPivot = hardwareMap.get(ExpansionHubServo.class, "rightPivot");
        leftGrabber = hardwareMap.get(ExpansionHubServo.class, "leftGrabber");
        rightGrabber = hardwareMap.get(ExpansionHubServo.class, "rightGrabber");
        actuator = new Actuator(actuatorServo);
        wobbleGoal = new WobbleGoal(leftPivot, rightPivot, leftGrabber, rightGrabber);
    }

    @Override
    public void loop() {
        driveTrain.update();
        intake.update();
        intakeTransfer.update();
        shooter.update();
    }
}
