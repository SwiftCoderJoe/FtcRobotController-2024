package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Far Autonomous: BLUE", group="Skula and Joe")
public class AutoFarBlue extends BaseAuto {

    @Override
    public void runOpMode() {
        Robot bot = new Robot(hardwareMap);
        setRobot(bot); // Set the Robot instance

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        bot.setPanStandardPosition(); // Call setPanStandardPosition()

        bot.lift.setPower(-.5);

        driveForward(0.02);
        spinLeft(.78);
        driveForward(3.0);
        bot.lift.setPower(0);
        stopRobot();

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

        while(opModeIsActive()) { }
    }
}
