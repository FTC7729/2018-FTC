package chawks.tests;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.GenericDetector;
import com.disnodeteam.dogecv.filters.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.opencv.core.Scalar;

@Autonomous(name = "Gold Color Test", group = "DogeCV")
// @Disabled //Comment this out to enable opmode
public class MineralRecogTest extends LinearOpMode {
    private GenericDetector detector = null;
    public void runOpMode() {
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
        detector.colorFilter = new HSVColorFilter(new Scalar(57,68,70), new Scalar(10,15,15));
        detector.enable();
        while(opModeIsActive()) {

        }
    }
    public void end() {
        detector.disable();
    }
}
