package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx

@TeleOp(name = "Manual slide test", group = "Manual test")
class ManualTestSlide : LinearOpMode() {
	override fun runOpMode() {
		val slide = hardwareMap.get(DcMotorEx::class.java, "slide")
		slide.configure()
		val positionItem = telemetry.addData("Position", "")

		waitForStart()

		while (opModeIsActive()) {
			slide.power = (gamepad1.right_trigger - gamepad1.left_trigger).toDouble()
			positionItem.setValue(slide.currentPosition)
			telemetry.update()
		}
	}
}