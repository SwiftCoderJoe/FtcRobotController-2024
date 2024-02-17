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
        drive(0, 0, 0.1);
    }

    private void drive(double leftPower, double rightPower, double seconds) {
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < seconds)) {
            bot.leftRearMotor.setPower(leftPower);
            bot.leftFrontMotor.setPower(leftPower * 0.9);
            bot.rightRearMotor.setPower(rightPower * 0.9);
            bot.rightFrontMotor.setPower(rightPower * 0.9);

            telemetry.addData("Path", "Elapsed: %4.1f S", runtime.seconds());
            telemetry.update();
        }
    }
}
