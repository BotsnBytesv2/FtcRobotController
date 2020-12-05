package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import static org.firstinspires.ftc.teamcode.tensorFlowInit.initTfod;
import static org.firstinspires.ftc.teamcode.tensorFlowInit.initVuforia;
import static org.firstinspires.ftc.teamcode.tensorFlowInit.tfod;


@Autonomous(name = "Auto1205", group = "Concept")

public class Auto1205 extends LinearOpMode {





    private ElapsedTime runtime = new ElapsedTime();


    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;
    private DcMotor input = null;
    private DcMotor output = null;
    private DcMotor wobbleArm = null;
    private Servo topClaw = null;
    private Servo bottomClaw = null;
    private Servo transfer = null;
    public int numberOfRings;



    @Override
    public void runOpMode() {
        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        input = hardwareMap.get(DcMotor.class, "input");
        output = hardwareMap.get(DcMotor.class, "output");
        wobbleArm = hardwareMap.get(DcMotor.class, "wobbleArm");
        transfer = hardwareMap.get(Servo.class, "transferServo");
        topClaw = hardwareMap.get(Servo.class, "topClaw");
        bottomClaw = hardwareMap.get(Servo.class, "bottomClaw");






        frontleftmotor.setPower(0.0);
        frontrightmotor.setPower(0.0);
        backleftmotor.setPower(0.0);
        backrightmotor.setPower(0.0);


        // transfer.setPosition((-0.5));
        topClaw.setPosition(0.23);
        bottomClaw.setPosition(0.4);
        transfer.setPosition(0.8);

        initVuforia();
        initTfod();
        if (tfod != null) {
            tfod.activate();
        }
        waitForStart();
        move(5, "f");
        move(25, "f");
        move(13, "r");


        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        numberOfRings= updatedRecognitions.size();
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                            double area = recognition.getTop()*recognition.getLeft();
                            telemetry.addData(String.format(" area (%d)",i), "%.03",area);
                        }
                        telemetry.update();
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
        if(numberOfRings == 0){
            path1();
        }
        if(numberOfRings == 1){
            path2();
        }
        if(numberOfRings == 4){
            path3();
        }



    }


    private void stopMotors(){
        frontrightmotor.setPower(0);
        frontleftmotor.setPower(0);
        backrightmotor.setPower(0);
        backleftmotor.setPower(0);

    }

    private void moveForward(long sleep){
        frontrightmotor.setPower(-0.4);
        frontleftmotor.setPower(0.425);
        backrightmotor.setPower(-0.4);
        backleftmotor.setPower(0.425);
        sleep(sleep);

    }
    private void moveBackward(long sleep){
        frontrightmotor.setPower(0.4);
        frontleftmotor.setPower(-0.425);
        backrightmotor.setPower(0.4);
        backleftmotor.setPower(-0.425);
        sleep(sleep);

    }



    private void spinleft(long sleep){
        frontrightmotor.setPower(-0.3);
        frontleftmotor.setPower(-0.31);
        backrightmotor.setPower(-0.3);
        backleftmotor.setPower(-0.31);
        sleep(sleep);
    }

    private void spinright(long sleep){
        frontrightmotor.setPower(0.4);
        frontleftmotor.setPower(0.42);
        backrightmotor.setPower(0.4);
        backleftmotor.setPower(0.42);
        sleep(sleep);
    }

    private void straferight(long sleep) {
        frontleftmotor.setPower(0.515);
        backleftmotor.setPower(-0.5);
        frontrightmotor.setPower(0.515);
        backrightmotor.setPower(-0.5);
        sleep(sleep);
    }

    private void strafeleft(long sleep) {
        frontleftmotor.setPower(-0.515);
        backleftmotor.setPower(0.5);
        frontrightmotor.setPower(-0.515);
        backrightmotor.setPower(0.5);
        sleep(sleep);
    }

    private void path1(){
        move(25, "l");
        move(42, "f");
        wobbleArm.setPower(-0.6);
        sleep(125);
        wobbleArm.setPower(0.0);
        topClaw.setPosition(0.6);
        bottomClaw.setPosition(0.1);
        move(25, "r");
        move(11, "b");
        output.setPower(1.0);
        transfer.setPosition(0.7);
        sleep(50);
        transfer.setPosition(0.8);
        transfer.setPosition(0.7);
        sleep(50);
        transfer.setPosition(0.8);
        sleep(50);
        output.setPower(0.0);
        move(11, "f");

    }
    private void path2(){
        move(65, "f");
        wobbleArm.setPower(-0.6);
        sleep(125);
        wobbleArm.setPower(0.0);
        topClaw.setPosition(0.6);
        bottomClaw.setPosition(0.1);
        move(35, "b");
        output.setPower(1.0);
        transfer.setPosition(0.7);
        sleep(50);
        transfer.setPosition(0.8);
        transfer.setPosition(0.7);
        sleep(50);
        transfer.setPosition(0.8);
        sleep(50);
        output.setPower(0.0);
        move(11, "f");

    }
    private void path3(){
        move(25, "l");
        move(90, "f");
        wobbleArm.setPower(-0.6);
        sleep(125);
        wobbleArm.setPower(0.0);
        topClaw.setPosition(0.6);
        bottomClaw.setPosition(0.1);
        move(25, "r");
        move(60, "b");
        output.setPower(1.0);
        transfer.setPosition(0.7);
        sleep(50);
        transfer.setPosition(0.8);
        transfer.setPosition(0.7);
        sleep(50);
        transfer.setPosition(0.8);
        sleep(50);
        output.setPower(0.0);
        move(11, "f");

    }


    private void move(double distance, String direction){
        if(direction == "f"){
            moveForward((long)(Math.rint(distance)*25));
        }
        else if(direction == "b"){
            moveBackward((long)(Math.rint(distance)* 30));
        }
        else if(direction == "l"){
            strafeleft((long)(Math.rint(distance)* 43));
        }
        else if(direction == "r"){
            straferight((long)(Math.rint(distance)* 43));
        }
        else{
        }
        stopMotors();
    }
    private void turn(String direction, int degrees){
        if(direction == "l"){
            spinleft((long)(Math.rint(degrees * 11)));
        }
        if(direction == "r"){
            spinright((long)(Math.rint(degrees * 11)));
        }
        stopMotors();
    }

}