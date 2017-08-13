package instant.alarmreceptortoolkitapp.data.protocols.entities.contactid;

import java.util.ArrayList;
import java.util.List;

import instant.alarmreceptortoolkitapp.data.protocols.entities.EventCode;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Protocol;

/**
 * Created by hector on 30/07/17.
 */

public class ContactIDProtocol extends Protocol {
    
    @Override
    public List<EventCode> getDefaulEventCodes() {
        List<EventCode> r = new ArrayList<>();
        for (String[] v: codes) {
            EventCode e = new EventCode(v[0].getBytes(),v[1]);
            r.add(e);
        }
        return r;
    }

    @Override
    public List<EventCode> getDefaulSystemCodes() {
        return null;
    }

    public final static String[][] codes = {
            {"100", "Medical"},
            {"100", "Medical Restauration"},
            {"101", "Personal Emergency"},
            {"101", "Personal Emergency Restauration"},
            {"102", "Fail To Report In"},
            {"102", "Fail To Report In Restauration"},
            {"110", "Fire"},
            {"110", "Fire Restauration"},
            {"111", "Smoke"},
            {"111", "Smoke Restauration"},
            {"112", "Combustion"},
            {"112", "Combustion Restauration"},
            {"113", "Water Flow"},
            {"113", "Water Flow Restauration"},
            {"114", "Heat"},
            {"114", "Heat Restauration"},
            {"115", "Pull Station"},
            {"115", "Pull Station Restauration"},
            {"116", "Duct"},
            {"116", "Duct Restauration"},
            {"117", "Flame"},
            {"117", "Flame Restauration"},
            {"118", "Near Alarm"},
            {"118", "Near Alarm Restauration"},
            {"120", "Panic"},
            {"120", "Panic Restauration"},
            {"121", "Duress"},
            {"121", "Duress Restauration"},
            {"122", "Silent"},
            {"122", "Silent Restauration"},
            {"123", "Audible"},
            {"123", "Audible Restauration"},
            {"124", "Duress - Access Granted"},
            {"124", "Duress - Access Granted Restauration"},
            {"125", "Duress - Egress Granted"},
            {"125", "Duress - Egress Granted Restauration"},
            {"130", "Burglary"},
            {"130", "Burglary Restauration"},
            {"131", "Perimeter"},
            {"131", "Perimeter Restauration"},
            {"132", "Interior"},
            {"132", "Interior Restauration"},
            {"133", "24 Hour (Safe)"},
            {"133", "24 Hour (Safe) Restauration"},
            {"134", "Entry/Exit"},
            {"134", "Entry/Exit Restauration"},
            {"135", "Day/Night"},
            {"135", "Day/Night Restauration"},
            {"136", "Outdoor"},
            {"136", "Outdoor Restauration"},
            {"137", "Tamper"},
            {"137", "Tamper Restauration"},
            {"138", "Near Alarm"},
            {"138", "Near Alarm Restauration"},
            {"139", "Intrusion Verifier"},
            {"139", "Intrusion Verifier Restauration"},
            {"140", "General Alarm"},
            {"140", "General Alarm Restauration"},
            {"141", "Polling Loop Open"},
            {"141", "Polling Loop Open Restauration"},
            {"142", "Polling Loop Short"},
            {"142", "Polling Loop Short Restauration"},
            {"143", "Expansion Module Failure"},
            {"143", "Expansion Module Failure Restauration"},
            {"144", "Sensor Tamper"},
            {"144", "Sensor Tamper Restauration"},
            {"145", "Expansion Module Tamper"},
            {"145", "Expansion Module Tamper Restauration"},
            {"146", "Silent Burglary"},
            {"146", "Silent Burglary Restauration"},
            {"147", "Sensor Supervision Failure"},
            {"147", "Sensor Supervision Failure Restauration"},
            {"150", "24 Hour Non-Burglary"},
            {"150", "24 Hour Non-Burglary Restauration"},
            {"151", "Gas Detected"},
            {"151", "Gas Detected Restauration"},
            {"152", "Refrigeration"},
            {"152", "Refrigeration Restauration"},
            {"153", "Loss of Heat"},
            {"153", "Loss of Heat Restauration"},
            {"154", "Water Leakage"},
            {"154", "Water Leakage Restauration"},
            {"155", "Foil Break"},
            {"155", "Foil Break Restauration"},
            {"156", "Day Trouble"},
            {"156", "Day Trouble Restauration"},
            {"157", "Low Bottled Gas Level"},
            {"157", "Low Bottled Gas Level Restauration"},
            {"158", "High Temp"},
            {"158", "High Temp Restauration"},
            {"159", "Low Temp"},
            {"159", "Low Temp Restauration"},
            {"161", "Loss of Air Flow"},
            {"161", "Loss of Air Flow Restauration"},
            {"162", "Carbon Monoxide Detected"},
            {"162", "Carbon Monoxide Detected Restauration"},
            {"163", "Tank Level"},
            {"163", "Tank Level Restauration"},
            {"200", "Fire Supervisory"},
            {"200", "Fire Supervisory Restauration"},
            {"201", "Low Water Pressure"},
            {"201", "Low Water Pressure Restauration"},
            {"202", "Low CO2"},
            {"202", "Low CO2 Restauration"},
            {"203", "Gate Valve Sensor"},
            {"203", "Gate Valve Sensor Restauration"},
            {"204", "Low Water Level"},
            {"204", "Low Water Level Restauration"},
            {"205", "Pump Activated"},
            {"205", "Pump Activated Restauration"},
            {"206", "Pump Failure"},
            {"206", "Pump Failure Restauration"},
            {"300", "System Trouble"},
            {"300", "System Trouble Restauration"},
            {"301", "AC Loss"},
            {"301", "AC Loss Restauration"},
            {"302", "Low System Battery"},
            {"302", "Low System Battery Restauration"},
            {"303", "RAM Checksum Bad"},
            {"303", "RAM Checksum Bad Restauration"},
            {"304", "ROM Checksum Bad"},
            {"304", "ROM Checksum Bad Restauration"},
            {"305", "System Reset"},
            {"305", "System Reset Restauration"},
            {"306", "Panel Programming Changed"},
            {"306", "Panel Programming Changed Restauration"},
            {"307", "Self-Test Failure"},
            {"307", "Self-Test Failure Restauration"},
            {"308", "System Shutdown"},
            {"308", "System Shutdown Restauration"},
            {"309", "Battery Test Failure"},
            {"309", "Battery Test Failure Restauration"},
            {"310", "Ground Fault Failure"},
            {"310", "Ground Fault Restauration"},
            {"311", "Battery Missing/Dead"},
            {"311", "Battery Missing/Dead Restauration"},
            {"312", "Power Supply Overcurrent"},
            {"312", "Power Supply Overcurrent Restauration"},
            {"313", "Engineer"},
            {"313", "Engineer Restauration"},
            {"320", "Sounder/Relay"},
            {"320", "Sounder/Relay Restauration"},
            {"321", "Bell 1"},
            {"321", "Bell 1 Restauration"},
            {"322", "Bell 2"},
            {"322", "Bell 2 Restauration"},
            {"323", "Alarm Relay"},
            {"323", "Alarm Relay Restauration"},
            {"324", "Trouble Relay"},
            {"324", "Trouble Relay Restauration"},
            {"325", "Reversing Relay"},
            {"325", "Reversing Relay Restauration"},
            {"326", "Notification Appliance Ckt. #3"},
            {"326", "Notification Appliance Ckt. #3 Restauration"},
            {"327", "Notification Appliance Ckt. #4"},
            {"327", "Notification Appliance Ckt. #4 Restauration"},
            {"330", "System Peripheral Trouble"},
            {"330", "System Peripheral Trouble Restauration"},
            {"331", "Polling Loop Open"},
            {"331", "Polling Loop Open Restauration"},
            {"332", "Polling Loop Short"},
            {"332", "Polling Loop Short Restauration"},
            {"333", "Expansion Module Failure"},
            {"333", "Expansion Module Failure Restauration"},
            {"334", "Repeater Failure"},
            {"334", "Repeater Failure Restauration"},
            {"335", "Local Printer Out of Paper"},
            {"335", "Local Printer Out of Paper Restauration"},
            {"336", "Local Printer Failure"},
            {"336", "Local Printer Failure Restauration"},
            {"337", "Exp. Module DC Loss"},
            {"337", "Exp. Module DC Loss Restauration"},
            {"338", "Exp. Module Low Batt."},
            {"338", "Exp. Module Low Batt. Restauration"},
            {"339", "Exp. Module Reset"},
            {"339", "Exp. Module Reset Restauration"},
            {"341", "Exp. Module Tamper"},
            {"341", "Exp. Module Tamper Restauration"},
            {"342", "Exp. Module AC Loss"},
            {"342", "Exp. Module AC Loss Restauration"},
            {"343", "Exp. Module Self-Test Fail"},
            {"343", "Exp. Module Self-Test Fail Restauration"},
            {"344", "RF Receiver Jam Detect"},
            {"344", "RF Receiver Jam Detect Restauration"},
            {"350", "Communication Trouble"},
            {"350", "Communication Trouble Restauration"},
            {"351", "Telco 1 Fault"},
            {"351", "Telco 1 Fault Restauration"},
            {"352", "Telco 2 Fault"},
            {"352", "Telco 2 Fault Restauration"},
            {"353", "Long Range Radio Xmitter Fault"},
            {"353", "Long Range Radio Xmitter Fault Restauration"},
            {"354", "Failure to Communicate Event"},
            {"354", "Failure to Communicate Event Restauration"},
            {"355", "Loss of Radio Supervision"},
            {"355", "Loss of Radio Supervision Restauration"},
            {"356", "Loss of Central Polling"},
            {"356", "Loss of Central Polling Restauration"},
            {"357", "Long Range Radio VSWR Problem"},
            {"357", "Long Range Radio VSWR Problem Restauration"},
            {"370", "Protection Loop"},
            {"370", "Protection Loop Restauration"},
            {"371", "Protection Loop Open"},
            {"371", "Protection Loop Open Restauration"},
            {"372", "Protection Loop Short"},
            {"372", "Protection Loop Short Restauration"},
            {"373", "Fire Trouble"},
            {"373", "Fire Trouble Restauration"},
            {"374", "Exit Error Alarm (zone)"},
            {"374", "Exit Error Alarm (zone) Restauration"},
            {"375", "Panic Zone Trouble"},
            {"375", "Panic Zone Trouble Restauration"},
            {"376", "Hold-Up Zone Trouble"},
            {"376", "Hold-Up Zone Trouble Restauration"},
            {"377", "Swinger Trouble"},
            {"377", "Swinger Trouble Restauration"},
            {"378", "Cross-Zone Trouble"},
            {"378", "Cross-Zone Trouble Restauration"},
            {"380", "Sensor Trouble"},
            {"380", "Sensor Trouble Restauration"},
            {"381", "Loss of Supervision - RF"},
            {"381", "Loss of Supervision - RF Restauration"},
            {"382", "Loss of Supervision - RPM"},
            {"382", "Loss of Supervision - RPM Restauration"},
            {"383", "Sensor Tamper"},
            {"383", "Sensor Tamper Restauration"},
            {"384", "RF Low Battery"},
            {"384", "RF Low Battery Restauration"},
            {"385", "Smoke Detector Hi Sensitivity"},
            {"385", "Smoke Detector Hi Sensitivity Restauration"},
            {"386", "Smoke Detector Low Sensitivity"},
            {"386", "Smoke Detector Low Sensitivity Restauration"},
            {"387", "Intrusion Detector Hi Sensitivity"},
            {"387", "Intrusion Detector Hi Sensitivity Restauration"},
            {"388", "Intrusion Detector Low Sensitivity"},
            {"388", "Intrusion Detector Low Sensitivity Restauration"},
            {"389", "Sensor Self-Test Failure"},
            {"389", "Sensor Self-Test Failure Restauration"},
            {"391", "Sensor Watch Trouble"},
            {"391", "Sensor Watch Trouble Restauration"},
            {"392", "Drift Compensation Error"},
            {"392", "Drift Compensation Error Restauration"},
            {"393", "Maintenance Alert"},
            {"393", "Maintenance Alert Restauration"},
            {"400", "Open"},
            {"400", "Close"},
            {"401", "Open by User"},
            {"401", "Close by User"},
            {"402", "Open Group"},
            {"402", "Close Group"},
            {"403", "Open Automatic"},
            {"403", "Close Automatic"},
            {"404", "Open Late"},
            {"404", "Close Late"},
            {"405", "Deferred Open"},
            {"405", "Deferred Close"},
            {"406", "Open Cancel"},
            {"407", "Open Remote Disarm"},
            {"407", "Close Remote Arm"},
            {"408", "Close Quick Arm"},
            {"409", "Open Keyswich"},
            {"409", "Close Keyswich"},
            {"441", "Armed STAY"},
            {"441", "Armed STAY Restore"},
            {"442", "Keyswich Armed STAY"},
            {"450", "Exception Open"},
            {"450", "Exception Close"},
            {"451", "Open Early"},
            {"451", "Close Early"},
            {"452", "Open Late"},
            {"452", "Close Late"},
            {"453", "Failed to Open"},
            {"454", "Failed to Close"},
            {"455", "Auto Arm Failed"},
            {"456", "Partial Arm"},
            {"459", "Recent Close"},
            {"501", "Access Reader Disable Bypass"},
            {"501", "Access Reader Disable UnBypass"},
            {"520", "Sounder/Relay Disable Bypass"},
            {"520", "Sounder/Relay Disable UnBypass"},
            {"521", "Bell 1 Disable Bypass"},
            {"521", "Bell 1 Disable UnBypass"},
            {"522", "Bell 2 Disable Bypass"},
            {"522", "Bell 2 Disable UnBypass"},
            {"523", "Alarm Relay Disable Bypass"},
            {"523", "Alarm Relay Disable UnBypass"},
            {"524", "Trouble Relay Disable Bypass"},
            {"524", "Trouble Relay Disable UnBypass"},
            {"525", "Reversing Relay Disable Bypass"},
            {"525", "Reversing Relay Disable UnBypass"},
            {"526", "Notification Appliance Ckt. #3 Disable Bypass"},
            {"526", "Notification Appliance Ckt. #3 Disable UnBypass"},
            {"527", "Notification Appliance Ckt. #4 Disable Bypass"},
            {"527", "Notification Appliance Ckt. #4 Disable UnBypass"},
            {"531", "Module Added Bypass"},
            {"531", "Module Added UnBypass"},
            {"532", "Module Removed Bypass"},
            {"532", "Module Removed UnBypass"},
            {"551", "Dialer Disabled Bypass"},
            {"551", "Dialer Disabled UnBypass"},
            {"552", "Radio Transmitter Disabled Bypass"},
            {"552", "Radio Transmitter Disabled UnBypass"},
            {"553", "Remote Upload/Download Disabled Bypass"},
            {"553", "Remote Upload/Download Disabled UnBypass"},
            {"558", ""},
            {"570", "Zone/Sensor Bypass"},
            {"570", "Zone/Sensor UnBypass"},
            {"571", "Fire Bypass"},
            {"571", "Fire UnBypass"},
            {"572", "24 Hour Zone Bypass"},
            {"572", "24 Hour Zone UnBypass"},
            {"573", "Burglary Bypass"},
            {"573", "Burglary UnBypass"},
            {"574", "Group Bypass"},
            {"574", "Group UnBypass"},
            {"575", "Swinger Bypass"},
            {"575", "Swinger UnBypass"},
            {"576", "Access Zone Shunt Bypass"},
            {"576", "Access Zone Shunt UnBypass"},
            {"577", "Access Point Bypass"},
            {"577", "Access Point UnBypass"},
            {"601", "Manual Trigger Test Report"},
            {"602", "Periodic Test Report"},
            {"603", "Periodic RF Transmission"},
            {"126", "Hold-up suspicion print"},
            {"126", "Hold-up suspicion print Restauration"},
            {"129", "Panic Verifier"},
            {"129", "Panic Verifier Restauration"},
            {"168", "High Humidity"},
            {"168", "High Humidity Restauration"},
            {"169", "Low Humidity"},
            {"169", "Low Humidity Restauration"},
            {"314", "Primary Power Supply Failure"},
            {"314", "Primary Power Supply Failure Restauration"},
            {"316", "System Tamper"},
            {"316", "System Tamper Restauration"},
            {"345", "AES Encryption disabled/ enabled"},
            {"345", "AES Encryption disabled/ enabled Restauration"},
            {"358", "Periodic Comm Test Fail /Restore"},
            {"394", "CO Detector needs replacement"},
            {"394", "CO Detector needs replacement Restauration"},
            {"443", "Armed with System Trouble Override"},
            {"443", "Armed with System Trouble Override Restauration"},
            {"457", "Exit Error (user)"},
            {"457", "Exit Error (user) Restauration"},
            {"458", "User on Premises"},
            {"458", "User on Premises Restauration"},
            {"461", "Wrong Code Entry"},
            {"461", "Wrong Code Entry Restauration"},
            {"462", "Legal Code Entry"},
            {"462", "Legal Code Entry Restauration"},
            {"463", "Re-arm after Alarm"},
            {"463", "Re-arm after Alarm Restauration"},
            {"464", "Auto-arm Time Extended"},
            {"464", "Auto-arm Time Extended Restauration"},
            {"465", "Panic Alarm Reset"},
            {"465", "Panic Alarm Reset Restauration"},
            {"466", "Service On/Off Premises"},
            {"466", "Service On/Off Premises Restauration"},
            {"411", "Callback request made"},
            {"412", "Successful download/access"},
            {"413", "Unsuccessful access"},
            {"414", "System shutdown command received"},
            {"414", "System shutdown command received Restauration"},
            {"415", "Dialer shutdown command received"},
            {"415", "Dialer shutdown command received Restauration"},
            {"416", "Successful Upload"},
            {"421", "Access denied"},
            {"422", "Access report by user"},
            {"423", "Forced Access"},
            {"423", "Forced Access Restauration"},
            {"424", "Egress Denied"},
            {"425", "Egress Granted"},
            {"426", "Access Door propped open"},
            {"426", "Access Door propped open Restauration"},
            {"427", "Access point Door Status Monitor trouble"},
            {"427", "Access point Door Status Monitor trouble Restauration"},
            {"428", "Access point Request To Exit trouble"},
            {"428", "Access point Request To Exit trouble Restauration"},
            {"429", "Access program mode entry"},
            {"430", "Access program mode exit"},
            {"431", "Access threat level change"},
            {"431", "Access threat level change Restauration"},
            {"432", "Access relay/trigger fail"},
            {"432", "Access relay/trigger fail Restauration"},
            {"433", "Access RTE shunt"},
            {"433", "Access RTE shunt Restauration"},
            {"434", "Access DSM shunt"},
            {"434", "Access DSM shunt Restauration"},
            {"435", "Second Person Access"},
            {"435", "Second Person Access Restauration"},
            {"436", "Irregular Access"},
            {"436", "Irregular Access Restauration"},
            {"578", "Vault Bypass"},
            {"578", "Vault Bypass Restauration"},
            {"579", "Vent Zone Bypass"},
            {"579", "Vent Zone Bypass Restauration"},
            {"604", "Fire test"},
            {"604", "Fire test Restauration"},
            {"605", "Status report to follow"},
            {"605", "Status report to follow Restauration"},
            {"606", "Listen-in to follow"},
            {"607", "Walk test mode"},
            {"607", "Walk test mode Restauration"},
            {"608", "Periodic test - System Trouble Present"},
            {"609", "Video Xmitter active"},
            {"611", "Point tested OK"},
            {"612", "Point not tested"},
            {"613", "Intrusion Zone Walk Tested"},
            {"614", "Fire Zone Walk Tested"},
            {"615", "Panic Zone Walk Tested"},
            {"616", "Service Request"},
            {"616", "Service Request Restauration"},
            {"621", "Event Log reset"},
            {"622", "Event Log 50% full"},
            {"623", "Event Log 90% full"},
            {"624", "Event Log overflow"},
            {"625", "Time/Date reset"},
            {"626", "Time/Date inaccurate"},
            {"627", "Program mode entry"},
            {"628", "Program mode exit"},
            {"629", "32 Hour Event log marker"},
            {"630", "Schedule change"},
            {"631", "Exception schedule change"},
            {"632", "Access schedule change"},
            {"641", "Senior Watch Trouble"},
            {"641", "Senior Watch Trouble Restauration"},
            {"642", "Latch-key Supervision"},
            {"642", "Latch-key Supervision Restauration"},
            {"651", "Reserved for Ademco Use"},
            {"651", "Reserved for Ademco Use Restauration"},
            {"652", "Reserved for Ademco Use"},
            {"652", "Reserved for Ademco Use Restauration"},
            {"653", "Reserved for Ademco Use"},
            {"653", "Reserved for Ademco Use Restauration"},
            {"654", "System Inactivity"},
            {"654", "System Inactivity Restauration"},
            {"655", "User Code X modified by Installer"},
            {"655", "User Code X modified by Installer Restauration"},
            {"703", "Auxiliary #3"},
            {"703", "Auxiliary #3 Restauration"},
            {"704", "Installer Test"},
            {"704", "Installer Test Restauration"},
            {"796", "Unable to output signal (Derived Channel)"},
            {"796", "Unable to output signal (Derived Channel) Restauration"},
            {"076", "Test – Cell Backup"},
            {"103", "Test/Misc"},
            {"104", "DSC CID RESTORE"},
            {"105", "Test/Misc"},
            {"106", "ABORT"},
            {"109", "Tamper"},
            {"109", "Tamper Restauration"},
            {"11F", "Test/Misc"},
            {"13E", "Test/Misc"},
            {"145906", "AES IP-Link Door Tamper"},
            {"145906", "AES IP-Link Door Tamper Restauration"},
            {"199", "After alarm open"},
            {"199", "After alarm open Restauration"},
            {"1B0", "Test/Misc"},
            {"1BC", "Test/Misc"},
            {"1C1", "Test/Misc"},
            {"1CD", "Test/Misc"},
            {"1CF", "Test/Misc"},
            {"1D0", "Test/Misc"},
            {"1DD", "Test/Misc"},
            {"1EE", "Test/Misc"},
            {"210", "Supervisory"},
            {"210", "Supervisory Restauration"},
            {"213", "SPRINKLER TROUBLE"},
            {"213", "SPRINKLER TROUBLE Restauration"},
            {"271", "General Alarm"},
            {"271", "General Alarm Restauration"},
            {"301912", "AES IP-Link AC Failure"},
            {"301912", "AES IP-Link AC Failure Restauration"},
            {"302911", "AES IP-Link Low Battery"},
            {"302911", "AES IP-Link Low Battery Restauration"},
            {"305901", "AES Radio Manual Reset"},
            {"305901", "AES Radio Manual Reset Restauration"},
            {"305902", "AES Radio Power-On Reset"},
            {"305902", "AES Radio Power-On Reset Restauration"},
            {"307800", "AES Radio All OK"},
            {"307800", "AES Radio All OK Restauration"},
            {"307801", "AES Radio Low Battery"},
            {"307801", "AES Radio Low Battery Restauration"},
            {"307802", "RAM Data Error/Corrupt"},
            {"307802", "RAM Data Error/Corrupt Restauration"},
            {"307803", "U11 RAM Chip Battery Bad"},
            {"307803", "U11 RAM Chip Battery Bad Restauration"},
            {"307804", "AES Radio A to D Converter Faulted"},
            {"307804", "AES Radio A to D Converter Faulted Restauration"},
            {"307805", "Modem Chip Failed or missing"},
            {"307805", "Modem Chip Failed or missing Restauration"},
            {"307806", "Timing Error btwn CPU and Modem"},
            {"307806", "Timing Error btwn CPU and Modem Restauration"},
            {"307807", "Ram Chip Read/Write test Failure"},
            {"307807", "Ram Chip Read/Write test Failure Restauration"},
            {"307808", "AES RAdio Modem Loopback Failed"},
            {"307808", "AES RAdio Modem Loopback Failed Restauration"},
            {"307809", "AES Radio AC Fail or DC voltage LOW"},
            {"307809", "AES Radio AC Fail or DC voltage LOW Restauration"},
            {"309910", "AES Radio Battery Low"},
            {"309910", "AES Radio Battery Low Restauration"},
            {"315", "Trouble"},
            {"315", "Trouble Restauration"},
            {"328", "SIGNAL SILENCED"},
            {"328", "SIGNAL SILENCED Restauration"},
            {"340", "SYSTEM PERIPHERAL"},
            {"340", "SYSTEM PERIPHERAL Restauration"},
            {"350951", "Primary Comm Path Failure"},
            {"350951", "Primary Comm Path Failure Restauration"},
            {"350952", "Secondary Comm Path Failure"},
            {"350952", "Secondary Comm Path Failure Restauration"},
            {"353906", "AES IP-Link UnitID Conflict"},
            {"353906", "AES IP-Link UnitID Conflict Restauration"},
            {"354906", "AES IP-Link IP Checkin Failure"},
            {"354906", "AES IP-Link IP Checkin Failure Restauration"},
            {"354907", "AES MultiNet Modem Failure"},
            {"354907", "AES MultiNet Modem Failure Restauration"},
            {"354908", "AES IP-Link Modem Failure"},
            {"354908", "AES IP-Link Modem Failure Restauration"},
            {"354915", "AES RADIO DIAGNOSTICS FAILED"},
            {"354915", "AES RADIO DIAGNOSTICS FAILED Restauration"},
            {"355906", "AES IP-Link RF Ping Failure"},
            {"355906", "AES IP-Link RF Ping Failure Restauration"},
            {"356903", "AES Radio Comm Failure"},
            {"356903", "AES Radio Comm Failure Restauration"},
            {"361", "TELCO FAULT"},
            {"361", "TELCO FAULT Restauration"},
            {"362", "TELCO 2 FAULT"},
            {"362", "TELCO 2 FAULT Restauration"},
            {"365903", "AES Radio Failure"},
            {"365903", "AES Radio Failure Restauration"},
            {"370009", "AES Radio Battery Charger Failure"},
            {"370009", "AES Radio Battery Charger Failure Restauration"},
            {"370010", "AES Radio Ground Fault"},
            {"370010", "AES Radio Ground Fault Restauration"},
            {"379", "UNDEFINED DSC CONTACT ID CODE"},
            {"379", "UNDEFINED DSC CONTACT ID CODE Restauration"},
            {"399", "Trouble"},
            {"399", "Trouble Restauration"},
            {"3F0", "UNDEFINED DSC CONTACT ID CODE"},
            {"3F0", "UNDEFINED DSC CONTACT ID CODE Restauration"},
            {"3F1", "UNDEFINED DSC CONTACT ID CODE"},
            {"410", "OPEN"},
            {"440", "PARTIAL CLOSE"},
            {"440", "PARTIAL CLOSE Restauration"},
            {"460", "UNDEFINED DSC CONTACT ID CODE"},
            {"460", "UNDEFINED DSC CONTACT ID CODE Restauration"},
            {"470", "PARTIAL CLOSE"},
            {"470", "PARTIAL CLOSE Restauration"},
            {"474", "KEY SWITCH"},
            {"474", "KEY SWITCH Restauration"},
            {"481", "OPEN"},
            {"482", "UNDEFINED DSC CONTACT ID CODE"},
            {"482", "UNDEFINED DSC CONTACT ID CODE Restauration"},
            {"491", "CLOSE"},
            {"4B1", "UNDEFINED DSC CONTACT ID CODE"},
            {"4B9", "UNDEFINED DSC CONTACT ID CODE"},
            {"4C1", "UNDEFINED DSC CONTACT ID CODE"},
            {"500", "SYSTEM DISABLED"},
            {"510", "SYSTEM DISABLED"},
            {"600", "UNDEFINED DSC CONTACT ID CODE"},
            {"607915", "AHJ INSPECTION STARTED"},
            {"60B", "UNDEFINED DSC CONTACT ID CODE"},
            {"610", "TEST"},
            {"634", "TEST"},
            {"667", "TEST"},
            {"680", "UNDEFINED DSC CONTACT ID CODE"},
            {"692", "Test/Misc."},
            {"698", "Test/Misc."},
            {"699", "Test/Misc."},
            {"6EE", "UNDEFINED DSC CONTACT ID CODE"},
            {"777", "Burg – Crash and Smash"},
            {"777", "Burg – Crash and Smash Restauration"},
            {"900", "Test"},
            {"912", "FIRE ALARM SILENCE"},
            {"912", "FIRE ALARM SILENCE Restauration"},
            {"995", "NON-CRITICAL RECEIVER MESSAGE"},
            {"996", "DISK SPACE LOW"},
            {"996", "DISK SPACE LOW Restauration"},
            {"997", "RECEIVER MESSAGE"},
            {"998", "UNKNOWN SIGNAL"},
            {"999", "General Alarm"},
            {"999", "General Alarm Restauration"},
            {"ode", "name"},
            {"126", "Hold-up suspicion print"},
            {"129", "Panic Verifier"},
            {"ode", "name"},
            {"126", "Hold-up suspicion print"},
            {"129", "Panic Verifier"},
            {"168", "High Humidity"},
            {"169", "Low Humidity"},
            {"314", "Primary Power Supply Failure"},
            {"316", "System Tamper"},
            {"345", "AES Encryption disabled/ enabled"},
            {"358", "Periodic Comm Test Fail /Restore"},
            {"358", "Periodic Comm Test Fail /Restore"},
            {"394", "CO Detector needs replacement"},
            {"443", "Armed with System Trouble Override"},
            {"457", "Exit Error (user)"},
            {"458", "User on Premises"},
            {"463", "Re-arm after Alarm"},
            {"464", "Auto-arm Time Extended"},
            {"465", "Panic Alarm Reset"},
            {"466", "Service On/Off Premises"},
            {"411", "Callback request made"},
            {"412", "Successful download/access"},
            {"413", "Unsuccessful access"},
            {"414", "System shutdown command received"},
            {"415", "Dialer shutdown command received"},
            {"416", "Successful Upload"},
            {"578", "Vault Bypass"},
            {"579", "Vent Zone Bypass"},
            {"604", "Fire test"},
            {"605", "Status report to follow"},
            {"606", "Listen-in to follow"},
            {"607", "Walk test mode"},
            {"608", "Periodic test - System Trouble Present"},
            {"609", "Video Xmitter active"},
            {"611", "Point tested OK"},
            {"612", "Point not tested"},
            {"613", "Intrusion Zone Walk Tested"},
            {"614", "Fire Zone Walk Tested"},
            {"615", "Panic Zone Walk Tested"},
            {"616", "Service Request"},
            {"621", "Event Log reset"},
            {"622", "Event Log 50% full"},
            {"623", "Event Log 90% full"},
            {"624", "Event Log overflow"},
            {"625", "Time/Date reset"},
            {"626", "Time/Date inaccurate"},
            {"627", "Program mode entry"},
            {"628", "Program mode exit"},
            {"629", "32 Hour Event log marker"},
            {"630", "Schedule change"},
            {"631", "Exception schedule change"},
            {"641", "Senior Watch Trouble"},
            {"642", "Latch-key Supervision"},
            {"651", "Reserved for Ademco Use"},
            {"652", "Reserved for Ademco Use"},
            {"653", "Reserved for Ademco Use"},
            {"654", "System Inactivity"},
            {"655", "User Code X modified by Installer"},
            {"703", "Auxiliary #3"},
            {"796", "Unable to output signal (Derived Channel)"}};
}
