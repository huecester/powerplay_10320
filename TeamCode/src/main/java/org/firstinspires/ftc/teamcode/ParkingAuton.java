package org.firstinspires.ftc.teamcode;


/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import static org.firstinspires.ftc.teamcode.Config.APRIL_TAG_SIZE;
import static org.firstinspires.ftc.teamcode.Config.DECIMAL_FORMAT;
import static org.firstinspires.ftc.teamcode.Config.TAG_LEFT;
import static org.firstinspires.ftc.teamcode.Config.TAG_MIDDLE;
import static org.firstinspires.ftc.teamcode.Config.TAG_RIGHT;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Autonomous(name = "Parking")
public class ParkingAuton extends LinearOpMode {
	@Override
	public void runOpMode() throws InterruptedException {
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
        telemetry.log().add("Initializing...");

        telemetry.log().add("Activating cache...");
        List<LynxModule> hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : hubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

		telemetry.log().add("Setting up hardware...");
        Drive drive = new Drive(hardwareMap, telemetry, Drive.Flag.AUTON);
		Claw claw = new Claw(hardwareMap, telemetry);
		claw.open();

		telemetry.log().add("Creating AprilTagDetection components...");
		AprilTagDetection tagOfInterest = null;
		int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
		OpenCvCamera camera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

		// Lens intrinsics
		// UNITS ARE PIXELS
		// NOTE: this calibration is for the C920 webcam at 800x448.
		// You will need to do your own calibration for other configurations!
		final double fx = 578.272;
		final double fy = 578.272;
		final double cx = 402.145;
		final double cy = 221.506;
		AprilTagDetectionPipeline aprilTagDetectionPipeline = new AprilTagDetectionPipeline(APRIL_TAG_SIZE, fx, fy, cx, cy);

		camera.setPipeline(aprilTagDetectionPipeline);
		camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
			@Override
			public void onOpened() {
				camera.startStreaming(800, 600, OpenCvCameraRotation.UPRIGHT);
			}

			@Override
			public void onError(int errorCode) {
                telemetry.log().add("[ERROR] " + errorCode);
			}
		});

		waitForStart();

		telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.OLDEST_FIRST);
		// TODO: move to config
		final int MAX_TRIES = 10;
		int tries = 0;
		while (tagOfInterest == null && tries++ < MAX_TRIES) {
			ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

			if (currentDetections.size() != 0) {
				boolean tagFound = false;

				for (AprilTagDetection tag : currentDetections) {
					if (tag.id == TAG_LEFT || tag.id == TAG_MIDDLE || tag.id == TAG_RIGHT) {
						tagOfInterest = tag;
						tagFound = true;
					}
				}

				if (tagFound) {
					telemetry.log().add("Found valid tag:\n" + tagToTelemetryString(tagOfInterest));
				} else {
					telemetry.log().add("Tag not found.");
				}
			} else {
				telemetry.log().add("Tag not found.");
			}

			telemetry.update();
			Thread.sleep(500);
		}

		if (tagOfInterest != null) {
			telemetry.addLine("Using tag:\n" + tagToTelemetryString(tagOfInterest));
		} else {
			telemetry.addLine("Tag was never found. Defaulting to forward parking spot.");
		}
		telemetry.update();

		drive.driveAtAngle(1.0, 0, 0, false);
		Thread.sleep(1050);
		switch (tagOfInterest != null ? tagOfInterest.id : -1) {
			case TAG_LEFT:
				drive.driveAtAngle(1.0, Math.toRadians(-90), 0, false);
				Thread.sleep(1200);
				break;
			case TAG_RIGHT:
				drive.driveAtAngle(1.0, Math.toRadians(90), 0, false);
				Thread.sleep(1200);
				break;
		}
		drive.stop();
	}

	private String tagToTelemetryString(AprilTagDetection tag) {
		return String.format(Locale.US, "ID: %d\nx: " + DECIMAL_FORMAT + " m\ny: " + DECIMAL_FORMAT + " m\nz: " + DECIMAL_FORMAT + " m\nyaw: " + DECIMAL_FORMAT + " deg\npitch: " + DECIMAL_FORMAT + " deg\nroll: " + DECIMAL_FORMAT + " deg",
			tag.id, tag.pose.x, tag.pose.y, tag.pose.z, Math.toDegrees(tag.pose.yaw), Math.toDegrees(tag.pose.pitch), Math.toDegrees(tag.pose.roll));
	}
}

