package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Simple Testing Mode", group="Joe Himself")
public class TestingOpMode extends LinearOpMode {
    // Store a simple DC Motor
    private Robot bot = null;

    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            dualStickDrive();
        }

        telemetry.update();

    }
    
    void singleStickDrive() {
        float y = -gamepad1.left_stick_y;
        float x = gamepad1.left_stick_x;
        float rx = gamepad1.right_stick_x;

        telemetry.addData("y value", y);

        double scalar = (1 - 0.5 * gamepad1.right_trigger) / Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        bot.rightFrontMotor.setPower((-rx + y - x) * scalar);
        bot.leftFrontMotor.setPower((rx + y + x) * scalar);
        bot.rightRearMotor.setPower((-rx + y + x) * scalar);
        bot.leftRearMotor.setPower((rx + y - x) * scalar);
    }

    void dualStickDrive() {
        float leftY = -gamepad1.left_stick_y;
        float leftX = gamepad1.left_stick_x;
        double scalar = 1 - 0.5 * gamepad1.right_trigger;

        double leftScalar = scalar / Math.max(Math.abs(leftY) + Math.abs(leftX), 1);
        bot.leftFrontMotor.setPower((leftY - leftX) * leftScalar);
        bot.leftRearMotor.setPower((leftY + leftX) * leftScalar);
        
        float rightY = -gamepad1.right_stick_y;
        float rightX = gamepad1.right_stick_x;

        double rightScalar = scalar / Math.max(Math.abs(rightY) + Math.abs(rightX), 1);
        bot.leftFrontMotor.setPower((rightY - rightX) * rightScalar);
        bot.leftRearMotor.setPower((rightY + rightX) * rightScalar);

        telemetry.addData("left y value", leftY);
    }
}
