package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * This OpMode illustrates the concept of driving a path based on time.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: RobotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 0.5 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backward for 1 Second
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

// Please place the robot so the left wheel is aligned with the left of the three strips

@Autonomous(name="Far Autonomous: BLUE", group="Skula and Joe")
public class AutoFarBlue extends LinearOpMode {

    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;

    private ElapsedTime     runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        Robot bot = new Robot(hardwareMap);

        bot.leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
        bot.leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        bot.rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        bot.rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Step 1:  Drive forward for 0.02 seconds
        bot.leftRearMotor.setPower(FORWARD_SPEED);
        bot.leftFrontMotor.setPower(FORWARD_SPEED);
        bot.rightRearMotor.setPower(FORWARD_SPEED);
        bot.rightFrontMotor.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.02)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 2:  Spin left for 1.22 seconds
        bot.leftRearMotor.setPower(-TURN_SPEED);
        bot.leftFrontMotor.setPower(-TURN_SPEED);
        bot.rightRearMotor.setPower(TURN_SPEED);
        bot.rightFrontMotor.setPower(TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.22)) {
            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 3:  Drive Forward for 4.6 Seconds
        bot.leftRearMotor.setPower(FORWARD_SPEED);
        bot.leftFrontMotor.setPower(FORWARD_SPEED);
        bot.rightRearMotor.setPower(FORWARD_SPEED);
        bot.rightFrontMotor.setPower(FORWARD_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 4.6)) {
            telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        // Step 4:  Stop
        bot.leftRearMotor.setPower(0);
        bot.leftFrontMotor.setPower(0);
        bot.rightRearMotor.setPower(0);
        bot.rightFrontMotor.setPower(0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}
