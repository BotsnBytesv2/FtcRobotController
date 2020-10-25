/*
Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *23
 * Redistributions in binary form must reproduce  /`the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.android.AndroidTextToSpeech;
/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 **/


@TeleOp(name = "AllDirection", group = "Concept")


public class AllDirection extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Working");
        telemetry.update();



        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");



        frontleftmotor.setPower(0.0);
        frontrightmotor.setPower(0.0);
        backleftmotor.setPower(0.0);
        backrightmotor.setPower(0.0);




        frontleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        
        /*output1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        output2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/



        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        double constant1 = 0.8;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            int changeMove = 0;

            //This moves the base
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = -r * Math.sin(robotAngle) + rightX;
            final double v2 = r * Math.cos(robotAngle) + rightX;
            final double v3 = -r * Math.cos(robotAngle) + rightX;
            final double v4 = r * Math.sin(robotAngle) + rightX;

            frontleftmotor.setPower(-v1*constant1);
            frontrightmotor.setPower(-v2*constant1);
            backleftmotor.setPower(-v3*constant1);
            backrightmotor.setPower(-v4*constant1);

            if(gamepad1.x){
                changeMove++;
            }

            if((changeMove % 2) == 0){
                frontleftmotor.setPower(-v1*constant1);
                frontrightmotor.setPower(-v2*constant1);
                backleftmotor.setPower(-v3*constant1);
                backrightmotor.setPower(-v4*constant1);
            }
            else{
                frontleftmotor.setPower(v1*constant1);
                frontrightmotor.setPower(v2*constant1);
                backleftmotor.setPower(v3*constant1);
                backrightmotor.setPower(v4*constant1);
            }


            int changeInput = 0;
            if(gamepad1.a){
                changeInput++;
            }
            if((changeInput % 2) != 0){
                //input.setPower(1.0);
            }
            else{
                //input.setPower(0.0);
            }

            int changeOutput = 0;
            if(gamepad1.b){
                changeOutput++;
            }
            if((changeOutput % 2) != 0){
                //output.setPower(1.0)
            }
            else{
                //output.setPower(0.0);
            }

            



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Mode", constant1);
            //telemetry.addData("Motors", "one (%.2f), two (%.2f), servoone (%.2f), servotwo (%.2f)", onePower, twoPower, servo1pos, servo2pos);
            telemetry.update();
        }
    }
}