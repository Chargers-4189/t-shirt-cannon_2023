// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Canon extends SubsystemBase {
  /** Creates a new Canon. */
  private VictorSPX canon = new VictorSPX(5);
  private VictorSPX solonoid = new VicorSPX(0);
  public Canon() {
    
  }
  public void fire (boolean f) {
    solonoid.set(VictorSPXControlMode.PercentOutput,f);
  }
  
  public void move(double x){
    canon.set(VictorSPXControlMode.PercentOutput,x);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
