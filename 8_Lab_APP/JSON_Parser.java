
package database;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Lev
 */
public class JSON_Parser {

    public static Main.Personal_File   struct_Pers_File    = new Main.Personal_File();
    public static Main.Recruit         struct_Recruit      = new Main.Recruit();
    public static Main.Father_info     struct_Father_info  = new Main.Father_info();
    public static Main.Mother_info     struct_Mother_info  = new Main.Mother_info();

    public String name_file_json = "";
    public JSON_Parser(Main.Personal_File struct_Pers_File, Main.Recruit struct_Recruit, Main.Father_info
            struct_Father_info, Main.Mother_info struct_Mother_info, String name_file_json ) {
        JSON_Parser.struct_Father_info     = struct_Father_info;
        JSON_Parser.struct_Mother_info     = struct_Mother_info;
        JSON_Parser.struct_Pers_File       = struct_Pers_File;
        JSON_Parser.struct_Recruit         = struct_Recruit;

        this.name_file_json = name_file_json;
    }
    public static void JSON_Parse_Error(String Message)
    {
        System.out.println("Ошибка при добавлении поля" + Message);
    }
    public boolean JSON_parse()
    {
        JSONParser parser = new JSONParser();
        String data = "";


                    Object obj = null;
        try {
            obj = parser.parse(new FileReader("D:/NetBeans/DataBase/" + name_file_json));
        } catch (IOException ex) {
            System.out.println(name_file_json + ": не удается открыть файл конфигурации ");
            return false;
        } catch (org.json.simple.parser.ParseException ex) {
            System.out.println("data.json: ошибка при синтаксическом разборе. Проверьте правильность синтаксиса");
            return false;
        }
        JSONObject jsonObject = (JSONObject) obj;

        // разбор данных для таблицы PERSONAL_FILE
        JSONArray personalFile = (JSONArray) jsonObject.get("personalFile");
        Iterator<JSONObject> iterator = personalFile.iterator();
        while (iterator.hasNext()) {
            jsonObject = (JSONObject) iterator.next();
            // добавление id_recruit
            if ((data = (String) jsonObject.get("idRecruit")) != null)  struct_Pers_File.id_recruit = data;
            else { JSON_Parse_Error("PERSONAL_FILE:idRecruit"); return false; }
            // добавление id_file
            if ((data = (String) jsonObject.get("idFile")) != null)  struct_Pers_File.id_file = data;
            else { JSON_Parse_Error("PERSONAL_FILE:idFile"); return false; }
            // добавление registrDate
            if ((data = (String) jsonObject.get("registrDate")) != null)  struct_Pers_File.registr_date = data;
            else { JSON_Parse_Error("PERSONAL_FILE:registrDate"); return false; }

            System.out.println("Парсинг таблицы Personal_file: Успешно");
        }

        JSONObject jsonObject1 = (JSONObject) obj;
        // разбор данных для таблицы Recruit
        JSONArray recruit = (JSONArray) jsonObject1.get("recruit");
        iterator = recruit.iterator();
        while (iterator.hasNext()) {
            jsonObject = (JSONObject) iterator.next();
            // добавление id_recruit
            if ((data = (String) jsonObject.get("idRecruit")) != null)  struct_Recruit.id_recruit = data;
            else { JSON_Parse_Error("Recruit:idRecruit"); return false; }
            // добавление name
            if ((data = (String) jsonObject.get("name")) != null)  struct_Recruit.name = data;
            else { JSON_Parse_Error("Recruit:name"); return false; }
            // добавление birth_date
            if ((data = (String) jsonObject.get("birthDate")) != null)  struct_Recruit.birth_date = data;
            else { JSON_Parse_Error("Recruit:birthDate"); return false; }
            // добавление registrPlace
            if ((data = (String) jsonObject.get("registrPlace")) != null)  struct_Recruit.registr_place = data;
            else { JSON_Parse_Error("Recruit:registrPlace"); return false; }
            // добавление idFather
            if ((data = (String) jsonObject.get("idFather")) != null)  struct_Recruit.id_father = data;
            else { JSON_Parse_Error("Recruit:idFather"); return false; }
            // добавление idMother
            if ((data = (String) jsonObject.get("idMother")) != null)  struct_Recruit.id_mother = data;
            else { JSON_Parse_Error("Recruit:idMother"); return false; }
            if ((data = (String) jsonObject.get("idAcademic")) != null)  struct_Recruit.id_academic = data;
            else { JSON_Parse_Error("Recruit:idAcademic"); return false; }
            
            System.out.println("Парсинг таблицы Recruit:       Успешно");
        }

        JSONObject jsonObject2 = (JSONObject) obj;
        // разбор данных для таблицы Father_info
        JSONArray fatherInfo = (JSONArray) jsonObject2.get("fatherInfo");
        iterator = fatherInfo.iterator();
        while (iterator.hasNext()) {
            jsonObject = (JSONObject) iterator.next();
            // добавление idFather
            if ((data = (String) jsonObject.get("idFather")) != null)  struct_Father_info.id_father = data;
            else { JSON_Parse_Error("FatherInfo:idFather"); return false; }
            // добавление name
            if ((data = (String) jsonObject.get("name")) != null)  struct_Father_info.name = data;
            else { JSON_Parse_Error("FatherInfo:name"); return false; }
            // добавление birth_date
            if ((data = (String) jsonObject.get("birthDate")) != null)  struct_Father_info.birth_date = data;
            else { JSON_Parse_Error("FatherInfo:birthDate"); return false; }
            // добавление registrPlace
            if ((data = (String) jsonObject.get("registrPlace")) != null)  struct_Father_info.registr_place = data;
            else { JSON_Parse_Error("FatherInfo:registrPlace"); return false; }


            System.out.println("Парсинг таблицы Father_Info:   Успешно");
        }

        JSONObject jsonObject3 = (JSONObject) obj;
        // разбор данных для таблицы Mother_info
        JSONArray mother_info = (JSONArray) jsonObject3.get("motherInfo");
        iterator = mother_info.iterator();
        while (iterator.hasNext()) {
            jsonObject = (JSONObject) iterator.next();
            // добавление idMother
            if ((data = (String) jsonObject.get("idMother")) != null)  struct_Mother_info.id_mother = data;
            else { JSON_Parse_Error("Mother_info:idMother"); return false; }
            // добавление name
            if ((data = (String) jsonObject.get("name")) != null)  struct_Mother_info.name = data;
            else { JSON_Parse_Error("Mother_info:name"); return false; }
            // добавление birth_date
            if ((data = (String) jsonObject.get("birthDate")) != null)  struct_Mother_info.birth_date = data;
            else { JSON_Parse_Error("Mother_info:birthDate"); return false; }
            // добавление registrPlace
            if ((data = (String) jsonObject.get("registrPlace")) != null)  struct_Mother_info.registr_place = data;
            else { JSON_Parse_Error("Mother_info:registrPlace"); return false; }


            System.out.println("Парсинг таблицы Mother_info:   Успешно");
        }

        return true;
       }
}
 

