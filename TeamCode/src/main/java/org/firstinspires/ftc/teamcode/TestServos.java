package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

@Autonomous(name = "Test servos")
public class TestServos extends LinearOpMode {
	@Override
	public void runOpMode() throws InterruptedException {
		ServoImplEx left = hardwareMap.get(ServoImplEx.class, "clawLeft");
		ServoImplEx right = hardwareMap.get(ServoImplEx.class, "clawRight");

		left.setDirection(Servo.Direction.FORWARD);
		right.setDirection(Servo.Direction.REVERSE);

		left.scaleRange(0.0, 1.0);
		right.scaleRange(0.0, 1.0);

		waitForStart();

		for (double i = 0.0; i <= 1.0; i += 0.1) {
			left.setPosition(i);
			right.setPosition(i);
			Thread.sleep(1000);
		}
	}
}
