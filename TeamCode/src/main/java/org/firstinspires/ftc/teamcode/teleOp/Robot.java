package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

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

    public final Servo planeLauncher;
    public final Servo planeShield;
    public final Servo goat;
    public final ColorSensor colorSensor;


    public Robot(HardwareMap hardwareMap) {

        // Drive motors
        rightFrontMotor = hardwareMap.get(DcMotor.class, "fr");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "fl");
        rightRearMotor = hardwareMap.get(DcMotor.class, "br");
        leftRearMotor = hardwareMap.get(DcMotor.class, "bl");

        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);

        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Other motors
        blackWheels = hardwareMap.dcMotor.get("black_wheels"); // expansion hub port 2
        blackWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        linearSlide = hardwareMap.dcMotor.get("linear_slide"); // expansion hub port 0
        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Servos
        topOfSlide = hardwareMap.servo.get("top_of_slide"); // expansion port 1
        handleRotator = hardwareMap.servo.get("handle_rotator"); // expansion port 5
        planeLauncher = hardwareMap.servo.get("plane_launcher"); // expansion port 2
        planeShield = hardwareMap.servo.get("plane_shield"); // control hub port 0
        goat = hardwareMap.servo.get("goat"); // expansion port ???

        // Sensors
        colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");
    }

    void setPanStandardPosition() {
        this.handleRotator.setPosition(0.15);
        this.topOfSlide.setPosition(0.23);
    }


}
