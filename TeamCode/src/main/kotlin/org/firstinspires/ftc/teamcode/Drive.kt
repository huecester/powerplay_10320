package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.absoluteValue

class Drive(hardwareMap: HardwareMap, telemetry: Telemetry) {
	val frontLeft: DcMotorEx
	val backLeft: DcMotorEx
	val frontRight: DcMotorEx
	val backRight: DcMotorEx

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
		frontRight.configure(Direction.REVERSE)
		backRight.configure(Direction.REVERSE)

		telemetry.log().add("Drive initialized.")
	}

	fun drive(gamepad: Gamepad) {
		// https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
		val x = (gamepad.right_bumper.toNum() - gamepad.left_bumper.toNum()) * 1.1
		val y = -gamepad.left_stick_y
		val turn = gamepad.left_stick_x

		val denominator = Math.max(x.absoluteValue + y.absoluteValue + turn.absoluteValue, 1.0)

		frontLeft.power = (y + x + turn) / denominator
		backLeft.power = (y - x + turn) / denominator
		frontRight.power = (y - x - turn) / denominator
		backRight.power = (y + x - turn) / denominator
	}
}