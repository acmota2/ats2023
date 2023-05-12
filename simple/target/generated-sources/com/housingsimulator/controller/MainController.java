/*

=======================================================
==     THIS IS AN AUTOMATICALLY GENERATED FILE       ==
=======================================================

                    DO NOT EDIT!

*/

package com.housingsimulator.controller;

import com.housingsimulator.model.Model;
import com.housingsimulator.view.View;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The main controller of the application.
 * Is called by the main function and runs the application loop
 */
public class MainController extends Controller {
    private Map<String, Controller> controllers; /*! The map of controllers indexed by their class name */

    /**
     * Constructor which sets the model of the application and loads all the controllers
     * @param model the model of the application
     */
    public MainController(Model model) {
        this.setModel(model);
        this.controllers = new HashMap<>();
        this.loadControllers();
    }

    /**
     * The main application loop
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        boolean running = true;

        System.out.println("THE HOUSING SIMULATOR");
        System.out.println("TP POO 2022 - Group 10");
        System.out.println("For more help, type 'SEND HELP'");
        System.out.println("============================================");


        while(running) {
            System.out.print("thehousingsimulator> ");
            String input = sc.nextLine();

            if(input.equals("QUIT")) {
                running = false;
            } else {
                if(!parseInput(input))
                    System.out.println("Command not recognized");
            }
        }
    }

    /**
     * Loads all the controllers to be used by the application
     */
    private void loadControllers() {
		controllers.put("SmartBulbController", new SmartBulbController(this.getModel()));
		controllers.put("SmartSpeakerController", new SmartSpeakerController(this.getModel()));
		controllers.put("ReceiptController", new ReceiptController(this.getModel()));
		controllers.put("EnergySupplierController", new EnergySupplierController(this.getModel()));
		controllers.put("SmartCameraController", new SmartCameraController(this.getModel()));
		controllers.put("SimulationController", new SimulationController(this.getModel()));
		controllers.put("SmartHouseController", new SmartHouseController(this.getModel()));
		controllers.put("EntityController", new EntityController(this.getModel()));
		controllers.put("FileController", new FileController(this.getModel()));
		controllers.put("SmartDeviceController", new SmartDeviceController(this.getModel()));

    }

    /**
     * Processes a set of commands by the user.
     *
     * @param lines the input lines. Each line is a command to execute
     * @return Whether or not the commands were found and executed
     */
    public boolean parseInput(String[] lines) {
        boolean result = true;

        for(String line : lines) {
            result &= parseInput(line);
        }

        return result;
    }

    /**
     * Processes a command by the user.
     *
     * @param input the command string
     * @return Whether or not the command was found and executed
     */
    public boolean parseInput(String input) {
        if(input.equals(""))
            return true;
		if(input.matches("(?i)BULB GET ID=(\\d+)")) {
			Pattern p = Pattern.compile("BULB GET ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartBulbController)controllers.get("SmartBulbController")).getBulb(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)BULB GET HOUSE=(\\d+)")) {
			Pattern p = Pattern.compile("BULB GET HOUSE=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartBulbController)controllers.get("SmartBulbController")).getBulbInHouse(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)BULB ADD HOUSE=(\\d+) ROOM=(.+) NAME=(.+) ON=(TRUE|FALSE) COLOUR=(WARM|NEUTRAL|COLD) DIMENSION=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("BULB ADD HOUSE=(\\d+) ROOM=(.+) NAME=(.+) ON=(TRUE|FALSE) COLOUR=(WARM|NEUTRAL|COLD) DIMENSION=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartBulbController)controllers.get("SmartBulbController")).addBulb(new java.lang.Integer(m.group(1)),new java.lang.String(m.group(2)),new java.lang.String(m.group(3)),new java.lang.Boolean(m.group(4)),new java.lang.String(m.group(5)),new java.lang.Float(m.group(6)));
			}
			return true;
		}
		if(input.matches("(?i)BULB UPDATE ID=(\\d+) NAME=(.+) ON=(TRUE|FALSE) COLOUR=(WARM|NEUTRAL|COLD) DIMENSION=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("BULB UPDATE ID=(\\d+) NAME=(.+) ON=(TRUE|FALSE) COLOUR=(WARM|NEUTRAL|COLD) DIMENSION=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartBulbController)controllers.get("SmartBulbController")).updateBulb(new java.lang.Integer(m.group(1)),new java.lang.String(m.group(2)),new java.lang.Boolean(m.group(3)),new java.lang.String(m.group(4)),new java.lang.Float(m.group(5)));
			}
			return true;
		}
		if(input.matches("(?i)BRAND LIST ALL")) {
			Pattern p = Pattern.compile("BRAND LIST ALL", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartSpeakerController)controllers.get("SmartSpeakerController")).getBrands();
			}
			return true;
		}
		if(input.matches("(?i)BRAND ADD NAME=(.+) DAILY_CONSUMPTION=(\\d+) INSTALLATION_COST=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("BRAND ADD NAME=(.+) DAILY_CONSUMPTION=(\\d+) INSTALLATION_COST=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartSpeakerController)controllers.get("SmartSpeakerController")).addBrand(new java.lang.String(m.group(1)),new java.lang.Integer(m.group(2)),new java.lang.Double(m.group(3)));
			}
			return true;
		}
		if(input.matches("(?i)BRAND UPDATE OLD_NAME=(.+) NEW_NAME=(.+) DAILY_CONSUMPTION=(\\d+) INSTALLATION_COST=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("BRAND UPDATE OLD_NAME=(.+) NEW_NAME=(.+) DAILY_CONSUMPTION=(\\d+) INSTALLATION_COST=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartSpeakerController)controllers.get("SmartSpeakerController")).updateBrand(new java.lang.String(m.group(1)),new java.lang.String(m.group(2)),new java.lang.Integer(m.group(3)),new java.lang.Double(m.group(4)));
			}
			return true;
		}
		if(input.matches("(?i)SPEAKER GET ID=(\\d+)")) {
			Pattern p = Pattern.compile("SPEAKER GET ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartSpeakerController)controllers.get("SmartSpeakerController")).getSpeakerById(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)SPEAKER GET HOUSE=(\\d+)")) {
			Pattern p = Pattern.compile("SPEAKER GET HOUSE=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartSpeakerController)controllers.get("SmartSpeakerController")).getSpeakersByHouse(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)SPEAKER ADD HOUSE=(\\d+) ROOM=(.+) NAME=(.+) ON=(TRUE|FALSE) BRAND=(.+) STATION=(.+) VOLUME=(\\d+)")) {
			Pattern p = Pattern.compile("SPEAKER ADD HOUSE=(\\d+) ROOM=(.+) NAME=(.+) ON=(TRUE|FALSE) BRAND=(.+) STATION=(.+) VOLUME=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartSpeakerController)controllers.get("SmartSpeakerController")).addSpeaker(new java.lang.Integer(m.group(1)),new java.lang.String(m.group(2)),new java.lang.String(m.group(3)),new java.lang.Boolean(m.group(4)),new java.lang.String(m.group(5)),new java.lang.String(m.group(6)),new java.lang.Integer(m.group(7)));
			}
			return true;
		}
		if(input.matches("(?i)SPEAKER UPDATE ID=(\\d+) NAME=(.+) ON=(TRUE|FALSE) BRAND=(.+) STATION=(.+) VOLUME=(\\d+)")) {
			Pattern p = Pattern.compile("SPEAKER UPDATE ID=(\\d+) NAME=(.+) ON=(TRUE|FALSE) BRAND=(.+) STATION=(.+) VOLUME=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartSpeakerController)controllers.get("SmartSpeakerController")).updateSpeaker(new java.lang.Integer(m.group(1)),new java.lang.String(m.group(2)),new java.lang.Boolean(m.group(3)),new java.lang.String(m.group(4)),new java.lang.String(m.group(5)),new java.lang.Integer(m.group(6)));
			}
			return true;
		}
		if(input.matches("(?i)RECEIPT LIST ALL INITIALDATE=(\\d+) FINALDATE=(\\d+)")) {
			Pattern p = Pattern.compile("RECEIPT LIST ALL INITIALDATE=(\\d+) FINALDATE=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((ReceiptController)controllers.get("ReceiptController")).getReceipts(new java.lang.Double(m.group(1)),new java.lang.Double(m.group(2)));
			}
			return true;
		}
		if(input.matches("(?i)RECEIPT GET SUPPLIER=(\\d+)")) {
			Pattern p = Pattern.compile("RECEIPT GET SUPPLIER=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((ReceiptController)controllers.get("ReceiptController")).getReceiptsbySupplier(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)RECEIPT GET HOUSE=(\\d+)")) {
			Pattern p = Pattern.compile("RECEIPT GET HOUSE=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((ReceiptController)controllers.get("ReceiptController")).getReceiptsbyHouse(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)RECEIPT GET HOUSE=(\\d+) TIME=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("RECEIPT GET HOUSE=(\\d+) TIME=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((ReceiptController)controllers.get("ReceiptController")).getReceiptsbyHouseTime(new java.lang.Integer(m.group(1)),new java.lang.Double(m.group(2)));
			}
			return true;
		}
		if(input.matches("(?i)SUPPLIER LIST ALL")) {
			Pattern p = Pattern.compile("SUPPLIER LIST ALL", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((EnergySupplierController)controllers.get("EnergySupplierController")).listSuppliers();
			}
			return true;
		}
		if(input.matches("(?i)SUPPLIER GET NAME=(.+)")) {
			Pattern p = Pattern.compile("SUPPLIER GET NAME=(.+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((EnergySupplierController)controllers.get("EnergySupplierController")).getSuppliersByName(new java.lang.String(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)SUPPLIER GET ID=(\\d+)")) {
			Pattern p = Pattern.compile("SUPPLIER GET ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((EnergySupplierController)controllers.get("EnergySupplierController")).getSupplierById(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)SUPPLIER ADD NAME=(.+) BASE_VALUE=(\\d+\\.?\\d*) TAX=(\\d+\\.?\\d*) FORMULA=(.+)")) {
			Pattern p = Pattern.compile("SUPPLIER ADD NAME=(.+) BASE_VALUE=(\\d+\\.?\\d*) TAX=(\\d+\\.?\\d*) FORMULA=(.+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((EnergySupplierController)controllers.get("EnergySupplierController")).addSupplier(new java.lang.String(m.group(1)),new java.lang.Float(m.group(2)),new java.lang.Float(m.group(3)),new java.lang.String(m.group(4)));
			}
			return true;
		}
		if(input.matches("(?i)SUPPLIER UPDATE ID=(\\d+) NAME=(.+) BASE_VALUE=(\\d+\\.?\\d*) TAX=(\\d+\\.?\\d*) FORMULA=(.+)")) {
			Pattern p = Pattern.compile("SUPPLIER UPDATE ID=(\\d+) NAME=(.+) BASE_VALUE=(\\d+\\.?\\d*) TAX=(\\d+\\.?\\d*) FORMULA=(.+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((EnergySupplierController)controllers.get("EnergySupplierController")).updateSupplier(new java.lang.Integer(m.group(1)),new java.lang.String(m.group(2)),new java.lang.Float(m.group(3)),new java.lang.Float(m.group(4)),new java.lang.String(m.group(5)));
			}
			return true;
		}
		if(input.matches("(?i)SUPPLIER GET MOST SELLER")) {
			Pattern p = Pattern.compile("SUPPLIER GET MOST SELLER", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((EnergySupplierController)controllers.get("EnergySupplierController")).bestSellingSupplier();
			}
			return true;
		}
		if(input.matches("(?i)CAMERA GET ID=(\\d+)")) {
			Pattern p = Pattern.compile("CAMERA GET ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartCameraController)controllers.get("SmartCameraController")).getCameraById(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)CAMERA GET HOUSE=(\\d+)")) {
			Pattern p = Pattern.compile("CAMERA GET HOUSE=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartCameraController)controllers.get("SmartCameraController")).getCamerasByHouse(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)CAMERA ADD HOUSE=(\\d+) ROOM=(.+) NAME=(.+) ON=(TRUE|FALSE) CAMERARESOLUTION=\\((\\d+),(\\d+)\\) FILERESOLUTION=(\\d+)")) {
			Pattern p = Pattern.compile("CAMERA ADD HOUSE=(\\d+) ROOM=(.+) NAME=(.+) ON=(TRUE|FALSE) CAMERARESOLUTION=\\((\\d+),(\\d+)\\) FILERESOLUTION=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartCameraController)controllers.get("SmartCameraController")).addCamera(new java.lang.Integer(m.group(1)),new java.lang.String(m.group(2)),new java.lang.String(m.group(3)),new java.lang.Boolean(m.group(4)),new java.lang.Integer(m.group(5)),new java.lang.Integer(m.group(6)),new java.lang.Long(m.group(7)));
			}
			return true;
		}
		if(input.matches("(?i)CAMERA UPDATE ID=(\\d+) NAME=(.+) ON=(TRUE|FALSE) CAMERARESOLUTION=\\((\\d+),(\\d+)\\) FILERESOLUTION=(\\d+)")) {
			Pattern p = Pattern.compile("CAMERA UPDATE ID=(\\d+) NAME=(.+) ON=(TRUE|FALSE) CAMERARESOLUTION=\\((\\d+),(\\d+)\\) FILERESOLUTION=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartCameraController)controllers.get("SmartCameraController")).updateCamera(new java.lang.Integer(m.group(1)),new java.lang.String(m.group(2)),new java.lang.Boolean(m.group(3)),new java.lang.Integer(m.group(4)),new java.lang.Integer(m.group(5)),new java.lang.Integer(m.group(6)));
			}
			return true;
		}
		if(input.matches("(?i)SIMULATION MOMENT SET=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("SIMULATION MOMENT SET=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SimulationController)controllers.get("SimulationController")).setMoment(new java.lang.Double(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)SIMULATION MOMENT GET")) {
			Pattern p = Pattern.compile("SIMULATION MOMENT GET", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SimulationController)controllers.get("SimulationController")).getMoment();
			}
			return true;
		}
		if(input.matches("(?i)SIMULATION MOMENT ADD=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("SIMULATION MOMENT ADD=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SimulationController)controllers.get("SimulationController")).addMoment(new java.lang.Double(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)SIMULATION MOMENT ADD DAYS=(\\d+) HOURS=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("SIMULATION MOMENT ADD DAYS=(\\d+) HOURS=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SimulationController)controllers.get("SimulationController")).addMomentSpecified(new java.lang.Integer(m.group(1)),new java.lang.Double(m.group(2)));
			}
			return true;
		}
		if(input.matches("(?i)HOUSE LIST ALL")) {
			Pattern p = Pattern.compile("HOUSE LIST ALL", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartHouseController)controllers.get("SmartHouseController")).getDevices();
			}
			return true;
		}
		if(input.matches("(?i)HOUSE ADD NAME=(.+) NIF=(\\d+) OWNER=(.+) ENERGY_SUPPLIER_ID=(\\d+)")) {
			Pattern p = Pattern.compile("HOUSE ADD NAME=(.+) NIF=(\\d+) OWNER=(.+) ENERGY_SUPPLIER_ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartHouseController)controllers.get("SmartHouseController")).addHouse(new java.lang.String(m.group(1)),new java.lang.Integer(m.group(2)),new java.lang.String(m.group(3)),new java.lang.Integer(m.group(4)));
			}
			return true;
		}
		if(input.matches("(?i)HOUSE UPDATE ID=(\\d+) NAME=(.+) NIF=(\\d+) OWNER=(.+) ENERGY_SUPPLIER_ID=(\\d+)")) {
			Pattern p = Pattern.compile("HOUSE UPDATE ID=(\\d+) NAME=(.+) NIF=(\\d+) OWNER=(.+) ENERGY_SUPPLIER_ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartHouseController)controllers.get("SmartHouseController")).updateHouse(new java.lang.Integer(m.group(1)),new java.lang.String(m.group(2)),new java.lang.Integer(m.group(3)),new java.lang.String(m.group(4)),new java.lang.Integer(m.group(5)));
			}
			return true;
		}
		if(input.matches("(?i)HOUSE CHANGE_SUPPLIER ID=(\\d+) ENERGY_SUPPLIER_ID=(\\d+)")) {
			Pattern p = Pattern.compile("HOUSE CHANGE_SUPPLIER ID=(\\d+) ENERGY_SUPPLIER_ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartHouseController)controllers.get("SmartHouseController")).changeHouseSupplier(new java.lang.Integer(m.group(1)),new java.lang.Integer(m.group(2)));
			}
			return true;
		}
		if(input.matches("(?i)HOUSE DELETE ID=(\\d+)")) {
			Pattern p = Pattern.compile("HOUSE DELETE ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartHouseController)controllers.get("SmartHouseController")).deleteHouse(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)HOUSE GET ID=(\\d+)")) {
			Pattern p = Pattern.compile("HOUSE GET ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartHouseController)controllers.get("SmartHouseController")).getHouseById(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)HOUSE SPENT MOST START=(\\d+\\.?\\d*) END=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("HOUSE SPENT MOST START=(\\d+\\.?\\d*) END=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartHouseController)controllers.get("SmartHouseController")).getHouseWhichSpentMost(new java.lang.Double(m.group(1)),new java.lang.Double(m.group(2)));
			}
			return true;
		}
		if(input.matches("(?i)HOUSE GET BY ENERGY CONSUMPTION START=(\\d+\\.?\\d*) END=(\\d+\\.?\\d*)")) {
			Pattern p = Pattern.compile("HOUSE GET BY ENERGY CONSUMPTION START=(\\d+\\.?\\d*) END=(\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartHouseController)controllers.get("SmartHouseController")).getHouseByEnergyConsumptionDouble(new java.lang.Double(m.group(1)),new java.lang.Double(m.group(2)));
			}
			return true;
		}
		if(input.matches("(?i)ENTITY LIST ALL")) {
			Pattern p = Pattern.compile("ENTITY LIST ALL", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((EntityController)controllers.get("EntityController")).getAll();
			}
			return true;
		}
		if(input.matches("(?i)ENTITY GET NAME=(.+)")) {
			Pattern p = Pattern.compile("ENTITY GET NAME=(.+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((EntityController)controllers.get("EntityController")).get(new java.lang.String(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)ENTITY DELETE ID=(\\d+)")) {
			Pattern p = Pattern.compile("ENTITY DELETE ID=(\\d+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((EntityController)controllers.get("EntityController")).delete(new java.lang.Integer(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)FILE SAVE (.+)")) {
			Pattern p = Pattern.compile("FILE SAVE (.+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((FileController)controllers.get("FileController")).saveSimulationToFile(new java.lang.String(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)FILE LOAD (.+)")) {
			Pattern p = Pattern.compile("FILE LOAD (.+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((FileController)controllers.get("FileController")).loadSimulationFromFile(new java.lang.String(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)SCRIPT EXEC (.+)")) {
			Pattern p = Pattern.compile("SCRIPT EXEC (.+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				String ret = ((FileController)controllers.get("FileController")).loadScript(new java.lang.String(m.group(1)));
				this.parseInput(ret.toString().split("\n"));
			}
			return true;
		}
		if(input.matches("(?i)SEND HELP")) {
			Pattern p = Pattern.compile("SEND HELP", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((FileController)controllers.get("FileController")).sendHelp();
			}
			return true;
		}
		if(input.matches("(?i)DEVICE LIST ALL")) {
			Pattern p = Pattern.compile("DEVICE LIST ALL", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartDeviceController)controllers.get("SmartDeviceController")).getDevices();
			}
			return true;
		}
		if(input.matches("(?i)DEVICE TURN ALL ON=(TRUE|FALSE)")) {
			Pattern p = Pattern.compile("DEVICE TURN ALL ON=(TRUE|FALSE)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartDeviceController)controllers.get("SmartDeviceController")).turnAllDevices(new java.lang.Boolean(m.group(1)));
			}
			return true;
		}
		if(input.matches("(?i)DEVICE TURN HOUSE=(\\d+) ROOM=(.+) ON=(TRUE|FALSE)")) {
			Pattern p = Pattern.compile("DEVICE TURN HOUSE=(\\d+) ROOM=(.+) ON=(TRUE|FALSE)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartDeviceController)controllers.get("SmartDeviceController")).turnAllDevices(new java.lang.Integer(m.group(1)),new java.lang.String(m.group(2)),new java.lang.Boolean(m.group(3)));
			}
			return true;
		}
		if(input.matches("(?i)DEVICE TURN HOUSE=(\\d+) ON=(TRUE|FALSE)")) {
			Pattern p = Pattern.compile("DEVICE TURN HOUSE=(\\d+) ON=(TRUE|FALSE)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartDeviceController)controllers.get("SmartDeviceController")).turnAllDevices(new java.lang.Integer(m.group(1)),new java.lang.Boolean(m.group(2)));
			}
			return true;
		}
		if(input.matches("(?i)DEVICE TURN ID=(\\d+) ON=(TRUE|FALSE)")) {
			Pattern p = Pattern.compile("DEVICE TURN ID=(\\d+) ON=(TRUE|FALSE)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartDeviceController)controllers.get("SmartDeviceController")).turnDevice(new java.lang.Integer(m.group(1)),new java.lang.Boolean(m.group(2)));
			}
			return true;
		}
		if(input.matches("(?i)DEVICE MOVE ID=(\\d+) HOUSE=(\\d+) ROOM=(.+)")) {
			Pattern p = Pattern.compile("DEVICE MOVE ID=(\\d+) HOUSE=(\\d+) ROOM=(.+)", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(input);
			if(m.find()) {
				((SmartDeviceController)controllers.get("SmartDeviceController")).moveDevice(new java.lang.Integer(m.group(1)),new java.lang.Integer(m.group(2)),new java.lang.String(m.group(3)));
			}
			return true;
		}

        return false;
    }
}
