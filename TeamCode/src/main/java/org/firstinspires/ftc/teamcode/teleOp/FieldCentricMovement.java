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

        // Temporary button states for GP2 single-click linear slide
        boolean rightBumperState = false;
        boolean leftBumperState = false;

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match the robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);

        waitForStart();

//        bot.linearSlide.setTargetPosition(0);
//        bot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        bot.linearSlide.setPower(.8);

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double speedVar = gamepad1.left_bumper ? .3 : 1;
            // This button choice was made so that it is hard to hit on accident,
            // it can be freely changed based on preference.
            // The equivalent button is start on Xbox-style controllers
//            if (gamepad1.options) {
//                imu.resetYaw();
//            }
//
//            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//
//            // Rotate the movement direction counter to the bot's rotation
//            double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
//            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            // rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]

            float y = -gamepad1.left_stick_y;
            float x = gamepad1.left_stick_x;
            float rx = gamepad1.right_stick_x;

            double scalar = (speedVar) / Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            bot.rightFrontMotor.setPower((-rx + y - x) * scalar * bot.SPEED_CORRECTION_FACTOR);
            bot.leftFrontMotor.setPower((rx + y + x) * scalar);
            bot.rightRearMotor.setPower((-rx + y + x) * scalar * bot.SPEED_CORRECTION_FACTOR);
            bot.leftRearMotor.setPower((rx + y - x) * scalar * bot.SPEED_CORRECTION_FACTOR);

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
                bot.blackWheels.setPower(
                        bot.blackWheels.getPower() == 0 ? .9 : 0
                );
            } else if (!gamepad1.a) {
                aButtonState = false;
            }

            // Reverse Black Wheels
            if (gamepad1.right_bumper && !rightBumperButtonState) {
                rightBumperButtonState = true;
                bot.blackWheels.setPower(
                        bot.blackWheels.getPower() <= 0 ? -0.5 : 0
                );
            } else if (!gamepad1.right_bumper) {
                rightBumperButtonState = false;
            }

            // Linear Slide Control
            if (gamepad1.right_trigger > .1) {
                // UP
                bot.linearSlide.setPower(-gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > .1) {
                // DOWN
                bot.linearSlide.setPower(gamepad1.left_trigger);
            } else {
                bot.linearSlide.setPower(0);
            }

//            if (gamepad2.right_bumper && !rightBumperState) {
//                rightBumperState = true;
//                bot.linearSlide.setTargetPosition(-1900);
//            } else if (!gamepad2.left_bumper) {
//                rightBumperState = false;
//            }
//
//            if (gamepad2.left_bumper && !leftBumperState) {
//                leftBumperState = true;
//                bot.linearSlide.setTargetPosition(0);
//            } else if (!gamepad2.left_bumper) {
//                leftBumperState = false;
//            }

            // Lift Control
            // For now, we won't use an encoder and instead just hope we don't over run the spool
            if (gamepad1.dpad_up) {
                bot.lift.setPower(1);
            } else if (gamepad1.dpad_down) {
                bot.lift.setPower(-1);
            } else {
                bot.lift.setPower(0);
            }

            if (gamepad1.dpad_right) {
                bot.planeLauncher.setPosition(0);
            } else {
                bot.planeLauncher.setPosition(0.5);
            }

            /* OLD CODE FROM LAST YEAR
            if (gamepad1.dpad_left) {
                v4b.setPosition(0);
            }
            if (gamepad1.dpad_right) {
                v4b.setPosition(0.7);
            }
            if (gamepad1.dpad_up) {
                leftLinkage.setTargetPosition(leftLinkage.getTargetPosition()-2);
                rightLinkage.setTargetPosition(rightLinkage.getTargetPosition()+2);
                leftLinkage.setPower(1);
                rightLinkage.setPower(1);
                leftLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (gamepad1.dpad_down) {
                leftLinkage.setTargetPosition(leftLinkage.getTargetPosition()+2);
                rightLinkage.setTargetPosition(rightLinkage.getTargetPosition()-2);
                leftLinkage.setPower(1);
                rightLinkage.setPower(1);
                leftLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            */
        }
    }
}