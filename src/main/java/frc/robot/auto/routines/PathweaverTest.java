// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto.routines;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.auto.commands.SetIndexingFeedMode;
import frc.robot.auto.commands.SetIndexingIdleMode;
import frc.robot.auto.commands.SetIndexingIntakeMode;
import frc.robot.auto.commands.SetShooterIdleMode;
import frc.robot.auto.commands.SetShooterSpinUpMode;
import frc.robot.auto.commands.SetTurretDriveMode;
import frc.robot.auto.commands.SetTurretTrackMode;
import frc.robot.auto.commands.StopTrajectory;
import frc.robot.subsystems.Drive;


public class PathweaverTest extends SequentialCommandGroup {

  String GenericBallJSON = "paths/ToGenericBall.wpilib.json";
  
  public PathweaverTest(Drive s_Swerve) {
    
    var thetaController = new ProfiledPIDController(
      Constants.AutoConstants.KP_THETA_CONTROLLER, 0, 0,
      Constants.AutoConstants.K_THETA_CONTROLLER_CONSTRAINTS);
      thetaController.enableContinuousInput(-Math.PI, Math.PI);

    SwerveControllerCommand grabGenericBallCommand = new SwerveControllerCommand(
      ConvertTrajectoryFromFile(GenericBallJSON),
      s_Swerve::getPose,
      Constants.Swerve.SWERVE_KINEMATICS,
      new PIDController(Constants.AutoConstants.KPX_CONTROLLER, 0, 0),
      new PIDController(Constants.AutoConstants.KPY_CONTROLLER, 0, 0),
      thetaController,
      Drive.getSwerveHeadingSupplier(0),
      s_Swerve::setModuleStates,
      s_Swerve);


      
    addCommands(
      new InstantCommand(() -> s_Swerve.resetOdometry(new Pose2d())),
      // new SetTurretTrackMode(),
      // new SetShooterSpinUpMode(2350),
      // new SetIndexingIntakeMode(),
      grabGenericBallCommand,
      new StopTrajectory()
      // new WaitCommand(0.3),
      // new SetIndexingFeedMode(),
      // new WaitCommand(0.5),
      // new SetTurretDriveMode(),
      // new SetIndexingIdleMode(),
      // new SetShooterIdleMode()
    );
  }

  public static Trajectory ConvertTrajectoryFromFile(String JSON) {
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(JSON);
      return TrajectoryUtil.fromPathweaverJson(trajectoryPath);
      
   } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + JSON, ex.getStackTrace());
   }
   return new Trajectory();
  }

}
