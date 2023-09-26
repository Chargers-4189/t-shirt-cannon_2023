// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANMotorControl extends SubsystemBase {
  /** Creates a new CANMotorControl. */
  private CANSparkMax rmaster = new CANSparkMax(4, MotorType.kBrushed);
  private CANSparkMax lmaster = new CANSparkMax(3,MotorType.kBrushed);
  private CANSparkMax lslave = new CANSparkMax(2, MotorType.kBrushed);
  private CANSparkMax rslave = new CANSparkMax(1,MotorType.kBrushed);
  private DifferentialDrive drive;

  public CANMotorControl() {
    rslave.follow(rmaster);
    lslave.follow(lmaster);
    drive = new DifferentialDrive(lmaster,rmaster);
  }
  public void drive(double y, double x){
    drive.arcadeDrive(x, y);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
