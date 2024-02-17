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

@Autonomous(name="Close Autonomous: BLUE", group="Skula and Joe")
public class AutoCloseBlue extends BaseAuto {
    @Override
    public void runOpMode() {
        Robot bot = new Robot(hardwareMap);
        setRobot(bot); // Set the Robot instance

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        driveForward(.8);
        spinLeft(.8);
        driveForward(.4);
        stopRobot();

        bot.linearSlide.setPower(-0.9);
        bot.blackWheels.setPower(0.4);
        sleep(2000);
        bot.linearSlide.setPower(0);
        bot.blackWheels.setPower(0);

        bot.topOfSlide.setPosition(0.52);
        while (opModeIsActive() && bot.colorSensor.red() < 30 && bot.colorSensor.green() < 30 && bot.colorSensor.blue() < 30 && !gamepad1.left_bumper) {
            bot.rightFrontMotor.setPower(0.2);
            bot.leftFrontMotor.setPower(0.2);
            bot.rightRearMotor.setPower(0.2);
            bot.leftRearMotor.setPower(0.2);
        }
        bot.rightFrontMotor.setPower(0);
        bot.leftFrontMotor.setPower(0);
        bot.rightRearMotor.setPower(0);
        bot.leftRearMotor.setPower(0);

        bot.handleRotator.setPosition(0.95);
        sleep(1500);
        bot.setPanStandardPosition();
        sleep(500);


        spinLeft(0.8);
        sleep(100);
        driveForward(0.8);
        stopRobot();

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

        while(opModeIsActive()) { }
    }
}
