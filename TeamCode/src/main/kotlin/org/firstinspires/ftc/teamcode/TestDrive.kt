package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx

private const val INTERVAL = 1500L
private const val POWER = 0.25

@TeleOp(name = "Test drive motors", group = "Test")
class TestDrive : LinearOpMode() {
	override fun runOpMode() {
		val frontLeft = hardwareMap.get(DcMotorEx::class.java, "frontLeft")
		val backLeft = hardwareMap.get(DcMotorEx::class.java, "backLeft")
		val frontRight = hardwareMap.get(DcMotorEx::class.java, "frontRight")
		val backRight = hardwareMap.get(DcMotorEx::class.java, "backRight")

		frontLeft.configure()
		backLeft.configure()
		frontRight.configure()
		backRight.configure()

		waitForStart()

		frontLeft.power = POWER
		sleep(INTERVAL)
		frontLeft.power = 0.0
		backLeft.power = POWER
		sleep(INTERVAL)
		backLeft.power = 0.0
		frontRight.power = POWER
		sleep(INTERVAL)
		frontRight.power = 0.0
		backRight.power = POWER
		sleep(INTERVAL)
		backRight.power = 0.0
	}
}