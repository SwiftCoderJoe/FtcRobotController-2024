package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
    // Motors
    public final DcMotor rightFrontMotor;
    public final DcMotor leftFrontMotor;
    public final DcMotor rightRearMotor;
    public final DcMotor leftRearMotor;
    public final DcMotor blackWheels;
    public final DcMotor linearSlide;
    public final DcMotor lift;

    // Servos
    public final Servo topOfSlide;
    public final Servo handleRotator;

    // Front left wheel is 312 rpm
    // 312 / 435 = .7172
    // Therefore we want to scale all motors except front left by this amount, until this is rectified
    public final double SPEED_CORRECTION_FACTOR = 0.7172;


    public Robot(HardwareMap hardwareMap) {

        // Drive motors
        rightFrontMotor = hardwareMap.get(DcMotor.class, "fr");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "fl");
        rightRearMotor = hardwareMap.get(DcMotor.class, "br");
        leftRearMotor = hardwareMap.get(DcMotor.class, "bl");

        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);

        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Other motors
        blackWheels = hardwareMap.dcMotor.get("black_wheels"); // expansion hub port 2
        blackWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        linearSlide = hardwareMap.dcMotor.get("linear_slide"); // expansion hub port 0
        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Servos
        topOfSlide = hardwareMap.servo.get("top_of_slide"); // expansion port 0
        handleRotator = hardwareMap.servo.get("handle_rotator"); // expansion port 1
    }
}
