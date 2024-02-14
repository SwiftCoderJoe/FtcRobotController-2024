package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

@TeleOp(name="TeleOp", group="Skula and Joe")
public class FieldCentricMovement extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Robot bot = new Robot(hardwareMap);

        bot.leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
        bot.leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        bot.rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        bot.rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);

        // Button States
        boolean aButtonState = false;
        // Pretend the B button started pressed so we can reset the servo position every time
        boolean bButtonState = true;
        boolean xButtonState = false;
        boolean rightBumperButtonState = false;

        boolean blackWheelsOverride = false;
        double blackWheelsTargetPower = 0;

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match the robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double speedVar = gamepad1.left_bumper ? .3 : 1;

            float y = -gamepad1.left_stick_y;
            float x = gamepad1.left_stick_x;
            float rx = gamepad1.right_stick_x;

            double scalar = (speedVar) / Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            bot.rightFrontMotor.setPower((-rx + y - x) * scalar);
            bot.leftFrontMotor.setPower((rx + y + x) * scalar);
            bot.rightRearMotor.setPower((-rx + y + x) * scalar);
            bot.leftRearMotor.setPower((-rx - y + x) * scalar);

            // Neutral State
            if (gamepad1.b && !bButtonState) {
                xButtonState = false;
                bButtonState = true;
                // Closer to the rear of the bot
                bot.handleRotator.setPosition(0);
                bot.topOfSlide.setPosition(0.28);
            }
            if (!gamepad1.b && bButtonState) {
                bButtonState = false;
                // Normal 'b button state'
                bot.setPanStandardPosition();
            }

            // Up State
            if (gamepad1.y) {
                bot.topOfSlide.setPosition(0.65);
            }

            // Dump State
            if (gamepad1.x && !xButtonState) {
                xButtonState = true;
                bot.handleRotator.setPosition(
                        bot.handleRotator.getPosition() <= 0.20 ? 0.95 : 0.15
                );
            } else if (!gamepad1.x) {
                xButtonState = false;
            }

            // Black Wheels
            if (gamepad1.a && !aButtonState) {
                aButtonState = true;
                blackWheelsTargetPower = (
                        blackWheelsTargetPower <= 0 ? .9 : 0
                );
            } else if (!gamepad1.a) {
                aButtonState = false;
            }

            // Reverse Black Wheels
            if (gamepad1.right_bumper && !rightBumperButtonState) {
                rightBumperButtonState = true;
                blackWheelsTargetPower = (
                        blackWheelsTargetPower >= 0 ? -0.9 : 0
                );
            } else if (!gamepad1.right_bumper) {
                rightBumperButtonState = false;
            }

            // Linear Slide Control
            if (gamepad1.right_trigger > .1) {
                // UP
                // Lean it back while going up
                blackWheelsOverride = true;
                bot.topOfSlide.setPosition(0.2);
                bot.blackWheels.setPower(0.5 * gamepad1.right_trigger);
                bot.linearSlide.setPower(-gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > .1) {
                // DOWN
                // Lean it back while going up
                blackWheelsOverride = true;
                bot.topOfSlide.setPosition(0.2);
                bot.blackWheels.setPower(-0.5 * gamepad1.left_trigger);
                bot.linearSlide.setPower(gamepad1.left_trigger);
            } else {
                blackWheelsOverride = false;
                bot.linearSlide.setPower(0);
            }

            // Lift Control
            // For now, we won't use an encoder and instead just hope we don't over run the spool
            if (gamepad1.dpad_up) {
                bot.lift.setPower(0.8);
            } else if (gamepad1.dpad_down) {
                bot.lift.setPower(-0.8);
            } else {
                bot.lift.setPower(0);
            }

            if (!blackWheelsOverride) {
                bot.blackWheels.setPower(blackWheelsTargetPower);
            }


        }
    }
}