// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SetRightClimber extends CommandBase {
  /** Creates a new SetRightClimber. */
  double percent;
  public SetRightClimber(double percent) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.percent = percent;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.climber.setRightOutput(percent);
  }

  @Override
  public void end(boolean interrupted) {
      // TODO Auto-generated method stub
      RobotContainer.climber.setRightOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
