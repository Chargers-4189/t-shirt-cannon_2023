// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANMotorControl;

public class DriveTrain extends Command {
  private CANMotorControl m_subsystem;
  private XboxController controller;
  boolean pause;
  /** Creates a new DriveTrain. */
  public DriveTrain(CANMotorControl motorControl, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_subsystem = motorControl;
    this.controller = controller;
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pause = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!pause){
      m_subsystem.drive(controller.getLeftY()*-1, controller.getRightX()*-1);
    }else{
      m_subsystem.drive(0,0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
