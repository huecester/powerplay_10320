package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
	private final ServoImplEx left;
	private final ServoImplEx right;

	public Claw(HardwareMap hardwareMap, Telemetry telemetry) {
		// TODO: other things
		telemetry.log().add("Setting up claw hardware...");

		left = hardwareMap.get(ServoImplEx.class, "clawLeft");
		right = hardwareMap.get(ServoImplEx.class, "clawRight");

		telemetry.log().add("Claw is ready.");
	}

	// TODO: control claw
}
