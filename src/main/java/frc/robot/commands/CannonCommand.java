// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.Map;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CanonControl;

public class CannonCommand extends Command {
  /** Creates a new CannonControl. */
  private XboxController controller;
  private CanonControl m_subsystem;
  private DriveTrain drive;
  private double time;
  private double delay;
  private double endtime;
  private GenericEntry delaybox;
  
  private boolean fire;
  public CannonCommand(CanonControl canon, XboxController xboxController, DriveTrain driveTrain, ShuffleboardTab tab) {
    // Use addRequirements() here to declare subsystem dependencies.
    delaybox = tab.add("Delay", delay).withWidget(BuiltInWidgets.kNumberSlider).withProperties(Map.of("Min",0,"Max",1)).getEntry();
    controller = xboxController;
    m_subsystem = canon;
    drive = driveTrain;
    addRequirements(canon);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    fire = false;
    delay=.5;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time = Timer.getFPGATimestamp();
    if(!fire){
      m_subsystem.move(controller.getRightY()*-1);
      if(controller.getLeftTriggerAxis() == 1 && controller.getRightTriggerAxis() == 1){
        drive.pause = true;
        m_subsystem.move(0);
        fire = true;
        endtime = time+delaybox.getDouble(delay);
      }
    }else if(time<endtime){
      m_subsystem.openSolonoid();
    }else{
      m_subsystem.closeSolonoid();
      if(controller.getLeftTriggerAxis() == 0 && controller.getRightTriggerAxis() == 0){
        fire=false;
        drive.pause = false;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.move(0);
    m_subsystem.closeSolonoid();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
