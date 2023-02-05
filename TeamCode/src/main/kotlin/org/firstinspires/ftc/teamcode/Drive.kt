package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.absoluteValue

class Drive(hardwareMap: HardwareMap, telemetry: Telemetry) {
	private val frontLeft: DcMotorEx
	private val backLeft: DcMotorEx
	private val frontRight: DcMotorEx
	private val backRight: DcMotorEx

	init {
		telemetry.log().add("Setting up drive...")

		telemetry.log().add("Setting up driving hardware...")
		frontLeft = hardwareMap.get(DcMotorEx::class.java, "frontLeft")
		backLeft = hardwareMap.get(DcMotorEx::class.java, "backLeft")
		frontRight = hardwareMap.get(DcMotorEx::class.java, "frontRight")
		backRight = hardwareMap.get(DcMotorEx::class.java, "backRight")

		telemetry.log().add("Configuring motors...")
		frontLeft.configure()
		backLeft.configure()
		frontRight.configure(DcMotorSimple.Direction.REVERSE)
		backRight.configure(DcMotorSimple.Direction.REVERSE)

		telemetry.log().add("Drive initialized.")
	}

	fun drive(gamepad: Gamepad) {
		// https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
		val x = (gamepad.right_bumper.toDouble() - gamepad.left_bumper.toDouble()) * 1.1
		val y = -gamepad.left_stick_y
		val turn = gamepad.left_stick_x

		val denominator =
			(x.absoluteValue + y.absoluteValue + turn.absoluteValue).coerceAtLeast(1.0)

		frontLeft.power = (y + x + turn) / denominator
		backLeft.power = (y - x + turn) / denominator
		frontRight.power = (y - x - turn) / denominator
		backRight.power = (y + x - turn) / denominator
	}
}