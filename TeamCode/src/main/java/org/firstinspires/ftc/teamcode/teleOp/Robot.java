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

    // Servos
    public final Servo topOfSlide;
    public final Servo handleRotator;


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

        // Other motors
        blackWheels = hardwareMap.dcMotor.get("black_wheels"); // expansion hub port 1
        blackWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        linearSlide = hardwareMap.dcMotor.get("linear_slide"); // expansion hub port ?
        blackWheels.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Servos
        topOfSlide = hardwareMap.servo.get("top_of_slide"); // expansion port 0
        handleRotator = hardwareMap.servo.get("handle_rotater"); // Horizontal Rotator of the Arm (connection TBD)
    }
}
