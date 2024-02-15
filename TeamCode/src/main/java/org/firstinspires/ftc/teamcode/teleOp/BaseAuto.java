package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class BaseAuto extends LinearOpMode {

    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    private ElapsedTime runtime = new ElapsedTime();
    protected Robot bot;

    protected void setRobot(Robot robot) {
        this.bot = robot;
        bot.leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
        bot.leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        bot.rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        bot.rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        bot.rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bot.leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bot.rightRearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bot.leftRearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    protected void driveForward(double seconds) {
        drive(FORWARD_SPEED, FORWARD_SPEED, seconds);
    }

    protected void spinLeft(double seconds) {
        drive(-TURN_SPEED, TURN_SPEED, seconds);
    }

    protected void spinRight(double seconds) {
        drive(TURN_SPEED, -TURN_SPEED, seconds);
    }

    protected void stopRobot() {
        drive(0, 0, 0);
    }

    private void drive(double leftPower, double rightPower, double seconds) {
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < seconds)) {
            bot.leftRearMotor.setPower(leftPower);
            bot.leftFrontMotor.setPower(leftPower);
            bot.rightRearMotor.setPower(rightPower);
            bot.rightFrontMotor.setPower(rightPower);

            telemetry.addData("Path", "Elapsed: %4.1f S", runtime.seconds());
            telemetry.update();
        }
    }
}
