package chawks.tests;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.GenericDetector;
import com.disnodeteam.dogecv.filters.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.opencv.core.Scalar;

@Autonomous(name = "Gold Color Test", group = "DogeCV")
// @Disabled //Comment this out to enable opmode
public class MineralRecogTest extends LinearOpMode {
    private GenericDetector detector = null;
    private final Scalar HSV_GOLD = new Scalar(26,226,255); // 0 - 255 scale
    private final Scalar HSV_RANGE = new Scalar(5,10,10);
    private ElapsedTime runtime = new ElapsedTime();
    public void runOpMode() {
        telemetry.addData("Status","Initialized");
        telemetry.update();
        detector = new GenericDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        //Jewel Detector Settings
        detector.areaWeight = 0.02;
        detector.detectionMode = GenericDetector.GenericDetectionMode.MAX_AREA; // PERFECT_AREA
        //jewelDetector.perfectArea = 6500; <- Needed for PERFECT_AREA
        detector.debugContours = true;
        detector.maxDiffrence = 15;
        detector.ratioWeight = 15;
        detector.minArea = 700;
        detector.colorFilter = new LeviColorFilter(LeviColorFilter.ColorPreset.YELLOW);
        detector.enable();
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addData("Runtime",runtime.toString());
            if(detector.getFound()) {
                telemetry.addData("Status","Gold Found");
            } else {
                telemetry.addData("Status","Searching...");
            }
            telemetry.update();
        }
    }

    public void end() {
        detector.disable();
    }
}
