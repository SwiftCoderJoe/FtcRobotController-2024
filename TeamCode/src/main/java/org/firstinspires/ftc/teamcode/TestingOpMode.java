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
        // We are negating the y values because up is negative on the joysticks (from what I remember?).
        double leftPower = -gamepad1.left_stick_y;
        double rightPower = -gamepad1.right_stick_y;

        bot.setLeftPower(leftPower);
        bot.setRightPower(rightPower);
    }
}
