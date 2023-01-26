package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

@TeleOp(name = "Basic driving", group = "Driver")
public class BasicDriveOp extends LinearOpMode {
	@Override
	public void runOpMode() {
		telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
		telemetry.log().add("Initializing...");

		telemetry.log().add("Activating cache...");
		List<LynxModule> hubs = hardwareMap.getAll(LynxModule.class);
		for (LynxModule hub : hubs) {
			hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
		}

		telemetry.log().add("Setting up drive...");
		Drive drive = new Drive(hardwareMap, telemetry);

		telemetry.log().add("Initialized.");
		waitForStart();

		while (opModeIsActive()) {
			drive.drive(gamepad1);
			telemetry.update();
		}
	}
}
