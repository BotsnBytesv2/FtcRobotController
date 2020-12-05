package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;


@Autonomous(name = "Auto1205", group = "Concept")

public class Auto1205 extends LinearOpMode {





    private ElapsedTime runtime = new ElapsedTime();


    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;




    @Override
    public void runOpMode() {
        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        //frontleftmotor.setDirection(DcMotor.Direction.REVERSE);
        //backleftmotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();


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


    private void DistanceFormula(double x1, double y1 , double x2, double y2, String direction){
        double x_final = Math.pow((x2-x1), 2);
        double y_final = Math.pow((y2-y1), 2);
        double distance = Math.sqrt(x_final+y_final);
        if(direction == "forward"){
            moveForward((long)(Math.rint(distance)*25));
        }
        else if(direction == "backward"){
            moveBackward((long)(Math.rint(distance)* 30));
        }
        else if(direction == "left"){
            strafeleft((long)(Math.rint(distance)* 43));
        }
        else if(direction == "right"){
            straferight((long)(Math.rint(distance)* 43));
        }
        else{
        }
        stopMotors();
    }
    private void turn(String direction, int degrees){
        if(direction == "left"){
            spinleft((long)(Math.rint(degrees * 11)));
        }
        if(direction == "right"){
            spinright((long)(Math.rint(degrees * 11)));
        }
        stopMotors();
    }

}