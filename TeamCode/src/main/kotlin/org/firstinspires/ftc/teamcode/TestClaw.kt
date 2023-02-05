package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.ServoImplEx

@TeleOp(name = "Test claw servos", group = "Test")
class TestClaw : LinearOpMode() {
	override fun runOpMode() {
		val left = hardwareMap.get(ServoImplEx::class.java, "clawLeft")
		val right = hardwareMap.get(ServoImplEx::class.java, "clawRight")
		left.configure()
		right.configure(Servo.Direction.REVERSE)

		val leftPositionItem = telemetry.addData("Left", "")
		val rightPositionItem = telemetry.addData("Right", "")


		waitForStart()

		var position = 0.0
		repeat(10) {
			left.position = position
			right.position = position

			leftPositionItem.setValue(left.position)
			rightPositionItem.setValue(right.position)

			sleep(1250)
			position += 0.1
		}
	}
}