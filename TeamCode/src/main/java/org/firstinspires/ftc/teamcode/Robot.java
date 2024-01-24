package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    public final DcMotor rightFrontMotor;
    public final DcMotor leftFrontMotor;
    public final DcMotor rightRearMotor;
    public final DcMotor leftRearMotor;

//    public final DcMotor leftElevator;
//
//    public final DcMotor rightElevator;

    public Robot(HardwareMap hardwareMap) {

        // Drive motors

        rightFrontMotor = hardwareMap.get(DcMotor.class, "fr");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "fl");
        rightRearMotor = hardwareMap.get(DcMotor.class, "br");
        leftRearMotor = hardwareMap.get(DcMotor.class, "bl");

        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);

        // Elevator

//        leftElevator = hardwareMap.get(DcMotor.class, "left_linkage");
//        rightElevator = hardwareMap.get(DcMotor.class, "right_linkage");
//
//        leftElevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightElevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        leftElevator.setDirection(DcMotor.Direction.FORWARD);
//        rightElevator.setDirection(DcMotor.Direction.REVERSE);
//
//        leftElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightElevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Method to set power for the left side
    public void setLeftPower(double power) {
        leftFrontMotor.setPower(power);
        leftRearMotor.setPower(power);
    }

    // Method to set power for the right side
    public void setRightPower(double power) {
        rightFrontMotor.setPower(power);
        rightRearMotor.setPower(power);
    }
}
