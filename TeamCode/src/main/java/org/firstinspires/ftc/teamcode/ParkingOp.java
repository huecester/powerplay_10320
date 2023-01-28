package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.List;

@Autonomous(name = "Parking", group = "Basic")
public class ParkingOp extends LinearOpMode {
	@Override
	public void runOpMode() throws InterruptedException {
		telemetry.log().add("Initializing...");

		telemetry.log().add("Activating cache...");
		List<LynxModule> hubs = hardwareMap.getAll(LynxModule.class);
		for (LynxModule hub : hubs) {
			hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
		}

		telemetry.log().add("Setting up drive...");
		Drive drive = new Drive(hardwareMap, telemetry, Drive.Flag.AUTON);

		telemetry.log().add("Initialized.");
		waitForStart();

		drive.driveAtAngle(0.25, 0, 0, false);
		Thread.sleep(2500);
	}
}
