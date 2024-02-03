package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Linear Slide Homing", group="Skula and Joe")
public class LinearSlideHoming extends LinearOpMode {

    // Button states
    boolean slideMoving = false;
    boolean liftMoving = false;

    @Override
    public void runOpMode() throws InterruptedException {
        Robot bot = new Robot(hardwareMap);
        bot.linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            // Linear Slide Control
            if (gamepad1.right_trigger > .1) {
                // UP
                slideMoving = true;
                bot.linearSlide.setPower(-gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > .1) {
                // DOWN
                slideMoving = true;
                bot.linearSlide.setPower(gamepad1.left_trigger);
            } else if (slideMoving) {
                slideMoving = false;
                telemetry.addData("Linear Slide Position", bot.linearSlide.getCurrentPosition());
                telemetry.update();
                bot.linearSlide.setPower(0);
            }
            // Lift Control
            if (gamepad1.dpad_up) {
                // UP
                liftMoving = true;
                bot.linearSlide.setPower(-gamepad1.right_trigger);
            } else if (gamepad1.dpad_down) {
                // DOWN
                liftMoving = true;
                bot.linearSlide.setPower(gamepad1.left_trigger);
            } else if (liftMoving) {
                liftMoving = false;
                telemetry.addData("Lift Position", bot.lift.getCurrentPosition());
                telemetry.update();
                bot.linearSlide.setPower(0);
            }
        }
    }
}
