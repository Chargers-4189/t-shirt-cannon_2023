// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

public class DriveTrain extends SubsystemBase {
  private Talon lmaster = new Talon(2);
  private Talon rmaster = new Talon(3);
  private Talon lfollow = new Talon(0);
  private Talon rfollow = new Talon(1);
  private MotorControllerGroup left = new MotorControllerGroup(lmaster, lfollow);
  private MotorControllerGroup right = new MotorControllerGroup(rmaster, rfollow);
  private DifferentialDrive drive;
  
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    drive = new DifferentialDrive(left, right);
  }

  public void drive(double y, double x) {
    drive.tankDrive(.3, .3);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}