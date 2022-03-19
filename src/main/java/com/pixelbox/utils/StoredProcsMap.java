package com.pixelbox.utils;

import java.util.HashMap;

public class StoredProcsMap {
	
	//define the procedure id  for login here
	public final static int LOGIN_AUTHENTICATION = 1;
	public final static int SIGNUP = 2;
	public final static int USER_DETAILS_GENERAL = 3;
	public final static int USER_DETAILS_BASIC = 4;
	public final static int USER_DETAILS_PERSONAL_CONTACT = 5;
	public final static int USER_DETAILS_EDUCATION = 6;	
	public final static int USER_DETAILS_EMPLOYMENT = 7;	
	public final static int USER_DETAILS_BUSINESS = 8;	
	public final static int ADD_CARD = 9;	
	public final static int USER_CONTACTS_SHARE = 10;	
	public final static int OWN_CARD_BASIC_AND_PERSONAL_FIELDS = 11;	
	public final static int OWN_CARD_EMPLOYMENT_FIELDS = 12;	
	public final static int OWN_CARD_BUSINESS_FIELDS = 13;	
	public final static int OWN_CARD_PERSONAL_SOCIAL_MEDIA_FIELDS = 14;	
	public final static int GET_OWN_CARDS = 15;	
	public final static int GET_OWN_CARD_BASIC_PERSONAL_DETAILS = 16;	
	public final static int GET_OWN_CARD_EMPLOYMENT_DETAILS = 17;	
	public final static int GET_OWN_CARD_BUSINESS_DETAILS = 18;	
	public final static int GET_OWN_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS = 19;	
	public final static int GET_CARDS = 20;	
	public final static int SHARE_CARD_WITH_HOOP_USER = 21;	
	public final static int SEARCH_USERS = 22;	
	public final static int GET_LOGGED_IN_USER_NAME = 23;	
	public final static int GET_HOOP_USERS_MATCHING_WITH_CONTACTS = 24;	
	public final static int GET_USER_DETAILS_BASIC = 25;	
	public final static int GET_USER_DETAILS_GENERAL = 26;	
	public final static int GET_USER_DETAILS_PERSONAL_CONTACT = 27;	
	public final static int GET_USER_DETAILS_EDUCATION = 28;	
	public final static int GET_USER_DETAILS_EMPLOYMENT = 29;	
	public final static int GET_USER_DETAILS_BUSINESS = 30;	
		
	public final static int UPDATE_USER_DETAILS_BASIC = 31;	
	public final static int UPDATE_USER_DETAILS_GENERAL = 32;	
	public final static int UPDATE_USER_DETAILS_PERSONAL_CONTACT = 33;	
	public final static int UPDATE_USER_DETAILS_EDUCATION = 34;	
	public final static int UPDATE_USER_DETAILS_EMPLOYMENT = 35;	
	public final static int UPDATE_USER_DETAILS_BUSINESS = 36;		
	public final static int GET_USER_REFERRED_CONTACTS = 37;	
	public final static int GET_USER_DETAILS_PERSONAL_SOCIAL_MEDIA = 38;	
	public final static int UPDATE_USER_DETAILS_PERSONAL_SOCIAL_MEDIA = 39;	
	public final static int USER_DETAILS_PERSONAL_SOCIAL_MEDIA = 40;	
	public final static int GET_ALL_CONNECTED_USERS = 41;	
	public final static int GET_FILTERED_CONNECTED_USERS_BY_LOCATION = 42;	
	public final static int ADD_USER_DETAILS_UPDATE_NOTIFICATIONS = 43;	
	public final static int GET_USER_DETAILS_UPDATE_NOTIFICATIONS = 44;	
	public final static int SET_USER_DETAILS_UPDATE_NOTIFICATIONS_VIEWED = 45;	
	public final static int DELETE_USER_DETAILS_UPDATE_NOTIFICATIONS_RECORD = 46;	
	public final static int ADD_OWN_CARD_SHARE_NOTIFICATION = 47;	
	public final static int GET_OWN_CARD_SHARE_NOTIFICATIONS = 48;	
	public final static int SET_OWN_CARD_SHARE_NOTIFICATIONS_VIEWED = 49;	
	public final static int DELETE_OWN_CARD_SHARE_NOTIFICATION_RECORD = 50;	
	public final static int ADD_USER_DETAIL_MISSING_NOTIFICATION = 51;	
	public final static int GET_USER_DETAIL_MISSING_NOTIFICATIONS = 52;	
	public final static int SET_USER_DETAIL_MISSING_NOTIFICATIONS_VIEWED = 53;	
	public final static int SHARE_CARD_WITH_NON_HOOP_USER = 54;	
	public final static int ADD_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION = 55;	
	public final static int GET_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATIONS = 56;	
	public final static int SET_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_VIEWED = 57;	
	public final static int DELETE_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_RECORD = 58;	
	public final static int GET_CARD_OWNER = 59;	
	public final static int GET_RECEIVED_CARDS = 60;	
	public final static int SET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS = 61;	
	public final static int GET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_NOTIFICATIONS = 62;	
	public final static int SET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_NOTIFICATIONS_VIEWED = 63;	
	public final static int GET_USER_DETAILS_MISSING = 64;	
	public final static int GET_IMAGE_URL_FROM_USERNAME = 65;	
	public final static int GET_IMAGE_URL_FROM_USERID = 66;	
	public final static int CREATE_GROUP = 67;	
	public final static int ADD_GROUP_MEMBERS = 68;	
	public final static int GET_GROUP_MEMBERS = 69;	
	public final static int UPDATE_GROUP_MEMBERS = 70;	
	public final static int GET_RECENT_CONNECTED_USERS = 71;	
	public final static int GET_FILTERED_CONNECTED_USERS_BY_GENDER = 72;	
	public final static int GET_FILTERED_CONNECTED_USERS_BY_AGE = 73;	
	public final static int GET_FILTERED_CONNECTED_USERS_BY_INDUSTRY = 74;	
	public final static int GET_FILTERED_CONNECTED_USERS_BY_PROFESSION = 75;	
	public final static int GET_GROUPS = 76;	
	public final static int GET_CARD_NAME_FROM_CARD_ID = 77;	
	public final static int DELETE_OWN_CARD = 78;	
	public final static int SHARE_CARD_WITH_HOOP_USER_VIA_BLUETOOTH = 79;	
	public final static int GET_HOOP_USERS_MATCHING_BY_ANY_INFO = 80;	
	public final static int GET_USER_DETAILS_MISSING_ALREADY_SHARED_ON_REQUEST = 81;	
	public final static int GET_CONNECTED_USERS_MATCHING_BY_ANY_INFO = 82;	
		
	public final static int CUSTOM_CARD_BASIC_AND_PERSONAL_FIELDS = 83;	
	public final static int CUSTOM_CARD_EMPLOYMENT_FIELDS = 84;	
	public final static int CUSTOM_CARD_BUSINESS_FIELDS = 85;	
	public final static int CUSTOM_CARD_PERSONAL_SOCIAL_MEDIA_FIELDS = 86;	
	
	public final static int SHARE_CUSTOM_CARD = 87;	
	
	public final static int ADD_CUSTOM_CARD_SHARE_NOTIFICATION = 88;	
	
	public final static int GET_CUSTOM_CARD_BASIC_PERSONAL_DETAILS = 89;	
	public final static int GET_CUSTOM_CARD_EMPLOYMENT_DETAILS = 90;	
	public final static int GET_CUSTOM_CARD_BUSINESS_DETAILS = 91;	
	public final static int GET_CUSTOM_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS = 92;	
	
	public final static int GET_CUSTOM_CARD_OWNER = 93;	
	
	public final static int GET_USER_DETAIL_SHARED_THROUGH_CUSTOM_CARD = 94;	
	public final static int DELETE_GROUP = 95;	
	public final static int GET_NON_HOOP_CONTACTS = 96;	
	public final static int GET_USER_DETAIL_SHARED_THROUGH_NORMAL_CARD = 97;	
	public final static int ACTIVATE_USER_WITH_OTP = 98;	
	public final static int GET_SINGLE_CONNECTED_USER_DETAILS = 99;	
	
	
	public final static int ADD_DRIVER_DETAILS = 100;
	public final static int ADD_SELLER_FUEL = 101;
	public final static int ADD_SELLER_DISPATCH = 102;
	public final static int ADD_SELLER_INVOICE = 103;
	public final static int ADD_SELLER_PURCHASE = 104;
	public final static int ADD_STORAGE_FUEL = 105;
	public final static int ADD_STORAGE_DISPATCH = 106;
	public final static int ADD_STORAGE_INVOICE = 107;
	public final static int ADD_STORAGE_INCOMING_LOAD = 108;
	public final static int ADD_TRUCKS_ALLOTMENT = 109;
	public final static int ADD_TRUCKS_DETAILS = 110;
	public final static int ADD_TRUCKS_HEALTH_HISTORY = 111;
	public final static int ADD_TRUCKS_PARTS_QUOTE = 112;
	// public final static int ADD_TRUCKS_SERVICE = 113;
	public final static int ADD_SPARE_PART_ALLOTMENT = 114;
	
	
	public final static int GET_STORAGE_DISPATCH = 115;
	public final static int GET_STORAGE_FUEL = 116;
	public final static int GET_STORAGE_INCOMING_LOAD = 117;
	public final static int GET_STORAGE_INVOICE = 118;
	
	public final static int GET_SELLER_DISPATCH = 119;
	public final static int GET_SELLER_FUEL = 120;
	public final static int GET_SELLER_INVOICE = 121;
	public final static int GET_SELLER_PURCHASE = 122;
	
	public final static int GET_TRUCKS_ALLOTMENT = 123;
	public final static int GET_TRUCKS_DETAILS = 124;
	public final static int GET_TRUCKS_PARTS_QUOTE = 125;
	public final static int GET_TRUCKS_HEALTH_HISTORY= 126;
	public final static int GET_TRUCKS_SERVICE= 127;
	public final static int GET_SPARE_PART_ALLOTMENT= 128;
	public final static int GET_STORAGE_DISPATCH_BY_DATE= 129;
	public final static int GET_STORAGE_FUEL_BY_DATE= 130;
	public final static int GET_STORAGE_INCOMING_LOAD_BY_DATE= 131;
	public final static int GET_STORAGE_INVOICE_BY_DATE= 132;
	public final static int GET_SELLER_DISPATCH_BY_DATE= 133;
	public final static int GET_SELLER_FUEL_BY_DATE= 134;
	public final static int GET_SELLER_INVOICE_BY_DATE= 135;
	public final static int GET_SELLER_PURCHASE_BY_DATE= 136;
	public final static int GET_TRUCKS_ALLOTMENT_BY_DATE= 137;
	public final static int GET_TRUCKS_HEALTH_HISTORY_BY_DATE= 138;
	public final static int GET_TRUCKS_PARTS_QUOTE_BY_DATE= 139;
	public final static int GET_TRUCKS_SERVICE_BY_DATE= 140;
	public final static int GET_SPARE_PART_ALLOTMENT_BY_DATE= 141;
	
	public final static int UPDATE_DRIVER_DETAILS = 142;
	public final static int UPDATE_STORAGE_DISPATCH = 143;
	public final static int UPDATE_STORAGE_FUEL = 144;
	public final static int UPDATE_STORAGE_INVOICE = 145;
	public final static int UPDATE_STORAGE_INCOMING_LOAD = 146;
	public final static int UPDATE_SELLER_FUEL = 147;
	public final static int UPDATE_SELLER_DISPATCH = 148;
	public final static int UPDATE_SELLER_INVOICE = 149;
	public final static int UPDATE_SELLER_PURCHASE = 150;
	public final static int UPDATE_TRUCKS_ALLOTMENT = 151;
	public final static int UPDATE_TRUCKS_DETAILS = 152;
	public final static int UPDATE_TRUCKS_HEALTH_HISTORY = 153;
	public final static int UPDATE_TRUCKS_PARTS_QUOTE = 154;
	public final static int UPDATE_TRUCKS_SERVICE = 155;
	public final static int UPDATE_SPARE_PART_ALLOTMENT = 156;
	public final static int GET_ALL_DRIVER_DETAILS = 157;
	public final static int GET_DRIVER_DETAILS = 158;
	public final static int GET_ALL_SPARE_PARTS = 159;
	public final static int GET_ALL_TRUCKS_DETAILS = 160;
	
	public final static int GET_STATUS_DISPATCH = 161;
	public final static int UPDATE_STATUS_DISPATCH = 162;
	public final static int GET_CLOSE_DISPATCH = 163;
	public final static int UPDATE_CLOSE_DISPATCH = 164;
	
	public final static int GET_STATUS_STORAGE_DISPATCH = 165;
	public final static int UPDATE_STATUS_STORAGE_DISPATCH = 166;
	public final static int GET_CLOSE_STORAGE_DISPATCH = 167;
	public final static int UPDATE_CLOSE_STORAGE_DISPATCH = 168;
	
	
	//--------------------------------------------------------
	
//	public final static int ADD_DRIVER_DETAILS=118;
	public final static int UPDATE_FACTORY_DISPATCH_INITIALLY=169;
	public final static int ADD_FACTORY_FUEL=170;
	public final static int ADD_FACTORY_INVOICE=171;
	public final static int ADD_VEHICLE_TYRE_SUMMARY_REPORT=172;
	public final static int ADD_FRONT_TOTAL_SUMMARY=173;
	public final static int ADD_HOSING_TOTAL_SUMMARY =174;
	public final static int ADD_LOCATION_ALLOTMENT =175;
	public final static int ADD_LOCATION_STATUS =176;
//	public final static int GET_DRIVER_DETAILS =127;
	public final static int GET_FACTORY_DISPATCH =177;
	public final static int GET_FACTORY_FUEL =178;
	public final static int GET_FACTORY_INVOICE =179;
	public final static int GET_VEHICLE_TYRE_SUMMARY_REPORT=180;
	public final static int GET_LOCATION_ALLOTMENT =181;
	public final static int GET_LOCATION_STATUS=182;
	public final static int GET_FACTORY_DISPATCH_BY_DATE=183;
	public final static int GET_FACTORY_FUEL_BY_DATE=184;
	public final static int GET_FACTORY_INVOICE_BY_DATE=185;
	public final static int GET_LOCATION_ALLOTMENT_BY_DATE=186; 
	public final static int GET_LOCATION_STATUS_BY_DATE=187; 
//	public final static int UPDATE_DRIVER_DETAILS=139;
	public final static int UPDATE_FACTORY_DISPATCH=189;
	public final static int UPDATE_FACTORY_FUEL=190;
	public final static int UPDATE_FACTORY_INVOICE=191;
	public final static int UPDATE_LOCATION_ALLOTMENT =192;
	public final static int UPDATE_LOCATION_STATUS=193;
	public final static int GET_VEHICLE_TYRE_SUMMARY_REPORT_BY_DATE=194;
//	public final static int GET_ALL_DRIVER_DETAILS =146;
	public final static int UPDATE_VEHICLE_TYRE_SUMMARY_REPORT=195;
//	public final static int ADD_TRUCKS_DETAILS=148;
//	public final static int GET_TRUCKS_DETAILS=149;
//	public final static int GET_ALL_TRUCKS_DETAILS=150;
//	public final static int UPDATE_TRUCKS_DETAILS=151;
	public final static int GET_FACTORY_DISPATCH_STATUS=196;
	public final static int UPDATE_FACTORY_DISPATCH_STATUS=197;
	public final static int GET_CLOSE_FACTORY_DISPATCH=198;
	public final static int UPDATE_CLOSE_FACTORY_DISPATCH=199;

	public final static int ADD_REGISTRATION_USERS=200;
	public final static int ADD_REGISTRATION_BASIC=201;
	public final static int GET_ACTIONS_LOG=202;
	public final static int ADD_FACTORY_TRIP_DETAILS=203;
	public final static int GET_TRIP_DETAILS=204;
	public final static int UPDATE_TRIP_DETAILS=205;
	public final static int GET_CLOSE_TRIP_DETAILS=206;
	public final static int UPDATE_CLOSE_TRIP_DETAILS=207;
	
	public final static int GET_DRIVER_SALARY_SLIP=208;
	public final static int GET_TRIP_BY_DATE=209;
	
	public final static int ADD_STORAGE_TRIP_DETAILS=210;
	public final static int GET_STORAGE_TRIP_DETAILS=211;
	public final static int UPDATE_STORAGE_TRIP_DETAILS=212;
	public final static int GET_CLOSE_STORAGE_TRIP_DETAILS=213;
	public final static int UPDATE_CLOSE_STORAGE_TRIP_DETAILS=214;
	public final static int GET_STORAGE_TRIP_BY_DATE=215;
	
	public final static int ADD_SELLER_TRIP_DETAILS=216;
	public final static int GET_SELLER_TRIP_DETAILS=217;
	public final static int UPDATE_SELLER_TRIP_DETAILS=218;
	public final static int GET_CLOSE_SELLER_TRIP_DETAILS=219;
	public final static int UPDATE_CLOSE_SELLER_TRIP_DETAILS=220;
	public final static int GET_SELLER_TRIP_BY_DATE=221;
	public final static int ADD_SPARE_PART_INVENTORY = 222;
	public final static int ADD_TYRE_INVENTORY = 223;

	public final static int GET_TRUCKS_SERVICE_BY_Truck=224;
	public final static int GET_ALL_TYRES = 225;
	public final static int GET_AVAILABLE_AND_ENGAGED_DRIVER_DETAILS=226;
	public final static int GET_ALL_BRAND_NAMES = 227;
	public final static int GET_ALL_RUNNING_BRAND_NAMES = 228;
	public final static int GET_ALL_TYRES_BY_CATEGORY = 229;
	public final static int ADD_ISSUED_TYRES=230;
	public final static int ADD_RECOUP_TYRES=231;
	public final static int GET_ALL_RUNNING_TYRES = 232;
	public final static int GET_ALL_PENDING_BRAND_NAMES = 233;
	public final static int GET_ALL_PENDING_TYRES = 234;
	public final static int UPDATE_RECOUP_TYRE_STATUS = 235;
	
	public final static int GET_DRIVER_ALLOTMENT_TO_TRUCK_BY_DATE= 236;
	public final static int ADD_DRIVER_ALLOTMENT_TO_TRUCK = 237;
	public final static int GET_AVAILABLE_AND_ENGAGED_TRUCKS_DETAILS=238;
	public final static int GET_ALLOTED_TRUCKS=239;
	public final static int GET_FACTORY_DISPATCHED_TRUCKS=240;
	public final static int GET_FACTORY_UNLOADING_TRUCKS=241;
	public final static int ADD_TRUCKS_SERVICE = 242;
	public final static int CLOSE_TRUCKS_SERVICE=243;

	public final static int GET_TRUCKS_SERVICE_BY_TRUCKNUMBER=244;
	public final static int GET_STORAGE_DISPATCHED_TRUCKS=245;
	public final static int GET_STORAGE_UNLOADING_TRUCKS=246;
	public final static int GET_SELLER_DISPATCHED_TRUCKS=247;
	public final static int GET_SELLER_UNLOADING_TRUCKS=248;
	public final static int GET_RUNNING_TYRES=249;
	public final static int GET_ISSUED_TYRES=250;
	public final static int GET_WASTE_TYRES=251;
	public final static int GET_ALL_TRUCKS = 252;
	public final static int GET_RECOUP_TYRES = 253;
	public final static int CHANGE_PASSWORD=254;
	public final static int  SET_LINK_FOR_NEW_PASSWORD=255;
	public final static int SENT_EMAIL_FOR_FORGOT_PASSWORD=256;
	public final static int GET_TRUCKS = 257;
	public final static int GET_DRIVERS = 258;
	public final static int GET_TYRES_CURRENT_INVENTORY = 259;
	public final static int GET_TYRES_INVENTORY_HISTORY = 260;
	public final static int GET_SPARE_PART_CURRENT_INVENTORY = 261;
	public final static int GET_SPARE_PART_INVENTORY_HISTORY = 262;
	public final static int GET_OIL_BRAND_NAMES = 263;
	public final static int GET_OIL_DISTINCT_BRAND_NAMES = 264;
	public final static int ADD_OIL_INVENTORY = 265;
	public final static int ADD_ISSUED_OIL= 266;	
	public final static int GET_OIL_CURRENT_INVENTORY= 267;
	public final static int GET_OIL_INVENTORY_HISTORY= 268;
	public final static int GET_ISSUED_OIL= 269;
	public final static int ADD_OIL_BRAND_NAME= 270;
	public final static int GET_BATTERY_BRAND_NAMES = 271;
	public final static int ADD_BATTERY_BRAND_NAME= 272;
	public final static int ADD_ISSUED_BATTERY=273;
	public final static int ADD_BATTERY_INVENTORY=274;
	public final static int GET_INVENTORY_BATTERY_BRANDS = 275;
	public final static int GET_RUNNING_BATTERY_BRANDS = 276;
	public final static int GET_INVENTORY_BATTERY_NUMBERS = 277;
	public final static int GET_RUNNING_BATTERY_NUMBERS = 278;
	public final static int GET_BATTERY_CURRENT_INVENTORY = 279;
	public final static int GET_BATTERY_INVENTORY_HISTORY = 280;
	public final static int GET_RUNNING_BATTERY = 281;
	public final static int GET_WASTE_BATTERY = 282;
	public final static int GET_ISSUED_BATTERY = 283;
	public final static int GET_TRUCKS_DRIVER_CHANGE = 284;
	public final static int ADD_FACTORY_DRIVER_CHANGE = 285;
	public final static int GET_ENGAGED_TRUCKS = 286;
	public final static int GET_FACTORY_CHANGE = 287;
	public final static int ADD_STORAGE_DRIVER_CHANGE = 288;
	public final static int GET_STORAGE_CHANGE = 289;
	public final static int ADD_SELLER_DRIVER_CHANGE = 290;
	public final static int GET_SELLER_CHANGE = 291;
	public final static int GET_FACTORY_ENGAGED_TRUCKS = 292;
	public final static int GET_STORAGE_ENGAGED_TRUCKS = 293;
	public final static int GET_SELLER_ENGAGED_TRUCKS = 294;
	public final static int GET_ASSOCIATION = 295;
	public final static int GET_ASSIGNED_TRUCKS=296;
	public final static int GET_DRIVER_ALLOTMENT_TO_TRUCK=297;
	public final static int UPDATE_DRIVER_ALLOTMENT_TO_TRUCK=298;
	public final static int ADD_DRIVERS_IMAGE_URL=299;
	public final static int UPDATE_DRIVERS_IMAGE_URL=300;
	public final static int ADD_TRUCKS_IMAGE_URL=301;
	public final static int UPDATE_TRUCKS_IMAGE_URL=302;
	public final static int GET_FACTORY_DRIVER_EXPENDITURE=303;
	public final static int GET_FACTORY_DRIVERS_CLOSE_EXPENDITURE=304;
	public final static int ADD_FACTORY_DRIVER_EXPENDITURE=305;
	public final static int GET_DRIVER_BALANCE=306;
	public final static int GET_SELLER_DRIVER_EXPENDITURE=307;
	public final static int GET_SELLER_DRIVERS_CLOSE_EXPENDITURE=308;
	public final static int ADD_SELLER_DRIVER_EXPENDITURE=309;
	public final static int GET_STORAGE_DRIVER_EXPENDITURE=310;
	public final static int GET_STORAGE_DRIVERS_CLOSE_EXPENDITURE=311;
	public final static int ADD_STORAGE_DRIVER_EXPENDITURE=312;
	public final static int GET_TRIP_STARTED_TRUCKS=313;
	public final static int GET_TRIP_DRIVER_EXPENDITURE=314;
	public final static int GET_TRIP_DRIVERS_CLOSE_EXPENDITURE=315;
	public final static int ADD_TRIP_DRIVER_EXPENDITURE=316;
	public final static int GET_TRUCK_STATUS=317;
	public final static int ADD_FACTORY_LOADING=318;
	public final static int ADD_TRUCK_IDLE_STATUS=319;
	public final static int GET_LOGIN_USER_DETAILS=320;
	public final static int GET_ASSIGNED_AVAILABLE_TRUCKS=321;
	public final static int GET_TRUCKS_STATUS_TRACKING=322;
	public final static int GET_TRUCKS_GRID_CALENDAR=323;
	public final static int GET_EXPIRY_TRUCKS_PERMIT=324;
	public final static int GET_EXPIRY_TRUCKS_INSURANCE=325;
	public final static int GET_EXPIRY_TRUCKS_FITNESS=326;
	public final static int ADD_DISPATCH_STARTING_METER_READING_IMAGE_UPLOAD_URL=327;
	public final static int ADD_DISPATCH_CLOSING_METER_READING_IMAGE_UPLOAD_URL=328;
	public final static int GET_TRUCK_CURRENT_DAY_STATUS_DETAILS=329;
	public final static int ADD_TRIP_STARTING_METER_READING_IMAGE_UPLOAD_URL=330;
	public final static int ADD_TRIP_CLOSING_METER_READING_IMAGE_UPLOAD_URL=331;
	public final static int ADD_TRUCK_MAINTENANCE_ADVANCE_REQUEST=332;
	public final static int GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_NUMBER=333;
	public final static int GET_TRUCK_MAINTENANCE_ADVANCE_ITEM_DETAILS=334;
	public final static int GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_COUNT=335;
	public final static int UPDATE_TRUCK_MAINTENANCE_ADVANCE_APPROVAL_STATUS=336;
	public final static int GET_AVAILABLE_DRIVERS = 337;
	public final static int GET_FUEL_STATION_NAME = 338;
	public final static int GET_DRIVER_SALARY=339;
	public final static int UPDATE_DRIVER_SALARY_PAYMENT=340;
	public final static int ADD_DRIVER_SALARY_PAYMENT_HISTORY=341;
	public final static int GET_FACTORIES_CUMULATIVE_REPORT=342;
	public final static int GET_FACTORIES_ASSOCIATION = 343;
	public final static int TOTAL_DISPATCH_REPORT = 344;
	public final static int GET_UNLOAD_LOCATION_NAMES = 345;
	public final static int ADD_OUTSIDE_FACTORY_DISPATCH = 346;
	public final static int GET_OUTSIDE_COMPANY_NAMES = 347;
	public final static int GET_FACTORY_DISPATCH_DAILY_REPORT = 348;
	public final static int GET_QUANTITY_AND_FREIGHT = 349;
	public final static int GET_FACTORY_DISPATCH_BILL=350;
	public final static int GET_FACTORY_DISPATCH_BILL_COUNT=351;
	public final static int ADD_TRUCK_MAINTENANCE_EXPENDITURE=352;
	public final static int GET_REQUEST_OIL=353;
	public final static int UPDATE_OIL_INVENTORY_COUNT=354;
	public final static int GET_TRUCK_OVERALL_MAINTENANCE_REPORT=355;
	public final static int ADD_LAT_LONG=356;
	public final static int GET_DRIVER_FULL_DETAILS = 357;
	public final static int ADD_PETROL_PUMP_READING_IMAGE_UPLOAD_URL = 358;
	public final static int GET_FACTORY_DISPATCH_BY_ID=359;
	public final static int GET_LAT_LONG=360;
	public final static int UPDATE_OUTSIDE_FACTORY_DISPATCH=361;
	public final static int GET_FACTORY_DISPATCH_FUEL_BY_DATE=362;
	public final static int CHECK_SPARE_PARTS_COUNT=363;
	public final static int GET_TRUCKS_STATUS_TRACKING_LIST=364;
	public final static int GET_TRUCKS_MAINTENANCE_COST=365;
	public final static int GET_TRUCKS_TRIP_COUNT=366;
	
	public final static int ADD_CASH_AND_BANK_ACCOUNT=367;
	public final static int ADD_ACCOUNT_NAME=368;
	public final static int ADD_PAYMENT=369;
	public final static int ADD_RECEIPT=370;
	public final static int ADD_INCOME=371;
	public final static int ADD_EXPENDITURE=372;
	public final static int GET_LEDGER=373;
	public final static int GET_CASH_AND_BANK_NAME=374;
	public final static int GET_ACCOUNT_NAME=375;
	public final static int GET_ALL_ACCOUNTS=376;
	public final static int GET_TRIAL_BALANCE=377;
	public final static int GET_PURCHASE_ACCOUNT_NAMES=378;
	public final static int GET_SALES_AND_INCOME_ACCOUNT_NAMES=379;
	public final static int GET_VENDOR_AND_CUSTOMER_ACCOUNT_NAMES=380;
	public final static int GET_VEHICLE_EXPENSES_ACCOUNT_NAMES=381;
	public final static int GET_ACCOUNT_GROUP_NAMES=382;
	public final static int GET_BALANCE_SHEET=383;
	public final static int GET_PROFIT_AND_LOSS=384;
	public final static int GET_ACCOUNT_TREE_NAMES=385;
	public final static int GET_OPENING_BALANCE=386;
	public final static int GET_TRUCK_MAINTENANCE_EXPENDITURE=387;
	public final static int GET_UNLOAD_REPORT=388;
	public final static int GET_UNLOAD_REPORT_BY_ID=389;
	public final static int UPDATE_UNLOAD_REPORT=390;
	public final static int GET_PAYMENT=391;
	public final static int GET_RECEIPT=392;
	public final static int GET_EXPENDITURE=393;
	public final static int GET_INCOME=394;
	public final static int UPDATE_PAYMENT=395;
	public final static int UPDATE_RECEIPT=396;
	public final static int UPDATE_INCOME=397;
	public final static int UPDATE_EXPENDITURE=398;
	public final static int DELETE_PAYMENT=399;
	public final static int DELETE_RECEIPT=400;
	public final static int DELETE_INCOME=401;
	public final static int DELETE_EXPENDITURE=402;
	public final static int GET_ALL_AND_OTHER_TRUCKS = 403;
	public final static int ADD_OPENING_BALANCE=404;
	public final static int GET_OPENING_BALANCE_FOR_TB=405;
	public final static int ADD_JOURNAL_ENTRY=406;
	public final static int GET_OPENING_BALANCES=407;
	public final static int UPDATE_OPENING_BALANCES=408;
	public final static int DELETE_OPENING_BALANCE=409;
	public final static int GET_TRUCK_PROFIT_AND_LOSS=410;
	
	//-------------------
	//Last Initialized number is  : 370
	
	public final static int GET_FACTORY_FUEL_BY_FUEL_STATION=411;
	public final static int GET_USER_LOGIN_DETAILS=412;

	public final static int ADD_INWARD=413;
	public final static int GET_ACTIONS=414; 
	public final static int GET_INWARD = 415;
	public final static int UPDATE_INWARD = 416;
	public final static int DELETE_INWARD = 417;
	public final static int GET_UNLOAD = 418;
	public final static int GET_BILL = 419;
	public final static int GET_TYPECEMENT = 420;
	public final static int ADD_OUTWARD=421;
	public final static int GET_OUTWARD = 422;
	public final static int UPDATE_OUTWARD = 423;
	public final static int DELETE_OUTWARD = 424;
	public final static int GET_HAMALI = 425;
	public final static int ADD_BILL=426;
	public final static int GET_HAMALI_ACCOUNT = 427;
	public final static int GET_HAMALI_TYPE=428;
	public final static int GET_BILL_DETAILS = 429;
	public final static int ADD_INWARD_TRUCK_OR_WORK_IMAGE_URLS=430;
	public final static int ADD_OUTWARD_TRUCK_OR_WORK_IMAGE_URLS=431;
	public final static int UPDATE_BILL = 432;
	public final static int DELETE_BILL = 433;
	public final static int GET_ASSOCIATION_GODOWN = 434;
	public final static int ADD_OUTWARD_HAMALI = 435;
	public final static int GET_OUTWARD_HAMALI = 436;
	public final static int ADD_HAMALI_DETAILS=437;
	public final static int GET_HAMALI_DETAILS = 438;
	public final static int UPDATE_HAMALI_DETAILS = 439;
	public final static int DELETE_HAMALI_DETAILS = 440;
	
	public final static int ADD_ADVANCE_BOOKING=441;
	public final static int GET_ADVANCE_BOOKING = 442;
	public final static int UPDATE_ADVANCE_BOOKING = 443;
	public final static int DELETE_ADVANCE_BOOKING = 444;
	public final static int ADD_CUSTOMER=445;
	public final static int GET_CUSTOMER=446;
	public final static int GET_OUTWARD_CUSTOMER = 447;
	public final static int UPDATE_OUTWARD_CUSTOMER = 448;
	public final static int DELETE_OUTWARD_CUSTOMER = 449;
	public final static int GET_DC_PENDING=450;
	public final static int UPDATE_DC_PENDING = 451;
	public final static int DELETE_DC_PENDING = 452;
	
	public final static int ADD_ACCOUNT_GROUP=453;
	public final static int GET_ACCOUNT_GROUPS=454;
	public final static int UPDATE_ACCOUNT_GROUP=455;
	public final static int DELETE_ACCOUNT_GROUP=456;
	
	public final static int GET_TRUCK_MAINTENANCE_EXPENDITURE_REPORT=457;
	public final static int GET_FREIGHT_AND_QUANTITY=458;
	public final static int GET_FUEL_COST_REPORT=459;
	public final static int GET_FUEL_STATION_COST_REPORT=460;
	public final static int DELETE_FACTORY_DISPATCH=461;
	public final static int GET_FACTORY_DISPATCH_LOADING_REPORT = 462;
	public final static int GET_INWARD_ACTIONS=463;
	public final static int GET_OUTWARD_ACTIONS=464;
	public final static int GET_TODAY_PAYMENT_RECEIPT_AMOUNT=465;
	public final static int GET_FREIGHT_AND_QUANTITY_MONTH=466;
	public final static int GET_DISPATCH_REPORT_FOR_BILL=467;
	public final static int UPDATE_DISPATCH_BILL_IDS=468;
	public final static int GET_GENERATED_BILLS=469;
	public final static int GET_GENERATED_BILLS_GROUP=470;
	public final static int ADD_DRIVER_ADVANCE=471;
	public final static int GET_ALL_DRIVER_BALANCE=472;
	public final static int GET_DRIVER_EXPENDITURE_FOR_APPROVAL = 473;
	public final static int UPDATE_DRIVER_EXPENDITURE_APPROVAL=474;
	public final static int GET_DISPATCH_FUEL_LINKING=475;
	public final static int ADD_ERROR_FUEL=476;
	public final static int GET_STOCK = 477;
	public final static int GET_STOCK_TYPE=478;
	public final static int GET_ACTIONS_BILL_TYPE=479;
	public final static int ADD_EXCEL_BILL=480;
	public final static int GET_BILL_EXCEL = 481;
	public final static int GET_FACTORY_DISPATCH_FOR_INVOICE_PHOTO = 482;
	public final static int ADD_INVOICE_PHOTO = 483;

//--------------------------------------------------------------------------------------------------------
	//define the procedure name with params for login here
	public final static String LOGIN_AUTHENTICATION_STORED_PROC = 
			"call getPassword(? , ? , ? , ? , ?)";
	public final static String SIGNUP_STORED_PROC = 
			"call signup(? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String USER_DETAILS_GENERAL_STORED_PROC = 
			"call userDetailsGeneral(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String USER_DETAILS_BASIC_STORED_PROC = 
			"call userDetailsBasic(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String USER_DETAILS_PERSONAL_CONTACT_STORED_PROC = 
			"call userDetailsPersonalContact(? , ? , ? , ? , ? , ? , ? , ? ,  ? , ? , ? , ? , ?)";
	public final static String USER_DETAILS_EDUCATION_STORED_PROC = 
			"call userDetailsEducation(? , ? , ? , ? , ? , ? , ? , ? ,  ? , ? , ? , ? , ? , ?)";
	public final static String USER_DETAILS_EMPLOYMENT_STORED_PROC = 
			"call userDetailsEmployment(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String USER_DETAILS_BUSINESS_STORED_PROC = 
			"call userDetailsBusiness(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String ADD_CARD_STORED_PROC = 
			"call addCard(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String USER_CONTACTS_SHARE_STORED_PROC = 
			"call shareUserContacts(? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String OWN_CARD_BASIC_AND_PERSONAL_FIELDS_STORED_PROC = 
			"call addOwnCardBasicAndPersonalFields(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String OWN_CARD_EMPLOYMENT_FIELDS_STORED_PROC = 
			"call addOwnCardEmploymentFields(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String OWN_CARD_BUSINESS_FIELDS_STORED_PROC = 
			"call addOwnCardBusinessFields(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String OWN_CARD_PERSONAL_SOCIAL_MEDIA_FIELDS_STORED_PROC = 
			"call addOwnCardPersonalSocialMediaFields(? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String GET_OWN_CARDS_STORED_PROC = 
			"call getOwnCards(? , ? , ? , ?)";
	public final static String GET_OWN_CARD_BASIC_PERSONAL_DETAILS_STORED_PROC = 
			"call getOwnCardBasicPersonalDetails(? , ? , ? , ?)";
	public final static String GET_OWN_CARD_EMPLOYMENT_DETAILS_STORED_PROC = 
			"call getOwnCardEmploymentDetails(? , ? , ? , ? , ?)";
	public final static String GET_OWN_CARD_BUSINESS_DETAILS_STORED_PROC = 
			"call getOwnCardBusinessDetails(? , ? , ? , ? , ?)";
	public final static String GET_OWN_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS_STORED_PROC = 
			"call getOwnCardPersonalSocialMediaDetails(? , ? , ? , ?)";
	public final static String GET_CARDS_STORED_PROC = 
			"call getCards(? , ? , ? , ?)";
	public final static String SHARE_CARD_WITH_HOOP_USER_STORED_PROC = 
			"call shareCardWithHoopUser(? , ? , ?  , ? , ? , ?)";
	public final static String SEARCH_USERS_STORED_PROC = 
			"call searchUsers(? , ? , ?  , ? , ? , ?)";
	public final static String GET_LOGGED_IN_USER_NAME_STORED_PROC = 
			"call getLoggedInUserName(? , ? , ?  , ? , ? , ? , ?)";
	public final static String GET_HOOP_USERS_MATCHING_WITH_CONTACTS_STORED_PROC = 
			"call getHoopUsersMatchingWithContacts(? , ? , ?  , ?)";
	public final static String GET_USER_DETAILS_BASIC_STORED_PROC = 
			"call getUserDetailsBasic(? , ? , ?  , ?)";
	public final static String GET_USER_DETAILS_GENERAL_STORED_PROC = 
			"call getUserDetailsGeneral(? , ? , ?  , ?)";
	public final static String GET_USER_DETAILS_PERSONAL_CONTACT_STORED_PROC = 
			"call getUserDetailsPersonalContact(? , ? , ?  , ?)";
	public final static String GET_USER_DETAILS_EDUCATION_STORED_PROC = 
			"call getUserDetailsEducation(? , ? , ?  , ?)";
	public final static String GET_USER_DETAILS_EMPLOYMENT_STORED_PROC = 
			"call getUserDetailsEmployment(? , ? , ?  , ?)";
	public final static String GET_USER_DETAILS_BUSINESS_STORED_PROC = 
			"call getUserDetailsBusiness(? , ? , ?  , ?)";	
	public final static String UPDATE_USER_DETAILS_BASIC_STORED_PROC = 
			"call updateUserDetailsBasic(? , ? , ? , ?  , ? , ? , ? , ? , ?  , ? , ?)";
	public final static String UPDATE_USER_DETAILS_GENERAL_STORED_PROC = 
			"call updateUserDetailsGeneral(? , ? , ?  , ? , ? , ? , ? , ?  , ? , ? , ?  , ? , ?)";
	public final static String UPDATE_USER_DETAILS_PERSONAL_CONTACT_STORED_PROC = 
			"call updateUserDetailsPersonalContact(? , ? , ?  , ? , ? , ? , ?  , ?, ? , ? , ?, ? , ?)";
	public final static String UPDATE_USER_DETAILS_EDUCATION_STORED_PROC = 
			"call updateUserDetailsEducation(? , ? , ?  , ? , ? , ? , ?  , ? ,  ?  , ? , ?  , ? , ? , ?)";
	public final static String UPDATE_USER_DETAILS_EMPLOYMENT_STORED_PROC = 
			"call updateUserDetailsEmployment(? , ?  , ? , ? , ? , ?  , ? , ?  , ? , ? , ?  , ? , ?  , ? , ? , ?)";
	public final static String UPDATE_USER_DETAILS_BUSINESS_STORED_PROC = 
			"call updateUserDetailsBusiness(? , ? , ?  , ? , ? , ? , ?  , ? , ? , ? ,  ?  , ? , ?  , ? , ?)";
	public final static String GET_USER_REFERRED_CONTACTS_STORED_PROC = 
			"call getUserReferredContacts(? , ? , ?  , ?)";
	public final static String GET_USER_DETAILS_PERSONAL_SOCIAL_MEDIA_STORED_PROC = 
			"call getUserDetailsPersonalSocialMedia(? , ? , ?  , ?)";	
	public final static String UPDATE_USER_DETAILS_PERSONAL_SOCIAL_MEDIA_STORED_PROC = 
			"call updateUserDetailsPersonalSocialMedia(? , ? , ?  , ? , ? , ?)";	
	public final static String USER_DETAILS_PERSONAL_SOCIAL_MEDIA_STORED_PROC = 
			"call userDetailsPersonalSocialMedia(? , ? , ?  , ? , ? , ?)";
	public final static String GET_ALL_CONNECTED_USERS_STORED_PROC = 
			"call getAllConnectedUsers(? , ? , ?  , ?)";
	public final static String GET_FILTERED_CONNECTED_USERS_BY_LOCATION_STORED_PROC = 
			"call getFilteredConnectedUsersByLocation(? , ?  , ? , ? , ?  , ? , ?)";
	public final static String ADD_USER_DETAILS_UPDATE_NOTIFICATIONS_STORED_PROC = 
			"call addUserDetailsUpdateNotifications(? , ? , ?  , ? , ? , ?)";
	public final static String GET_USER_DETAILS_UPDATE_NOTIFICATIONS_STORED_PROC = 
			"call getUserDetailsUpdateNotifications(? , ? , ?  , ?)";
	public final static String SET_USER_DETAILS_UPDATE_NOTIFICATIONS_VIEWED_STORED_PROC = 
			"call setUserDetailsUpdateNotificationsViewed(? , ? , ?  , ? , ? )";
	public final static String DELETE_USER_DETAILS_UPDATE_NOTIFICATIONS_RECORD_STORED_PROC = 
			"call deleteUserDetailsUpdateNotification(? , ? , ?  , ? , ? )";
	public final static String ADD_OWN_CARD_SHARE_NOTIFICATION_STORED_PROC = 
			"call addOwnCardShareNotification(? , ? , ? , ?  , ? , ? )";
	public final static String GET_OWN_CARD_SHARE_NOTIFICATIONS_STORED_PROC = 
			"call getOwnCardShareNotifications(? , ? , ? , ?)";
	public final static String SET_OWN_CARD_SHARE_NOTIFICATIONS_VIEWED_STORED_PROC = 
			"call setOwnCardShareNotificationsViewed(? , ? , ? , ? , ?)";
	public final static String DELETE_OWN_CARD_SHARE_NOTIFICATION_RECORD_STORED_PROC = 
			"call deleteOwnCardShareNotification(? , ? , ? , ? , ?)";
	public final static String ADD_USER_DETAIL_MISSING_NOTIFICATION_STORED_PROC = 
			"call addUserDetailMissingNotification(? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String GET_USER_DETAIL_MISSING_NOTIFICATIONS_STORED_PROC = 
			"call getUserDetailMissingNotifications(? , ? , ? , ?)";
	public final static String SET_USER_DETAIL_MISSING_NOTIFICATIONS_VIEWED_STORED_PROC = 
			"call setUserDetailMissingNotificationViewed(? , ? , ? , ? , ?)";
	public final static String SHARE_CARD_WITH_NON_HOOP_USER_STORED_PROC = 
			"call shareCardWithNonHoopUser(? , ? , ? , ? , ? , ?)";
	public final static String ADD_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_STORED_PROC = 
			"call addOwnCardShareWithNonHoopUserNotification(? , ? , ? , ? , ? , ?)";
	public final static String GET_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATIONS_STORED_PROC = 
			"call getOwnCardShareWithNonHoopUserNotifications(? , ? , ? , ?)";
	public final static String SET_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_VIEWED_STORED_PROC = 
			"call setOwnCardShareWithNonHoopUserNotificationViewed(? , ? , ? , ? , ?)";
	public final static String DELETE_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_RECORD_STORED_PROC = 
			"call deleteOwnCardShareWithNonHoopUserNotification(? , ? , ? , ? , ?)";
	public final static String GET_CARD_OWNER_STORED_PROC = 
			"call getCardOwner(? , ? , ? , ?)";
	public final static String GET_RECEIVED_CARDS_STORED_PROC = 
			"call getReceivedCards(? , ? , ? , ?)";
	public final static String SET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_STORED_PROC = 
			"call setUserDetailMissingAllowedForAcess(? , ? , ? , ?)";
	public final static String GET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_NOTIFICATIONS_STORED_PROC = 
			"call getUserDetailMissingAllowedForAcessNotifications(? , ? , ? , ?)";
	public final static String SET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_NOTIFICATIONS_VIEWED_STORED_PROC = 
			"call setUserDetailMissingAllowedForAcessNotificationViewed(? , ? , ? , ? , ?)";
	public final static String GET_USER_DETAILS_MISSING_STORED_PROC = 
			"call getUserDetailMissing(? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String GET_IMAGE_URL_FROM_USERNAME_STORED_PROC = 
			"call getImageUrlFromUsername(? , ? , ? , ?)";
	public final static String GET_IMAGE_URL_FROM_USERID_STORED_PROC = 
			"call getImageUrlFromUserid(? , ? , ? , ?)";
	public final static String CREATE_GROUP_STORED_PROC = 
			"call createGroup(? , ? , ? , ? , ? , ?)";
	public final static String ADD_GROUP_MEMBERS_STORED_PROC = 
			"call addGroupMembers(? , ? , ? , ? , ? , ?)";
	public final static String GET_GROUP_MEMBERS_STORED_PROC = 
			"call getGroupMembers(? , ? , ? , ? , ?)";
	public final static String UPDATE_GROUP_MEMBERS_STORED_PROC = 
			"call updateGroupMembers(? , ? , ? , ? , ? , ? , ?)";
	public final static String GET_RECENT_CONNECTED_USERS_STORED_PROC = 
			"call getRecentConnectedUsers(? , ? , ? , ?)";
	public final static String GET_FILTERED_CONNECTED_USERS_BY_GENDER_STORED_PROC = 
			"call getFilteredConnectedUsersByGender(? , ? , ? , ? , ?)";
	public final static String GET_FILTERED_CONNECTED_USERS_BY_AGE_STORED_PROC = 
			"call getFilteredConnectedUsersByAge(? , ? , ? , ? , ? , ?)";
	public final static String GET_FILTERED_CONNECTED_USERS_BY_INDUSTRY_STORED_PROC = 
			"call getFilteredConnectedUsersByIndustry(? , ? , ? , ? , ?)";
	public final static String GET_FILTERED_CONNECTED_USERS_BY_PROFESSION_STORED_PROC = 
			"call getFilteredConnectedUsersByProfession(? , ? , ? , ? , ?)";
	public final static String GET_GROUPS_STORED_PROC = 
			"call getGroups(? , ? , ? , ?)";
	public final static String GET_CARD_NAME_FROM_CARD_ID_STORED_PROC = 
			"call getCardNameFromCardId(? , ? , ? , ?)";
	public final static String DELETE_OWN_CARD_STORED_PROC = 
			"call deleteOwnCard(? , ? , ? , ?)";
	public final static String SHARE_CARD_WITH_HOOP_USER_VIA_BLUETOOTH_STORED_PROC = 
			"call shareCardWithHoopUserViaBluetooth(? , ? , ? , ? , ? , ?)";
	public final static String GET_HOOP_USERS_MATCHING_BY_ANY_INFO_STORED_PROC = 
			"call getHoopUsersIdentifiebByAnyInformation(? , ? , ? , ?)";
	public final static String GET_USER_DETAILS_MISSING_ALREADY_SHARED_ON_REQUEST_STORED_PROC = 
			"call getUserDetailMissingAlreadySharedOnRequest(? , ? , ? , ?, ? , ? , ? , ?)";
	public final static String GET_CONNECTED_USERS_MATCHING_BY_ANY_INFO_STORED_PROC = 
			"call getConnectedUsersIdentifiedByAnyInformation(? , ? , ? , ?)";
	
	public final static String CUSTOM_CARD_BASIC_AND_PERSONAL_FIELDS_STORED_PROC = 
			"call addCustomCardBasicAndPersonalFields(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String CUSTOM_CARD_EMPLOYMENT_FIELDS_STORED_PROC = 
			"call addCustomCardEmploymentFields(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String CUSTOM_CARD_BUSINESS_FIELDS_STORED_PROC = 
			"call addCustomCardBusinessFields(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String CUSTOM_CARD_PERSONAL_SOCIAL_MEDIA_FIELDS_STORED_PROC = 
			"call addCustomCardPersonalSocialMediaFields(? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	
	public final static String SHARE_CUSTOM_CARD_STORED_PROC = 
			"call shareCustomCard(? , ? , ? , ? , ? , ?)";
	public final static String ADD_CUSTOM_CARD_SHARE_NOTIFICATION_STORED_PROC = 
			"call addCustomCardShareNotification(? , ? , ? , ? , ? , ?)";
	
	public final static String GET_CUSTOM_CARD_BASIC_PERSONAL_DETAILS_STORED_PROC = 
			"call getCustomCardBasicPersonalDetails(? , ? , ? , ?)";
	public final static String GET_CUSTOM_CARD_EMPLOYMENT_DETAILS_STORED_PROC = 
			"call getCustomCardEmploymentDetails(? , ? , ? , ? , ?)";
	public final static String GET_CUSTOM_CARD_BUSINESS_DETAILS_STORED_PROC = 
			"call getCustomCardBusinessDetails(? , ? , ? , ? , ?)";
	public final static String GET_CUSTOM_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS_STORED_PROC = 
			"call getCustomCardPersonalSocialMediaDetails(? , ? , ? , ?)";
	
	public final static String GET_CUSTOM_CARD_OWNER_STORED_PROC = 
			"call getCustomCardOwner(? , ? , ? , ?)";
	public final static String GET_USER_DETAIL_SHARED_THROUGH_CUSTOM_CARD_STORED_PROC = 
			"call getUserDetailSharedThroughCustomCard(? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String DELETE_GROUP_STORED_PROC = 
			"call deleteGroup(? , ? , ? , ?)";
	public final static String GET_NON_HOOP_CONTACTS_STORED_PROC = 
			"call getNonHoopContacts(? , ? , ? , ?)";
	public final static String GET_USER_DETAIL_SHARED_THROUGH_NORMAL_CARD_STORED_PROC = 
			"call getUserDetailSharedThroughNormalCard(? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String ACTIVATE_USER_WITH_OTP_STORED_PROC = 
			"call activateUserWithOtp(? , ? , ? , ? , ?)";
	public final static String GET_SINGLE_CONNECTED_USER_DETAILS_STORED_PROC = 
			"call getSingleConnectedUserDetails(? , ? , ? , ?)";
	
	
	public final static String ADD_DRIVER_DETAILS_STORED_PROC = 
			"call addDriverDetails(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String ADD_SELLER_FUEL_STORED_PROC = 
	"call addSellerFuel(? ,? ,? ,? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String ADD_SELLER_DISPATCH_STORED_PROC = 
			"call addSellerDispatch( ? , ? , ? , ?,? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ? , ?  )";
 
	public final static String ADD_SELLER_INVOICE_STORED_PROC = 
			"call addSellerInvoice(? ,? , ? , ? , ? , ? , ? , ? , ?,? , ? , ? , ? , ? , ? , ? , ?, ? , ?  , ? )";
	public final static String ADD_SELLER_PURCHASE_STORED_PROC = 
			"call addSellerPurchase(? ,? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String ADD_STORAGE_FUEL_STORED_PROC = 
			"call addStorageFuel(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String ADD_STORAGE_DISPATCH_STORED_PROC = 
			"call addStorageDispatch( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ? , ?  )";
	public final static String ADD_STORAGE_INVOICE_STORED_PROC = 
			"call addStorageInvoice(? , ? , ? , ? , ? , ? , ? , ? , ?,? , ? , ? , ? , ? , ? , ? , ?, ? , ?, ? , ?   )";
	public final static String ADD_STORAGE_INCOMING_LOAD_STORED_PROC = 
			"call addStorageIncomingLoad(? , ? , ? , ? , ? , ? , ? , ? , ?,? , ? , ? , ? , ? , ? , ? , ?, ?   )";
	public final static String ADD_TRUCKS_ALLOTMENT_STORED_PROC = 
			"call addTrucksAllotment(? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String ADD_TRUCKS_DETAILS_STORED_PROC = 
			"call addTrucksDetails(? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String ADD_TRUCKS_HEALTH_HISTORY_STORED_PROC = 
			"call addTrucksHealthHistory(? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String ADD_TRUCKS_PARTS_QUOTE_STORED_PROC = 
			"call addTrucksPartsQuote( ? , ? , ? , ? , ? , ? ,? , ? , ?, ? , ? , ? , ? , ? ,? , ? , ? , ? )";
	/*public final static String ADD_TRUCKS_SERVICE_STORED_PROC = 
			"call addTrucksService(? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";*/
	public final static String ADD_SPARE_PART_ALLOTMENT_STORED_PROC = 
			"call addSparePartAllotment( ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String GET_STORAGE_DISPATCH_STORED_PROC = 
			"call getStorageDispatch(? , ? , ? , ?)";
	public final static String GET_STORAGE_FUEL_STORED_PROC = 
			"call getStorageFuel(? , ? , ? , ?)";
	public final static String GET_STORAGE_INCOMING_LOAD_STORED_PROC = 
			"call getStorageIncomingLoad(? , ? , ? , ?)";
	public final static String GET_STORAGE_INVOICE_STORED_PROC = 
			"call getStorageInvoice(? , ? , ? , ?)";
	
	public final static String GET_SELLER_DISPATCH_STORED_PROC = 
			"call getSellerDispatch(? , ? , ? , ?)";
	public final static String GET_SELLER_FUEL_STORED_PROC = 
			"call getSellerFuel(? , ? , ? , ?)";
	public final static String GET_SELLER_INVOICE_STORED_PROC = 
			"call getSellerInvoice(? , ? , ? , ?)";
	public final static String GET_SELLER_PURCHASE_STORED_PROC = 
			"call getSellerPurchase(? , ? , ? , ?)";
	
	public final static String GET_TRUCKS_ALLOTMENT_STORED_PROC = 
			"call getTrucksAllotment(? , ? , ? , ?)";
	public final static String GET_TRUCKS_DETAILS_STORED_PROC = 
			"call getTrucksDetails(? , ? , ? , ?)";
	public final static String GET_TRUCKS_PARTS_QUOTE_STORED_PROC = 
			"call getTrucksPartsQuote(? , ? , ? , ?)";
	public final static String GET_TRUCKS_HEALTH_HISTORY_STORED_PROC = 
			"call getTrucksHealthHistory(? , ? , ? , ?)";
	public final static String GET_TRUCKS_SERVICE_STORED_PROC = 
			"call getTrucksService(? , ? , ? , ?)";
	public final static String GET_SPARE_PART_ALLOTMENT_STORED_PROC = 
			"call getSparePartAllotment(? , ? , ? , ?)";
	public final static String GET_STORAGE_DISPATCH_BY_DATE_STORED_PROC = 
			"call getStorageDispatchByDate(? , ? , ? , ? , ?)";
	public final static String GET_STORAGE_FUEL_BY_DATE_STORED_PROC = 
			"call getStorageFuelByDate(? , ? , ? , ? , ? , ?)";
	public final static String GET_STORAGE_INCOMING_LOAD_BY_DATE_STORED_PROC = 
			"call getStorageIncomingLoadByDate(? , ? , ? , ? , ?)";
	public final static String GET_STORAGE_INVOICE_BY_DATE_STORED_PROC = 
			"call getStorageInvoiceByDate(? , ? , ? , ? , ?)";
	public final static String GET_SELLER_DISPATCH_BY_DATE_STORED_PROC = 
			"call getSellerDispatchByDate(? , ? , ? , ? , ?)";
	public final static String GET_SELLER_FUEL_BY_DATE_STORED_PROC = 
			"call getSellerFuelByDate(? , ? , ? , ? , ? , ?)";
	public final static String GET_SELLER_INVOICE_BY_DATE_STORED_PROC = 
			"call getSellerInvoiceByDate(? , ? , ? , ? , ?)";
	public final static String GET_SELLER_PURCHASE_BY_DATE_STORED_PROC = 
			"call getSellerPurchaseByDate(? , ? , ? , ? , ?)";
	
	public final static String GET_TRUCKS_ALLOTMENT_BY_DATE_STORED_PROC = 
			"call getTrucksAllotmentByDate(? , ? , ? , ? , ?)";
	public final static String GET_TRUCKS_HEALTH_HISTORY_BY_DATE_STORED_PROC = 
			"call getTrucksHealthHistoryByDate(? , ? , ? , ? , ? , ?)";
	public final static String GET_TRUCKS_PARTS_QUOTE_BY_DATE_STORED_PROC = 
			"call getTrucksPartsQuoteByDate(? , ? , ? , ? , ?)";
	public final static String GET_TRUCKS_SERVICE_BY_DATE_STORED_PROC = 
			"call getTrucksServiceByDate(? , ? , ? , ? , ?)";
	public final static String GET_SPARE_PART_ALLOTMENT_BY_DATE_STORED_PROC = 
			"call getSparePartAllotmentByDate(? , ? , ? , ? , ?)";
	
	public final static String UPDATE_DRIVER_DETAILS_STORED_PROC = 
			"call updateDriverDetails(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String UPDATE_STORAGE_DISPATCH_STORED_PROC = 
			"call updateStorageDispatch( ? , ? , ? , ? , ? , ?,? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ? , ? , ? )";
	public final static String UPDATE_STORAGE_FUEL_STORED_PROC = 
			"call updateStorageFuel( ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String UPDATE_STORAGE_INVOICE_STORED_PROC = 
			"call updateStorageInvoice( ? ,? , ? , ? , ? , ? , ? , ? , ?,? , ? , ? , ? , ? , ? , ? , ?, ? , ?, ? , ?   )";
	public final static String UPDATE_STORAGE_INCOMING_LOAD_STORED_PROC = 
			"call updateStorageIncomingLoad( ? ,? , ? , ? , ? , ? , ? , ? , ?,? , ? , ? , ? , ? , ? , ? , ?, ? )";
	
	public final static String UPDATE_SELLER_FUEL_STORED_PROC = 
			"call updateSellerFuel(? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String UPDATE_SELLER_DISPATCH_STORED_PROC = 
			"call updateSellerDispatch( ? , ? , ? , ? , ? , ? , ?,? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ? , ? , ?  )";		 
	public final static String UPDATE_SELLER_INVOICE_STORED_PROC = 
			"call updateSellerInvoice( ? ,? , ? , ? , ? , ? , ? , ?,? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ?  , ? )";
	public final static String UPDATE_SELLER_PURCHASE_STORED_PROC = 
			"call updateSellerPurchase( ? ,? , ? , ? , ? , ? , ? , ? , ?, ?)";
	
	public final static String UPDATE_TRUCKS_ALLOTMENT_STORED_PROC = 
			"call updateTrucksAllotment( ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String UPDATE_TRUCKS_DETAILS_STORED_PROC = 
			"call updateTrucksDetails(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String UPDATE_TRUCKS_HEALTH_HISTORY_STORED_PROC = 
			"call updateTrucksHealthHistory(? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String UPDATE_TRUCKS_PARTS_QUOTE_STORED_PROC = 
			"call updateTrucksPartsQuote( ? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ? )";
	public final static String UPDATE_TRUCKS_SERVICE_STORED_PROC = 
			"call updateTrucksService(? , ? , ?, ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String UPDATE_SPARE_PART_ALLOTMENT_STORED_PROC = 
			"call updateSparePartAllotment( ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String GET_ALL_DRIVER_DETAILS_STORED_PROC = 
			"call getAllDriverDetails(? , ? , ? )";
	public final static String GET_DRIVER_DETAILS_STORED_PROC =
			"call getDriverDetails(? , ? , ? , ? )";
	public final static String GET_DRIVERS_STORED_PROC = 
			"call getDrivers( ? , ? , ? )";
	public final static String GET_ALL_SPARE_PARTS_STORED_PROC = 
			"call getAllSpareParts(? , ? , ? )";
	public final static String GET_ALL_TRUCKS_DETAILS_STORED_PROC = 
			"call getAllTrucksDetails(? , ? , ? )";
	public final static String GET_TRUCKS_STORED_PROC = 
			"call getTrucks(? , ? , ? )";
	public final static String GET_ALL_TRUCKS_STORED_PROC = 
			"call getAllTrucks(? , ? , ? )";
	
	public final static String GET_STATUS_DISPATCH_STORED_PROC = 
			"call startSellerDispatch(? , ? , ? , ?)";
	public final static String UPDATE_STATUS_DISPATCH_STORED_PROC = 
			"call updateStatusDispatch(? ,? , ? , ? , ? , ? , ? )";
	public final static String GET_CLOSE_DISPATCH_STORED_PROC = 
			"call closeSellerDispatch(? , ? , ? , ?)";
	public final static String UPDATE_CLOSE_DISPATCH_STORED_PROC = 
			"call updateCloseDispatch( ? , ? , ? , ? , ? , ? , ? )";
	
	public final static String GET_STATUS_STORAGE_DISPATCH_STORED_PROC = 
			"call startStorageDispatch(? , ? , ? , ?)";
	public final static String UPDATE_STATUS_STORAGE_DISPATCH_STORED_PROC = 
			"call updateStatusStorageDispatch(? ,? , ? , ? , ? , ? , ? )";
	public final static String GET_CLOSE_STORAGE_DISPATCH_STORED_PROC = 
			"call closeStorageDispatch(? , ? , ? , ?)";
	public final static String UPDATE_CLOSE_STORAGE_DISPATCH_STORED_PROC = 
			"call updateCloseStorageDispatch( ? , ? , ? , ? , ? , ? , ? )";
	
	
	public final static String ADD_REGISTRATION_USERS_STORED_PROC = 
			"call registration_users(? , ? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ? )";
	public final static String ADD_REGISTRATION_BASIC_STORED_PROC = 
			"call registration_basic(? , ? , ? , ? , ? , ? , ? )";
	public final static String GET_ACTIONS_LOG_STORED_PROC = 
			"call getActionLog(? , ? , ? , ? , ? , ? , ? )";

	public final static String ADD_FACTORY_TRIP_DETAILS_STORED_PROC=
    		"call addFactoryTripDetails(? ,  ? , ? ,  ? , ? , ? , ? ,? , ? , ? , ? , ? , ? , ? , ? , ?)";
    public final static String GET_TRIP_DETAILS_STORED_PROC=
    		"call getTripDetails( ? , ? , ? , ?)"; 
    public final static String UPDATE_TRIP_DETAILS_STORED_PROC=
    		"call updateTripDetails( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )"; 
    public final static String GET_CLOSE_TRIP_DETAILS_STORED_PROC=
    		"call closeTripStatus( ? , ? , ? , ? )"; 
    public final static String UPDATE_CLOSE_TRIP_DETAILS_STORED_PROC=
    		"call updateCloseTripStatus(? , ? , ? , ? , ? , ? , ? , ?  , ?)"; 
    public final static String ADD_INVOICE_PHOTO_STORED_PROC=
    		"call addInvoicePhoto(? , ? , ? , ? , ? )";
	
	//------------------------------------------------------------------------
	
	/*public final static String ADD_DRIVER_DETAILS_URL_STORED_PROC = 
			"call addDriverDetails(? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";*/
	
	public final static String UPDATE_FACTORY_DISPATCH_INITIALLY_STORED_PROC = 
			"call updateFactoryDispatchInitially(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String ADD_FACTORY_FUEL_URL_STORED_PROC = 
			"call addFactoryFuel1( ? ,? , ? ,? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ?)";
	public final static String ADD_ERROR_FUEL_STORED_PROC = 
			"call addErrorFuel( ? ,? , ? ,? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ?)";
	public final static String ADD_FACTORY_INVOICE_URL_STORED_PROC = 
			"call addFactoryInvoice(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String ADD_VEHICLE_TYRE_SUMMARY_REPORT_URL_STORED_PROC = 
			"call addVehicleTyreSummaryReport(? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String ADD_FRONT_TOTAL_SUMMARY_URL_STORED_PROC =
			"call addFrontTotalSummary(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String ADD_HOSING_TOTAL_SUMMARY_URL_STORED_PROC =
	        "call addHosingTotalSummary(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String ADD_LOCATION_ALLOTMENT_URL_STORED_PROC =
		    "call addLocationAllotment(? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String ADD_LOCATION_STATUS_URL_STORED_PROC =
		    "call addLocationStatus(? , ? , ? , ? , ? , ? , ?)";
	/*public final static String GET_DRIVER_DETAILS_URL_STORED_PROC =
			"call getDriverDetails(? , ? , ? , ?)";*/
	public final static String GET_FACTORY_DISPATCH_URL_STORED_PROC =
			"call getFactoryDispatch(? , ? , ? , ?)";
	public final static String GET_FACTORY_FUEL_URL_STORED_PROC =
			"call getFactoryFuel(? , ? , ? , ?)";
	public final static String GET_FACTORY_INVOICE_URL_STORED_PROC =
			"call getFactoryInvoice(? , ? , ? , ?)";
	public final static String GET_VEHICLE_TYRE_SUMMARY_REPORT_URL_STORED_PROC =
			"call getVehicleTyreSummaryReport(? , ? , ? , ?)";
	public final static String GET_LOCATION_ALLOTMENT_URL_STORED_PROC =
			"call getLocationAllotment(? , ? , ? , ?)";
	public final static String GET_LOCATION_STATUS_URL_STORED_PROC =
			"call getLocationStatus(? , ? , ? , ?)";
	public final static String GET_FACTORY_DISPATCH_BY_DATE_URL_STORED_PROC =
			"call getFactoryDispatchByDate(? , ? , ? , ? , ? ,?)";
	public final static String GET_FACTORY_DISPATCH_FUEL_BY_DATE_URL_STORED_PROC =
			"call getFactoryDispatchFuelByDate(? , ? , ? , ? , ? ,?)";
	public final static String GET_FACTORY_FUEL_BY_DATE_URL_STORED_PROC =
			"call getFactoryFuelByDate(? , ? , ? , ? , ? ,? ,?)";
	public final static String GET_FACTORY_INVOICE_BY_DATE_URL_STORED_PROC =
			"call getFactoryInvoiceByDate(? , ? , ? , ? ,?)";
	public final static String GET_LOCATION_ALLOTMENT_BY_DATE_URL_STORED_PROC =
			"call getLocationAllotmentByDate(? , ? , ? , ? ,?)";
	public final static String GET_LOCATION_STATUS_BY_DATE_URL_STORED_PROC =
			"call getLocationStatusByDate(? , ? , ? , ? ,?)";
	/*public final static String UPDATE_DRIVER_DETAILS_URL_STORED_PROC =
	        "call updateDriverDetails(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";*/
	public final static String UPDATE_FACTORY_DISPATCH_URL_STORED_PROC = 
			"call updateFactoryDispatch(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String UPDATE_FACTORY_FUEL_URL_STORED_PROC = 
			"call updateFactoryFuel(? ,? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ?)";
	public final static String UPDATE_FACTORY_INVOICE_URL_STORED_PROC = 
			"call updateFactoryInvoice(? , ? ,? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String UPDATE_LOCATION_ALLOTMENT_URL_STORED_PROC =
		    "call updateLocationAllotment(? , ? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String UPDATE_LOCATION_STATUS_URL_STORED_PROC =
		    "call updateLocationStatus(? , ? , ? , ? , ? , ? , ? , ?)";
	public final static String GET_VEHICLE_TYRE_SUMMARY_REPORT_BY_DATE_URL_STORED_PROC =
			"call getVehicleTyreSummaryReportByDate(? , ? , ? , ? ,?)";
	public final static String  UPDATE_VEHICLE_TYRE_SUMMARY_REPORT_URL_STORED_PROC=
			"call updateVehicleTyreSummaryReport( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?)";
	public final static String GET_FACTORY_DISPATCH_STATUS_URL_STORED_PROC=
			"call startFactoryDispatchStatus( ? , ? , ? , ?)";
    public final static String UPDATE_FACTORY_DISPATCH_STATUS_URL_STORED_PROC=
    		"call updateStatusFactoryDispatch(? , ? , ? , ? , ? , ? , ? ,?)";
    public final static String GET_CLOSE_FACTORY_DISPATCH_URL_STORED_PROC=
    		"call closeFactoryDispatch( ? , ? , ? , ?)";
    public final static String UPDATE_CLOSE_FACTORY_DISPATCH_URL_STORED_PROC=
    		"call updateClosingFactoryDispatch( ? , ? , ? , ? , ? , ? , ? , ?)";

    public final static String GET_DRIVER_SALARY_SLIP_STORED_PROC=
    		"call getDriverSalarySlip ( ? , ? , ? , ? , ? , ? )"; 
    public final static String GET_DRIVER_SALARY_STORED_PROC=
    		"call getDriverSalary( ? , ? , ? , ? )"; 

    public final static String GET_TRIP_BY_DATE_STORED_PROC=
       		"call getTripDetailsByDate ( ? , ? , ? , ? , ? )"; 
    
	public final static String ADD_STORAGE_TRIP_DETAILS_STORED_PROC=
    		"call addStorageTripDetails( ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? , ? , ? , ?)";
    public final static String GET_STORAGE_TRIP_DETAILS_STORED_PROC=
    		"call getStorageTripDetails( ? , ? , ? , ?)"; 
    public final static String UPDATE_STORAGE_TRIP_DETAILS_STORED_PROC=
    		"call updateStorageTripDetails( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )"; 
    public final static String GET_CLOSE_STORAGE_TRIP_DETAILS_STORED_PROC=
    		"call closeStorageTripStatus( ? , ? , ? , ? )"; 
    public final static String UPDATE_CLOSE_STORAGE_TRIP_DETAILS_STORED_PROC=
    		"call updateCloseStorageTripStatus( ? , ? , ? , ? , ? , ?  , ?)"; 
    public final static String GET_STORAGE_TRIP_BY_DATE_STORED_PROC=
       		"call getStorageTripDetailsByDate ( ? , ? , ? , ? , ? )"; 
    
	public final static String ADD_SELLER_TRIP_DETAILS_STORED_PROC=
    		"call addSellerTripDetails( ? , ? , ? , ? , ? ,? , ? , ? , ? , ? , ? , ? , ? , ?)";
    public final static String GET_SELLER_TRIP_DETAILS_STORED_PROC=
    		"call getSellerTripDetails( ? , ? , ? , ?)"; 
    public final static String UPDATE_SELLER_TRIP_DETAILS_STORED_PROC=
    		"call updateSellerTripDetails( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )"; 
    public final static String GET_CLOSE_SELLER_TRIP_DETAILS_STORED_PROC=
    		"call closeSellerTripStatus( ? , ? , ? , ? )"; 
    public final static String UPDATE_CLOSE_SELLER_TRIP_DETAILS_STORED_PROC=
    		"call updateCloseSellerTripStatus( ? , ? , ? , ? , ? , ?  , ?)"; 
    public final static String GET_SELLER_TRIP_BY_DATE_STORED_PROC=
       		"call getSellerTripDetailsByDate ( ? , ? , ? , ? , ? )"; 
    public final static String ADD_SPARE_PART_INVENTORY_STORED_PROC = 
			"call addSparePartInventory(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
    public final static String ADD_TYRE_INVENTORY_STORED_PROC = 
			"call addTyresInventory(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
    
    public final static String GET_TRUCKS_SERVICE_BY_Truck_STORED_PROC=
    		"call getTrucksBYTruck ( ? , ? , ? , ? , ? , ?)"; 
	public final static String GET_ALL_TYRES_STORED_PROC = 
			"call getAllTyres(? , ? , ? )";
	public final static String GET_ALL_TYRES_BY_CATEGORY_STORED_PROC = 
			"call getAllTyresByCategory(? , ? , ? , ? , ? , ?  )";
	public final static String GET_ALL_RUNNING_TYRES_STORED_PROC = 
			"call getAllRunningTyres( ? , ? , ? , ?  )";
	public final static String GET_ALL_PENDING_TYRES_STORED_PROC = 
			"call getAllRecoupPendingTyres( ? , ? , ? , ? , ?  )";
	public final static String GET_ALL_BRAND_NAMES_STORED_PROC = 
			"call getAllBrandNames(? , ? , ? )";
	public final static String GET_ALL_RUNNING_BRAND_NAMES_STORED_PROC = 
			"call getAllRunningBrandNames(? , ? , ? )";
	public final static String GET_AVAILABLE_AND_ENGAGED_DRIVER_DETAILS_STORED_PROC=
    		"call availableAndEngagedDrivers( ? , ? , ? )";
	public final static String ADD_ISSUED_TYRES_STORED_PROC = 
			"call addIssuedTyres(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String ADD_RECOUP_TYRES_STORED_PROC = 
			"call addRecoupTyres(? , ? , ? , ? , ? , ? , ? , ? )";
	public final static String GET_ALL_PENDING_BRAND_NAMES_STORED_PROC = 
			"call getAllPendingBrandNames(? , ? , ? )";
	public final static String UPDATE_RECOUP_TYRE_STATUS_STORED_PROC = 
			"call updateRecoupTyreStatus(? , ? , ? , ? , ? )";

	public final static String GET_DRIVER_ALLOTMENT_TO_TRUCK_BY_DATE_STORED_PROC = 
			"call getDriverAllotmentToTruckByDate(? , ? , ? , ? , ?)";
	public final static String ADD_DRIVER_ALLOTMENT_TO_TRUCK_STORED_PROC = 
			"call addDriverAllotmentToTruck( ? , ? , ? , ? , ? , ? , ? , ?  )";
	public final static String GET_AVAILABLE_AND_ENGAGED_TRUCKS_DETAILS_STORED_PROC=
			"call availableAndEngagedTrucksAndDrivers(? , ? , ?)";
	public final static String GET_ALLOTED_TRUCKS_STORED_PROC=
			"call getAllotedTrucks(? , ? , ? , ?)";
	public final static String GET_FACTORY_DISPATCHED_TRUCKS_STORED_PROC=
			"call getFactoryDispatchedTrucks(? , ? , ? , ?)";
	public final static String GET_FACTORY_UNLOADING_TRUCKS_STORED_PROC=
			"call getFactoryUnloadingTrucks(? , ? , ? , ?)";
	
	public final static String ADD_TRUCKS_SERVICE_STORED_PROC = 
			"call addTrucksService(? , ? , ? , ? , ? , ?)";
	public final static String  CLOSE_TRUCKS_SERVICE_STORED_PROC=
    		"call closeTrucksService(? , ? , ? , ? , ? , ? , ?  )";
	public final static String GET_TRUCKS_SERVICE_BY_TRUCKNUMBER_STORED_PROC=
    		"call getTruckServicesByTruckNumber ( ? , ? , ? , ? , ? , ?)"; 
	public final static String GET_STORAGE_DISPATCHED_TRUCKS_STORED_PROC=
			"call getStorageDispatchedTrucks( ? , ? , ? , ?)";
	public final static String GET_STORAGE_UNLOADING_TRUCKS_STORED_PROC=
			"call getStorageUnloadingTrucks( ? , ? , ? , ?)";
	public final static String GET_SELLER_DISPATCHED_TRUCKS_STORED_PROC=
			"call getSellerDispatchedTrucks( ? , ? , ? , ?)";
	public final static String GET_SELLER_UNLOADING_TRUCKS_STORED_PROC=
			"call getSellerUnloadingTrucks( ? , ? , ? , ?)";
	public final static String GET_RUNNING_TYRES_STORED_PROC=
			"call getRunningTyres(? , ? , ? , ?)";
	public final static String GET_ISSUED_TYRES_STORED_PROC=
			"call getIssuedTyres(? , ? , ? , ? , ? , ? , ?)";
	public final static String GET_WASTE_TYRES_STORED_PROC=
			"call getWasteTyres( ? , ? , ? , ? , ?)";
	public final static String GET_RECOUP_TYRES_STORED_PROC = 
			"call getRecoupTyres(? , ? , ? )";
	public final static String CHANGE_PASSWORD_STORED_PROC=
    		"call changePassword(? , ? , ? , ? , ? , ?)";
	 public final static String SET_LINK_FOR_NEW_PASSWORD_STORED_PROC=
	    		"call linkForNewPassword(? , ? , ? , ? , ? )";
	 public final static String SENT_EMAIL_FOR_FORGOT_PASSWORD_STORED_PROC=
    		"call sentEmailForForgotPassword( ? , ? , ? , ? )";	
	 public final static String GET_TYRES_CURRENT_INVENTORY_STORED_PROC = 
				"call getTyresCurrentInventory(? , ? , ? )";
	 public final static String GET_TYRES_INVENTORY_HISTORY_STORED_PROC = 
				"call getTyresInventoryHistory(? , ? , ? , ? , ? )";
	 public final static String GET_SPARE_PART_CURRENT_INVENTORY_STORED_PROC = 
				"call getSparePartCurrentInventory(? , ? , ? )";
	 public final static String GET_SPARE_PART_INVENTORY_HISTORY_STORED_PROC = 
				"call getSparePartInventoryHistory(? , ? , ? , ? , ? )";
	 
	 public final static String GET_OIL_BRAND_NAMES_STORED_PROC = 
				"call getAllOilBrands(? , ? , ? )";
	 public final static String GET_OIL_DISTINCT_BRAND_NAMES_STORED_PROC = 
				"call getDistinctOilBrands( ? , ? , ? )";
	 public final static String ADD_OIL_INVENTORY_STORED_PROC = 
				"call addOilInventory( ? , ? , ? , ? , ? , ? , ? , ? , ?  )";
	 public final static String ADD_ISSUED_OIL_STORED_PROC = 
				"call addIssuedOil( ? , ? , ? , ? , ? , ? , ? , ? , ?  )";
	 public final static String GET_OIL_CURRENT_INVENTORY_STORED_PROC = 
				"call getOilCurrentInventory(? , ? , ? )";
	 public final static String GET_OIL_INVENTORY_HISTORY_STORED_PROC = 
				"call getOilInventoryHistory(? , ? , ? , ? , ? )";
	 public final static String GET_ISSUED_OIL_STORED_PROC = 
				"call getIssuedOil(? , ? , ? , ? , ? , ? )";
	 public final static String ADD_OIL_BRAND_NAME_STORED_PROC = 
				"call addNewOilBrand(? , ? , ? , ? )";
	 public final static String GET_INVENTORY_BATTERY_BRANDS_STORED_PROC = 
				"call getInventoryBatteryBrands(? , ? , ? )";
	 public final static String GET_BATTERY_BRAND_NAMES_STORED_PROC = 
				"call getAllBatteryBrands(? , ? , ? )";
	 public final static String GET_RUNNING_BATTERY_BRANDS_STORED_PROC = 
				"call getRunningBatteryBrands(? , ? , ? )";
	 public final static String ADD_BATTERY_BRAND_NAME_STORED_PROC = 
				"call addNewBatteryBrand(? , ? , ? , ? )";
	 public final static String ADD_ISSUED_BATTERY_STORED_PROC = 
				"call addIssuedBattery(? , ? , ? , ? , ? , ? , ? , ? , ? , ?  )";
	 public final static String ADD_BATTERY_INVENTORY_STORED_PROC = 
				"call addBatteryInventory(? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_INVENTORY_BATTERY_NUMBERS_STORED_PROC = 
				"call getInventoryBatteryNumbers(? , ? , ? , ? )";
	 public final static String GET_RUNNING_BATTERY_NUMBERS_STORED_PROC = 
				"call getRunningBatteryNumbers(? , ? , ? , ? )";
	 public final static String GET_BATTERY_CURRENT_INVENTORY_STORED_PROC = 
				"call getBatteryCurrentInventory( ? , ? , ? )";
	 public final static String GET_BATTERY_INVENTORY_HISTORY_STORED_PROC = 
				"call getBatteryInventoryHistory(? , ? , ? , ? , ? )";
	 public final static String GET_RUNNING_BATTERY_STORED_PROC = 
				"call getRunningBattery( ? , ? , ? )";
	 public final static String GET_WASTE_BATTERY_STORED_PROC = 
				"call getWasteBattery(? , ? , ? , ? , ? )";
	 public final static String GET_ISSUED_BATTERY_STORED_PROC = 
				"call getIssuedBattery(? , ? , ? , ? , ? )";
	 public final static String GET_TRUCKS_DRIVER_CHANGE_STORED_PROC = 
				"call getTrucksDriverChange( ? , ? , ? )";
	 public final static String ADD_FACTORY_DRIVER_CHANGE_STORED_PROC = 
				"call addFactoryDriverChange(?, ? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_ENGAGED_TRUCKS_STORED_PROC = 
				"call getEngagedTrucks( ? , ? , ? )";
	 public final static String GET_FACTORY_CHANGE_STORED_PROC = 
				"call getFactoryChange(? ,  ? , ? , ? )";
	 public final static String ADD_STORAGE_DRIVER_CHANGE_STORED_PROC = 
				"call addStorageDriverChange(? ,? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_STORAGE_CHANGE_STORED_PROC = 
				"call getStorageChange(? ,  ? , ? , ? )";
	 public final static String ADD_SELLER_DRIVER_CHANGE_STORED_PROC = 
				"call addSellerDriverChange(? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_SELLER_CHANGE_STORED_PROC = 
				"call getSellerChange(? ,  ? , ? , ? )";
	 public final static String GET_FACTORY_ENGAGED_TRUCKS_STORED_PROC = 
				"call getFactoryEngagedTrucks( ? , ? , ? )";
	 public final static String GET_STORAGE_ENGAGED_TRUCKS_STORED_PROC = 
				"call getStorageEngagedTrucks( ? , ? , ? )";
	 public final static String GET_SELLER_ENGAGED_TRUCKS_STORED_PROC = 
				"call getSellerEngagedTrucks( ? , ? , ? )";

	 public final static String GET_FACTORIES_ASSOCIATION_STORED_PROC = 
				"call getFactoriesAssociation( ? , ? , ? )";
	 public final static String GET_ASSIGNED_TRUCKS_STORED_PROC=
				"call getAssignedTrucks( ? , ? , ?)";
	 public final static String GET_DRIVER_ALLOTMENT_TO_TRUCK_STORED_PROC=
				"call getDriverAllotmentToTruck(? , ? , ? , ?)";
	 public final static String UPDATE_DRIVER_ALLOTMENT_TO_TRUCK_STORED_PROC=
				"call updateDriverAllotmentToTruck(? , ? , ? , ? , ?, ? , ? , ?)";
	 public final static String ADD_DRIVERS_IMAGE_URL_STORED_PROC=
				"call addDriversImageUrl(? , ? , ? , ? , ? , ?)";
	 public final static String UPDATE_DRIVERS_IMAGE_URL_STORED_PROC=
				"call updateDriversImageUrl(? , ? , ? , ? , ? , ?)";
	 public final static String ADD_TRUCKS_IMAGE_URL_STORED_PROC=
				"call addTrucksImageUrl(? , ? , ? , ? , ? , ? , ? , ?)";
	 public final static String UPDATE_TRUCKS_IMAGE_URL_STORED_PROC=
				"call updateTrucksImageUrl(? , ? , ? , ? , ? , ? , ? , ?)";
	 public final static String GET_FACTORY_DRIVER_EXPENDITURE_STORED_PROC=
				"call getFactoryDriverExpenditure( ? , ? , ? , ? )";
	 public final static String GET_FACTORY_DRIVERS_CLOSE_EXPENDITURE_STORED_PROC=
				"call getFactoryDriversCloseExpenditure(? , ? , ? , ?  )";
	 public final static String ADD_FACTORY_DRIVER_EXPENDITURE_STORED_PROC=
				"call addFactoryDriverExpenditure(? ,? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ?,? , ? , ? , ?,? )";
	 public final static String GET_DRIVER_BALANCE_STORED_PROC = 
				"call getDriverBalance(? , ? , ? , ? )";
	 public final static String GET_SELLER_DRIVER_EXPENDITURE_STORED_PROC=
				"call getSellerDriverExpenditure(? , ? , ? , ? , ? )";
	 public final static String GET_SELLER_DRIVERS_CLOSE_EXPENDITURE_STORED_PROC=
				"call getSellerDriversCloseExpenditure(? , ? , ? , ?  )";
	 public final static String ADD_SELLER_DRIVER_EXPENDITURE_STORED_PROC=
				"call addSellerDriverExpenditure(? ,? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ?,? , ? , ? , ?,? )";
	 public final static String GET_STORAGE_DRIVER_EXPENDITURE_STORED_PROC=
				"call getStorageDriverExpenditure(? , ? , ? , ? , ? )";
	 public final static String GET_STORAGE_DRIVERS_CLOSE_EXPENDITURE_STORED_PROC=
				"call getStorageDriversCloseExpenditure(? , ? , ? , ?  )";
	 public final static String ADD_STORAGE_DRIVER_EXPENDITURE_STORED_PROC=
				"call addStorageDriverExpenditure(? ,? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ?,? , ? , ? , ?,? )";
	 public final static String GET_TRIP_STARTED_TRUCKS_STORED_PROC=
				"call getTripStartedTrucks(? ,? , ? )";
	 public final static String GET_TRIP_DRIVER_EXPENDITURE_STORED_PROC=
				"call getTripDriverExpenditure(? , ? , ? , ? , ? )";
	 public final static String GET_TRIP_DRIVERS_CLOSE_EXPENDITURE_STORED_PROC=
				"call getTripDriversCloseExpenditure(? , ? , ? , ? , ?  )";
	 public final static String ADD_TRIP_DRIVER_EXPENDITURE_STORED_PROC=
				"call addTripDriverExpenditure(? , ? ,? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ?,? , ? , ? , ?,? )";
	 public final static String GET_TRUCK_STATUS_STORED_PROC=
				"call getTruckStatus(? , ? , ? , ? , ? )";
	 public final static String ADD_FACTORY_LOADING_STORED_PROC=
				"call addFactoryLoading(? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String ADD_TRUCK_IDLE_STATUS_STORED_PROC=
				"call addTruckIdleStatus(? , ? , ? , ? , ? , ? )";
	 public final static String GET_LOGIN_USER_DETAILS_STORED_PROC=
				"call getLoginUserDetails(? , ? , ? , ? )";
	 public final static String GET_ASSIGNED_AVAILABLE_TRUCKS_STORED_PROC=
				"call getAssignedAvailableTrucks( ? , ? , ? )";
	 public final static String GET_TRUCKS_STATUS_TRACKING_STORED_PROC=
				"call getTruckStatusTracking(? , ? , ? , ? )";
	 public final static String GET_TRUCKS_GRID_CALENDAR_STORED_PROC=
				"call getTrucksGridCalendar(? , ? , ? , ? , ? )";
	 public final static String GET_EXPIRY_TRUCKS_PERMIT_STORED_PROC=
				"call getExpiryTrucksPermit( ? , ? , ? , ? )";
	 public final static String GET_EXPIRY_TRUCKS_INSURANCE_STORED_PROC=
				"call getExpiryTrucksInsurance( ? , ? , ? , ? )";
	 public final static String GET_EXPIRY_TRUCKS_FITNESS_STORED_PROC=
				"call getExpiryTrucksFitness( ? , ? , ? , ? )";
	 public final static String ADD_DISPATCH_STARTING_METER_READING_IMAGE_UPLOAD_URL_STORED_PROC=
				"call addDispatchStartingMeterReadingImageUrl(? , ? , ? , ? , ? )";
	 public final static String ADD_DISPATCH_CLOSING_METER_READING_IMAGE_UPLOAD_URL_STORED_PROC=
				"call addDispatchClosingMeterReadingImageUrl(? , ? , ? , ? , ? )";
	 public final static String GET_TRUCK_CURRENT_DAY_STATUS_DETAILS_STORED_PROC=
				"call getTruckCurrentDayStatusDetails(? , ? , ? , ? , ? )";
	 public final static String ADD_TRIP_STARTING_METER_READING_IMAGE_UPLOAD_URL_STORED_PROC=
				"call addTripStartingMeterReadingImageUrl(? , ? , ? , ? , ? )";
	 public final static String ADD_TRIP_CLOSING_METER_READING_IMAGE_UPLOAD_URL_STORED_PROC=
				"call addTripClosingMeterReadingImageUrl(? , ? , ? , ? , ? )";
	 public final static String ADD_TRUCK_MAINTENANCE_ADVANCE_REQUEST_STORED_PROC=
				"call addTruckMaintenanceAdvanceRequest(? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_NUMBER_STORED_PROC=
				"call getTruckMaintenanceAdvanceRequestNumber(? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_TRUCK_MAINTENANCE_ADVANCE_ITEM_DETAILS_STORED_PROC=
				"call getTruckMaintenanceAdvanceItemDetails( ? , ? , ? , ? , ? )";
	 public final static String GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_COUNT_STORED_PROC=
				"call getTruckMaintenanceAdvanceNotificationCount( ? , ? , ? , ? )";
	 public final static String UPDATE_TRUCK_MAINTENANCE_ADVANCE_APPROVAL_STATUS_STORED_PROC=
				"call updatetTruckMaintenanceAdvanceApprovalStatus(? , ? , ? , ? , ? )";
	 public final static String GET_AVAILABLE_DRIVERS_STORED_PROC = 
				"call getAvailableDrivers(  ? , ? , ? )";
	 public final static String GET_FUEL_STATION_NAME_STORED_PROC = 
				"call getFuelStationNames(  ? , ? , ? )";
	 public final static String UPDATE_DRIVER_SALARY_PAYMENT_STORED_PROC = 
				"call updateDriverSalaryPayment( ? , ? , ? , ? , ? )";
	 public final static String ADD_DRIVER_SALARY_PAYMENT_HISTORY_STORED_PROC = 
				"call addDriverSalaryPaymentHistory( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_FACTORIES_CUMULATIVE_REPORT_STORED_PROC =
				"call getFactoriesCumulativeReport(? , ? , ? , ? , ? , ?)";
	 public final static String GET_DISPATCH_REPORT_FOR_BILL_STORED_PROC =
				"call getDispatchReportForBill(? , ? , ? , ? , ? , ?)";
	 public final static String TOTAL_DISPATCH_REPORT_STORED_PROC =
				"call totalDispatchReport( ? , ? , ? , ? ,?)";
	 public final static String GET_UNLOAD_LOCATION_NAMES_STORED_PROC =
				"call getUnloadLocationNames( ? , ? , ? ,?)";
	 public final static String ADD_OUTSIDE_FACTORY_DISPATCH_STORED_PROC =
				"call addOutsideFactoryDispatch(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ?,? , ? , ? , ?,? , ? , ?)";
	 public final static String GET_OUTSIDE_COMPANY_NAMES_STORED_PROC =
				"call getOutsideCompanyNames( ? , ? , ? ,?)";
	 public final static String GET_FACTORY_DISPATCH_DAILY_REPORT_STORED_PROC =
				"call getFactoryDispatchDialyReport( ? , ? , ? , ? ,?)";
	 public final static String GET_FACTORY_DISPATCH_LOADING_REPORT_STORED_PROC =
				"call getFactoryDispatchLoadingReport( ? , ? , ? , ? ,?)";
	 public final static String GET_QUANTITY_AND_FREIGHT_STORED_PROC =
				"call getQuantityAndFreight(? , ? , ? , ? , ? ,?)";
	 public final static String GET_FACTORY_DISPATCH_BILL_STORED_PROC =
				"call getFactoryDispatchBill(? , ? , ? , ? , ? , ?)";
	 public final static String GET_FACTORY_DISPATCH_BILL_COUNT_STORED_PROC =
				"call getFactoryDispatchBillCount(? , ? , ? , ? , ? , ?)";
	 public final static String ADD_TRUCK_MAINTENANCE_EXPENDITURE_STORED_PROC =
				"call addTruckMaintenanceExpenditure(? , ? , ? , ? , ? , ? , ? , ? , ?)";
	 public final static String GET_REQUEST_OIL_STORED_PROC =
				"call getRequestOil(? , ? , ? , ? , ? )";
	 public final static String UPDATE_OIL_INVENTORY_COUNT_STORED_PROC =
				"call updateOilInventoryCount(? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_TRUCK_OVERALL_MAINTENANCE_REPORT_STORED_PROC =
				"call getTruckOverallMaintenanceReport(? , ? , ? , ? , ? )";
	 public final static String ADD_LAT_LONG_STORED_PROC =
				"call addLatLong( ? , ? , ? , ? , ? , ? )";
	 public final static String GET_DRIVER_FULL_DETAILS_STORED_PROC =
				"call getDriverFullDetails( ? , ? , ? )";
	 public final static String ADD_PETROL_PUMP_READING_IMAGE_UPLOAD_URL_STORED_PROC=
				"call addPetrolPumpMeterReadingImageUrl(? , ? , ? , ? , ? , ? )";
	 public final static String GET_FACTORY_DISPATCH_BY_ID_STORED_PROC =
				"call getFactoryDispatchById( ? , ? , ? , ? , ?)";
	 public final static String GET_LAT_LONG_STORED_PROC =
				"call getLatLOng( ? , ? , ?)";
	 public final static String UPDATE_OUTSIDE_FACTORY_DISPATCH_STORED_PROC =
				"call updateOutsideFactoryDispatch( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String CHECK_SPARE_PARTS_COUNT_STORED_PROC =
				"call checkSparePartsCount(? , ? , ? , ? , ?)";
	 public final static String GET_TRUCKS_STATUS_TRACKING_LIST_STORED_PROC=
				"call getTruckStatusTrackingList(? , ? , ? , ? )";
	 public final static String GET_TRUCKS_MAINTENANCE_COST_STORED_PROC=
				"call getTrucksMaintenanceCost(? , ? , ? , ? , ? )";
	 public final static String GET_TRUCKS_TRIP_COUNT_STORED_PROC=
				"call getTrucksTripCount(? , ? , ? , ? , ? )";
	 
	 
	 public final static String ADD_CASH_AND_BANK_ACCOUNT_STORED_PROC=
				"call addCashAndBankAccount(? , ? , ? , ? , ? , ? )";
	 public final static String ADD_ACCOUNT_NAME_STORED_PROC=
				"call addAccount(? , ? , ? , ? , ? , ? )";
	 public final static String ADD_PAYMENT_STORED_PROC=
				"call addPayment(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
	 public final static String ADD_RECEIPT_STORED_PROC=
				"call addReceipt(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String ADD_INCOME_STORED_PROC=
				"call addIncome(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String ADD_EXPENDITURE_STORED_PROC=
				"call addExpenditure(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_LEDGER_STORED_PROC=
				"call getLedger(? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_CASH_AND_BANK_NAME_STORED_PROC=
				"call getCashAndBankNames( ? , ? , ? )";
	 public final static String GET_ACCOUNT_NAME_STORED_PROC=
				"call getAccountNames( ? , ? , ? )";
	 public final static String GET_ALL_ACCOUNTS_STORED_PROC=
				"call getAllAccounts( ? , ? , ? )";
	 public final static String GET_TRIAL_BALANCE_STORED_PROC=
				"call getTrailBalance(? , ? , ? , ? , ? )";
	 public final static String GET_PURCHASE_ACCOUNT_NAMES_STORED_PROC=
				"call getPurchaseAccountNames( ? , ? , ? )";
	 public final static String GET_SALES_AND_INCOME_ACCOUNT_NAMES_STORED_PROC=
				"call getSalesAndIncomeAccountNames( ? , ? , ? )";
	 public final static String GET_VENDOR_AND_CUSTOMER_ACCOUNT_NAMES_STORED_PROC=
				"call getVendorAndCustomerAccountNames( ? , ? , ? )";
	 public final static String GET_VEHICLE_EXPENSES_ACCOUNT_NAMES_STORED_PROC=
				"call getVehicleExpensesAccountNames( ? , ? , ? )";
	 public final static String GET_ACCOUNT_GROUP_NAMES_STORED_PROC=
				"call getAccountGroupNames( ? , ? , ? )";
	 public final static String GET_BALANCE_SHEET_STORED_PROC=
				"call getBalanceSheet(? , ? , ? , ? , ? )";
	 public final static String GET_PROFIT_AND_LOSS_STORED_PROC=
				"call getProfitAndLoss(? , ? , ? , ? , ? )";
	 public final static String GET_ACCOUNT_TREE_NAMES_STORED_PROC=
				"call getAccountTreeNames( ? , ? , ? )";
	 public final static String GET_OPENING_BALANCE_STORED_PROC=
				"call getOpeningBalance( ? , ? , ? , ? , ? , ? , ? , ?)";
	 public final static String GET_TRUCK_MAINTENANCE_EXPENDITURE_STORED_PROC=
				"call getTruckMaintenaceExpenditure( ? , ? , ? , ? , ? , ? )";
	 public final static String GET_UNLOAD_REPORT_STORED_PROC=
				"call getUnloadReport( ? , ? , ? )";
	 public final static String GET_UNLOAD_REPORT_BY_ID_STORED_PROC=
				"call getUnloadReportById(? , ? , ? , ? )";
	 public final static String UPDATE_UNLOAD_REPORT_STORED_PROC=
				"call updateUnloadReport(? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_PAYMENT_STORED_PROC=
				"call getPayment(? , ? , ? , ? , ? )";
	 public final static String GET_RECEIPT_STORED_PROC=
				"call getReceipt(? , ? , ? , ? , ? )";
	 public final static String GET_EXPENDITURE_STORED_PROC=
				"call getExpenditure(? , ? , ? , ? , ? )";
	 public final static String GET_INCOME_STORED_PROC=
				"call getIncome(? , ? , ? , ? , ? )";
	 public final static String UPDATE_PAYMENT_STORED_PROC=
				"call updatePayment(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?  , ?)";
	 public final static String UPDATE_RECEIPT_STORED_PROC=
				"call updateReceipt( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String UPDATE_INCOME_STORED_PROC=
				"call updateIncome(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String UPDATE_EXPENDITURE_STORED_PROC=
				"call updateExpenditure(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String DELETE_PAYMENT_STORED_PROC=
				"call deletePayment( ? , ? , ? , ? )";
	 public final static String DELETE_RECEIPT_STORED_PROC=
				"call deleteReceipt( ? , ? , ? , ? )";
	 public final static String DELETE_INCOME_STORED_PROC=
				"call deleteIncome( ? , ? , ? , ? )";
	 public final static String DELETE_EXPENDITURE_STORED_PROC=
				"call deleteExpenditure( ? , ? , ? , ? )";
	 public final static String GET_ALL_AND_OTHER_TRUCKS_STORED_PROC=
				"call getAllAndOtherTrucks( ? , ? , ? )";
	 public final static String ADD_OPENING_BALANCE_STORED_PROC=
				"call addOpeningBalance(? , ? , ? , ? , ? , ? , ? )";
	 public final static String GET_OPENING_BALANCE_FOR_TB_STORED_PROC=
				"call getOpeningBalanceForTb(? , ? , ? , ? , ? )";
	 public final static String ADD_JOURNAL_ENTRY_STORED_PROC=
				"call addJournalEntry(? , ? , ? , ? , ? , ? , ? , ? , ?)";
	 public final static String GET_OPENING_BALANCES_STORED_PROC=
				"call getOpeningBalances(? , ? , ? )";
	 public final static String UPDATE_OPENING_BALANCES_STORED_PROC=
				"call updateOpeningBalances(? , ? , ? , ? , ? , ? , ? , ? )";
	 public final static String DELETE_OPENING_BALANCE_STORED_PROC=
				"call deleteOpeningbalance(? , ? , ? , ? )";
	 public final static String GET_TRUCK_PROFIT_AND_LOSS_STORED_PROC=
				"call getTruckProfitAndLoss(? , ? , ? , ? , ? , ? )";
	 
		public final static String GET_ASSOCIATION_GODOWN_STORED_PROC = 
				"call getAssociationGodown(?,?,?)";
		public final static String GET_FACTORY_FUEL_BY_FUEL_STATION_STORED_PROC = 
				"call getFactoryFuelByFuelStation( ? , ? , ? , ? , ? , ?)";
		public final static String GET_USER_LOGIN_DETAILS_STORED_PROC = 
				"call getUserLoginDetails( ? , ? , ? , ?)";
		public final static String ADD_INWARD_STORED_PROC = 
				"call addInward(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String GET_ACTIONS_STORED_PROC = 
				"call getActions(?,?,?)";
		public final static String GET_TYPECEMENT_STORED_PROC = 
				"call getTypeOfCement(?,?,?)";
		public final static String GET_INWARD_STORED_PROC =
				"call getInward(?,?,?,?,?,?)";
		public final static String UPDATE_INWARD_STORED_PROC =
				"call updateInward(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String GET_UNLOAD_STORED_PROC =
				"call unloadLocations(?,?,?)";
		public final static String DELETE_INWARD_STORED_PROC =
				"call deleteInward(?,?,?,?)";
		public final static String GET_BILL_STORED_PROC =
				"call hamaliBill(?,?,?,?,?,?,?)";
		public final static String ADD_OUTWARD_STORED_PROC =
				"call addOutward(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String GET_OUTWARD_STORED_PROC =
				"call getOutward(?,?,?,?,?,?)";
		public final static String UPDATE_OUTWARD_STORED_PROC =
				"call updateOutward(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String DELETE_OUTWARD_STORED_PROC =
				"call deleteOutward(?,?,?,?)";
		public final static String GET_HAMALI_STORED_PROC =
				"call hamaliDetails(?,?,?,?,?,?)";
		public final static String ADD_BILL_STORED_PROC =
				"call addBillingInfo(?,?,?,?,?,?)";
		public final static String GET_HAMALI_ACCOUNT_STORED_PROC =
				"call getHamaliAccount(?,?,?,?,?,?,?,?)";
		public final static String GET_HAMALI_TYPE_STORED_PROC = 
				"call hamaliType(?,?,?)";
		public final static String GET_BILL_DETAILS_STORED_PROC =
				"call getBillingDetails(?,?,?,?)";
		public final static String ADD_INWARD_TRUCK_OR_WORK_IMAGE_URLS_STORED_PROC =
				"call inward_update_truck_or_work_image_urls(?,?,?,?,?,?)";
		public final static String ADD_OUTWARD_TRUCK_OR_WORK_IMAGE_URLS_STORED_PROC =
				"call outward_update_truck_or_work_image_urls(?,?,?,?,?,?)";
		public final static String UPDATE_BILL_STORED_PROC =
				"call updateBilling(?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String DELETE_BILL_STORED_PROC =
				"call deleteBill(?,?,?,?)";
		public final static String GET_ASSOCIATION_STORED_PROC = 
				"call getAssociation( ? , ? , ? )";
		public final static String ADD_OUTWARD_HAMALI_STORED_PROC =
				"call addOutwardHamali(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String GET_OUTWARD_HAMALI_STORED_PROC =
				"call getOutwardHamali(?,?,?,?,?,?,?,?)";
		public final static String ADD_HAMALI_DETAILS_STORED_PROC =
				"call addHamaliDetails(?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String GET_HAMALI_DETAILS_STORED_PROC =
				"call getHamaliDetails(?,?,?,?)";
		public final static String UPDATE_HAMALI_DETAILS_STORED_PROC =
				"call updateHamaliDetails(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String DELETE_HAMALI_DETAILS_STORED_PROC =
				"call deleteHamaliDetails(?,?,?,?)";
		public final static String ADD_ADVANCE_BOOKING_STORED_PROC =
				"call addAdvanceBooking(?,?,?,?,?,?,?,?,?,?,?)";
		public final static String GET_ADVANCE_BOOKING_STORED_PROC =
				"call getAdvanceBooking(?,?,?,?,?,?)";
		public final static String UPDATE_ADVANCE_BOOKING_STORED_PROC =
				"call updateAdvanceBooking(?,?,?,?,?,?,?,?,?,?,?)";
		public final static String DELETE_ADVANCE_BOOKING_STORED_PROC =
				"call deleteAdvanceBooking(?,?,?,?)";
		public final static String ADD_CUSTOMER_STORED_PROC =
				"call addCustomer(?,?,?,?)";
		public final static String GET_CUSTOMER_STORED_PROC =
				"call getCustomerNames(?,?,?)";
		public final static String GET_OUTWARD_CUSTOMER_STORED_PROC =
				"call getOutwardCustomer(?,?,?,?,?,?)";
		public final static String UPDATE_OUTWARD_CUSTOMER_STORED_PROC =
				"call updateOutwardCustomer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String DELETE_OUTWARD_CUSTOMER_STORED_PROC =
				"call deleteOutwardCustomer(?,?,?,?)";
		public final static String GET_DC_PENDING_STORED_PROC =
				"call getDcPending(?,?,?,?,?,?)";
		public final static String UPDATE_DC_PENDING_STORED_PROC =
				"call updateDcPending(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		public final static String DELETE_DC_PENDING_STORED_PROC =
				"call deleteDcPending(?,?,?,?)";
		 public final static String ADD_ACCOUNT_GROUP_STORED_PROC=
					"call addAccountGroup(? , ? , ? , ? , ? )";
		 public final static String GET_ACCOUNT_GROUPS_STORED_PROC=
					"call getAccountGroup(? , ? , ? )";
		 public final static String UPDATE_ACCOUNT_GROUP_STORED_PROC=
					"call updateAccountGroup(? , ? , ? , ? , ? , ?)";
		 public final static String DELETE_ACCOUNT_GROUP_STORED_PROC=
					"call deleteAccountGroup(? , ? , ? , ? )";
		 public final static String GET_TRUCK_MAINTENANCE_EXPENDITURE_REPORT_STORED_PROC=
					"call getTruckMaintenaceExpenditureReport( ? , ? , ? , ? , ? )";
		 public final static String GET_FREIGHT_AND_QUANTITY_STORED_PROC=
					"call getFreightAndQuantity( ? , ? , ? , ? , ? )";
		 public final static String GET_FUEL_COST_REPORT_STORED_PROC=
					"call getFuelCostReport( ? , ? , ? , ? , ? )";
		 public final static String GET_FUEL_STATION_COST_REPORT_STORED_PROC=
					"call getFuelStationCostReport( ? , ? , ? , ? , ? )";
		 public final static String DELETE_FACTORY_DISPATCH_STORED_PROC=
					"call deleteFactoryDispatch(? , ? , ? , ? )";
		 public final static String GET_INWARD_ACTIONS_STORED_PROC = 
					"call getInwardActions(?,?,?)";
		public final static String GET_OUTWARD_ACTIONS_STORED_PROC = 
					"call getOutwardActions(?,?,?)";
		public final static String GET_TODAY_PAYMENT_RECEIPT_AMOUNT_STORED_PROC = 
				"call getTodayPaymentReceiptAmount(?,?,?,?,?,?,?)";
		public final static String GET_FREIGHT_AND_QUANTITY_MONTH_STORED_PROC = 
				"call getFreightAndQuantityMonth(?,?,?,?,?)";
		public final static String UPDATE_DISPATCH_BILL_IDS_STORED_PROC = 
				"call updateDispatchIdsBill(?,?,?,?)";
		public final static String GET_GENERATED_BILLS_STORED_PROC = 
				"call getGeneratedBills(?,?,?,?)";
		public final static String GET_GENERATED_BILLS_GROUP_STORED_PROC = 
				"call getGeneratedBillsGroup(?,?,?,?)";
		public final static String ADD_DRIVER_ADVANCE_STORED_PROC = 
				"call addDriverAdvance(? , ? , ? , ? , ? , ? , ? , ?)";
		public final static String GET_ALL_DRIVER_BALANCE_STORED_PROC = 
				"call getAllDriverBalance( ? , ? , ?)";
		public final static String GET_DRIVER_EXPENDITURE_FOR_APPROVAL_STORED_PROC = 
				"call getDriverExpenditureForApproval( ? , ? , ?)";
		 public final static String UPDATE_DRIVER_EXPENDITURE_APPROVAL_STORED_PROC=
					"call updateDriverExpenditureApproval(? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ? ,? , ? , ? , ?,? , ? , ? , ?,? )";
		 public final static String GET_DISPATCH_FUEL_LINKING_STORED_PROC = 
					"call getDispatchFuelLinking( ? , ? , ?)";
		 public final static String GET_STOCK_STORED_PROC =
					"call getStock(?,?,?,?,?,?,?)";
			public final static String GET_STOCK_TYPE_STORED_PROC = 
					"call stockType(?,?,?)";
			public final static String GET_ACTIONS_BILL_TYPE_STORED_PROC = 
					"call getBillActions(?,?,?)";
			public final static String  ADD_EXCEL_BILL_STORED_PROC = 
					"call addExcelBill(?,?,?,?,?,?,?,?,?,?,?)";
			public final static String GET_BILL_EXCEL_STORED_PROC =
					"call hamaliBillExcel(?,?,?,?,?,?)";
			public final static String GET_FACTORY_DISPATCH_FOR_INVOICE_PHOTO_STORED_PROC =
					"call getFactoryDispatchForInvoicePhoto(?,?,?)";
//--------------------------------------------------------------------------------------------------------
	/**
	 * Stored Proc IDs and Stored Proc Names Map
	 */
	public final static HashMap<Integer, String> ProcIDsVsNames = new HashMap<Integer, String>(){	

		/**
		 *  
		 */
		private static final long serialVersionUID = 1L;

		{       
			put(LOGIN_AUTHENTICATION, LOGIN_AUTHENTICATION_STORED_PROC);
			put(SIGNUP, SIGNUP_STORED_PROC);
			put(USER_DETAILS_GENERAL, USER_DETAILS_GENERAL_STORED_PROC);
			put(USER_DETAILS_BASIC, USER_DETAILS_BASIC_STORED_PROC);
			put(USER_DETAILS_PERSONAL_CONTACT, USER_DETAILS_PERSONAL_CONTACT_STORED_PROC);
			put(USER_DETAILS_EDUCATION, USER_DETAILS_EDUCATION_STORED_PROC);
			put(USER_DETAILS_EMPLOYMENT, USER_DETAILS_EMPLOYMENT_STORED_PROC);
			put(USER_DETAILS_BUSINESS, USER_DETAILS_BUSINESS_STORED_PROC);
			put(ADD_CARD, ADD_CARD_STORED_PROC);
			put(USER_CONTACTS_SHARE, USER_CONTACTS_SHARE_STORED_PROC);
			put(OWN_CARD_BASIC_AND_PERSONAL_FIELDS, OWN_CARD_BASIC_AND_PERSONAL_FIELDS_STORED_PROC);
			put(OWN_CARD_EMPLOYMENT_FIELDS, OWN_CARD_EMPLOYMENT_FIELDS_STORED_PROC);
			put(OWN_CARD_BUSINESS_FIELDS, OWN_CARD_BUSINESS_FIELDS_STORED_PROC);
			put(OWN_CARD_PERSONAL_SOCIAL_MEDIA_FIELDS, OWN_CARD_PERSONAL_SOCIAL_MEDIA_FIELDS_STORED_PROC);	
			put(GET_OWN_CARDS, GET_OWN_CARDS_STORED_PROC);	
			put(GET_OWN_CARD_BASIC_PERSONAL_DETAILS, GET_OWN_CARD_BASIC_PERSONAL_DETAILS_STORED_PROC);	
			put(GET_OWN_CARD_EMPLOYMENT_DETAILS, GET_OWN_CARD_EMPLOYMENT_DETAILS_STORED_PROC);	
			put(GET_OWN_CARD_BUSINESS_DETAILS, GET_OWN_CARD_BUSINESS_DETAILS_STORED_PROC);	
			put(GET_OWN_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS, GET_OWN_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS_STORED_PROC);	
			put(GET_OWN_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS, GET_OWN_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS_STORED_PROC);	
			put(GET_CARDS, GET_CARDS_STORED_PROC);	
			put(SHARE_CARD_WITH_HOOP_USER, SHARE_CARD_WITH_HOOP_USER_STORED_PROC);	
			put(SEARCH_USERS, SEARCH_USERS_STORED_PROC);	
			put(GET_LOGGED_IN_USER_NAME, GET_LOGGED_IN_USER_NAME_STORED_PROC);	
			put(GET_HOOP_USERS_MATCHING_WITH_CONTACTS, GET_HOOP_USERS_MATCHING_WITH_CONTACTS_STORED_PROC);	
			put(GET_USER_DETAILS_BASIC, GET_USER_DETAILS_BASIC_STORED_PROC);	
			put(GET_USER_DETAILS_GENERAL, GET_USER_DETAILS_GENERAL_STORED_PROC);	
			put(GET_USER_DETAILS_PERSONAL_CONTACT, GET_USER_DETAILS_PERSONAL_CONTACT_STORED_PROC);	
			put(GET_USER_DETAILS_EDUCATION, GET_USER_DETAILS_EDUCATION_STORED_PROC);	
			put(GET_USER_DETAILS_EMPLOYMENT, GET_USER_DETAILS_EMPLOYMENT_STORED_PROC);	
			put(GET_USER_DETAILS_BUSINESS, GET_USER_DETAILS_BUSINESS_STORED_PROC);	
			
			put(UPDATE_USER_DETAILS_BASIC, UPDATE_USER_DETAILS_BASIC_STORED_PROC);	
			put(UPDATE_USER_DETAILS_GENERAL, UPDATE_USER_DETAILS_GENERAL_STORED_PROC);	
			put(UPDATE_USER_DETAILS_PERSONAL_CONTACT, UPDATE_USER_DETAILS_PERSONAL_CONTACT_STORED_PROC);	
			put(UPDATE_USER_DETAILS_EDUCATION, UPDATE_USER_DETAILS_EDUCATION_STORED_PROC);	
			put(UPDATE_USER_DETAILS_EMPLOYMENT, UPDATE_USER_DETAILS_EMPLOYMENT_STORED_PROC);	
			put(UPDATE_USER_DETAILS_BUSINESS, UPDATE_USER_DETAILS_BUSINESS_STORED_PROC);	
			
			put(GET_USER_REFERRED_CONTACTS, GET_USER_REFERRED_CONTACTS_STORED_PROC);	
			put(GET_USER_DETAILS_PERSONAL_SOCIAL_MEDIA, GET_USER_DETAILS_PERSONAL_SOCIAL_MEDIA_STORED_PROC);	
			put(UPDATE_USER_DETAILS_PERSONAL_SOCIAL_MEDIA, UPDATE_USER_DETAILS_PERSONAL_SOCIAL_MEDIA_STORED_PROC);	
			put(USER_DETAILS_PERSONAL_SOCIAL_MEDIA, USER_DETAILS_PERSONAL_SOCIAL_MEDIA_STORED_PROC);	
			put(GET_ALL_CONNECTED_USERS, GET_ALL_CONNECTED_USERS_STORED_PROC);	
			put(GET_ALL_CONNECTED_USERS, GET_ALL_CONNECTED_USERS_STORED_PROC);	
			put(GET_FILTERED_CONNECTED_USERS_BY_LOCATION, GET_FILTERED_CONNECTED_USERS_BY_LOCATION_STORED_PROC);	
			put(ADD_USER_DETAILS_UPDATE_NOTIFICATIONS, ADD_USER_DETAILS_UPDATE_NOTIFICATIONS_STORED_PROC);	
			put(GET_USER_DETAILS_UPDATE_NOTIFICATIONS, GET_USER_DETAILS_UPDATE_NOTIFICATIONS_STORED_PROC);	
			put(SET_USER_DETAILS_UPDATE_NOTIFICATIONS_VIEWED, SET_USER_DETAILS_UPDATE_NOTIFICATIONS_VIEWED_STORED_PROC);	
			put(DELETE_USER_DETAILS_UPDATE_NOTIFICATIONS_RECORD, DELETE_USER_DETAILS_UPDATE_NOTIFICATIONS_RECORD_STORED_PROC);	
			put(ADD_OWN_CARD_SHARE_NOTIFICATION, ADD_OWN_CARD_SHARE_NOTIFICATION_STORED_PROC);	
			put(GET_OWN_CARD_SHARE_NOTIFICATIONS, GET_OWN_CARD_SHARE_NOTIFICATIONS_STORED_PROC);	
			put(SET_OWN_CARD_SHARE_NOTIFICATIONS_VIEWED, SET_OWN_CARD_SHARE_NOTIFICATIONS_VIEWED_STORED_PROC);	
			put(SET_OWN_CARD_SHARE_NOTIFICATIONS_VIEWED, SET_OWN_CARD_SHARE_NOTIFICATIONS_VIEWED_STORED_PROC);	
			put(DELETE_OWN_CARD_SHARE_NOTIFICATION_RECORD, DELETE_OWN_CARD_SHARE_NOTIFICATION_RECORD_STORED_PROC);	
			put(ADD_USER_DETAIL_MISSING_NOTIFICATION, ADD_USER_DETAIL_MISSING_NOTIFICATION_STORED_PROC);	
			put(GET_USER_DETAIL_MISSING_NOTIFICATIONS, GET_USER_DETAIL_MISSING_NOTIFICATIONS_STORED_PROC);	
			put(SET_USER_DETAIL_MISSING_NOTIFICATIONS_VIEWED, SET_USER_DETAIL_MISSING_NOTIFICATIONS_VIEWED_STORED_PROC);	
			put(SHARE_CARD_WITH_NON_HOOP_USER, SHARE_CARD_WITH_NON_HOOP_USER_STORED_PROC);	
			put(ADD_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION, ADD_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_STORED_PROC);	
			put(GET_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATIONS, GET_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATIONS_STORED_PROC);	
			put(SET_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_VIEWED, SET_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_VIEWED_STORED_PROC);	
			put(DELETE_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_RECORD, DELETE_OWN_CARD_SHARE_WITH_NON_HOOP_USER_NOTIFICATION_RECORD_STORED_PROC);	
			put(GET_CARD_OWNER, GET_CARD_OWNER_STORED_PROC);	
			put(GET_RECEIVED_CARDS, GET_RECEIVED_CARDS_STORED_PROC);	
			put(SET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS, SET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_STORED_PROC);	
			put(GET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_NOTIFICATIONS, GET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_NOTIFICATIONS_STORED_PROC);	
			put(SET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_NOTIFICATIONS_VIEWED, SET_USER_DETAIL_MISSING_ALLOWED_FOR_ACCESS_NOTIFICATIONS_VIEWED_STORED_PROC);	
			put(GET_USER_DETAILS_MISSING, GET_USER_DETAILS_MISSING_STORED_PROC);	
			put(GET_IMAGE_URL_FROM_USERNAME, GET_IMAGE_URL_FROM_USERNAME_STORED_PROC);	
			put(GET_IMAGE_URL_FROM_USERID, GET_IMAGE_URL_FROM_USERID_STORED_PROC);	
			put(CREATE_GROUP, CREATE_GROUP_STORED_PROC);	
			put(ADD_GROUP_MEMBERS, ADD_GROUP_MEMBERS_STORED_PROC);	
			put(GET_GROUP_MEMBERS, GET_GROUP_MEMBERS_STORED_PROC);	
			put(UPDATE_GROUP_MEMBERS, UPDATE_GROUP_MEMBERS_STORED_PROC);	
			put(GET_RECENT_CONNECTED_USERS, GET_RECENT_CONNECTED_USERS_STORED_PROC);	
			put(GET_FILTERED_CONNECTED_USERS_BY_GENDER, GET_FILTERED_CONNECTED_USERS_BY_GENDER_STORED_PROC);	
			put(GET_FILTERED_CONNECTED_USERS_BY_AGE, GET_FILTERED_CONNECTED_USERS_BY_AGE_STORED_PROC);	
			put(GET_FILTERED_CONNECTED_USERS_BY_INDUSTRY, GET_FILTERED_CONNECTED_USERS_BY_INDUSTRY_STORED_PROC);	
			put(GET_FILTERED_CONNECTED_USERS_BY_PROFESSION, GET_FILTERED_CONNECTED_USERS_BY_PROFESSION_STORED_PROC);	
			put(GET_GROUPS, GET_GROUPS_STORED_PROC);	
			put(GET_CARD_NAME_FROM_CARD_ID, GET_CARD_NAME_FROM_CARD_ID_STORED_PROC);	
			put(DELETE_OWN_CARD, DELETE_OWN_CARD_STORED_PROC);	
			put(SHARE_CARD_WITH_HOOP_USER_VIA_BLUETOOTH, SHARE_CARD_WITH_HOOP_USER_VIA_BLUETOOTH_STORED_PROC);	
			put(GET_HOOP_USERS_MATCHING_BY_ANY_INFO, GET_HOOP_USERS_MATCHING_BY_ANY_INFO_STORED_PROC);	
			put(GET_USER_DETAILS_MISSING_ALREADY_SHARED_ON_REQUEST, GET_USER_DETAILS_MISSING_ALREADY_SHARED_ON_REQUEST_STORED_PROC);	
			put(GET_CONNECTED_USERS_MATCHING_BY_ANY_INFO, GET_CONNECTED_USERS_MATCHING_BY_ANY_INFO_STORED_PROC);	
			
			put(CUSTOM_CARD_BASIC_AND_PERSONAL_FIELDS, CUSTOM_CARD_BASIC_AND_PERSONAL_FIELDS_STORED_PROC);
			put(CUSTOM_CARD_EMPLOYMENT_FIELDS, CUSTOM_CARD_EMPLOYMENT_FIELDS_STORED_PROC);
			put(CUSTOM_CARD_BUSINESS_FIELDS, CUSTOM_CARD_BUSINESS_FIELDS_STORED_PROC);
			put(CUSTOM_CARD_PERSONAL_SOCIAL_MEDIA_FIELDS, CUSTOM_CARD_PERSONAL_SOCIAL_MEDIA_FIELDS_STORED_PROC);
			
			put(SHARE_CUSTOM_CARD, SHARE_CUSTOM_CARD_STORED_PROC);
			put(ADD_CUSTOM_CARD_SHARE_NOTIFICATION, ADD_CUSTOM_CARD_SHARE_NOTIFICATION_STORED_PROC);
			
			put(GET_CUSTOM_CARD_BASIC_PERSONAL_DETAILS, GET_CUSTOM_CARD_BASIC_PERSONAL_DETAILS_STORED_PROC);
			put(GET_CUSTOM_CARD_EMPLOYMENT_DETAILS, GET_CUSTOM_CARD_EMPLOYMENT_DETAILS_STORED_PROC);
			put(GET_CUSTOM_CARD_BUSINESS_DETAILS, GET_CUSTOM_CARD_BUSINESS_DETAILS_STORED_PROC);
			put(GET_CUSTOM_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS, GET_CUSTOM_CARD_PERSONAL_SOCIAL_MEDIA_DETAILS_STORED_PROC);
			
			put(GET_CUSTOM_CARD_OWNER, GET_CUSTOM_CARD_OWNER_STORED_PROC);
			put(GET_USER_DETAIL_SHARED_THROUGH_CUSTOM_CARD, GET_USER_DETAIL_SHARED_THROUGH_CUSTOM_CARD_STORED_PROC);
			put(DELETE_GROUP, DELETE_GROUP_STORED_PROC);
			put(GET_NON_HOOP_CONTACTS, GET_NON_HOOP_CONTACTS_STORED_PROC);
			put(GET_USER_DETAIL_SHARED_THROUGH_NORMAL_CARD, GET_USER_DETAIL_SHARED_THROUGH_NORMAL_CARD_STORED_PROC);
			put(ACTIVATE_USER_WITH_OTP, ACTIVATE_USER_WITH_OTP_STORED_PROC);
			put(GET_SINGLE_CONNECTED_USER_DETAILS, GET_SINGLE_CONNECTED_USER_DETAILS_STORED_PROC);
			
			
			put(ADD_DRIVER_DETAILS, ADD_DRIVER_DETAILS_STORED_PROC);
			put(ADD_SELLER_FUEL, ADD_SELLER_FUEL_STORED_PROC);
			put(ADD_SELLER_DISPATCH, ADD_SELLER_DISPATCH_STORED_PROC);
			put(ADD_SELLER_INVOICE, ADD_SELLER_INVOICE_STORED_PROC);
			put(ADD_SELLER_PURCHASE, ADD_SELLER_PURCHASE_STORED_PROC);
			put(ADD_STORAGE_FUEL, ADD_STORAGE_FUEL_STORED_PROC);
			put(ADD_STORAGE_DISPATCH, ADD_STORAGE_DISPATCH_STORED_PROC);
			put(ADD_STORAGE_INVOICE, ADD_STORAGE_INVOICE_STORED_PROC);
			put(ADD_STORAGE_INCOMING_LOAD, ADD_STORAGE_INCOMING_LOAD_STORED_PROC);
			put(ADD_TRUCKS_ALLOTMENT, ADD_TRUCKS_ALLOTMENT_STORED_PROC);
			put(ADD_TRUCKS_DETAILS, ADD_TRUCKS_DETAILS_STORED_PROC);
			put(ADD_TRUCKS_HEALTH_HISTORY, ADD_TRUCKS_HEALTH_HISTORY_STORED_PROC);
			put(ADD_TRUCKS_PARTS_QUOTE, ADD_TRUCKS_PARTS_QUOTE_STORED_PROC);
		//	put(ADD_TRUCKS_SERVICE, ADD_TRUCKS_SERVICE_STORED_PROC);
			put(ADD_SPARE_PART_ALLOTMENT, ADD_SPARE_PART_ALLOTMENT_STORED_PROC);
			
			put(GET_STORAGE_DISPATCH, GET_STORAGE_DISPATCH_STORED_PROC);
			put(GET_STORAGE_FUEL, GET_STORAGE_FUEL_STORED_PROC);
			put(GET_STORAGE_INCOMING_LOAD, GET_STORAGE_INCOMING_LOAD_STORED_PROC);
			put(GET_STORAGE_INVOICE, GET_STORAGE_INVOICE_STORED_PROC);
			put(GET_SELLER_DISPATCH, GET_SELLER_DISPATCH_STORED_PROC);
			put(GET_SELLER_FUEL, GET_SELLER_FUEL_STORED_PROC);
			put(GET_SELLER_INVOICE, GET_SELLER_INVOICE_STORED_PROC);
			put(GET_SELLER_PURCHASE, GET_SELLER_PURCHASE_STORED_PROC);
			put(GET_TRUCKS_DETAILS, GET_TRUCKS_DETAILS_STORED_PROC);
			put(GET_TRUCKS_ALLOTMENT, GET_TRUCKS_ALLOTMENT_STORED_PROC);
			put(GET_TRUCKS_PARTS_QUOTE, GET_TRUCKS_PARTS_QUOTE_STORED_PROC);
			put(GET_TRUCKS_HEALTH_HISTORY, GET_TRUCKS_HEALTH_HISTORY_STORED_PROC);
			put(GET_TRUCKS_SERVICE, GET_TRUCKS_SERVICE_STORED_PROC);
			put(GET_SPARE_PART_ALLOTMENT, GET_SPARE_PART_ALLOTMENT_STORED_PROC);
			put(GET_STORAGE_DISPATCH_BY_DATE, GET_STORAGE_DISPATCH_BY_DATE_STORED_PROC);
			put(GET_STORAGE_FUEL_BY_DATE, GET_STORAGE_FUEL_BY_DATE_STORED_PROC);
			put(GET_STORAGE_INCOMING_LOAD_BY_DATE, GET_STORAGE_INCOMING_LOAD_BY_DATE_STORED_PROC);
			put(GET_STORAGE_INVOICE_BY_DATE, GET_STORAGE_INVOICE_BY_DATE_STORED_PROC);
			put(GET_SELLER_DISPATCH_BY_DATE, GET_SELLER_DISPATCH_BY_DATE_STORED_PROC);
			put(GET_SELLER_FUEL_BY_DATE, GET_SELLER_FUEL_BY_DATE_STORED_PROC);
			put(GET_SELLER_INVOICE_BY_DATE, GET_SELLER_INVOICE_BY_DATE_STORED_PROC);
			put(GET_SELLER_PURCHASE_BY_DATE, GET_SELLER_PURCHASE_BY_DATE_STORED_PROC);
			put(GET_TRUCKS_ALLOTMENT_BY_DATE, GET_TRUCKS_ALLOTMENT_BY_DATE_STORED_PROC);
			put(GET_TRUCKS_HEALTH_HISTORY_BY_DATE, GET_TRUCKS_HEALTH_HISTORY_BY_DATE_STORED_PROC);
			put(GET_TRUCKS_PARTS_QUOTE_BY_DATE, GET_TRUCKS_PARTS_QUOTE_BY_DATE_STORED_PROC);
			put(GET_TRUCKS_SERVICE_BY_DATE, GET_TRUCKS_SERVICE_BY_DATE_STORED_PROC);
			put(GET_SPARE_PART_ALLOTMENT_BY_DATE, GET_SPARE_PART_ALLOTMENT_BY_DATE_STORED_PROC);
			put(UPDATE_DRIVER_DETAILS, UPDATE_DRIVER_DETAILS_STORED_PROC);
			put(UPDATE_STORAGE_DISPATCH, UPDATE_STORAGE_DISPATCH_STORED_PROC);
			put(UPDATE_STORAGE_FUEL, UPDATE_STORAGE_FUEL_STORED_PROC);
			put(UPDATE_STORAGE_INVOICE, UPDATE_STORAGE_INVOICE_STORED_PROC);
			put(UPDATE_STORAGE_INCOMING_LOAD, UPDATE_STORAGE_INCOMING_LOAD_STORED_PROC);
			
			put(UPDATE_SELLER_FUEL, UPDATE_SELLER_FUEL_STORED_PROC);
			put(UPDATE_SELLER_DISPATCH, UPDATE_SELLER_DISPATCH_STORED_PROC);
			put(UPDATE_SELLER_INVOICE, UPDATE_SELLER_INVOICE_STORED_PROC);
			put(UPDATE_SELLER_PURCHASE, UPDATE_SELLER_PURCHASE_STORED_PROC);
			put(UPDATE_TRUCKS_ALLOTMENT, UPDATE_TRUCKS_ALLOTMENT_STORED_PROC);
			put(UPDATE_TRUCKS_DETAILS, UPDATE_TRUCKS_DETAILS_STORED_PROC);
			put(UPDATE_TRUCKS_HEALTH_HISTORY, UPDATE_TRUCKS_HEALTH_HISTORY_STORED_PROC);
			put(UPDATE_TRUCKS_PARTS_QUOTE, UPDATE_TRUCKS_PARTS_QUOTE_STORED_PROC);
			put(UPDATE_TRUCKS_SERVICE, UPDATE_TRUCKS_SERVICE_STORED_PROC);
			put(UPDATE_SPARE_PART_ALLOTMENT, UPDATE_SPARE_PART_ALLOTMENT_STORED_PROC);
			put(GET_ALL_DRIVER_DETAILS, GET_ALL_DRIVER_DETAILS_STORED_PROC);
			put(GET_DRIVER_DETAILS, GET_DRIVER_DETAILS_STORED_PROC);
			put(GET_ALL_SPARE_PARTS, GET_ALL_SPARE_PARTS_STORED_PROC);
			put(GET_ALL_TRUCKS_DETAILS, GET_ALL_TRUCKS_DETAILS_STORED_PROC);
			put(GET_TRUCKS, GET_TRUCKS_STORED_PROC);
			put(GET_ALL_TRUCKS, GET_ALL_TRUCKS_STORED_PROC);
			put(GET_STATUS_DISPATCH, GET_STATUS_DISPATCH_STORED_PROC);
			put(UPDATE_STATUS_DISPATCH, UPDATE_STATUS_DISPATCH_STORED_PROC);
			put(GET_CLOSE_DISPATCH, GET_CLOSE_DISPATCH_STORED_PROC);
			put(UPDATE_CLOSE_DISPATCH, UPDATE_CLOSE_DISPATCH_STORED_PROC);
			put(GET_STATUS_STORAGE_DISPATCH, GET_STATUS_STORAGE_DISPATCH_STORED_PROC);
			put(UPDATE_STATUS_STORAGE_DISPATCH, UPDATE_STATUS_STORAGE_DISPATCH_STORED_PROC);
			put(GET_CLOSE_STORAGE_DISPATCH, GET_CLOSE_STORAGE_DISPATCH_STORED_PROC);
			put(UPDATE_CLOSE_STORAGE_DISPATCH, UPDATE_CLOSE_STORAGE_DISPATCH_STORED_PROC);
			
			
			//------------------------------------------------------------------------
			
			
//			put(ADD_DRIVER_DETAILS,ADD_DRIVER_DETAILS_URL_STORED_PROC);
			put(UPDATE_FACTORY_DISPATCH_INITIALLY,UPDATE_FACTORY_DISPATCH_INITIALLY_STORED_PROC);
			put(ADD_FACTORY_FUEL,ADD_FACTORY_FUEL_URL_STORED_PROC);
			put(ADD_FACTORY_INVOICE,ADD_FACTORY_INVOICE_URL_STORED_PROC);
			put(ADD_VEHICLE_TYRE_SUMMARY_REPORT,ADD_VEHICLE_TYRE_SUMMARY_REPORT_URL_STORED_PROC);
			put(ADD_FRONT_TOTAL_SUMMARY,ADD_FRONT_TOTAL_SUMMARY_URL_STORED_PROC);
			put(ADD_HOSING_TOTAL_SUMMARY,ADD_HOSING_TOTAL_SUMMARY_URL_STORED_PROC);
			put(ADD_LOCATION_ALLOTMENT,ADD_LOCATION_ALLOTMENT_URL_STORED_PROC);
			put(ADD_LOCATION_STATUS,ADD_LOCATION_STATUS_URL_STORED_PROC);
//			put(GET_DRIVER_DETAILS,GET_DRIVER_DETAILS_URL_STORED_PROC);
			put(GET_FACTORY_DISPATCH,GET_FACTORY_DISPATCH_URL_STORED_PROC);
			put(GET_FACTORY_FUEL,GET_FACTORY_FUEL_URL_STORED_PROC);
			put(GET_FACTORY_INVOICE,GET_FACTORY_INVOICE_URL_STORED_PROC);
			put(GET_VEHICLE_TYRE_SUMMARY_REPORT,GET_VEHICLE_TYRE_SUMMARY_REPORT_URL_STORED_PROC);
			put(GET_LOCATION_ALLOTMENT,GET_LOCATION_ALLOTMENT_URL_STORED_PROC);
			put(GET_LOCATION_STATUS,GET_LOCATION_STATUS_URL_STORED_PROC);
			put(GET_FACTORY_DISPATCH_BY_DATE,GET_FACTORY_DISPATCH_BY_DATE_URL_STORED_PROC);
			put(GET_FACTORY_DISPATCH_FUEL_BY_DATE,GET_FACTORY_DISPATCH_FUEL_BY_DATE_URL_STORED_PROC);
			put(GET_FACTORY_DISPATCH_BY_ID,GET_FACTORY_DISPATCH_BY_ID_STORED_PROC);
			put(GET_FACTORY_FUEL_BY_DATE,GET_FACTORY_FUEL_BY_DATE_URL_STORED_PROC);
			put(GET_FACTORY_INVOICE_BY_DATE,GET_FACTORY_INVOICE_BY_DATE_URL_STORED_PROC);
			put(GET_LOCATION_ALLOTMENT_BY_DATE,GET_LOCATION_ALLOTMENT_BY_DATE_URL_STORED_PROC);
			put(GET_LOCATION_STATUS_BY_DATE,GET_LOCATION_STATUS_BY_DATE_URL_STORED_PROC);
//			put(UPDATE_DRIVER_DETAILS,UPDATE_DRIVER_DETAILS_URL_STORED_PROC);
			put(UPDATE_FACTORY_DISPATCH,UPDATE_FACTORY_DISPATCH_URL_STORED_PROC);
			put(UPDATE_FACTORY_FUEL,UPDATE_FACTORY_FUEL_URL_STORED_PROC);
			put(UPDATE_FACTORY_INVOICE,UPDATE_FACTORY_INVOICE_URL_STORED_PROC);
			put(UPDATE_LOCATION_ALLOTMENT,UPDATE_LOCATION_ALLOTMENT_URL_STORED_PROC);
			put(UPDATE_LOCATION_STATUS,UPDATE_LOCATION_STATUS_URL_STORED_PROC);
			put(GET_VEHICLE_TYRE_SUMMARY_REPORT_BY_DATE,GET_VEHICLE_TYRE_SUMMARY_REPORT_BY_DATE_URL_STORED_PROC);
//			put(GET_ALL_DRIVER_DETAILS,GET_ALL_DRIVER_DETAILS_URL_STORED_PROC);
			put(UPDATE_VEHICLE_TYRE_SUMMARY_REPORT, UPDATE_VEHICLE_TYRE_SUMMARY_REPORT_URL_STORED_PROC);
//			put(ADD_TRUCKS_DETAILS,ADD_TRUCKS_DETAILS_URL_STORED_PROC);
	//		put(GET_TRUCKS_DETAILS,GET_TRUCKS_DETAILS_URL_STORED_PROC);
//			put(GET_ALL_TRUCKS_DETAILS,GET_ALL_TRUCKS_DETAILS_URL_STORED_PROC);
	//		put(UPDATE_TRUCKS_DETAILS,UPDATE_TRUCKS_DETAILS_URL_STORED_PROC);
			put(GET_FACTORY_DISPATCH_STATUS,GET_FACTORY_DISPATCH_STATUS_URL_STORED_PROC);
		    put(UPDATE_FACTORY_DISPATCH_STATUS,UPDATE_FACTORY_DISPATCH_STATUS_URL_STORED_PROC);
		    put(GET_CLOSE_FACTORY_DISPATCH,GET_CLOSE_FACTORY_DISPATCH_URL_STORED_PROC);
		    put(UPDATE_CLOSE_FACTORY_DISPATCH,UPDATE_CLOSE_FACTORY_DISPATCH_URL_STORED_PROC);
		    
		    put(ADD_REGISTRATION_USERS,ADD_REGISTRATION_USERS_STORED_PROC);
		    put(ADD_REGISTRATION_BASIC,ADD_REGISTRATION_BASIC_STORED_PROC);
		    put(GET_ACTIONS_LOG,GET_ACTIONS_LOG_STORED_PROC);
		    
		    put(ADD_FACTORY_TRIP_DETAILS,ADD_FACTORY_TRIP_DETAILS_STORED_PROC);
		    put(GET_TRIP_DETAILS,GET_TRIP_DETAILS_STORED_PROC);
		    put(UPDATE_TRIP_DETAILS,UPDATE_TRIP_DETAILS_STORED_PROC);
		    put(GET_CLOSE_TRIP_DETAILS,GET_CLOSE_TRIP_DETAILS_STORED_PROC);
		    put(UPDATE_CLOSE_TRIP_DETAILS,UPDATE_CLOSE_TRIP_DETAILS_STORED_PROC);
		    
		    put(GET_DRIVER_SALARY_SLIP,GET_DRIVER_SALARY_SLIP_STORED_PROC);
		    put(GET_DRIVER_SALARY,GET_DRIVER_SALARY_STORED_PROC);
		    put(GET_TRIP_BY_DATE,GET_TRIP_BY_DATE_STORED_PROC);
		    
		    put(ADD_STORAGE_TRIP_DETAILS,ADD_STORAGE_TRIP_DETAILS_STORED_PROC);
		    put(GET_STORAGE_TRIP_DETAILS,GET_STORAGE_TRIP_DETAILS_STORED_PROC);
		    put(UPDATE_STORAGE_TRIP_DETAILS,UPDATE_STORAGE_TRIP_DETAILS_STORED_PROC);
		    put(GET_CLOSE_STORAGE_TRIP_DETAILS,GET_CLOSE_STORAGE_TRIP_DETAILS_STORED_PROC);
		    put(UPDATE_CLOSE_STORAGE_TRIP_DETAILS,UPDATE_CLOSE_STORAGE_TRIP_DETAILS_STORED_PROC);
		    put(GET_STORAGE_TRIP_BY_DATE,GET_STORAGE_TRIP_BY_DATE_STORED_PROC);
		    
		    put(ADD_SELLER_TRIP_DETAILS,ADD_SELLER_TRIP_DETAILS_STORED_PROC);
		    put(GET_SELLER_TRIP_DETAILS,GET_SELLER_TRIP_DETAILS_STORED_PROC);
		    put(UPDATE_SELLER_TRIP_DETAILS,UPDATE_SELLER_TRIP_DETAILS_STORED_PROC);
		    put(GET_CLOSE_SELLER_TRIP_DETAILS,GET_CLOSE_SELLER_TRIP_DETAILS_STORED_PROC);
		    put(UPDATE_CLOSE_SELLER_TRIP_DETAILS,UPDATE_CLOSE_SELLER_TRIP_DETAILS_STORED_PROC);
		    put(GET_SELLER_TRIP_BY_DATE,GET_SELLER_TRIP_BY_DATE_STORED_PROC);
		    
		    put(ADD_SPARE_PART_INVENTORY, ADD_SPARE_PART_INVENTORY_STORED_PROC);
		    put(ADD_TYRE_INVENTORY, ADD_TYRE_INVENTORY_STORED_PROC);
		    
		    
		    put(GET_TRUCKS_SERVICE_BY_Truck,GET_TRUCKS_SERVICE_BY_Truck_STORED_PROC);
			put(GET_ALL_TYRES, GET_ALL_TYRES_STORED_PROC);
			put(GET_ALL_BRAND_NAMES, GET_ALL_BRAND_NAMES_STORED_PROC);
			put(GET_ALL_RUNNING_BRAND_NAMES, GET_ALL_RUNNING_BRAND_NAMES_STORED_PROC);
			put(GET_ALL_TYRES_BY_CATEGORY, GET_ALL_TYRES_BY_CATEGORY_STORED_PROC);
			put(GET_AVAILABLE_AND_ENGAGED_DRIVER_DETAILS,GET_AVAILABLE_AND_ENGAGED_DRIVER_DETAILS_STORED_PROC);
			put(ADD_ISSUED_TYRES,ADD_ISSUED_TYRES_STORED_PROC);
			put(ADD_RECOUP_TYRES,ADD_RECOUP_TYRES_STORED_PROC);
			put(GET_ALL_RUNNING_TYRES, GET_ALL_RUNNING_TYRES_STORED_PROC);
			put(GET_ALL_PENDING_BRAND_NAMES, GET_ALL_PENDING_BRAND_NAMES_STORED_PROC);
			put(GET_ALL_PENDING_TYRES, GET_ALL_PENDING_TYRES_STORED_PROC);
			put(UPDATE_RECOUP_TYRE_STATUS, UPDATE_RECOUP_TYRE_STATUS_STORED_PROC);
			

			put(GET_DRIVER_ALLOTMENT_TO_TRUCK_BY_DATE, GET_DRIVER_ALLOTMENT_TO_TRUCK_BY_DATE_STORED_PROC);
			put(ADD_DRIVER_ALLOTMENT_TO_TRUCK, ADD_DRIVER_ALLOTMENT_TO_TRUCK_STORED_PROC);
			put(GET_AVAILABLE_AND_ENGAGED_TRUCKS_DETAILS,GET_AVAILABLE_AND_ENGAGED_TRUCKS_DETAILS_STORED_PROC);
			put(GET_ALLOTED_TRUCKS, GET_ALLOTED_TRUCKS_STORED_PROC);
			put(GET_FACTORY_DISPATCHED_TRUCKS, GET_FACTORY_DISPATCHED_TRUCKS_STORED_PROC);
			put(GET_FACTORY_UNLOADING_TRUCKS, GET_FACTORY_UNLOADING_TRUCKS_STORED_PROC);
			
			put(ADD_TRUCKS_SERVICE, ADD_TRUCKS_SERVICE_STORED_PROC);
			put(CLOSE_TRUCKS_SERVICE,CLOSE_TRUCKS_SERVICE_STORED_PROC);
			put(GET_TRUCKS_SERVICE_BY_TRUCKNUMBER,GET_TRUCKS_SERVICE_BY_TRUCKNUMBER_STORED_PROC);
			put(GET_STORAGE_DISPATCHED_TRUCKS, GET_STORAGE_DISPATCHED_TRUCKS_STORED_PROC);
			put(GET_STORAGE_UNLOADING_TRUCKS, GET_STORAGE_UNLOADING_TRUCKS_STORED_PROC);
			put(GET_SELLER_DISPATCHED_TRUCKS, GET_SELLER_DISPATCHED_TRUCKS_STORED_PROC);
			put(GET_SELLER_UNLOADING_TRUCKS, GET_SELLER_UNLOADING_TRUCKS_STORED_PROC);
			put(GET_RUNNING_TYRES, GET_RUNNING_TYRES_STORED_PROC);
			put(GET_ISSUED_TYRES, GET_ISSUED_TYRES_STORED_PROC);
			put(GET_WASTE_TYRES, GET_WASTE_TYRES_STORED_PROC);
			put(GET_RECOUP_TYRES, GET_RECOUP_TYRES_STORED_PROC);
			put(CHANGE_PASSWORD,CHANGE_PASSWORD_STORED_PROC);
			put(SET_LINK_FOR_NEW_PASSWORD,SET_LINK_FOR_NEW_PASSWORD_STORED_PROC);
			put(SENT_EMAIL_FOR_FORGOT_PASSWORD,SENT_EMAIL_FOR_FORGOT_PASSWORD_STORED_PROC);
			put(GET_DRIVERS, GET_DRIVERS_STORED_PROC);
			put(GET_TYRES_CURRENT_INVENTORY, GET_TYRES_CURRENT_INVENTORY_STORED_PROC);
			put(GET_TYRES_INVENTORY_HISTORY, GET_TYRES_INVENTORY_HISTORY_STORED_PROC);
			put(GET_SPARE_PART_CURRENT_INVENTORY, GET_SPARE_PART_CURRENT_INVENTORY_STORED_PROC);
			put(GET_SPARE_PART_INVENTORY_HISTORY, GET_SPARE_PART_INVENTORY_HISTORY_STORED_PROC);
			put(GET_OIL_BRAND_NAMES, GET_OIL_BRAND_NAMES_STORED_PROC);
			put(GET_OIL_DISTINCT_BRAND_NAMES, GET_OIL_DISTINCT_BRAND_NAMES_STORED_PROC);
			put(ADD_OIL_INVENTORY, ADD_OIL_INVENTORY_STORED_PROC);
			put(ADD_ISSUED_OIL, ADD_ISSUED_OIL_STORED_PROC);
			put(GET_OIL_CURRENT_INVENTORY, GET_OIL_CURRENT_INVENTORY_STORED_PROC);
			put(GET_OIL_INVENTORY_HISTORY, GET_OIL_INVENTORY_HISTORY_STORED_PROC);
			put(GET_ISSUED_OIL, GET_ISSUED_OIL_STORED_PROC);
			put(ADD_OIL_BRAND_NAME, ADD_OIL_BRAND_NAME_STORED_PROC);
			put(GET_BATTERY_BRAND_NAMES, GET_BATTERY_BRAND_NAMES_STORED_PROC);
			put(ADD_BATTERY_BRAND_NAME, ADD_BATTERY_BRAND_NAME_STORED_PROC);
			put(ADD_ISSUED_BATTERY,ADD_ISSUED_BATTERY_STORED_PROC);
			put(ADD_BATTERY_INVENTORY,ADD_BATTERY_INVENTORY_STORED_PROC);
			put(GET_INVENTORY_BATTERY_BRANDS, GET_INVENTORY_BATTERY_BRANDS_STORED_PROC);
			put(GET_RUNNING_BATTERY_BRANDS, GET_RUNNING_BATTERY_BRANDS_STORED_PROC);
			put(GET_INVENTORY_BATTERY_NUMBERS, GET_INVENTORY_BATTERY_NUMBERS_STORED_PROC);
			put(GET_BATTERY_CURRENT_INVENTORY, GET_BATTERY_CURRENT_INVENTORY_STORED_PROC);	
			put(GET_RUNNING_BATTERY, GET_RUNNING_BATTERY_STORED_PROC);
			put(GET_ISSUED_BATTERY, GET_ISSUED_BATTERY_STORED_PROC);
			put(GET_RUNNING_BATTERY_NUMBERS, GET_RUNNING_BATTERY_NUMBERS_STORED_PROC);
			put(GET_BATTERY_INVENTORY_HISTORY, GET_BATTERY_INVENTORY_HISTORY_STORED_PROC);
			put(GET_WASTE_BATTERY, GET_WASTE_BATTERY_STORED_PROC);
			put(GET_TRUCKS_DRIVER_CHANGE, GET_TRUCKS_DRIVER_CHANGE_STORED_PROC);
			put(ADD_FACTORY_DRIVER_CHANGE, ADD_FACTORY_DRIVER_CHANGE_STORED_PROC);
			put(GET_ENGAGED_TRUCKS, GET_ENGAGED_TRUCKS_STORED_PROC);
			put(GET_FACTORY_CHANGE, GET_FACTORY_CHANGE_STORED_PROC);
			put(ADD_STORAGE_DRIVER_CHANGE, ADD_STORAGE_DRIVER_CHANGE_STORED_PROC);
			put(GET_STORAGE_CHANGE, GET_STORAGE_CHANGE_STORED_PROC);
			put(ADD_SELLER_DRIVER_CHANGE, ADD_SELLER_DRIVER_CHANGE_STORED_PROC);
			put(GET_SELLER_CHANGE, GET_SELLER_CHANGE_STORED_PROC);
			put(GET_FACTORY_ENGAGED_TRUCKS, GET_FACTORY_ENGAGED_TRUCKS_STORED_PROC);
			put(GET_STORAGE_ENGAGED_TRUCKS, GET_STORAGE_ENGAGED_TRUCKS_STORED_PROC);
			put(GET_SELLER_ENGAGED_TRUCKS, GET_SELLER_ENGAGED_TRUCKS_STORED_PROC);
			put(GET_ASSIGNED_TRUCKS, GET_ASSIGNED_TRUCKS_STORED_PROC);
			put(GET_DRIVER_ALLOTMENT_TO_TRUCK,GET_DRIVER_ALLOTMENT_TO_TRUCK_STORED_PROC);
			put(UPDATE_DRIVER_ALLOTMENT_TO_TRUCK, UPDATE_DRIVER_ALLOTMENT_TO_TRUCK_STORED_PROC);
			put(ADD_DRIVERS_IMAGE_URL, ADD_DRIVERS_IMAGE_URL_STORED_PROC);
			put(UPDATE_DRIVERS_IMAGE_URL, UPDATE_DRIVERS_IMAGE_URL_STORED_PROC);
			put(ADD_TRUCKS_IMAGE_URL, ADD_TRUCKS_IMAGE_URL_STORED_PROC);
			put(UPDATE_TRUCKS_IMAGE_URL, UPDATE_TRUCKS_IMAGE_URL_STORED_PROC);
			put(GET_FACTORY_DRIVER_EXPENDITURE, GET_FACTORY_DRIVER_EXPENDITURE_STORED_PROC);
			put(GET_FACTORY_DRIVERS_CLOSE_EXPENDITURE, GET_FACTORY_DRIVERS_CLOSE_EXPENDITURE_STORED_PROC);
			put(ADD_FACTORY_DRIVER_EXPENDITURE, ADD_FACTORY_DRIVER_EXPENDITURE_STORED_PROC);
			put(GET_DRIVER_BALANCE, GET_DRIVER_BALANCE_STORED_PROC);
			put(GET_SELLER_DRIVER_EXPENDITURE, GET_SELLER_DRIVER_EXPENDITURE_STORED_PROC);
			put(GET_SELLER_DRIVERS_CLOSE_EXPENDITURE, GET_SELLER_DRIVERS_CLOSE_EXPENDITURE_STORED_PROC);
			put(ADD_SELLER_DRIVER_EXPENDITURE, ADD_SELLER_DRIVER_EXPENDITURE_STORED_PROC);
			put(GET_STORAGE_DRIVER_EXPENDITURE, GET_STORAGE_DRIVER_EXPENDITURE_STORED_PROC);
			put(GET_STORAGE_DRIVERS_CLOSE_EXPENDITURE, GET_STORAGE_DRIVERS_CLOSE_EXPENDITURE_STORED_PROC);
			put(ADD_STORAGE_DRIVER_EXPENDITURE, ADD_STORAGE_DRIVER_EXPENDITURE_STORED_PROC);
			put(GET_TRIP_STARTED_TRUCKS, GET_TRIP_STARTED_TRUCKS_STORED_PROC);
			put(GET_TRIP_DRIVER_EXPENDITURE, GET_TRIP_DRIVER_EXPENDITURE_STORED_PROC);
			put(GET_TRIP_DRIVERS_CLOSE_EXPENDITURE, GET_TRIP_DRIVERS_CLOSE_EXPENDITURE_STORED_PROC);
			put(ADD_TRIP_DRIVER_EXPENDITURE, ADD_TRIP_DRIVER_EXPENDITURE_STORED_PROC);
			put(GET_TRUCK_STATUS, GET_TRUCK_STATUS_STORED_PROC);
			put(ADD_FACTORY_LOADING, ADD_FACTORY_LOADING_STORED_PROC);
			put(ADD_TRUCK_IDLE_STATUS, ADD_TRUCK_IDLE_STATUS_STORED_PROC);
			put(GET_LOGIN_USER_DETAILS, GET_LOGIN_USER_DETAILS_STORED_PROC);
			put(GET_ASSIGNED_AVAILABLE_TRUCKS, GET_ASSIGNED_AVAILABLE_TRUCKS_STORED_PROC);
			put(GET_TRUCKS_STATUS_TRACKING, GET_TRUCKS_STATUS_TRACKING_STORED_PROC);
			put(GET_TRUCKS_STATUS_TRACKING_LIST, GET_TRUCKS_STATUS_TRACKING_LIST_STORED_PROC);
			put(GET_TRUCKS_GRID_CALENDAR, GET_TRUCKS_GRID_CALENDAR_STORED_PROC);
			put(GET_EXPIRY_TRUCKS_PERMIT, GET_EXPIRY_TRUCKS_PERMIT_STORED_PROC);
			put(GET_EXPIRY_TRUCKS_INSURANCE, GET_EXPIRY_TRUCKS_INSURANCE_STORED_PROC);
			put(GET_EXPIRY_TRUCKS_FITNESS, GET_EXPIRY_TRUCKS_FITNESS_STORED_PROC);
			put(ADD_DISPATCH_STARTING_METER_READING_IMAGE_UPLOAD_URL, ADD_DISPATCH_STARTING_METER_READING_IMAGE_UPLOAD_URL_STORED_PROC);
			put(ADD_DISPATCH_CLOSING_METER_READING_IMAGE_UPLOAD_URL, ADD_DISPATCH_CLOSING_METER_READING_IMAGE_UPLOAD_URL_STORED_PROC);
			put(GET_TRUCK_CURRENT_DAY_STATUS_DETAILS, GET_TRUCK_CURRENT_DAY_STATUS_DETAILS_STORED_PROC);
			put(ADD_TRIP_STARTING_METER_READING_IMAGE_UPLOAD_URL, ADD_TRIP_STARTING_METER_READING_IMAGE_UPLOAD_URL_STORED_PROC);
			put(ADD_TRIP_CLOSING_METER_READING_IMAGE_UPLOAD_URL, ADD_TRIP_CLOSING_METER_READING_IMAGE_UPLOAD_URL_STORED_PROC);
			put(ADD_TRUCK_MAINTENANCE_ADVANCE_REQUEST, ADD_TRUCK_MAINTENANCE_ADVANCE_REQUEST_STORED_PROC);
			put(GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_NUMBER, GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_NUMBER_STORED_PROC);
			put(GET_TRUCK_MAINTENANCE_ADVANCE_ITEM_DETAILS, GET_TRUCK_MAINTENANCE_ADVANCE_ITEM_DETAILS_STORED_PROC);
			put(GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_COUNT, GET_TRUCK_MAINTENANCE_ADVANCE_REQUEST_COUNT_STORED_PROC);
			put(UPDATE_TRUCK_MAINTENANCE_ADVANCE_APPROVAL_STATUS, UPDATE_TRUCK_MAINTENANCE_ADVANCE_APPROVAL_STATUS_STORED_PROC);
			put(GET_AVAILABLE_DRIVERS, GET_AVAILABLE_DRIVERS_STORED_PROC);
			put(GET_FUEL_STATION_NAME, GET_FUEL_STATION_NAME_STORED_PROC);
			put(UPDATE_DRIVER_SALARY_PAYMENT, UPDATE_DRIVER_SALARY_PAYMENT_STORED_PROC);
			put(ADD_DRIVER_SALARY_PAYMENT_HISTORY, ADD_DRIVER_SALARY_PAYMENT_HISTORY_STORED_PROC);
			put(GET_FACTORIES_CUMULATIVE_REPORT, GET_FACTORIES_CUMULATIVE_REPORT_STORED_PROC);
			put(GET_DISPATCH_REPORT_FOR_BILL, GET_DISPATCH_REPORT_FOR_BILL_STORED_PROC);
			put(GET_FACTORIES_ASSOCIATION, GET_FACTORIES_ASSOCIATION_STORED_PROC);
			put(TOTAL_DISPATCH_REPORT, TOTAL_DISPATCH_REPORT_STORED_PROC);
			put(GET_UNLOAD_LOCATION_NAMES, GET_UNLOAD_LOCATION_NAMES_STORED_PROC);
			put(GET_OUTSIDE_COMPANY_NAMES, GET_OUTSIDE_COMPANY_NAMES_STORED_PROC);
			put(ADD_OUTSIDE_FACTORY_DISPATCH, ADD_OUTSIDE_FACTORY_DISPATCH_STORED_PROC);
			put(GET_FACTORY_DISPATCH_DAILY_REPORT, GET_FACTORY_DISPATCH_DAILY_REPORT_STORED_PROC);
			put(GET_FACTORY_DISPATCH_LOADING_REPORT, GET_FACTORY_DISPATCH_LOADING_REPORT_STORED_PROC);
			put(GET_QUANTITY_AND_FREIGHT, GET_QUANTITY_AND_FREIGHT_STORED_PROC);
			put(GET_FACTORY_DISPATCH_BILL, GET_FACTORY_DISPATCH_BILL_STORED_PROC);
			put(GET_FACTORY_DISPATCH_BILL_COUNT, GET_FACTORY_DISPATCH_BILL_COUNT_STORED_PROC);
			put(ADD_TRUCK_MAINTENANCE_EXPENDITURE, ADD_TRUCK_MAINTENANCE_EXPENDITURE_STORED_PROC);
			put(GET_REQUEST_OIL, GET_REQUEST_OIL_STORED_PROC);
			put(UPDATE_OIL_INVENTORY_COUNT, UPDATE_OIL_INVENTORY_COUNT_STORED_PROC);
			put(GET_TRUCK_OVERALL_MAINTENANCE_REPORT, GET_TRUCK_OVERALL_MAINTENANCE_REPORT_STORED_PROC);
			put(ADD_LAT_LONG, ADD_LAT_LONG_STORED_PROC);
			put(GET_LAT_LONG, GET_LAT_LONG_STORED_PROC);
			put(GET_DRIVER_FULL_DETAILS, GET_DRIVER_FULL_DETAILS_STORED_PROC);
			put(ADD_PETROL_PUMP_READING_IMAGE_UPLOAD_URL, ADD_PETROL_PUMP_READING_IMAGE_UPLOAD_URL_STORED_PROC);
			put(UPDATE_OUTSIDE_FACTORY_DISPATCH, UPDATE_OUTSIDE_FACTORY_DISPATCH_STORED_PROC);
			put(CHECK_SPARE_PARTS_COUNT, CHECK_SPARE_PARTS_COUNT_STORED_PROC);
			put(GET_TRUCKS_MAINTENANCE_COST, GET_TRUCKS_MAINTENANCE_COST_STORED_PROC);
			put(GET_TRUCKS_TRIP_COUNT, GET_TRUCKS_TRIP_COUNT_STORED_PROC);
			
			put(ADD_CASH_AND_BANK_ACCOUNT, ADD_CASH_AND_BANK_ACCOUNT_STORED_PROC);
			put(ADD_ACCOUNT_NAME, ADD_ACCOUNT_NAME_STORED_PROC);
			put(ADD_PAYMENT, ADD_PAYMENT_STORED_PROC);
			put(ADD_RECEIPT, ADD_RECEIPT_STORED_PROC);
			put(ADD_INCOME, ADD_INCOME_STORED_PROC);
			put(ADD_EXPENDITURE, ADD_EXPENDITURE_STORED_PROC);
			put(GET_LEDGER, GET_LEDGER_STORED_PROC);
			put(GET_CASH_AND_BANK_NAME, GET_CASH_AND_BANK_NAME_STORED_PROC);
			put(GET_ACCOUNT_NAME, GET_ACCOUNT_NAME_STORED_PROC);
			put(GET_ALL_ACCOUNTS, GET_ALL_ACCOUNTS_STORED_PROC);
			put(GET_TRIAL_BALANCE, GET_TRIAL_BALANCE_STORED_PROC);
			put(GET_PURCHASE_ACCOUNT_NAMES, GET_PURCHASE_ACCOUNT_NAMES_STORED_PROC);
			put(GET_SALES_AND_INCOME_ACCOUNT_NAMES, GET_SALES_AND_INCOME_ACCOUNT_NAMES_STORED_PROC);
			put(GET_VENDOR_AND_CUSTOMER_ACCOUNT_NAMES, GET_VENDOR_AND_CUSTOMER_ACCOUNT_NAMES_STORED_PROC);
			put(GET_VEHICLE_EXPENSES_ACCOUNT_NAMES, GET_VEHICLE_EXPENSES_ACCOUNT_NAMES_STORED_PROC);
			put(GET_ACCOUNT_GROUP_NAMES, GET_ACCOUNT_GROUP_NAMES_STORED_PROC);
			put(GET_BALANCE_SHEET, GET_BALANCE_SHEET_STORED_PROC);
			put(GET_PROFIT_AND_LOSS, GET_PROFIT_AND_LOSS_STORED_PROC);
			put(GET_ACCOUNT_TREE_NAMES, GET_ACCOUNT_TREE_NAMES_STORED_PROC);
			put(GET_OPENING_BALANCE, GET_OPENING_BALANCE_STORED_PROC);
			put(GET_TRUCK_MAINTENANCE_EXPENDITURE, GET_TRUCK_MAINTENANCE_EXPENDITURE_STORED_PROC);
			put(GET_UNLOAD_REPORT, GET_UNLOAD_REPORT_STORED_PROC);
			put(GET_UNLOAD_REPORT_BY_ID, GET_UNLOAD_REPORT_BY_ID_STORED_PROC);
			put(UPDATE_UNLOAD_REPORT, UPDATE_UNLOAD_REPORT_STORED_PROC);
			put(GET_PAYMENT, GET_PAYMENT_STORED_PROC);
			put(GET_RECEIPT, GET_RECEIPT_STORED_PROC);
			put(GET_EXPENDITURE, GET_EXPENDITURE_STORED_PROC);
			put(GET_INCOME, GET_INCOME_STORED_PROC);
			put(UPDATE_PAYMENT, UPDATE_PAYMENT_STORED_PROC);
			put(UPDATE_RECEIPT, UPDATE_RECEIPT_STORED_PROC);
			put(UPDATE_EXPENDITURE, UPDATE_EXPENDITURE_STORED_PROC);
			put(UPDATE_INCOME, UPDATE_INCOME_STORED_PROC);
			put(DELETE_PAYMENT, DELETE_PAYMENT_STORED_PROC);
			put(DELETE_RECEIPT, DELETE_RECEIPT_STORED_PROC);
			put(DELETE_EXPENDITURE, DELETE_EXPENDITURE_STORED_PROC);
			put(DELETE_INCOME, DELETE_INCOME_STORED_PROC);
			put(GET_ALL_AND_OTHER_TRUCKS, GET_ALL_AND_OTHER_TRUCKS_STORED_PROC);
			put(ADD_OPENING_BALANCE, ADD_OPENING_BALANCE_STORED_PROC);
			put(GET_OPENING_BALANCE_FOR_TB, GET_OPENING_BALANCE_FOR_TB_STORED_PROC);
			put(ADD_JOURNAL_ENTRY, ADD_JOURNAL_ENTRY_STORED_PROC);
			put(GET_OPENING_BALANCES, GET_OPENING_BALANCES_STORED_PROC);
			put(UPDATE_OPENING_BALANCES, UPDATE_OPENING_BALANCES_STORED_PROC);
			put(DELETE_OPENING_BALANCE, DELETE_OPENING_BALANCE_STORED_PROC);
			put(GET_TRUCK_PROFIT_AND_LOSS, GET_TRUCK_PROFIT_AND_LOSS_STORED_PROC);
			put(GET_FACTORY_FUEL_BY_FUEL_STATION,GET_FACTORY_FUEL_BY_FUEL_STATION_STORED_PROC);
			put(GET_USER_LOGIN_DETAILS,GET_USER_LOGIN_DETAILS_STORED_PROC);
			
			put(ADD_INWARD, ADD_INWARD_STORED_PROC);
			put(GET_ACTIONS,GET_ACTIONS_STORED_PROC);
			put(GET_INWARD,GET_INWARD_STORED_PROC);
			put(UPDATE_INWARD,UPDATE_INWARD_STORED_PROC);
			put(DELETE_INWARD,DELETE_INWARD_STORED_PROC);
			put(GET_UNLOAD,GET_UNLOAD_STORED_PROC);
			put(GET_BILL,GET_BILL_STORED_PROC);
			put(GET_TYPECEMENT ,GET_TYPECEMENT_STORED_PROC);
			put(ADD_OUTWARD, ADD_OUTWARD_STORED_PROC);
			put(GET_OUTWARD,GET_OUTWARD_STORED_PROC);
			put(UPDATE_OUTWARD,UPDATE_OUTWARD_STORED_PROC);
			put(DELETE_OUTWARD,DELETE_OUTWARD_STORED_PROC);
			put(GET_HAMALI,GET_HAMALI_STORED_PROC);
			put(ADD_BILL,ADD_BILL_STORED_PROC);
			put(GET_HAMALI_ACCOUNT,GET_HAMALI_ACCOUNT_STORED_PROC);
			put(GET_HAMALI_TYPE,GET_HAMALI_TYPE_STORED_PROC);
			put(GET_BILL_DETAILS,GET_BILL_DETAILS_STORED_PROC);
			put(ADD_INWARD_TRUCK_OR_WORK_IMAGE_URLS, ADD_INWARD_TRUCK_OR_WORK_IMAGE_URLS_STORED_PROC);
			put(ADD_OUTWARD_TRUCK_OR_WORK_IMAGE_URLS, ADD_OUTWARD_TRUCK_OR_WORK_IMAGE_URLS_STORED_PROC);
			put(UPDATE_BILL,UPDATE_BILL_STORED_PROC);
			put(DELETE_BILL,DELETE_BILL_STORED_PROC);
			put(GET_ASSOCIATION_GODOWN,GET_ASSOCIATION_GODOWN_STORED_PROC);
			put(GET_ASSOCIATION, GET_ASSOCIATION_STORED_PROC);
			put(ADD_OUTWARD_HAMALI, ADD_OUTWARD_HAMALI_STORED_PROC);
			put(GET_OUTWARD_HAMALI, GET_OUTWARD_HAMALI_STORED_PROC);
			put(ADD_HAMALI_DETAILS,ADD_HAMALI_DETAILS_STORED_PROC);
			put(GET_HAMALI_DETAILS,GET_HAMALI_DETAILS_STORED_PROC);
			put(UPDATE_HAMALI_DETAILS,UPDATE_HAMALI_DETAILS_STORED_PROC);
			put(DELETE_HAMALI_DETAILS,DELETE_HAMALI_DETAILS_STORED_PROC);

			put(ADD_ADVANCE_BOOKING, ADD_ADVANCE_BOOKING_STORED_PROC);
			put(GET_ADVANCE_BOOKING, GET_ADVANCE_BOOKING_STORED_PROC);
			put(UPDATE_ADVANCE_BOOKING, UPDATE_ADVANCE_BOOKING_STORED_PROC);
			put(DELETE_ADVANCE_BOOKING, DELETE_ADVANCE_BOOKING_STORED_PROC);
			
			put(ADD_CUSTOMER, ADD_CUSTOMER_STORED_PROC);
			put(GET_CUSTOMER,GET_CUSTOMER_STORED_PROC);
			put(GET_OUTWARD_CUSTOMER, GET_OUTWARD_CUSTOMER_STORED_PROC);
			put(UPDATE_OUTWARD_CUSTOMER,UPDATE_OUTWARD_CUSTOMER_STORED_PROC);
			put(DELETE_OUTWARD_CUSTOMER,DELETE_OUTWARD_CUSTOMER_STORED_PROC);
			put(GET_DC_PENDING,GET_DC_PENDING_STORED_PROC);
			put(UPDATE_DC_PENDING,UPDATE_DC_PENDING_STORED_PROC);
			put(DELETE_DC_PENDING,DELETE_DC_PENDING_STORED_PROC);
			
			put(ADD_ACCOUNT_GROUP, ADD_ACCOUNT_GROUP_STORED_PROC);
			put(GET_ACCOUNT_GROUPS, GET_ACCOUNT_GROUPS_STORED_PROC);
			put(UPDATE_ACCOUNT_GROUP, UPDATE_ACCOUNT_GROUP_STORED_PROC);
			put(DELETE_ACCOUNT_GROUP, DELETE_ACCOUNT_GROUP_STORED_PROC);
			put(GET_TRUCK_MAINTENANCE_EXPENDITURE_REPORT, GET_TRUCK_MAINTENANCE_EXPENDITURE_REPORT_STORED_PROC);
			put(GET_FREIGHT_AND_QUANTITY, GET_FREIGHT_AND_QUANTITY_STORED_PROC);
			put(GET_FUEL_COST_REPORT, GET_FUEL_COST_REPORT_STORED_PROC);
			put(GET_FUEL_STATION_COST_REPORT, GET_FUEL_STATION_COST_REPORT_STORED_PROC);
			put(DELETE_FACTORY_DISPATCH, DELETE_FACTORY_DISPATCH_STORED_PROC);
			put(GET_INWARD_ACTIONS,GET_INWARD_ACTIONS_STORED_PROC);
			put(GET_OUTWARD_ACTIONS,GET_OUTWARD_ACTIONS_STORED_PROC);
			put(GET_TODAY_PAYMENT_RECEIPT_AMOUNT,GET_TODAY_PAYMENT_RECEIPT_AMOUNT_STORED_PROC);
			put(GET_FREIGHT_AND_QUANTITY_MONTH,GET_FREIGHT_AND_QUANTITY_MONTH_STORED_PROC);
			put(UPDATE_DISPATCH_BILL_IDS,UPDATE_DISPATCH_BILL_IDS_STORED_PROC);
			put(GET_GENERATED_BILLS,GET_GENERATED_BILLS_STORED_PROC);
			put(GET_GENERATED_BILLS_GROUP,GET_GENERATED_BILLS_GROUP_STORED_PROC);
			put(ADD_DRIVER_ADVANCE,ADD_DRIVER_ADVANCE_STORED_PROC);
			put(GET_ALL_DRIVER_BALANCE,GET_ALL_DRIVER_BALANCE_STORED_PROC);
			put(GET_DRIVER_EXPENDITURE_FOR_APPROVAL,GET_DRIVER_EXPENDITURE_FOR_APPROVAL_STORED_PROC);
			put(UPDATE_DRIVER_EXPENDITURE_APPROVAL,UPDATE_DRIVER_EXPENDITURE_APPROVAL_STORED_PROC);
			put(GET_DISPATCH_FUEL_LINKING,GET_DISPATCH_FUEL_LINKING_STORED_PROC);
			put(ADD_ERROR_FUEL,ADD_ERROR_FUEL_STORED_PROC);
			put(GET_STOCK,GET_STOCK_STORED_PROC);
			put(GET_STOCK_TYPE,GET_STOCK_TYPE_STORED_PROC);
			put(GET_ACTIONS_BILL_TYPE,GET_ACTIONS_BILL_TYPE_STORED_PROC);
			put(ADD_EXCEL_BILL, ADD_EXCEL_BILL_STORED_PROC);
			put(GET_BILL_EXCEL,GET_BILL_EXCEL_STORED_PROC);
			put(GET_FACTORY_DISPATCH_FOR_INVOICE_PHOTO,GET_FACTORY_DISPATCH_FOR_INVOICE_PHOTO_STORED_PROC);
			put(ADD_INVOICE_PHOTO,ADD_INVOICE_PHOTO_STORED_PROC);
		} 
	};

}
