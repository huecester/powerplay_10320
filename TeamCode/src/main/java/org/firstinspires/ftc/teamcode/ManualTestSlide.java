package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Test slide manually")
public class ManualTestSlide extends LinearOpMode {
	@Override
	public void runOpMode() {
		Slide slide = new Slide(hardwareMap, telemetry, false);
		Claw claw = new Claw(hardwareMap, telemetry);
		GamepadEx p1 = new GamepadEx();

		waitForStart();

		while (opModeIsActive()) {
			p1.tick(gamepad1);
			slide.control(p1);
			claw.control(p1);
		}
	}
}
