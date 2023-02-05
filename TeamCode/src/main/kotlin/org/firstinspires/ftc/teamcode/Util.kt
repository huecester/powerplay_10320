package org.firstinspires.ftc.teamcode

import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor.RunMode
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.ServoImplEx

fun Boolean.toDouble() = if (this) 1.0 else 0.0

fun LinearOpMode.activateCache() {
	telemetry.log().add("Activating cache...")
	hardwareMap.getAll(LynxModule::class.java).forEach {
		it.bulkCachingMode = LynxModule.BulkCachingMode.AUTO
	}
}

fun DcMotorEx.configure(
	direction: Direction = Direction.FORWARD,
	mode: RunMode = RunMode.RUN_WITHOUT_ENCODER,
	zeroPowerBehavior: ZeroPowerBehavior = ZeroPowerBehavior.BRAKE,
) {
	this.direction = direction
	this.mode = RunMode.STOP_AND_RESET_ENCODER
	this.zeroPowerBehavior = zeroPowerBehavior
	if (mode == RunMode.RUN_TO_POSITION) this.targetPosition = 0
	this.mode = mode
}

fun ServoImplEx.configure(
	direction: Servo.Direction = Servo.Direction.FORWARD,
	rangeMin: Double = 0.0,
	rangeMax: Double = 1.0,
) {
	this.direction = direction
	this.scaleRange(rangeMin, rangeMax)
}