// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shooter.ShooterState;
import frc.robot.subsystems.Turret.TurretState;

public class SpinShooterWithHood extends CommandBase {
  /** Creates a new SpinShooterWithHood. */
  public SpinShooterWithHood() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Shooter.hoodControllerHasBeenReset = false;
    if (!RobotContainer.turret.getState().equals(TurretState.MANUAL)) {
      RobotContainer.turret.setState(TurretState.TRACKING);
    }
      RobotContainer.shooter.setState(ShooterState.SHOOTING);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.hoodControllerHasBeenReset = false;
    if (!RobotContainer.turret.getState().equals(TurretState.MANUAL)) {
      RobotContainer.turret.setState(TurretState.DRIVE);
    }
      RobotContainer.shooter.setState(ShooterState.IDLE);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
