// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CANMotorControl;
import frc.robot.subsystems.Canon;

public class DriveTrain extends CommandBase {
  private CANMotorControl m_subsystem;
  private Canon canon;
  private XboxController controller;
  /** Creates a new DriveTrain. */
  public DriveTrain(CANMotorControl motorControl, XboxController controller, Canon canon) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_subsystem = motorControl;
    this.controller = controller;
    this.canon = canon;
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.drive(controller.getLeftY()*-1, controller.getLeftX()*-1);
    canon.move(controller.getRightY()*-1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.drive(0, 0);
    canon.move(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
