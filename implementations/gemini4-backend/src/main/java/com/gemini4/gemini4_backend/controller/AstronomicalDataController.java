package com.gemini4.gemini4_backend.controller;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.example.MySciencePlan;
import edu.gemini.app.ocs.model.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // adjust origin as needed
public class AstronomicalDataController {

//  Initialize OCS class from library
    private OCS ocs;

    private OCS getOCS() {
        if (ocs == null) {
            ocs = new OCS(true);
            createSciencePlan();
            updateSciencePlanStatus();

        }
        return ocs;
    }


    @RequestMapping("/getallsp")
    public ArrayList<SciencePlan> getAllSciencePlans() {
        System.out.println(getOCS().getAllSciencePlans());
        return getOCS().getAllSciencePlans();
    }

    @PostMapping("/getspbylocation")
    public ArrayList<SciencePlan> getSciencePlanByLocation(@RequestBody Map<String, String> request) {
        String location = request.get("location");
        ArrayList<SciencePlan> allPlans = getOCS().getAllSciencePlans();
        ArrayList<SciencePlan> result= new ArrayList<>();
        for (SciencePlan sp : allPlans) {
            if (sp.getTelescopeLocation().toString().equals(location)) {
                result.add(sp);
            }
        }
        return result;
    }

    @PostMapping("/getspbydate")
    public ArrayList<SciencePlan> getSciencePlanByDate(@RequestBody Map<String, String> request) {
        String date = request.get("date");
        ArrayList<SciencePlan> allPlans = getOCS().getAllSciencePlans();
        ArrayList<SciencePlan> result= new ArrayList<>();
        for (SciencePlan sp : allPlans) {
            String startDate = sp.getStartDate().toString().split(" ")[0];
            String endDate = sp.getEndDate().toString().split(" ")[0];
            if (startDate.equals(date) || endDate.equals(date)) {
                result.add(sp);
            }
        }
        return result;
    }

    @PostMapping("/getspbystar")
    public ArrayList<SciencePlan> getSciencePlanByStarSystem(@RequestBody Map<String, String> request) {
        String star = request.get("star");
        ArrayList<SciencePlan> allPlans = getOCS().getAllSciencePlans();
        ArrayList<SciencePlan> result= new ArrayList<>();
        for (SciencePlan sp : allPlans) {
            if (sp.getStarSystem().toString().equals(star)) {
                result.add(sp);
            }
        }
        return result;
    }

    @PostMapping("/getastronomical")
    public ResponseEntity<Map<String, Object>> collectAstronomicalData(@RequestBody Map<String, Integer> request) {
        int sciplan_no = request.get("sciplan_no");

        try {
            SciencePlan sp = getOCS().getSciencePlanByNo(sciplan_no);
            AstronomicalData astroData = getOCS().getAstronomicalData(sp);
            System.out.println(sp);
            if (astroData != null) {
                ArrayList<String> images = astroData.getAstronomicalDataLinks();
                System.out.println("Images = " + images.size());

                List<String> imageUrls = new ArrayList<>();

                for (String image : images) {
                    System.out.println(image);
                    imageUrls.add(image);
                }

                Map<String, Object> response = new HashMap<>();
                response.put("images", imageUrls);
                response.put("size", astroData.getAllImages().size());
                Map<String, Object> spDetails = new HashMap<>();
                spDetails.put("planNo", sp.getPlanNo());
                spDetails.put("creator", sp.getCreator());
                spDetails.put("submitter", sp.getSubmitter());
                spDetails.put("fundingInUSD", sp.getFundingInUSD());
                spDetails.put("objectives", sp.getObjectives());
                spDetails.put("starSystem", sp.getStarSystem());
                spDetails.put("startDate", sp.getStartDate());
                spDetails.put("endDate", sp.getEndDate());
                spDetails.put("telescopeLocation", sp.getTelescopeLocation());
                spDetails.put("status", sp.getStatus());

                if (!sp.getDataProcRequirements().isEmpty()) {
                    DataProcRequirement dpr = sp.getDataProcRequirements().get(0);
                    Map<String, Object> dprDetails = new HashMap<>();
                    dprDetails.put("fileType", dpr.getFileType());
                    dprDetails.put("fileQuality", dpr.getFileQuality());
                    dprDetails.put("colorType", dpr.getColorType());
                    dprDetails.put("contrast", dpr.getContrast());
                    dprDetails.put("brightness", dpr.getBrightness());
                    dprDetails.put("saturation", dpr.getSaturation());
                    dprDetails.put("highlights", dpr.getHighlights());
                    dprDetails.put("exposure", dpr.getExposure());
                    dprDetails.put("shadows", dpr.getShadows());
                    dprDetails.put("whites", dpr.getWhites());
                    dprDetails.put("blacks", dpr.getBlacks());
                    dprDetails.put("luminance", dpr.getLuminance());
                    dprDetails.put("hue", dpr.getHue());

                    spDetails.put("dataProcRequirement", dprDetails);
                }
                response.put("sciencePlan", spDetails);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "No validated science plans found."));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Failed to process images."));
        }
    }

//    @PostMapping("/astrodatadownload")
//    public Image getImage(@RequestBody Map<String, String> request) {
//        try{
//            String url = request.get("url");
//            int sciplan_no = Integer.parseInt(request.get("spno"));
//            SciencePlan sp = getOCS().getSciencePlanByNo(sciplan_no);
//            AstronomicalData astroData = getOCS().getAstronomicalData(sp);
//            Method downloadMethod = AstronomicalData.class.getDeclaredMethod("downloadImage", String.class);
//            downloadMethod.setAccessible(true); // Make the private method accessible
//            System.out.println("Download is working");
//            return (Image) downloadMethod.invoke(astroData, url);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @PostMapping("/astrodatadownload")
//    public ResponseEntity<byte[]> getImage(@RequestBody Map<String, String> request) {
//        try {
//            String url = request.get("url");
//            int sciplan_no = Integer.parseInt(request.get("spno"));
//            SciencePlan sp = getOCS().getSciencePlanByNo(sciplan_no);
//            AstronomicalData astroData = getOCS().getAstronomicalData(sp);
//
//            Method downloadMethod = AstronomicalData.class.getDeclaredMethod("downloadImage", String.class);
//            downloadMethod.setAccessible(true);
//
//            // Download image (as a Java Image)
//            Image image = (Image) downloadMethod.invoke(astroData, url);
//
//            // Convert Image to BufferedImage
//            BufferedImage bufferedImage = new BufferedImage(
//                    image.getWidth(null),
//                    image.getHeight(null),
//                    BufferedImage.TYPE_INT_RGB
//            );
//            bufferedImage.getGraphics().drawImage(image, 0, 0, null);
//
//            // Convert to byte array
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "png", baos);
//            byte[] imageBytes = baos.toByteArray();
//
//            // Build HTTP response
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_PNG);
//            headers.setContentDisposition(ContentDisposition.builder("attachment")
//                    .filename("astrodata.png")
//                    .build());
//
//            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PostMapping("/astrodatadownload")
    public ResponseEntity<byte[]> getImage(@RequestBody Map<String, String> request) {
        try {
            String url = request.get("url");
            int sciplan_no = Integer.parseInt(request.get("spno"));
            SciencePlan sp = getOCS().getSciencePlanByNo(sciplan_no);
            AstronomicalData astroData = getOCS().getAstronomicalData(sp);

            Method downloadMethod = AstronomicalData.class.getDeclaredMethod("downloadImage", String.class);
            downloadMethod.setAccessible(true);

            // Download image (as a Java Image)
            Image image = (Image) downloadMethod.invoke(astroData, url);

            // Convert Image to BufferedImage
            BufferedImage bufferedImage = new BufferedImage(
                    image.getWidth(null),
                    image.getHeight(null),
                    BufferedImage.TYPE_INT_RGB
            );
            bufferedImage.getGraphics().drawImage(image, 0, 0, null);

            // Convert to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            // Build HTTP response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("astrodata.png")
                    .build());

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public void createSciencePlan() {
        MySciencePlan mySP = new MySciencePlan();
        mySP.setCreator("Morakot Choetkiertikul");
        mySP.setSubmitter("Chaiyong Ragkhitwetsagul");
        mySP.setFundingInUSD(1000);
        mySP.setObjectives("To study the Auriga star system.");
        mySP.setStarSystem(StarSystem.CONSTELLATIONS.Auriga);
        mySP.setStartDate("22/04/2021 23:00:00");
        mySP.setTelescopeLocation(SciencePlan.TELESCOPELOC.CHILE);
        mySP.setEndDate("23/04/2021 02:00:00");
        DataProcRequirement dpr1 =
                new DataProcRequirement("JPEG", "Low", "Color mode",
                        11, 10, 5, 0, 7, 0,
                        0, 0, 10, 8);
        mySP.setDataProcRequirements(dpr1);
        // submit it to the OCS system
        getOCS().createSciencePlan(mySP);
        System.out.println(getOCS().getAllSciencePlans());
    }

    public void updateSciencePlanStatus() {
        getOCS().updateSciencePlanStatus(3, SciencePlan.STATUS.COMPLETE);
        System.out.println(getOCS().getSciencePlanByNo(3));
    }
}
