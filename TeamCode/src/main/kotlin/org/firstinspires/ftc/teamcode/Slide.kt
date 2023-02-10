package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry

class Slide(hardwareMap: HardwareMap, telemetry: Telemetry) {
	private val motor: DcMotorEx

	init {
		telemetry.log().add("Setting up slide...")

		telemetry.log().add("Setting up slide hardware...")
		motor = hardwareMap.get(DcMotorEx::class.java, "slide")

		telemetry.log().add("Configuring motor...")
		motor.configure()

		telemetry.log().add("Slide initialized.")
	}

	fun slide(gamepad: GamepadEx) {
		if (gamepad.getButton(GamepadEx.Button.RT)) raise()
		else if (gamepad.getButton(GamepadEx.Button.LT)) lower()
		else stop()
	}

	private fun raise() {
		if (motor.currentPosition >= Parameters.SLIDE_TOP_LIMIT) stop()
		else motor.power = Parameters.SLIDE_POWER
	}

	private fun lower() {
		if (motor.currentPosition <= Parameters.SLIDE_BOTTOM_LIMIT) stop()
		else motor.power = -Parameters.SLIDE_POWER
	}

	private fun stop() { motor.power = 0.0 }
}