package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.ServoImplEx
import org.firstinspires.ftc.robotcore.external.Telemetry

@Config
object ClawParameters {
	@JvmField var CLAW_CLOSE_POSITION = 0.2
	@JvmField var CLAW_OPEN_POSITION = 0.7
}

class Claw(hardwareMap: HardwareMap, telemetry: Telemetry, gamepad: GamepadEx) {
	private val left: ServoImplEx
	private val right: ServoImplEx

	private var isOpen = false

	init {
		telemetry.log().add("Setting up claw...")

		telemetry.log().add("Setting up claw hardware...")
		left = hardwareMap.get(ServoImplEx::class.java, "clawLeft")
		right = hardwareMap.get(ServoImplEx::class.java, "clawRight")

		telemetry.log().add("Configuring servos...")
		left.configure()
		right.configure(Servo.Direction.REVERSE)

		telemetry.log().add("Closing claw...")
		close()

		telemetry.log().add("Adding gamepad handlers...")
		gamepad.addButtonHandler(GamepadEx.Button.A to GamepadEx.RisingOrFalling.RISING) {
			if (isOpen)
				close()
			else
				open()
		}

		telemetry.log().add("Claw initialized.")
	}

	private fun close() {
		left.position = ClawParameters.CLAW_CLOSE_POSITION
		right.position = ClawParameters.CLAW_CLOSE_POSITION
		isOpen = false
	}

	private fun open() {
		left.position = ClawParameters.CLAW_OPEN_POSITION
		right.position = ClawParameters.CLAW_OPEN_POSITION
		isOpen = true
	}
}