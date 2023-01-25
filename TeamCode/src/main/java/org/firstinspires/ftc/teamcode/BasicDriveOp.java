package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "Basic driving", group = "Driver")
public class BasicDriveOp extends LinearOpMode {
	static final double STRAFING_MODIFIER = 1.1;

	@Override
	public void runOpMode() throws InterruptedException {
		telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
		telemetry.log().add("Initializing...");

		telemetry.log().add("Setting up hardware...");
		DcMotorEx frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
		DcMotorEx backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
		DcMotorEx frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
		DcMotorEx backRight = hardwareMap.get(DcMotorEx.class, "backRight");

		telemetry.log().add("Configuring motors...");
		frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
		backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
		frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
		backRight.setDirection(DcMotorSimple.Direction.FORWARD);

		frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

		frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

		telemetry.log().add("Initialized.");
		waitForStart();

		while (opModeIsActive()) {
			// https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
			double y = -gamepad1.left_stick_y;
			double x = gamepad1.left_stick_x * STRAFING_MODIFIER; // Make strafing more powerful
			double turn = gamepad1.right_stick_x;

			// Clip power range to [0.0, 1.0]
			double denominator = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(turn), 1);
			frontLeft.setPower((y + x + turn) / denominator);
			backLeft.setPower((y - x + turn) / denominator);
			frontRight.setPower((y - x - turn) / denominator);
			backRight.setPower((y + x - turn) / denominator);
		}
	}
}
