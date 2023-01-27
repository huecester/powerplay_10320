package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {
	private final DcMotorEx motor;

	public Slide(HardwareMap hardwareMap, Telemetry telemetry) {
		// TODO: other things
		telemetry.log().add("Setting up slide hardware...");
		motor = hardwareMap.get(DcMotorEx.class, "slide");
		telemetry.log().add("Slide is ready.");
	}

	// TODO: control slide
}
