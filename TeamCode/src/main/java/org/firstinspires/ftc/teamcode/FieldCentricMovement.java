package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name="Field Centric Movement", group="Joe Himself")
public class FieldCentricMovement extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("fl");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("bl");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("fr");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("br");
        // DcMotor leftLinkage = hardwareMap.dcMotor.get("left_linkage");
        // DcMotor rightLinkage = hardwareMap.dcMotor.get("right_linkage");
        // Servo v4b = hardwareMap.servo.get("v4b");

        //DcMotor blackWheels = hardwareMap.dcMotor.get("black_wheels"); // port TBD
        //DcMotor linearSlide = hardwareMap.dcMotor.get("linear_slide"); // port TBD
        //DcMotor verticalSlide = hardwareMap.dcMotor.get("vertical_slide"); // port TBD, this one isn't built yet
        //Servo topOfSlide = hardwareMap.servo.get("top_of_slide"); // port TBD
        //Servo handleRotater = hardwareMap.servo.get("handle_rotater"); // Horizontal Rotater of the Arm (connection TBD)


        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        /*leftLinkage.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLinkage.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftLinkage.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLinkage.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);*/

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match the robot robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            double speedVar = 1.25-gamepad1.right_trigger;
            // This button choice was made so that it is hard to hit on accident,
            // it can be freely changed based on preference.
            // The equivalent button is start on Xbox-style controllers
            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeftMotor.setPower(speedVar*frontLeftPower);
            backLeftMotor.setPower(speedVar*backLeftPower);
            frontRightMotor.setPower(speedVar*frontRightPower);
            backRightMotor.setPower(speedVar*backRightPower);
            /*
            if (gamepad1.y) {
                v4b.setPosition(0.75);
                leftLinkage.setTargetPosition(-180);
                rightLinkage.setTargetPosition(180);
                leftLinkage.setPower(1);
                rightLinkage.setPower(1);
                leftLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (gamepad1.b) {
                v4b.setPosition(0.75);
                leftLinkage.setTargetPosition(-120);
                rightLinkage.setTargetPosition(120);
                leftLinkage.setPower(1);
                rightLinkage.setPower(1);
                leftLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (gamepad1.a) {
                v4b.setPosition(0.7);
                leftLinkage.setTargetPosition(0);
                rightLinkage.setTargetPosition(0);
                leftLinkage.setPower(0.5);
                rightLinkage.setPower(0.5);
                leftLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightLinkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            if (gamepad1.left_bumper) {
                claw.setPosition(1);
            }
            if (gamepad1.left_trigger > 0.75) {
                claw.setPosition(0.8);
            }
            // Horizontal Spinning of Paddle
            if (gamepad1.right_bumper) {
                handleRotater.setPower(-0.5);
            }
            if (gamepad1.right_trigger > 0.75) {
                handleRotater.setPower(0.5)
            }

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