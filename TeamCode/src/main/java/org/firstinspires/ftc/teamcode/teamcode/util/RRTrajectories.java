package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class RRTrajectories extends LinearOpMode{
    @Override
    public void runOpMode(){
        SampleMecanumDrive drivetrain = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        Pose2d startPose = new Pose2d(-60, 48, 0.17453292519943295);
        drivetrain.setPoseEstimate(startPose);

        Trajectory traj1 = drivetrain.trajectoryBuilder(startPose)
                .splineTo(new Vector2D(-30, 35), 0)
                .build();

        Trajectory traj2 = drivetrain.trajectoryBuilder(traj1.end())
                .splineTo(new Vector2D(12, 60), 0)
                .build();

        Trajectory traj3 = drivetrain.trajectoryBuilder(traj2.end())
                .splineTo(new Vector2D(0, 35), 0)
                .build();

        Trajectory traj4 = drivetrain.trajectoryBuilder(traj3.end())
                .splineTo(new Vector2D(11, 35), 0)
                .build();

        drivetrain.followTrajectory(traj1);
        drivetrain.followTrajectory(traj2);
        drivetrain.followTrajectory(traj3);
        drivetrain.followTrajectory(traj4);
    }
}