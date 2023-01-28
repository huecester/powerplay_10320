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

		// TODO: fine-tune button/toggle
		telemetry.log().add("Setting up drive...");
		Drive drive = new Drive(hardwareMap, telemetry, Drive.Flag.DISABLE_HEADING);

		// TODO: customizable controls
		telemetry.log().add("Setting up claw...");
		Claw claw = new Claw(hardwareMap, telemetry);

		telemetry.log().add("Setting up slide...");
		Slide slide = new Slide(hardwareMap, telemetry);

		telemetry.log().add("Initialized.");
		waitForStart();

		GamepadEx p1 = new GamepadEx();

		while (opModeIsActive()) {
			p1.tick(gamepad1);

			drive.drive(p1);
			claw.control(p1);
			slide.control(p1);

			telemetry.update();
		}
	}
}
