// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CanonControl;

public class fire extends Command {
  /** Creates a new fire. */
  private CanonControl canon;
  private double endTime;
  private double time;
  private boolean isFinished;
  private double delay;
  public fire(CanonControl canon, double delay) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.delay = delay;
    this.canon = canon;
    addRequirements(canon);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    isFinished = false;
    endTime = Timer.getFPGATimestamp()+delay;
    canon.openSolonoid();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time = Timer.getFPGATimestamp();
    if(time >= endTime){
      isFinished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    canon.closeSolonoid();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
