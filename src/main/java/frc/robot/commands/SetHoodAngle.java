// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class SetHoodAngle extends CommandBase {
  
  double val = 0;
  /** Creates a new SetHoodAngle. */
  public SetHoodAngle(double val) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.val = val;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Shooter.setPointHood = val;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.setPointHood = 0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
