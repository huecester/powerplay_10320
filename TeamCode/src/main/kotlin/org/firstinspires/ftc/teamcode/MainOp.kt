package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.Telemetry

@TeleOp(name = "Main manual op mode")
class MainOp : LinearOpMode() {
	override fun runOpMode() {
		telemetry.log().displayOrder = Telemetry.Log.DisplayOrder.NEWEST_FIRST
		telemetry.log().add("Initializing robot...")
		activateCache()
		val gamepad = GamepadEx(gamepad1)
		val drive = Drive(hardwareMap, telemetry)
		val claw = Claw(hardwareMap, telemetry, gamepad)
		val slide = Slide(hardwareMap, telemetry)
		telemetry.log().add("Initialized.")

		waitForStart()

		while (opModeIsActive()) {
			gamepad.tick()
			drive.drive(gamepad)
			slide.slide(gamepad)
			telemetry.update()
		}
	}
}