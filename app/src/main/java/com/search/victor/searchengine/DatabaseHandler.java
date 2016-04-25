package com.search.victor.searchengine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Josh, please thoroughly comment this
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public enum TABLES {ORGANIZATION, POPULATION, SERVESP, PHONE,
                        CATEGORY, FALLS_IN, LOCATION, LANGUAGE, SERVESL};
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "meettheneed";

    private static final String DATABASE_IP = "webwork.math.pacificu.edu/phpmyadmin";
    private static final String DATABASE_USER = "riel6019";
    private static final String DATABASE_PASSWORD = "711317Heaven";

    private static final String TABLE_ORGANIZATION = "Organization";
    private static final String TABLE_POPULATION = "Population";
    private static final String TABLE_SERVES_PEOPLE = "ServesP";
    private static final String TABLE_PHONE = "Phone";
    private static final String TABLE_CATEGORY = "Category";
    private static final String TABLE_FALLS_IN = "Falls_in";
    private static final String TABLE_LOCATION = "Location";
    private static final String TABLE_LANGUAGE = "Language";
    private static final String TABLE_SERVESL = "ServesL";

    private static final String FIELD_ORG_OID = "oid";
    private static final String FIELD_ORG_NAME = "Organization_Name";
    private static final String FIELD_ORG_COST = "Cost";
    private static final String FIELD_ORG_OTHER = "Other_Notables";
    private static final String FIELD_ORG_EMAIL = "Email_Address";
    private static final String FIELD_ORG_WEBSITE = "Website_Address";
    private static final String FIELD_ORG_ASSISTANCE = "Assistance";
    private static final String FIELD_ORG_ADDITIONAL = "Additional_Services";
    private static final String FIELD_POP_TYPE = "Population";
    private static final String RELATION_SERVESP_OID = "oid";
    private static final String RELATION_SERVESP_TYPE = "Population";
    private static final String FIELD_PHONE_PHONENUM = "Phone";
    private static final String FOREIGN_KEY_OID = "oid";
    private static final String FIELD_CAT_cName = "Category";
    private static final String RELATION_FALLS_IN_OID = "oid";
    private static final String RELATION_FALLS_IN_CNAME = "Category";
    private static final String FIELD_LOC_LKEY = "lKey";
    private static final String FIELD_LOC_OID = "oid";
    private static final String FIELD_LOC_CITY = "City";
    private static final String FIELD_LOC_STATE = "State";
    private static final String FIELD_LOC_STREET = "Street_Address";
    private static final String FIELD_LOC_ZIP = "Zip_Code";
    private static final String FIELD_LAN_LTYPE = "Language";
    private static final String RELATION_SERVES_LAN_LTYPE = "Language";
    private static final String RELATION_SERVES_LAN_OID = "oid";
    private static final String DPND_ORG_ID = "FOREIGN KEY(" + RELATION_SERVESP_OID + ") REFERENCES " + TABLE_ORGANIZATION + "(" + FIELD_ORG_OID + ") ON DELETE CASCADE";
    private static final String DPND_POP_ID = "FOREIGN KEY(" + RELATION_SERVESP_TYPE + ") REFERENCES " + TABLE_POPULATION + "(" + FIELD_POP_TYPE + ") ON DELETE CASCADE";
    private static final String DPND_PHONE_ORG = "FOREIGN KEY(" + FOREIGN_KEY_OID + ") REFERENCES " + TABLE_ORGANIZATION + "(" + FIELD_ORG_OID + ") ON DELETE CASCADE";
    private static final String DPND_FALLS_IN_ORG = "FOREIGN KEY(" + RELATION_FALLS_IN_OID + ") REFERENCES " + TABLE_ORGANIZATION + "(" + FIELD_ORG_OID + ") ON DELETE CASCADE";
    private static final String DPND_FALLS_IN_CAT = "FOREIGN KEY(" + RELATION_FALLS_IN_CNAME + ") REFERENCES " + TABLE_CATEGORY + "(" + FIELD_CAT_cName + ") ON DELETE CASCADE";
    private static final String DPND_LOC_ORG = "FOREIGN KEY(" + FIELD_LOC_OID + ") REFERENCES " + TABLE_ORGANIZATION + "(" + FIELD_ORG_OID + ") ON DELETE CASCADE";
    private static final String DPND_SERVL_LAN = "FOREIGN KEY(" + RELATION_SERVES_LAN_LTYPE + ") REFERENCES " + TABLE_LANGUAGE + "(" + FIELD_LAN_LTYPE + ") ON DELETE CASCADE";
    private static final String DPND_SERVL_ORG = "FOREIGN KEY(" + RELATION_SERVES_LAN_OID + ") REFERENCES " + TABLE_ORGANIZATION + "(" + FIELD_ORG_OID + ") ON DELETE  CASCADE";

    public DatabaseHandler (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase dbContext) {
        String CREATE_ORGANIZATION_TABLE = "CREATE TABLE " + TABLE_ORGANIZATION + "("
                + FIELD_ORG_OID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FIELD_ORG_NAME + " TEXT,"
                + FIELD_ORG_COST + " TEXT,"
                + FIELD_ORG_OTHER + " TEXT,"
                + FIELD_ORG_EMAIL + " TEXT,"
                + FIELD_ORG_WEBSITE + " TEXT,"
                + FIELD_ORG_ASSISTANCE + " TEXT,"
                + FIELD_ORG_ADDITIONAL + " TEXT" + ")";

        String CREATE_POPULATION_TABLE = "CREATE TABLE " + TABLE_POPULATION + "("
                + FIELD_POP_TYPE + " TEXT PRIMARY KEY NOT NULL" + ")";

        String CREATE_SERVES_PEOPLE_TABLE = "CREATE TABLE " + TABLE_SERVES_PEOPLE + "("
                + RELATION_SERVESP_OID + " INTEGER,"
                + RELATION_SERVESP_TYPE + " TEXT,"
                + "PRIMARY KEY (" + RELATION_SERVESP_OID + "," + RELATION_SERVESP_TYPE + "),"
                + DPND_ORG_ID + "," + DPND_POP_ID + ")";

        String CREATE_PHONE_TABLE = "CREATE TABLE " + TABLE_PHONE + "("
                + FIELD_PHONE_PHONENUM + " INTEGER PRIMARY KEY,"
                + FIELD_ORG_OID + " INTEGER,"
                + DPND_PHONE_ORG + ")";

        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
                + FIELD_CAT_cName + " TEXT PRIMARY KEY" + ")";

        String CREATE_FALLS_IN_TABLE = "CREATE TABLE " + TABLE_FALLS_IN + "("
                + RELATION_FALLS_IN_OID + " INTEGER,"
                + RELATION_FALLS_IN_CNAME + " TEXT,"
                + "PRIMARY KEY (" + RELATION_FALLS_IN_OID + "," + RELATION_FALLS_IN_CNAME + "),"
                + DPND_FALLS_IN_ORG + "," + DPND_FALLS_IN_CAT + ")";

        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_LOCATION + "("
                + FIELD_LOC_LKEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FIELD_LOC_OID + " INTEGER,"
                + FIELD_LOC_CITY + " TEXT,"
                + FIELD_LOC_STATE + " TEXT,"
                + FIELD_LOC_STREET + " TEXT,"
                + FIELD_LOC_ZIP + " INTEGER,"
                + DPND_LOC_ORG + ")";

        String CREATE_LANGUAGE_TABLE = "CREATE TABLE " + TABLE_LANGUAGE + "("
                + FIELD_LAN_LTYPE + " TEXT PRIMARY KEY)";

        String CREATE_SERVES_LANGUAGE_TABLE = "CREATE TABLE " + TABLE_SERVESL + "("
                + RELATION_SERVES_LAN_LTYPE + " TEXT,"
                + RELATION_SERVES_LAN_OID + " INTEGER,"
                + "PRIMARY KEY (" + RELATION_SERVES_LAN_LTYPE + "," + RELATION_SERVES_LAN_OID + "),"
                + DPND_SERVL_LAN + "," + DPND_SERVL_ORG + ")";

        Log.e("Organzation Table", CREATE_ORGANIZATION_TABLE);
        Log.e("Population Table", CREATE_POPULATION_TABLE);
        Log.e("Serves People Table", CREATE_SERVES_PEOPLE_TABLE);
        Log.e("Phone", CREATE_PHONE_TABLE);
        Log.e("Category", CREATE_CATEGORY_TABLE);
        Log.e("Falls In", CREATE_FALLS_IN_TABLE);
        Log.e("Location", CREATE_LOCATION_TABLE);
        Log.e("Language", CREATE_LANGUAGE_TABLE);
        Log.e("Serves Language", CREATE_SERVES_LANGUAGE_TABLE);
        dbContext.execSQL("PRAGMA foreign_keys = ON;");
        dbContext.execSQL (CREATE_ORGANIZATION_TABLE);
        dbContext.execSQL (CREATE_POPULATION_TABLE);
        dbContext.execSQL (CREATE_SERVES_PEOPLE_TABLE);
        dbContext.execSQL (CREATE_PHONE_TABLE);
        dbContext.execSQL (CREATE_CATEGORY_TABLE);
        dbContext.execSQL (CREATE_FALLS_IN_TABLE);
        dbContext.execSQL (CREATE_LOCATION_TABLE);
        dbContext.execSQL (CREATE_LANGUAGE_TABLE);
        dbContext.execSQL (CREATE_SERVES_LANGUAGE_TABLE);
        /*
        The following code manually populates the database It is long because there is a lot of data
        to put in the database. {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}}
         */
        String[][][] Organizations = {{{"A&D/Youth Helpline"}, {""}, {""}, {"Free"}, {""}, {""}, {""}, {"Help Line"}, {""}, {""}, {""}, {""}, {"5032441611"}, {"Youth"}, {""}},
                {{"Abuse Recovery Ministry and Services"}, {"info@armsonline.org"}, {"http://www.armsonline.org"}, {"Some free services (preventative and recovery programs)"}, {"Group Classes"}, {""}, {"Fees and homework required for abusive/control oriented classrooms. Fees are waived for those attending preventative/recovery classes from encountering or recognizing abuse and controlling behaviors. "}, {"Parent Counseling", "Abuse Education"}, {"", ""}, {"97124", "97124"}, {"Confidential", "Hillsboro"}, {"OR", "OR"}, {"5038469284"}, {"Men who have been mandated by the courts", "Women who have been in past abusive/controlling relationships or who exhibit controlling behaviors", "Adolescents", "Adults."}, {"English"}},
                {{"Adelante Mujeres"}, {"info@adelantemujeres.org"}, {"http://www.adelantemujeres.org"}, {""}, {""}, {""}, {""}, {"Education"}, {"2036 Main St. Ste A"}, {"97116"}, {"Forest Grove"}, {"OR"}, {"5039920078"}, {"Latina women"}, {"English", "Spanish"}},
                {{"Affordable Dentures"}, {""}, {"http://affordabledentures.com/office/wilsonville"}, {"Accepts OHP, Reduced Cost"}, {"Dentures, Repairs, Tooth Extractions, Sedation Dentistry, X-Ray services, Gold Crowns"}, {""}, {"Medicaid accepted, Care Credit available. Open 8AM-5PM Monday-Friday."}, {"Denture Services"}, {"8229 SW Wilsonville Rd. Suite 6AB"}, {"97070"}, {"Wilsonville"}, {"OR"}, {"5038318817"}, {"Adults"}, {"English"}},
                {{"Albertina Kerr Centers"}, {"info@albertinakerr.org"}, {"http://www.albertinakerr.org/ChildrensDevelopmentalHealth"}, {""}, {"Speech language pathology, services for autism, occupational therapy, case management, education"}, {""}, {""}, {"Youth Residential", "Outpatient Services", "Mental Health Counseling", "Adult Group Homes", "Youth Group Homes", "Supported Living"}, {"247 SE Washington St."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5032398101"}, {"Children with developmental disabilities and mental health challenges", "Families", "Adults"}, {"English"}},
                {{"Alcohol and Drug Helpline"}, {""}, {""}, {"Free"}, {""}, {""}, {""}, {"Help Line"}, {""}, {""}, {""}, {""}, {"5032441312"}, {""}, {""}},
                {{"Allies in Change"}, {""}, {""}, {""}, {"Anger Management, Individual/group counseling, support groups"}, {""}, {""}, {"Domestic Violence", "Counseling", "Support Groups"}, {""}, {""}, {""}, {""}, {"5032977979"}, {""}, {""}},
                {{"Anxiety Support Line"}, {""}, {""}, {"Free"}, {""}, {""}, {""}, {"Help Line"}, {""}, {""}, {""}, {""}, {"5036472642"}, {""}, {""}},
                {{"Asian Health and Service Center"}, {"info@ahscpdx.org"}, {"ahscpdx.org"}, {"Varies by service"}, {"Many social and support services, inluding English Language classes, senior services, employment"}, {""}, {""}, {"Community Resource Center"}, {"12500 SW Allen Blvd."}, {"97008"}, {"Beaverton"}, {"OR"}, {"5036414113"}, {"Asian Immigrants"}, {"English", "Cantonese", "Mandarin", "Korean", "Vietnamese"}},
                {{"Beaverton Community Basket"}, {""}, {""}, {"Free"}, {""}, {""}, {"Open 2nd Fri of the month at Beaverton Resource Center.  Bring your own Bag."}, {"Food Pantry"}, {"12500 SW Allen Blvd."}, {"97008"}, {"Beaverton"}, {"OR"}, {"5034396510"}, {""}, {""}},
                {{"Beaverton Family Resource Center"}, {""}, {""}, {"Free"}, {""}, {""}, {"Open 3rd Thursday of each month at 10am"}, {"Food Pantry"}, {"16550 SW Merlo Rd. num 1"}, {"97006"}, {"Beaverton"}, {"OR"}, {"5036490367"}, {""}, {""}},
                {{"Beaverton Hispanic Center"}, {"officeadministrator@beavertonhc.org"}, {"beavertonhc.org"}, {"No Fees for most services"}, {"Helps families locate social services, translation services, English and Spanish classes, GED, Domestic violence counseling services,  and Gand, Drug, and crime workshops"}, {"No funds for assistance at this time"}, {"Open M-F 9am-12pm"}, {"Community Resource Center"}, {"3800 SW Cedar Hills Blvd., Ste. 123"}, {"97005"}, {"Beaverton"}, {"OR"}, {"9712492421"}, {"Hispanic families"}, {"English", "Spanish"}},
                {{"Beaverton Literacy Council"}, {"director@beavertonliteracy.org"}, {"beavertonliteracy.org"}, {"Purchase class materials"}, {"English as a second language courses, Citizenship education for adults"}, {""}, {""}, {"Literacy services"}, {"PO Box 952"}, {"97075"}, {"Beaverton"}, {"OR"}, {"5035208764"}, {""}, {""}},
                {{"Beaverton Seventh Day Adventist"}, {""}, {""}, {"Free"}, {""}, {""}, {""}, {"Open Tuesdays 9am-12pm and 12:30pm-2pm"}, {"Food Pantry"}, {"14645 SW Davis Rd."}, {"97007"}, {"Beaverton"}, {"5035919025"}, {""}, {""}},
                {{"Bienestar"}, {"elmpark@cascade-management.com"}, {"http://www.bienestar-or.org/"}, {""}, {"Provides affordable rental housing for farm workers and low-income working families. Resident services programs are offered that include help with resources and referrals, (TENANTS ONLY) ESL, GED and computer classes, job club, community gardens and activities for children. They also offer a \"Promotores\" (Community Connectors) program for residents."}, {""}, {""}, {"Housing"}, {"481 S Alpine Street"}, {"97113"}, {"Cornelius"}, {"OR"}, {"5033594532"}, {""}, {"English", "Spanish"}},
                {{"Bienestar"}, {"montebello@cascade-management.com"}, {"http://www.bienestar-or.org/"}, {"Low Cost Housing, Free additional services"}, {"Culinary skills job training for low-income residents,"}, {""}, {"Applications for low income housing must meet 50% or less of an area median income."}, {"Low Income Housing, GED, ESL courses"}, {"220 SE 12th Ave"}, {"97123"}, {"Hillsboro"}, {"OR"}, {"0"}, {"Farm Workers", "Low income families"}, {"English", "Spanish"}},
                {{"Bienstar De La Familia Multnomah County"}, {""}, {""}, {""}, {"Group counseling"}, {""}, {""}, {"Counseling"}, {""}, {""}, {""}, {""}, {"503988546528814"}, {"Individuals", "couples"}, {"Spanish"}},
                {{"Birthright of Hillsboro"}, {"hillsboroor@birthright.org"}, {"http://www.birthright.org"}, {"Free"}, {"Pregnancy, maternity and baby cloths, education, professional counseling, too free 24/7 helpline"}, {""}, {"Phone line 24/7. Office open from 10AM-4PM Tuesday-Friday."}, {"Pregnancy", "Parenting Support"}, {"232 NE Lincoln St. Suite F"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5036486766"}, {"Pregnant Women of all ages"}, {"English"}},
                {{"Boys and Girls Aid"}, {""}, {"boysandgirlsaid.org"}, {""}, {""}, {""}, {"Must call to get added to the waiting list."}, {"Transitional Housing"}, {"8196 SW Hall Blvd., Ste. 102"}, {"97008"}, {"Beaverton"}, {"OR"}, {"5036403263"}, {"Youth ages 16-23 homeless or at risk of homelessness", "young women with children."}, {""}},
                {{"Boys and Girls Clubs of Portland"}, {"admin@bgcportland.org"}, {"http://www.bgcportland.org"}, {"$25/year"}, {""}, {""}, {"6 clubhouses located in the Portland Metro Area. Rate is $25/year to join. After School Hours Monday, Tuesday, Thursday, Friday: 2PM-7PM, Wednesday 145PM-7PM. Teen Hours Monday, Tuesday, Thursday, Friday, 2PM-7PM, Wednesday, 145PM-7PM, Friday & Saturday, 7PM-11PM."}, {"Public Computers, Free After School Meal Programs, Alcohol Abuse Education"}, {"560 SE 3rd Ave."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036404558"}, {"Youth ages 6-18"}, {"English"}},
                {{"Bradley Angle House"}, {""}, {"bradleyangle.org"}, {""}, {""}, {""}, {""}, {"Domestic Violence"}, {""}, {""}, {""}, {""}, {"5032812442"}, {""}, {""}},
                {{"Bridges to Change"}, {"contact@bridgestochange.com"}, {"http://www.bridgestochange.com"}, {""}, {""}, {""}, {""}, {"Transitional Housing/Shelter", "Job Assitance", "Low Cost Housing"}, {"260 SW Adams Ave."}, {"971233"}, {"Hillsboro"}, {"OR"}, {"5037199909"}, {"Homeless to Work Program"}, {"English"}},
                {{"Bright Now Dental"}, {""}, {"http://brightnow.com"}, {"Accepts OHP, Reduced Fees and Discounts, 20% or more off services"}, {""}, {""}, {"Union discounts of 40% or more for covered unions. 20% or more off all services for those who need financial assistance and do not have insurance."}, {"Low Cost Dental Care"}, {"7206 NE Cornell Rd"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5036409999"}, {"All ages."}, {"English"}},
                {{"Calvin Presbyterian Church"},{""},{""},{"Free"},{""},{""},{"Open Fridays from 12/4/15-3/25/16 from 6pm to 7am."},{"Sever Weather Shelter"},{"10445 SW Canterbury Ln."},{"97224"},{"Tigard"},{"OR"},{"211"},{"Adults age 18 or older",  "No pets."},{""}},
                {{"Cascadia"},{""},{"cascadiabhc.org"},{""},{""},{""},{""},{"Counseling"},{""},{""},{""},{""},{"5032380769"},{""},{""}},
                {{"Cedar Mill Bible Church - The Jesus Table"},{""},{""},{"Free"},{""},{""},{"Tuesdays 6:00-7:00pm"},{"Meals"},{"12208 NW Cornell Rd."},{"97229"},{"Portland"},{"OR"},{"-1"},{""},{""}},
                {{"Centro Cultural of Washington County"},{"dlopez@centrocultural.org"},{"http://www.centrocultural.org"},{"Application fee $40-$70"},{"Promotes and provides an integral education opportunity for Latino adults, at the literacy and secondary levels of education. It accredits students, pursuant the objectives and contents of instructional programs established by the Ministry of Public Education (SEP) with the support of the Institute of Mexicans Abroad (IME) and the Ministry of Foreign Affairs of Mexico (SRE). The Adult Basic Education program offers GED classes Friday evenings from 6:30 pm-8:30 pm, INEA on Fridays from 6:30pm-8:30pm, Citizenship classes are offered in the fall, winter and spring."},{""},{""},{"Education"},{"1174 North Adair Street 1110 North Adair Street"},{"97113"},{"Cornelius"},{"OR"},{"5033590446"},{""},{"English", "Spanish"}},
                {{"City of Beaverton"},{"disputemail@ci.beaverton.or.us"},{"beavertonoregon.gov/disputeresolution/"},{"Free"},{""},{""},{"Both parties must agree to mediation."},{"Mediation Services"},{"4755 SW Griffith Dr."},{"97005"},{"Beaverton"},{"OR"},{"5035262523"},{""},{"English","Spanish"}},
                {{"City of Hillsboro Community Senior Center"},{"Paula.Stewart@hillsboro-oregon.gov"},{"https://www.hillsboro-oregon.gov/index.aspx?page=873"},{"Free"},{""},{""},{"Open Monday-Friday 9AM-5PM."},{"Legal Counseling, Rentals, Classes, Educational Workshops, Trips"},{"750 SE 8th Ave."},{"97123"},{"Hillsboro"},{"OR"},{"5036151090"},{"Ages 55+"},{"English","interpreters available"}},
                {{"City of Wilsonville"},{"duke@wilsonvillelibrary.org"},{"http://www.wilsonville.lib.or.us"},{"Free"},{""},{""},{"Library open Monday-Thursday 10AM-8PM, Friday-Saturday 10AM-6PM, Sunday 1PM-6PM."},{"Public Computers"},{"8200 SW Wilsonville Rd"},{"97070"},{"Wilsonville"},{"OR"},{"5036598634"},{"All ages."},{"Any"}},
                {{"CODA"},{"laurajacobo@codainc.org"},{"http://www.codainc.org"},{"Reduced rates based on income, some no-cost care, Accepts OHP"},{"Assessment, Counseling groups, Medication-assisted treatment, Case Management"},{""},{"Does not accept Medicare insurance. Some alcohol and drug free housing available."},{"Substance Abuse Counseling"},{"720 SE Washington"},{"97123"},{"Hillsboro"},{"OR"},{"5036480755"},{"Families", "Individuals", "Adults"},{"English"}},
                {{"Community Action Head Start"},{"contact@caowash.org"},{"http://caowash.org/what_we_do/head-start/head-start.html"},{"Free"},{"Home based visits, Full-Day Full-Year Head Start and Early Head Start available."},{""},{"Must live in Washington Couty and have an income that is within 130 percent of the current federal poverty level to be prioritized (http://caowash.org/what_we_do/head-start/head-start-income.html)."},{"Head Start"},{"1001 SE Baseline St."},{"97123"},{"Hillsboro"},{"OR"},{"5036933262"},{"Children"},{"English","Spanish"}},
                {{"Conxiones"},{""},{""},{""},{"Information and referral to Spanish speaking resources"},{""},{""},{"Counselng"},{""},{""},{""},{""},{"5027524620"},{""},{"English","Spanish"}},
                {{"Council on Aging Inc Serving Seniors in Washington County"},{""},{""},{"Free"},{"Some financial assistance"},{""},{""},{"Counseling"},{""},{""},{""},{""},{"5032358057"},{""},{"English","Spanish"}},
                {{"De Paul Treatment Centers, Inc"},{"info@depaultc.org"},{"https://depaultreatmentcenters.org/"},{"Accepts OHP, Sliding Scale"},{"Outpatient mental health, medical detox, residential services, day treatment, family therapy"},{""},{"Sliding scale available for self-pay. Monday-Friday 8AM-6PM, Satruday 8AM-1PM."},{"Mental Health Evaluation, Substance Abuse Counseling"},{"205 SE 3rd St. Suite 100"},{"97123"},{"Hillsboro"},{"OR"},{"5035351151"},{"Ages 18+"},{"English"}},
                {{"Dental Foundation of Oregon"},{"foundation@smileonoregon.org"},{"http://www.smileonoregon.org/"},{"Free"},{"Screenings, Cleanings, Sealants, X-rays, fillings, minor oral surgery, oral education, oral hygiene supplies."},{""},{"Dental van that provides free dental care to school locations. Dental van can be requested, or may be scheduled for your area already http://www.smileonoregon.org/index.asp?N=dental-nonprofit-Wilsonville-OR-Tooth-Taxi-Schedule&C=401&P=3496."},{"Dental Services"},{"8699 SW Sun Place"},{"97070"},{"Wilsonville"},{"OR"},{"5033298877"},{"Uninsured and underserved children."},{"English"}},
                {{"Department of Human Services"},{""},{""},{""},{""},{""},{""},{"Cash Assistance", "Food assistance"},{"5350 NE Elam Young Pkwy."},{""},{"Hillsboro"},{"OR"},{"5036488951"},{""},{""}},
                {{"Domestic Violence Resource Center"},{"dvrc@dvrc-or.org"},{"http://www.dvrc-or.org"},{"Free"},{"Monikas House, Restraining Orders, Advocacy"},{""},{"Pets allowed in shelter. Counseling office open Monday-Thursday."},{"24-hour Crisis line, Shelter, Domestic Violence Legal Services, Professional Counseling, Safety Planning, Trainings, Referrals"},{"Confidential"},{"97123"},{"Confidential"},{"OR"},{"5036405352"},{"Victims of Domestic Violence", "Individuals", "Families", "Children", "Pets"},{"English", "Spanish"}},
                {{"Domestic Violence Resource Center"},{""},{""},{"Free"},{""},{""},{""},{"Hotline"},{""},{""},{""},{""},{"5034698620"},{""},{""}},
                {{"Donated Dental Services Program"},{"dbowman@nsdh.org"},{"http://dentallifeline.org"},{"Free"},{"Comprehensive dental treatment (non-emergency)"},{""},{"Waitlist can be as long as one year. People must not have dental insurance."},{"Dental Services"},{"PO Box 690"},{"97070"},{"Wilsonville"},{"OR"},{"5035940837"},{"Low income adults", "Ages 65+", "Persons with disabilities"},{"English"}},
                {{"Dorcas Society-Tualatin"},{""},{"http://homelessneeds.org/agency-information/oregon/tualatin/51/"},{"Free"},{"Showers, Clothing"},{""},{"Provides clothing, bedding and linens for residents of Wilsonville, Tualatin and nearby towns. 10AM-noon Wednesday provides clothing."},{"Shelter"},{"22222 SW Grahams Ferry Rd"},{"97062"},{"Tualatin"},{"OR"},{"5036922915"},{"Homeless"},{"English"}},
                {{"Easter Seals Oregon"},{""},{"http://www.or.easterseals.com"},{"Free"},{"English as a second language program. Resume Building, Computer Classes."},{""},{""},{"Job Assistance", "Vocational Rehabilitation"},{"1049 SW Baseline St, Suite, D450"},{"97123"},{"Hillsboro"},{"OR"},{"9712288462"},{"Latinos 18 years and older with documented disability and legally be able to reside in this country."},{"English", "Spanish"}},
                {{"El Enfoque De La Familia Counseling Center, Inc"},{""},{"http://edlfamilia.com/en/"},{""},{"Alcohol and Drug services, Anger Management, Domestic Violence and Parenting Classes, Individual Therapy, Marriage and Family Therapy, Alcohol and Drug evaluations"},{""},{""},{"Mental Health Counseling", "Court Ordered Parenting Programs"},{"205 SE 3rd St. Suite 200"},{"97123"},{"Hillsboro"},{"OR"},{"5036814870"},{"Hispanic or Latino offenders"},{"Spanish", "English"}},
                {{"Evergreen Christian Center Food Bank"},{"Rick@ecc4.org"},{"http://www.ecc4.org/"},{"Free"},{""},{""},{"Open Sunday 1215PM, Line begins at 930AM."},{"Food Bank"},{"4400 NW Glencoe Rd."},{"97124"},{"Hillsboro"},{"OR"},{"5036487168"},{"Low income", "Homeless"},{"English"}},
                {{"Fairhaven Recovery Homes"},{"jpliebertz@hotmail.com"},{"fairhavenrecovery.com"},{"Varies by home"},{"Ex-offender halfway houses"},{""},{""},{"Substance Abuse Rehabilitation"},{"20 SW 97th Ave."},{"97225"},{"Portland"},{"OR"},{"5039959023"},{""},{""}},
                {{"Forest Grove Family Resource Center"},{""},{""},{""},{"Provides food pantry for residents in Bank county school ditricts"},{""},{""},{"Food Pantry"},{"42350 NW Trellis Way"},{"97106"},{"Banks"},{"OR"},{"5033245686"},{""},{""}},
                {{"Good Neighbor Center"},{"staff@goodneighborcenter.org"},{"goodneighborcenter.org"},{""},{""},{""},{""},{"Family Shelter"},{"11130 SW Greenburg Rd."},{"97223"},{"Tigard"},{"OR"},{"5034436084"},{"Families only.  No singles."},{""}},
                {{"Goodwill Industries of the Columbia Willamette"},{""},{"http://meetgoodwill.org/blog/job-connection-events/"},{"Free"},{""},{""},{"Monday-Friday 9Am-530PM."},{"Job Assistance", "Resume Building", "ESL Classes and Citizenship", "Microsoft Certification Classes", "GED Preparation Classes", "Job Search", "Job Readiness"},{"966 SE Oak St."},{"97123"},{"Hillsboro"},{"OR"},{"5036487971"},{"Serves ages 16 and older"},{"English", "Spanish"}},
                {{"Goodwill Industries of the Columbia Willamette"},{""},{"metrogoodwill.org"},{"Free"},{""},{""},{""},{"Job Assistance Center"},{"12975 SW Westgate Dr."},{"97005"},{"Beaverton"},{"OR"},{"5036413762"},{"Ages 16 and older"},{""}},
                {{"Goodwill Industries of the Columbia Willamette"},{""},{"metrogoodwill.org"},{"Free"},{""},{""},{""},{"Job Assistance Center"},{"13920 SW Pacific Hwy"},{"97223"},{"Tigard"},{"OR"},{"5036399482"},{"Ages 16 and older"},{""}},
                {{"Hillsboro Police Department"},{"police_dept@ci.hillsboro.or.us"},{"http://www.ci.hillsboro.or.us/index.aspx?page=381"},{"Free"},{"Mediation can include neighbor hood conflicts, property line disputes, noise complaints, community livability issues, parent/teen relationships, family disputes, community facilitations, landlord/tenant issues."},{""},{"Sessions typically last 1-2 hours with the goal to create a written agreement that is not legally binding but kept and maintained in good faith."},{"Crisis Intervention, Legal Disputes, Mediation"},{"250 SE 10th Ave."},{"97123"},{"Hillsboro"},{"OR"},{"5036156651"},{"Adults"},{"English", "interpreters available"}},
                {{"Hillsboro School District"},{"johnsjer@hsd.k12.or.us"},{"http://www.hsd.k12.or.us/miller/connect.html#"},{"Free"},{"School Base Teen Parent/Pregnant Teen Program."},{""},{"Must be in Hillsboro School District to take part in Homeless School Transition and Public Clinic Programs."},{"Career Counseling", "Homeless School Transition Program", "Public Clinic"},{"215 SE Sixth Ave"},{"97123"},{"Hillsboro"},{"OR"},{"5038441000"},{"ages 17-21"},{"English"}},
                {{"HomeForward Merlo Station Harvest Share"},{""},{""},{"Free"},{""},{""},{"Open second Thursday of the month 2:30-4:00pm.  Please bring your own bag"},{""},{"2032 SW Merlo Ct."},{"97006"},{"Beaverton"},{"OR"},{"-2"},{""},{""}},
                {{"Homeplate"},{"info@homeplateyouth.org"},{"http://www.homeplateyouth.org"},{"Free"},{"Showers, Personal Grooming Supplies, Pre Job Guidance and Assistance"},{""},{"Drop in Centers are available three days a week. Current Monday, Wednesday and Thursday nights."},{"Drop in Center", "Meals", "Clothing"},{"332 NE 6th Ave"},{"97124"},{"Hillsboro"},{"OR"},{"5038674954"},{"11-24 year olds who are homeless or at risk of being homeless"},{"English"}},
                {{"Hope Diner at St. Francis"},{""},{""},{"Free"},{""},{""},{"Sun 5pm"},{"Meals"},{"15659 SW Oregon"},{"97140"},{"Sherwood"},{"OR"},{"5036257067"},{""},{""}},
                {{"IRCO"},{""},{""},{""},{""},{""},{""},{"Immigration Services"},{""},{""},{""},{""},{"5032211689"},{""},{"English","Spanish"}},
                {{"Jubilee Transition Homes"},{"jubilee@comcast.net"},{"jubileeth.com"},{""},{""},{""},{""},{"Transitional Housing"},{"Confidential Address"},{""},{"Tigard"},{"OR"},{"-3"},{"Single adult males"},{""}}, //56
                {{"Lifeskills Learning Institute, INC."},{""},{"http://lsli.net/"},{"$15-25 per 45 minute session"},{""},{""},{""},{"Counseling"},{"4110 Pacific Avenue Suite 102b"},{"97116"},{""},{"OR"},{"5036015400"},{""},{"English"}},
                {{"Lifeworks NW"},{""},{"lifeworksnw.org"},{""},{""},{""},{""},{"Counseling"},{""},{""},{""},{""},{"5036459010","8005461666"},{""},{""}},
                {{"Love in the Name of Christ of Greater Hillsboro"},{"office.loveinc@gmail.com"},{"http://www.loveincgreaterhillsboro.org"},{"Free"},{"Personal Grooming Supplies, Laundry Products, Bedding"},{""},{"Mondays 130PM-330PM, Tuesdays and Thursdays 930AM-12PM."},{"School Supplies"},{"1055 NE 25th Ave. Suite O"},{"97124"},{"Hillsboro"},{"OR"},{"5036480700"},{"Adults", "Individuals", "Families", "Children"},{"English"}},
                {{"Luke-Dorf West"},{"kradecki@luke-dorf.org"},{"http://www.luke-dorf.org"},{""},{""},{""},{""},{"Shelter", "Mental Health Evaluation", "Counseling", "Case Management", "Skills Training", "Group Therapy", "Medication Management", "Group Homes", "Supportive Housing", "Transitional Housing"},{"Confidential"},{"97123"},{"Confidential"},{"OR"},{"5036403262"},{"Chronically homeless persons with persistant mental illness", "Individuals 18+ who are experiencing homelessness, poverty, substance abuse, and lack support"},{"English"}},
                {{"McKinney-Vento Homeless-BSD"},{"lisa_mentensana@beaverton.k12.or.us"},{"beaverton.k12.or.us/cmnty/Pages/homeless-program.aspx"},{"Free"},{""},{""},{""},{"Homeless School Transition Program"},{"16550 SW Merlo Rd."},{"97006"},{"Beaverton"},{"OR"},{"5035914462"},{"Homeless youth"},{"English", "Spanish"}},
                {{"Meals on Wheels People"},{"info@mealsonwheelspeople.org"},{"http://www.mealsonwheelspeople.org/what-we-do/dining-centers/hillsboro-center"},{"Free, $7.39/meal donation requested, but not required if unaffordable"},{""},{""},{"Lunch is served Monday-Friday at service locations. Meals on wheels delivers Monday-Friday between the hours of 11AM-1PM. Meal service begins 48 hours after the office is notified. No one is refused service."},{"Home Delivered Meals", "Hot Meals"},{"525 SE Baseline St."},{"97123"},{"Hillsboro"}, {"OR"},{"5036482950"},{"Adults ages 60+ who are homebound"},{"English", "Spanish", "German", "Arabic"}},
                {{"Mens Resource Center"},{""},{""},{""},{""},{""},{""},{""},{""},{""},{""},{""},{"5032353433"},{""},{""}},
                {{"Metropolitan Public Defender"},{""},{"http://www.mpdlaw.com"},{"Low Cost"},{"Advocacy"},{""},{"Court referral only. Office Hours 8AM-5PM Monday-Friday."},{"Public Defender"},{"400 E Main St. Suite 210"},{"97123"},{"Hillsboro"},{"OR"},{"5037267900"},{"Indigent and Veteran clients"},{"English"}},
                {{"Morrison Family Services"},{""},{""},{""},{""},{""},{""},{""},{""},{""},{""},{""},{"5035423025"},{""},{""}},
                {{"Mountain Retreat Secured Transport"},{"thora@spiritone.com"},{"mtretreat.org"},{""},{""},{""},{""},{"Ride/Transit Program"},{""},{""},{""},{""},{"8666669895"},{"Children", "Adolescents", "Adults typically between mental health facilities."},{""}},
                {{"National College of Natural Medicine"},{"ccsupport@ncnm.edu"},{"http://www.ncnm-clinic.com"},{"Low Cost/Sliding Scale"},{"Naturopathic Medicine"},{""},{"Visit fees range from $20-$40 depending on service. Clinic Open Mondays-Fridays 9AM-4PM. Must be able to pay discounted fee at time of service."},{"Community Clinic", "Primary Care", "Womens Health", "Acupuncture", "Immunizations"},{"222 SE 8th Ave. Suite 212"},{"97123"},{"Hillsboro"},{"OR"},{"5033527333"},{"Adults"},{"English", "Spanish"}},
                {{"National Runaway Switchboard"},{""},{""},{"Free"},{""},{""},{""},{"Hotline"},{""},{""},{""},{""},{"8007862929"},{""},{""}},
                {{"Neighorhood House Community Services"},{""},{""},{"Free"},{""},{""},{"Mon 10:00 am - 12:00-& 1:30 pm - 5:00 pm, Wed 10:00 am - 12:00 pm-& 1:30pm - 5:00 pm, Thurs 2:00 pm-8:00 pm"},{"Food Pantry"},{"3445 SW Moss St."},{"97219"},{"Portland"},{""},{"5032461663"},{"Serves West Multnomah County", "East Washington County","North Portland. Please call for locations"},{""}},
                {{"Northwest Regional Education Service Distrct"},{"referral@nwresd.k12.or.us"},{"http://www.nwresdeiecse.org"},{"Free"},{"Developmental Screenings and Evaluations, Parent coaching, special education services, physical, occupations or speech and language therapy"},{""},{"Spanish Interpretation available."},{"Mental Illness Early Intervention"},{"5825 NE Ray Circle"},{"97124"},{"Hillsboro"}, {"OR"},{"5036141446"},{"Children with developmental disabilities or developmental delays", "age birth to five years", "Families"},{"English", "Spanish"}},
                {{"OHSU Behavioral Health Center"},{""},{"ohsu.edu/xd/health/ohsu-near-you/index.cfm"},{""},{""},{""},{""},{"Counseling"},{""},{""},{""},{""},{"5034944745"},{""},{""}},
                {{"Open Door Counseling Center"},{"info@opendoorcc.net"},{"http://www.opendoorcc.net"},{"Free"},{"Showers,Personal Grooming Supplies"},{""},{"Open Monday-Friday 9AM-5PM, Drop in closed on Wednesday. Food boxes are available Monday-Friday from 1pm until 3pm."},{"HUD/Home Buyer Counseling", "Mental Health Counseling", "Drop in Center", "Case Management", "Telephone usage", "Hot Meals"},{"34420 SW Tualatin Valley Highway"},{"97123"},{"Hillsboro"},{"OR"},{"5036406689"},{"Adults"},{"English"}},
                {{"Oregon Child Development Coalition"},{"info@ocdc.net"},{"http://www.ocdc.net"},{"Free"},{"Pre-Kindergarten, Migrant Education, Family Childcare"},{""},{"Cribs for kids. Head start programs available for those with family income 100% of the Federal Poverty Line, family income must come from agricultural labor, migrant farmworkers must have moved within the last two years. Priority is given to children with disabilities, and families coping with homelessness or other significant needs."},{"Furniture", "Head Start"},{"9140 SW Pioneer Court Suite E"},{"97070"},{"Wilsonville"}, {"OR"},{"5035701110"},{"Migrant farmworkers and families", "families at significant disadvantage"},{"English", "Spanish"}},
                {{"Oregon Department of Veterans Affairs"},{"davisinfo@co.washington.or.us"},{"http://www.co.washington.or.us/HHS/DAVS/Veterans/index.cfm"},{"Free", "Low Cost"},{"Assistance with living independently, counseling for residential living"},{"Phones, Energy Assistance"},{"Open from Monday-Friday 8AM-430PM."},{"Emergency Financial Assitance", "Benefits Assistance", "In-Home Assistance", "Referrals"},{"5240 NE Elam Young Parkway, Suite 300"},{"97124"},{"Hillsboro"}, {"OR"},{"5038463060"},{"Older adults with physical disabilities"},{"English", "Spanish", "interpreters available"}},
                {{"Oregon DHS Children, Adults and Families Division for Washington County"},{""},{"http://www.oregon.gov/DHS/assistance/localoffices.shtml"},{"Free", "Low cost", "income-based child care"},{"Food Benefits, Cash Benefits, Medical and Dental Insurance, Rent Assistance, Self-Sufficiency Program for Domestic Violence Survivors, Energy Assistance, Perscription Assistance"},{""},{"Domestic Violence self sufficiency program can give $1200 over 90 days to tell toward deposits, rents, and utilities."},{"Temporary Assitance", "Aging and disability services", "Benefit Enrollment", "Financial Aide", "Child Care Assistance"},{"5300 NE Elam Young Pky"},{"97124"},{"Hillsboro"},{"OR"},{"5036934555"},{"Domestic Violence Survivors", "Seniors", "Individuals", "Families", "Students", "Residents of Oregon"},{"English", "Spanish", "Russian", "Vietnamese", "interpreters available"}},
                {{"Oregon Human Development Corporation"},{"info@ohdc.org"},{"http://www.ohdc.org"},{"Free"},{"English as a second language program."},{""},{"Youth program participants must be recent high school or GED drop outs, low income, and willing to work, join the military or join college upon completing the program. Farmworker Program participants must be citizens of the US, or lawfully admitted, must not have violated Section 3 of the Military Selective Service Act and must be a disadvantage seasonal or migrant farmworker during any consecutive 12 month period within the most recently 24 month period, or be the spouse or dependent of such a worker who additionally meats the first two criteria."},{"Job Assistance", "Referral", "Advocacy", "Education"},{"334 SE 5th St."},{"97123"},{"Hillsboro"},{"OR"},{"5036405496229"},{"Serves ages 16-19", "farmworkers and disadvantaged individuals"},{"English", "Spanish"}},
                {{"Oregon Korean Community Center"},{"oregonkorean@gmail.com"},{"oregonkorean.org"},{"Free"},{"Translation, interpretation, eduation with ESL and citizenship classes, free health screenings, senior programs, youth programs (e.g. counseling), referral to other social services"},{""},{""},{"Community Resource Center"},{"12555 SW 4th St."},{"97005"},{"Beaverton"},{"OR"},{"5036417887"},{""},{"English", "Korean"}},
                {{"Oregon Law Center"},{"laurie.hoefer@lasoregon.org"},{"http://www.oregonlawcenter.org"},{"Free"},{"Administractice Law, Employment Law, Family Law, Farmworker Law, Housing Law, Indigeneous Project"},{""},{"Helps provide civil legal services to assure faireness on matters related to critical needs like food, shelter, medical care, income and physical safety. Office open from 9AM-5PM Monday, Wednesday, Thursdays and Fridays and from 11-7 on Tuesdays."},{"Tenant Rights", "Legal Counseling and Representation"},{"230 NE Second Ave. Suite F"},{"97124"},{"Hillsboro"},{"OR"},{"5036404115"},{"Low income individuals"},{"English", "Spanish", "Mixteco", "interpreters supplied on request."}},
                {{"Oregon Rx Card"},{"igonzales@oregonrxcard.com"},{"oregonrxcard.com"},{"Free"},{""},{""},{""},{"Prescription Discount Card"},{"Website only"},{"97228"},{"Portland"},{"OR"},{"8772793424"},{""},{""}},
                {{"Oregon Somali Family Education Center"},{"sfec1@yahoo.com"},{"osfec.org"},{"Free"},{""},{""},{"Tutoring, parent education and support, sports and recreation, ESL classes, skill building, health education."},{"Assistance for Immigrants"},{"2032 SW Merlo Ct."},{"97006"},{"Beaverton"},{"OR"},{"5039956031"},{"Somali children and their families"},{""}},
                {{"Oregon State Bar Modest Means Program"},{""},{"osbar.org"},{"$35 for first consultation, reduced retainer and hourly fee based on income"},{""},{""},{"Only handle landlord/tenant, family law, and criminal defense."},{"Legal Services"},{"16037 SW Upper Boones Ferry Rd."},{"97224"},{"Tigard"},{"OR"},{"8004527636"},{"Low income Oregonians"},{"English", "Spanish"}},
                {{"OrFIrst"},{""},{""},{""},{""},{""},{""},{"Special Education"},{""},{""},{""},{""},{"5032320302"},{""},{"English", "Spanish"}},
                {{"Our Saviors Lutheran Church"},{"oslc@oursaviors.net"},{"info@oursaviors.net"},{""},{"Provides an emergency three day supply of non-perishable food items for individuals and families in need."},{""},{""},{"Food Pantry"},{"15751 SW Quarry Road"},{"97034"},{"Lake Oswego"},{"OR"},{"5036354563"},{""},{""}},
                {{"Outside In"},{""},{""},{""},{""},{""},{""},{"Counseling"},{""},{""},{""},{""},{"5035353800"},{"Homeless and uninsured youth and adults"},{""}},
                {{"Pacific University College of Optometry"},{""},{"http://www.pacificu.edu/our-resources/clinics/eyeclinics/pacific-eyeclinic-hillsboro"},{"Accepts OHP, Reduced Fees, Payment plans"},{""},{""},{"Open Monday-Friday 8AM-5PM. Closed daily from noon-1PM. Fees for services that are not in the complete evaluation are extra and are not offered at a reduced rate."},{"Vision Service"},{"222 SE 7th Ave."},{"97123"},{"Hillsboro"},{"OR"},{"5033527300"},{"Low income", "Adults", "Children"},{"English"}},
                {{"Pacific University Dental"},{"dentalhealth@pacificu.edu"},{"http://www.pacificu.edu/our-resources/clinics/pacific-dental-hygiene-clinic"},{"http://www.pacificu.edu/our-resources/clinics/pacific-dental-health-clinic/pacific-dental-health-clinic-services"},{"Basic restorative services (no crowns, extractions, dental emergencies or comprehensive dental care)"},{""},{"Hours are Monday 8AM-noon, 130PM-5PM, 6PM-9PM, Tuesdays 8AM-1130AM, 1PM-5PM, Wednesday 8AM-noon, 130PM-5PM, 6PM-9PM, Thursday 8AM-noon. Appointments may last as long as 4 hours due to the nature of an educational clinic."},{"Dental Care"},{"222 SE 8th Ave. Suite 270"},{"97123"},{"Hillsboro"},{"OR"},{"5033527373"},{"Ages 3+"},{"English"}},
                {{"Partnership for Prescription Assistance Oregon"},{""},{"pparxor.org"},{"Free"},{""},{""},{""},{"Prescription Assistance"},{""},{"website only"},{""},{""},{"8884772669"},{"Qualifying individuals"},{""}},
                {{"Patient Assistance Program Center"},{""},{"rxassist.org"},{"Free"},{""},{""},{"Database of prescription assistance programs along with other health related information."},{"Prescription Assistance"},{"website only"},{""},{""},{""},{"-4"},{""},{""}},
                {{"Portland Womens Crisis Line"},{""},{""},{"Free"},{""},{""},{""},{"Crisis Line", "Domestic Violence"},{""},{""},{""},{""},{"5032355333"},{""},{"Spanish"}},
                {{"Portland Communit College"},{""},{"pcc.edu/resources/women/rock-creek/new-directions.html"},{"Free"},{""},{"Some transportation and child care remibursement available"},{""},{"Job Assistance"},{"17705 NW Springville Rd."},{"97229"},{"Portland"},{"OR"},{"9717227448"},{"Serves women who are single parents or displaced homemakers"},{""}},
                {{"Portland Vineyard Church - Kings Kindness"},{""},{"portlandvineyard.org"},{"Free"},{""},{""},{"Open Th 12pm-3pm.  Proof of residency in Washington County or ID required."},{"Food Pantry"},{"11460 SW 114th Ave."},{"97224"},{"Tigard"},{"OR"},{"5036848225"},{""},{""}},
                {{"Project Respond"},{""},{""},{"Free"},{"Resources and support groups for survivors of sexaul assault and domestic violence"},{""},{""},{"Help Line/Crisis Line?"},{""},{""},{""},{""},{"5039884888"},{""},{""}},
                {{"Providence Health and Services"},{""},{"http://www.providence.org/oregon"},{"Accepts OHP, Reduced Fees, Payment plans"},{""},{""},{"Open 24/7"},{"Urgent Care"},{"18610 NW Cornell Rd. Suite 300"},{"97124"},{"Hillsboro"}, {"OR"},{"5032154300"},{"All ages"},{"English", "Spanish", "Vietnamese"}},
                {{"Proyecto Unica (Catholic Charitiies)"},{""},{"catholiccharitiesoregon.org/latino_services_unica.asp"},{""},{""},{""},{""},{"Crisis Line", "Domestic Violence"},{""},{""},{""},{""},{"5032324448", "8882324448"},{""},{"Spanish"}},
                {{"Puentes/Central City Concern"},{""},{"centralcityconern.org/changing-lives/puentes.html"},{""},{""},{""},{""},{"Domestic Violence"},{"232 NW 6th Ave."},{"97209"},{"Portland"}, {"OR"},{"5032941681"},{""},{"Spanish"}},
                {{"Raphael House"},{""},{"raphaelhouse.com"},{""},{"Emergency Shelter, Advocacy Center, Youth Program, Transitional Housing"},{""},{"Crisis Line: 503-222-6222"},{"Domestic Violence","Crisis Line"},{"4110 SE Hawthorn Blvd. #503"},{"97214"},{"Portland"},{"OR"},{"5032226507"},{""},{""}},
                {{"Ride Connection"},{"triprequests@rideconnection.org"},{"triprequests@rideconnection.org"},{"Free/$1.50 donations suggested but not required in some routes"},{""},{""},{"Office open Monday-Friday 730AM-5PM. Schedules available online or over the phone."},{"Transportation"},{"9955 NE Glisan St."},{"97220"},{"Portland"}, {"OR"},{"5032260700"},{"Must have OHP insurance to qualify for free rides, or a senior, or with a disability."},{"English", "Interpreters available when needed."}},
                {{"Ride to Care"},{""},{"https://www.ridetocare.healthcare/about/contact-us"},{"Free to OHP clients"},{""},{""},{"Can order online 24 hours ahead of time, if you call you must do so 2 days before regular services are needed. You can call or order services up to three months in advance. You must have OHP insurance and be in need of a ride to a medically based appointment where you will use the insurance. You may also request a ride for an emergency, or to the pharmacy. Business days are Monday-Friday 7AM-7PM. You may bring one person on the ride with you as a support person."},{"Medical Transportation"},{""},{""},{""},{""},{"18553214899"},{"OHP plan holders"},{"English"}},
                {{"Rock Creek Church EFB"},{""},{""},{"Free"},{"Meals Thursdays 6-7pm."},{""},{"Open M 3-4pm, T 9am-12pm and 4-7pm.  Mondays by appointment only. Please call day of for appointment all other days."},{"Food Pantry"},{"4470 NW 185th Ave."},{"97229"},{"Portland"},{"OR"},{"5036452525"},{""},{""}},
                {{"Rolling Hills Community Church"},{"info@rollinghills.org"},{"http://www.rollinghills.org"},{"Free"},{""},{""},{"Open Wednesdays through 3/2/16, 630PM-7AM, Intake 630PM-8PM. No pets"},{"Cold Weather Shelter", "Marriage Counseling", "Work Clothing", "Group Recovery Counseling"},{"3550 SW Borland Rd."},{"97062"},{"Tualatin"}, {"OR"},{"5036385900"},{"Adults 18+"},{"English"}},
                {{"Safe Journeys, LLC"},{""},{""},{"Reduced Cost"},{"Outpatient and partial hospitalization for addition services."},{""},{"Self Payment and Private Health Insurance."},{"Gambling Addition Prevention Services"},{"18801 SW Martinazzi Ave. Building A, Suite 206"},{"97062"},{"Tualatin"}, {"OR"},{"5039890991"},{"Adults"},{"English"}},
                {{"Safe Place for Youth - Boys and Girls Aid"},{"info@boysandgirlsaid.org"},{"http://www.boysandgirlsaid.org"},{"Free"},{"Clothing, Showers, Case Management, Laundry, Meals and Snacks"},{""},{"No pets, must not be under the influence of drugs or alcohol, must be able to get along in a group setting, cannot be suicidal or in need of a crisis mental health treatment or medical care that isnt provided at the facility. Also has facilities for girls ages 10-18, and a transitional housing program for 16-23 year olds."},{"Cold Weather Shelter", "24/7 services and phone availability", "Referrals"},{"454 SE Washington St."},{"97123"},{"Hillsboro"},{"OR"},{"5035422717"},{"Youth ages 13-19 years"},{"English"}},
                {{"Saint Child"},{"office@saintchild.org"},{"saintchild.org"},{""},{"Pregnancy Support"},{""},{""},{"Transitional Housing", "Pregnancy Support"},{"Confidential"},{"97005"},{"Beaverton"},{"OR"},{"5036484227"},{"Pregnant women ages 14-24 with no children and are less than 7 months pregnant."},{""}},
                {{"Salud Services"},{"leda.garside@tuality.org"},{"http://www.saludauction.org/"},{"Accepts OHP, Low Cost"},{""},{""},{"Accepts Oregon Health Plan Insurance. Will negotiate pricing and payment of services if low income/financial difficulty can be proven."},{"Community Clinic, Health Clinic, Dental Clinic"},{"335 SE 8th Ave."},{"97123"},{"Hillsboro"}, {"OR"},{"5036814290"},{"All ages."},{"Spanish", "English"}},
                {{"Salvation Army Tualatin Valley Citadel"},{""},{"http://www.salvationarmyportland.org/portland/tualatin_valley_citadel"},{"Free"},{""},{""},{"Only available to Hillsboro residents. Food Assistance available Monday/Tuesday/Thursday/Friday 1PM-3PM, Saturday 9AM-1130AM"},{"Water Bill Assistance", "Food boxes", "School Supplies"},{"351 SE Oak St."},{"97123"},{"Hillsboro"},{"OR"},{"5036404311"},{"All ages."},{"English"}},
                {{"Samaritans Kitchen at True Life Church"},{""},{""},{"Free"},{""},{""},{"Meals second and fourth Sat. of the month 10:00am-12:00pm."},{"Meals"},{"1895 NW 169th Pl."},{"97006"},{"Beaverton"},{"OR"},{"5038107436"},{""},{""}},
                {{"Sequoia Mental Health Services"},{"info@sequoiamhs.org"},{"http://www.sequoiamhs.org"},{""},{"Child and Family Services, Individual Therapy, Group Therapy, Adolescent Services, Family Therapy, Case Management"},{""},{""},{"Mental Health Evaluation", "Counseling", "Supportive Housing", "Residential Treatment", "Substance Use/Addiction Treatment"},{"395 W Main St."},{"97123"},{"Hillsboro"},{"OR"},{"5036191560"},{"Individuals", "Families", "Adolescents"},{"English"}},
                {{"Seventh Day Adventist Church"},{"hillsborosda@yahoo.com"},{"http://www.hillsborosda.org/article/21/the-ministries-of-our-church/community-service"},{"Free"},{""},{""},{"Open Thursdays from 630PM-8PM."},{"Clothing", "Food Pantry"},{"367 NE Grant St."},{"97124"},{"Hillsboro"}, {"OR"},{"5036483922"},{"Families"},{"English"}},
                {{"Sonrise Church"},{"mercy@isonrise.com"},{"http://www.isonrise.com"},{"Free"},{"Showers on designated nights, hygiene items, employment resources"},{""},{"Food boxes available Thursday 12PM-130PM. Shelter open Monday-Friday 545PM-830AM, Saturdays 8PM-730AM, Sundays 545-830AM. TB tests are required to stay overnight. Sleeping backs and sleeping mats are provided. Men and women are required to sleep in different areas."},{"Clothing", "Food Pantry", "Shelter"},{"6701 NE Campus Way"},{"97124"},{"Hillsboro"},{"OR"},{"5036402449"},{"Families", "Individuals", "Adults"},{"English"}},
                {{"Southwest Community Health Center"},{"contact@swchc-pdx.org"},{"http://www.swchc-pdx.org"},{"Sliding Scale"},{""},{""},{"Open Mondays and Thursdays from 530PM-830PM - walk in time and scheduled appointments."},{"Community Clinic", "Primary Care", "Emergency Dental Services", "Perscription Medication", "Immunizations", "Preventative Health"},{"226 W Main St."},{"97123"},{"Hillsboro"},{"OR"},{"5038464418"},{"Low income", "Individuals", "Families", "Uninsured", "Children"},{"English"}},
                {{"St Andrew Legal Clinic"},{"info@salcgroup.org"},{"http://www.salcgroup.org"},{"Sliding Scale, $25 for first consultation"},{""},{""},{"Sliding scale based on income, family size, and ability to pay. Open from 9AM-5PM with break from noon-1PM. Intakes on Wednesday evenings."},{"Family Law", "Child Custody"},{"232 NE Lincoln St. Suite H"},{"97124"},{"Hillsboro"},{"OR"},{"5036481600"},{"Families", "Children", "Low and Moderate Income"},{"English", "Spanish"}},
                {{"St. Anthony Catholic Church"},{""},{"stanthonytigard.org"},{"Free"},{""},{""},{"Open M,T,F 12pm-2:30pm, W 1-3pm, Th 12pm-2:30pm and 4:30-6:30pm, Sat 10am-12pm"},{"Food Pantry"},{"9905 SW McKenzie St."},{"97223"},{"Tigard"},{"OR"},{"5036394179"},{""},{""}},
                {{"St. Anthonys Catholic Church - Tigard"},{""},{"stanthonytigard.org"},{"Free"},{""},{""},{"Open Saturdays 11/7/15-3/26/16 and severe weather days.  Hours 5:30pm-7am (must check in prior to 9pm when doors close)."},{"Severe Weather Shelter"},{"9905 SW McKenzie St."},{"97223"},{"Tigard"},{"OR"},{"-5"},{"Adults only.  No pets."},{""}},
                {{"St. Bartholomew Episcopal"},{"welcomehome@stbartsbeaverton.org"},{""},{"Free, ELS classes $5 for ten week term"},{""},{""},{"Open F 1:30pm-4:30pm for food.  ESL classes Th 6:30-8pm"},{"Food Pantry", "ESL classes"},{"11265 SW Cabot St."},{"97005"},{"Beaverton"},{"OR"},{"5036443468"},{""},{""}},
                {{"State of Oregon Employment Department"},{""},{"oregon.gov/EMPLOY"},{"Free"},{""},{""},{""},{"Unemployment", "Employment Insurance"},{"875 Union St. NE"},{"97311"},{"Salem"},{"OR"},{"8773453484"},{""},{""}},
                {{"Sunshine Pantry"},{""},{""},{"Free"},{""},{""},{"Open M-F: 11am-2pm"},{"Food Pantry"},{"10895 SW 5th St."},{"97008"},{"Beaverton"},{"OR"},{"9715067827"},{""},{""}},
                {{"SVDP St. Anthony Community Café"},{""},{""},{"Free"},{""},{""},{"Sun 5:30pm-7:00pm"},{"Meals"},{"9905 SW McKenzie St."},{"97223"},{"Tigard"},{"OR"},{"5033092121"},{""},{""}},
                {{"SVDP St. Anthonys"},{""},{""},{"Free"},{""},{""},{"Mon 12:00 pm-2:30 pm, Tues 12:00 pm-2:30 pm, Wed 1:00 pm-3:00 pm, Thurs 12:00-2:30 &-4:30-6:30pm, Fri 12:00 pm-2:30 pm, Sat 10:00am -12:00 pm.  Proof of residence required."},{"Food Pantry"},{"12630 SW Grant Ave."},{"97223"},{"Tigard"},{"OR"},{"5036848280"},{""},{""}},
                {{"Therapy Matters"},{"therapymatters@zoho.com"},{"http://therapymatters.zohosites.com"},{""},{""},{""},{""},{"Counseling"},{"16780 SW Bryant Road #202, West Bay Offices"},{"Lake Oswego"},{"97035"},{"OR"},{"5035446573"},{""},{""}},
                {{"Tigard Community Basket"},{""},{""},{"Free"},{""},{""},{"Third Thrusday of the month 1-2pm.  Bring your own bag."},{"Food Pantry"},{"11905 SW 91st Ave."},{"97223"},{"Tigard"},{"OR"},{"5032934038"},{""},{""}},
                {{"Tigard UMC - Bethlehem House of Bread"},{""},{""},{"Free"},{""},{""},{"Open Sun. 1:00pm-3:00pm."},{"Food Pantry"},{"9055 SW Locust"},{"97223"},{"Tigard"},{"OR"},{"-6"},{""},{""}},
                {{"Trans Lifeline"},{""},{"http://hotline.translifeline.org"},{"Free"},{""},{""},{"Generally available Mondays 12AM-1AM, 9AM-9PM, and 11PM-midnight, Tuesdays 12AM-3AM, 10AM-5PM, 1PM-10PM and 2PM-midnight, Wednesdays from 12AM-3AM, 11AM-12PM, 1PM-10PM and 2PM-midnight, Thursdays 12AM-8AM, 10AM,12PM, 1PM-10PM, and 2PM-midnight, Fridays 12AM-3AM, 1PM-10PM, and 2PM-11PM, Saturdays 10AM-10PM, and 2PM-midnight, Sundays from 9AM-6PM, 10AM-7PM, 4PM-9PM and 7PM-midnight EST."},{"Crisis Line"},{""},{""},{""},{""},{"8775658860"},{"Trans People"},{"English"}},
                {{"Trevor Project"},{""},{"http://www.thetrevorproject.org"},{"Free"},{"Secure Networking"},{""},{"Text the word Trevor to 1-202-304-1200, available thursdays and fridays 1pm-5pm. TrevorChat available 7 days a week from 12PM-6PM PST. TrevorSpace available to securely network LGBTQ young people (ages 13-24)."},{"24 Hour Crisis Line", "Text and Online Chat Service"},{""},{""},{""},{""},{"8664887386"},{"Lesbian", "gay", "bisexual", "transgender", "queer and questioning young people ages 13-24"},{"English"}},
                {{"Trimet"},{"accessible@trimet.org"},{"http://trimet.org/fares/honoredcitizen.htm"},{"Low Cost/Reduced Cost http://trimet.org/fares/index.htm#farechart"},{""},{""},{"Open 830AM-530PM Monday-Friday. Rides run all days generally from 430AM-230AM. If you are unable to use regular TRIMET transportation you may be eligible to apply for LIFT, which is a shared-ride purblic transport service. In order to apply for LIFT you must visit the Trimet Transit Mobility Center at 515 NW Davis St. Portland, OR 97209 - 503-962-8200."},{"Low Cost Transportation"},{""},{""},{""},{""},{"5039622455"},{"Seniors and People with Disabilities"},{"English", "Spanish"}},
                {{"True Life Fellowship"},{""},{""},{"Free"},{""},{""},{"Open M 12:00pm-2:00pm, F 12:00pm-2:00pm, second and fourth Sat. 10:00am-1:00pm."},{"Food Pantry"},{"1895 NW 169th Pl."},{"97006"},{"Beaverton"},{"OR"},{"5038107426"},{""},{""}},
                {{"Tualatin Public Library"},{"librarymail@ci.tualatin.or.us"},{"http://www.tualatinoregon.gov/library/esl-tutors-english-second-language-tutores-de-ingl%C3%A9s"},{"Free/Low Cost"},{""},{""},{"Library Open Monday-Thursday 10AM-PM, Friday-Saturday 10AM-6PM, Sunday 1PM-6PM."},{"English Language, Tech, and Homework Tutors"},{"18878 SW Martinazzi Ave."},{"97062"},{"Tualatin"}, {"OR"},{"5036913072"},{"Students", "non-English speakers"},{"English", "Spanish"}},
                {{"Tualatin School House Pantry"},{"pantry@schoolhousepantry.org"},{"http://www.schoolhousepantry.org"},{"Free"},{""},{""},{"Open Monday 3PM-8PM, Wednesday and Friday 10AM-3PM. Identification/proof of residence in Tualatin, Durham, Lake Oswego or West Linn is required when visiting the pantry. Food boxes include five days worth of food and personal supplies and are available once monthly. Dental vans see 12 patients or so per visit, wait times can be 2-3 months before getting a appointment. To be added to the waitlist please call in prior to visiting."},{"Dental Van", "Food Bank"},{"3550 SW Borland Rd."},{"97062"},{"Tualatin"}, {"OR"},{"5037830721"},{"Adults", "Families", "Individuals", "Children (dental van)"},{"English"}},
                {{"Tuality Healthcare"},{"jenn.lunzmann@tuality.org"},{"http://www.tuality.org"},{"Varies"},{"Breast feeding support program."},{"Financial assistance available based on criteria (includes submitting tax documents, letters, valid government issued ID, school aide letters and bank statements)"},{"Breast feeding support participants must have children under two years of age. Full financial assistance can be applied for, and will most likely be granted to those with family income at or below 200% the Federal Poverty Guidelines. Discounts are given for those who do not have health insurance and lowers the amount owed by 10 to 50 percent depending on patients account balance."},{"Medical Transportation", "Urgent Care", "Health Care"},{"335 SE 8th Ave.","7545 SE TV Highway"},{"97123","97123"},{"Hillsboro","Hillsboro"},{"OR","OR"},{"5036811818","5036814223"},{"All ages."},{"English", "Spanish"}},
                {{"TVW Inc"},{""},{"http://www.tvwinc.org"},{"Free"},{""},{""},{"Open 8Am-4PM Monday-Friday."},{"Job Assitance", "School-Transition Programs"},{"6615 SE Alexander St."},{"97123"},{"Hillsboro"}, {"OR"},{"5036498571"},{"Developmentally and physically disabled", "18 and older"},{"English"}},
                {{"Unity of Beaverton"},{""},{""},{"Free"},{""},{""},{"Open T,W 10am-4pm.  Bring your own bags/boxes."},{"Food Pantry"},{"12650 SW 5th St."},{"97005"},{"Beaverton"},{"OR"},{"5036463364"},{""},{""}},
                {{"Veterans Crisis Line"},{""},{"http://www.veteranscrisisline.net"},{""},{""},{""},{""},{"Hotline"},{""},{""},{""},{""},{"80027382551"},{""},{""}},
                {{"Virginia Garcia Memorial Health Center - Cornelius"},{""},{"http://virginiagarcia.org/locations/cornelius/"},{""},{"Primary care, pharmacy, dental, vision, and wellness classes"},{""},{""},{"Counseling"},{"1151 N. Adair St."},{"97113"},{"Cornelius"}, {"OR"},{"5033595564"},{"Individuals", "couples"},{"English","Spanish"}},
                {{"Washington County Community Corrections"},{"volntr@co.washington.or.us"},{"http://www.co.washington.or.us/CommunityCorrections/VictimServices/index.cfm"},{"Free"},{"Individual, Couples, Family, Group Counseling"},{""},{"Free general counseling. Parental Visitation Facilitation not open on weekends. Hours of operation 8:30AM-5PM Monday-Friday."},{"Counseling Services", "Parental Visitation Facilitation", "Advocacy", "Referrals"},{"160 SW Washington St."},{"97124"},{"Hillsboro"}, {"OR"},{"5038463020"},{"All alges."},{"English", "Spanish"}},
                {{"Washington County Cooperative Library Services"},{"libraryfeedback@hillsboro-oregon.gov"},{"http://www.wccls.org/libraries/hillsboroshutepark"},{"Free"},{"English as a second language program."},{""},{"Library open Monday-Wednesday 10AM-8PM, Thursday-Saturday 10AM-6PM, Sunday 12PM-6PM."},{"Public Computers", "Continuing Education"},{"775 SE 10th Ave"},{"97123"},{"Hillsboro"},{"OR"},{"5036156500"},{"All ages."},{"Any"}},
                {{"Washington County Crisis Line"},{""},{""},{"Free"},{""},{""},{""},{"Crisis Line"},{""},{""},{""},{""},{"5032919111"},{""},{"English"}},
                {{"Washington County Department of Housing Services"},{"washco_housing@co.washington.or.us"},{"http://www.co.washington.or.us/housingpreap"},{"Free/Low Cost"},{""},{""},{"Waitlists are currently closed. Those with combined incomes of 50% of the national average can apply for housing vouchers and section 8. Rental rates are usually 30% of the applicants income."},{"Public Housing", "Low Income Housing", "HUD and section 8 applications"},{"111 NE Lincoln St. Suite 200-L"},{"97124"},{"Hillsboro"},{"OR"},{"5038464794"},{"Recently Homeless", "80% of median income or less"},{"English", "Spanish", "interpreters available"}},
                {{"Washington County District Attorney"},{"childsupport@co.washington.or.us"},{"http://www.co.washington.or.us/DistrictAttorney/ChildSupport/index.cfm"},{"Free"},{"Establish Paternity, Establish a Child Support or Medical Support Order, Modify a Child Support Order, Collect and Account for Child Support"},{""},{"Open 8AM-5PM Monday-Friday."},{"Legal Support", "Child Support"},{"150 N 1st Ave. Suite 300"},{"97124"},{"Hillsboro"}, {"OR"},{"5038468759"},{"Families with Children"},{"English", "Spanish"}},
                {{"Washington County Juvenile Department"},{""},{"http://www.co.washington.or.us/Juvenile/HarkinsHouse/"},{"Free"},{"Aide for custody investigations"},{""},{"Mediation available to residents of Washington County with children who currently have a open domestic relations case. Two sessions are free, $80 per session after. Appointments are offered Mondays 830AM-1015AM. Counseling available for marital/couples issues, co-parenting concerns and separation/divorce. Must be a Washington County resident with children. First four sessions are free, then $80 fee per session applies."},{"Temporary residential care", "divorce counseling", "mediation"},{"24 W Main St."},{"97123"},{"Hillsboro"}, {"OR"},{"5038468766"},{"Ages 12-17"},{"English", "Spanish", "interpreters available"}},
                {{"Washington County Mental Health Services"},{"vikki_moore@co.washington.or.us"},{"http://www.co.washington.or.us/hhs"},{"Free"},{"Interpreters"},{""},{"In order to receive treatment you must have a mental health disorder that is covered by the Oregon Health Plan that negatively impacts your ability fo function at home or in the community, or be a low income Washington County resident who is uninsured. If a clinician is not available who speaks your language an interpreter will be provided."},{"Crisis Line", "Counseling", "Education", "Womens Health", "Family Planning", "Residential and Outpatient Mental Health Services"},{"155 N 1st Ave. Suite 250"},{"97124"},{"Hillsboro"}, {"OR"},{"5038462815"},{"Adults", "Children", "Individuals"},{"All"}},
                {{"Western Psychological and Counseling"},{"emilyh@westernpsychservices.com"},{"http://www.westernpsych.com/"},{"Accepts OHP, Reduced Cost"},{"Group and individual therapy. Classes and workshops."},{""},{""},{"Outpatient Mental Health", "Autism Program", "School-Based Programs"},{"21210 NW Mauzey Rd"},{"97124"},{"Hillsboro"}, {"OR"},{"5034399531"},{"Individual", "Family", "Group", "Adults", "Children"},{"English"}},
                {{"Western Pyschological Counseling"},{""},{"westernpsych.com"},{""},{""},{""},{""},{"Counseling"},{""},{""},{""},{""},{"5032355405"},{""},{""}},
                {{"Westside Community Church - Food Brigade"},{""},{""},{"Free"},{""},{""},{"Open T 6:30pm-8:00pm, Sat. 9:30am-11:00am"},{"Food Pantry"},{"18390 SW Farmington Rd."},{"97007"},{"Beaverton"},{"OR"},{"5038770256"},{""},{""}},
                {{"Womens Counseling Center"},{""},{""},{""},{""},{""},{""},{""},{""},{""},{""},{""},{"5032354050"},{""},{""}},
                {{"Worksource Oregon"},{""},{"http://www.worksourceoregon.org"},{"Free"},{""},{""},{"Open 8AM-5PM Monday-Friday. Language services available at no cost upon request."},{"Job Assistance"},{"7995 SW Mohawk St."},{"97062"},{"Tualatin"}, {"OR"},{"5036124200"},{"Disabled Veterans", "Adults"},{"All"}},
                {{"WorkSource Oregon"},{""},{"worksourceoregon.org"},{"Free"},{""},{""},{""},{"Job Assistance Center"},{"241 SW Edgeway Dr."},{"97006"},{"Beaverton"},{"OR"},{"5035262700"},{""},{""}},
                {{"YMCA of Columbia-Willamette"},{"beavertonhoop@ymca.org"},{""},{""},{""},{""},{""},{"Youth Center"},{"9685 SW Harvest"},{"97005"},{"Beaverton"},{"OR"},{"5036442191"},{""},{""}},
                {{"YMCA of Columbia-Willamette"},{"sherwood@ymcaw.org"},{"ymcacw.org"},{""},{""},{""},{""},{"Youth Center"},{"23000 SW Pacific Hwy."},{"97140"},{"Sherwood"},{"OR"},{"5036259622"},{""},{""}},
                {{"Youth Contact Incorporated"},{"youthcontact@youthcontact.org"},{"http://www.youthcontact.org"},{"Free"},{"Literacy Programs, Health Insurance Information"},{""},{"Must be in a public school in Washington County for Drop Out Program. OHP and some private insurances accepted. Some free services available for uninsured children and youth up to age 19. Food pantry open Tuesday/Wednesday 130PM-630PM."},{"Dropout Programs", "Counseling", "Food Pantry", "Court Ordered Parenting Programs"},{"447 SE Baseline St."},{"97123"},{"Hillsboro"},{"OR"},{"5036404222"},{"Youth"},{"English"}}};

        Log.d("testing","git");
                //{{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}}};
        //{{"Willsonvile Community"},{""},{"http://wilsonvillecommunitysharing.org/Home/tabid/41/Default.aspx"},{"Free"},{""},{""},{"Food bank hours: Tuesday 1PM-3PM, 6PM-8PM. You can visit the food bank once montly and prior to taking your first food from the bank you must show proof of residence in Wilsonville. Determination on assistance with utilities and perscriptions are determined based on income and require a meeting with a information and referral specialist. Office hours for these meetings are 9AM-3PM Monday-Friday."},{"Shelter", "Food Assistance", "Utilities Assistance", "Perscription Assistance", "Rent Assistance"},{"28925 SW Boberg Rd."},{"97070"},{"Wilsonville"}, {"OR"},{"5036826939"},{"Homeless", "Low Income", "Individuals", "Families"},{"English"}}};
        //{{"Virginia Garcia Memorial Health Center"},{"info@vgmhc.org"},{"http://virginiagarcia.org/locations/hillsboro"},{"Accepts OHP, Sliding Scale"},{"Primary health care, Preventative and Restorative dental care for children, acute dental services for adults, aide with applying for CoverOregon benefits"},{""},{"Open Monday-Friday 8AM-7PM, Exceptions are the 1st, 3rd, and 5th Wednesday of the month from 1PM-2PM. Sliding scale available to verifiable low income and traditional Medicare copays. Additionally influenced by family size."},{"Community Clinic", "Dental Clinic", "Mental Health Services"},{"226 SE 8th Ave."},{"97123"},{"Hillsboro"}, {"OR"},{"5036017400"},{"Migrant and Seasonal Farm Workers", "Children up to age 21", "Low Income"},{"English", "Spanish"}}
        //{{"SVDP St. Pius X (Cedar Mill)"},{""},{""},{"Free"},{""},{""},{"Open M 2:00-4:00pm, W 2:00-4:00pm, F 2:00-4:00pm.  Please call for appointment."},{"Food Pantry"},{"1170 NW 123rd Ave."},{"97229"},{"Portland"},{"OR"},{"5035919025"},{""},{""}}
        //{{"SVDP St. Cecilia"},{""},{""},{"Free"},{""},{""},{"Open Mon 9:00 am-12:00 pm and 6:00 pm-7:30 pm, Tues 9:00 am-12:00 pm and 6:00 pm-7:30 pm, Wed 9:00 am-12:00 pm and 6:00 pm-7:30 pm, Thurs 9:00 am-12:00 pm and 6:00 pm-7:30 pm, Fri 9:00 am-12:00 pm.  Please call for appointment."},{"Food Pantry"},{"5120 SW Franklin Ave."},{"97005"},{"Beaverton"},{"OR"},{"5035919025"},{""},{""}
        //{{"Sunset Prebsyterian Church - Helping Hands"},{""},{""},{"Free"},{""},{""},{"Open W 9:30am-12:00pm, Th 4:00-6:30pm.  Please call for appointment. Closed 12/21-1/3 for the holidays"},{"Food Pantry"},{"14986 NW Cornell Rd."},{"97229"},{"Portland"},{"OR"},{"5035919025"},{""},{""}}
        //{{"St. Lutheran SCAT"},{""},{""},{"Free"},{""},{""},{"Open M 6:30-7:30pm, T 5:00-6:00pm, W 6:30-7:30pm, Th 11:30am-12:30pm and 6:30-7:30pm.  Open last Sun. 1:00pm-2:00pm.  Not open first Wed. of the month"},{"Food Pantry"},{"10390 SW Canyon Rd."},{"97005"},{"Beaverton"},{"OR"},{"5035919025"},{""},{""}}};
        //{{"St. Francis Social Action"},{"jennifer@stfrancissherwood.org"},{"http://www.stfrancissherwood.org"},{""},{""},{"St. Francis provides a food pantry and a free weekly community meal. The Clothes Closet provides clothing and shoes for children and adults. All items are in good condition and organized by size or description."},{""},{"Food Pantry"},{"15659 SW Oregon Street"}, {"Sherwood"},{"97140"},{"OR"},{"5036257067"},{"Low-income residents of Sherwood", "Tualatin and Tigard"},{""}},
        //{{"Salvation Army Veterans and Family Center"},{"savets@usw.salvationarmy.org"},{"cascade.salvationarmy.org/veterans_and_family_center"},{""},{""},{""},{"Must be referred from the VA."},{"Addiction Recovery", "Shelter", "Domestic Violence", "Food Assistance", "Teen Pregnancy", "Senior Services"},{"14825 SW Farmington"},{"97007"},{"Beaverton"},{"OR"},{"5032931259"},{"Veterans and families"},{""}},
        //{{"Pacific University Counseling"},{"john.monahan@pacificu.edu"},{"http://www.pacificu.edu/our-resources/clinics/pacific-psychology-comprehensive-health-clinic"},{"Accepts OHP, Reduced Cost"},{"Outpatient mental health counseling for Groups, Individuals, Couples, Families, Children"}{""},{"Proof of income required for reduced fees, yearly income and number of dependents are main criteria in determining session fees. General fees are $50 for an intake session $20 for individual sessions and $10 for group sessions. Students and Veterans are offered services at $10 per session. Copays are additionally eligible for discounts based on need. No crisis intake. No primary alcohol/drug counseling or conservator/guardian evaluations. Accepts OHP for Washington County. Open Monday-Thursday 9AM-8PM, Fridays 9AM-5PM. $600 flat rate for comprehensive assessments and $350 for abreviated assessments such as ADD & ADHD. National College of Naturopathic Medicine offers appointments through our clinic for naturopathic services and medication management, first appointments are $40 and follow up appointments are $20."},{"Counseling", "Mental Health Evaluation"},{"222 SE 8th Ave. Suite 212"},{"97123"},{"Hillsboro"},{"OR"},{"5033527333"},{"Adults", "Children", "Families", "Couples", "Individuals"},{"English", "Spanish"}}
        //{{"Pacific Psychology and Comprehensive Health Clinic"},{""},{""},{"Sliding scale"},{"Assessment services, naturopaths"},{""},{""},{"Counseling"},{""},{""},{""},{""},{"5033527333"},{"child", "adolescent", "adult", "couples"},{"English", "Spanish"}}
        //{{"Murray Hills Christian Church"},{""},{""},{"Free"},{""},{""},{"Open M 3:30pm-5:30pm, W 9:30am-12:00pm, Sat. 10:00am-2:00pm.  Call for appointment."},{"Food Pantry"},{"15050 SW Weir Rd."},{"97007"},{"Beaverton"},{"OR"},{"5035919025"},{""},{""}}};
        //{{"Lifeworks NW"},{"intake@lifeworksnw.org"},{"http://www.lifeworksnw.org"},{"Accepts OHP"},{"Reduced Fees, Some Free (Prevention) Services"},{"Individual, Family and Group therapies"},{""},{"Mental Health Counseling", "Evaluations", "After School Programs", "Education"},{"14600 NW Cornell Rd"},{"97229"},{"Portland"}, {"OR"},{"5036459010"},{"Children", "Adults", "Adolescents", "Families", "Older Adults"},{"English", "Spanish"}}
        //{{"Lifeworks NW"},{""},{"lifeworksnw.org"},{""},{""},{""},{""},{"Counseling"},{""},{""},{""},{"5036459010","800-546-1666"},{""},{""}}
        //{{"Homeplate"},{"info@homeplateyouth.org"},{"homeplateyouth.org"},{"Free"},{"Hot showers, hygiene supplies, To go food, clothes, sleeping bags/camping support"},{""},{""},{"Drop In Center"},{"1841 SW Merlo Dr."},{"97006"},{"Beaverton"},{"OR"},{"5038674954"},{"Youth ages 12-24 (and their children)"},{""}}
        //{{"Holy Trinity Community Outreach"},{""},{""},{"Free"},{""},{""},{"Open T, W, F: 10am-12:30pm. Please call for appointment."},{"Food Pantry"},{"13715 SW Walker Rd."},{"97005"},{"Beaverton"},{"OR"},{"5035919025"},{""},{""}}
        //{{"Family Promise"},{"familypromiseannieheart@gmail.com"},{"http://www.Familypromisewashingtoncountyoregon.org"},{"Free"},{""},{""},{"Must call 503-640-3263 to be added to the wait list for shelter."},{"Shelter", "Meals"},{"183 SE 6th Ave."},{"97123"},{"Hillsboro"},{"OR"},{"5036403263"},{"Families with children only"},{"English"}},
        //{{"Ecumenical Ministries of Oregon"},{""},{""},{""},{""},{""},{""},{"Housing", "Food Assistance", "Refugee & Immigration Services", "Russian Oregon Social Services"},{""},{""},{""},{"OR"},{"-2"},{""},{""}}
        //{{"Depression/Bi-Polar Support"},{""},{""},{""},{""},{""},{""},{"Support Group"},{""},{""},{""},{""},{"50364401679"},{"Individuals", "families", "friends"},{""}}
        //{{"Community Counseling Center"},{""},{""},{""},{""},{""},{""},{"Counseling"},{""},{""},{""},{""},{"5027524620"},{"Individuals", "couples", "families"},{""}},
        // {{"Community Action"},{"contact@caowash.org"},{"http://caowash.org/what_we_do/housing.html"},{"Free"},{"Support services for Veteran Families, Aide with WIC and OHP enrollment, Classes, Family Advocacy"},{""},{"Must call 503-640-3263 to be added to the wait list for shelter. Clients have to request an appointment for assitance benefits via the website, response time is 1-2 business days. Food boxes are provided to anyone in Washington County, hours are Monday-Friday from 530PM-9PM and weekends 9AM-9PM. Rent and Utilities Assistance is based on income guildelines set by state averages: http://caowash.org/what_we_do/energy-rent-assistance/rent-assistance.html. Current bill, or 72-hour eviction notice, or a new lease agreement in the month you are requesting assistance must be provided at time of application."},{"Shelter, Emergency Rent Assistance, Energy Assistance, Assitance with Benefit Enrollment"},{"210 SE 12th Ave."},{"97123"},{"Hillsboro"},{"OR"},{"5036403263"},{"Chronically homeless persons with persistent mental illness", "Adults", "Families", "Veterans", "Families of Veterans", "Transitional housing for women and children"},{"English","Spanish"}}};
        // {{"Care to Share"},{"info@caretosharehelp.org"},{"caretosharehelp.org"},{"Free"},{"Financial Aid for Utilities.  Please call 503-726-0407 for utilities and 503-924-3129 for water."},{""},{"Must live in the following zip codes: 97003, 97005, 97006, 97007, 97008, 97078, 97123, 97124,  97223, 97225 and 97229."},{"Food, Utilities Assistance"},{"PO Box 397"},{"97075"},{"Beaverton"},{"OR"},{"5035919025"},{"Various food pantries throughout the area.  Call for services near you."},{""}},
        //{{"Bright Now Dental Wilsonville"},{""},{"http://brightnow.com"},{"Reduced Fees and Discounts, 20% or more off services"},{""},{""},{"Union discounts of 40% or more for covered unions. 20% or more off all services for those who need financial assistance and do not have insurance."},{"Low Cost Dental Care"},{"7206 NE Cornell Rd"},{"97124"},{"Hillsboro"},{"OR"},{"5036409999"},{},{}}};
        //{{"Bienestar"},{"elmpark@cascade-management.com"},{"http://bienestar-or.org/web/housing/elmpark-forestgrove.asp"},{""},{"Located at two sites in Forest Grove. Provides affordable rental housing for farm workers and low-income working families. Resident services programs are offered that include help with resources and referrals (TENANTS ONLY), GED and computer classes, job club, community gardens and activities for children.. They also offer a \"Promotores\" (Community Connectors) program for residents."},{""},{""},{"Housing"},{"2350 and 2351 Elm Street"},{"97116"},{"Forest Grove"},{"OR"},{""},{},{}}};
        //{{"Bethel Congregation"},{""},{"faithcafeor.org"},{"Free"},{"Meals Sun 5pm and last Thursday of the month at 5pm."},{""},{"Open M 11:30am-12:30pm, W 6:30pm-7:30pm, Th 11;30-12:30PM.  Call for apt."},{"Food Pantry","Meals"},{"5150 SW Watson Ave."},{"97005"},{"Beaverton"},{"OR"},{"5035919025"},{""},{""}}};
        for (int i = 0; i < Organizations.length; ++i) {
            Log.e("I",String.valueOf(i));
            // Inserts a row into the Organization field
            dbContext.execSQL("INSERT INTO " + TABLE_ORGANIZATION + " ( " + FIELD_ORG_NAME + ", "
                    + FIELD_ORG_EMAIL + ", " + FIELD_ORG_WEBSITE + ", " + FIELD_ORG_COST + ", "
                    + FIELD_ORG_ADDITIONAL + ", " + FIELD_ORG_ASSISTANCE + ", " +
                    FIELD_ORG_OTHER + ") " + " VALUES ( '" + Organizations[i][0][0] + "', '" + Organizations[i][1][0] + "', '" +
                    Organizations[i][2][0] + "', '" + Organizations[i][3][0] + "', '" + Organizations[i][4][0] + "', '" + Organizations[i][5][0] + "', '" + Organizations[i][6][0] +
                    "');");
            Cursor maxKey = dbContext.rawQuery("SELECT max(oid) FROM Organization", null);
            if (maxKey.moveToFirst()) {
                Integer maximum = maxKey.getInt(0);
                maxKey.close();
                for (int j = 0; j < Organizations[i][7].length; ++j) {
                    // If necessary, inserts a row into the Category table
                    Cursor category = dbContext.rawQuery("SELECT " + FIELD_CAT_cName + " FROM " +
                            TABLE_CATEGORY + " AS C WHERE C." + FIELD_CAT_cName +
                            " LIKE '" + Organizations[i][7][j] + "';", null);
                    if (category.moveToFirst() == false) {
                        dbContext.execSQL("INSERT INTO " + TABLE_CATEGORY + " ( " +
                                FIELD_CAT_cName + ") " + "VALUES ( '" +
                                Organizations[i][7][j] + "');");
                    }
                    category.close();
                    // Inserts a new member into the Falls in table connecting an organization to its
                    // its category
                    dbContext.execSQL("INSERT INTO " + TABLE_FALLS_IN + " ( " + RELATION_FALLS_IN_OID + ", "
                            + RELATION_FALLS_IN_CNAME + ") VALUES ( " + maximum.toString() + ", '"
                            + Organizations[i][7][j] + "');");
                }
                for (int j = 0; j < Organizations[i][8].length; ++j) {
                    // Inserts the Address informaton into the Address table
                    dbContext.execSQL("INSERT INTO " + TABLE_LOCATION + " ( " + FIELD_LOC_OID + "," +
                            FIELD_LOC_STREET + ", " +
                            FIELD_LOC_ZIP + ", " + FIELD_LOC_CITY + ", " + FIELD_LOC_STATE + ") "
                            + "VALUES ( " + maximum.toString() + ", '" + Organizations[i][8][j] + "', '" + Organizations[i][9][j]
                            + "', '" + Organizations[i][10][j] + "', '" + Organizations[i][11][j] + "');");
                }
                for (int j=0; j < Organizations[i][12].length; ++j) {
                    // Inserts the phone number into the Phone Table
                    dbContext.execSQL("INSERT INTO " + TABLE_PHONE + " ( " + FIELD_ORG_OID + ", " +
                            FIELD_PHONE_PHONENUM + ") VALUES ( " + maximum.toString() + ", '" + Organizations[i][12][j]
                            + "');");
                }
                for (int j = 0; j < Organizations[i][13].length; ++j) {
                    // If necessary, inserts a row into the Category table
                    Cursor population = dbContext.rawQuery("SELECT " + FIELD_POP_TYPE + " FROM " +
                            TABLE_POPULATION + " AS P WHERE P." + FIELD_POP_TYPE +
                            " LIKE '" + Organizations[i][13][j] + "';", null);
                    if (population.moveToFirst() == false) {
                        dbContext.execSQL("INSERT INTO " + TABLE_POPULATION + " ( " +
                                FIELD_POP_TYPE + ") " + "VALUES ( '" +
                                Organizations[i][13][j] + "');");
                    }
                    population.close();
                    // Inserts a new member into the Falls in table connecting an organization to its
                    // its category
                    dbContext.execSQL("INSERT INTO " + TABLE_SERVES_PEOPLE + " ( " + RELATION_SERVESP_OID +
                            ", " + RELATION_SERVESP_TYPE + ") VALUES ( " + maximum.toString() + ", '"
                            + Organizations[i][13][j] + "');");
                }

                for(int j = 0; j < Organizations[i][14].length; ++j) {
                    // If necessary, inserts a row into the Language table
                    Cursor language = dbContext.rawQuery("SELECT " + FIELD_LAN_LTYPE + " FROM " +
                            TABLE_LANGUAGE + " AS L WHERE L." + FIELD_LAN_LTYPE +
                            " LIKE '" + Organizations[i][14][j] + "';", null);
                    if (language.moveToFirst() == false) {
                        dbContext.execSQL("INSERT INTO " + TABLE_LANGUAGE + " ( " +
                                FIELD_LAN_LTYPE + ") " + "VALUES ( '" +
                                Organizations[i][14][j] + "');");
                    }
                    language.close();
                    // Inserts a new member into the Falls in table connecting an organization to its
                    // its category
                    dbContext.execSQL("INSERT INTO " + TABLE_SERVESL + " ( " + RELATION_SERVES_LAN_OID +
                            ", " + RELATION_SERVES_LAN_LTYPE + ") VALUES ( " + maximum.toString() + ", '"
                            + Organizations[i][14][j] + "');");
                }
            }
        }
        //dbContext.close();

/*
        ContentValues insertValues = new ContentValues ();
        insertValues.put(KEY_ID, 1); // Hard code key_id
        insertValues.put(KEY_NAME, "Situps");
        insertValues.put(KEY_CODE, "Alpha");
        insertValues.put(KEY_DESC, "10 Situps");
        insertValues.put(KEY_CRT_ON, (new Date()).toString()); // TODO: Check to see if this formats correctly in the database.

        dbContext.insert(TABLE_EXERCISES, null, insertValues);

        insertValues.put(KEY_ID, 2); // Hard code key_id
        insertValues.put(KEY_NAME, "Pushups");
        insertValues.put(KEY_CODE, "Beta");
        insertValues.put(KEY_DESC, "10 Pushups");
        insertValues.put(KEY_CRT_ON, (new Date ()).toString()); // TODO: Check to see if this formats correctly in the database.

        dbContext.insert(TABLE_EXERCISES, null, insertValues);

        insertValues.put(KEY_ID, 3); // Hard code key_id
        insertValues.put(KEY_NAME, "Run");
        insertValues.put(KEY_CODE, "Gamma");
        insertValues.put(KEY_DESC, "100 Strides of running");
        insertValues.put(KEY_CRT_ON, (new Date ()).toString()); // TODO: Check to see if this formats correctly in the database.

        dbContext.insert(TABLE_EXERCISES, null, insertValues);

        insertValues.put(KEY_ID, 4); // Hard code key_id
        insertValues.put(KEY_NAME, "Break");
        insertValues.put(KEY_CODE, "Delta");
        insertValues.put(KEY_DESC, "2 minute break");
        insertValues.put(KEY_CRT_ON, (new Date ()).toString()); // TODO: Check to see if this formats correctly in the database.

        dbContext.insert(TABLE_EXERCISES, null, insertValues);
*/
    }

    @Override
    public void onOpen (SQLiteDatabase dbContext) {
        super.onOpen(dbContext);
        dbContext.execSQL("PRAGMA foreign_keys = ON;");
    }

    @Override
    public void onUpgrade (SQLiteDatabase dbContext, int oldVersion, int newVersion) {
        dbContext.execSQL("DROP TABLE " + TABLE_SERVESL + ";");
        dbContext.execSQL("DROP TABLE " + TABLE_LANGUAGE + ";");
        dbContext.execSQL("DROP TABLE " + TABLE_LOCATION + ";");
        dbContext.execSQL("DROP TABLE " + TABLE_FALLS_IN + ";");
        dbContext.execSQL("DROP TABLE " + TABLE_CATEGORY + ";");
        dbContext.execSQL("DROP TABLE " + TABLE_PHONE + ";");
        dbContext.execSQL("DROP TABLE " + TABLE_SERVES_PEOPLE + ";");
        dbContext.execSQL("DROP TABLE " + TABLE_POPULATION + ";");
        dbContext.execSQL("DROP TABLE " + TABLE_ORGANIZATION + ";");
        onCreate(dbContext);
    }


    // Adding new Organization
    public void addOrganization (String data[]) {
        Log.e("Test", "What are you waiting for?");
        SQLiteDatabase dbContext = this.getWritableDatabase();
        // If the data member is empty, we set it to null
        Log.e("Length Data", (new Integer(data.length)).toString());
        // If no Zip is entered, replaces data[9] with null
        if (Integer.getInteger(data[9]) == null)
        {
            data[9] = null;
        }
        if (Integer.getInteger(data[12]) == null)
        {
            data[12] = null;
        }

        // Inserts a row into the Organization field
        dbContext.execSQL("INSERT INTO " + TABLE_ORGANIZATION + " ( " + FIELD_ORG_NAME + ", "
                + FIELD_ORG_EMAIL + ", " + FIELD_ORG_WEBSITE + ", " + FIELD_ORG_COST + ", "
                + FIELD_ORG_ADDITIONAL + ", " + FIELD_ORG_ASSISTANCE + ", " +
                FIELD_ORG_OTHER + ") " + " VALUES ( '" + data[0] + "', '" + data[1] + "', '" +
                data[2] + "', '" + data[3] + "', '" + data[4] + "', '" + data[5] + "', '" + data[6] +
                "');");
        Cursor maxKey = dbContext.rawQuery("SELECT max(oid) FROM Organization", null);
        if (maxKey.moveToFirst())
        {
            Integer maximum = maxKey.getInt(0);
            maxKey.close();
            // If necessary, inserts a row into the Category table
            Cursor category = dbContext.rawQuery("SELECT " + FIELD_CAT_cName + " FROM " +
                                                 TABLE_CATEGORY + " AS C WHERE C." +FIELD_CAT_cName +
                                                 " LIKE '" + data[7] + "';",null);
            if (category.moveToFirst() == false) {
                dbContext.execSQL("INSERT INTO " + TABLE_CATEGORY + " ( " +
                        FIELD_CAT_cName + ") " + "VALUES ( '" +
                        data[7] + "');");
            }
            category.close();
            // Inserts a new member into the Falls in table connecting an organization to its
            // its category
            dbContext.execSQL("INSERT INTO " + TABLE_FALLS_IN + " ( " + RELATION_FALLS_IN_OID + ", "
                              + RELATION_FALLS_IN_CNAME + ") VALUES ( " + maximum.toString() + ", '"
                              + data[7] + "');");
            Cursor test = dbContext.rawQuery("SELECT * FROM " + TABLE_FALLS_IN,null);
            if (test.moveToFirst())
            {
                Log.e("Falls In", test.getString(0) + ", " + test.getString(1));
                while (test.move(1))
                {
                    Log.e("Falls In", test.getString(0) + ", " + test.getString(1));
                }
            }
            // Inserts the Address informaton into the Address table
            dbContext.execSQL("INSERT INTO " + TABLE_LOCATION + " ( " +FIELD_LOC_OID +"," +
                              FIELD_LOC_STREET + ", " +
                              FIELD_LOC_ZIP + ", " + FIELD_LOC_CITY + ", " + FIELD_LOC_STATE + ") "
                              + "VALUES ( " + maximum.toString() + ", '" + data[8] + "', " + data[9]
                              + ", '" + data[10] + "', '" + data[11] + "');");

            // Inserts the phone number into the Phone Table
            if (data[12] != null) {
                dbContext.execSQL("INSERT INTO " + TABLE_PHONE + " ( " + FIELD_ORG_OID + ", " +
                        FIELD_PHONE_PHONENUM + ") VALUES ( " + maximum.toString() + ", '" + data[12]
                        + "');");
            }
            Log.e("1","Where?");
            // If necessary, inserts a row into the Category table
            Cursor population = dbContext.rawQuery("SELECT " + FIELD_POP_TYPE + " FROM " +
                    TABLE_POPULATION + " AS P WHERE P." +FIELD_POP_TYPE +
                    " LIKE '" + data[13] + "';",null);
            if (population.moveToFirst() == false) {
                dbContext.execSQL("INSERT INTO " + TABLE_POPULATION + " ( " +
                        FIELD_POP_TYPE + ") " + "VALUES ( '" +
                        data[13] + "');");
            }
            Log.e("2", "Oh Where?");
            population.close();
            // Inserts a new member into the Falls in table connecting an organization to its
            // its category
            dbContext.execSQL("INSERT INTO " + TABLE_SERVES_PEOPLE + " ( " + RELATION_SERVESP_OID +
                    ", " + RELATION_SERVESP_TYPE + ") VALUES ( " + maximum.toString() + ", '"
                    + data[13] + "');");
            Log.e("3", "Have We ");
            // If necessary, inserts a row into the Category table
            Cursor language = dbContext.rawQuery("SELECT " + FIELD_LAN_LTYPE + " FROM " +
                    TABLE_LANGUAGE + " AS L WHERE L." +FIELD_LAN_LTYPE +
                    " LIKE '" + data[14] + "';",null);
            if (language.moveToFirst() == false) {
                dbContext.execSQL("INSERT INTO " + TABLE_LANGUAGE + " ( " +
                        FIELD_LAN_LTYPE + ") " + "VALUES ( '" +
                        data[14] + "');");
            }
            Log.e("4","Failed");
            language.close();
            // Inserts a new member into the Falls in table connecting an organization to its
            // its category
            dbContext.execSQL("INSERT INTO " + TABLE_SERVESL + " ( " + RELATION_SERVES_LAN_OID +
                    ", " + RELATION_SERVES_LAN_LTYPE + ") VALUES ( " + maximum.toString() + ", '"
                    + data[14] + "');");

            dbContext.close();
        }
    }

    // Populates the internal database from a database stored on line to be continued

    // Gets all Organizations
    public void getOrganizations()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM Organization", null);
        if (results.moveToFirst())
        {
            Log.e("ID",results.getString(0));
            Log.e("Name",results.getString(1));
            while (results.move(1))
            {
                Log.e("ID",results.getString(0));
                Log.e("Name",results.getString(1));
            }
            results.close();
        }
        db.close();
    }

    // Queries the DataBase and returns a list of organizations with descriptions
    // Satisfying the Query.
    public Cursor[] search(String data[])
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results[] = new Cursor[6];
        String innerProduct = TABLE_ORGANIZATION + " O";
        String whereClause = "";
        boolean hasLoc = false;
//        Log.e("Data Length",String.valueOf(data.length));
        for (int i = 0; i < data.length; ++i)
        {
            Log.e("HERE","HERE");
            Log.e("Data[i]",data.toString());
            if ((data[i].length() > 0))
            {
                if (i == 1)
                {
                    innerProduct =  innerProduct + ", " + TABLE_CATEGORY + " C";
                    innerProduct = innerProduct + ", " + TABLE_FALLS_IN + " F";
                    whereClause = whereClause + " AND O." + FIELD_ORG_OID + " = " + " F." + RELATION_FALLS_IN_OID;
                    whereClause = whereClause + " AND F." + RELATION_FALLS_IN_CNAME + " = " + " C." + FIELD_CAT_cName;
                    whereClause = whereClause + " AND C." + FIELD_CAT_cName + " LIKE '%" + data[i] + "%'";
                }
                if ( (i >= 2)&&( i <= 4 )&&(!hasLoc))
                {
                    whereClause = whereClause + " AND O." + FIELD_ORG_OID + " = " + " LOC." + FIELD_ORG_OID;
                    innerProduct = innerProduct + ", " + TABLE_LOCATION + " LOC";
                    hasLoc = true;
                }
                if ( i == 2)
                {
                    whereClause = whereClause + " AND LOC." + FIELD_LOC_ZIP + " LIKE '%" + data[i] + "%'";
                }
                if ( i == 3)
                {
                    whereClause = whereClause + " AND LOC." + FIELD_LOC_CITY + " LIKE '%" + data[i] + "%'";
                }
                if ( i == 4)
                {
                    whereClause = whereClause + " AND LOC." + FIELD_LOC_STATE + " LIKE '%" + data[i] + "%'";
                }
                if ( i == 6 )
                {
                    innerProduct = innerProduct + ", " + TABLE_POPULATION + " P";
                    innerProduct = innerProduct + ", " + TABLE_SERVES_PEOPLE + " SP";
                    whereClause = whereClause + " AND SP." + RELATION_SERVESP_OID + " = " + " O." + FIELD_ORG_OID;
                    whereClause = whereClause + " AND SP." + RELATION_SERVESP_TYPE + " = " + " P." + FIELD_POP_TYPE;
                    whereClause = whereClause + " AND P." + FIELD_POP_TYPE + " LIKE '%" + data[i] + "%'";
                }
                if ( i == 7 )
                {
                    innerProduct = innerProduct + ", " + TABLE_LANGUAGE + " L";
                    innerProduct = innerProduct + ", " + TABLE_SERVESL + " SL";
                    whereClause = whereClause + " AND SL." + RELATION_SERVES_LAN_OID + " = " + " O." + FIELD_ORG_OID;
                    whereClause = whereClause + " AND SL." + RELATION_SERVES_LAN_LTYPE + " = " + " L." + FIELD_LAN_LTYPE;
                    whereClause = whereClause + " AND L." + FIELD_LAN_LTYPE + " LIKE '%" + data[i] + "%'";
                }
            }
        }
        Log.e("Querry","SELECT O." +FIELD_ORG_OID + ", O." + FIELD_ORG_NAME +
                ", O." + FIELD_ORG_EMAIL + ", O." + FIELD_ORG_WEBSITE +
                ", O." + FIELD_ORG_COST + ", O." + FIELD_ORG_ADDITIONAL
                + ", O." + FIELD_ORG_ASSISTANCE + ", O." + FIELD_ORG_OTHER
                + " FROM " + innerProduct + " WHERE O."
                + FIELD_ORG_NAME + " LIKE '%" + data[0] + "%'" +
                " AND O." + FIELD_ORG_COST + " LIKE '%" + data[5]
                + "%'" + whereClause + ";");
        Cursor Organization =   db.rawQuery("SELECT O." +FIELD_ORG_OID + ", O." + FIELD_ORG_NAME +
                                            ", O." + FIELD_ORG_EMAIL + ", O." + FIELD_ORG_WEBSITE +
                                            ", O." + FIELD_ORG_COST + ", O." + FIELD_ORG_ADDITIONAL
                                            + ", O." + FIELD_ORG_ASSISTANCE + ", O." + FIELD_ORG_OTHER
                                            + " FROM " + innerProduct + " WHERE O."
                                            + FIELD_ORG_NAME + " LIKE '%" + data[0] + "%'" +
                                            " AND O." + FIELD_ORG_COST + " LIKE '%" + data[5]
                                            + "%'" + whereClause + ";" ,null);
        Cursor Language = null;
        Cursor Population = null;
        Cursor Category = null;
        Cursor Location = null;
        Cursor Phone = null;
        if (Organization.moveToFirst())
        {
            Log.e("More","stuff");
            String sLang = " SL." + RELATION_SERVES_LAN_OID + " = " + Organization.getString(0) +
                           " AND SL." + RELATION_SERVES_LAN_LTYPE + " = " + " L." + FIELD_LAN_LTYPE;
            String sPop = " SP." + RELATION_SERVESP_OID + " = " + Organization.getString(0) +
                    " AND SP." + RELATION_SERVESP_TYPE + " = " + " P." + FIELD_POP_TYPE;
            String sCat = " F." + RELATION_FALLS_IN_OID + " = " + Organization.getString(0) +
                    " AND F." + RELATION_FALLS_IN_CNAME + " = " + " C." + FIELD_CAT_cName;
            String sLoc = " LOC." + FIELD_LOC_OID + " = " + Organization.getString(0);
            String sPhone = " P." + FIELD_ORG_OID + " = " + Organization.getString(0);
            Log.e("Die", "1");
            while (Organization.move(1))
            {
                Log.e("In","While");
                sCat = sCat + " AND F." + RELATION_FALLS_IN_OID + " = " + Organization.getString(0) +
                        " AND F." + RELATION_FALLS_IN_CNAME + " = " + " C." + FIELD_CAT_cName;
                sPop = sPop + " AND SP." + RELATION_SERVESP_OID + " = " + Organization.getString(0) +
                        " AND SP." + RELATION_SERVESP_TYPE + " = " + " P." + FIELD_POP_TYPE;
                sLang = sLang + " AND SL." + RELATION_SERVES_LAN_OID + " = " + Organization.getString(0) +
                " AND SL." + RELATION_SERVES_LAN_LTYPE + " = " + " L." + FIELD_LAN_LTYPE;
                sLoc = sLoc + " AND LOC." + FIELD_LOC_OID + " = " + Organization.getString(0);
                sPhone = sPhone + " AND P." + FIELD_ORG_OID + " = " + Organization.getString(0);
            }
            Category = db.rawQuery("SELECT C." + FIELD_CAT_cName + ", F." + RELATION_FALLS_IN_OID + " FROM " + TABLE_FALLS_IN + " F, " +
                    TABLE_CATEGORY + " C WHERE" + sCat,null);
            Population = db.rawQuery("SELECT P." + FIELD_POP_TYPE + ", SP." + RELATION_SERVESP_OID + " FROM " + TABLE_POPULATION + " P, " +
                    TABLE_SERVES_PEOPLE + " SP WHERE" + sPop,null);
            Language = db.rawQuery("SELECT L." + FIELD_LAN_LTYPE + ", SL." + RELATION_SERVES_LAN_OID + " FROM " + TABLE_LANGUAGE + " L, " +
                                          TABLE_SERVESL + " SL WHERE" + sLang,null);
            Location = db.rawQuery("SELECT LOC." + FIELD_LOC_STREET + ", LOC." + FIELD_LOC_CITY + ", LOC." +
                                   FIELD_LOC_STATE + ", LOC." + FIELD_LOC_ZIP + ", LOC." + FIELD_LOC_OID + " FROM " + TABLE_LOCATION + " LOC " +
                                   "WHERE" + sLoc, null);
            Phone = db.rawQuery("SELECT P." + FIELD_PHONE_PHONENUM + ", P." + FIELD_ORG_OID + " FROM " + TABLE_PHONE + " P WHERE" + sPhone, null);

        }
        results[0] = Organization;
        results[1] = Category;
        results[2] = Population;
        results[3] = Language;
        results[4] = Location;
        results[5] = Phone;
        return results;
    }

    public String[] searchOrg(String data)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor querryOut;
        String results[] = new String[13];
        for (int i = 0; i < 13; ++i)
        {
            results[i] = "";
        }
        // Gets stuff from main table
        querryOut = db.rawQuery("SELECT * FROM " + TABLE_ORGANIZATION + " O Where O." + FIELD_ORG_OID + " = " + data + ";",null);
        Log.e("and","SELECT * FROM " + TABLE_ORGANIZATION + " O Where O." + FIELD_ORG_OID + " = '" + data + "';");
        if(querryOut.moveToFirst()) {
            Log.e("1", querryOut.getString(0));
            Log.e("2", querryOut.getString(1));
            for (int i = 0; i < 7;++i)
            {
                results[i] = querryOut.getString(i+1);
            }
        }
        // Gets the phone
        querryOut = db.rawQuery("Select P." + FIELD_PHONE_PHONENUM + " FROM " + TABLE_PHONE + " P WHERE P." +FIELD_ORG_OID + " = " + data + ";",null);
        if(querryOut.moveToFirst())
        {
            results[7] = querryOut.getString(0);
        }
        else
        {
            results[7] = "-1";
        }
        // Gets the address
        querryOut = db.rawQuery("Select A." + FIELD_LOC_STREET + ", A." + FIELD_LOC_ZIP + ", A." + FIELD_LOC_CITY + ", A." + FIELD_LOC_STATE + " FROM " + TABLE_LOCATION + " A WHERE A." +FIELD_ORG_OID + " = " + data + ";",null);
        if(querryOut.moveToFirst())
        {
            results[8] = querryOut.getString(0);
            results[9] = querryOut.getString(1);
            results[10] = querryOut.getString(2);
            results[11] = querryOut.getString(3);
        }
        else
        {
            results[8] = "";
        }
        return results;
    };

    public String[] allCategories()
    {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct C." + FIELD_CAT_cName + " FROM " + TABLE_CATEGORY + " C;",null);
        if (output.moveToFirst())
        {
            resultsList.add(output.getString(0));
        }
        while(output.move(1))
        {
            resultsList.add(output.getString(0));
        }
        results = new String[resultsList.size()+1];
        results[0] = "";
        for (int i = 0; i < resultsList.size();++i)
        {
            results[i+1] = resultsList.get(i);
        }
        return results;
    };


  public String[] allOrganizations() {
    Log.e("allOrg","AllOrg");
    ArrayList<String> resultsList = new ArrayList<String>();
    String[] results;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor output = db.rawQuery("Select Distinct O." + FIELD_ORG_NAME + " FROM " + TABLE_ORGANIZATION + " O;", null);
    if (output.moveToFirst()) {
      resultsList.add(output.getString(0));
    }
    while (output.move(1)) {
      resultsList.add(output.getString(0));
    }
    results = new String[resultsList.size() + 1];
    results[0] = "";
    for (int i = 0; i < resultsList.size(); ++i) {
      results[i + 1] = resultsList.get(i);
    }
    return results;
  }

    public String[] allAges()
    {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct P." + FIELD_POP_TYPE + " FROM " + TABLE_POPULATION + " P;",null);
        if (output.moveToFirst())
        {
            resultsList.add(output.getString(0));
        }
        while(output.move(1))
        {
            resultsList.add(output.getString(0));
        }
        results = new String[resultsList.size()+1];
        results[0] = "";
        for (int i = 0; i < resultsList.size();++i)
        {
            results[i+1] = resultsList.get(i);
        }
        return results;
    };

    public String[] allZips()
    {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct L." + FIELD_LOC_ZIP + " FROM " + TABLE_LOCATION + " L;",null);
        if (output.moveToFirst())
        {
            resultsList.add(output.getString(0));
        }
        while(output.move(1))
        {
            resultsList.add(output.getString(0));
        }
        results = new String[resultsList.size()+1];
        results[0] = "";
        for (int i = 0; i < resultsList.size();++i)
        {
            results[i+1] = resultsList.get(i);
        }
        return results;
    };

    public String[] allCity()
    {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct L." + FIELD_LOC_CITY + " FROM " + TABLE_LOCATION + " L;",null);
        if (output.moveToFirst())
        {
            resultsList.add(output.getString(0));
        }
        while(output.move(1))
        {
            resultsList.add(output.getString(0));
        }
        results = new String[resultsList.size()+1];
        results[0] = "";
        for (int i = 0; i < resultsList.size();++i)
        {
            results[i+1] = resultsList.get(i);
        }
        return results;
    };

    public String[] allState()
    {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct L." + FIELD_LOC_STATE + " FROM " + TABLE_LOCATION + " L;",null);
        if (output.moveToFirst())
        {
            resultsList.add(output.getString(0));
        }
        while(output.move(1))
        {
            resultsList.add(output.getString(0));
        }
        results = new String[resultsList.size()+1];
        results[0] = "";
        for (int i = 0; i < resultsList.size();++i)
        {
            results[i+1] = resultsList.get(i);
        }
        return results;
    };

    public String[] allCost()
    {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct O." + FIELD_ORG_COST + " FROM " + TABLE_ORGANIZATION + " O;",null);
        if (output.moveToFirst())
        {
            resultsList.add(output.getString(0));
        }
        while(output.move(1))
        {
            resultsList.add(output.getString(0));
        }
        results = new String[resultsList.size()+1];
        results[0] = "";
        for (int i = 0; i < resultsList.size();++i)
        {
            results[i+1] = resultsList.get(i);
        }
        return results;
    };

    public String[] allLanguage()
    {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct L." + FIELD_LAN_LTYPE + " FROM " + TABLE_LANGUAGE + " L;",null);
        if (output.moveToFirst())
        {
            resultsList.add(output.getString(0));
        }
        while(output.move(1))
        {
            resultsList.add(output.getString(0));
        }
        results = new String[resultsList.size()+1];
        results[0] = "";
        for (int i = 0; i < resultsList.size();++i)
        {
            results[i+1] = resultsList.get(i);
        }
        return results;
    }
    /*
    // Getting single exercise
    public Exercise getExercise(int id) {
        SQLiteDatabase dbContext = this.getReadableDatabase ();
        Exercise cResult;
        Cursor dbCursor = dbContext.query(TABLE_EXERCISES, new String[]{KEY_ID,
                        KEY_NAME, KEY_CODE, KEY_DESC, KEY_CRT_ON}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (dbCursor != null) {
            dbCursor.moveToFirst ();
        }
        cResult = new Exercise (Integer.parseInt (dbCursor.getString (0)),
                dbCursor.getString (1), dbCursor.getString (2),
                dbCursor.getString (3), dbCursor.getString (4));

        return cResult;
    }

    // Getting All RoutineExercises
    public List<Exercise> getAllExercises() {
        List<Exercise> exerciseList = new ArrayList<Exercise>();

        String selectQuery = "SELECT * FROM " + TABLE_EXERCISES;

        SQLiteDatabase dbContext = this.getReadableDatabase ();
        Cursor dbCursor = dbContext.rawQuery (selectQuery, null);


        if (dbCursor.moveToFirst ())
        {
            do
            {
                Exercise exercise = new Exercise ();
                exercise.setId (Integer.parseInt (dbCursor.getString (0)));
                exercise.setName (dbCursor.getString (1));
                exercise.setCode (dbCursor.getString (2));
                exercise.setDescription (dbCursor.getString (3));
                exercise.setCreatedOn (dbCursor.getString (4));
                exerciseList.add (exercise);
            } while (dbCursor.moveToNext ());
        }

        return exerciseList;
    }

    // Does not work
    // Getting exercises Count
    public int getExerciseCount() {
        Integer temp;
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_EXERCISES;
        SQLiteDatabase dbContext = this.getReadableDatabase ();
        Cursor cursor = dbContext.rawQuery (countQuery, null);
        temp = Integer.parseInt (cursor.getString (0));
        cursor.close();


        return temp;
    }

    // Updating single exercise
    public int updateExercise(Exercise exercise) {
        SQLiteDatabase dbContext = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues ();
        updatedValues.put (KEY_NAME, exercise.getName ());
        updatedValues.put (KEY_CODE, exercise.getCode ());
        updatedValues.put(KEY_DESC, exercise.getDescription());
        updatedValues.put(KEY_CRT_ON, exercise.getCreatedOn());

        return dbContext.update(TABLE_EXERCISES, updatedValues, KEY_ID + " = ?",
                new String[] { String.valueOf (exercise.getId()) });
    }

    // Deleting single exercise
    public void deleteExercise(Exercise exercise) {
        SQLiteDatabase dbContext = this.getWritableDatabase();
        dbContext.delete(TABLE_EXERCISES, KEY_ID + " = ?",
                new String[]{String.valueOf(exercise.getId())});
        dbContext.close();

    }

    // Adding new routine
    public void addRoutine(Routine routine) {
        SQLiteDatabase dbContext = this.getWritableDatabase();

        ContentValues insertValues = new ContentValues ();
        insertValues.put (KEY_ID, routine.getId ()); // hard code ID
        insertValues.put (KEY_NAME, routine.getName ());
        insertValues.put (KEY_CODE, routine.getCode ());
        insertValues.put (KEY_DESC, routine.getDescription ());
        insertValues.put (KEY_CRT_ON, routine.getCreatedOn ().toString ());

        dbContext.insert (TABLE_ROUTINES, null, insertValues);
        dbContext.close ();
    }

    // Getting single routine
    public Routine getRoutine(int id) {
        SQLiteDatabase dbContext = this.getReadableDatabase ();
        Routine cResult;

        Cursor dbCursor = dbContext.query(TABLE_ROUTINES, new String[]{KEY_ID,
                        KEY_NAME, KEY_CODE, KEY_DESC, KEY_CRT_ON}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (dbCursor != null) {
            dbCursor.moveToFirst ();
        }

        cResult = new Routine (Integer.parseInt (dbCursor.getString (0)),
                dbCursor.getString (1), dbCursor.getString (2),
                dbCursor.getString (3), dbCursor.getString (4));

        return cResult;
    }

    // Getting All Routines
    public List<Routine> getAllRoutines() {
        List<Routine> routineList = new ArrayList<Routine> ();

        String selectQuery = "SELECT * FROM " + TABLE_ROUTINES;

        SQLiteDatabase dbContext = this.getReadableDatabase ();
        Cursor dbCursor = dbContext.rawQuery (selectQuery, null);


        if (dbCursor.moveToFirst ())
        {
            do
            {
                Routine routine = new Routine ();
                routine.setId (Integer.parseInt (dbCursor.getString (0)));
                routine.setName (dbCursor.getString (1));
                routine.setCode (dbCursor.getString (2));
                routine.setDescription (dbCursor.getString (3));
                routine.setCreatedOn (dbCursor.getString (4));
                routineList.add (routine);
            } while (dbCursor.moveToNext ());
        }

        return routineList;
    }

    // Does not work
    // Getting routines Count
    public int getRoutinesCount() {
        Integer temp;
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_ROUTINES;
        SQLiteDatabase dbContext = this.getReadableDatabase ();
        Cursor cursor = dbContext.rawQuery (countQuery, null);
        temp = Integer.parseInt (cursor.getString (0));
        cursor.close();

        return temp;
    }
    // Updating single routine
    public int updateRoutine(Routine routine) {
        SQLiteDatabase dbContext = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues ();
        updatedValues.put (KEY_NAME, routine.getName ());
        updatedValues.put (KEY_CODE, routine.getCode ());
        updatedValues.put(KEY_DESC, routine.getDescription());
        updatedValues.put(KEY_CRT_ON, routine.getCreatedOn());

        return dbContext.update(TABLE_ROUTINES, updatedValues, KEY_ID + " = ?",
                new String[] { String.valueOf (routine.getId()) });
    }

    // Deleting single routine
    public void deleteRoutine(Routine routine) {
        SQLiteDatabase dbContext = this.getWritableDatabase();
        dbContext.delete(TABLE_ROUTINES, KEY_ID + " = ?",
                new String[]{String.valueOf(routine.getId())});
        dbContext.close();
    }

    // Adding new routineExercise
    public void addRoutineExercise(RoutineExercise routineExercise) {
        SQLiteDatabase dbContext = this.getWritableDatabase();

        ContentValues insertValues = new ContentValues ();
        insertValues.put (KEY_ID, routineExercise.getId ());// Hard Code Key_Id
        insertValues.put (KEY_RTN_ID, routineExercise.getRoutineId ());
        insertValues.put (KEY_EXR_ID, routineExercise.getExerciseId ());
        insertValues.put (KEY_REP_CT, routineExercise.getRepCount ());
        insertValues.put (KEY_ORDER, routineExercise.getOrder());

        dbContext.insert(TABLE_ROUTINE_EXERCISES, null, insertValues);
        dbContext.close();
    }

    // Getting single routineExercise
    public RoutineExercise getRoutineExercise(int id) {
        SQLiteDatabase dbContext = this.getReadableDatabase ();
        RoutineExercise cResult;

        Cursor dbCursor = dbContext.query(TABLE_ROUTINE_EXERCISES, new String[]{KEY_ID,
                        KEY_RTN_ID, KEY_EXR_ID, KEY_REP_CT, KEY_ORDER}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (dbCursor != null) {
            dbCursor.moveToFirst ();
        }

        cResult = new RoutineExercise (Integer.parseInt (dbCursor.getString (0)),
                Integer.parseInt (dbCursor.getString (1)), Integer.parseInt (dbCursor.getString (2)),
                Integer.parseInt (dbCursor.getString (3)), Integer.parseInt (dbCursor.getString (4)));

        cResult.setRoutine(getRoutine(cResult.getRoutineId()));
        cResult.setExercise(getExercise(cResult.getExerciseId()));

        return cResult;
    }

    // does not work, fails at Po10
    // Getting All RoutineExercises
    public List<RoutineExercise> getAllRoutineExercises() {
        List<RoutineExercise> routineExerciseList = new ArrayList<RoutineExercise> ();
        String selectQuery = "SELECT * FROM " + TABLE_ROUTINE_EXERCISES;
        SQLiteDatabase dbContext = this.getReadableDatabase();
        Cursor dbCursor = dbContext.rawQuery (selectQuery, null);

        if (dbCursor.moveToFirst ())
        {
            do
            {
                RoutineExercise routineExercise = new RoutineExercise ();
                routineExercise.setId (Integer.parseInt (dbCursor.getString (0)));
                routineExercise.setRoutineId (Integer.parseInt(dbCursor.getString (1)));
                routineExercise.setExerciseId (Integer.parseInt(dbCursor.getString (2)));
                routineExercise.setRepCount (Integer.parseInt(dbCursor.getString (3)));
                routineExercise.setOrder (Integer.parseInt(dbCursor.getString (4)));
                routineExercise.setRoutine(getRoutine(routineExercise.getRoutineId()));
                routineExercise.setExercise(getExercise(routineExercise.getExerciseId()));
                routineExerciseList.add (routineExercise);
            } while (dbCursor.moveToNext ());
        }

        return routineExerciseList;
    }

    // Getting routineExercises Count
    public int getRoutineExercisesCount() {
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_ROUTINE_EXERCISES;
        SQLiteDatabase dbContext = this.getReadableDatabase ();
        Cursor cursor = dbContext.rawQuery (countQuery, null);
        cursor.close();

        return Integer.parseInt(cursor.getString(0));
    }
    // Updating single routineExercise
    public int updateRoutineExercise(RoutineExercise routineExercise) {
        SQLiteDatabase dbContext = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues ();
        updatedValues.put (KEY_RTN_ID, routineExercise.getRoutineId ());
        updatedValues.put (KEY_EXR_ID, routineExercise.getExerciseId ());
        updatedValues.put(KEY_REP_CT, routineExercise.getRepCount());
        updatedValues.put(KEY_ORDER, routineExercise.getOrder());

        return dbContext.update(TABLE_ROUTINE_EXERCISES, updatedValues, KEY_ID + " = ?",
                new String[] { String.valueOf (routineExercise.getId()) });
    }

    // Deleting single routineExercise
    public void deleteRoutineExercise(RoutineExercise routineExercise) {
        SQLiteDatabase dbContext = this.getWritableDatabase();
        dbContext.delete(TABLE_ROUTINE_EXERCISES, KEY_ID + " = ?",
                new String[]{String.valueOf(routineExercise.getId())});
        dbContext.close();
    }

    // Adding new historyRecord
    public void addHistoryRecord(History historyRecord) {
        SQLiteDatabase dbContext = this.getWritableDatabase ();
        ContentValues insertValues = new ContentValues ();
        insertValues.put (KEY_START, historyRecord.getStartTC ());
        insertValues.put (KEY_END, historyRecord.getEndTC ());
        insertValues.put (KEY_RTN_ID, historyRecord.getRoutineId ());
        insertValues.put (KEY_EXR_ID, historyRecord.getExerciseId ());
        insertValues.put (KEY_REPS_COMP, historyRecord.getRepsCompleted ());
        dbContext.insert (TABLE_HISTORY, null, insertValues);
        dbContext.close ();
    }

    // Getting single historyRecord
    public History getHistoryRecord(int id) {
        SQLiteDatabase dbContext = this.getReadableDatabase ();
        History cResult;

        Cursor dbCursor = dbContext.query (TABLE_HISTORY, new String[] { KEY_ID,
                        KEY_START, KEY_END, KEY_RTN_ID, KEY_EXR_ID, KEY_REPS_COMP }, KEY_ID + "=?",
                new String[] { String.valueOf (id) }, null, null, null, null);
        if (dbCursor != null) {
            dbCursor.moveToFirst ();
        }

        cResult = new History (Integer.parseInt (dbCursor.getString (0)),
                Long.valueOf (dbCursor.getString (1)).longValue(), Long.valueOf (dbCursor.getString (2)).longValue(),
                Integer.parseInt (dbCursor.getString (3)), Integer.parseInt (dbCursor.getString (4)),
                Integer.parseInt (dbCursor.getString (5)));

        cResult.setRoutine(getRoutine(cResult.getRoutineId()));
        cResult.setExercise(getExercise(cResult.getExerciseId()));

        return cResult;
    }

    // Getting All HistoryRecords
    public List<History> getAllHistoryRecords() {
        List<History> historyList = new ArrayList<History> ();

        String selectQuery = "SELECT * FROM " + TABLE_HISTORY;

        SQLiteDatabase dbContext = this.getReadableDatabase ();
        Cursor dbCursor = dbContext.rawQuery (selectQuery, null);


        if (dbCursor.moveToFirst ())
        {
            do
            {
                History historyRecord = new History ();
                historyRecord.setStartTC (Long.valueOf(dbCursor.getString (1)).longValue());
                historyRecord.setEndTC (Long.valueOf(dbCursor.getString (2)).longValue());
                historyRecord.setId (Integer.parseInt (dbCursor.getString (0)));
                historyRecord.setRoutineId (Integer.parseInt(dbCursor.getString (3)));
                historyRecord.setExerciseId (Integer.parseInt(dbCursor.getString (4)));
                historyRecord.setRepsCompleted(Integer.parseInt(dbCursor.getString (5)));
                historyRecord.setRoutine(getRoutine(historyRecord.getRoutineId()));
                historyRecord.setExercise(getExercise(historyRecord.getExerciseId()));
                historyList.add (historyRecord);
            } while (dbCursor.moveToNext ());
        }

        return historyList;
    }

    // Getting historyRecords Count
    public int getHistoryRecordsCount() {
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_HISTORY;
        SQLiteDatabase dbContext = this.getReadableDatabase ();
        Cursor cursor = dbContext.rawQuery (countQuery, null);
        cursor.close();


        return Integer.parseInt (cursor.getString (0));
    }
    // Updating single historyRecord
    public int updateHistoryRecord(History historyRecord) {
        SQLiteDatabase dbContext = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues ();
        updatedValues.put (KEY_START, historyRecord.getStartTC ());
        updatedValues.put (KEY_END, historyRecord.getEndTC ());
        updatedValues.put (KEY_RTN_ID, historyRecord.getRoutineId ());
        updatedValues.put (KEY_EXR_ID, historyRecord.getExerciseId ());
        updatedValues.put (KEY_REP_CT, historyRecord.getRepsCompleted());

        return dbContext.update(TABLE_HISTORY, updatedValues, KEY_ID + " = ?",
                new String[] { String.valueOf (historyRecord.getId()) });
    }

    // Deleting single historyRecord
    public void deleteHistoryRecord(History historyRecord) {
        SQLiteDatabase dbContext = this.getWritableDatabase();
        dbContext.delete (TABLE_HISTORY, KEY_ID + " = ?",
                new String[] { String.valueOf (historyRecord.getId ()) });
        dbContext.close();
    }
    */
}
