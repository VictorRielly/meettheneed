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
public class SpanishDatabaseHandler extends SQLiteOpenHelper {
    public enum TABLES {
        ORGANIZATION, POPULATION, SERVESP, PHONE,
        CATEGORY, FALLS_IN, LOCATION, LANGUAGE, SERVESL
    }

    ;
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "meettheneedEspanol";

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

    public SpanishDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dbContext) {
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
        dbContext.execSQL(CREATE_ORGANIZATION_TABLE);
        dbContext.execSQL(CREATE_POPULATION_TABLE);
        dbContext.execSQL(CREATE_SERVES_PEOPLE_TABLE);
        dbContext.execSQL(CREATE_PHONE_TABLE);
        dbContext.execSQL(CREATE_CATEGORY_TABLE);
        dbContext.execSQL(CREATE_FALLS_IN_TABLE);
        dbContext.execSQL(CREATE_LOCATION_TABLE);
        dbContext.execSQL(CREATE_LANGUAGE_TABLE);
        dbContext.execSQL(CREATE_SERVES_LANGUAGE_TABLE);
        /*
        The following code manually populates the database It is long because there is a lot of data
        to put in the database. {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}}
         */
        String[][][] Organizations = {{{"A&D/Youth Helpline"}, {""}, {""}, {"Gratis"}, {""}, {""}, {""}, {"Línea de Ayuda"}, {""}, {""}, {""}, {"OR"}, {"5032441611"}, {"Jóvenes"}, {""}},
                {{"Abuse Recovery Ministry and Services"}, {"info@armsonline.org"}, {"http://www.armsonline.org"}, {"Algunos servicios gratuitos (Programas preventivos y programas de recuperación)"}, {"Grupos educativos"}, {""}, {"Honorarios y deberes exigibles por abuso/control orientado en las aulas. Los honorarios son suspendidos para aquellos que asisten a clases recuperación preventiva al encontrar o reconocer el abuso y el control de comportamientos."}, {"Consejería para padres", "Educación de abuso sexual"}, {""}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5038469284"}, {"Los hombres que han pasado por los tribunales", "Las mujeres que han estado en relaciones donde ha habido abuso /o que exhiben comportamientos de control", "Adolescentes", "Adultos"}, {"Inglés"}},
                {{"Adelante Mujeres"}, {"info@adelantemujeres.org"}, {"http://www.adelantemujeres.org"}, {""}, {""}, {""}, {""}, {"Educación"}, {"2036 Main St. Ste A"}, {"97116"}, {"Forest Grove"}, {"OR"}, {"5039920078"}, {"Mujeres latinas"}, {"Inglés", "Español"}},
                {{"Affordable Dentures"}, {""}, {"http://affordabledentures.com/office/wilsonville"}, {"aceptan OHP", "costo reducido"}, {"Dentures", "Repairs", "extracción dental", "Sedation Dentistry", "X-Ray services", "Gold Crowns"}, {""}, {"Seguro de enfermedad, Crédito aceptado de servicios de salud disponible. Abierto de 8AM-5PM de lunes a viernes."}, {"Servicios Dentales"}, {"8229 SW Wilsonville Rd. Suite 6AB"}, {"97070"}, {"Wilsonville"}, {"OR"}, {"5038318817"}, {"Adultos"}, {"Inglés"}},
                {{"Albertina Kerr Centers"}, {"info@albertinakerr.org"}, {"http://www.albertinakerr.org/ChildrensDevelopmentalHealth"}, {""}, {"patologia del lenguaje, servicio para autismo, terapia ocupacional, manejo de casos, educacion"}, {""}, {""}, {"Residencias para Jóvenes", "Servicios para pacientes externos", "Consejería de educación mental", "Grupos de hospedaje para jóvenes y adultos", "Vivienda sostenida"}, {"247 SE Washington St."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5032398101"}, {"niños con dificultades de desarrollo mental", "familias", "Adultos"}, {"Inglés"}},
                {{"Alcohol and Drug Helpline"}, {""}, {""}, {"Gratis"}, {""}, {""}, {""}, {"Línea de Ayuda"}, {""}, {""}, {""}, {""}, {"5032441312"}, {""}, {""}},
                {{"Allies in Change"}, {""}, {""}, {""}, {"manejo de la ira, consejería individual y de grupo, grupos de ayuda"}, {""}, {""}, {"Violencia Intrafamiliar", "Consejería", "Grupos de ayuda"}, {""}, {""}, {""}, {""}, {"5032977979"}, {""}, {""}},
                {{"Anxiety Support Line"}, {""}, {""}, {"Gratis"}, {""}, {""}, {""}, {"Línea de Ayuda"}, {""}, {""}, {""}, {""}, {"5036472642"}, {""}, {""}},
                {{"Asian Health and Service Center"}, {"info@ahscpdx.org"}, {"ahscpdx.org"}, {"Varia según el servicio"}, {"Mucha ayuda de servicio social, clases de Inglés, servicios para personas mayores, empleos"}, {""}, {""}, {"Centro de Recursos Sociales"}, {"12500 SW Allen Blvd."}, {"97008"}, {"Beaverton"}, {"OR"}, {"5036414113"}, {"migranteses Asiáticos"}, {"Inglés", "Cantones", "Mandarin", "Coreano", "Vietnamita"}},
                {{"Beaverton Community Basket"}, {""}, {""}, {"Gratis"}, {""}, {""}, {"Abierto el segundo viernes del mes en Beaverton Resource Center. Traer tu propia bolsa de dormir."}, {"Despensa de Alimentos"}, {"12500 SW Allen Blvd."}, {"97008"}, {"Beaverton"}, {""}, {"5034396510"}, {""}, {""}},
                {{"Beaverton Family Resource Center"}, {""}, {""}, {"Gratis"}, {""}, {""}, {"Abierto el tercer jueves de cada mes a las 10:00"}, {"Despensa de Alimentos"}, {"16550 SW Merlo Rd. #1"}, {"97006"}, {"Beaverton"}, {"OR"}, {"5036490367"}, {""}, {""}},
                {{"Beaverton Hispanic Center"}, {"officeadministrator@beavertonhc.org"}, {"beavertonhc.org"}, {"no comisiones para la mayoría de los servicios"}, {"Ayudan a las familias a encontrar servicios sociales, servicios de traducción, Clases de Inglés y Español, GED, Servicios de consejería de maltrato intrafamiliar,  y seminarios de pandillas, drogas y crimenes."}, {"No hay fondos para la asistencia en este momento"}, {"Abierto Lunes a Viernes de 9am a 12pm"}, {"Centro de Recursos Sociales"}, {"3800 SW Cedar Hills Blvd., Ste. 123"}, {"97005"}, {"Beaverton"}, {"OR"}, {"9712492421"}, {"Familias Hispanas"}, {"Inglés", "Español"}},
                {{"Beaverton Literacy Council"}, {"director@beavertonliteracy.org"}, {"beavertonliteracy.org"}, {"apoya en el material de clases"}, {"Cursos de Inglés como segundo idioma, Educación ciudadana para adultos"}, {""}, {""}, {"Sercivios anti-analfabetismo"}, {"PO Box 952"}, {"97075"}, {"Beaverton"}, {"OR"}, {"5035208764"}, {""}, {""}},
                {{"Beaverton Seventh Day Adventist"}, {""}, {""}, {"Gratis"}, {""}, {""}, {"Abierto Miércoles de 9am a 12pm y de 12:30pm-2pm"}, {"Sercivios anti-analfabetismo"}, {"14645 SW Davis Rd."}, {"97007"}, {"Beaverton"}, {"OR"}, {"5035919025"}, {""}, {""}},
                {{"Bienestar"}, {"elmpark@cascade-management.com"}, {"http://www.bienestar-or.org/"}, {""}, {"Provee viviendas de alquiler alcanzables para los trabajadores agrícolas y familias trabajadoras de bajos ingresos. Se ofrecen programas de servicios a residentes, que incluyen ayuda con recursos y referencias, (A inquilinos solamente ) ESL , GED y clases de computación , club del empleo, jardines comunitarios y actividades para niños. También ofrecen un programa de \"Promotores\" (Conectores de la Comunidad) para los residentes."}, {""}, {""}, {"Hospedaje"}, {"481 S Alpine Street"}, {"97113"}, {"Cornelius"}, {"OR"}, {"5033594532"}, {""}, {"Inglés", "Español"}},
                {{"Bienestar"}, {"montebello@cascade-management.com"}, {"http://www.bienestar-or.org/"}, {"hospedaje de bajo costo, servicios adicionales gratuitos"}, {"capacitación para el trabajo con habilidades culinarias para los residentes de bajos ingresos,"}, {""}, {"Aplicaciones para viviendas de bajos ingresos, deben cumplir con un 50% o menos de un área de mediano ingreso. "}, {"Hospedaje de bajo costo", "GED", "cursos de ESL "}, {"220 SE 12th Ave."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5038469266"}, {"Agricultpres", "personas de bajos recursos."}, {"Inglés", "Español"}},
                {{"Bienestar De La Familia Multnomah County"}, {""}, {""}, {""}, {"Consejería grupal"}, {""}, {""}, {"Consejería"}, {""}, {""}, {""}, {""}, {"5039885465"}, {"Individuales o por parejas"}, {"Español"}},
                {{"Birthright of Hillsboro"}, {"hillsboroor@birthright.org"}, {"http://www.birthright.org"}, {"Gratis"}, {"Embarazo, Ropa para maternidad y para bebé, educacion, Consejería profesional, Línea de ayuda las 24 horas los 7 días de la semana"}, {""}, {"Línea telefónica las 24 horas los 7 días de la semana. oficina abierta de de las 10am-4pm de martes a viernes. "}, {"Apoyo para padres en el embarazo"}, {"232 NE Lincoln St. Suite F"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5036486766"}, {"Mujeres embarazas de cualquier edad."}, {"Inglés"}},
                {{"Boys and Girls Aid"}, {""}, {"boysandgirlsaid.org"}, {""}, {""}, {""}, {"Debe llamar para agregar a la lista de espera."}, {"Hospedaje Transitorio"}, {"8196 SW Hall Blvd., Ste. 102"}, {"97008"}, {"Beaverton"}, {"OR"}, {"5036403263"}, {"Jóvenes entre los 16-23 años", "personas sin hogar o con riesgo a quedar sin hogar", "mujeres jovenes con niños. "}, {""}},
                {{"Boys and Girls Clubs of Portland"}, {"admin@bgcportland.org"}, {"http://www.bgcportland.org"}, {"$25 al año"}, {""}, {""}, {"6 casa clubs ubicados en el área metropolitana de Portland. La tarifa es de $25/año para unirse. Después del horario escolar lunes, martes, jueves, viernes: 2PM-7PM, miércoles 145pm-7pm. Horas juveniles los lunes, martes, jueves, viernes, 2PM-7PM, miércoles , 145PM-7PM, viernes & sábado, 7PM-11PM."}, {"Computadoras públicas", "Programas despues de la escuela de alimentación gratuita", "Educación en el abuso del alcohol"}, {"560 SE 3rd Ave."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036404558"}, {"Jóvenes entre los de 6-18 años"}, {"Inglés"}},
                {{"Bradley Angle House"}, {""}, {"bradleyangle.org"}, {""}, {""}, {""}, {""}, {"Violencia Intrafamiliar"}, {""}, {""}, {""}, {""}, {"5032812442"}, {""}, {""}},
                {{"Bridges to Change"}, {"contact@bridgestochange.com"}, {"http://www.bridgestochange.com"}, {""}, {""}, {""}, {""}, {"Hospeje Transitorio", "Asistencia Laboral", "Hospedaje de bajo costo"}, {"260 SW Adams Ave."}, {"971233"}, {"Hillsboro"}, {"OR"}, {"5037199909"}, {"personas sin hogar para programas de trabajo"}, {"Inglés"}},
                {{"Bright Now Dental"}, {""}, {"http://brightnow.com"}, {"aceptan OHP, comisiones reducidasand Discounts, 20% o más en servicios"}, {""}, {""}, {"descuentos sindicales  de 40% o más para las cubiertas de los sindicatos. 20% o más de todos los servicios para quienes necesitan asistencia financiera y no tienen seguro."}, {"Servicios Dentales de bajo costo"}, {"7206 NE Cornell Rd"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5036409999"}, {"abierto a Todas las edades."}, {"Inglés"}},
                {{"Bright Now Dental Wilsonville"}, {""}, {"http://brightnow.com"}, {"comisiones reducidas y descuentos, 20% o más en servicios"}, {""}, {""}, {"descuentos sindicales  de 40% o más para las cubiertas de los sindicatos. 20% o más de todos los servicios para quienes necesitan asistencia financiera y no tienen seguro."}, {"Servicios Dentales de bajo costo"}, {"25700 SW Argyle Ave. Unit F"}, {"97070"}, {"Wilsonville"}, {"OR"}, {"5036828552"}, {"abierto a Todas las edades."}, {"Inglés"}},
                {{"Calvin Presbyterian Church"}, {""}, {""}, {"Gratis"}, {""}, {""}, {"Abierto los viernes del 12/4/15 al 3/25/16 de 6pm a 7am."}, {"Refugio del servidor"}, {"10445 SW Canterbury Ln."}, {"97224"}, {"Tigard"}, {"OR"}, {"211"}, {"Adultos de 18  años  o mas grandes.  No mascotas."}, {""}},
                {{"Cascadia"}, {""}, {"cascadiabhc.org"}, {""}, {""}, {""}, {""}, {"Consejería"}, {""}, {""}, {""}, {""}, {"5032380769"}, {""}, {""}},
                {{"Cedar Mill Bible Church - The Jesus Table"}, {""}, {""}, {"Gratis"}, {""}, {""}, {"Martes 6:00-7:00pm."}, {"comidas"}, {"12208 NW Cornell Rd."}, {"97229"}, {"Portland"}, {"OR"}, {"-1"}, {""}, {""}},
                {{"Centro Cultural of Washington County"}, {"dlopez@centrocultural.org"}, {"http://www.centrocultural.org"}, {"Aplican honorarios de $40-$70"}, {"Promueve y proporciona una oportunidad para la Educación Integral adultos latinos , en la alfabetización y los niveles de educacion secundaria . Se acredita a los estudiantes, de acuerdo con los objetivos y contenidos de los programas de instrucción establecidos por el Ministerio de Educación Pública (SEP ), con el apoyo del Instituto de los Mexicanos en el Exterior (IME  y el Ministerio de Asuntos Exteriores de México (SRE) . El programa de educacion básica para adultos ofrece clases de GED viernes por la tarde de 6:30 pm -8 : 30 pm , el INEA los viernes de 18:30-20:30, lasclases de ciudadanía se ofrecen en el otoño, invierno y primavera ."}, {""}, {""}, {"Educación"}, {"1174 North Adair Street 1110 North Adair Street"}, {"97113"}, {"Cornelius"}, {"OR"}, {"5033590446"}, {""}, {"Inglés", "Español"}},
                {{"City of Beaverton"}, {"disputemail@ci.beaverton.or.us"}, {"beavertonoregon.gov/disputeresolution/"}, {"Gratis"}, {""}, {""}, {"Ambas partes deben estar de acuerdo en la meditación."}, {"Servicios de Meditación"}, {"4755 SW Griffith Dr."}, {"97005"}, {"Beaverton"}, {"OR"}, {"5035262523"}, {""}, {"Inglés", "Español"}},
                {{"City of Hillsboro Community Senior Center"}, {"Paula.Stewart@hillsboro-oregon.gov"}, {"https://www.hillsboro-oregon.gov/index.aspx?page=873"}, {"Gratis"}, {""}, {""}, {"Abierto lunes a viernes de 9AM-5PM."}, {"Consejería legal", "Rentas", "Clases", "Seminarios educacionales", "Viajes"}, {"750 SE 8th Ave."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036151090"}, {"personas de 55 años o más."}, {"Inglés", "Interpretes disponibles"}},
                {{"City of Wilsonville"}, {"duke@wilsonvillelibrary.org"}, {"http://www.wilsonville.lib.or.us"}, {"Gratis"}, {""}, {""}, {"Librería abierta de lunes a jueves de 10AM-8PM, viernes-sábado de 10am-6pm, Domingo de 1pm-6pm. "}, {"Computadoras públicas"}, {"8200 SW Wilsonville Rd"}, {"97070"}, {"Wilsonville"}, {"OR"}, {"5036598634"}, {"abierto a Todas las edades."}, {"Cualquier idioma"}},
                {{"CODA"}, {"laurajacobo@codainc.org"}, {"http://www.codainc.org"}, {"Se reduce costo según el ingreso, algunos servicios son gratuitos, aceptan OHP"}, {"Evaluación, Grupos de Consejería, tratamiento asistido con medicamentos, manejo de casos"}, {""}, {"No acepta el seguro Medicare.Paras el uso de drogas y alcohol el alojamiento es gratuito."}, {"consejería de abuso de sustancias"}, {"720 SE Washington"}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036480755"}, {"familias and individuales", "Adultos"}, {"Inglés"}},
                {{"Council on Aging Inc Serving Seniors in Washington County"}, {""}, {""}, {"Gratis"}, {"Alguna ayuda financiera"}, {""}, {"las personas que hablen pueden dejar el mensaje en cualquier momento."}, {"Referencias"}, {""}, {"97123"}, {""}, {"OR"}, {"5036158080"}, {"55 años  o más grandes"}, {"Inglés"}},
                {{"De Paul Treatment Centers, Inc"}, {"info@depaultc.org"}, {"https://depaultreatmentcenters.org/"}, {"aceptan OHP, precio móvil"}, {"Servicios de salud mental para foráneos, desintoxicación medica, serivicios de residencia, tratamientos al día, terapia familiar"}, {""}, {"Escala móvil disponibles para el auto-pago. lunes-viernes 8AM-6PM, sábados de 8AM-1PM."}, {"Evaluación de salud mental", "consejería de abuso de sustancias"}, {"205 SE 3rd St. Suite 100"}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5035351151"}, {"edades de  18 o más"}, {"Inglés"}},
                {{"Department of Human Services"}, {""}, {""}, {""}, {""}, {""}, {""}, {"Asistencia financiera", "Asistencia alimenticia"}, {"5350 NE Elam Young Pkwy."}, {"Hillsboro"}, {"97124"}, {"OR"}, {"5036488951"}, {""}, {""}},
                {{"Domestic Violence Resource Center"}, {"dvrc@dvrc-or.org"}, {"http://www.dvrc-or.org"}, {"Gratis"}, {"La casa de Monika, órdenes de restricción, Servicio de Abogados"}, {""}, {"Se permiten mascotas en la vivienda. Oficina de Asesoramiento Abierto de lunes-jueves."}, {" línea de crisis de 24 horas", "refugio", "Servicios legales de Violencia intrafamiliar", "consejería Profesional", "Planificación segura", "Entrenamientos", "Referencias"}, {"Confidential"}, {"97123"}, {""}, {"OR"}, {"5036405352"}, {"Victimas de maltrato intrafamiliar", "individuales", "familias", "niños", "Mascotas"}, {"Inglés", "Español"}},
                {{"Domestic Violence Resource Center"}, {""}, {""}, {"Gratis"}, {""}, {""}, {""}, {"linea caliente"}, {""}, {""}, {""}, {""}, {"5034698620"}, {""}, {""}},
                {{"Dorcas Society-Tualatin"}, {""}, {"http://homelessneeds.org/agency-information/oregon/tualatin/51/"}, {"Gratis"}, {"Duchas, Vestimenta."}, {""}, {"Proporciona ropa, ropa de cama y ropa blanca para residentes de Wilsonville, Tualatin y localidades cercanas. 10AM hasta el mediodía, loos miércoles ofrece vestimenta. "}, {"refugio"}, {"22222 SW Grahams Ferry Rd"}, {"97062"}, {"Tualatin"}, {"OR"}, {"5036922915"}, {"personas sin hogar"}, {"Inglés"}},
                {{"Easter Seals Oregon"}, {""}, {"http://www.or.easterseals.com"}, {"Gratis"}, {"Programa de Inglés como segundo idioma. Reactivación de las obras, Clases de computación."}, {""}, {""}, {"Asistencia Laboral", "Rehabilitación Vocacional"}, {"1049 SW Baseline St, Suite, D450"}, {"97123"}, {"Hillsboro"}, {"OR"}, {"9712288462"}, {"Latinos  con 18 años o más sin capacidad de adiquirir documentación o  estan ilegalmente en este país."}, {"Inglés"}, {"Español"}},
                {{"Ecumenical Ministries of Oregon"}, {""}, {""}, {""}, {""}, {""}, {""}, {"Alojamiento", "Asistencia alimenticia", "Refugio & Servicios al inmigrante", "Servicios sociales de Oregon"}, {""}, {""}, {""}, {"OR"}, {"-2"}, {""}, {""}},
                {{"El Enfoque De La Familia Counseling Center, Inc"}, {""}, {"http://edlfamilia.com/en/"}, {""}, {"Alcohol and Drug services, manejo de la ira, Domestic Violence and Parenting Classes, Individual Therapy, Marriage and Terapia familiar, Alcohol and Drug evaluations"}, {""}, {""}, {"consejería de Salud mental", "Programas de crianza de hijos ordenos por la corte"}, {"205 SE 3rd St. Suite 200"}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036814870"}, {"delincuentes Hispanos o Latinos"}, {"Español", "Inglés"}},
                {{"Evergreen Christian Center Food Bank"}, {"Rick@ecc4.org"}, {"http://www.ecc4.org/"}, {"Gratis"}, {""}, {""}, {"Abierto el domingo de 12 a 15PM, la línea empieza en 9;30AM."}, {"banco de alimentos"}, {"4400 NW Glencoe Rd."}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5036487168"}, {"bajos recursos", "personas sin hogar"}, {"Inglés"}},
                {{"Fairhaven Recovery Homes"}, {"jpliebertz@hotmail.com"}, {"fairhavenrecovery.com"}, {"Varia según la casa"}, {"Centros de reinserción exdelincuente"}, {""}, {""}, {"Rehabilitacion  de Abuso de sustancias"}, {"20 SW 97th Ave."}, {"97225"}, {"Portland"}, {"OR"}, {"5039959023"}, {""}, {""}},
                {{"Forest Grove Family Resource Center"}, {""}, {""}, {""}, {"Proporciona despensa de alimentos para los residentes en ditrictos escolares del condado  del Banco"}, {""}, {""}, {"despensas"}, {"42350 NW Trellis Way"}, {"97106"}, {"Banks"}, {"OR"}, {"5033245686"}, {""}, {""}},
                {{"Good Neighbor Center"}, {"staff@goodneighborcenter.org"}, {"goodneighborcenter.org"}, {""}, {""}, {""}, {""}, {"refugio familiar"}, {"11130 SW Greenburg Rd."}, {"97223"}, {"Tigard"}, {"OR"}, {"5034436084"}, {"solo familias", "No solteros"}, {""}},
                {{"Goodwill Industries of the Columbia Willamette"}, {""}, {"http://meetgoodwill.org/blog/job-connection-events/"}, {"Gratis"}, {""}, {""}, {"Lunes-viernes 9AM-5 30H."}, {"Asistencia Laboral", "reactivación de las obras", "Clases y Ciudadanía de ESL", "Clases de ceritificación en Micorsoft", "Preparación de  Clases para GED", "busqueda de empleo", "Preparación para el trabajo"}, {"966 SE Oak St."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036487971"}, {"edades de 16 y mas grandes"}, {"Inglés", "Español"}},
                {{"Goodwill Industries of the Columbia Willamette"}, {""}, {"metrogoodwill.org"}, {"Gratis"}, {""}, {""}, {""}, {"Centro de atención laboral"}, {"12975 SW Westgate Dr."}, {"97005"}, {"Beaverton"}, {"OR"}, {"5036413762"}, {"edades de 16 y mas grandes"}, {""}},
                {{"Goodwill Industries of the Columbia Willamette"}, {""}, {"metrogoodwill.org"}, {"Gratis"}, {""}, {""}, {""}, {"Centro de atención laboral"}, {"13920 SW Pacific Hwy"}, {"97223"}, {"Tigard"}, {"OR"}, {"5036399482"}, {"edades de 16 y mas grandes"}, {""}},
                {{"Hillsboro Police Department"}, {"police_dept@ci.hillsboro.or.us"}, {"http://www.ci.hillsboro.or.us/index.aspx?page=381"}, {"Gratis"}, {"La Mediacion puede incluir problemas de vecindario,disputas de limitacionde de terreno, quejas por ruido , problemas de habitabilidad de la comunidad, las relaciones entre padres / adolescentes, disputas familiares, facilitadores a la comunidad,temas de propietario / inquilino .. "}, {""}, {"Las reuniones suelen durar 1-2 horas con el objetivo de crear un acuerdo escrito que no es jurídicamente vinculante pero conserva y mantiene la buena fe."}, {"Intervensión de crisis", "Disputas Legales", "Mediación"}, {"250 SE 10th Ave."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036156651"}, {"Adultos"}, {"Inglés", "Interpretes disponibles"}},
                {{"Hillsboro School District"}, {"johnsjer@hsd.k12.or.us"}, {"http://www.hsd.k12.or.us/miller/connect.html#"}, {"Gratis"}, {"Escuela para Padres / Programa de Jóvenes embarazadas ."}, {""}, {"Debe estar en el Distrito Escolar de Hillsboro para tomar parte en la Escuela y  los progrmas de transición de clínica pública."}, {"consejería de carreras profesionales", "Homeless School Transition Program", "Clínica pública"}, {"215 SE Sixth Ave"}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5038441000"}, {"edades entre los 17-21 años"}, {"Inglés"}},
                {{"HomeForward Merlo Station Harvest Share"}, {""}, {""}, {"Gratis"}, {""}, {""}, {"Abierto el segundo jueves del mes de 2:30-4:00pm. Por favor traiga tu propia bolsa de dormir"}, {""}, {"2032 SW Merlo Ct."}, {"97006"}, {"Beaverton"}, {"OR"}, {"-3"}, {""}, {""}},
                {{"Homeplate"}, {"info@homeplateyouth.org"}, {"http://www.homeplateyouth.org"}, {"Gratis"}, {"duchas, suministros de aseo personal, Pre Orientación de Empleo y Asistencia."}, {""}, {"los albergues están disponibles 3 días a la semana. Los lunes, miércoles y jueves por la noche."}, {"Centros de albergue", "comidas", "vestimenta"}, {"332 NE 6th Ave"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5038674954"}, {"personas entre los 11-24 años que quedaron sin hogar o estan es riesgo de perderlo."}, {"Inglés"}},
                {{"Hope Diner at St. Francis"}, {""}, {""}, {"Gratis"}, {""}, {""}, {"Domingo 5pm"}, {"comidas"}, {"15659 SW Oregon"}, {"97140"}, {"Sherwood"}, {"OR"}, {"5036257067"}, {""}, {""}},
                {{"IRCO"}, {""}, {""}, {""}, {""}, {""}, {""}, {"Servicios al inmigrante"}, {""}, {""}, {""}, {"OR"}, {"5032211689"}, {""}, {"Inglés", "Español"}},
                {{"Jubilee Transition Homes"}, {"jubilee@comcast.net"}, {"jubileeth.com"}, {""}, {""}, {""}, {""}, {"alojamiento transitorio"}, {"Confidential Address"}, {"97223"}, {"Tigard"}, {"OR"}, {"-4"}, {"adultos hombres solteros"}, {""}},
                {{"Lifeskills Learning Institute, INC."}, {""}, {"http://lsli.net/"}, {"de $15-25 por 45 minuos de sesión"}, {""}, {""}, {""}, {"Consejería"}, {"4110 Pacific Avenue Suite 102b"}, {"97116"}, {"Forest Grove"}, {"OR"}, {"5036015400"}, {""}, {"Inglés"}},
                {{"Lifeworks NW"}, {""}, {"lifeworksnw.org"}, {""}, {""}, {""}, {""}, {"Consejería"}, {""}, {""}, {""}, {"OR"}, {"5036459010", "8005461666"}, {""}, {""}},
                {{"Love in the Name of Christ of Greater Hillsboro"}, {"office.loveinc@gmail.com"}, {"http://www.loveincgreaterhillsboro.org"}, {"Gratis"}, {"Suministros de aseo personal, productos de lavandería, ropa de cama"}, {""}, {"Luness 1;30PM-3;30H, martes y jueves 9;30AM-12PM."}, {"artículos escolares"}, {"1055 NE 25th Ave. Suite O"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5036480700"}, {"Adultos", "individuales", "familias", "niños"}, {"Inglés"}},
                {{"Luke-Dorf West"}, {"kradecki@luke-dorf.org"}, {"http://www.luke-dorf.org"}, {""}, {""}, {""}, {""}, {"refugio", "Evaluación de salud mental", "Consejería", "manejo de casos", "Desarrollo de habilidades", "Terapia grupal", "manejo de medicamento", "grupos del hogar", "apoyo de alojamiento", "alojamiento transitorio"}, {"Confidential"}, {"97123"}, {""}, {"OR"}, {"5036403262"}, {"personas sin hogar persons con problemas cronicos de salud mental", "individuos de 18 años o que no tienen hogar", "pobreza", "abuso sustancial", "carencia de poyo"}, {"Inglés"}},
                {{"McKinney-Vento Homeless-BSD"}, {"lisa_mentensana@beaverton.k12.or.us"}, {"beaverton.k12.or.us/cmnty/Pages/homeless-program.aspx"}, {"Gratis"}, {""}, {""}, {""}, {"escuela de Programa transitorio para personas sin hogar"}, {"16550 SW Merlo Rd."}, {"97006"}, {"Beaverton"}, {"OR"}, {"5035914462"}, {"personas sin hogar Jóvenes"}, {"Inglés", "Español"}},
                {{"Meals on Wheels People"}, {"info@mealsonwheelspeople.org"}, {"http://www.mealsonwheelspeople.org/what-we-do/dining-centers/hillsboro-center"}, {"Gratis", "$7.39/Donación de alimentos requerida", "pero no se obliga a personas de bajos recursos"}, {""}, {""}, {"la comida se sirve de lunes viernes en las ubicaciones de servicio. Meals on Wheels entrega los lunes-viernes entre  las horas de 11am-1pm. El servicio de comida empieza 48 horas después de que la notificación de las oficina. El servicio no se niega  a nadie."}, {"Entrega a domicilio de comidas", "comidas calientes"}, {"525 SE Baseline St."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036482950"}, {"Adultos de 60 o mas años que estan fuera de su casa"}, {"Inglés", "Español", "Alemán", "Arabe"}},
                {{"Mens Resource Center"}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {"5032353433"}, {""}, {""}},
                {{"Metropolitan Public Defender"}, {""}, {"http://www.mpdlaw.com"}, {"Bajo costo"}, {"Servicio de Abogados"}, {""}, {"Corte sólo de referencia. Horario de oficina de 8am-5pm lunes-viernes."}, {"Defensa pública"}, {"400 E Main St. Suite 210"}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5037267900"}, {"Indigentes", "clientes Veteranos"}, {"Inglés"}},
                {{"Morrison Family Services"}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {"5035423025"}, {""}, {""}},
                {{"Mountain Retreat Secured Transport"}, {"thora@spiritone.com"}, {"mtretreat.org"}, {""}, {""}, {""}, {""}, {"Manejo/Programa de educación vial"}, {""}, {""}, {""}, {""}, {"8666669895"}, {"niños", "Adolescentes", "Adultos típicamente entre centros de salud mental"}, {""}},
                {{"National College of Natural Medicine"}, {"ccsupport@ncnm.edu"}, {"http://www.ncnm-clinic.com"}, {"Bajo costo/Precios Móviles"}, {"La medicina naturopática"}, {""}, {"Visite tarifas van desde $20-$40 dependiendo del servicio. Clínica vierness luness abierto de 9AM-4PM. Debe ser capaz de pagar tarifa de descuento en tiempo de servicio."}, {"Clínica comunitaria", "Atención primaria", "Salud de la mujer", "Acupunctura", "Vacunación"}, {"222 SE 8th Ave. Suite 212"}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5033527333"}, {"Adultos"}, {"Inglés", "Español"}},
                {{"National Runaway Switchboard"}, {""}, {""}, {"Gratis"}, {""}, {""}, {""}, {"linea caliente"}, {""}, {""}, {""}, {"OR"}, {"8007862929"}, {""}, {""}},
                {{"Neighorhood House Community Services"}, {""}, {""}, {"Gratis"}, {""}, {""}, {"Lunes 10:00 am - 12:00-& 1:30 pm - 5:00 pm, Miercoles de 10:00 am - 12:00 pm-& 1:30pm - 5:00 pm, Jueves a las 2:00 pm a 8:00 pm"}, {"despensas"}, {"3445 SW Moss St."}, {"97219"}, {"Portland"}, {"OR"}, {"5032461663"}, {"personas de  West Multnomah County", "East Washington County", "North Portland", "Favor de llamar por localidad"}, {""}},
                {{"Northwest Regional Education Service Distrct"}, {"referral@nwresd.k12.or.us"}, {"http://www.nwresdeiecse.org"}, {"Gratis"}, {"Proyecciones del desarrollo y evaluaciones, orientación para los padres,  servicios especiales de educación, física , ocupaciones o terapia del habla y el lenguaje."}, {""}, {"Interpretes en español disponibles"}, {"Intervención temprana de enfermedades mentales"}, {"5825 NE Ray Circle"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5036141446"}, {"niños con problemas de desarrollo o retraso", "desde recien nacidos a los cinco años", "familias"}, {"Inglés", "Español"}},
                {{"OHSU Behavioral Health Center"}, {""}, {"ohsu.edu/xd/health/ohsu-near-you/index.cfm"}, {""}, {""}, {""}, {""}, {"Consejería"}, {""}, {""}, {""}, {"OR"}, {"5034944745"}, {""}, {""}},
                {{"Open Door Counseling Center"}, {"info@opendoorcc.net"}, {"http://www.opendoorcc.net"}, {"Gratis"}, {"duchas, Suministros de aseo personal"}, {""}, {"Abierto lunes-viernes 9AM-5PM, el albergue cierra el miércoles. Hay despensas disponibles de  lunes-viernes de 1pm hasta las 3pm. "}, {"HUD/consejería para la compra de casas", "Salud mental consejería", "Albergues", "manejo de casos", "uso del teléfono", "comidas calientes"}, {"34420 SW Tualatin Valley Highway"}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036406689"}, {"Adultos"}, {"Inglés"}},
                {{"Oregon Child Development Coalition"}, {"info@ocdc.net"}, {"http://www.ocdc.net"}, {"Gratis"}, {"Pre-Escolar, Educación al migrante, Cuidado de niños."}, {""}, {"Cunas para niños. Los programas de apoyo inicial estan disponibles para aquellas personas con ingresos familiares el 100% de la línea de pobreza federal, los ingresos de la familia deben venir de mano de obra agrícola, \"los trabajadores agrícolas migrantes\" deben haber migrado dentro de los últimos dos años. Se da prioridad a los niños con discapacidades y las familias afectadas por la carencia de vivienda u otras necesidades importantes."}, {"muebles", "apoyo inicial"}, {"9140 SW Pioneer Court Suite E"}, {"97070"}, {"Wilsonville"}, {"OR"}, {"5035701110"}, {"agricultores migrantes y familias", "familias con discapacidades significativas"}, {"Inglés", "Español"}},
                {{"Oregon Department of Veterans Affairs"}, {"davisinfo@co.washington.or.us"}, {"http://www.co.washington.or.us/HHS/DAVS/Veterans/index.cfm"}, {"Gratis", "Bajo costo"}, {"Asistencia con la vida independiente, consejería para la vida residencial"}, {"Telefono, asistencia en energía eléctrica"}, {"Abierto de lunes-viernes 8AM-4;30H."}, {"Asistencia financiera de emergencia", "Asistencia de beneficios", "Asistencia dentro del hogar", "Referencias"}, {"5240 NE Elam Young Parkway, Suite 300"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5038463060"}, {"Adultos mayores con discapacidades físicas"}, {"Inglés", "Español", "Interpretes disponibles"}},
                {{"Oregon DHS Children, Adults and Families Division for Washington County"}, {""}, {"http://www.oregon.gov/DHS/assistance/localoffices.shtml"}, {"Gratis, Bajo costo, ingresos basados en el cuidado del niño"}, {"Ayuda de los alimentos, ayuda en efectivo, Seguro médico y dental, asistencia en  Rentas, Programa de Autosuficiencia para sobrevivientes de violencia intrafamilia, Asistencia de Energía Eléctrica, Asistencia de Prescripción"}, {""}, {"El Programa de autosuficiencia en el maltrato intrafamiliar  puede ofrecer $1200 durante 90 días para depósitos, alquileres y servicios públicos. "}, {"Asistencia temporal", "Servicios para la vejez y la incapacidad", "beneficios patronales", "ayuda financiera", "asistencia en el cuidado de niños"}, {"5300 NE Elam Young Pky"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5036934555"}, {"sobrevivientes a maltrato intrafamiliar", "personas mayores", "individuales", "familias", "estudiantes", "Residentes de Oregon"}, {"Inglés", "Español", "Ruso", "Vietnamita", "Interpretes disponibles"}},
                {{"Oregon Human Development Corporation"}, {"info@ohdc.org"}, {"http://www.ohdc.org"}, {"Gratis"}, {"Programa de Ingles como segundo idioma."}, {""}, {"Los jóvenes participantes del programa deebn cumplir requisitos de ser recientes de la high school o GED abandonados, bajos de  ingresos y dispuestos a trabajar, unirse al ejército o unirse colegio tras completar el programa. Los participantes en el programa de trabajadores agrícolas deben ser ciudadanos de los Estados Unidos, o admitidos legalmente, no deben haber violado el artículo 3 de la Ley de Servicio Militar Selectiva y deben estar en  una  desventaja en el trabajado agrícola por temporada o durante cualquier período de 12 meses consecutivos en el último período de 24 meses, ya sea  cónyuge o dependiente de un trabajador que además tenga los dos primeros criterios. "}, {"Asistencia Laboral", "Referencias", "Servicio de abogados", "Educación"}, {"334 SE 5th St."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5036405496"}, {"Edades entre los 16-19 años", "agricultores", "personas con discapacidades"}, {"Inglés", "Español"}},
                {{"Oregon Korean Community Center"}, {"oregonkorean@gmail.com"}, {"oregonkorean.org"}, {"Gratis"}, {"Traducción, interpretación, eduation con ESL y clases de ciudadanía, exámenes de salud gratuitos, programas para ancianos, programas para jóvenes (por ejemplo, de consejería), relacionados a otros servicios sociales."}, {""}, {""}, {"Centro de recursos comunitarios"}, {"12555 SW 4th St."}, {"97005"}, {"Beaverton"}, {"OR"}, {"5036417887"}, {""}, {"Inglés", "Coreano"}},
                {{"Oregon Law Center"}, {"laurie.hoefer@lasoregon.org"}, {"http://www.oregonlawcenter.org"}, {"Gratis"}, {"Ley administrativa, ley en empleos, Ley familiar, Ley en la agricultura, Ley del hogar, Projectos ingeniosos"}, {""}, {"Ayuda a proporcionar servicios legales civiles para \"asegurar justicia sobre asuntos relacionados a las necesidades básicas como alimentos, vivienda, atención médica, ingreso y seguridad física\". oficina abierta de 9AM-5PM lunes, miércoles , juevess y vierness y de 11-7 el martes. "}, {"Derechos de inquilino", "consejería Legal  y Representación"}, {"230 NE Second Ave. Suite F"}, {"97124"}, {"Hillsboro"}, {"OR"}, {"5036404115"}, {"bajos recursos individuales"}, {"Inglés", "Español", "Mixteco", "Interpretes adicionales cuando son requeridos"}},
                {{"Oregon Rx Card"}, {"igonzales@oregonrxcard.com"}, {"oregonrxcard.com"}, {"Gratis"}, {""}, {""}, {""}, {"Prescription Discount Card"}, {"Website only"}, {"97228"}, {"Portland"}, {"OR"}, {"8772793424"}, {""}, {""}},
                {{"Oregon Somali Family Education Center"}, {"sfec1@yahoo.com"}, {"osfec.org"}, {"Gratis"}, {""}, {""}, {"La enseñanza, la educación de los padres y el apoyo, los deportes y la recreación, clases de ESL, habilidades, educación para la salud.  "}, {"Asistencia a inmigrantes"}, {"2032 SW Merlo Ct."}, {"97006"}, {"Beaverton"}, {"OR"}, {"5039956031"}, {"niños y sus familias de somalia"}, {""}},
                {{"Oregon State Bar Modest Means Program"}, {""}, {"osbar.org"}, {"$35 por primera consulta,reducción de retención y tarifa por hora basado en los ingresos"}, {""}, {""}, {"Sólo manejan el arrendador y arrendatario, el derecho de familia y defensa criminal."}, {"servicio legal"}, {"16037 SW Upper Boones Ferry Rd."}, {"97224"}, {"Tigard"}, {"OR"}, {"8004527636"}, {"Personas de Oregon con bajos recursos"}, {"Inglés", "Español"}},
                {{"OrFIrst"}, {""}, {""}, {""}, {""}, {""}, {""}, {"educación especial"}, {""}, {""}, {""}, {"OR"}, {"5032320302"}, {""}, {"Inglés", "Español"}},
                {{"Our Saviors Lutheran Church"}, {"oslc@oursaviors.net"}, {"info@oursaviors.net"}, {""}, {"Provee un suministro de memergencia de tres días de duración  en alimentos no perecederos para las personas y familias necesitadas."}, {""}, {""}, {"despensas"}, {"15751 SW Quarry Road"}, {"97034"}, {"Lake Oswego"}, {"OR"}, {"5036354563"}, {""}, {""}},
                {{"Outside In"}, {""}, {""}, {""}, {""}, {""}, {""}, {"Consejería"}, {""}, {""}, {""}, {""}, {"5035353800"}, {"personas sin hogar", "Jóvenes", "Adultos sin seguro"}, {""}},
                {{"Pacific University College of Optometry"}, {""}, {"http://www.pacificu.edu/our-resources/clinics/eyeclinics/pacific-eyeclinic-hillsboro"}, {"aceptan OHP, Comisiones reducidas, planes de pago"}, {""}, {""}, {"Abierto los lunes-viernes 8AM-5PM. Cerrado todos los días desde el mediodía-1PM. Honorarios por servicios que no se encuentran en la evaluación completa son extras y no son ofrecidos con descuento."}, {"Servicio de visión"}, {"222 SE 7th Ave."}, {"97123"}, {"Hillsboro"}, {"OR"}, {"5033527300"}, {"bajos recursos", "Adultos", "niños"}, {"Inglés"}},
                {{"Pacific University Dental"},{"dentalhealth@pacificu.edu"},{"http://www.pacificu.edu/our-resources/clinics/pacific-dental-hygiene-clinic"},{"Diferentes opciones de pago basado en la página web http://www.pacificu.edu/our-resources/clinics/pacific-dental-health-clinic/pacific-dental-health-clinic-services"},{"Los servicios básicos de restauración (no hay coronas , extracciones dentales, emergencias o atención dental integral)"},{""},{"Horario lunes 8AM hasta el mediodía, 1;30PM-5PM, 6PM-9PM, martes 8AM-1130AM, 1PM-5PM, miércoles 8AM hasta el mediodía, 130PM-5PM, 6PM-9PM, jueves de 8 de la mañana a las 12.00 horas. Las citas pueden durar hasta 4 horas, debido a la naturaleza de la situación clínica. "},{"Cuidado dental"},{"222 SE 8th Ave. Suite 270"},{"97123"},{"Hillsboro"},{"OR"},{"5033527373"},{"edades de 3 años en adelante"},{"Inglés"}},
                {{"Partnership for Prescription Assistance Oregon"},{""},{"pparxor.org"},{"Gratis"},{""},{""},{""},{"Asistencia de prescripción médica"},{"website only"},{""},{""},{""},{"8884772669"},{"Individuos cualificados"},{""}},
                {{"Patient Assistance Program Center"},{""},{"rxassist.org"},{"Gratis"},{""},{""},{"Base de datos de programas de asistencia para medicamentos con prescripción, junto con otra información relacionada con la salud."},{"Asistencia de prescripción médica"},{"website only"},{""},{""},{""},{"-5"},{""},{""}},
                {{"Portland Womens Crisis Line"},{""},{""},{"Gratis"},{""},{""},{""},{"línea de crisis","Violencia Intrafamiliar"},{""},{""},{""},{""},{"5032355333"},{""},{"Español"}},
                {{"Portland Communit College"},{""},{"pcc.edu/resources/women/rock-creek/new-directions.html"},{"Gratis"},{""},{"Algunas transporte y cuidado de niños disponibles"},{""},{"Centro de atención laboral"},{"17705 NW Springville Rd."},{"97229"},{"Portland"},{"OR"},{"9717227448"},{"Mujeres que son mamás solteras o amas de casa."},{""}},
                {{"Portland Vineyard Church - Kings Kindness"},{""},{"portlandvineyard.org"},{"Gratis"},{""},{""},{"Abierto Jueves de 12am-3pm. Prueba de residencia en el Condado de Washington o identificación necesaria."},{"despensas"},{"11460 SW 114th Ave."},{"97224"},{"Tigard"},{"OR"},{"5036848225"},{""},{""}},
                {{"Project Respond"},{""},{""},{"Gratis"},{"Recursos y grupos de ayuda para sobrevivientes de abuso sexual y maltrato intrafamiliar."},{""},{""},{"Línea de Ayuda","línea de crisis"},{""},{""},{""},{""},{"5039884888"},{""},{""}},
                {{"Providence Health and Services"},{""},{"http://www.providence.org/oregon"},{"aceptan OHP, Comisiones reducidas, planes de pago"},{""},{""},{"Abierto los 24 días /7 días de la semana"},{"Cuidados urgentes"},{"18610 NW Cornell Rd. Suite 300"},{"97124"},{"Hillsboro"},{"OR"},{"5032154300"},{"abierto a Todas las edades"},{"Inglés", "Español", "Vietnamita"}},
                {{"Proyecto Unica (Catholic Charitiies)"},{""},{"catholiccharitiesoregon.org/latino_services_unica.asp"},{""},{""},{""},{""},{"línea de crisis", "Violencia Intrafamiliar"},{""},{""},{""},{""},{"5032324448","8882324448"},{""},{"Español"}},
                {{"Puentes/Central City Concern"},{""},{"centralcityconern.org/changing-lives/puentes.html"},{""},{""},{""},{""},{"Violencia Intrafamiliar"},{"232 NW 6th Ave."},{"97209"},{"Portland"},{"OR"},{"5032941681"},{""},{"Español"}},
                {{"Raphael House"},{""},{"raphaelhouse.com"},{""},{"Hospedaje de emergencia, Servicio de Abogados, Programa Juvenil, Hospedaje transitorio"},{""},{"línea de crisis: 503-222-6222"},{"Violencia Intrafamiliar"},{"4110 SE Hawthorn Blvd. #503"},{"97214"},{"Portland"},{"OR"},{"5032226507"},{""},{""}},
                {{"Ride Connection"},{"triprequests@rideconnection.org"},{"triprequests@rideconnection.org"},{"Gratis/donaciones requeridas de $1.50 pero aplicable en todos los casos"},{""},{""},{"Oficina abierta de lunes-viernes 7;30AM-5PM. Horarios disponibles presencialmente  o por teléfono."},{"transporte"},{"9955 NE Glisan St."},{"97220"},{"Portland"},{"OR"},{"5032260700"},{"Debe tener un seguro OHP para calificar a servios gratuitos, o ser una persona mayor, o con una incapacidad. "},{"Inglés","Interpretes disponibles cuando se necesitan."}},
                {{"Ride to Care"},{""},{"https://www.ridetocare.healthcare/about/contact-us"},{"Gratis a clientes OHP"},{""},{""},{"Puede ordenar en línea 24 horas antes de tiempo, si se llama debe hacerlo 2 días antes de los servicios. Usted puede llamar o solicitar servicios de hasta tres meses de anticipación. Debe tener seguro de OHP y estar en necesidad de un traslado con prescripción médica donde podrá usar el seguro. También puede solicitar un tralado por una emergencia, o a la farmacia. Los días hábiles son de lunes-viernes7AM-7PM. Usted puede traer a una persona con usted como una persona de apoyo. "},{"transporte médico"},{""},{""},{""},{""},{"18553214899"},{"OHP con planes"},{"Inglés"}},
                {{"Rock Creek Church EFB"},{""},{""},{"Gratis"},{"Comidas los Jueves de 6-7pm."},{""},{"Abierto M 3-4pm, T 9am-12pm y 4-7pm. luness solo por cita. Por favor llame a día de para el nombramiento de todos los demás días."},{"despensas"},{"4470 NW 185th Ave."},{"97229"},{"Portland"},{"OR"},{"5036452525"},{""},{""}},
                {{"Rolling Hills Community Church"},{"info@rollinghills.org"},{"http://www.rollinghills.org"},{"Gratis"},{""},{""},{"Abierto Miércoles a través de 3/2/16, 630PM-7AM, la ingesta de 630pm-8pm. No se permiten mascotas"},{" refugio para climas fríos","consejería matrimonial","vestimenta laboral", "consejería grupal de recuperación"},{"3550 SW Borland Rd"},{"97062"},{"Tualatin"},{"OR"},{"5036385900"},{"Adultos de mas de 18 años"},{"Inglés"}},
                {{"Safe Journeys, LLC"},{""},{""},{"costo reducido"},{"Servicios foráneos de hospitalización y servicios adicionales."},{""},{"Auto pago y seguros privados de salud."},{"Servicios de prevención de adicción del juego(casinos, apuestas,etc)"},{"18801 SW Martinazzi Ave. Building A, Suite 206"},{"97062"},{"Tualatin"}, {"OR"},{"5039890991"},{"Adultos"},{"Inglés"}},
                {{"Safe Place for Youth - Boys and Girls Aid"},{"info@boysandgirlsaid.org"},{"http://www.boysandgirlsaid.org"},{"Gratis"},{"Vestimenta, duchas, manejo de casos, lavanderia, Comidas y frituras"},{""},{"No se permiten mascotas, no deben estar bajo el influjo de drogas o alcohol, deben ser capaces de desenvolverse en un entorno de grupo, no pueden ser suicidas o que te esten en una crisis de salud mental, manejando un tratamiento o atención médica que no es provisto en la planta. También tienen instalaciones para las niñas de 10-18 años, y un programa de viviendas de transición de 16-23 años de edad. "},{"refugio para clima frío", "servicios con línea telefónica disponible las  24 horas los 7 días de la semana","Referencias"},{"454 SE Washington St."},{"97123"},{"Hillsboro"},{"OR"},{"5035422717"},{"Jóvenes entre los 13-19 años"},{"Inglés"}},
                {{"Saint Child"},{"office@saintchild.org"},{"saintchild.org"},{""},{"Apoyo al Embarazo"},{""},{""},{"alojamiento transitorio", "Apoyo para mujeres embarazadas"},{"Confidentilal"},{"97005"},{"Beaverton"},{"OR"},{"5036484227"},{"Mujeres embarazadas entre los 14-24 años que no tengan niños y que tengan menos de 7 meses de embarazo."},{""}},
                {{"Salud Services"},{"leda.garside@tuality.org"},{"http://www.saludauction.org/"},{"aceptan OHP, bajo costo"},{""},{""},{"Acepta el Plan de Salud de Oregon seguro. Se negocian precios y pago de servicios si las personas son bajos en ingresos o tienen dificultades financieras."},{"Clínica comunitaria","Clínica de salud", "Clínica dental"},{"335 SE 8th Ave."},{"97123"},{"Hillsboro"},{"OR"},{"5036814290"},{"abierto a Todas las edades."},{"Español","Inglés"}},
                {{"Salvation Army Tualatin Valley Citadel"},{""},{"http://www.salvationarmyportland.org/portland/tualatin_valley_citadel"},{"Gratis"},{""},{""},{"Sólo disponible para residentes de Hillsboro. La asistencia alimentaria disponible lunes/martes/jueves/viernes 1AM-3PM, sábado 9:00 AM-1130"},{"apoyo en la factura del agua","despensa de alimento","artículos escolares"},{"351 SE Oak St."},{"97123"},{"Hillsboro"}, {"OR"},{"5036404311"},{"abierto a Todas las edades."},{"Inglés"}},
                {{"Salvation Army Veterans and Family Center"},{"savets@usw.salvationarmy.org"},{"cascade.salvationarmy.org/veterans_and_family_center"},{""},{""},{""},{"Debe ser contemplado desde la AV."},{"recuperación de adicciones","refugio", "Violencia Intrafamiliar", "Asistencia alimenticia","Apoyo a jovenes embarazadas", "servicios para personas mayores"},{"14825 SW Farmington"},{"97007"},{"Beaverton"},{"OR"},{"5032931259"},{"Veteranos y familias"},{""}},
                {{"Samaritans Kitchen at True Life Church"},{""},{""},{"Gratis"},{""},{""},{"Comidas segundo y cuarto Sábado del mes 10:00am-12:00pm."},{"comidas"},{"1895 NW 169th Pl."},{"97006"},{"Beaverton"},{"OR"},{"5038107436"},{""},{""}},
                {{"Sequoia Mental Health Services"},{"info@sequoiamhs.org"},{"http://www.sequoiamhs.org"},{""},{"Servicios infantiles y para la familia, Terapia invidual, Terapia grupal, Servicios para adolescentes, Terapia familiar, manejo de casos"},{""},{""},{"Evaluación de salud mental", "Consejería", "apoyo de alojamiento" , "Tratamiento de residentes", "abuso de sustancias/tratamiento de adicciones"},{"395 W Main St."},{"97123"},{"Hillsboro"},{"OR"},{"5036191560"},{"individuales", "familias", "Adolescentes"},{"Inglés"}},
                {{"Seventh Day Adventist Church"},{"hillsborosda@yahoo.com"},{"http://www.hillsborosda.org/article/21/the-ministries-of-our-church/community-service"},{"Gratis"},{""},{""},{"Abierto jueves desde 6;30pm-8pm."},{"vestimenta", "despensas"},{"367 NE Grant St"},{"97124"},{"Hillsboro"},{"OR"},{"5036483922"},{"familias"},{"Inglés"}},
                {{"Sonrise Church"},{"mercy@isonrise.com"},{"http://www.isonrise.com"},{"Gratis"},{"duchas en noches designadas, accesorios de higiene personal, busqueda de empleos"},{""},{"despensas disponible jueves 12PM-130H. Refugio Abierto lunes-viernes 5;45PM-830h, sábados de 8PM-7;30h, domingos 545-830AM. Pruebas para la tuberculosis son necesarios para pasar la noche. Espalda para dormir y colchonetas. Los hombres y las mujeres están obligadas a dormir en las diferentes áreas. "},{"vestimenta", "despensas", "refugio"},{"6701 NE Campus Way"},{"97124"},{"Hillsboro"},{"OR"},{"5036402449"},{"familias","individuales","Adultos"},{"Inglés"}},
                {{"Southwest Community Health Center"},{"contact@swchc-pdx.org"},{"http://www.swchc-pdx.org"},{"precio móvil"},{""},{""},{"Abierto y luness juevess desde 530PM-830H - paseo en tiempo y las citas programadas."},{"Clínica comunitaria", "auxilios primarios", "servicios dentales de emergencia", "prescripción médica médica", "Vacunación", "Salud preventiva"},{"226 W Main St."},{"97123"},{"Hillsboro"},{"OR"},{"5038464418"},{"bajos recursos","individuales", "familias", "no asegurados", "niños"},{"Inglés"}},
                {{"St Andrew Legal Clinic"},{"info@salcgroup.org"},{"http://www.salcgroup.org"},{"precio móvil, $25 por primera consulta"},{""},{""},{"Escala móvil basada en los ingresos, el tamaño de la familia, y la capacidad de pago. Abierto de 9AM-5PM con descanso de mediodía-1PM. Las incorporaciones el miércoles por la noche."},{"Ley familiar", "custodia infantil"},{"232 NE Lincoln St. Suite H"},{"97124"},{"Hillsboro"},{"OR"},{"5036481600"},{"familias", "niños", "bajos y moderos recursos"},{"Inglés", "Español"}},
                {{"St. Anthony Catholic Church"},{""},{"stanthonytigard.org"},{"Gratis"},{""},{""},{"Abierto Lunes,martes,Viernes de 12pm-2:30pm, Miercoles 1-3pm, Jueves 12pm-2:30pm y las 4:30-6:30pm, Sábados de 10.00 a 12.00 h."},{"despensas"},{"9905 SW McKenzie St."},{"97223"},{"Tigard"},{"OR"},{"5036394179"},{""},{""}},
                {{"St. Anthonys Catholic Church - Tigard"},{""},{"stanthonytigard.org"},{"Gratis"},{""},{""},{"Abierto sábados 11/7/15-3/26/16 y dias de clima extremo. Horas 5:30pm-7am (deben registrarse antes de las 9pm cuando las puertas se cierran)."},{"Severe Weather refugio"},{"9905 SW McKenzie St."},{"97223"},{"Tigard"},{"OR"},{"-6"},{"Sólo Adultos.  No mascotas."},{""}},
                {{"St. Bartholomew Episcopal"},{"welcomehome@stbartsbeaverton.org"},{""},{"Gratis, ELS clases ELS de $5 por 10 semanas"},{""},{""},{"Abierto Vienes 1:30pm-4:30pm para los alimentos. Las clases de ESL son los jueves de 6:30-8pm"},{"despensas","clases para ESL"},{"11265 SW Cabot St."},{"97005"},{"Beaverton"},{"OR"},{"5036443468"},{""},{""}},
                {{"State of Oregon Employment Department"},{""},{"oregon.gov/EMPLOY"},{"Gratis"},{""},{""},{""},{"desempleo, seguros de empleo"},{"875 Union St. NE"},{"97311"},{"Salem"},{"OR"},{"8773453484"},{""},{""}},
                {{"Sunshine Pantry"},{""},{""},{"Gratis"},{""},{""},{"Abierto de lunes a viernes: de 11.00 a 14.00 h."},{"despensas"},{"10895 SW 5th St."},{"97008"},{"Beaverton"},{"OR"},{"9715067827"},{""},{""}},
                {{"SVDP St. Anthony Community Café"},{""},{""},{"Gratis"},{""},{""},{"Domingo de  5:30pm a 7:00pm"},{"comidas"},{"9905 SW McKenzie St."},{"97223"},{"Tigard"},{"OR"},{"5033092121"},{""},{""}},
                {{"SVDP St. Anthonys"},{""},{""},{"Gratis"},{""},{""},{"Lunes de 12:00 pm-2:30 pm, Martes 12:00 pm-2:30 pm Miercoles 1:00 pm-3:00 pm Jue 12:00-2:30 &4:30-6:30pm, Viernes a las 12:00 pm-2:30 pm, Sábados de 10:00am-12:00 pm. Prueba de residencia requerido."},{"despensas"},{"12630 SW Grant Ave."},{"97223"},{"Tigard"},{"OR"},{"5036848280"},{""},{""}},
                {{"Therapy Matters"},{"therapymatters@zoho.com"},{"http://therapymatters.zohosites.com"},{""},{""},{""},{""},{"Consejería"},{"16780 SW Bryant Road #202, West Bay Offices"},{"97035"},{"Lake Oswego"},{"OR"},{"5035446573"},{""},{""}},
                {{"Tigard Community Basket"},{""},{""},{"Gratis"},{""},{""},{"Tercer Jueves del mes de 1-2pm. Trae tu propia bolsa de dormir."},{"despensas"},{"11905 SW 91st Ave."},{"97223"},{"Tigard"},{"OR"},{"5032934038"},{""},{""}},
                {{"Tigard UMC - Bethlehem House of Bread"},{""},{""},{"Gratis"},{""},{""},{"Abierto Domingos 1:00pm-3:00pm."},{"despensas"},{"9055 SW Locust"},{"97223"},{"Tigard"},{"OR"},{"-7"},{""},{""}},
                {{"Trans Lifeline"},{""},{"http://hotline.translifeline.org"},{"Gratis"},{""},{""},{"Generalmente disponible lunes de 12AM-1AM, 9AM-9PM y 11PM-medianoche, martes 12AM-3AM, 10AM-5PM, de 1pm a 10pm y de 2 PM a medianoche, miércoles de 12AM-3AM, 11AM-12PM, de 1pm a 10pm y de 2 PM a medianoche, juevess 12AM-8AM, 10H, 12H, 1PM-10PM y 2PM-medianoche vierness 12AM-3AM, 1PM-10PM y 2PM-11PM, sábados de 10.00 a 22.00 h y de 14.00 a 24.00 h, Domingos de 9AM-6PM, de 10am a 7PM, 4pm-9pm y de 7pm a la medianoche EST."},{"línea de crisis"},{""},{""},{""},{""},{"8775658860"},{"Personas transitorias"},{"Inglés"}},
                {{"Trevor Project"},{""},{"http://www.thetrevorproject.org"},{"Gratis"},{"Redes de seguros"},{""},{"Enviar la palabra de texto \"Trevor\" al 1-202-304-1200, disponible y viernes jueves 1pm-5pm. El \"TrevorChat\" esta disponible los 7 días de la semana de 12pm-6pm PST. El \"TrevorSpace\" esta disponibles de forma segura a la red de jóvenes LGBTQ (edades 13-24)."},{"línea de crisis de 24 horas los 7 días de la semana","Servicio de chat de texto en llínea"},{""},{""},{""},{""},{"8664887386"},{"Lesbianas","gays", "bisexuales", "transgenero",   "jovenes entre los  13-24 años"},{"Inglés"}},
                {{"Trimet"},{"accessible@trimet.org"},{"http://trimet.org/fares/honoredcitizen.htm"},{"bajo costo/costo reducido http://trimet.org/fares/index.htm#farechart"},{""},{""},{"Abierto 8;30am-5:30pm lunes-viernes. Rutas a realizarse todos los días generalmente de 430am-230am. Si usted es incapaz de utilizar transporte TRIMET regular, usted puede ser elegible para aplicar para subir al camión, el cual es un viaje compartido de servicio público de transporte. A fin de aplicar debe visitar el Centro de movilidad de tránsito Trimet en 515 NW Davis St. Portland, OR 97208 - 503-962-8200."},{"transporte de bajo costo"},{""},{""},{""},{""},{"5039622455"},{"Personas mayores de edad y con  discapacidades"},{"Inglés", "Español"}},
                {{"True Life Fellowship"},{""},{""},{"Gratis"},{""},{""},{"Abierto M 12:00pm-2:00pm, F 12:00pm-2:00pm, el segundo y el cuarto Sábado 10:00am-1:00pm."},{"despensas"},{"1895 NW 169th Pl."},{"97006"},{"Beaverton"},{"OR"},{"5038107426"},{""},{""}},
                {{"Tualatin Public Library"},{"librarymail@ci.tualatin.or.us"},{"http://www.tualatinoregon.gov/library/esl-tutors-english-second-language-tutores-de-ingl%C3%A9s"},{"Gratis","bajo costo"},{""},{""},{"Biblioteca Abierto lunes-jueves 10AM-PM, viernes-sábado de 10am-6pm, Domingo 1pm-6pm."},{"Idioma ingles", "Ténicas", "tareas de tutores"},{"18878 SW Martinazzi Ave."},{"97062"},{"Tualatin"},{"OR"},{"5036913072"},{"estudiantes, que no hablen ingles."},{"Inglés", "Español"}},
                {{"Tualatin School House Pantry"},{"pantry@schoolhousepantry.org"},{"http://www.schoolhousepantry.org"},{"Gratis"},{""},{""},{"Abierto Lunes 3PM-8PM, miércoles y viernes 10AM-3PM. Identificación y prueba de residencia en Tualatin, Durham, Lake Oswego o West Linn es obligatorio cuando se visita la despensa. La despensa incluyen cinco días de comida y suministros personales y están disponibles una vez al mes. Furgonetas Dental ver 12 pacientes o así por visita, los tiempos de espera pueden ser de 2-3 meses antes de conseguir una cita. Para ser agregado a la lista de espera por favor llame antes de visitarlos. "},{"Dental Van", "banco de alimentos"},{"3550 SW Borland Rd."},{"97062"},{"Tualatin"}, {"OR"},{"5037830721"},{"Adultos", "familias", "individuales", "niños (chequeo dental)"},{"Inglés"}},
                {{"Tuality Healthcare"},{"jenn.lunzmann@tuality.org"},{"http://www.tuality.org"},{"Varia"},{"Programas de mejoramiento de alimentacion"},{"La ayuda financiera disponibles en base a criterios (incluye la presentación de documentos de impuestos, cartas , identificaciones emitidas por el gobierno, cartas de asistencia de la escuela y los extractos bancarios )"},{"Para el apoyo la lactancia materna los participantes deben tener niños menores de dos años de edad. La completa Asistencia financiera puede ser solicitada y lo más probable es que se otorgue a aquellos con ingresos familiares por debajo del 200% de la línea de pobreza Federal. Se dan descuentos para quienes no tienen seguro de salud y disminuye la cantidad adeudada por 10 a 50 por ciento dependiendo del saldo de la cuenta del paciente."},{"transporte médico", "Atención de salud urgente", "Atención médica"},{"335 SE 8th Ave.","7545 SE TV Highway"},{"97123","97123"},{"Hillsboro", "Hillsboro"},{"OR","OR"},{"5036811818","5036814223"},{"abierto a Todas las edades."},{"Inglés", "Español"}},
                {{"TVW Inc"},{""},{"http://www.tvwinc.org"},{"Gratis"},{""},{""},{"Abierto de 8am-4pm lunes-viernes."},{"Ayuda laboral", "Programas de escuela transitoria"},{"6615 SE Alexander St."},{"97123"},{"Hillsboro"}, {"OR"},{"5036498571"},{"Discapacitados física o mentalmente,  de 18 años y mas grandes"},{"Inglés"}},
                {{"Unity of Beaverton"},{""},{""},{"Gratis"},{""},{""},{"Abierto Martes,miercóles 10am-4pm. Traer tu propia bolsa de dormir."},{"despensas"},{"12650 SW 5th St."},{"97005"},{"Beaverton"},{"OR"},{"5036463364"},{""},{""}},
                {{"Veterans Crisis Line"},{""},{"http://www.veteranscrisisline.net"},{""},{""},{""},{""},{"linea caliente"},{""},{""},{""},{""},{"8002738255"},{""},{""}},
                {{"Virginia Garcia Memorial Health Center"},{"info@vgmhc.org"},{"http://virginiagarcia.org/locations/hillsboro"},{"aceptan OHP, precio móvil"},{"Primeras atenciones, cuidado dental preventivo o restaurador para niños, cuidado dental para adultos, ayuda a solicitar los beneficios cubiertos por Oregon"},{""},{"Abierto lunes-viernes 8AM-7PM, las excepciones son el primer, el tercero y el quinto miércoles del mes desde la 1pm-2pm. Precio movil a disposición de bajos ingresos comprobables y copagos de Medicare tradicional. Además influenciados por el tamaño de la familia. "},{"Clínica comunitaria", "Clínica dental","servicios de Salud mental"},{"226 SE 8th Ave."},{"97123"},{"Hillsboro"}, {"OR"},{"5036017400"},{"migrantes y agricultores por temporadas", "niños arriba de los 21", "bajos recursos"},{"Inglés", "Español"}},
                {{"Virginia Garcia Memorial Health Center - Cornelius"},{""},{"http://virginiagarcia.org/locations/cornelius/"},{""},{"Cuidados primarios, farmaceuticos, dentales, de vision, y clases de bienestar"},{""},{""},{"Consejería"},{"1151 N. Adair St."},{"97113"},{"Cornelius"},{"OR"},{"5033595564"},{"Individual","parejas"},{"Inglés","Español"}},
                {{"Washington County Community Corrections"},{"volntr@co.washington.or.us"},{"http://www.co.washington.or.us/CommunityCorrections/VictimServices/index.cfm"},{"Gratis"},{"Individual, Couples, Family, Consejería grupal"},{""},{"Asesoría general libre. Para facilitación de visita infantil esta abierto los fines de semana. Horas de operación de 8:30am-5pm lunes-viernes."},{"servicios de consejería","Facilitación visita de padres a sus hijos", "Servicio de abogados", "Referencias"},{"160 SW Washington St."},{"97124"},{"Hillsboro"},{"OR"},{"5038463020"},{"abierto a Todas las edades."},{"Inglés", "Español"}},
                {{"Washington County Cooperative Library Services"},{"libraryfeedback@hillsboro-oregon.gov"},{"http://www.wccls.org/libraries/hillsboroshutepark"},{"Gratis"},{"Programa de Inglés como segundo idioma"},{""},{"Biblioteca Abierta de lunes a miércoles de 10am-8pm, de jueves a sábado de 10am-6pm, Domingo 12pm-6pm. "},{"Computadoras públicas", "educación continua"},{"775 SE 10th Ave"},{"97123"},{"Hillsboro"},{"OR"},{"5036156500"},{"abierto a Todas las edades."},{"Any"}},
                {{"Washington County Crisis Line"},{""},{""},{"Gratis"},{""},{""},{""},{"línea de crisis"},{""},{""},{""},{"OR"},{"5032919111"},{""},{"Inglés"}},
                {{"Washington County Department of Housing Services"},{"washco_housing@co.washington.or.us"},{"http://www.co.washington.or.us/housingpreap"},{"Gratis/bajo costo"},{""},{""},{"Waitlists están actualmente cerrados. Aquellos con ingresos combinados de 50% del promedio nacional puede solicitar los vales de vivienda y en la sección 8. Las tarifas de alquiler son normalmente el 30% de los ingresos del solicitante."},{"alojamiento público", "alojamiento para gente de bajos ingresos", "HUD y sección de 8 aplicaciones"},{"111 NE Lincoln St. Suite 200-L"},{"97124"},{"Hillsboro"},{"OR"},{"5038464794"},{"personas que quedaron sin hogar recientemente, 80% de medianos recursos o menos de eso"},{"Inglés", "Español", "Interpretes disponibles"}},
                {{"Washington County District Attorny"},{"childsupport@co.washington.or.us"},{"http://www.co.washington.or.us/DistrictAttorney/ChildSupport/index.cfm"},{"Gratis"},{"Esteablecimiento de paternidad, Apoyo en Establecimiento de niños u ordenes médicas, Modificar la  Orden de apoyo médico, apoyo para la toma de paternidad del niño"},{""},{"Abierto de 8am-5pm lunes-viernes."},{"Soporte Legal", "Ayuda Infantil"},{"150 N 1st Ave. Suite 300"},{"97124"},{"Hillsboro"}, {"OR"},{"5038468759"},{"familias con niños"},{"Inglés", "Español"}},
                {{"Washington County Juvenile Department"},{""},{"http://www.co.washington.or.us/Juvenile/HarkinsHouse/"},{"Gratis"},{"Investigaciones de apoyo para la custodia"},{""},{"La mediación está disponible a los residentes del Condado de Washington con niños que actualmente tienen Abierto algun caso de relaciones domésticas. Dos sesiones son gratis, despues se cobra $80 por sesión. Las citas son ofrecidos lunes 8;30AM-10;15AM. Asesoramiento disponible para problemas de pareja o marital, preocupaciones co-padres  y separación o divorcio. Debe ser un residente del Condado de Washington con los niños. Las primeras cuatro sesiones son gratis, luegose cobra un tarifa de $80 por sesión."},{"alojamientos temporales", "consejería de divorcio", "Mediación"},{"24 W Main St."},{"97123"},{"Hillsboro"},{"OR"},{"5038468766"},{"Edades entre los 12-17 años"},{"Inglés", "Español", "Interpretes disponibles"}},
                {{"Washington County Mental Health Services"},{"vikki_moore@co.washington.or.us"},{"http://www.co.washington.or.us/hhs"},{"Gratis"},{"Interpretes"},{""},{"A fin de recibir tratamiento debe tener un trastorno de salud mental que está cubierto por el Plan de Salud de Oregon que afecta negativamente a su capacidad para funcionar en la casa o en la comunidad, o ser un residente del Condado de Washington de bajos ingresos que no tienen seguro. Si no está disponible un médico que hable su idioma, se proveerá un intérprete."},{"línea de crisis", "Consejería", "Educación", "Salud de la mujer", "planificación familiar", "Residential and servicios Salud mental para foráneos"},{"155 N 1st Ave. Suite 250,"},{"97124"},{"Hillsboro"}, {"OR"},{"5038462815"},{"Adultos", "niños", "Individual"},{"All"}},
                {{"Western Psychological and Counseling"},{"emilyh@westernpsychservices.com"},{"http://www.westernpsych.com/"},{"aceptan OHP, costo reducido"},{"Terapias en grupo o individuales. Clases y seminarios."},{""},{""},{"Programas de atención de salud mental para foráneos", "Programas de autismo", "Programas escolares de educación"},{"21210 NW Mauzey Rd"},{"97124"},{"Hillsboro"},{"OR"},{"5034399531"},{"Individual", "Familiar", "Grupal", "Adultos", "niños"},{"Inglés"}},
                {{"Western Pyschological Counseling"},{""},{"westernpsych.com"},{""},{""},{""},{""},{"Consejería"},{""},{""},{""},{""},{"5032355405"},{""},{""}},
                {{"Westside Community Church - Food Brigade"},{""},{""},{"Gratis"},{""},{""},{"Abierto Martes 6:30pm-8:00pm, Sábados de 9:30am-11:00am"},{"despensas"},{"18390 SW Farmington Rd."},{"97007"},{"Beaverton"},{"OR"},{"5038770256"},{""},{""}},
                {{"Willsonvile Community"},{""},{"http://wilsonvillecommunitysharing.org/Home/tabid/41/Default.aspx"},{"Gratis"},{""},{""},{"Banco de Alimentos Horario: martes 1PM-3PM, 6PM-8PM. Usted puede visitar el banco de alimentos mensuales una vez y antes de tomar sus primeros alimentos del banco debe mostrar prueba de residencia en Wilsonville. Determinación de asistencia con servicios públicos y sprescripociones médicas se determinan sobre la base de ingresos y requieren una reunión con un especialista de información y referencia. Las horas de oficina para estas reuniones son los 9am-3pm lunes-viernes."},{"refugio", "Asistencia alimenticia", "Asistencia de accesorios", "Asistencia de prescripción médica", "Asistencia de renta"},{"28925 SW Boberg Rd."},{"97070"},{"Wilsonville"}, {"OR"},{"5036826939"},{"personas sin hogar", "bajos recursos", "individuales", "Familiares"},{"Inglés"}},
                {{"Willsonville Community Center Daytime Warming Center"},{""},{"http://www.wilsonvilleparksandrec.com/Facility/Details/Community-Center-22"},{"Gratis"},{""},{""},{"No mascotas, Abierto los lunes a viernes de 8AM-5PM."},{"El calentamiento diurno"},{"7965 Wilsonville Rd."},{"97070"},{"Wilsonville"}, {"OR"},{"5036823727"},{"abierto a Todas las edades"},{"Inglés"}},
                {{"Womens Counseling Center"},{""},{""},{""},{""},{""},{""},{""},{""},{""},{""},{""},{"5032354050"},{""},{""}},
                {{"Worksource Oregon"},{""},{"http://www.worksourceoregon.org"},{"Gratis"},{""},{""},{"Abierto de 8AM-5PM de lunes a viernes. Servicios de idiomas disponible sin coste bajo petición."},{"Ayuda laboral"},{"7995 SW Mohawk St."},{"97062"},{"Tualatin"},{"OR"},{"5036124200"},{"Veteranos sin recursos", "Adultos"},{"All"}},
                {{"WorkSource Oregon"},{""},{"worksourceoregon.org"},{"Gratis"},{""},{""},{""},{"Centro de atención laboral"},{"241 SW Edgeway Dr."},{"97006"},{"Beaverton"},{"OR"},{"5035262700"},{""},{""}},
                {{"YMCA of Columbia-Willamette"},{"beavertonhoop@ymca.org"},{""},{""},{""},{""},{""},{"Centro juvenil"},{"9685 SW Harvest"},{"97005"},{"Beaverton"},{"OR"},{"5036442191"},{""},{""}},
                {{"YMCA of Columbia-Willamette"},{"sherwood@ymcaw.org"},{"ymcacw.org"},{""},{""},{""},{""},{"Centro juvenil"},{"23000 SW Pacific Hwy."},{"97140"},{"Sherwood"},{"OR"},{"5036259622"},{""},{""}},
                {{"Youth Contact Incorporated"},{"youthcontact@youthcontact.org"},{"http://www.youthcontact.org"},{"Gratis"},{"Programas anti-analfabetismo, información de seguros médicos"},{""},{"Debe estar en una escuela pública en el Condado de Washington para programa de deserción. OHP y algunos seguros privados aceptados. Algunos servicios gratuitos disponibles para niños no asegurados y los jóvenes hasta los 19 años de edad. Despensas disponibles martes/miércoles de 130 horas a 630 horas."},{"Programas de abandono", "Consejería", "despensas", "Programas de orden judicial para el cuidado de los niños"},{"447 SE Baseline St."},{"97123"},{"Hillsboro"},{"OR"},{"5036404222"},{"Jóvenes"},{"Inglés"}}};
       /*
        Nombre de la Compañía,Correo Electrónico,Página Web,Costo,Servicios Adicionales,Asistencia,Otros puntos importantes,Categoría,Dirección,Código Postal,Ciudad,Teléfono,Población,Lenguaje
YMCA of Columbia-Willamette,sherwood@ymcaw.org,ymcacw.org,,,,,Centro juvenil,23000 SW Pacific Hwy.,97140,Sherwood,503-625-9622,,
Youth Contact Incorporated,youthcontact@youthcontact.org,http://www.youthcontact.org,Gratis,"Programas anti-analfabetismo, información de seguros médicos",,Debe estar en una escuela pública en el Condado de Washington para programa de deserción. OHP y algunos seguros privados aceptados. Algunos servicios gratuitos disponibles para niños no asegurados y los jóvenes hasta los 19 años de edad. Despensas disponibles martes/miércoles de 130 horas a 630 horas.,"Programas de abandono, consejería, despensas, Programas de orden judicial para el cuidado de los niños",,97123,"447 SE Baseline St. Hillsboro, OR",503-640-4222,Jóvenes,Inglés
        */
        Log.d("testing", "git");
        //{{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}}};
        //{{"Bethel Congregation"},{""},{"faithcafeor.org"},{"Gratis"},{"comidas los domingos a las 5pm y el último jueves de cada mes a las 5 pm"},{""},{"Abierto los lunes de 11:30am-12:30pm miercoles de 6:30pm a 7:30pm, Jueves de 11:30-12:30h. Llamar para cita."},{"Despensa de Alimentos/Comidas"},{"5150 SW Watson Ave."},{"97005"},{"Beaverton"},{"OR"},{"5035919025"},{""},{""}},
        //{{"Bienestar"},{"elmpark@cascade-management.com"},{"http://bienestar-or.org/web/housing/elmpark-forestgrove.asp"},{""},{"Localizada en dos sitios del Forest Grove. Provee viviendas de alquiler alcanzables para los trabajadores agrícolas y familias trabajadoras de bajos ingresos. Se ofrecen programas de servicios a residentes, que incluyen ayuda con recursos y referencias, (A inquilinos solamente ) ESL , GED y clases de computación , club del empleo, jardines comunitarios y actividades para niños. También ofrecen un programa de \" Promotores \" (Conectores de la Comunidad) para los residentes."},{""},{""},{"Hospedaje"},{"2350 and 2351 Elm Street"},{"97116"},{"Forest Grove"},{"OR"},{"5033594532"},{""},{"Inglés","Español"}},
        //{{"Care to Share"},{"info@caretosharehelp.org"},{"caretosharehelp.org"},{"Gratis"},{"Ayuda financiera con las utilidades.  Favor de llamar al 503-726-0407 para las utilidades y 503-924-3129 para el agua."},{""},{"Debe vivir en el siguiente código postal: 97003, 97005, 97006, 97007, 97008, 97078, 97123, 97124, 97223, 97224 y 97229."},{"comida","asistencia de útiles"},{"PO Box 397"},{"97075"},{"Beaverton"},{"OR"},{"5035919025"},{"Varios bancos de alimentos en toda la zona. Llamar a los servicios de su zona."},{""}},
        //{{"Community Action"},{"contact@caowash.org"},{"http://caowash.org/what_we_do/housing.html"},{"Gratis"},{"Ofrecimiento de servicios a familias veteranas, Ayuda con el envolvimiento de WIC y OHP, Clases , Defensa de la familia"},{""},{"Debe llamar al 503-640-3263 para ser agregado a la lista de espera para la vivienda. Los clientes tienen que solicitar una cita para ayuda a través de la página web, el tiempo de respuesta es de 1-2 días laborables. Las despensas son proporcionados en el Condado de Washington, el horario es de lunes-viernes de 530pm-9pm y fines de semana de 9AM-9PM. alquiler y los accesorios de asistencia se basan en los ingresos promedios en los siguientes lineamientos : http://caowash.org/what_we_do/energy-rent-assistance/rent-assistance.html. Proyecto de ley actual, o 72 horas de notificación de desalojo, o un nuevo contrato de arrendamiento en el mes en que se solicita ayuda debe ser proporcionada en el momento de la aplicación."},{"refugio", "Asistencia de emergencia de  renta","asistencia de energía eléctrica","Asistencia con beneficios patronales"},{"210 SE 12th Ave."},{"97123"},{"Hillsboro"},{"OR"},{"5036403263"},{"personas sin hogar persons con problemas mentales crónicos","Adultos","familias","Veterans","familias de Veteranos","Casa tradicional para mujeres y niños"},{"Inglés","Español"}},
        //{{"Community Counseling Center"},{""},{""},{""},{""},{""},{""},{"consejería"},{""},{""},{""},{"OR"},{"5027524620"},{"individuales","Parejas","familias"},{""}},
        //{{"Conxiones"},{""},{""},{""},{"Información de  recursos del habla hispana"},{""},{""},{"consejería"},{""},{""},{""},{"OR"},{"5032358057"},{""},{"Inglés","Español"}}};
        //{{"Dental Foundation of Oregon"},{"foundation@smileonoregon.org"},{"http://www.smileonoregon.org/"},{"Gratis"},{"Proyecciones, Limpiezas, sellados , Rayos X, Rellenos, Cirujía menor oral, Educación oral, herramientas para higine dental."},{""},{" Van dental que brinda atención dental gratuita a establecimientos escolares. Van dental puede ser solicitada, o pueden ser programadas deacuerdo a  el área http://www.smileonoregon.org/index.asp?N=dental-nonprofit-Wilsonville-OR-Tooth-Taxi-Schedule&C=401&P=3496."},{"servicios dentales"},{"8699 SW Sun Place"},{"97070"},{"Wilsonville"},{"OR"},{"5033298877"},{"niños no deseados y sin seguro."},{"Inglés"}},
        //{{"Depression/Bi-Polar Support"},{""},{""},{""},{""},{""},{""},{"grupos de ayuda"},{""},{""},{""},{""},{"50364401679"},{"individuales","familias","friends"},{""}},
        //{{"Donated Dental Services Program"},{"dbowman@nsdh.org"},{"http://dentallifeline.org"},{"Gratis"},{"tratamiento dental integral (no de emergencia)"},{""},{"En lista de espera puede ser tan largo como un año. La gente no debe tener seguro dental."},{"servicios dentales"},{"PO Box 690"},{"97070"},{"Wilsonville"},{"OR"},{"5035940837"},{"Adultos de bajos recursos","de mas de 65 años","Personas con discapacidades"},{"Inglés"}},
        //{{"Family Promise"},{"familypromiseannieheart@gmail.com"},{"http://www.Familypromisewashingtoncountyoregon.org"},{"Gratis"},{""},{""},{"Debe llamar al 503-640-3263 para ser agregado a la lista de espera para la vivienda."},{"refugio","comidas"},{"183 SE 6th Ave."},{"97123"},{"Hillsboro"},{"OR"},{"5036403263"},{"familias con  niños unicamente"},{"Inglés"}},
        //{{"Holy Trinity Community Outreach"},{""},{""},{"Gratis"},{""},{""},{"Abierto Martes, Miercoles, Viernes de : 10am-12:30pm. Por favor llame para una cita."},{"despensas"},{"13715 SW Walker Rd."},{"97005"},{"Beavrton"},{"OR"},{"5035919025"},{""},{""}},
        //{{"Homeplate"},{"info@homeplateyouth.org"},{"homeplateyouth.org"},{"Gratis"},{"duchas calientes,accesorios de higiene personal, Comida para salir del apuro, ropa, bolsas para dormir/apoyo para acampar."},{""},{""},{"Centros de albergue"},{"1841 SW Merlo Dr."},{"97006"},{"Beaverton"},{"OR"},{"5038674954"},{"Jóvenes entre los 12-24 (y sus  niños)"},{""}}};
        //{{"Lifeworks NW"},{"intake@lifeworksnw.org"},{"http://www.lifeworksnw.org"},{"aceptan OHP, Comisiones reducidas, algunos servicios gratuitos(de Prevención) "},{"Terapia grupal e  Individual"},{""},{""},{"consejería de Salud mental","Evaluaciónes", "After School Programs", "Educación"},{"14600 NW Cornell Rd"},{"97229"},{"Portland"},{"OR"},{"5036459010"},{"niños","Adultos","Adolescents","familias","Adultos mayores"},{"Inglés","Español"}},
        //{{"Murray Hills Christian Church"},{""},{""},{"Gratis"},{""},{""},{"Abierto M 3:30pm a 5:30pm, W 9:30am-12:00pm, Sábados de 10:00am-2:00pm. Llame para una cita."},{"despensas"},{"15050 SW Weir Rd."},{"97007"},{"Beaverton"},{"OR"},{"5035919025"},{""},{""}},
        //{{"Pacific Psychology and Comprehensive Health Clinic"},{""},{""},{"precio móvil"},{"Evaluación services, naturopaths"},{""},{""},{"Consejería"},{""},{""},{""},{""},{"5033527333"},{"niños","Adolescentes","Adultos","Parejas"},{"Inglés","Español"}},
        //{{"Pacific University Counseling"},{"john.monahan@pacificu.edu (assessments only)"},{"http://www.pacificu.edu/our-resources/clinics/pacific-psychology-comprehensive-health-clinic"},{"aceptan OHP, costo reducido"},{"Servicios de salud mental para foráneos, consejería en grupos, de forma Individual, en parejas, Familires,  o para niños."},{""},{"Justificante de ingresos necesarios para reducir los honorarios, los ingresos anuales y el número de dependientes son los principales criterios en la determinación de honorarios de sesión. Tarifas generales son de $50 para una sesión de admisión para sesiones individuales de $20 y $10 para las sesiones de grupo. Los estudiantes y los veteranos son servicios ofrecidos por $10 por sesión. Copagos además son elegibles para descuentos basados en la necesidad. No hay crisis de ingesta. No primarios alcohol/drogas o el conservador conseling/tutor evaluaciones. Acepta OHP para el condado de Washington. Abierto lunes a jueves de 9AM-8PM, vierness 9AM-5PM. $600 tarifa plana para evaluaciones integrales y $350 para las evaluaciones abreviadas como add y adhd. Escuela Nacional de la naturopatía ofrece citas a través de nuestra clínica de servicios naturopaticos  y administración de la medicación, primeras citas son de $40 y el seguimiento de las citas son de $20. "},{"Consejería, Evaluación de salud mental"},{"222 SE 8th Ave., Suite 212"},{"97123"},{"Hillsboro"},{"OR"},{"5033527333"},{"Adultos","niños","familias","Parejas","individuales"},{"Inglés", "Español"}}};
        //{{"St. Francis Social Action"},{"jennifer@stfrancissherwood.org"},{"http://www.stfrancissherwood.org"},{""},{""},{"St. Francis ofrece una despensa de alimentos y una comida gratis semanal de la comunidad . El armario de ropas ofrece ropa y calzado"},{""},{"despensas"},{"15659 SW Oregon Street"},{"97140"},{"Sherwood"},{"OR"},{"5036257067"},{"residentes de bajos recursos de Sherwood, Tualatin y Tigard"},{""}},
        //{{"St. Lutheran SCAT"},{""},{""},{"Gratis"},{""},{""},{"Abierto Lunes 6:30-7:30pm, Martes 5:00-6:00pm, Miercoles 6:30-7:30h, 11:30am-12:30pm y las 6:30-7:30pm. Abierto el pasado domingo 1:00pm-2:00pm. No Abierto primer miércoles del mes."},{"despensas"},{"10390 SW Canyon Rd."},{"97005"},{"Beaverton"},{"OR"},{"5035919025"},{""},{""}},
        //{{"Sunset Prebsyterian Church - Helping Hands"},{""},{""},{"Gratis"},{""},{""},{"Abierto Miercoles 9:30am-12:00pm, Jueves 4:00-6:30pm. Por favor llame para una cita. Cerrado el 12/21-1/3 por  vacaciones"},{"despensas"},{"14986 NW Cornell Rd."},{"97229"},{"Portland"},{"OR"},{"5035919025"},{""},{""}},
        //{{"SVDP St. Cecilia"},{""},{""},{"Gratis"},{""},{""},{"Abierto de lunes a las 9:00 am-12:00 pm y las 6:00 pm a 7:30 pm, Martes a las 9:00 am-12:00 pm y las 6:00 pm a 7:30 pm, el miércoles a las 9:00 am-12:00 pm y las 6:00 pm a 7:30 pm, Jueves de 9:00 am-12:00 pm y las 6:00 pm a 7:30 pm, Viernes de 9:00 am-12:00 pm. Por favor llame para una cita."},{"despensas"},{"5120 SW Franklin Ave."},{"97005"},{"Beaverton"},{"OR"},{"5035919025"},{""},{""}}};
        //{{"SVDP St. Pius X (Cedar Mill)"},{""},{""},{"Gratis"},{""},{""},{"Abierto M 2:00-4:00pm, W 2:00-4:00pm, F 2:00-4:00pm. Por favor llame para una cita."},{"despensas"},{"1170 NW 123rd Ave."},{"97229"},{"Portland"},{"OR"},{"5035919025"},{""},{""}}};


        for (int i = 0; i < Organizations.length; ++i) {
            Log.e("I", String.valueOf(i));
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
                int temporar = 0;
                for (int j=0; j < Organizations[i][8].length;++j)
                {
                    temporar = temporar + Organizations[i][8][j].length();
                }
                if (temporar != 0) {
                    for (int j = 0; j < Organizations[i][8].length; ++j) {
                        // Inserts the Address informaton into the Address table
                        dbContext.execSQL("INSERT INTO " + TABLE_LOCATION + " ( " + FIELD_LOC_OID + "," +
                                FIELD_LOC_STREET + ", " +
                                FIELD_LOC_ZIP + ", " + FIELD_LOC_CITY + ", " + FIELD_LOC_STATE + ") "
                                + "VALUES ( " + maximum.toString() + ", '" + Organizations[i][8][j] + "', '" + Organizations[i][9][j]
                                + "', '" + Organizations[i][10][j] + "', '" + Organizations[i][11][j] + "');");
                    }
                }
                for (int j = 0; j < Organizations[i][12].length; ++j) {
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

                for (int j = 0; j < Organizations[i][14].length; ++j) {
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
    public void onOpen(SQLiteDatabase dbContext) {
        super.onOpen(dbContext);
        dbContext.execSQL("PRAGMA foreign_keys = ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbContext, int oldVersion, int newVersion) {
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
    public void addOrganization(String data[]) {
        Log.e("Test", "What are you waiting for?");
        SQLiteDatabase dbContext = this.getWritableDatabase();
        // If the data member is empty, we set it to null
        Log.e("Length Data", (new Integer(data.length)).toString());
        // If no Zip is entered, replaces data[9] with null
        if (Integer.getInteger(data[9]) == null) {
            data[9] = null;
        }
        if (Integer.getInteger(data[12]) == null) {
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
        if (maxKey.moveToFirst()) {
            Integer maximum = maxKey.getInt(0);
            maxKey.close();
            // If necessary, inserts a row into the Category table
            Cursor category = dbContext.rawQuery("SELECT " + FIELD_CAT_cName + " FROM " +
                    TABLE_CATEGORY + " AS C WHERE C." + FIELD_CAT_cName +
                    " LIKE '" + data[7] + "';", null);
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
            Cursor test = dbContext.rawQuery("SELECT * FROM " + TABLE_FALLS_IN, null);
            if (test.moveToFirst()) {
                Log.e("Falls In", test.getString(0) + ", " + test.getString(1));
                while (test.move(1)) {
                    Log.e("Falls In", test.getString(0) + ", " + test.getString(1));
                }
            }
            // Inserts the Address informaton into the Address table
            dbContext.execSQL("INSERT INTO " + TABLE_LOCATION + " ( " + FIELD_LOC_OID + "," +
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
            Log.e("1", "Where?");
            // If necessary, inserts a row into the Category table
            Cursor population = dbContext.rawQuery("SELECT " + FIELD_POP_TYPE + " FROM " +
                    TABLE_POPULATION + " AS P WHERE P." + FIELD_POP_TYPE +
                    " LIKE '" + data[13] + "';", null);
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
                    TABLE_LANGUAGE + " AS L WHERE L." + FIELD_LAN_LTYPE +
                    " LIKE '" + data[14] + "';", null);
            if (language.moveToFirst() == false) {
                dbContext.execSQL("INSERT INTO " + TABLE_LANGUAGE + " ( " +
                        FIELD_LAN_LTYPE + ") " + "VALUES ( '" +
                        data[14] + "');");
            }
            Log.e("4", "Failed");
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
    public void getOrganizations() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM Organization", null);
        if (results.moveToFirst()) {
            Log.e("ID", results.getString(0));
            Log.e("Name", results.getString(1));
            while (results.move(1)) {
                Log.e("ID", results.getString(0));
                Log.e("Name", results.getString(1));
            }
            results.close();
        }
        db.close();
    }

    // Queries the DataBase and returns a list of organizations with descriptions
    // Satisfying the Query.
    public Cursor[] search(String data[]) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results[] = new Cursor[6];
        String innerProduct = TABLE_ORGANIZATION + " O";
        String whereClause = "";
        boolean hasLoc = false;
//        Log.e("Data Length",String.valueOf(data.length));
        for (int i = 0; i < data.length; ++i) {
            Log.e("HERE", "HERE");
            Log.e("Data[i]", data.toString());
            if ((data[i].length() > 0)) {
                if (i == 1) {
                    innerProduct = innerProduct + ", " + TABLE_CATEGORY + " C";
                    innerProduct = innerProduct + ", " + TABLE_FALLS_IN + " F";
                    whereClause = whereClause + " AND O." + FIELD_ORG_OID + " = " + " F." + RELATION_FALLS_IN_OID;
                    whereClause = whereClause + " AND F." + RELATION_FALLS_IN_CNAME + " = " + " C." + FIELD_CAT_cName;
                    whereClause = whereClause + " AND C." + FIELD_CAT_cName + " LIKE '%" + data[i] + "%'";
                }
                if ((i >= 2) && (i <= 4) && (!hasLoc)) {
                    whereClause = whereClause + " AND O." + FIELD_ORG_OID + " = " + " LOC." + FIELD_ORG_OID;
                    innerProduct = innerProduct + ", " + TABLE_LOCATION + " LOC";
                    hasLoc = true;
                }
                if (i == 2) {
                    whereClause = whereClause + " AND LOC." + FIELD_LOC_ZIP + " LIKE '%" + data[i] + "%'";
                }
                if (i == 3) {
                    whereClause = whereClause + " AND LOC." + FIELD_LOC_CITY + " LIKE '%" + data[i] + "%'";
                }
                if (i == 4) {
                    whereClause = whereClause + " AND LOC." + FIELD_LOC_STATE + " LIKE '%" + data[i] + "%'";
                }
                if (i == 6) {
                    innerProduct = innerProduct + ", " + TABLE_POPULATION + " P";
                    innerProduct = innerProduct + ", " + TABLE_SERVES_PEOPLE + " SP";
                    whereClause = whereClause + " AND SP." + RELATION_SERVESP_OID + " = " + " O." + FIELD_ORG_OID;
                    whereClause = whereClause + " AND SP." + RELATION_SERVESP_TYPE + " = " + " P." + FIELD_POP_TYPE;
                    whereClause = whereClause + " AND P." + FIELD_POP_TYPE + " LIKE '%" + data[i] + "%'";
                }
                if (i == 7) {
                    innerProduct = innerProduct + ", " + TABLE_LANGUAGE + " L";
                    innerProduct = innerProduct + ", " + TABLE_SERVESL + " SL";
                    whereClause = whereClause + " AND SL." + RELATION_SERVES_LAN_OID + " = " + " O." + FIELD_ORG_OID;
                    whereClause = whereClause + " AND SL." + RELATION_SERVES_LAN_LTYPE + " = " + " L." + FIELD_LAN_LTYPE;
                    whereClause = whereClause + " AND L." + FIELD_LAN_LTYPE + " LIKE '%" + data[i] + "%'";
                }
            }
        }
        Log.e("Querry", "SELECT O." + FIELD_ORG_OID + ", O." + FIELD_ORG_NAME +
                ", O." + FIELD_ORG_EMAIL + ", O." + FIELD_ORG_WEBSITE +
                ", O." + FIELD_ORG_COST + ", O." + FIELD_ORG_ADDITIONAL
                + ", O." + FIELD_ORG_ASSISTANCE + ", O." + FIELD_ORG_OTHER
                + " FROM " + innerProduct + " WHERE O."
                + FIELD_ORG_NAME + " LIKE '%" + data[0] + "%'" +
                " AND O." + FIELD_ORG_COST + " LIKE '%" + data[5]
                + "%'" + whereClause + ";");
        Cursor Organization = db.rawQuery("SELECT O." + FIELD_ORG_OID + ", O." + FIELD_ORG_NAME +
                ", O." + FIELD_ORG_EMAIL + ", O." + FIELD_ORG_WEBSITE +
                ", O." + FIELD_ORG_COST + ", O." + FIELD_ORG_ADDITIONAL
                + ", O." + FIELD_ORG_ASSISTANCE + ", O." + FIELD_ORG_OTHER
                + " FROM " + innerProduct + " WHERE O."
                + FIELD_ORG_NAME + " LIKE '%" + data[0] + "%'" +
                " AND O." + FIELD_ORG_COST + " LIKE '%" + data[5]
                + "%'" + whereClause + ";", null);
        Cursor Language = null;
        Cursor Population = null;
        Cursor Category = null;
        Cursor Location = null;
        Cursor Phone = null;
        if (Organization.moveToFirst()) {
            Log.e("More", "stuff");
            String sLang = " SL." + RELATION_SERVES_LAN_OID + " = " + Organization.getString(0) +
                    " AND SL." + RELATION_SERVES_LAN_LTYPE + " = " + " L." + FIELD_LAN_LTYPE;
            String sPop = " SP." + RELATION_SERVESP_OID + " = " + Organization.getString(0) +
                    " AND SP." + RELATION_SERVESP_TYPE + " = " + " P." + FIELD_POP_TYPE;
            String sCat = " F." + RELATION_FALLS_IN_OID + " = " + Organization.getString(0) +
                    " AND F." + RELATION_FALLS_IN_CNAME + " = " + " C." + FIELD_CAT_cName;
            String sLoc = " LOC." + FIELD_LOC_OID + " = " + Organization.getString(0);
            String sPhone = " P." + FIELD_ORG_OID + " = " + Organization.getString(0);
            Log.e("Die", "1");
            while (Organization.move(1)) {
                Log.e("In", "While");
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
                    TABLE_CATEGORY + " C WHERE" + sCat, null);
            Population = db.rawQuery("SELECT P." + FIELD_POP_TYPE + ", SP." + RELATION_SERVESP_OID + " FROM " + TABLE_POPULATION + " P, " +
                    TABLE_SERVES_PEOPLE + " SP WHERE" + sPop, null);
            Language = db.rawQuery("SELECT L." + FIELD_LAN_LTYPE + ", SL." + RELATION_SERVES_LAN_OID + " FROM " + TABLE_LANGUAGE + " L, " +
                    TABLE_SERVESL + " SL WHERE" + sLang, null);
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

    public String[] searchOrg(String data) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor querryOut;
        String results[] = new String[13];
        for (int i = 0; i < 13; ++i) {
            results[i] = "";
        }
        // Gets stuff from main table
        querryOut = db.rawQuery("SELECT * FROM " + TABLE_ORGANIZATION + " O Where O." + FIELD_ORG_OID + " = " + data + ";", null);
        Log.e("and", "SELECT * FROM " + TABLE_ORGANIZATION + " O Where O." + FIELD_ORG_OID + " = '" + data + "';");
        if (querryOut.moveToFirst()) {
            Log.e("1", querryOut.getString(0));
            Log.e("2", querryOut.getString(1));
            for (int i = 0; i < 7; ++i) {
                results[i] = querryOut.getString(i + 1);
            }
        }
        // Gets the phone
        querryOut = db.rawQuery("Select P." + FIELD_PHONE_PHONENUM + " FROM " + TABLE_PHONE + " P WHERE P." + FIELD_ORG_OID + " = " + data + ";", null);
        if (querryOut.moveToFirst()) {
            results[7] = querryOut.getString(0);
        } else {
            results[7] = "-1";
        }
        // Gets the address
        querryOut = db.rawQuery("Select A." + FIELD_LOC_STREET + ", A." + FIELD_LOC_ZIP + ", A." + FIELD_LOC_CITY + ", A." + FIELD_LOC_STATE + " FROM " + TABLE_LOCATION + " A WHERE A." + FIELD_ORG_OID + " = " + data + ";", null);
        if (querryOut.moveToFirst()) {
            results[8] = querryOut.getString(0);
            results[9] = querryOut.getString(1);
            results[10] = querryOut.getString(2);
            results[11] = querryOut.getString(3);
        } else {
            results[8] = "";
        }
        return results;
    }

    ;

    public String[] allCategories() {
        Log.e("allCat","AllCat");
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct C." + FIELD_CAT_cName + " FROM " + TABLE_CATEGORY + " C;", null);
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

    ;

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

    public String[] allAges() {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct P." + FIELD_POP_TYPE + " FROM " + TABLE_POPULATION + " P;", null);
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

    ;

    public String[] allZips() {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct L." + FIELD_LOC_ZIP + " FROM " + TABLE_LOCATION + " L;", null);
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

    ;

    public String[] allCity() {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct L." + FIELD_LOC_CITY + " FROM " + TABLE_LOCATION + " L;", null);
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

    ;

    public String[] allState() {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct L." + FIELD_LOC_STATE + " FROM " + TABLE_LOCATION + " L;", null);
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

    ;

    public String[] allCost() {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct O." + FIELD_ORG_COST + " FROM " + TABLE_ORGANIZATION + " O;", null);
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

    ;

    public String[] allLanguage() {
        ArrayList<String> resultsList = new ArrayList<String>();
        String[] results;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor output = db.rawQuery("Select Distinct L." + FIELD_LAN_LTYPE + " FROM " + TABLE_LANGUAGE + " L;", null);
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
}