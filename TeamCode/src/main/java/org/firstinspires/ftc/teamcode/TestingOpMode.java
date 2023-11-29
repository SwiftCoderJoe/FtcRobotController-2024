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

        while(opModeIsActive()) {
            drive();
        }

    }

    void drive() {
        float y = -gamepad1.left_stick_y;
        float x = gamepad1.left_stick_x;
        float rx = gamepad1.right_stick_x;

        double scalar = (1 - 0.5 * gamepad1.right_trigger) / Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        bot.rightFrontMotor.setPower((-rx + y - x) * scalar);
        bot.leftFrontMotor.setPower((rx + y + x) * scalar);
        bot.rightRearMotor.setPower((-rx + y + x) * scalar);
        bot.leftRearMotor.setPower((rx + y - x) * scalar);
    }
}
